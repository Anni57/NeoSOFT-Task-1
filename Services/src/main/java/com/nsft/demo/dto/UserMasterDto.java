package com.nsft.demo.dto;

import java.util.Date;

public class UserMasterDto {

	private Long id;
	private String userName;
	private String email;
	private String contactNumber;
	private Boolean isActive;
	private Date createdOn;
	private Date updatedOn;
	
	public UserMasterDto() {}
	
	public UserMasterDto(Long id, String userName, String email, String contactNumber, Boolean isActive,
							Date createdOn, Date updatedOn) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
