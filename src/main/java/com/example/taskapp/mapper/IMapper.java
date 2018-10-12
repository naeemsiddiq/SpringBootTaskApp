package com.example.taskapp.mapper;

import com.example.taskapp.dto.ListResponse;

/**
 * 
 * Copyright (c) 2016, VentureDive.com. All rights reserved.
 * 
 * @author Mazhar Hassan
 * @since Apr 6, 2016
 * @package com.vdif.core.mapper
 * @project if-services
 *
 */
public interface IMapper {

	void mapResponse(ListResponse src, ListResponse target);
}
