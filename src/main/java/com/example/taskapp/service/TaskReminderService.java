package com.example.taskapp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.taskapp.bean.TaskReminderBean;
import com.example.taskapp.dto.TaskReminder;
import com.example.taskapp.exception.ApiException;
import com.example.taskapp.mapper.ITaskReminderMapper;
import com.example.taskapp.repository.TaskReminerRepository;
import com.example.taskapp.utils.ValueUtils;

@Service
public class TaskReminderService implements ITaskReminderService {

	@Autowired
	TaskReminerRepository taskReminerRepository;

	@Autowired
	ITaskReminderMapper mapper;

	@Autowired
	ITaskService iTaskService;

	@Override
	public TaskReminder create(TaskReminder dto) {
		validateTaskReminder(dto);

		TaskReminderBean bean = mapper.map(dto);
		bean.setCreatedBy(dto.getUserId());
		bean.setCreatedAt(new Date());
		bean.setDays(daysInCount(dto.getReminderDay(), dto.getTaskId(), dto.getUserId()));

		return mapper.map(taskReminerRepository.save(bean));
	}

	private void validateTaskReminder(TaskReminder dto) {
		if (dto == null)
			throw ApiException.nullError("Task object is required");
		ValueUtils.checkRequired(dto.getUserId(), "User id is required");
		ValueUtils.checkRequired(dto.getTaskId(), "Task id is required");
		ValueUtils.checkRequired(dto.getReminderDay(), "Reminer Day is required");

	}

	@Override
	public TaskReminder find(Long userId, Long reminderId) {
		TaskReminderBean bean = findWithCheck(reminderId);
		if (!bean.getUserId().equals(userId)) {
			throw new ApiException(ApiException.ERROR_INVALID_INPUT, "This reminder does not belong to you");
		}

		return mapper.map(bean);
	}

	private TaskReminderBean findWithCheck(Long reminderId) {
		TaskReminderBean bean = taskReminerRepository.findById(reminderId)
				.orElseThrow(() -> ApiException.notFound("Reminer does not exist wih reminder id " + reminderId));
		return bean;
	}

	@Override
	public List<TaskReminder> findAll(Long userId) {
		List<TaskReminderBean> beans = taskReminerRepository.findAllByUserId(userId);
		if (beans.isEmpty())
			throw ApiException.notFound("No reminders found with given user id");
		return mapper.map(beans);
	}

	private Long daysInCount(Date reminderTime, Long taskId, Long userId) {
		Date taskDueDate = iTaskService.taskDueDate(taskId, userId);

		long difference = reminderTime.getTime() - taskDueDate.getTime();
		long differenceDates = difference / (24 * 60 * 60 * 1000);
		return differenceDates;
	}

	@Override
	public List<TaskReminder> finAllByTaskId(Long userId, Long taskId) {
		List<TaskReminderBean> beans = taskReminerRepository.findAllByTaskIdAndUserId(taskId, userId);
		if (beans.isEmpty())
			throw ApiException.notFound("No reminders found with given user id and task id");

		return mapper.map(beans);
	}

	@Override
	public TaskReminder update(Long userId, Long reminderId, Date reminderDay) {
		TaskReminderBean bean = findWithCheck(reminderId);
		if (!bean.getUserId().equals(userId)) {
			throw new ApiException(ApiException.ERROR_INVALID_INPUT,
					"This reminder does not belong to you,So, you can't update this");
		}
		bean.setReminderDay(reminderDay);
		bean.setDays(daysInCount(reminderDay, bean.getTaskId(), userId));
		bean.setUpdatedAt(new Date());
		bean.setUpdatedBy(userId);

		return mapper.map(taskReminerRepository.save(bean));
	}

	@Override
	public void delete(Long userId, Long reminderId) {
		TaskReminderBean bean = findWithCheck(reminderId);
		if (!bean.getUserId().equals(userId)) {
			throw new ApiException(ApiException.ERROR_INVALID_INPUT,
					"This reminder does not belong to you,So, you can't delete this");
		}
		taskReminerRepository.delete(bean);
	}
}
