/*
 * Created on Jul 7, 2007
 *
 * 
 */
package com.digital.circuit;

import java.util.ArrayList;
import java.util.List;

import com.digital.math.exceptions.InvalidUserInputException;

/**
 * @author msahu
 *
 * 
 */
public class SequentialCircuit extends DigitalCircuit 
{

	List circuit= new ArrayList();
	

	public SequentialCircuit(final String equation) throws InvalidUserInputException
	{
		super(equation);
	
		CircuitDesigner cd = new CircuitDesigner();
		circuit = cd.designCircuit(equation);
		if (circuit == null)
		{
			throw new InvalidUserInputException("invalid expression");
		}
		
	}
	public List getCircuit(){return this.circuit;}

	public String toString()
	{
		return circuit.toString();
	}

	private static class CircuitDesigner
	{

		
		public  List designCircuit(final String equation) 
		{
		
			return process(equation);
			
			
		}

		private List process(String equation) 
		{
			List result = new ArrayList();
			int gateCount=0;
			int inputCount=0;
			for(int i=0;i<equation.length();i++)
			{
				char ele = equation.charAt(i);
				Element element = Element.getElement(ele,i);
				if(element.isInput())
				{
					inputCount++;
				}else if(element.isGate())
				{
					gateCount++;
				}
				result.add(element);
				
			}
			
			return inputCount==gateCount+1
						?
									result
								:
									null
						;			
			
		}
		
		
		
	}

}
