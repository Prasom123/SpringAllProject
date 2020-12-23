package com.heraizen.springiplstats.exception;

public class TeamAlreadyExistException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TeamAlreadyExistException(String message) {
      super(message);
	}
}
