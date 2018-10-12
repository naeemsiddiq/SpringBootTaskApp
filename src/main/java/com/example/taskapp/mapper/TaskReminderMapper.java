package com.example.taskapp.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.taskapp.bean.TaskReminderBean;
import com.example.taskapp.dto.TaskReminder;

@Component
public class TaskReminderMapper extends AbstractMapper implements ITaskReminderMapper {

	@Override
	public TaskReminder map(TaskReminderBean bean) {
		TaskReminder dto = null;
		if (bean != null) {
			dto = new TaskReminder();
			dto.setId(bean.getId());
			dto.setTaskId(bean.getTaskId());
			dto.setUserId(bean.getUserId());
			dto.setReminderDay(bean.getReminderDay());
			dto.setDays(bean.getDays());
			dto.setCreatedBy(bean.getCreatedBy());
			dto.setCreatedAt(bean.getCreatedAt());
			dto.setUpdatedBy(bean.getUpdatedBy());
			dto.setUpdatedAt(bean.getUpdatedAt());
			dto.setStatus(bean.getStatus());
		}
		return dto;
	}

	@Override
	public TaskReminderBean map(TaskReminder dto) {
		TaskReminderBean bean = null;
		if (dto != null) {
			bean = new TaskReminderBean();
			bean.setId(dto.getId());
			bean.setTaskId(dto.getTaskId());
			bean.setUserId(dto.getUserId());
			bean.setReminderDay(dto.getReminderDay());
			bean.setStatus(dto.getStatus());
		}
		return bean;
	}

	@Override
	public List<TaskReminder> map(List<TaskReminderBean> beans) {
		List<TaskReminder> dtos = new ArrayList<TaskReminder>();
		for (TaskReminderBean bean : beans)
			dtos.add(map(bean));
		return dtos;
	}

}
