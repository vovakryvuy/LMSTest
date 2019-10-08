package com.lmstest.network.response;

public class ErrorResponse {
	private int code;
	private String status;
	private Response response;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}

	public class Response {
		private String message;
		private String fail_reason;
		private int fail_code;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getFail_reason() {
			return fail_reason;
		}

		public void setFail_reason(String fail_reason) {
			this.fail_reason = fail_reason;
		}

		public int getFail_code() {
			return fail_code;
		}

		public void setFail_code(int fail_code) {
			this.fail_code = fail_code;
		}
	}
}
