package com.tour.travelset.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.locations
 */

public class TravelItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String TYPE_PIC = "pic";
	public static final String TYPE_NOTE = "note";

	/**
	 * id
	 */
	private Long id;
	private Long headId;
	private String itemType;
	private String imgUrl;
	private String content;
	private Long linkSiteid;
	private String linkSite;
	private Date linkDate;
	private Date cdate;
	private Date udate;
	private String linkDateStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHeadId() {
		return headId;
	}

	public void setHeadId(Long headId) {
		this.headId = headId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getLinkSiteid() {
		return linkSiteid;
	}

	public void setLinkSiteid(Long linkSiteid) {
		this.linkSiteid = linkSiteid;
	}

	public String getLinkSite() {
		return linkSite;
	}

	public void setLinkSite(String linkSite) {
		this.linkSite = linkSite;
	}

	public Date getLinkDate() {
		return linkDate;
	}

	public void setLinkDate(Date linkDate) {
		this.linkDate = linkDate;
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

	public String getLinkDateStr() {
		return linkDateStr;
	}

	public void setLinkDateStr(String linkDateStr) {
		this.linkDateStr = linkDateStr;
	}

}
