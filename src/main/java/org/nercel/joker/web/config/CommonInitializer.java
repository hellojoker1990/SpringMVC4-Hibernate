/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.Log4jInitializer.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月18日 下午5:34:08 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月18日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.nercel.joker.web.servlet.DemoServlet;
import org.springframework.core.annotation.Order;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

/** 
 *Description: <web.xml通用设置>. <br>
 *<p>
	<配置相关Listener，servlet，filter等等>
 </p>
 *Makedate:2014年8月18日 下午5:34:08 
 * @author Administrator  
 * @version V1.0                             
 */
@Order(1)
public class CommonInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		
		//Log4jConfigListener
		servletContext.setInitParameter("log4jConfigLocation", "classpath:properties/log4j.properties");
		servletContext.addListener(Log4jConfigListener.class);
		
		
		//OpenSessionInViewFilter
		OpenSessionInViewFilter hibernateSessionInViewFilter = new OpenSessionInViewFilter();
		FilterRegistration.Dynamic filterRegistration = servletContext.addFilter(
				"hibernateFilter", hibernateSessionInViewFilter);
		filterRegistration.addMappingForUrlPatterns(
				EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE), false, "/");
		
		
		//DemoServlet
		DemoServlet demoServlet = new DemoServlet();
		ServletRegistration.Dynamic dynamic = servletContext.addServlet(
				"demoServlet", demoServlet);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/demo_servlet");
		
		
		
		
		
		
		
	}

	
}


