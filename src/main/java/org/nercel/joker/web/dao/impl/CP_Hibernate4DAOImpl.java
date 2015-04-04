/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.dao.hibernate.impl.CP_Hibernate4DAOImpl.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月18日 下午5:51:16 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月18日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.nercel.joker.web.dao.CP_HibernateDAO;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月18日 下午5:51:16 
 * @author Administrator  
 * @version V1.0                             
 */
public class CP_Hibernate4DAOImpl implements CP_HibernateDAO{
		
		/**
		 * @Fields logger : TODO(log日志)
		 */
		private Logger logger = Logger.getLogger(CP_Hibernate4DAOImpl.class);
		
		private SessionFactory sessionFactory;

		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}

		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}
		
		private void lazyInitialize(Class<?> entityClazz, List<?> l, String[] fields) {
			if (fields != null) {
				for (String field : fields) {

					String targetMethod = "get" + upperFirstWord(field);

					Method method;
					try {
						method = entityClazz.getDeclaredMethod(targetMethod);
						for (Object o : l) {
							Hibernate.initialize(method.invoke(o));
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}

		private void lazyInitialize(Class<?> entityClazz, Object obj,
				String[] fields) {
			if (fields != null) {
				for (String field : fields) {

					String targetMethod = "get" + upperFirstWord(field);

					Method method;
					try {
						method = entityClazz.getDeclaredMethod(targetMethod);
						Hibernate.initialize(method.invoke(obj));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		private String upperFirstWord(String str) {
			StringBuffer sb = new StringBuffer(str);
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
			return sb.toString();
		}
		
		private Session getHibernateSession(){
			Session session = sessionFactory.getCurrentSession();
			return session;
		}

		@Override
		public Object get(Class<?> entityClazz, Serializable id, String... str) {
			Object obj = getHibernateSession().get(entityClazz, id);
			lazyInitialize(entityClazz, obj, str);
			return obj;
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#load(java.io.Serializable)
		 */
		@Override
		public Object load(Class<?> entityClazz, Serializable id, String... str) {
			Object obj = getHibernateSession().load(entityClazz, id);
			lazyInitialize(entityClazz, obj, str);
			return obj;
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findById(java.io.Serializable
		 * )
		 */
		@Override
		public Object findById(Class<?> entityClazz, Serializable id, String... str) {
			Object obj = getHibernateSession().get(entityClazz, id);
			lazyInitialize(entityClazz, obj, str);
			return obj;
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#loadAll()
		 */
		@Override
		public List<?> loadAll(Class<?> entityClazz, String... str) {				
			return findAll(entityClazz, str);
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#findAll()
		 */
		@Override
		public List<?> findAll(Class<?> entityClazz, String... str) {
			DetachedCriteria dc = DetachedCriteria.forClass(entityClazz);
			List<?> list = findAllByCriteria(dc);
			lazyInitialize(entityClazz, list, str);
			return list;
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#update(java.lang.Object)
		 */
		@Override
		public void update(Object entity) {
			getHibernateSession().update(entity);
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#save(java.lang.Object)
		 */
		@Override
		public void save(Object entity) {
			getHibernateSession().save(entity);
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#saveOrUpdate(java.lang.
		 * Object)
		 */
		@Override
		public void saveOrUpdate(Object entity) {
			getHibernateSession().saveOrUpdate(entity);
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#saveOrUpdateAll(java.util
		 * .Collection)
		 */
		@Override
		public void saveOrUpdateAll(Collection<?> entities) {
			logger.info(".......saveOrUpdateAll...");
			for (Object entity : entities) {
				getHibernateSession().saveOrUpdate(entity);
			}
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#delete(java.lang.Object)
		 */
		@Override
		public void delete(Object entity) {
			getHibernateSession().delete(entity);
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#deleteByKey(java.io.
		 * Serializable)
		 */
		@Override
		public void deleteByKey(Serializable id, Class<?> entityClazz) {
			getHibernateSession().delete(load(entityClazz, id));
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#deleteAll(java.util.Collection
		 * )
		 */
		@Override
		public void deleteAll(Collection<?> entities) {
			for (Object entity : entities) {
				getHibernateSession().delete(entity);
			}
		}

		

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#bulkUpdate(java.lang.String
		 * )
		 */
		@Override
		public int bulkUpdate(String hql) {
			Query queryObject = getHibernateSession().createQuery(hql);
			return queryObject.executeUpdate();
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#bulkUpdate(java.lang.String
		 * , java.lang.Object[])
		 */
		@Override
		public int bulkUpdate(String hql, Object... values) {
			Query queryObject = getHibernateSession().createQuery(hql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.executeUpdate();
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#find(java.lang.String)
		 */
		@Override
		public List<?> findAllByHQL(String hql) {
			Query queryObject = getHibernateSession().createQuery(hql);
			return queryObject.list();
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#find(java.lang.String,
		 * java.lang.Object[])
		 */
		@Override
		public List<?> find(String hql, Object... values) {
			Query queryObject = getHibernateSession().createQuery(hql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#createCriteria()
		 */
		@Override
		public Criteria createCriteria(Class<?> entityClazz) {
			return this.createDetachedCriteria(entityClazz).getExecutableCriteria(
					getHibernateSession());
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#createDetachedCriteria()
		 */
		@Override
		public DetachedCriteria createDetachedCriteria(Class<?> entityClazz) {
			return DetachedCriteria.forClass(entityClazz);
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByCriteria(org.hibernate
		 * .criterion.DetachedCriteria)
		 */
		@Override
		public List<?> findByCriteria(DetachedCriteria detachedCriteria) {
			Criteria executableCriteria = detachedCriteria.getExecutableCriteria(getHibernateSession());		
			return executableCriteria.list();
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByCriteria(org.hibernate
		 * .criterion.DetachedCriteria, int, int)
		 */
		@Override
		public List<?> findByCriteria(final DetachedCriteria detachedCriteria,
				final int startIndex, final int pageSize) {
			
					Criteria criteria = detachedCriteria
							.getExecutableCriteria(getHibernateSession())
							.setFirstResult(startIndex).setMaxResults(pageSize);
					return criteria.list();
				
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#getRowCount(org.hibernate
		 * .criterion.DetachedCriteria)
		 */

		@Override
		public Integer getTotalCountByCriteria(
				final DetachedCriteria detachedCriteria) {
			Criteria criteria = detachedCriteria
									.getExecutableCriteria(getHibernateSession()).setProjection(
											Projections.rowCount());
			Number count = (Number)criteria.uniqueResult();
						
			return count.intValue();

		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findEqualByEntity(java.
		 * lang.Object, java.lang.String[])
		 */
		@SuppressWarnings("rawtypes")
		@Override
		public List<?> findEqualByEntity(Object entity, String[] propertyNames,
				Class<?> entityClazz, String... str) {
			Criteria criteria = this.createCriteria(entityClazz);
			Example exam = Example.create(entity);
			exam.excludeZeroes();
			String[] defPropertys = getSessionFactory().getClassMetadata(
					entityClazz).getPropertyNames();
			for (String defProperty : defPropertys) {
				int ii = 0;
				for (ii = 0; ii < propertyNames.length; ++ii) {
					if (defProperty.equals(propertyNames[ii])) {
						criteria.addOrder(Order.asc(defProperty));
						break;
					}
				}
				if (ii == propertyNames.length) {
					exam.excludeProperty(defProperty);
				}
			}
			criteria.add(exam);
			List list = criteria.list();
			lazyInitialize(entityClazz, list, str);
			return list;
		};

		

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#merge(java.lang.Object)
		 */
		@Override
		public Object merge(Object entity) {
			return getHibernateSession().merge(entity);
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByNamedParam(java.lang
		 * .String, java.lang.String[], java.lang.Object[])
		 */
		@Override
		public List<?> findByNamedParam(String queryString, String[] paramNames,
				Object[] values) {
			Query queryObject = getHibernateSession().createQuery(queryString);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}
			return queryObject.list();
		}
		
		@SuppressWarnings("rawtypes")
		protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
				throws HibernateException {

			if (value instanceof Collection) {
				queryObject.setParameterList(paramName, (Collection) value);
			}
			else if (value instanceof Object[]) {
				queryObject.setParameterList(paramName, (Object[]) value);
			}
			else {
				queryObject.setParameter(paramName, value);
			}
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByNamedQuery(java.lang
		 * .String)
		 */
		@Override
		public List<?> findByNamedQuery(String queryName) {
			Query queryObject = getHibernateSession().createQuery(queryName);
			
			return queryObject.list();
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByNamedQuery(java.lang
		 * .String, java.lang.Object[])
		 */
		@Override
		public List<?> findByNamedQuery(String queryName, Object[] values) {
			Query queryObject = getHibernateSession().getNamedQuery(queryName);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					queryObject.setParameter(i, values[i]);
				}
			}
			return queryObject.list();
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#findByNamedQueryAndNamedParam
		 * (java.lang.String, java.lang.String[], java.lang.Object[])
		 */
		@Override
		public List<?> findByNamedQueryAndNamedParam(String queryName,
				String[] paramNames, Object[] values) {
			Query queryObject = getHibernateSession().getNamedQuery(queryName);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
				}
			}
			return queryObject.list();
		}

		/*
		 * @see org.cpframework.dao.hibernate.CP_HibernateDAO#flush()
		 */
		@Override
		public void flush() {
			getHibernateSession().flush();
		}

		/*
		 * @see
		 * org.cpframework.dao.hibernate.CP_HibernateDAO#initialize(java.lang.Object
		 * )
		 */
		@Override
		public void initialize(Object proxy) {
			Hibernate.initialize(proxy);
		}

		
		

		@Override
		public List<?> findAllByCriteria(DetachedCriteria detachedCriteria) {
			// TODO Auto-generated method stub
			Criteria criteria = detachedCriteria
					.getExecutableCriteria(getHibernateSession());
			return criteria.list();
		}

		@Override
		public List<?> getListBySql(String sql) {
			// TODO Auto-generated method stub
			logger.info("getListBySql=" + sql);
			final String tempsql = sql;	
			List<?> list = getHibernateSession().createSQLQuery(tempsql).list();
			return list;
		}

		public int updateBySQL(String sql) {
			logger.info("updateBySQL="+sql);
			final String tempsql = sql;		
			SQLQuery sqlQuery = getHibernateSession().createSQLQuery(tempsql);	
			return sqlQuery.executeUpdate();
						
		}

}


