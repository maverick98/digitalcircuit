/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.math.test;

import com.digital.math.Number;
import com.digital.math.exceptions.InvalidNumberException;
import com.digital.math.exceptions.InvalidUserInputException;
import com.digital.util.Displayer;

/**
 * @author msahu
 *
 * 
 */
public class NumberTester {

	public static void main(String[] args) 
	{
		NumberTester nt = new NumberTester();
		nt.testNumber();
	
		
	}
	
	public void testNumber() {
		
		try{
		long startTime= System.currentTimeMillis();
		//for(int i=0;i < x;i++){
			Number n = new Number(5);
			//Displayer.sop(""+n);
			//Displayer.sop(""+n.getValue());
			
			int res [] = n.getBits(7);
			for(int i=0;i<res.length;i++)
			{
				System.out.print(""+res[i]);
			}
			System.out.println();
		/*	
			Number n1 = new Number(new int[]{1,0,1,1});
			Displayer.sop(""+n1);
			Displayer.sop(""+n1.getValue());
			
			Displayer.sop(""+n.getOnesCount());
			Displayer.sop(""+n1.getOnesCount());
		//}
			
		long t = System.currentTimeMillis()-startTime;
		Displayer.sop("time taken for creating no object "+t+" ms");
	/*	
		
		Displayer.sop(n.getValue()+"");
		Displayer.sop("no of bits "+Number.getNoOfBits(x));
		Displayer.sop("1s "+n.getOnesCount());
		Displayer.sop("0s "+n.getZerosCount());
		Displayer.sop("Bin representation="+n);
		/*
		Number n1 = new Number(x);
		
		Displayer.sop(n.hashCode()+"");
		Displayer.sop(n1.hashCode()+"");
		if(n.equals(n1))
		{
			Displayer.sop("equal");
		}else{
			Displayer.sop("not equal");
		}
		*/
			
	}catch(InvalidNumberException ine)
	{
		Displayer.sop(ine);
	} catch (InvalidUserInputException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
