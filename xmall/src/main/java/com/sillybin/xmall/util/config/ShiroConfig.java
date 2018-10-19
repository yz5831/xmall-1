package com.sillybin.xmall.util.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sillybin.xmall.util.ShiroDBRealmUtil;

@Configuration
public class ShiroConfig {
	@Bean
	public ShiroDBRealmUtil shiroDBRealmUtil() {
		return new ShiroDBRealmUtil();
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroDBRealmUtil());
		return securityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		
		shiroFilter.setLoginUrl("/user/login");
		shiroFilter.setSuccessUrl("/");
		
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/user/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");
		
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilter;
	} 
}
