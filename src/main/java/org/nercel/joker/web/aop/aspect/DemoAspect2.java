/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.aop.aspect.DemoAspect2.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 上午11:30:47 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年9月3日 上午11:30:47 
 * @author Administrator  
 * @version V1.0                             
 */
@Component
//声明这是一个切面Bean
@Aspect
//@Aspect放在类头上，把这个类作为一个切面，但是这个类一定要显式的注册在Spring容器中。
@Order(2)
public class DemoAspect2 {
	private static final Logger logger = Logger.getLogger(DemoAspect2.class);
	
	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
		@Pointcut("execution (* web.function.service.*.*Service*.*(..))")
		// @Pointcut放在方法头上，定义一个可被别的方法引用的切入点表达式。
		public void aspect() {
			logger.info("DemoAspect @Pointcut");
		}

	@Before("aspect()")
	// @Before，前置通知，放在方法头上。
	public void before(JoinPoint joinPoint) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect2 before " + joinPoint);
		}
	}
}


