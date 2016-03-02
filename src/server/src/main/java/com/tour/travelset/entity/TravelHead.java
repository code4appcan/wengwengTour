package com.tour.travelset.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.locations
 */

public class TravelHead implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	private String name;
	private String coverurl;
	private Long userid;
	private String username;
	private Long status;
	private String tag;
	private Date cdate;
	private Date udate;
	private Long plTotal;
	private Long scTotal;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCoverurl() {
		return coverurl;
	}
	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	public Long getPlTotal() {
		return plTotal;
	}
	public void setPlTotal(Long plTotal) {
		this.plTotal = plTotal;
	}
	public Long getScTotal() {
		return scTotal;
	}
	public void setScTotal(Long scTotal) {
		this.scTotal = scTotal;
	}
	
}
