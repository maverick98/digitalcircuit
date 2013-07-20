/*
 * Created on Jun 17, 2007
 *
 * 
 */
package org.digital.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.digital.math.exceptions.InvalidNumberException;
import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 *
 * 
 */
public class Number implements Comparable , Externalizable
{

	
	private int [] bits = null;
	private long n = -1;
	public Number(long n) throws InvalidNumberException 
	{
		this.n=n;
		this.bits=getBinary(n, getNoOfBits(n));
	}
	
	public Number(int [] bits) throws InvalidNumberException 
	{
		
		if(checkBits(bits))
		{
			this.bits=bits;	
			this.n=getValue();
		}else{
			throw new InvalidNumberException("not binary number");
		}
		
		
	}
	/**
	 * added as an alternate way for Object.clone()
	 * 
	 */
	private Number(Number number) throws InvalidNumberException
	{
	
		this(number.getValue());
	}
	
	
	/**
	 * 
	 */
	public Number() 
	{
		
	}
	/**
	 * @param bits
	 */
	public static boolean checkBits(int[] bits) {
		for(int i=0;i<bits.length;i++)
		{
			if(bits[i]!=1 && bits[i]!=0)
			{
				return false;
			}
		}
		return true;
		
	}

	
	public static int [] getBitsOf(final long n) throws InvalidNumberException
	{
		return getBinary(n,getNoOfBits(n));
		
	}
	
	public static int [] getBitsOf(final long n ,final int base) throws InvalidNumberException, InvalidUserInputException
	{
		int bits [] = getBinary(n,getNoOfBits(n));
		
		return  padZeroes(base, bits);
		
	}
	
	/**
	 * @param base
	 * @param bits
	 * @return
	 * @throws InvalidUserInputException
	 */
	private static int[] padZeroes(final int base, int[] bits) throws InvalidUserInputException {
		if(bits.length > base)
		{
			throw new InvalidUserInputException("Base size "+base+" less than the number of bits in the number");
		}
		int [] result = new int[base];
		
		System.arraycopy(bits,0,result,base-bits.length,bits.length);
		return result;
	}

	public int [] getBits()
	{
		return (int []) bits.clone();		
		
	}
	
	public int [] getBits(final int base) throws InvalidUserInputException
	{
		return padZeroes(base,bits);
	}
	
	/**
	 * @param n
	 * @param length
	 */
	public static int []getBinary(long n, int length) {
		int bits[] = new int[length];
		int i=length-1;
		while(n!=0)
		{
			if(n%2==1)
			{
				bits[i]=1;
			}
			n/=2;
			i--;
		}
		return bits;
	}
	public static int getNoOfBits(long n) throws InvalidNumberException {
		if(n<0)
		{
			throw new InvalidNumberException(n+" is  a negative number");
		}
		int i=1;
			
		while(Math.pow(2,i)<n || Math.pow(2,i)==n)
		{
			if(Math.pow(2,i)==n)
			{
				i++;
				break;
			}
			i++;
		};
	
		return i;
	}
	public long getValue()
	{
		if(n != -1)
		{
			return n;
		}
		long value =0;
		int l=bits.length;
		for(int i=bits.length-1;i>=0;i--)
		{
			if(bits[i]==1)
			{
				value += Math.pow(2,l-i-1);
			}
		}
		return value;
		
	}
	public int getOnesCount()
	{
		int count=0;
		for(int i=0;i<bits.length;i++)
		{
			count+=(bits[i]==1)?1:0;
		}
		return count;

	}
	public int getZerosCount(){return bits.length-getOnesCount();}
	
	public String toString()
	{
		StringBuffer sb= new StringBuffer();
		sb.append("{ ");
		
		for(int i=0;i<bits.length;i++)
		{
			sb.append(bits[i]);
		}
		
		sb.append(" }");
		return sb.toString();
	}
	
	public String showBits(final int base) 
	{
		StringBuffer sb = new StringBuffer();
		sb.append("{ ");
		int bits []=null;
		try {
			bits = this.getBits(base);
		} catch (InvalidUserInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<bits.length;i++)
		{
			sb.append(bits[i]);
		}
		
		sb.append(" }");
		
		
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) 
	{
		Number that = (Number)o;
		
		return this.getValue()== that.getValue()
					?
								0
							:
								this.getValue()<that.getValue()
									?
											-1
											:
											+1	
					;	

		
	}
	
	public boolean lessThan(Number herNumber)
	{
		Number myNumber = this;
		
		int ourCompatibility = myNumber.compareTo(herNumber);
		
		return ourCompatibility == 0
					?
							false
							:
								ourCompatibility >0
									?
											false
											:
											true
					;						
		
	}
	public boolean equals(Object o)
	{
		if(o==null || !(o instanceof Number))
		{
			return false;
		}
		Number myNumber = this;
		Number herNumber = (Number)o;
		
		return myNumber.compareTo(herNumber) == 0
					?
							true
							:
							false
					;		
		
	}
	
	public boolean greaterThan(Number herNumber)
	{
		
		Number myNumber = this;
		
		return myNumber.equals(herNumber)
					?
							false
							:
								myNumber.lessThan(herNumber)
									?
											false
											:
											true	
					;			
		
		
	}

	
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
	
		long n = in.readLong();
		this.n=n;
		try {
			this.bits=getBinary(n, getNoOfBits(n));
		} catch (InvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}

	
	public void writeExternal(ObjectOutput out) throws IOException 
	{
		out.writeLong(n);
		
	}
	/**
	 * added as an alternate way for Object.clone()
	 * 
	 */
	public static Number clone(Number number) 
	{
		Number numberClone =null;
		
		try {
			numberClone = new Number(number);
		} catch (InvalidNumberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return numberClone;
	}
	
	public boolean equalsTo(final long n)
	{
	
		return this.getValue()==n
						?
								true
								:
								false
						;		
		
	}
	


}
