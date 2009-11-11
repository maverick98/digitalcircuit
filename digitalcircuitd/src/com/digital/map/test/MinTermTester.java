/*
 * Created on Jun 24, 2007
 *
 * 
 */
package com.digital.map.test;

import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;

import com.digital.exceptions.DigException;
import  com.digital.math.Number;
import com.digital.math.exceptions.InvalidNumberException;
import com.digital.math.exceptions.InvalidUserInputException;
import com.digital.map.MinTerm;
import com.digital.util.Displayer;
/**
 * @author admin
 *
 * 
 */
public class MinTermTester {
	public static void main(String args[])
	{
		MinTermTester mt = new MinTermTester();
		try {
			mt.testMinTerm(args);
			
			
		} catch (InvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	/**
	 * @throws InvalidNumberException
	 * @throws InvalidUserInputException
	 * 
	 */
	private void testMinTerm(String args[]) throws InvalidNumberException, InvalidUserInputException 
	{
		
		List list = new ArrayList();
		int l =-1;
		int i=1;
		int size = Integer.parseInt(args[1]);
		do
		{
			l = Integer.parseInt(args[i++]);
			list.add(new Number(l));
			
		}while(i<size+2);
		
		
		
		MinTerm mt = new MinTerm(list,Integer.parseInt(args[0]));
		mt.display();
		Displayer.sop(mt.toString());
		//System.out.println("="+args[i]);
		Displayer.sop(mt.toString(args[i].toCharArray()));
		
		List numbers = new ArrayList();
		numbers.add(new Number(0));
		numbers.add(new Number(1));
		numbers.add(new Number(4));
		numbers.add(new Number(5));
		numbers.add(new Number(6));
		
		
		System.out.println("result "+mt.isSubsetOf(numbers));
	}

}
