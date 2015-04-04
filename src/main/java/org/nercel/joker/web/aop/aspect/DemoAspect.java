/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.aop.aspect.DemoAspect.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月29日 下午3:25:40 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月29日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.aop.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.nercel.joker.web.service.DemoDeclareParentsService;
import org.nercel.joker.web.service.impl.DemoDeclareParentsServiceImpl;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年8月29日 下午3:25:40
 * 
 * @author Administrator
 * @version V1.0
 */
@Component
// 声明这是一个切面Bean
@Aspect
// @Aspect放在类头上，把这个类作为一个切面，但是这个类一定要显式的注册在Spring容器中。
@Order(1)//多个aop作用于同一个贴入点时的先后顺序，数值越小越靠前
public class DemoAspect {

	private static final Logger logger = Logger.getLogger(DemoAspect.class);

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution (* web.function.service.*.*Service*.*(..))")
	// @Pointcut放在方法头上，定义一个可被别的方法引用的切入点表达式。
	public void aspect() {
		logger.info("DemoAspect @Pointcut");
	}

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	// @Before，前置通知，放在方法头上。
	public void before(JoinPoint joinPoint) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect before " + joinPoint);
		}
	}
	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()") //按声明的先后顺序执行
	// @Before，前置通知，放在方法头上。
	public void before2(JoinPoint joinPoint) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect before2 " + joinPoint);
		}
	}

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()&&args(id,..)")
	// 只拦截第一个参数包含long参数的方法，并将该参数赋值给当前id，以便于在拦截方法中使用 @After，后置【finally】通知，放在方法头上。
	public void after(JoinPoint joinPoint, long id) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect after " + joinPoint);

			logger.info("DemoAspect args(id): " + id);

			// 返回方法参数value
			for (Object object : joinPoint.getArgs()) {
				logger.info("arg value:" + object);
			}
			// 返回返回目标,被拦截的类对象
			logger.info("被拦截的类对象:" + joinPoint.getTarget());
			// 返回正在被通知的方法相关信息
			logger.info("被拦截的类对象的执行方法:" + joinPoint.getSignature());

			Signature signature = joinPoint.getSignature();
			logger.debug("DeclaringType:" + signature.getDeclaringType());
			logger.debug("DeclaringTypeName:"
					+ signature.getDeclaringTypeName());
			logger.debug("Modifiers:" + signature.getModifiers());
			logger.debug("Name:" + signature.getName());
			logger.debug("LongString:" + signature.toLongString());
			logger.debug("ShortString:" + signature.toShortString());

			// 打印出正在被通知的方法的有用信息
			logger.info("打印出正在被通知的方法的有用信息:" + joinPoint.toString());
		}
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	// @Around，环绕通知，放在方法头上，这个方法要决定真实的方法是否执行，而且必须有返回值。
	public Object around(JoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		Object object = null;
		try {
			object = ((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info("DemoAspect around " + joinPoint + "\tUse time : "
						+ (end - start) + " ms!");
			}
		} catch (Throwable e) {
			long end = System.currentTimeMillis();
			if (logger.isInfoEnabled()) {
				logger.info("DemoAspect around " + joinPoint + "\tUse time : "
						+ (end - start) + " ms with exception : "
						+ e.getMessage());
			}
		}

		return object;
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning(pointcut = "aspect()", returning = "object")
	// @AfterReturning，后置【try】通知，放在方法头上，使用returning来引用方法返回值,这里将被拦截方法的返回值绑定到object上。
	public void afterReturn(JoinPoint joinPoint, Object object) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect afterReturn " + joinPoint);
			logger.info("DemoAspect afterReturn Object : " + object);
			
		}

	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	// 由于@Around已经对异常进行了处理，这里不会再次捕获异常
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	// @AfterThrowing，后置【catch】通知，放在方法头上，使用throwing来引用抛出的异常，这里将异常绑定到ex上。
	public void afterThrow(JoinPoint joinPoint, Exception ex) {
		if (logger.isInfoEnabled()) {
			logger.info("DemoAspect afterThrow " + joinPoint + "\t"
					+ ex.getMessage());
		}
	}
	
	
	
	/** 
	* @Fields declareParents : TODO(为web.function.service.DemoService接口的所有实现类织入DemoDeclareParentsServiceImpl实现类的功能)
	* value：拦截切入点    defaultImpl：默认的实现类  
	* 声明的对象declareParents是要实现的接口
	* 使用时可以通过强制类型转换将 DemoService 强制转换成DemoDeclareParentsService
	*/ 
	@DeclareParents(value="web.function.service.DemoService+",defaultImpl=DemoDeclareParentsServiceImpl.class)
	public static DemoDeclareParentsService declareParents;
}
