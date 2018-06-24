package com.xrom.ssh.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xrom.ssh.entity.ApplyGoods;
import com.xrom.ssh.service.ApplyGoodsService;
import com.xrom.ssh.util.Result;

@Controller
@RequestMapping("/applyGoods")
public class ApplyGoodsController {
	@Autowired
	private ApplyGoodsService applyGoodsService;
	@RequiresRoles(value={"admin"})
	@RequestMapping("/findPage")
	@ResponseBody
	public Result findPage(ApplyGoods applyGoods,@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return applyGoodsService.findPage(applyGoods, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/findPageUser")
	@ResponseBody
	public Result findPageUser(ApplyGoods applyGoods,@RequestParam(defaultValue = "0") int pageIndex,
			@RequestParam(defaultValue = "15") int pageSize, String orderBy) {
		return applyGoodsService.findPageUser(applyGoods, pageIndex, pageSize, orderBy);
	}
	@RequestMapping("/save")
	@ResponseBody
	public Result add(Integer agentTypeId,
			String receiverAddress, String receiverName, String receiverPhone) {

		return applyGoodsService.save(agentTypeId,receiverAddress,
				receiverName, receiverPhone);
	}
	@RequiresRoles(value={"admin"})
	@RequestMapping("/active")
	@ResponseBody
	public Result active(Integer id) {

		return applyGoodsService.active(id);
	}
	@RequestMapping("/get")
	@ResponseBody
	public Result get(Integer id) {
		return applyGoodsService.get(id);
	}
}
