/*
 * Created on Jul 22, 2007
 *
 * 
 */
package org.digital.ui.components;

import java.awt.Button;

/**
 * @author msahu
 *
 * 
 */
public class InputButton extends Button implements Comparable
{

	private char operand;
	private int value;
	public InputButton(final char operand)
	{
		this(operand,0);
	
	}
	public InputButton(final char operand , final int value)
	{
		super(processOperand(operand)+"="+value);
		this.operand=processOperand(operand);
		this.value=value;
		
	}
	/**
	 * @param operand
	 * @return
	 */
	private static char processOperand(char operand) 
	{
	
		return operand <91 ?(char)(operand+32):operand;
	}
	public String toString(){return getLabel();}
	
	public void setLabel(final int value)
	{
		super.setLabel(operand+"="+value);
		this.value=value;
	}
	public int  getValue()
	{
		return value;
	}
	public int flip()
	{
		
		value = value==1?0:1;
	
		setLabel(value);
		
		return value;
	}

	public int hashCode() {return operand;}
	public boolean equals(Object herObject)
	{
		InputButton myButt = this;
		InputButton herButt=null;
	
		return herButt!=null || !(herObject instanceof InputButton)
							?
										false
									:
										myButt.getOperand()==((InputButton)herObject).getOperand()
							;			
		
	}
	public int compareTo(Object herButt) 
	{

		InputButton myButt = this;
		
		return herButt!=null || !(herButt instanceof InputButton)
					?
								-2
							:
								(myButt.getOperand()-((InputButton)herButt).getOperand())
					;			
		
		
	}
	
	/**
	 * @return Returns the operand.
	 */
	public char getOperand() {
		return operand;
	}
	/**
	 * @param operand The operand to set.
	 */
	public void setOperand(char operand) {
		this.operand = operand;
	}


}
