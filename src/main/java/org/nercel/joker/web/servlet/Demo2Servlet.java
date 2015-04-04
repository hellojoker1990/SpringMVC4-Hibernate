/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.servlet.Demo2Servlet.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月27日 下午2:56:31 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月27日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.nercel.joker.web.service.DemoService;
import org.springframework.stereotype.Controller;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年8月27日 下午2:56:31
 * 
 * @author Administrator
 * @version V1.0
 */
@Controller("/demo_servlet2")
public class Demo2Servlet extends HttpServlet {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 8975959920935824256L;

	private static final String CONTENT_TYPE = "application/xml; charset=UTF-8";

	private static final Logger logger = Logger.getLogger(DemoServlet.class);

	@Resource(name = "demoService")
	private DemoService demoService;
	
	@PostConstruct  //init-method="init"
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		logger.info("Demo2Servlet init start");
		
		
		logger.info("Demo2Servlet init end");
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		logger.info("Demo2Servlet service start");

		response.setContentType(CONTENT_TYPE);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String result = demoService.getById(1l).getName();
		//Integer.valueOf(result); //测试异常显示页面
		PrintWriter out = response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<root>\n");
		out.println("<result>" + result + "</result>\n");
		out.println("</root>\n");

		out.flush();
		out.close();

		logger.info("Demo2Servlet service end");
	}

	@PreDestroy //destroy-method="destroy"
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		logger.info("Demo2Servlet destroy start");
		
		
		logger.info("Demo2Servlet destroy end");
	}
	
	
	
}
