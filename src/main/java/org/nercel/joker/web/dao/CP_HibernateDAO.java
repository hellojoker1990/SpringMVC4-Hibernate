/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.dao.hibernate.CP_HibernateDAO.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月18日 下午5:46:29 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月18日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月18日 下午5:46:29 
 * @author Administrator  
 * @version V1.0                             
 */
public interface CP_HibernateDAO {
	/**
	 * 
	 * 描述 : <根据主键获取实体。直接从数据库中查询>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * @param id
	 *            主键
	 * @return
	 */
	public Object get(Class<?> entityClazz, Serializable id, String... str);

	/**
	 * 
	 * 描述 : <根据主键获取实体。如果没有相应的实体，抛出异常。>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * @param id
	 *            主键
	 * @return
	 */
	public Object load(Class<?> entityClazz, Serializable id, String... str);

	/**
	 * 
	 * 描述 : <根据主键获取实体。如果没有相应的实体，抛出异常。>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * @param id
	 *            主键
	 * @return
	 */
	public Object findById(Class<?> entityClazz, Serializable id, String... str);

	/**
	 * 
	 * 描述 : <获取全部实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * 
	 * @return
	 */

	public List<?> loadAll(Class<?> entityClazz, String... str);

	/**
	 * 
	 * 描述 : <获取全部实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * 
	 * @return
	 */

	public List<?> findAll(Class<?> entityClazz, String... str);

	/**
	 * 
	 * 描述 : <更新实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entity
	 */
	public void update(Object entity);

	/**
	 * 
	 * 描述 : <存储实体到数据库>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entity
	 */
	public void save(Object entity);

	/**
	 * 
	 * 描述 : <增加或更新实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(Object entity);

	/**
	 * 
	 * 描述 : <增加或更新集合中的全部实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entities
	 */
	public void saveOrUpdateAll(Collection<?> entities);

	/**
	 * 
	 * 描述 : <删除指定的实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entity
	 */
	public void delete(Object entity);

	/**
	 * 
	 * 描述 : <根据主键删除指定实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param id
	 *            主键
	 * 
	 * @param entityClazz
	 *            实体类型
	 */
	public void deleteByKey(Serializable id, Class<?> entityClazz);

	/**
	 * 
	 * 描述 : <删除集合中的全部实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entities
	 */
	public void deleteAll(Collection<?> entities);

	

	// -------------------- HSQL ----------------------------------------------
	/**
	 * 
	 * 描述 : <使用HSQL语句直接增加、更新、删除实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param hql
	 *            操作语句hql
	 * @return
	 */
	public int bulkUpdate(String hql);

	/**
	 * 
	 * 描述 : <使用带参数的HSQL语句增加、更新、删除实体>. <br>
	 * <p>
	 * 例如：bulkUpdate("update Entity set username='abc' where id=?",
	 * Long.valueOf(id))
	 * </p>
	 * 
	 * @param hql
	 *            操作语句hql
	 * @param values
	 *            参数
	 * @return
	 */
	public int bulkUpdate(String hql, Object... values);

	/**
	 * 
	 * 描述 : <使用HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param hql
	 *            检索hql
	 * @return
	 */
	public List<?> findAllByHQL(String hql);

	/**
	 * 
	 * 描述 : <使用带参数的HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param hql
	 *            检索hql
	 * @param values
	 *            参数
	 * @return
	 */
	public List<?> find(String hql, Object... values);

	// -------------------------------- Criteria ------------------------------

	/**
	 * 
	 * 描述 : <创建与会话无关的检索标准对象>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * 
	 * @return
	 */
	public DetachedCriteria createDetachedCriteria(Class<?> entityClazz);

	/**
	 * 
	 * 描述 : <创建与会话绑定的检索标准对象>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entityClazz
	 *            实体类型
	 * @return
	 */
	public Criteria createCriteria(Class<?> entityClazz);

	/**
	 * 
	 * 描述 : <使用指定的检索标准检索数据>. <br>
	 * <p>
	 * 
	 * DetachedCriteria criteria = testHibernateDAO .createDetachedCriteria();
	 * criteria.add(Restrictions.eq("id", Long.valueOf(1015)));
	 * 
	 * </p>
	 * 
	 * @param criteria
	 * @return
	 */
	public List<?> findByCriteria(DetachedCriteria criteria);

	/**
	 * 根据条件准则查询所有相关记录列表
	 * 
	 * @param detachedCriteria
	 *            记录准则
	 * @return 数据列表
	 */
	public List<?> findAllByCriteria(DetachedCriteria detachedCriteria);

	/**
	 * 
	 * 描述 : <使用指定的检索标准检索数据，返回部分记录>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param criteria
	 * @param startIndex
	 *            开始索引值
	 * @param pageSize
	 *            每页显示记录值
	 * @return
	 */
	public List<?> findByCriteria(DetachedCriteria criteria, int startIndex,
			int pageSize);

	/**
	 * 
	 * 描述 : <使用指定的检索标准检索数据，返回指定范围的记录>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param criteria
	 * @return
	 */
	public Integer getTotalCountByCriteria(DetachedCriteria criteria);

	/**
	 * 
	 * 描述 : <使用指定的实体及属性检索（满足除主键外属性＝实体值）数据>. <br>
	 * <p>
	 * entity是已知的实体，propertyNames是该实体中的参数，组成propertyNames=entity的改属性值
	 * </p>
	 * 
	 * @param entity
	 *            实体
	 * @param propertyNames
	 *            参数名称
	 * 
	 * @param entityClazz
	 *            实体类型
	 * @return
	 */
	public List<?> findEqualByEntity(Object entity, String[] propertyNames,
			Class<?> entityClazz, String... str);

	

	/**
	 * 
	 * 描述 : <将自定义对象合并到Hibernate缓存对象>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param entity
	 *            自定义对象
	 * @return hibernate缓存对象
	 */
	public Object merge(Object entity);

	/**
	 * 
	 * 描述 : <强制初始化指定的实体>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param proxy
	 */
	public void initialize(Object proxy);

	/**
	 * 
	 * 描述 : <强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 */
	public void flush();

	/**
	 * 
	 * 描述 : <使用带命名的参数的HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param queryString
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List<?> findByNamedParam(String queryString, String[] paramNames,
			Object[] values);

	/**
	 * 
	 * 描述 : <使用命名的HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param queryName
	 * @return
	 */
	public List<?> findByNamedQuery(String queryName);

	/**
	 * 
	 * 描述 : <使用带参数的命名HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param queryName
	 * @param values
	 * @return
	 */
	public List<?> findByNamedQuery(String queryName, Object[] values);

	/**
	 * 
	 * 描述 : <使用带命名参数的命名HSQL语句检索数据>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 * 
	 * @param queryName
	 * @param paramNames
	 * @param values
	 * @return
	 */
	public List<?> findByNamedQueryAndNamedParam(String queryName,
			String[] paramNames, Object[] values);

	

	

	/**
	 * 描述 : <查询sql>. <br>
	 * <p>
	 * 
	 * @param sql
	 * @return
	 */
	public List<?> getListBySql(String sql);
	
	
	/**
	 * 描述 : <update sql>. <br>
	 * <p>
	 * 
	 * @param sql
	 * @return
	 */
	public int updateBySQL(String sql);
}


