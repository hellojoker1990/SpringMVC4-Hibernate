/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.service.impl.DemoServiceImpl.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月19日 下午2:37:27 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月19日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.nercel.joker.web.dao.CP_HibernateDAO;
import org.nercel.joker.web.entity.Demo;
import org.nercel.joker.web.service.DemoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月19日 下午2:37:27 
 * @author Administrator  
 * @version V1.0                             
 */
@Service("demoService")
//启用注解事务，默认策略是所有方法都必须在事务中运行，必须声明在实现类上，接口声明无效
@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
//不加事务会报异常：No Session found for current thread，所以不能使用@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class DemoServiceImpl implements DemoService{
	
	private static final Logger logger = Logger
			.getLogger(DemoServiceImpl.class);
	
	@Resource(name="hibernateDAO")
	private CP_HibernateDAO dao;

	@SuppressWarnings("unchecked")
	@Override
	public List<Demo> findAll() {
		// TODO Auto-generated method stub
		logger.info("List<Demo> findAll()");
		return (List<Demo>)dao.findAll(Demo.class);
	}

	@Override
	@Cacheable(value="commonCache",key="#id + 'DemoServiceImpl.getById'") 
	public Demo getById(long id) {
		// TODO Auto-generated method stub
		logger.info("Demo getById(int id)|| id==" + id);
		return (Demo)dao.findById(Demo.class, id);
	}

	
	@Override
	@Cacheable(value="commonCache",key="#id + '_' + #name + 'DemoServiceImpl.getByName'") 
	public Demo getByName(long id, String name) {
		// TODO Auto-generated method stub
		logger.info("Demo getByName(int id, String name)|| id==" + id + "||name==" + name);
		return (Demo)dao.findById(Demo.class, id);
	}

	@Override
	@CacheEvict(value="commonCache",key="#demo.id + 'DemoServiceImpl.getById'")  
	public void save(Demo demo) {
		// TODO Auto-generated method stub
		//测试异常拦截
//		Integer.valueOf("hei");
		
		dao.save(demo);
	}

	@Override
	@CacheEvict(value="commonCache",allEntries=true) 
	public void delete(Demo demo) {
		// TODO Auto-generated method stub
		
	}

	
	

}


