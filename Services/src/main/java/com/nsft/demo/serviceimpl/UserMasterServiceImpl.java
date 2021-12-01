package com.nsft.demo.serviceimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nsft.demo.dto.UserMasterDto;
import com.nsft.demo.model.UserMaster;
import com.nsft.demo.repository.UserMasterRepository;
import com.nsft.demo.service.UserMasterService;
import com.nsft.demo.utility.ApiResponse;
import com.nsft.demo.utility.Constants;

@Component("UserMasterServiceImpl")
public class UserMasterServiceImpl implements UserMasterService {
	
	@Autowired
	private UserMasterRepository userMasterRepository;
	
	private static final Logger log = LogManager.getLogger(UserMasterServiceImpl.class);

	public ApiResponse getUsers(JSONObject inputJson) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(Constants.SC_SUCCESS);
		log.info("getting Users");
		try {
			List<UserMaster> userMaster = userMasterRepository.findAllByIsActiveOrderById(true);
			apiResponse.setResponse(getUserMasterDtos(userMaster));
		} catch (Exception e) {
			apiResponse.setCode(Constants.SC_FAIL);
		}
		return apiResponse;
	}

	public ApiResponse saveUser(JSONObject inputJson) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(Constants.SC_SUCCESS);
		log.info("getting Users");
		try {
			if (inputJson.has("userDetails")) {
				Timestamp currentTime = new Timestamp(System.currentTimeMillis());
				JSONObject formData = inputJson.optJSONObject("userDetails");

				UserMaster userMaster = null; 
				if (inputJson.has("userMasterId")) {
					userMaster = userMasterRepository.
							findByIdAndIsActive(inputJson.optLong("userMasterId"), true);
				}else {
					userMaster = new UserMaster();
				}
				
				userMaster = setUserDetails(currentTime, formData, userMaster);

				UserMaster user = saveUser(userMaster);
				apiResponse.setResponse(getDtoDetails(user));
			}
		} catch (Exception e) {
			apiResponse.setCode(Constants.SC_FAIL);
		}
		return apiResponse;
	}

	private UserMaster setUserDetails(Timestamp currentTime, JSONObject formData, UserMaster userMaster) {
		userMaster.setUserName(formData.optString("userName"));
		userMaster.setContactNumber(formData.optString("contactNumber"));
		userMaster.setEmail(formData.optString("email"));
		userMaster.setIsActive(true);
		userMaster.setCreatedOn(currentTime);
		
		return userMaster;
	}
	
	public UserMaster saveUser(UserMaster user) {
		Objects.requireNonNull("User Master Entity should not be null");
		return userMasterRepository.save(user);
	}
	
	public static UserMasterDto getDtoDetails(UserMaster userMaster) {
		return new UserMasterDto(userMaster.getId(),
								 userMaster.getUserName(),
								 userMaster.getEmail(),
								 userMaster.getContactNumber(),
								 userMaster.getIsActive(),
								 userMaster.getCreatedOn(),
								 userMaster.getUpdatedOn());
	}
	
	public static List<UserMasterDto> getUserMasterDtos(List<UserMaster> users) {
		List<UserMasterDto> userMasters = new ArrayList<UserMasterDto>();
		for (UserMaster user : users) {
			userMasters.add(getDtoDetails(user));
		}
		return userMasters;
	}

	public ApiResponse deleteUser(JSONObject inputJson) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(Constants.SC_SUCCESS);
		log.info("deleting User");
		try {
			if (null != inputJson && inputJson.has("id")) {
				UserMaster userMaster = userMasterRepository.findByIdAndIsActive(
						inputJson.optLong("id"), true);
				if(null != userMaster) {
					userMaster.setIsActive(false);
					
					UserMaster user = saveUser(userMaster);
					apiResponse.setResponse(getDtoDetails(user));
				}
			}else {
				apiResponse.setCode(Constants.SC_NOT_FOUND);
				apiResponse.setMessage(Constants.DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Exception occured in disabling Users",e);
		}
		return apiResponse;
	}

	public ApiResponse loadUserDetailsById(JSONObject inputJson) throws Exception {
		ApiResponse apiResponse = new ApiResponse();
		apiResponse.setCode(Constants.SC_SUCCESS);
		log.info("getting User Details By User Id");
		try {
			if (null != inputJson && inputJson.has("userMasterId")) {
				UserMaster userMaster = userMasterRepository.findByIdAndIsActive(
						inputJson.optLong("userMasterId"), true);
				if(null != userMaster) {
					apiResponse.setResponse(getDtoDetails(userMaster));
				}else {
					apiResponse.setCode(Constants.SC_NOT_FOUND);
					apiResponse.setMessage(Constants.DATA_NOT_FOUND);
				}
			}else {
				apiResponse.setCode(Constants.SC_NOT_FOUND);
				apiResponse.setMessage(Constants.DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Exception occured in disabling Users",e);
		}
		return apiResponse;
	}

}
