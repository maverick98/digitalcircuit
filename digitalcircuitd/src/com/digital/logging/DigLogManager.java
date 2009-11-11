/*
 * Created on Jul 28, 2007
 *
 * 
 */
package com.digital.logging;

/**
 * @author msahu
 * 
 * 
 */
public class DigLogManager 
{
	private DigLogManager()
	{
		
	}
	
	public static DigLogger getLogger(Class clazz)
	{
		return DigLogger.getInstance(clazz);
	}

	
	
}
