package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;

/**
 * 
 * @author <Auto generate>
 * @version 2015-07-15
 * @see com.tour.account.entity.Account
 */

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	/**
	 * username
	 */
	private String userName;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * password
	 */
	@JsonIgnore
	private String password;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * email
	 */
	@Email
	private String email;
	/**
	 * 用户类型 0:普通会员 1:管理员
	 */
	private Integer userType;
	/**
	 * login_time
	 */
	private Date loginTime;
	/**
	 * 是否可用(0激活；1:为激活；2：未开启)
	 */
	private Integer disabled;

	private String keyWords;// 查询字段
	
	private Date birthday;
	
	private int sex;
	
	private String signature;
	
	private String qq;
	
	private String city;
	
	private String photoURL;
	
	private String token;
	
	private String verifyCode;

	public void setId(Long id) {

		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setNickName(String nickName) {

		if (StringUtils.isNotBlank(nickName)) {
			nickName = nickName.trim();
		}
		this.nickName = nickName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setPassword(String password) {

		if (StringUtils.isNotBlank(password)) {
			password = password.trim();
		}
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setMobile(String mobile) {

		if (StringUtils.isNotBlank(mobile)) {
			mobile = mobile.trim();
		}
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {

		if (StringUtils.isNotBlank(email)) {
			email = email.trim();
		}
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setUserType(Integer userType) {

		this.userType = userType;
	}

	public Integer getUserType() {
		return this.userType;
	}

	/*
	 * public String getlogin_timeString() { return
	 * DateUtils.convertDate2String(FORMAT_LOGIN_TIME, getlogin_time()); }
	 * public void setlogin_timeString(String value) throws ParseException{
	 * setlogin_time(DateUtils.convertString2Date(FORMAT_LOGIN_TIME,value)); }
	 */

	public void setLoginTime(Date loginTime) {

		this.loginTime = loginTime;
	}

	public Date getLoginTime() {
		return this.loginTime;
	}

	public void setDisabled(Integer disabled) {

		this.disabled = disabled;
	}

	public Integer getDisabled() {
		return this.disabled;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String toString() {
		return new StringBuffer().append("id=").append(getId()).append(",")
				.append("username=").append(getUserName()).append(",")
				.append("nickName=").append(getNickName()).append(",")
				.append("password=").append(getPassword()).append(",")
				.append("mobile=").append(getMobile()).append(",")
				.append("email=").append(getEmail()).append(",")
				.append("userType=").append(getUserType()).append(",")
				.append("loginTime=").append(getLoginTime()).append(",")
				.append("birthday=").append(getBirthday()).append(",")
				.append("sex=").append(getSex()).append(",")
				.append("signature=").append(getSignature()).append(",")
				.append("city=").append(getCity()).append(",")
				.append("photoURL=").append(getPhotoURL()).append(",")
				.toString();
	}
}
