/*
 * Created on Jun 17, 2007
 *
 */
package org.digital.map;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;
import org.digital.math.Number;
import org.digital.math.exceptions.InvalidNumberException;
import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 * 
 * 
 */
public class MinTerm implements Externalizable
{
	private static DigLogger logger = DigLogManager.getLogger(MinTerm.class);
	private List numbers = new ArrayList();
	private int width;
	public MinTerm(List numbers , int width) throws InvalidNumberException, InvalidUserInputException
	{
		if(numbers == null || !checkNumbers(numbers))
		{
			throw new InvalidNumberException("found non number object in numbers or numbers may be null");
		}
		this.numbers = numbers;
		validateWidthAndNumbers(width);
		this.width = width;
		
	}
	
	public List getNumbers()
	{
		List results = new ArrayList();
		
		for(int i=0;i<numbers.size();i++)
		{
			Number number = (Number)numbers.get(i);
			results.add(Number.clone(number));
			
		}
		
		return numbers;
	}
	public int getCount()
	{
		return numbers.size();
	}
	public int getWidth()
	{
		return width;
	}
	/**
	 * added as an alternate way for Object.clone()
	 * 
	 */
	private MinTerm(MinTerm mt) throws InvalidNumberException, InvalidUserInputException
	{
	
		this(mt.getNumbers(),mt.getWidth());
	}
	
	
	/**
	 * added for serialization reflection 
	 */
	public MinTerm() 
	{
	
	}
	/**
	 * @throws InvalidUserInputException
	 * 
	 */
	private void validateWidthAndNumbers(int width) throws InvalidUserInputException 
	{
		
		if(width < getMaxWidth())
		{
			throw new InvalidUserInputException("width "+width +" is less than the width of number");
		}
		
	}
	
	private int  getMaxWidth() 
	{
		Number big =(Number)numbers.get(0);
		
		for(int i=1;i<numbers.size();i++)
		{
			Number cur = (Number)numbers.get(i);
			if(big.lessThan(cur))
			{
				big = cur;
			}
		}
		
		return big.getBits().length;
	}
	

	/**
	 * @param numbers2
	 */
	private static boolean checkNumbers(List numbers) 
	{
		for(int i=0;i<numbers.size();i++)
		{
			Object o = numbers.get(i);
			if(o instanceof Number)
			{
				continue;
			}else
			{
				return false;
			}
		}
		return true;
	}
	public String toString()
	{
		return stringify(null);
	}
	public String toString(char names[])
	{
		return stringify(names);
	}
	
	public String showMinTerms()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		for(int i=0;i <numbers.size();i++)
		{
			sb.append(((Number)numbers.get(i)).getValue());
			sb.append(i==(numbers.size()-1)?"":", ");
		}
		sb.append("}");
		
		return sb.toString();
		
	}
	
	public void display()
	{
		logger.debug("----");
		for(int i=0;i<numbers.size();i++)
		{
			
				logger.debug(((Number)numbers.get(i)).showBits(width));
			
		}
		logger.debug("----");
	}
	
	public boolean equals(Object that)
	{
		return that == null || !(that instanceof MinTerm)
					?
							false
							:
							!((MinTerm)that).stringify(null).equals(this.stringify(null))
								?
										false
										:
										true	
					;		
	}
	
	private String  stringify(char names[])
	{
		
		Map map = new LinkedHashMap(width);
		intializeMap(map);
		int l= numbers.size();
		
		for(int i=0; i<l-1;i++)
		{
			Number number1 =(Number)numbers.get(i);
			Number number2 =(Number)numbers.get(i+1);
			
			try {
				logger.debug("Trying comparing "+number1.showBits(width) +" and "+number2.showBits(width) );
				findSimilarBits(number1.getBits(width),number2.getBits(width),map);
			} catch (InvalidUserInputException e) {
				
				e.printStackTrace();
			}
			
		}
		
		logger.debug(map);
		char c = 0;
		StringBuffer sb= new StringBuffer();
		int i=0;	
			for(Iterator itr = map.keySet().iterator();itr.hasNext();i++)
			{
				Object key  = itr.next();
				int idx = ((Integer)key).intValue();
				int value =((Integer) (map.get(key))).intValue(); 
			
				if(value == 0)
				{
					c=(names==null)?(char)(65+idx):(char)(names[idx]-32);
					sb.append(c);
				}else if(value==1)
				{
					c=(names==null)?(char)(97+idx):(char)(names[idx]);
					sb.append(c);
				}
			
				
			}
		
		return sb.toString();
	}
	/**
	 * 
	 */
	private void intializeMap(Map map) 
	{
		int bitsOfFirstNumber [] = null;
		try {
			 bitsOfFirstNumber = ((Number)numbers.get(0)).getBits(width);
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0 ; i < width ; i++)
		{
			putIntInMap(map,i,bitsOfFirstNumber[i]);
		}
		
	}
	/**
	 * @param result
	 * @param bits
	 * @return
	 */
	private void findSimilarBits(int[] number1, int[] number2 , Map  intermediateMap) 
	{
		logger.debug("map is "+intermediateMap);
		
		for(Iterator itr = intermediateMap.keySet().iterator();itr.hasNext();)
		{
			
			int x = ((Integer)itr.next()).intValue();
			
			logger.debug("x= "+x);
			if(number1[x]!=number2[x])
			{
				itr.remove();
				
			}else 
			{
				if(number1[x] == 0)
				{
					putIntInMap(intermediateMap , x , 0);
					
				}else if(number1[x] == 1)
				{
					putIntInMap(intermediateMap , x , 1);
					
				}
			}
			
			
		}
		
		
		
		
		logger.debug("after comparing above numbers intermediate result is "+intermediateMap);
		
		
		

	}
	

	private static void putIntInMap(Map map , int idx ,int value)
	{
		map.put(new Integer(idx) , new Integer(value));
	}
	
	



	
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
	
		int width = in.readInt();
		this.width=width;
		int size =  in.readInt();	
		List numbers = new ArrayList();
		for(int i=0 ;i <size;i++)
		{
			Number number = (Number)in.readObject();
			numbers.add(number);
			
		}
		this.numbers=numbers;
		
	}


	public void writeExternal(ObjectOutput out) throws IOException 
	{
		out.writeInt(width);
		out.writeInt(numbers.size());
		for(int i=0 ;i <numbers.size();i++)
		{
			Number number = (Number)numbers.get(i);
			out.writeObject(number);
			
		}
		
	}

	/**
	 * added as an alternate way for Object.clone()
	 * 
	 */
	public static MinTerm clone(MinTerm mt)
	{
		MinTerm mtClone = null;
		
		try 
		{
			mtClone = new MinTerm(mt);
		} catch (InvalidNumberException e) 
		{

			logger.error(e);
		} catch (InvalidUserInputException e) 
		{
			logger.error(e);
		}
		
		return mtClone;
	}
	
	public boolean isSubsetOf(List numbers)
	{
		logger.debug("in subset this  size"+this.numbers.size());
		logger.debug("input  size"+numbers.size());
		
		
		if(this.numbers.size() >= numbers.size())
		{
			return false;
		}
		
		logger.debug(this+" is subset  status  "+checkWithTheseNumbers(numbers));
		return checkWithTheseNumbers(numbers);
	}
	
	public boolean isEqualsTo(List numbers)
	{

		if(this.numbers.size() != numbers.size())
		{
			return false;
		}

		return checkWithTheseNumbers(numbers);
	}
	
	private boolean checkWithTheseNumbers(List numbers)
	{
		int counter=0;
		for(int j=0;j<this.numbers.size();j++)
		{
			Number myNumber = (Number)this.numbers.get(j);
			for(int i =0;i<numbers.size();i++)
			{
				Number herNumber = (Number)numbers.get(i);
				logger.debug("comparing "+myNumber.getValue() +" with "+herNumber.getValue());
				logger.debug("equality = "+myNumber.equals(herNumber));
				if(myNumber.equals(herNumber))
				{
					counter++;
				}
			}
		}
		
		return counter==this.numbers.size();
	}
	
	public boolean belongsTo(MinTerm herMinTerm)
	{
		
		MinTerm myMinTerm = this;
		
		if(myMinTerm.equals(herMinTerm) || myMinTerm.getCount()>=herMinTerm.getCount())
		{
			return false;
		}
		
		return isSubsetOf(herMinTerm.getNumbers());
		
		
	}
	


}
