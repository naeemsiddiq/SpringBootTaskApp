package com.example.taskapp.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskapp.IConstants;
import com.example.taskapp.dto.TaskReminder;
import com.example.taskapp.service.ITaskReminderService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(IConstants.API_URL)
public class TaskReminderApiController {

	@Autowired
	ITaskReminderService iTaskReminderService;

	@PostMapping("/reminders")
	@ApiOperation(value = "Create a reminder")
	public TaskReminder create(@Valid @RequestBody TaskReminder reminder) {
		return iTaskReminderService.create(reminder);
	}

	@GetMapping("/user/{userId}/reminders/{reminderId}")
	@ApiOperation(value = "Find a reminder")
	public TaskReminder find(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "reminderId") Long reminderId) {
		return iTaskReminderService.find(userId, reminderId);
	}

	@GetMapping("/user/{userId}/reminders")
	@ApiOperation(value = "Find all reminders")
	public List<TaskReminder> findAll(@PathVariable(value = "userId") Long userId) {
		return iTaskReminderService.findAll(userId);
	}

	@GetMapping("/user/{userId}/task/{taskId}/reminders")
	@ApiOperation(value = "Find all reminders by task id")
	public List<TaskReminder> findAllByTaskId(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "taskId") Long taskId) {
		return iTaskReminderService.finAllByTaskId(userId, taskId);
	}

	@PatchMapping("/user/{userId}/reminders/{reminderId}")
	@ApiOperation(value = "Update a reminder")
	public TaskReminder update(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "reminderId") Long reminderId,
			@RequestParam(value = "reminderDay") @DateTimeFormat(iso = ISO.DATE) Date reminderDay) {
		return iTaskReminderService.update(userId, reminderId, reminderDay);
	}

	@DeleteMapping("/user/{userId}/reminders/{reminderId}")
	@ApiOperation(value = "Delete a reminder")
	public void delete(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "reminderId") Long reminderId) {
		iTaskReminderService.delete(userId, reminderId);
	}

}
