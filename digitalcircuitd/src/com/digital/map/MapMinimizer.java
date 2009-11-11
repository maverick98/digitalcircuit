/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.map;

import java.util.List;

import com.digital.exceptions.DigException;
import com.digital.exceptions.InitializationException;
import com.digital.math.Number;
import com.digital.math.exceptions.InvalidUserInputException;
import com.digital.util.Displayer;
/**
 * @author msahu
 * 
 */
public  abstract class MapMinimizer implements Minimizer
{
	private static final String NOTIMPLEMENTED="NOTIMPLEMENTED";
	
	
	protected MapMinimizer(final int N)
	{
		KarnaughMap.initialize(N);
	}
	/**
	 * subclasses are encouraged to override this method.
	 * 
	 */
	public  abstract void  generate() throws DigException ;
	

	/**
	 * subclasses are encouraged to override this method.
	 * However they should call this for  type checkings
	 * the set should contain Number object
	 * 
	 */
	public  String minimize(final List numbers) throws DigException
	{
	
		if(numbers == null || numbers.size() == 0)
		{
			throw new InvalidUserInputException("Either numbers is null or its size is 0");
		}
		//System.out.println();
		for(int i =0; i <numbers.size();i++)
		{
			Object o = numbers.get(i);
			Number num=null;
			boolean isThisANumber = o instanceof Number;
			boolean isThisInsideTheRange =validateNumber((int)((num=(Number)o).getValue())); 
			if(isThisANumber && isThisInsideTheRange )
			{

		//		Displayer.sop(num.getBits(KarnaughMap.getN()));
				continue;
			}else
			{

				if(!isThisANumber)
				{
					throw new InvalidUserInputException("Expecting com.digital.math.Number found "+o.getClass());
				}else
				{
					throw new InvalidUserInputException("number is outside the range ");
				}
			}
			
		}
		//System.out.println();
		return NOTIMPLEMENTED;
		
	}
	

	public  String minimize(final int [] numbers ) throws DigException
	{
	
		if(numbers == null || numbers.length == 0)
		{
			throw new InvalidUserInputException("Either numbers is null or its size is 0");
		}
		System.out.println();
		
		for(int i =0; i <numbers.length;i++)
		{
			
			if(validateNumber(numbers[i]))
			{
				
				Displayer.sop(""+numbers[i]);
				continue;
			}else
			{
				throw new InvalidUserInputException("number "+ i +" is outside the range ");
			}
			
		}
		System.out.println();
	
	
		return NOTIMPLEMENTED;
	}
	/**
	 * @param i
	 * @return
	 * @throws InitializationException
	 */
	private boolean validateNumber(final int i)    
	{
	
		if(i <0 || i >=(int)Math.pow(2,KarnaughMap.getN()))
		{
			return false;
			
		}
		return true;
	}

}
