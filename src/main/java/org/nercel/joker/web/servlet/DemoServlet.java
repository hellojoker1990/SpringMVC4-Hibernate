/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.servlet.DemoServlet.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月20日 上午10:54:33 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月20日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月20日 上午10:54:33 
 * @author Administrator  
 * @version V1.0                             
 */
public class DemoServlet extends HttpServlet{

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -9187642226361597464L;
	private static final Logger logger = Logger
			.getLogger(DemoServlet.class);
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		logger.info("DemoServlet init start");
		
		
		logger.info("DemoServlet init end");
	}



	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		logger.info("DemoServlet service start");
		
		
		logger.info("DemoServlet service end");
	}

	
}


