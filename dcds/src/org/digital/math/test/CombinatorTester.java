/*
 * Created on Jun 17, 2007
 *
 */
package org.digital.math.test;

import java.util.List;

import org.digital.math.Combinator;
import org.digital.math.exceptions.InvalidUserInputException;
import org.digital.util.Displayer;
/**
 * @author msahu
 * 
 */
public class CombinatorTester 
{

	public static void main(String[] args) 
	{
		CombinatorTester ct = new CombinatorTester();
		ct.testCombinator(args);
	}

	/**
	 * 
	 */
	private void testCombinator(String [] args) 
	{
		
		int size = Integer.parseInt(args[0]);
		
		if(args.length != size+2)
		{
			Displayer.sop("Please try again");
			return ;
		}
		
		int r = Integer.parseInt(args[1]);
		
		int inputs []= new int[size];
		
		for(int i=2;i<=size+1;i++)
		{
			
			inputs[i-2] = Integer.parseInt(args[i]);
		}

		
		List result=null;
		try {
			result = Combinator.findCombinations(inputs,r);
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ;
		}
		for(int i=0;i<result.size();i++)
		{
			int combination [] = (int [])result.get(i);
			
			for(int j=0;j<combination.length;j++)
			{
				if(combination[j] != -1){
					System.out.print("  "+combination[j]);	
				}
				
			}
			System.out.println("");
		}
		
		
	}
	
	
}
