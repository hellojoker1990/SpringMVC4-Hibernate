/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.DataSourceConfig.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 下午5:24:02 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年9月3日 下午5:24:02 
 * @author Administrator  
 * @version V1.0                             
 */
@Configuration
//加载资源文件
@PropertySource({"classpath:/properties/db.properties"})
public class DataSourceConfig {
	private static final Logger logger = Logger.getLogger(DataSourceConfig.class);
	/*
	 * 绑定资源属性
	 */
	@Value("${jdbc.driver}")
	String driverClass;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String userName;
	@Value("${jdbc.password}")
	String passWord;
	
	@Bean(name = "dataSource")
	public DataSource dataSource() {
		logger.info("DataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(passWord);
		return dataSource;
	}
}


