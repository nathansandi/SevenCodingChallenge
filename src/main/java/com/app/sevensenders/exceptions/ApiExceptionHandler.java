package com.app.sevensenders.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = {GeneralException.class})
	public ResponseEntity<Object> handleException(Throwable e){
		//create payload containing exeption details
		ApiException apiExcp = new ApiException(
			e.getMessage(),
			HttpStatus.BAD_REQUEST,
			ZonedDateTime.now(ZoneId.of("Z"))
				
		);
		
		
		//return response
		return new ResponseEntity<>(apiExcp, HttpStatus.BAD_REQUEST);
	}
	

}
