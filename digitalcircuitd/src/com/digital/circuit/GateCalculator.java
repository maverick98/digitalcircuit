package org.digital.circuit;

public class GateCalculator 
{
	public static boolean and(Input x , Input y)
	{
		return x.getValue() && y.getValue();
					
	}

	public static boolean or(Input x , Input y)
	{
		return x.getValue() || y.getValue();
					
	}
	public static boolean nand(Input x , Input y)
	{
		return !and(x,y);
					
	}

	public static boolean nor(Input x , Input y)
	{
		return !or(x,y);
					
	}
	
	public static boolean xor(Input x , Input y)
	{
		
		return !xnor(x,y);
					
	}

	public static boolean xnor(Input x , Input y)
	{
		return x.getValue()==  y.getValue();
					
	}
	

	
}
