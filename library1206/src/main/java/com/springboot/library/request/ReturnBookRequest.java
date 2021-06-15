package com.springboot.library.request;

import java.util.Date;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.library.base.BaseRequest;

public class ReturnBookRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean has_fine;
	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date returnDate;
	
	@NotNull
	private Long libBookId;

	@NotNull
	private Long libCustomerId;
	
	@NotNull
	private Long libEmployeeId;

	public boolean isHas_fine() {
		return has_fine;
	}

	public void setHas_fine(boolean has_fine) {
		this.has_fine = has_fine;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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

	public Long getLibEmployeeId() {
		return libEmployeeId;
	}

	public void setLibEmployeeId(Long libEmployeeId) {
		this.libEmployeeId = libEmployeeId;
	}


}
