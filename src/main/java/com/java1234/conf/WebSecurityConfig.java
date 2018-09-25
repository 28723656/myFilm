package com.java1234.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security配置
 * @author xr
 *
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	/**
	 * 配置用户认证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		    .withUser("java1234")
		    .password("123456")
		    .roles("ADMIN");
	}

	/**
	 * 配置请求授权
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().disable().headers().disable()
		.authorizeRequests()
		.antMatchers("/","/static/**").permitAll()   //不需要身份认证的请求地址
		.anyRequest().authenticated()     //  其他所有访问路径需要身份认证
		.and()
		.formLogin()
		.loginPage("/login")  //制定登陆请求地址
		.defaultSuccessUrl("/admin" )  // 登陆成功后的默认跳转页面
		.permitAll() 
		.and()
		.logout()
		.logoutSuccessUrl("/login")
		.permitAll();
	}

	
}