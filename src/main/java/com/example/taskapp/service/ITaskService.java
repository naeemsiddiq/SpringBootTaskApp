package com.example.taskapp.service;

import java.util.Date;
import java.util.List;

import com.example.taskapp.dto.Task;

public interface ITaskService {

	public Task create(Task task);

	public Task find(Long taskId);

	public Task update(Long taskId, Task task);

	public void delete(Long taskId);

	public Task assign(Long taskId, Long userId);

	public List<Task> findAll();

	public List<Task> assignedTasks(Long userId);

	public Date taskDueDate(Long taskId, Long userId);
}
