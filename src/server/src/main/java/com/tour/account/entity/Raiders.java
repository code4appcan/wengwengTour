package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

public class Raiders implements Serializable{

	private Long id;
	
	private String title;
	
	private String cover;
	
	private String content;
	
	private Date cdate;

	private String tag;
	
	private Long plTotal;
	
	private Long scTotal;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
