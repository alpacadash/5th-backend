package com.spring.api.hanlder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.spring.api.dto.ErrorResponse;

@RestControllerAdvice
public class GlobalApiExceptionHandler {
	
	/*
	 * 예외 발생
	 *    -> try ~ catch
	 *    -> 컨트롤러의 @ExceptionHanlder
	 *    -> Advice의 @ExceptionHanlder
	 *    -> Spring 기본 예외 처리(BasicErrorController)
	 */
	
	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException e) {
		return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(ErrorResponse.of("BAD_REQEUST", e.getMessage()));
	}
	
}
