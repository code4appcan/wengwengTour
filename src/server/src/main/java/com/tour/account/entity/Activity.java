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
 * @see com.tour.account.entity.Activity
 */

public class Activity implements Serializable {

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
	private double price;
	private double longitude;
	private double latitude;
	private String type;
	private String photo;
	private String comments;
	private Date cTime;
	private Date uTime;
	private Long cBy;
	
	
	
}
