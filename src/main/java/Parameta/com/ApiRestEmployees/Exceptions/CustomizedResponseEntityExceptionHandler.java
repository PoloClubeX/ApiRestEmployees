package Parameta.com.ApiRestEmployees.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	

	  @ExceptionHandler(BadRequest.class)
	  public final ResponseEntity<ExceptionResponse> handleNotFoundException(BadRequest ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
	        request.getDescription(false),HttpStatus.BAD_REQUEST.getReasonPhrase());
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	  }
	  
	  @ExceptionHandler(InternalServerError.class)
	  public final ResponseEntity<ExceptionResponse> handleNotFoundException(InternalServerError ex, WebRequest request) {
	    ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),
	        request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	    return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }

	
}
