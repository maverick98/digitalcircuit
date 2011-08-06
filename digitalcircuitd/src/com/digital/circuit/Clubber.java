package org.digital.circuit;

public class Clubber extends Element
{

	
	public Clubber(final char bracket , final int pos)
	{
		super(bracket,pos);
		
	}
	
	public boolean isStarting(){return element=='(';}

	public void flip()
	{
		if (element == '(')
		{
			element=')';
		}else  if(element == ')')
		{
			element='(';
		}
	
	}
	


	public static Element getInstance(final char element,final int pos )
	{
		return new Clubber(element,pos);
	}
	

}
