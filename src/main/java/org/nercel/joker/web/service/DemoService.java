/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.service.DemoService.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月19日 下午2:35:27 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月19日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.service;

import java.util.List;

import org.nercel.joker.web.entity.Demo;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月19日 下午2:35:27 
 * @author Administrator  
 * @version V1.0                             
 */

public interface DemoService {

	public List<Demo> findAll();
	
	public Demo getById(long id);
	
	public void save(Demo demo);
	
	public void delete(Demo demo);
	
	public Demo getByName(long id,String name);
}


