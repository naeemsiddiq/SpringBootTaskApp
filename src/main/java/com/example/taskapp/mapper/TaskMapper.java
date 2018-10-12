package com.example.taskapp.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
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

@Component
public class TaskMapper extends AbstractMapper implements ITaskMapper {

	@Override
	public Task map(TaskBean bean) {
		Task dto = null;
		if (bean != null) {
			dto = new Task();
			dto.setId(bean.getId());
			dto.setTitle(bean.getTitle());
			dto.setDescription(bean.getDescription());
			dto.setCategoryId(bean.getCategoryId());
			dto.setCreatedBy(bean.getCreatedBy());
			dto.setCreatedAt(bean.getCreatedAt());
			dto.setUpdatedBy(bean.getUpdatedBy());
			dto.setAssignedToId(bean.getAssignedToId());
			dto.setUpdatedAt(bean.getUpdatedAt());
			dto.setDueDate(bean.getDueDate());
			dto.setStatus(bean.getStatus());
		}
		return dto;
	}

	@Override
	public TaskBean map(Task dto) {
		TaskBean bean = null;
		if (dto != null) {
			bean = new TaskBean();
			bean.setId(dto.getId());
			bean.setTitle(dto.getTitle());
			bean.setDescription(dto.getDescription());
			bean.setCategoryId(dto.getCategoryId());
			bean.setDueDate(dto.getDueDate());
			bean.setStatus(dto.getStatus());
		}
		return bean;
	}

	@Override
	public void map(TaskBean existingBean, Task dto) {

		existingBean.setTitle(dto.getTitle());
		existingBean.setDescription(dto.getDescription());
		existingBean.setCategoryId(dto.getCategoryId());
		existingBean.setDueDate(dto.getDueDate());
		existingBean.setStatus(dto.getStatus());
	}

	@Override
	public List<Task> map(List<TaskBean> beans) {
		List<Task> tasks = new ArrayList<Task>();

		for (TaskBean taskBean : beans) {
			tasks.add(map(taskBean));
		}
		return tasks;
	}
}
