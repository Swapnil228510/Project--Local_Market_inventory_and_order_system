package com.app.exception;

import org.hibernate.ResourceClosedException;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String mesg) {
		super(mesg);
	}

}
