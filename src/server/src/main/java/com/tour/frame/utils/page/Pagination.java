/**
 * @(#)Pagination.java
 *
 * Copyright 2008 jointown, Inc. All rights reserved.
 */

package com.tour.frame.utils.page;

import java.io.Serializable;

public class Pagination implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int DEFAULT_PAGE_SIZE = 15;
	public static final int DEFAULT_CURRENT_PAGE = 1;
	public static final int DEFAULT_SKIP_SIZE = 10;
	public static final int DEFAULT_CURRENT_SKIP = 1;
	public static final int MAX_PAGE_SIZE = 200;

	private int pageSize = 10; // 每页显示记录数
	private int total; // 总条目数
	// private int totalResult; //总记录数
	private int totalPage; // 总页数
	private int pageNo = DEFAULT_CURRENT_SKIP; // 当前页

	// private int currentResult; //当前记录起始索引

	public Pagination() {

	}

	public Pagination(int pageSize) {
		this(pageSize, DEFAULT_CURRENT_SKIP);
	}

	public Pagination(int pageSize, int pageNo) {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
		totalPage = total % pageSize == 0 ? total / pageSize : (total / pageSize + 1);

	}

	public int getPageNo() {
		if (pageNo <= 1) {
			return 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
