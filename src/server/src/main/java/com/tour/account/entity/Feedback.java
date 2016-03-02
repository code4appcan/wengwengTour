package com.tour.account.entity;

import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Feedback
 */

public class Feedback{

	public static final int	NOT_VIEWED = 0;// 代查看
	public static final int	VIEW = 1;// 已查看
	
	private Long id;
	private String flag;
	private String contents;
	private Date cdate;
	private Long userid;
	private Integer status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
