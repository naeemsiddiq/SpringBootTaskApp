package com.example.taskapp.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "taskReminder")
@EntityListeners(AuditingEntityListener.class)
public class TaskReminderBean extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2151264306870203191L;

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
