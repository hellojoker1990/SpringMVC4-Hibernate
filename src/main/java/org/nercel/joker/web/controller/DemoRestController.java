/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.controller.DemoRestController.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月1日 下午3:28:10 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月1日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nercel.joker.web.entity.Demo;
import org.nercel.joker.web.service.DemoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年9月1日 下午3:28:10 
 * @author Administrator  
 * @version V1.0                             
 */
@RestController
@RequestMapping("/demorest")
public class DemoRestController {

	private static final Logger logger = Logger
			.getLogger(DemoController.class);
	
	@Resource(name="demoService")
	private DemoService demoService;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8")
	public Demo findById(@PathVariable("id") Long id) {
		logger.info("DemoRestController findById id==" + id);
		return demoService.getById(1L);
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8",MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8"})
	public Demo save(@RequestBody Demo demo) {
		// update by id
		demoService.save(demo);
		return demo;
	}
	
	@RequestMapping(value = "/savexml", method = RequestMethod.POST, produces = {MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8"})
	public Demo saveXml(@RequestBody Demo demo) {
		// update by id
		demoService.save(demo);
		return demo;
	}
	
	
	
}


