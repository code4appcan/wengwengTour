package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class GroupMember implements Serializable,Cloneable {

	public Long id;
	public Long groupID;
	public Long userID;
	public String groupName;
	/**
	 * 组员的状态信息
	 */
	public String isAccept;
	/**
	 * 是否同意接受新消息
	 */
	public String isNotify;
	public Date joinTime;
	public Date leaveTime;
	public String status;
	public Long updator;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGroupID() {
		return groupID;
	}
	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getIsAccept() {
		return isAccept;
	}
	public void setIsAccept(String isAccept) {
		this.isAccept = isAccept;
	}
	public String getIsNotify() {
		return isNotify;
	}
	public void setIsNotify(String isNotify) {
		this.isNotify = isNotify;
	}
	public Date getJoinTime() {
		return joinTime;
	}
	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getUpdator() {
		return updator;
	}
	public void setUpdator(Long updator) {
		this.updator = updator;
	}
	
	
}
