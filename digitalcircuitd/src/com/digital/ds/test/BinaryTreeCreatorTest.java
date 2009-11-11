/*
 * Created on Jul 7, 2007

 */
package com.digital.ds.test;

import com.digital.ds.util.BinaryTreeCreator;
import com.digital.exceptions.DigException;
import com.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 */
public class BinaryTreeCreatorTest 
{
	public static void main(String args[])
	{
		BinaryTreeCreatorTest btct= new BinaryTreeCreatorTest();
		btct.test(args);
		
	}
	
	public void test(String [] args)
	{
		
		   StringBuffer infix= new StringBuffer(args[0]);
		   BinaryTreeCreator  btc;
		   try 
		   {
		   		btc = new BinaryTreeCreator(infix.toString());
		   		btc.setXY(30,200);
		   		
		   	//	btc.assignInputValues(Integer.parseInt(args[1]));
		   		
		   		//System.out.println("Answer is  "+btc.calculate() + " op is "+btc.getOutput() );
		   		btc.showTree(10);
		   		//btc.display(g);
		   } catch (InvalidUserInputException e) 
		   {
			
		   		e.printStackTrace();
		   } catch (DigException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
