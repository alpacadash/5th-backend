package com.spring.api.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL) // null이면 JSON 제외
public class ApiResponseDto<T> {

	private Integer statusCode;
	private HttpStatus httpStatus;
	private String message;
	private T data;
	
}
