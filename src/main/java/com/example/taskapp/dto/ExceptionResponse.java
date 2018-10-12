package com.example.taskapp.dto;

import org.springframework.http.HttpStatus;

import com.example.taskapp.exception.ApiException;

public class ExceptionResponse implements Transferable{
	private static final long serialVersionUID = 1L;

	private String message;
	private int code;
	private HttpStatus status;

	public ExceptionResponse(ApiException e) {
		this.code = e.getCode();
		this.message = e.getMessage();
		this.status = e.getStatus();
	}

	public ExceptionResponse(int code, String message, HttpStatus status) {
		super();
		this.message = message;
		this.code = code;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
