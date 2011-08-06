/*
 * Created on Jul 6, 2007
 *
 */
package org.digital.map;

import java.util.List;

import org.digital.exceptions.DigException;

/**
 * @author  msahu
 *
 * 
 */
public interface  Minimizer 
{
	
	
	 void  generate() throws DigException ;
	
	 String minimize(String exp) throws DigException;
	 String minimize(final List numbers) throws DigException;
	
	 String minimize(final int [] numbers ) throws DigException;
	
}
