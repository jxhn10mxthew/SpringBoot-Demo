package com.myproject.exception;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomHttpStatusCode {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public String handleException(Exception ex, HttpServletResponse response) {
		response.setStatus(867);
        return ex.getMessage();
}
}