package org.digital.circuit;

import org.digital.ds.DataStructureConstants;

public abstract class Element implements Comparable
{
	protected char element;
	protected int pos;
	public char getElement(){return this.element;}
	
	public int getPos(){return pos;}
	protected Element(final char element , final int pos)
	{
		this.element=element;
		this.pos=pos;
		
	}
	public boolean isGate()
	{
		return DataStructureConstants.OPERATOR.indexOf(element)!=-1
					?
								true
							:
								false
					;			
	}
	
	public boolean isClubber()
	{
		return element == '(' ||element == ')'
					?
								true
							:
								false
					;			
	}
	public boolean isInput()
	{
		return isGate()
				?
							false
						:
							isClubber()
								?
											false
										:
											true	
				;							
	}
	
	public static boolean isGate(char element)
	{
		return DataStructureConstants.OPERATOR.indexOf(element)!=-1
					?
								true
							:
								false
					;			
	}
	public static  boolean isClubber(char element)
	{
		return element == '(' ||element == ')'
					?
								true
							:
								false
					;			
	}
	
	public static Element getElement(final char element,final int pos)
	{
		return  isGate(element)
				?
							Gate.getInstance(element , pos)
						:
							element == '(' 
								?
											Clubber.getInstance(element , pos )
										:
											element == ')'
												?
															Clubber.getInstance(element , pos )
														:
															Input.getInstance(element,pos)
															
															
				;						 
	}
	
	
	public final int hashCode(){return pos;}
	
	public String toString(){return ""+element + pos;}
	
	public final boolean equals(Object herElement)
	{
		Element myElement = this;
		return herElement==null || !(herElement instanceof Gate)
				?
							false
						:
							myElement.hashCode()== ((Element)herElement).hashCode()
				;			
	}
	
	public int compareTo(Object herElement) 
	{
		Element myElement = this;
		return herElement==null || !(herElement instanceof Element)
				?
							-1
						:
							myElement.hashCode()- ((Element)herElement).hashCode()
				;
	}
	
	public boolean isLeft(Element herElement){return this.hashCode()<herElement.hashCode();}
	public boolean isRight(Element herElement){return !this.isLeft(herElement);}
	
	
	
	
}
