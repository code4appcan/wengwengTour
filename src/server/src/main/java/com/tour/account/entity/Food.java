package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Food
 */

public class Food implements Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	private String foodName;
	private String diningName;
	private String phone;
	private String address;
	private Double avgPrice;
	private String area;
	private Float longitude;
	private Float latitude;
	private Long type;
	private String photos;
	private String tags;
	private Date cTime;
	private Date uTime;
	private Long cBy;
	private Long pointid;
	private Long loveNum;
	private String introduction;
	private Long ordered;
	private Long hot;
	private Long recommend;
	private Long stroll;
	private Long plTotal;
	private Long scTotal;
	private String isFood;
	
	public Long getRecommend() {
		return recommend;
	}
	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}
	public Long getHot() {
		return hot;
	}
	public void setHot(Long hot) {
		this.hot = hot;
	}
	public Long getOrdered() {
		return ordered;
	}
	public void setOrdered(Long ordered) {
		this.ordered = ordered;
	}
	public Long getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(Long loveNum) {
		this.loveNum = loveNum;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getDiningName() {
		return diningName;
	}
	public void setDiningName(String diningName) {
		this.diningName = diningName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public Double getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(Double avgPrice) {
		this.avgPrice = avgPrice;
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
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getPhotos() {
		return photos;
	}
	public void setPhotos(String photos) {
		this.photos = photos;
	}
	public Date getcTime() {
		return cTime;
	}
	public void setcTime(Date cTime) {
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getPointid() {
		return pointid;
	}
	public void setPointid(Long pointid) {
		this.pointid = pointid;
	}
	public Long getStroll() {
		return stroll;
	}
	public void setStroll(Long stroll) {
		this.stroll = stroll;
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
	public String getIsFood() {
		return isFood;
	}
	public void setIsFood(String isFood) {
		this.isFood = isFood;
	}
	
}
