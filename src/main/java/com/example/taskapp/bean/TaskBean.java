package com.example.taskapp.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.taskapp.dto.Link;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class TaskBean extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5842128099564863928L;

	@NotBlank
	String title;

	@NotBlank
	String description;

	Long categoryId;

	@Column(columnDefinition = "DATETIME")
	Date dueDate;

	Long assignedToId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Long getAssignedToId() {
		return assignedToId;
	}

	public void setAssignedToId(Long assignedToId) {
		this.assignedToId = assignedToId;
	}

}
