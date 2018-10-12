package com.example.taskapp.dto;

import java.util.List;

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
public class ListResponse extends ListRequest {
	private static final long serialVersionUID = 1L;
	
	private int resultCount;
	private int totalRecords;
	private int totalPages;
	private List<? extends Listable> objects;
	public int getResultCount() {
		return resultCount;
	}
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<? extends Listable> getObjects() {
		return objects;
	}
	public void setObjects(List<? extends Listable> objects) {
		this.objects = objects;
	}
}
