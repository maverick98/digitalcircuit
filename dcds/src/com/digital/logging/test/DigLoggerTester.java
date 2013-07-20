/*
 * Created on Jul 29, 2007
 *
 * 
 */
package org.digital.logging.test;

import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;

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
