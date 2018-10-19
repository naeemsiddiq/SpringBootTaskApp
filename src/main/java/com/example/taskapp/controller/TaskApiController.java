package com.example.taskapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskapp.IConstants;
import com.example.taskapp.dto.Task;
import com.example.taskapp.exception.ApiException;
import com.example.taskapp.service.ITaskService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import hystrix.HystrixKey;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * Copyright (c) 2018, VentureDive.com. All rights reserved.
 * 
 * @author Naeem Siddiq
 * @since Oct 1,2018
 *
 */

@RestController
@RequestMapping(IConstants.API_URL + "/task")
public class TaskApiController {

	@Autowired
	ITaskService iTaskService;

	// Create a new Task
	@PostMapping("/")
	@ApiOperation(value = "Create a task")
	public Task create(@Valid @RequestBody Task task) {
		return iTaskService.create(task);
	}

	// find a Task by Id
	@GetMapping("/{id}")
	@ApiOperation("Find a task")
	@HystrixCommand(groupKey = HystrixKey.Service.TASK_SERVICE, commandKey = HystrixKey.Command.FIND_A_TASK, threadPoolKey = HystrixKey.ThreadPool.TASK_SERVICE_THREADPOOL)
	public Task find(@PathVariable(value = "id") Long taskId) {
		Task task = iTaskService.find(taskId);
		task.addLink(linkTo(TaskApiController.class).slash(task.getId()).toUri().toString(), "self");
		task.addLink(linkTo(TaskApiController.class).slash(task.getId())
				.slash(removeBaseUrl(linkTo(UserApiController.class))).slash(task.getAssignedToId())
				.slash(linkTo(methodOn(TaskReminderApiController.class).find(task.getAssignedToId(), task.getId())))
				.toUri().toString(), "reminders");

		task.addLink(linkTo(TaskApiController.class).slash(task.getId()).toUri().toString(), "delete");
		task.addLink(linkTo(TaskApiController.class).slash(task.getId()).toUri().toString(), "update");
		return task;
	}

	private String removeBaseUrl(ControllerLinkBuilder linkTo) {
		String baseUrl = IConstants.API_URL;
		String linkToUrl = linkTo.toString();
		int first_Index = linkToUrl.indexOf(baseUrl) + baseUrl.length();
		String controllerUrl = linkToUrl.substring(first_Index);

		return controllerUrl;
	}

	// find list of all Tasks
	@GetMapping("/")
	@ApiOperation(value = "Find all tasks")
	@HystrixCommand(groupKey = HystrixKey.Service.TASK_SERVICE, commandKey = HystrixKey.Command.FIND_ALL_TASK, threadPoolKey = HystrixKey.ThreadPool.TASK_SERVICE_THREADPOOL)
	public List<Task> findAll() {
		return iTaskService.findAll();
	}

	// Update a Task
	@PutMapping("/{id}")
	@ApiOperation(value = "Update a task")
	public Task update(@PathVariable(value = "id") Long taskId, @Valid @RequestBody Task task) {
		return iTaskService.update(taskId, task);
	}

	// Delete a task
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a task with id")
	@HystrixCommand(groupKey = HystrixKey.Service.TASK_SERVICE, commandKey = HystrixKey.Command.DELETE_A_TASK, threadPoolKey = HystrixKey.ThreadPool.TASK_SERVICE_THREADPOOL)
	public void delete(@PathVariable(value = "id") Long taskId) throws Exception {
		// Thread.sleep(2000);
		iTaskService.delete(taskId);
	}

	private void fallback_delete(Long taskId) {
		throw new ApiException("Request fails. It takes long time to response");
	}

	private Task fallback_find(Long taskId) {
		return null;
	}

	private List<Task> fallback_findAll() {
		return null;
	}

	// Assign task to some user
	@PatchMapping("/{taskId}/assign/{userId}")
	@ApiOperation(value = "Assign task")
	public Task assign(@PathVariable(value = "taskId") Long taskId, @PathVariable(value = "userId") Long userId) {
		return iTaskService.assign(taskId, userId);
	}

}
