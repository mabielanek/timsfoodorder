package com.timsmeet.services;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -800424749757810801L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}
	
}
