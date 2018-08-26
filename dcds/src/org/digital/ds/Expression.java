/*
 * Created on Jul 6, 2007
 *
 */
package org.digital.ds;

import java.util.ArrayList;
import java.util.List;

import org.digital.circuit.Clubber;
import org.digital.circuit.Element;
import org.digital.circuit.SequentialCircuit;
import org.digital.exceptions.IllegalStateException;
import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 *
 * 
 */
public class Expression 
{


	
	private List  expression;
	
	private String  type;
	public Expression(final List circuit ) 
	{
		this(circuit , DataStructureConstants.INFIX);
		
	}
	public Expression(final SequentialCircuit sc)
	{
		this(sc.getCircuit(),DataStructureConstants.INFIX);
	}
	public Expression(final List circuit ,final String type)  
	{
		this.expression= circuit;
		this.type=type;
	}
	public String getType(){return type;}
	
	public List toPrefix() throws IllegalStateException, InvalidUserInputException
	{
		if(!type.equals(DataStructureConstants.INFIX))
		{
			throw new IllegalStateException(" Expecting infix  found "+type);
		}
		List  infix = new ArrayList(expression);
		ListHelper helper = new ListHelper();
		infix =helper.reverse(infix);
		

		Expression tmp = new Expression(infix);


		tmp.toPostfix();
		
		this.expression = helper.reverse(tmp.expression);
		this.type=DataStructureConstants.PREFIX;
		return new ArrayList(expression);
	}
	
	public List toPostfix() throws IllegalStateException
	{
		if(!type.equals(DataStructureConstants.INFIX))
		{
			throw new IllegalStateException(" Expecting infix  found "+type);
		}
		
		
		
		List infix = new ArrayList(expression);
	
		final int infix_len = infix.size();
		List postfix = new ArrayList();
		int i = 0;
		final Stack stack = new Stack();
		
		while (i < infix_len) {
			
			Element element = (Element)infix.get(i);
			
//			System.out.println("Element is  " +element);

			if ( element.isClubber()  && ((Clubber)element).isStarting()) //push it into the stack
			{
//				System.out.println("found starting clubber");
				stack.push(element);
				
			} else if (element.isGate()) 
			{

//				System.out.println("found a  gate ");
				final Object objTop = stack.top();
				if (objTop != null) {
					final Element top = ((Element) objTop);
					

					if (top.isGate()) 
					{
//						System.out.println("stack top  gate "+top);
						if (element.compareTo(top) >0) {

//						System.out.println(element + " is greater than "+top );	
							stack.push(element);
							

						} else 
						{
//							System.out.println(element + " is smaller than "+top );
							stack.pop();
							postfix.add(top);
							stack.push(element);
						}

					} else {
//						System.out.println("found non gate stack top   "+top);
//						System.out.println("pushing  the element" +element);
						stack.push(element);
						
					}

				} else {
//					System.out.println("pushing  xxxx" +element);
					stack.push(element);
					
				}

			} else if (element.isClubber()  && !((Clubber)element).isStarting()) 
			{
//				System.out.println("end close bracket");
				Element top = ((Element) stack.top());
//				System.out.println("stack top   is "+top);
				while (!(top.isClubber()  && ((Clubber)top).isStarting())) 
				{
					stack.pop();
					postfix.add(top);
					top = ((Element) stack.top());
					
				}
				stack.pop();

			} else {
//				System.out.println("adding "+element +" to postfix");
				postfix.add(element);
			}

			i++;
		}

		Element top = (Element)stack.top();

		while (top != null) {
			stack.pop();
			
			postfix.add(top);
			top = (Element)stack.top();

		}//end of while loop

		this.expression = postfix;
		this.type=DataStructureConstants.POSTFIX;
		return this.expression;
	}
	
	private static class ListHelper
	{
		List reverse(final List list)
		{
			List result = new ArrayList();
			
			for(int i=list.size()-1,j=0;i>=0;i--,j++)
			{
				Element element = (Element)list.get(i);
				
				if(element.isClubber())
				{
					Clubber clubber = (Clubber)element;
					clubber.flip();
					result.add(j,clubber);
				}else
				{
					result.add(j,list.get(i));
				}
			}
				
			
			return result;
		}
	}

	
}
