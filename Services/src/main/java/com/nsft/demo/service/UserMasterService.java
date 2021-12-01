package com.nsft.demo.service;

import org.json.JSONObject;

import com.nsft.demo.utility.ApiResponse;

public interface UserMasterService {

	public ApiResponse getUsers(JSONObject inputJson) throws Exception;

	public ApiResponse saveUser(JSONObject inputJson) throws Exception;
	
	public ApiResponse deleteUser(JSONObject inputJson) throws Exception;

	public ApiResponse loadUserDetailsById(JSONObject inputJson) throws Exception;
}
