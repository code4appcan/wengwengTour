package com.tour.resources.entity;

import java.io.Serializable;

/**
 * 
 * @author <Auto generate>
 * @version 2015-05-21 23:16:41
 * @see com.yang.spinach.entity.Resources
 */

public class SysRight implements Serializable {

	// columns START
	/**
	 * id
	 */
	private Long id;
	/**
	 * 父节点名称
	 */
	private Integer pid;
	/**
	 * 名称
	 */
	private String name;
	
	private Boolean checked;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
