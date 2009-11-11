/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.math.exceptions;

import com.digital.exceptions.DigException;
/**
 * @author msahu
 *
 * 
 */
public class InvalidUserInputException extends DigException
{
	public InvalidUserInputException(String message)
	{
		super((String) message);
	}

}
