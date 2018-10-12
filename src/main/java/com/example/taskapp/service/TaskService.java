package com.example.taskapp.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.taskapp.bean.TaskBean;
import com.example.taskapp.bean.UserBean;
import com.example.taskapp.dto.Task;
import com.example.taskapp.exception.ApiException;
import com.example.taskapp.mapper.ITaskMapper;
import com.example.taskapp.repository.TaskRepository;
import com.example.taskapp.repository.UserRepository;
import com.example.taskapp.utils.ValueUtils;

/**
 * 
 * Copyright (c) 2018, VentureDive.com. All rights reserved.
 * 
 * @author Naeem Siddiq
 * @since Oct 1,2018
 *
 */

@Service
public class TaskService implements ITaskService {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ITaskMapper mapper;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IUserService userService;

	@Override
	public Task create(Task task) {
		validateTask(task);

		TaskBean bean = mapper.map(task);
		UserBean user = userRepository.findByUserRole("admin");
		bean.setCreatedBy(user.getId());
		bean.setCreatedAt(new Date());
		return mapper.map(taskRepository.save(bean));
	}

	@Override
	public Task find(Long taskId) {
		return mapper.map(findWithCheck(taskId));
	}

	@Override
	public List<Task> findAll() {
		List<TaskBean> beans = taskRepository.findAll();
		if (beans.isEmpty())
			throw ApiException.notFound("No Tasks found");
		return mapper.map(beans);
	}

	@Override
	public Task update(Long taskId, Task dto) {
		TaskBean existingBean = findWithCheck(taskId);
		UserBean user = userRepository.findByUserRole("updator");
		mapper.map(existingBean, dto);
		existingBean.setUpdatedBy(user.getId());
		existingBean.setUpdatedAt(new Date());

		return mapper.map(taskRepository.save(existingBean));
	}

	@Override
	public void delete(Long taskId) {
		taskRepository.delete(findWithCheck(taskId));
	}

	@Override
	public Task assign(Long taskId, Long userId) {
		TaskBean bean = findWithCheck(taskId);
		UserBean userBean = userService.findWithCheck(userId);
		validateAssigment(userBean, bean);
		bean.setAssignedToId(userId);
		taskRepository.save(bean);
		return mapper.map(bean);
	}

	private void validateAssigment(UserBean user, TaskBean task) {
		if (!user.getUserRole().equals("user")) {
			throw new ApiException(ApiException.ERROR_RECORD_NOT_FOUND_EXCEPTION,
					"This user cannot be assigned a task");
		} else if (!(task.getAssignedToId() == null || !task.getAssignedToId().equals(user.getId()))) {
			throw new ApiException(ApiException.ERROR_DUPLICATE_ENTRY,
					"Task is already assigned to this user with id " + user.getId());
		}
	}

	private TaskBean findWithCheck(Long taskId) {
		TaskBean bean = taskRepository.findById(taskId)
				.orElseThrow(() -> ApiException.notFound("Task not found with given id " + taskId));
		return bean;
	}

	@Override
	public List<Task> assignedTasks(Long userId) {
		List<TaskBean> beans = taskRepository.findAllByAssignedToId(userId);
		if (beans.isEmpty())
			throw ApiException.notFound("Tasks does not found with given user id " + userId);
		return mapper.map(beans);
	}

	@Override
	public Date taskDueDate(Long taskId, Long userId) {
		TaskBean bean = findWithCheck(taskId);
		Date date = null;
		if (!bean.getAssignedToId().equals(userId)) {
			throw ApiException.notFound("Task is not assigned to this user");
		}
		date = bean.getDueDate();

		return date;
	}

	private void validateTask(Task task) {
		if (task == null)
			throw ApiException.nullError("Task object is required");
		ValueUtils.checkRequiredString(task.getTitle(), "Title is required");
		ValueUtils.checkRequiredString(task.getDescription(), "Task Description is required");
		ValueUtils.checkRequired(task.getDueDate(), "Due Date is required");
		if (task.getTitle().equals("string") || task.getDescription().equals("string"))
			throw new ApiException(ApiException.ERROR_INVALID_INPUT, "Please enter valid Tilte and Description");

	}

}
