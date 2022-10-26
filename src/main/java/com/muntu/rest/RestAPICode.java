package com.muntu.rest;

public class RestAPICode {

	// 1xx Informational

	private RestAPICode() {

	}

	public static final String CONTINUE_STATUS = "Continue";
	public static final int CONTINUE_STATUS_CODE = 100;

	public static final String SWITCHING_PROTOCOLS_STATUS = "Switching Protocols";
	public static final int SWITCHING_PROTOCOLS_STATUS_CODE = 101;

	public static final String PROCESSING_STATUS = "Processing";
	public static final int PROCESSING_STATUS_CODE = 102;

	public static final String CHECKPOINT_STATUS = "Checkpoint";
	public static final int CHECKPOINT_STATUS_CODE = 103;

	// 2xx Success

	public static final String OK_STATUS = "OK";
	public static final int OK_STATUS_CODE = 200;

	public static final String CREATED_STATUS = "Created";
	public static final int CREATED_STATUS_CODE = 201;

	public static final String ACCEPTED_STATUS_STATUS = "Accepted";
	public static final int ACCEPTED_STATUS_CODE = 202;

	public static final String NO_CONTENT_STATUS = "No Content";
	public static final int NO_CONTENT_STATUS_CODE = 204;

	// --- 4xx Client Error ---

	public static final String BAD_REQUEST_STATUS = "Bad Request";
	public static final int BAD_REQUEST_STATUS_CODE = 400;
	public static final String UNAUTHORIZED_STATUS = "Unauthorized";
	public static final int UNAUTHORIZED_STATUS_CODE = 401;

	public static final String NOT_FOUND_STATUS = "Not Found";
	public static final int NOT_FOUND_STATUS_CODE = 404;

	public static final String METHOD_NOT_ALLOWED_STATUS = "Method Not Allowed";
	public static final int METHOD_NOT_ALLOWED_STATUS_CODE = 405;

	// --- 5xx Server Error ---

	public static final String INTERNAL_SERVER_ERROR_STATUS = "Internal Server Error";
	public static final int INTERNAL_SERVER_ERROR_STATUS_CODE = 500;

}