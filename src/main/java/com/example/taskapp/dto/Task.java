package com.example.taskapp.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Task extends AbstractDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String title;
	String description;
	Long categoryId;
	Date dueDate;
	Long assignedToId;

	List<Link> links = new ArrayList<Link>();

	public Task() {

	}

	public Task(String title, String description, Long categoryId, Date dueDate, Long assignedToId, List<Link> links) {
		super();
		this.title = title;
		this.description = description;
		this.categoryId = categoryId;
		this.dueDate = dueDate;
		this.assignedToId = assignedToId;
		this.links = links;
	}

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

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}

}
