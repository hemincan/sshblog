package com.xrom.ssh.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xrom.ssh.entity.AgentTree;
import com.xrom.ssh.entity.AgentType;
import com.xrom.ssh.entity.Bonus;
import com.xrom.ssh.entity.Message;
import com.xrom.ssh.entity.SysUser;

@Component
public class TimedTask {
	@Autowired
	SessionFactory sessionFactory;

	/*
	 * 计算对碰奖
	 */
//	 @Scheduled(cron="1/50 * * * * ?")
	@Scheduled(cron = "0 0 4 * * ?")
	// 每天早上三点执行s
	public void caculateCollision() {
		Calendar now = Calendar.getInstance();
		// System.out.println("年: " + now.get(Calendar.YEAR));
		// System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
		// System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		// System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
		// System.out.println("分: " + now.get(Calendar.MINUTE));
		// System.out.println("秒: " + now.get(Calendar.SECOND));
		Integer day = now.get(Calendar.DAY_OF_MONTH);
		System.out.println("自动任务" + day);

		 if (day == 8 || day == 18 || day == 28) {
//		if (true) {// 先改成每天碰一次
			System.out.println("计算对碰");
			// 计算对碰
			Session session = sessionFactory.openSession();
			Query q = session.createQuery("from " + AgentTree.class.getName() + " as c where c.leftPerformance>0 and c.rightPerformance>0 ");
			List<AgentTree> agentTreelist = q.list();
			for (int i = 0; i < agentTreelist.size(); i++) {
				Integer userId = agentTreelist.get(i).getUserId();
				SysUser user = (SysUser) session.get(SysUser.class, userId);
				AgentTree agentTree = agentTreelist.get(i);
				Integer left = agentTree.getLeftPerformance();
				Integer right = agentTree.getRightPerformance();
				left = left == null ? 0 : left;
				right = right == null ? 0 : right;
				Integer money = 0;
				if (left < right) {
					agentTree.setLeftPerformance(0);
					agentTree.setRightPerformance(right - left);
					money = left;
				} else {
					agentTree.setLeftPerformance(left - right);
					agentTree.setRightPerformance(0);
					money = right;
				}

				session.saveOrUpdate(agentTree);
				session.flush();
				// money就是可以用来计算的钱
				Integer collisionIntegral = money;
				AgentType agentType = (AgentType) session.get(AgentType.class,
						user.getAgentTypeId());
				double collisionPer = agentType.getCollisionPer();
				money = (int) Math.round(money * collisionPer);
				if (money > agentType.getTopReward()) {
					money = agentType.getTopReward();
				}
				// 发奖金
				if (money != 0) {

					Bonus bonus = new Bonus();
					bonus.setBonusType("对碰奖金");
					bonus.setObtainDate(new Date());
					bonus.setUserId(userId);

					bonus.setCollisionIntegral(collisionIntegral);
					bonus.setCollisionRatio(agentType.getCollisionPer());
					bonus.setCollisionTop(agentType.getTopReward());
					bonus.setMoney(money);
					// bonus.setAgentAccount(user.getAccountNumber());
					// bonus.setAgentName(user.getUserName());
					bonus.setRightPerformance(right);
					bonus.setLeftPerformance(left);
					bonus.setState(1);// 这个代理金未经过审核，并不是真的获得了代理金
					session.saveOrUpdate(bonus);
					session.flush();
				}

				if (user.getBalance() == null) {
					user.setBalance(0);
				}
				user.setBalance(user.getBalance() + money);
				session.saveOrUpdate(user);
				session.flush();
				// //////////////////
				// 发一条消息告诉用户它获得了这个奖金
				if (money != 0) {
					Message message = new Message();
					message.setReaded(false);
					message.setReceiveDate(new Date());
					message.setUserId(user.getId());
					String content = "恭喜你，获得对碰奖金:" + money + "元，已经成功发放到您的帐户。";
					message.setContent(content);
					session.saveOrUpdate(message);
					session.flush();
					
				}
				// //////////////////
				session.flush();
			}
			session.close();
		}

	}
}
