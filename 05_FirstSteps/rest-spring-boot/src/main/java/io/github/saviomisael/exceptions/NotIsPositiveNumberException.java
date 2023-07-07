package io.github.saviomisael.exceptions;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotIsPositiveNumberException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public NotIsPositiveNumberException(String message) {
		super(message);
	}
}
