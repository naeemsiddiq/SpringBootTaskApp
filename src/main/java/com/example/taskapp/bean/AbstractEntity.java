package com.example.taskapp.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@MappedSuperclass
public abstract class AbstractEntity implements Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -585700448193371853L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "BIGINT UNSIGNED")
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	protected Long id;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Column(columnDefinition = "SMALLINT")
	@ColumnDefault(value = "0")
	private Short status;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATETIME")
	@CreationTimestamp
	private Date createdAt;

	@JsonIgnore
	@Column(columnDefinition = "BIGINT UNSIGNED")
	@ColumnDefault(value = "1")
	private Long createdBy = 1L;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATETIME")
	@UpdateTimestamp
	private Date updatedAt;

	@Column(columnDefinition = "BIGINT UNSIGNED")
	@JsonIgnore
	private Long updatedBy;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

}
