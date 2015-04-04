/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.service.impl.DemoDeclareParentsServiceImpl.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 上午10:25:38 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.service.impl;

import org.nercel.joker.web.service.DemoDeclareParentsService;
import org.springframework.stereotype.Service;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年9月3日 上午10:25:38 
 * @author Administrator  
 * @version V1.0                             
 */
@Service
public class DemoDeclareParentsServiceImpl implements DemoDeclareParentsService{

	@Override
	public String printString(String mes) {
		// TODO Auto-generated method stub
		return "DemoDeclareParentsServicec printString==" + mes;
	}

}


