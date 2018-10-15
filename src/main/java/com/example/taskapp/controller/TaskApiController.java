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
@EnableHystrix
public class TaskApiController {

	@Autowired
	ITaskService iTaskService;

	// Create a new Task
	@PostMapping("/")
	public Task create(@Valid @RequestBody Task task) {
		return iTaskService.create(task);
	}

	// find a Task by Id
	@GetMapping("/{id}")
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
	public List<Task> findAll() {
		return iTaskService.findAll();
	}

	// Update a Task
	@PutMapping("/{id}")
	public Task update(@PathVariable(value = "id") Long taskId, @Valid @RequestBody Task task) {
		return iTaskService.update(taskId, task);
	}

	// Delete a task
	@DeleteMapping("/{id}")
	@HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public void delete(@PathVariable(value = "id") Long taskId) throws Exception {
		Thread.sleep(2000);
		iTaskService.delete(taskId);
	}

	private void fallback_hello(Long taskId) {
		throw new ApiException("Request fails. It takes long time to response");
	}

	// Assign task to some user
	@PatchMapping("/{taskId}/assign/{userId}")
	public Task assign(@PathVariable(value = "taskId") Long taskId, @PathVariable(value = "userId") Long userId) {
		return iTaskService.assign(taskId, userId);
	}

}
