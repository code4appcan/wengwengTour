package com.tour.account.entity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class Friend implements Serializable {
	private Long id;
	private Long userID;
	private Long friendID;
	/**
	 * 添加状态 
	 */
	private String status;
	private String nickName;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer isInBlackList;
	
	private Long createBy;
	private Long updateBy;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getFriendID() {
		return friendID;
	}
	public void setFriendID(Long friendID) {
		this.friendID = friendID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getIsInBlackList() {
		return isInBlackList;
	}
	public void setIsInBlackList(Integer isInBlackList) {
		this.isInBlackList = isInBlackList;
	}
	

}
