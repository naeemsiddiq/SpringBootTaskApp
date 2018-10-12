package com.example.taskapp.mapper;

import com.example.taskapp.dto.ListResponse;

public class AbstractMapper implements IMapper {

	public void mapResponse(ListResponse src, ListResponse target) {
		target.setKeyword(src.getKeyword());
		target.setLimitCount(src.getLimitCount());
		target.setPageno(src.getPageno());
		target.setTotalPages(src.getTotalPages());
		target.setTotalRecords(src.getTotalRecords());
	}
	
}
