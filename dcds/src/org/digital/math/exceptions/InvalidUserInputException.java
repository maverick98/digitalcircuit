/*
 * Created on Jun 17, 2007
 *
 * 
 */
package org.digital.math.exceptions;

import org.digital.exceptions.DigException;
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
