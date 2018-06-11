package com.xrom.ssh.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/user/login", "anon");// 允许登录匿名访问
		filterChainDefinitionMap.put("/**", "authc");
//		filterChainDefinitionMap.put("/**", "anon");
		//下面这个无效，加了也不跳转，只能在springmvc里面做异常拦截
		//shiroFilterFactoryBean.setUnauthorizedUrl("/user/authorityerror"); // 没有权限跳转
        shiroFilterFactoryBean.setLoginUrl("/user/authorityerror");//如果没有权限没有登录访问，跳转到这个url获取json数据
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	@Bean
	public UserRealm userRealm() {
		UserRealm realm = new UserRealm();
		return realm;
	}

	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm());
		return securityManager;
	}

}
