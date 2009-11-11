package com.digital.circuit.test;

import com.digital.circuit.SequentialCircuit;
import com.digital.math.exceptions.InvalidUserInputException;

public class SequentialCircuitTest 
{
	public static void main(String args[]) throws InvalidUserInputException
	{
		SequentialCircuitTest sct = new SequentialCircuitTest();
		sct.test(args[0]);
		
	}
	private void test(String exp) throws InvalidUserInputException
	{
		SequentialCircuit sc = new SequentialCircuit(exp);
		System.out.println("sc = "+sc);
	}

}
