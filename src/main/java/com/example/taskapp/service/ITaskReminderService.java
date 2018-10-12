package com.example.taskapp.service;

import java.util.Date;
import java.util.List;

import com.example.taskapp.dto.TaskReminder;

public interface ITaskReminderService {

	TaskReminder create(TaskReminder reminder);

	TaskReminder find(Long userId, Long reminderId);

	List<TaskReminder> findAll(Long userId);

	List<TaskReminder> finAllByTaskId(Long userId, Long taskId);

	TaskReminder update(Long userId, Long reminderId, Date reminderDay);

	void delete(Long userId, Long reminderId);

}
