package com.example.taskapp.dto;

/**
 * 
 * Copyright (c) 2016, VentureDive.com. All rights reserved.
 * 
 * @author Mazhar Hassan
 * @since Jan 18, 2016
 * @package com.vdif.core.dto
 * @project if-services
 *
 */
public class ListRequest implements Transferable {

	private static final long serialVersionUID = 1L;
	private String keyword;
	private int pageno;
	private int limitCount;
	/*
	 * if populated pageno will be ignored 
	 */
	private int firstIndex = -2;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getLimitCount() {
		return limitCount;
	}

	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}
}
