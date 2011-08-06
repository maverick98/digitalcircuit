/*
 * Created on Jul 4, 2007
 *
 *
 */
package org.digital.map;




/**
 * @author msahu
 *
 * 
 */
public class KarnaughMap 
{
	private  static int N=-1;

	private static KarnaughMap instance=null;  
	private KarnaughMap(final int n)
	{
		if(n <= 0)
		{
			System.out.println( "Inside pvt CTOR N is negative number");
			
			System.exit(1);
		}
		N=n;
	}
	
	public static  int getN()  
	{
		if(N <= 0)
		{
			System.out.println( "Inside getN N is negative number");
			
			System.exit(1);
		}
		
		return N;
		
	}
		
	public static void initialize(final int n)
	{
		 if(instance ==null ||N!=n) 
		 {
							instance = new KarnaughMap(n);
		 }		
	}
		


}
