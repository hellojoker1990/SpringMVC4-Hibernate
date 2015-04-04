/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.core.CP_SimpleMappingExceptionResolver.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月27日 下午5:51:32 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月27日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.core;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月27日 下午5:51:32 
 * @author Administrator  
 * @version V1.0                             
 */
public class CP_SimpleMappingExceptionResolver  extends SimpleMappingExceptionResolver{
	private static final Logger logger = Logger
			.getLogger(CP_SimpleMappingExceptionResolver.class);
	@Override
	protected ModelAndView getModelAndView(String viewName, Exception ex,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		logger.error("run time exception:", ex);//将异常信息打印到日志中
		return super.getModelAndView(viewName, ex, request);
	}

	

	
}

