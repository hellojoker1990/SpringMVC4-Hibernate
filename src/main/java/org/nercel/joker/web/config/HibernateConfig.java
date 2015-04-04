/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.config.DaoConfig.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年9月3日 下午2:55:14 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年9月3日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.nercel.joker.web.dao.impl.CP_Hibernate4DAOImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Description: <数据源相关bean的注册>. <br>
 * <p>
 * <使用说明>
 * </p>
 * Makedate:2014年9月3日 下午2:55:14
 * 
 * @author Administrator
 * @version V1.0
 */
@Configuration
// 启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfig.class })
public class HibernateConfig {

	private static final Logger logger = Logger.getLogger(HibernateConfig.class);

	@Value("${hibernate.dialect}")
	String hibernate_dialect;
	@Value("${hibernate.show_sql}")
	String hibernate_show_sql;
	@Value("${hbm2ddl.auto}")
	String hbm2ddl_auto;
	/**
	 * 描述 : <负责解析资源文件>. <br>
	 * <p>
	 * <这个类必须有，而且必须声明为static，否则不能正常解析>
	 * </p>
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		logger.info("PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Resource(name = "dataSource")
	public DataSource dataSource;

	@Bean(name = "sessionFactory")
	public LocalSessionFactoryBean localSessionFactoryBean() {
		logger.info("sessionFactory");
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		String[] packagesToScan = new String[] { "web.**.entity" };
		sessionFactory.setPackagesToScan(packagesToScan);

		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);
		hibernateProperties.setProperty("hibernate.show_sql",
				hibernate_show_sql);
		hibernateProperties.setProperty("hbm2ddl.auto", hbm2ddl_auto);
		hibernateProperties.setProperty(
				"hibernate.current_session_context_class",
				"org.springframework.orm.hibernate4.SpringSessionContext");
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;

	}

	@Bean(name = "hibernateDAO")
	public CP_Hibernate4DAOImpl hibernate4Dao() {
		logger.info("hibernateDAO");
		CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();
		dao.setSessionFactory(localSessionFactoryBean().getObject());
		return dao;
	}

	@Bean(name = "transactionManager")
	public HibernateTransactionManager hibernateTransactionManager() {
		logger.info("transactionManager");
		HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
		hibernateTransactionManager.setSessionFactory(localSessionFactoryBean()
				.getObject());
		return hibernateTransactionManager;
	}
}
