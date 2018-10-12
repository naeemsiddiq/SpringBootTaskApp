package com.example.taskapp.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException implements IException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;
	private HttpStatus status;

	public ApiException() {

	}

	public ApiException(String message) {
		this(ApiException.ERROR_RECORD_NOT_FOUND_EXCEPTION, message, HttpStatus.NOT_FOUND);
	}

	public ApiException(int code, String message) {
		super(message);
		this.code = code;
		this.status = HttpStatus.BAD_REQUEST;
	}

	public ApiException(int code, String message, HttpStatus status) {
		super(message);
		this.code = code;
		this.status = status;
	}

	public static ApiException nullError(String message) {
		return new ApiException(ApiException.ERROR_INVALID_INPUT, message, HttpStatus.BAD_REQUEST);
	}

	public static ApiException notFound(String message) {
		return new ApiException(message);
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
