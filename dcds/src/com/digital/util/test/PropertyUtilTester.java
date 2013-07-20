/*
 * Created on Jul 29, 2007
 *
 * 
 */
package org.digital.util.test;

import org.digital.util.PropertyUtil;

/**
 * @author msahu
 *
 */
public class PropertyUtilTester 
{
	public static void main(String args[])
	{
		String value = PropertyUtil.getProperty("xxx");
		
		System.out.println("value ="+value);
		System.out.println(PropertyUtil.setProperty("xxx","500"));
	}

}
