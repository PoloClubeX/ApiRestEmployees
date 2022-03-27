package Parameta.com.ApiRestEmployees.Exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String httpCodeMessage;

	public ExceptionResponse(LocalDateTime timestamp, String message, String details, String httpCodeMessage) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.httpCodeMessage = httpCodeMessage;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	public String getHttpCodeMessage() {
		return httpCodeMessage;
	}
}
