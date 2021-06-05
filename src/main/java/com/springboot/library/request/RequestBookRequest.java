package com.springboot.library.request;

import java.util.Date;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.springboot.library.base.BaseRequest;

public class RequestBookRequest extends BaseRequest{

	
	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private Date requestDate;
	
	@NotNull
	private Long libBookId;
	
	@NotNull
	private Long libCustomerId;

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
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
	
	
}
