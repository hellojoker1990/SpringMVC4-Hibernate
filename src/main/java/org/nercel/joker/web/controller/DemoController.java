/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.controller.DemoController.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月15日 上午10:12:33 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月15日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nercel.joker.web.entity.Demo;
import org.nercel.joker.web.service.DemoDeclareParentsService;
import org.nercel.joker.web.service.DemoService;
import org.nercel.joker.web.utils.HttpClientUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Description: <类功能描述>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年8月15日 上午10:12:33
 * 
 * @author Administrator
 * @version V1.0
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

	private static final Logger logger = Logger.getLogger(DemoController.class);

	@Resource(name = "demoService")
	private DemoService demoService;

	@RequestMapping("/{id}")
	public String demo01(@PathVariable("id") Long id, Model model) {
		logger.info("demo01  id==" + id);
		model.addAttribute("id", id);
		return "demo";
	}

	/**                                                          
	* 描述 : <描述函数实现的功能>. <br> 
	*<p> 
		<@AuthenticationPrincipal,负责绑定验证后的用户>  
	 </p>                                                                                                                                                                                                                                                
	* @param user
	* @param id
	* @param model
	* @return                                                                                                      
	*/  
	@RequestMapping("/{id}.do")
	public String demo02(@PathVariable("id") Long id, Model model) {
		logger.info("demo02  id==" + id);
		model.addAttribute("id", id);
		return "demo";
	}

	@RequestMapping("/findall")
	public String handleList(Model model) {
		logger.info("demo02  handleList");
		List<Demo> list = demoService.findAll();
		model.addAttribute("results", list);
		return "demo";
	}

	@RequestMapping(value = "/xml/write/{id}.xml", produces = { MediaType.APPLICATION_XML_VALUE
			+ ";charset=UTF-8" })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Demo handleWriteXML(@PathVariable("id") Long id, Model model) {
		logger.info("handleWriteXML  id==" + id);
		Demo demo = demoService.getById(id);
		
		//@DeclareParents
		DemoDeclareParentsService declareParents = (DemoDeclareParentsService)demoService;
		logger.info("@DeclareParents===" + declareParents.printString("@DeclareParents is ok"));
		
		return demo;
	}

	@RequestMapping(value = "/xml/write/{id}/{name}.do", produces = { MediaType.APPLICATION_XML_VALUE
			+ ";charset=UTF-8" })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Demo handleWriteXMLDo(@PathVariable("id") Long id,
			@PathVariable("name") String name, Model model) {
		logger.info("handleWriteXMLDo  id==" + id);
		Demo demo = demoService.getByName(id, name);
		demoService.save(demo);// 捕获异常
		return demo;
	}

	@RequestMapping(value = "/json/write/{id}.json", produces = { MediaType.APPLICATION_JSON_VALUE
			+ ";charset=UTF-8" })
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Demo handleWriteJSON(@PathVariable("id") Long id, Model model) {
		logger.info("handleWriteJSON  id==" + id);
		Demo demo = demoService.getById(id);
		return demo;
	}

	@RequestMapping("/jsonmock")
	public void handleJsonMock() {
		// 因为使用了属性拦截器对日期字符串yyyy-MM-dd自动转换为date类型
		String requestBody = "{\"name\":\"王五\",\"age\":30,\"createDate\":\"2014-08-21\",\"modifyDate\":\"2014-08-30\"}";
		try {
			String result = HttpClientUtil.httpPost(
					"http://localhost:8080/webmvc/demorest/save", requestBody,
					MediaType.APPLICATION_JSON_VALUE);
			logger.info("result======" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/xmlmock")
	public void handleXmlMock() {
		// 因为使用了属性拦截器对日期字符串yyyy-MM-dd自动转换为date类型
		String requestBody = "<demo><age>40</age><createDate>2014-08-20</createDate><modifyDate>2014-08-28</modifyDate><name>赵四</name></demo>";
		try {
			String result = HttpClientUtil.httpPost(
					"http://localhost:8080/webmvc/demorest/savexml",
					requestBody, MediaType.APPLICATION_XML_VALUE);
			logger.info("result======" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("/flashcache")
	public void handleFlashCache() {
		logger.info("==========handleFlashCache==============");
		demoService.delete(new Demo());

	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String handleSave(Demo demo , Model model) {
		logger.info("==========handleSave==============");
		demoService.save(demo);
		model.addAttribute("demo", demo);
		model.addAttribute("id", demo.getId());
		return "demo";
		
	}
	
	@RequestMapping(value = "/presave")
	public String handlePreSave(Model model) {
		logger.info("==========handlePreSave==============");
		
		return "savedemo";
		
	}
}
