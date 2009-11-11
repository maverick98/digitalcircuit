/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.math;

import java.util.ArrayList;
import java.util.List;

import com.digital.math.exceptions.InvalidNumberException;
import com.digital.math.exceptions.InvalidUserInputException;
import com.digital.util.Displayer;






/**
 * @author msahu
 *
 * 
 */
public class Combinator 
{
	
	
	
	
	public static  List findCombinations(int [] input , int  r) throws InvalidUserInputException
	{
		if(r < 0)
		{
			throw new InvalidUserInputException("r is negative number");
		}
		if(r > input.length)
		{
			
			throw new InvalidUserInputException("r > n");
		}
		long size = calculateNoOfCombinations(input.length,r);
		//Displayer.sop("size ="+size);
		List result = new ArrayList();
		
		//Hack --- need to fix it 
		if(r == 0)
		{
			for(int i=0; i<input.length ;i++)
			{
				input[i]=-1;
			}
			result.add(input);
			return result;
		}
		
		final int N = input.length;
		Number n=null;
		for(int i=1; i<(int)Math.pow(2,N);i++)
		{
			try 
			{
					n = new Number(i);
					
					if(n.getOnesCount() == r)
					{
						//Displayer.sop("my count matched"+n);
						int combination [] =findANDOfTwoArrays
												(
														input
														,
														convertBits
															(
																	n.getBits()
															)
												); 
						result.add(combination);
						//Displayer.sop(combination);
						if(result.size() == size)
						{
							break;
						}
					}
			
			} catch (InvalidNumberException e) {

				e.printStackTrace();
			}
			
		}
		
		//Displayer.sop("Before returning from findCombination");
		return result;
	}

	/**
	 * @param bits
	 * @return
	 */
	private static int[] convertBits(int[] bits) {

		for(int i=0;i<bits.length;i++)
		{
			if(bits[i]==0)
			{
				bits[i]=-1;
			}
		}
		return bits;
	}

	/**
	 * @param length
	 * @param r
	 * @return n!/(r! * (n-r)!)
	 */
	private static long calculateNoOfCombinations(final int n,final int r) 
	{
		return  factorial(n)/(factorial(r)*factorial(n-r));
	}
	private static long factorial(final int x)
	{
		if(x ==0)
		{
			return 1;
		}
		return x * factorial(x-1);
		
	}

	/**
	 * @param input
	 * @param bits
	 * @return
	 */
	private static int [] findANDOfTwoArrays(final int[] input, final int[] bits) 
	{
		int result[] = new int [input.length];
		convertBits(result);
		int paddedBits [] = padZeroes(bits , input.length);
		
		for(int i=0;i<input.length; i++)
		{
			if(paddedBits[i]!=-1)
			{
				result[i]= input[i];
			}
		}
		
		return result;
	}

	/**
	 * @param bits
	 */
	private static int [] padZeroes(final int[] bits ,final int N) 
	{
		int [] result = new int [N];
		for(int i=0;i<N;i++){
			result[i]=-1;
		}

		System.arraycopy(bits,0,result,N-bits.length,bits.length);
		return result;
	}

}
