package com.springboot.library.request;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.springboot.library.base.BaseRequest;
import com.springboot.library.entity.LibBook;
import com.springboot.library.entity.LibCustomer;
import com.springboot.library.entity.LibEmployee;

public class LoanBookRequest extends BaseRequest{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4067978980622928094L;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date issuedDate;
	
	@NotNull
	private Long libBookId;
	
	@NotNull
	private Long libCustomerId;
	
	@NotNull
	private Long libEmployeeIssuedId;
	
	@NotNull
	private Long libEmployeeReceived;

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public Long getLibBookId() {
		return libBookId;
	}

	public void setLibBookId(Long libBookId) {
		this.libBookId = libBookId;
	}

	public Long getLibCustomerId() {
		return libCustomerId;
	}

	public void setLibCustomerId(Long libCustomerId) {
		this.libCustomerId = libCustomerId;
	}

	public Long getLibEmployeeIssuedId() {
		return libEmployeeIssuedId;
	}

	public void setLibEmployeeIssuedId(Long libEmployeeIssuedId) {
		this.libEmployeeIssuedId = libEmployeeIssuedId;
	}

	public Long getLibEmployeeReceived() {
		return libEmployeeReceived;
	}

	public void setLibEmployeeReceived(Long libEmployeeReceived) {
		this.libEmployeeReceived = libEmployeeReceived;
	}
	
}
