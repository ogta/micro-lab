package com.core.fintech.customer.entities;

import org.springframework.http.HttpStatus;

public class BaseResponse {

	private Object objectDetails;
	private String responseCode;
	private String responseDescription;
	private String errorCode;
	private String errorDescription;
	private HttpStatus httpStatusCode;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	public Object getObjectDetails() {
		return objectDetails;
	}

	public void setObjectDetails(Object objectDetails) {
		this.objectDetails = objectDetails;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(HttpStatus httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
