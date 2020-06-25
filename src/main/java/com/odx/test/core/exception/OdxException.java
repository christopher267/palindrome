package com.odx.test.core.exception;

public class OdxException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ExceptionType exceptionType;
	
	public OdxException(final String message, final Throwable tb, final ExceptionType type) {
		super(message, tb);
		this.setExceptionType(type);
	}
	
	public OdxException(final String message, final ExceptionType type) {
		super(message);
		this.setExceptionType(type);
	}

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(ExceptionType exceptionType) {
		this.exceptionType = exceptionType;
	}

}
