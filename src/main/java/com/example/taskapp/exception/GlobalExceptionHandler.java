package com.example.taskapp.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import com.example.taskapp.dto.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ExceptionResponse> handleError404(HttpServletRequest request, Exception e) {
		ExceptionResponse obj = new ExceptionResponse(ApiException.ERROR_ENDPOINT_NOT_FOUND, "End point not found",
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<ExceptionResponse>(obj, obj.getStatus());
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<ExceptionResponse> defaultErrorHandler(HttpServletRequest req, HttpServletResponse response,
			Exception e) throws Exception {

		int code = ApiException.ERROR_INVALID_INPUT;
		HttpStatus httpCode = HttpStatus.BAD_REQUEST;

		String message = null;
		if (e instanceof ApiException) {
			code = ((ApiException) e).getCode();
			message = e.getMessage();
			System.out.println("it came here" + "");
		} else if (e instanceof org.springframework.beans.TypeMismatchException) {
			code = ApiException.ERROR_INVALID_VALUE_FORMAT;
			message = "Invalid value or format";
		} else {
			message = e.getMessage();
		}

		switch (code) {
		case ApiException.ERROR_RECORD_NOT_FOUND_EXCEPTION:
			httpCode = HttpStatus.NOT_FOUND;
			break;
		}

		ExceptionResponse obj = new ExceptionResponse(code, message, httpCode);

		return new ResponseEntity<ExceptionResponse>(obj, obj.getStatus());
	}

}
