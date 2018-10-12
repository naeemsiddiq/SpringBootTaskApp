package com.example.taskapp.mapper;

import java.util.List;

import com.example.taskapp.bean.Persistable;
import com.example.taskapp.dto.Transferable;

/**
 * 
 * Copyright (c) 2015, VentureDive.com. All rights reserved.
 * 
 * @author Mazhar Hassan
 * @since Dec 10, 2015
 * @package com.vdif.core.services
 * @project if-services
 *
 */
public interface Mappable<B extends Persistable, T extends Transferable> extends IMapper {

	/**
	 * Transform bean into transfer object
	 * @param bean
	 * @return
	 */
	T map(B bean);
	
	/**
	 * Transform DTO into entity bean
	 * @param dto
	 * @return
	 */
	B map(T dto);
	
	/**
	 * Transform entity bean list into transfer object list
	 * @param beans
	 * @return
	 */
	List<T> map(List<B> beans);
	
}
