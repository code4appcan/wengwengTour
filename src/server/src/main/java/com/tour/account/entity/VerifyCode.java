package com.tour.account.entity;

import java.io.Serializable;

/**
 * 
 * @author bear
 * @version 2015-07-15
 * @see com.wowtour.account.entity.VerifyCode
 */

public class VerifyCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Long id;
	
	private String phone;
	
	private String code;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
