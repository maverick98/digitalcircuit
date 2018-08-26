/*
 * Created on Jul 7, 2007
 *
 * 
 */
package org.digital.ds.test;

import org.digital.circuit.SequentialCircuit;
import org.digital.ds.Expression;
import org.digital.exceptions.IllegalStateException;
import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 *
 * 
 */
public class ExpresssionTest 
{

	public static void main(String[] args) throws IllegalStateException, InvalidUserInputException 
	{
	
		ExpresssionTest et = new ExpresssionTest();
		et.test(args[0]);
	}

	/**
	 * @throws IllegalStateException
	 * @throws InvalidUserInputException
	 * 
	 */
	private void test(String str) throws IllegalStateException, InvalidUserInputException 
	{
		//System.out.println("passing someting "+str);
		SequentialCircuit sc = new SequentialCircuit(str);
		Expression infix = new Expression(sc);
		
		System.out.println(infix.toPrefix());
		
		
		
	}
	
}
