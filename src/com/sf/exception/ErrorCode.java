
package com.sf.exception;


public enum ErrorCode
{
	ACCOUNT__NUMBER_DUPLICATE("There can't be 2 different accounts with the same Account Number {{number}}"),
	RECORD_DUPLICATE("There can't be duplicated records {{record}}"),
	FILE_CSV_NOT_FOUND("File CSV Not Found");

	private String message = "";

	private ErrorCode(String message)
	{
		this.message = message;
	}

	public String getMessage()
	{
		return message;
	}
}
