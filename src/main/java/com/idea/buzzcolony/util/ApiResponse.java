package com.idea.buzzcolony.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class ApiResponse {

	@ApiModelProperty(required = true)
	private HttpStatus status;
	private String message;
	private Object data;
	private long timestamp = new Date().getTime();
	private int statusCode;

	public ApiResponse() {
	}

	public ApiResponse(HttpStatus status, String message, Object resultObject) {
		this.status = status;
		this.message = message;
		this.data = resultObject;
		this.timestamp = new Timestamp(new Date().getTime()).getTime();
		this.statusCode = status.value();
	}


	public static ApiResponse getFailureResponse(){
		return new ApiResponse(HttpStatus.BAD_REQUEST , "Something Went wrong!", null);
	}
	public static ApiResponse getSuccessResponse(){
		return new ApiResponse(HttpStatus.OK , "Success", null);
	}
}
