package com.nsft.demo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class CommonAttributes {

	@Column(name = "createdOn")
	private Timestamp createdOn;
	
	@UpdateTimestamp
	@Column(name = "updatedOn")
	private Timestamp updatedOn;
	
	@Column(name = "isactive")
	private Boolean isActive;
	
	@Column(name = "createdby")
	private Integer createdBy;
	
	@Column(name = "updatedby")
	private Integer updatedBy;
	
	@Column(name = "version")
	private Integer version;

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
