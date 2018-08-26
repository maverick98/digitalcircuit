/*
 * Created on Jul 6, 2007
 *
 * 
 */
package org.digital.ds;

import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 *
 *
 * 
 */
public class Operator implements Comparable
{
	private char operator;
	
	public Operator(final char operator) throws InvalidUserInputException
	{
		if(isOperator(operator))
		{
			this.operator=operator;
		}
		else
		{
			throw  new InvalidUserInputException("expecting operator found "+operator);
		}
	}
	
	
	public  static boolean isOperator(final char charInfix) 
	{

		if (DataStructureConstants.OPERATORCODE.indexOf(charInfix)!=-1||DataStructureConstants.OPERATOR.indexOf(charInfix)!=-1 )
			return true;
		else
			return false;


	}//end of isOperator method
	public  static boolean isOperator(String str)
	{
	  char cData=str.charAt(0);
	  if(DataStructureConstants.OPERATORCODE.indexOf(cData)!=-1 ||DataStructureConstants.OPERATOR.indexOf(cData)!=-1 )
		  return true;
	  else
		  return false;
	 

	}

	public  int getPrecedence() {
		int retVal = 0;
		switch (operator) {
										/*************************************************************
										 *	  01234  | 56789 |  `~!^& |  _-=;[ |  {]}|? |   >.,<:	 *
										 *	   OR    | AND   |	  NOR |	  NAND |  XOR   |   XNOR     *
										 *************************************************************/
			
			
			case '0' : //OR Gate		(+)
			case '1' :
			case '2' :	
			case '3' :
			case '4' :	
				retVal = 1;
				break;
			case '5' : //AND Gate		(*)
			case '6' :
			case '7' :	
			case '8' :
			case '9' :	
	
				retVal = 2;
				break;
			case '`' : //NOR Gate		(%)
			case '~' :
			case '!' :	
			case '^' :
			case '&' :	
				
				retVal = 3;
				break;
			case '_' : //NAND Gate		($)
			case '-' :
			case ';' :	
			case '=' :
			case '[' :	
					
				retVal = 4;
				break;					 
			case '{' : //XOR Gate		(@)
			case ']' :
			case '}' :	
			case '|' :
			case '?' :	
					
				
				
				retVal = 5;
				break;
			case '>' : //XNOR Gate		(#)
			case '.' :
			case ',' :	
			case '<' :
			case ':' :	
	
				retVal = 6;
				break;

		}
		//end of switch case statement

		return retVal;

	}//end of getPrecedence method

	public int compareWith(Object herOperator) throws InvalidUserInputException
	{
		if(herOperator == null || !(herOperator instanceof Operator))
		{
			throw new InvalidUserInputException("Either operator is null or is not an instance of  com.digital.ds.Operator");
		}
		return this.compareTo(herOperator);
	}

	public int compareTo(Object herOperator) 
	{
	   Operator myOperator = this;						
       return 	(myOperator.getPrecedence() - ((Operator)herOperator).getPrecedence());
	}
	
	public static int compare(final char charInfix , final char charTop) throws InvalidUserInputException 
	{
		Operator operator1 = new Operator(charInfix);
		Operator operator2 = new Operator(charTop);
		return operator1.compareWith(operator2);
	}



}
