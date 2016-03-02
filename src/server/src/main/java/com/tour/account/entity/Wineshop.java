package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Wineshop
 */

public class Wineshop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	private String name;
	private String numbers;
	private String address;
	private Long hot;
	private Long star;
	private String longitude;
	private String latitude;
	private Long type;
	private String photo;
	private String area;
	private String phone;
	private String comments;
	private Date cTime;
	private Date uTime;
	private Long cBy;
	private Long pointid;
	private String roomEquipment;
	private String roomFacilities;
	private String foodBeverages;
	private String drivingSupport;
	private String payByCard;
	private String introduction;
	private String feature;
	private Long loveNum;
	private String park;
	private String roomPrice;
	private String howToGo;
	private String timeInOut;
	private Long ordered;
	private Long recommend;
	
	public Long getOrdered() {
		return ordered;
	}
	public void setOrdered(Long ordered) {
		this.ordered = ordered;
	}
	public String getTimeInOut() {
		return timeInOut;
	}
	public void setTimeInOut(String timeInOut) {
		this.timeInOut = timeInOut;
	}
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
	public String getNumbers() {
		return numbers;
	}
	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getHot() {
		return hot;
	}
	public void setHot(Long hot) {
		this.hot = hot;
	}
	public Long getStar() {
		return star;
	}
	public void setStar(Long star) {
		this.star = star;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getRoomEquipment() {
		return roomEquipment;
	}
	public void setRoomEquipment(String roomEquipment) {
		this.roomEquipment = roomEquipment;
	}
	public String getRoomFacilities() {
		return roomFacilities;
	}
	public void setRoomFacilities(String roomFacilities) {
		this.roomFacilities = roomFacilities;
	}
	public String getFoodBeverages() {
		return foodBeverages;
	}
	public void setFoodBeverages(String foodBeverages) {
		this.foodBeverages = foodBeverages;
	}
	public String getDrivingSupport() {
		return drivingSupport;
	}
	public void setDrivingSupport(String drivingSupport) {
		this.drivingSupport = drivingSupport;
	}
	public Long getPointid() {
		return pointid;
	}
	public void setPointid(Long pointid) {
		this.pointid = pointid;
	}
	public String getPayByCard() {
		return payByCard;
	}
	public void setPayByCard(String payByCard) {
		this.payByCard = payByCard;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public Long getLoveNum() {
		return loveNum;
	}
	public void setLoveNum(Long loveNum) {
		this.loveNum = loveNum;
	}
	public String getPark() {
		return park;
	}
	public void setPark(String park) {
		this.park = park;
	}
	public String getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getHowToGo() {
		return howToGo;
	}
	public void setHowToGo(String howToGo) {
		this.howToGo = howToGo;
	}
	public Long getType() {
		return type;
	}
	public Long getRecommend() {
		return recommend;
	}
	public void setRecommend(Long recommend) {
		this.recommend = recommend;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	
}
