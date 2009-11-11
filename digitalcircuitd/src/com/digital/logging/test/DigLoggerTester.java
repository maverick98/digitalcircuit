/*
 * Created on Jul 29, 2007
 *
 * 
 */
package com.digital.logging.test;

import com.digital.logging.DigLogManager;
import com.digital.logging.DigLogger;

/**
 * @author msahu
 *
 * 
 */
public class DigLoggerTester 
{

	private static final DigLogger logger = DigLogManager.getLogger(DigLoggerTester.class);
	
	public static void main(String args[])
	{
		logger.debug("hello");
	}
}
