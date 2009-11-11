/*
 * Created on Jul 29, 2007
 *
 * 
 */
package com.digital.util.test;

import com.digital.util.PropertyUtil;

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
