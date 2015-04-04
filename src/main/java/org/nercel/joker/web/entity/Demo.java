/*
 * COPYRIGHT Beijing NetQin-Tech Co.,Ltd.                                   *
 ****************************************************************************
 * 源文件名:  web.function.model.oracle.Demo.java 													       
 * 功能: cpframework框架													   
 * 版本:	@version 1.0	                                                                   
 * 编制日期: 2014年8月19日 上午11:13:03 						    						                                        
 * 修改历史: (主要历史变动原因及说明)		
 * YYYY-MM-DD |    Author      |	 Change Description		      
 * 2014年8月19日    |    Administrator     |     Created 
 */
package org.nercel.joker.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Lazy;

/** 
 *Description: <类功能描述>. <br>
 *<p>
	<使用说明>
 </p>
 *Makedate:2014年8月19日 上午11:13:03 
 * @author Administrator  
 * @version V1.0                             
 */
@Entity
@Table(name ="t_webmvc")
@Lazy(value=true)
@XmlRootElement
public class Demo implements java.io.Serializable{
	

	/** 
	* @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	*/ 
	private static final long serialVersionUID = -827759092352842181L;


	@Id
	@Column(name="SP_ID")
//	@SequenceGenerator(name = "sequenceGenerator", sequenceName = "BOSS_NQCP.SEQ_SPRING_DEMO")
//	@GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
	private Long id;
	

	@Column(name="SP_NAME")
	private String name;
	
	
	@Column(name="SP_AGE")
	private Integer age;
	
	
	@Column(name="SP_CREATEDATE")
	private Date createDate;
	
	@Column(name="SP_MODIFYDATE")
	private Date modifyDate;

	@XmlElement
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@XmlElement
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
	
	
}


