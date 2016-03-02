package com.tour.photos.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Wineshop
 */

public class Photos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	private String url;
	private Long type;
	private Date cTime;
	private String cBy;
	private Long typeid;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}
	public Long getTypeid() {
		return typeid;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	public String getcBy() {
		return cBy;
	}
	public void setcBy(String cBy) {
		this.cBy = cBy;
	}
	
}
