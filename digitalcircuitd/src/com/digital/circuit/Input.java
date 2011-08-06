/*
 * Created on Jul 7, 2007
 *
 * 
 */
package org.digital.circuit;


/**
 * @author msahu
 *
 * 
 */
public class Input  extends Element 
{

	
	private boolean value;
	
	public void setValue(final boolean value){this.value=value;}
	public boolean getValue(){return this.value;}
	
	public Input(final char input , final int pos)
	{
		super(input,pos);
		this.value=false;
	}
	
	public Input(Input input)
	{
		this(input.getElement(),input.pos);
	}
	
	public int intValue()
	{
		return this.getValue()?1:0;
	}

	
	


	public static Element getInstance(final char element,final int pos)
	{
		return new Input(element,pos);
	}
	

}
