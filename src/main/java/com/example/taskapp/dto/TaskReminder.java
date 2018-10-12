package com.example.taskapp.dto;

import java.util.Date;

public class TaskReminder extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Long userId;
	Long taskId;
	Date reminderDay;
	Long days;

	public Long getDays() {
		return days;
	}

	public void setDays(Long days) {
		this.days = days;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getReminderDay() {
		return reminderDay;
	}

	public void setReminderDay(Date reminderDay) {
		this.reminderDay = reminderDay;
	}

}
