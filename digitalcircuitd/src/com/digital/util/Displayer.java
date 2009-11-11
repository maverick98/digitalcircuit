/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.util;

import java.util.List;

/**
 * @author msahu
 *
 * 
 */
public class Displayer 
{
	
	
	public static void  sop(Object mesg)
	{
		System.out.println(mesg);
	}
	public static void sop(int [] arr)
	{
		
		for(int i=0;  i <arr.length ;i++)
		{
			System.out.print(arr[i]);
		}
		System.out.println();
		
		
	}
	public static void sop(List list)
	{
		for(int i=0; i <list.size();i++)
		{
			sop(list.get(i));
		}
		
	}
	

}
