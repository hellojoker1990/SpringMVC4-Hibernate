package org.nercel.joker.web.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.nercel.joker.web.security.CP_UserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionVoter;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年8月18日 上午11:09:27
 * 
 * @author Administrator
 * @version V1.0
 */
@SuppressWarnings("deprecation")
@Configuration
// @ImportResource("classpath:config/context/applicationContext-security.xml")
@EnableWebMvcSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger logger = Logger
			.getLogger(SpringSecurityConfig.class);

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 设置不拦截规则
		web.ignoring().antMatchers("/static/**", "/**/*.jsp");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 设置拦截规则
		// 自定义accessDecisionManager访问控制器,并开启表达式语言
		http.authorizeRequests().accessDecisionManager(accessDecisionManager())
				.expressionHandler(webSecurityExpressionHandler())
				.antMatchers("/**/*.do*").hasRole("USER")
				.antMatchers("/**/*.htm").hasRole("ADMIN").and()
				.exceptionHandling().accessDeniedPage("/login");

		// 开启默认登录页面
		// http.formLogin();

		// 自定义登录页面
		http.csrf().disable().formLogin().loginPage("/login")
				.failureUrl("/login?error=1")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("j_username")
				.passwordParameter("j_password").permitAll();

		// 自定义注销
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/login")
				.invalidateHttpSession(true);

		// session管理
		http.sessionManagement().sessionFixation().changeSessionId()
				.maximumSessions(1).expiredUrl("/");

		// RemeberMe
		http.rememberMe().key("webmvc#FD637E6D9C0F1A5A67082AF56CE32485");

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		// 设置内存用户角色
		// 自定义UserDetailsService
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				new Md5PasswordEncoder());

	}

	@Bean
	public CP_UserDetailsService userDetailsService() {
		logger.info("CP_UserDetailsService");
		CP_UserDetailsService userDetailsService = new CP_UserDetailsService();
		return userDetailsService;
	}

	@Bean
	public LoggerListener loggerListener() {
		logger.info("org.springframework.security.authentication.event.LoggerListener");
		LoggerListener loggerListener = new LoggerListener();

		return loggerListener;
	}

	@Bean
	public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
		logger.info("org.springframework.security.access.event.LoggerListener");
		org.springframework.security.access.event.LoggerListener eventLoggerListener = new org.springframework.security.access.event.LoggerListener();

		return eventLoggerListener;
	}

	/*
	 * 
	 * 这里可以增加自定义的投票器
	 */
	@Bean(name = "accessDecisionManager")
	public AccessDecisionManager accessDecisionManager() {
		logger.info("AccessDecisionManager");
		List<AccessDecisionVoter<? extends Object>> decisionVoters = new ArrayList<AccessDecisionVoter<? extends Object>>();
		decisionVoters.add(new RoleVoter());
		decisionVoters.add(new AuthenticatedVoter());
		decisionVoters.add(webExpressionVoter());// 启用表达式投票器

		AffirmativeBased accessDecisionManager = new AffirmativeBased(
				decisionVoters);

		return accessDecisionManager;
	}

	/*
	 * 表达式控制器
	 */
	@Bean(name = "expressionHandler")
	public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
		logger.info("DefaultWebSecurityExpressionHandler");
		DefaultWebSecurityExpressionHandler webSecurityExpressionHandler = new DefaultWebSecurityExpressionHandler();
		return webSecurityExpressionHandler;
	}

	/*
	 * 表达式投票器
	 */
	@Bean(name = "expressionVoter")
	public WebExpressionVoter webExpressionVoter() {
		logger.info("WebExpressionVoter");
		WebExpressionVoter webExpressionVoter = new WebExpressionVoter();
		webExpressionVoter.setExpressionHandler(webSecurityExpressionHandler());
		return webExpressionVoter;
	}

}
