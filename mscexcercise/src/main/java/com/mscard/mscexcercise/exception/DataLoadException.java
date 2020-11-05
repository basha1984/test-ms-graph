package com.mscard.mscexcercise.exception;

public class DataLoadException extends Exception {
	private static final long serialVersionUID = 1L;
	
	String message;

	/*
	 * Constructor of custom exception class here I am copying the message that we
	 * are passing while throwing the exception to a string and then displaying that
	 * string along with the message.
	 */
	public DataLoadException(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return ("MyException Occurred: " + message);
	}
}
