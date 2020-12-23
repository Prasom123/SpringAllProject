package com.heraizen.springiplstats.web;

import java.time.LocalDateTime;

import org.apache.catalina.valves.ErrorReportValve;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.heraizen.springiplstats.exception.DataNotFoundException;
import com.heraizen.springiplstats.exception.TeamAlreadyExistException;
import com.heraizen.springiplstats.exception.TeamLabelNotFoundException;
import com.heraizen.springiplstats.web.errorresponse.ErrorResponse;



@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(value = {TeamAlreadyExistException.class})
	public ResponseEntity<ErrorResponse> handleConflict1(Exception ex, WebRequest request) {
		ErrorResponse errorResponse=ErrorResponse.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {DataNotFoundException.class})
	public ResponseEntity<ErrorResponse> handleConflict2(Exception ex, WebRequest request) {
		ErrorResponse errorResponse=ErrorResponse.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.CONFLICT);
	}
	@ExceptionHandler(value = {TeamLabelNotFoundException.class})
	public ResponseEntity<ErrorResponse> handleConflict3(Exception ex, WebRequest request) {
		ErrorResponse errorResponse=ErrorResponse.builder().status(HttpStatus.CONFLICT.value()).message(ex.getMessage()).dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.CONFLICT);
	}
}
