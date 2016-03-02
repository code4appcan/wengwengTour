package com.tour.resources.entity;

/**
 * 系统用户
 * 
 * @author allen
 *
 */
public class SysUser {
	private Long id;
	private String username;
	private String password;
	private String name;
	private Long status;
	private Long departmentId;
	private String remark;
	private Long roleId;
	private String roleArr;
	private String roleNameArr;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoleArr() {
		return roleArr;
	}

	public void setRoleArr(String roleArr) {
		this.roleArr = roleArr;
	}

	public String getRoleNameArr() {
		return roleNameArr;
	}

	public void setRoleNameArr(String roleNameArr) {
		this.roleNameArr = roleNameArr;
	}

}
