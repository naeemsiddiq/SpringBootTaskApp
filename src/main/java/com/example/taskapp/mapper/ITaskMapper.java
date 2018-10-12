package com.example.taskapp.mapper;

import com.example.taskapp.bean.TaskBean;
import com.example.taskapp.dto.Task;

/**
 * 
 * Copyright (c) 2018, VentureDive.com. All rights reserved.
 * 
 * @author Naeem Siddiq
 * @since Oct 1,2018
 *
 */
public interface ITaskMapper extends Mappable<TaskBean,Task> {

	Task map(TaskBean bean);

	void map(TaskBean existingBean, Task dto);

}
