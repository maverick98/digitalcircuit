/*
 * Created on Jun 27, 2007
 *
 */
package org.digital.exceptions;

/**
 * @author msahu
 *
 * 
 */
public class DigException extends Exception
{
	public DigException(Object message)
	{
		super("Reason:- "+(String) message);
	}
}
