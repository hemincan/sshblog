package com.xrom.ssh.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.xrom.ssh.service.SysUserService;
import com.xrom.ssh.util.Result;
/**
 * shiro的登录和角色权限回调类
 *  
 *  @RequiresAuthentication:
 *	使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
	
	@RequiresGuest:
	使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录。
	
	@RequiresPermissions:
	示例同roles
	当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。如果当前Subject不具有这样的权限，则方法不会被执行。
	
	@RequiresRoles:
	示例
	@RequiresRoles({"user","admin"})
	//属于user或者admin之一;修改logical为OR 即可
	@RequiresRoles(value={"user","admin"},logical=Logical.OR)
	当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。如果当天Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
	
	@RequiresUser
	当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法
	
 * @author hemincan
 * @date 2018-6-11
 * @description
 */


public class UserRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService userService;
	
	/**
	 * 获取权限和角色
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// String username = (String) principals.getPrimaryPrincipal();
		String account = (String) principals.getPrimaryPrincipal();
//		System.out.println(account);
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		// 根据用户account来获取数据库中的权限和角色，加入到用户权限和角色中
		authorizationInfo.setRoles(userService.findUserRoles(account));
		authorizationInfo.setStringPermissions(userService.findUserPermisstions(account));
		return authorizationInfo;
	}

	/**
	 * 用户验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String account = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials()); //得到密码  
		Result result = null;
		result = userService.login(account, password);
		//可以自己判断username和password来判断用户是否符合要求
		SecurityUtils.getSubject().getSession().setAttribute("logininfo", result);
		if ("0".equals(result.getCode())){
			// 成功
			SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(account,password,getName());
			return authenticationInfo;
		} else {
//			System.out.println("登录失败！"); 
			//需要在外面Login处理异常
			return null;
		}
		
	}

}
