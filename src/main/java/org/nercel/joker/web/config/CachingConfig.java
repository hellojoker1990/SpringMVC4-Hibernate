/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.CachingConfig.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 上午9:54:53 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.config;

import org.apache.log4j.Logger;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
/** 
 *Description: <启用缓存>. <br>
 *<p>
	<使用说明>
 </p>
 * @author Administrator  
 * @version V1.0                             
 */
@Configuration
@EnableCaching//<!-- 启用缓存注解 --> <cache:annotation-driven cache-manager="cacheManager" />
public class CachingConfig {
	private static final Logger logger = Logger.getLogger(CachingConfig.class);

	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource(
				"ehcache.xml"));
		return ehCacheManagerFactoryBean;
	}

	@Bean
	public CacheManager cacheManager() {
		logger.info("EhCacheCacheManager");
		EhCacheCacheManager cacheManager = new EhCacheCacheManager();
		cacheManager.setCacheManager(ehCacheManagerFactoryBean().getObject());
		return cacheManager;
	}
}


