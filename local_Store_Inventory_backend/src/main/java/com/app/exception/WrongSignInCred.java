package com.app.exception;

@SuppressWarnings("serial")
public class WrongSignInCred extends RuntimeException {
	
	public WrongSignInCred(String mesg) {
	
		super(mesg);
	}
}
