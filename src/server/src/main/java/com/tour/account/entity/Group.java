package com.tour.account.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.NotBlank;

@SuppressWarnings("serial")
public class Group implements Serializable{

	public Long id;
	
	public String huanGroupID;
	@NotBlank(message = "群名称不能为空")
	public String groupName;
	public String ownerName;
	public Long ownerID;
	public int type;
	//该群是否有效
	public String status;
	@NotBlank(message = "群描述不能为空")
	public String describ;
	public Date createTime;
	public Date expireTime;
	
	public String isAccept;
	public String isNotify;
	public List<Map<String,Object>> members;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(Long ownerID) {
		this.ownerID = ownerID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescrib() {
		return describ;
	}
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getHuanGroupID() {
		return huanGroupID;
	}
	public void setHuanGroupID(String huanGroupID) {
		this.huanGroupID = huanGroupID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public List<Map<String, Object>> getMembers() {
		return members;
	}
	public void setMembers(List<Map<String, Object>> members) {
		this.members = members;
	}

	
}
