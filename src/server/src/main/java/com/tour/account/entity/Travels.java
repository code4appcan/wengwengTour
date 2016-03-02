package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Travels
 */

public class Travels implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	private Long userid;
	private String landscapeName;
	private Long landscapeId;
	private String content;
	private String title;
	private Long loveNum;
	private Long shareNum;
	private Float longitude;
	private Float latitude;
	private String type;
	private String photo;
	private String photo1;
	private String userName;
	private String feature;
	private Long commentNum;
	private String userHeadImg;
	private String tips;
	private String howToGo;
	private Date cTime;
	private Date uTime;
	private Long cBy;
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(Long commentNum) {
		this.commentNum = commentNum;
	}
	public String getUserHeadImg() {
		return userHeadImg;
	}
	public void setUserHeadImg(String userHeadImg) {
		this.userHeadImg = userHeadImg;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(Long loveNum) {
		this.loveNum = loveNum;
	}
	public Long getShareNum() {
		return shareNum;
	}
	public void setShareNum(Long shareNum) {
		this.shareNum = shareNum;
	}
	public Float getLongitude() {
		return longitude;
	}
	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}
	public Float getLatitude() {
		return latitude;
	}
	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getHowToGo() {
		return howToGo;
	}
	public void setHowToGo(String howToGo) {
		this.howToGo = howToGo;
	}
	public Date getCTime() {
		return cTime;
	}
	public void setCTime(Date cTime) {
		this.cTime = cTime;
	}
	public Date getuTime() {
		return uTime;
	}
	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}
	public Long getcBy() {
		return cBy;
	}
	public void setcBy(Long cBy) {
		this.cBy = cBy;
	}
	public String getLandscapeName() {
		return landscapeName;
	}
	public void setLandscapeName(String landscapeName) {
		this.landscapeName = landscapeName;
	}
	public Long getLandscapeId() {
		return landscapeId;
	}
	public void setLandscapeId(Long landscapeId) {
		this.landscapeId = landscapeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
