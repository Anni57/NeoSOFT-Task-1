package com.nsft.demo.utility;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class ApiResponse {

	private String message;
	private int code;
	private Object response;
	private List<Object> responseList;
	
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
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	public List<Object> getResponseList() {
		return responseList;
	}
	public void setResponseList(List<Object> responseList) {
		this.responseList = responseList;
	}
	
}
