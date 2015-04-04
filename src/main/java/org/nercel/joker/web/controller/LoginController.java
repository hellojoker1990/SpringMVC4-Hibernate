/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.controller.LoginController.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月4日 上午11:01:15 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月4日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年9月4日 上午11:01:15
 * 
 * @author Administrator
 * @version V1.0
 */
@Controller
public class LoginController {
	private static final Logger logger = Logger
			.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(String error, Model model) {
		logger.info("login");
		model.addAttribute("error", error);
		return "login";
	}
}
