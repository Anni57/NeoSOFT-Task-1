package com.nsft.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nsft.demo.service.UserMasterService;
import com.nsft.demo.utility.ApiResponse;

@CrossOrigin
@RestController
@RequestMapping(value = "/user/", produces = { MediaType.TEXT_PLAIN_VALUE, MediaType.APPLICATION_JSON_VALUE })
public class UserMasterController {

	@Autowired
	private UserMasterService userMasterService;

	private static final Logger log = LogManager.getLogger(UserMasterController.class);

	@RequestMapping(value = "getAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> getUsers(@RequestBody String input, HttpServletRequest req) throws Exception {
		log.info("getting All Users");
		JSONObject inputJson = new JSONObject(input);
		ApiResponse apiResponse = userMasterService.getUsers(inputJson);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> saveUser(@RequestBody String input, HttpServletRequest req) throws Exception {
		log.info("saving User");
		JSONObject inputJson = new JSONObject(input);
		ApiResponse apiResponse = userMasterService.saveUser(inputJson);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "disable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> deleteUser(@RequestBody String input, HttpServletRequest req) throws Exception {
		log.info("deleting user");
		JSONObject inputJson = new JSONObject(input);
		ApiResponse apiResponse = userMasterService.deleteUser(inputJson);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "loadById", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> loadUserDetailsById(@RequestBody String input, HttpServletRequest req)
			throws Exception {
		log.info("getting user details by Id");
		JSONObject inputJson = new JSONObject(input);
		ApiResponse apiResponse = userMasterService.loadUserDetailsById(inputJson);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

}
