package com.jbk.dto;

import java.sql.Timestamp;

public class ExceptionResponse {
	
	private String Message;
	private String Path;
	private Timestamp timestamp;
	
	public ExceptionResponse() {
		// TODO Auto-generated constructor stub
	}

	public ExceptionResponse(String message, String path, Timestamp timestamp) {
		super();
		Message = message;
		Path = path;
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ExceptionResponse [Message=" + Message + ", Path=" + Path + ", timestamp=" + timestamp + "]";
	}
	
	

}
