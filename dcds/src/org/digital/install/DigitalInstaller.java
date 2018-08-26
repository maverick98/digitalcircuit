/*
 * Created on Aug 5, 2007
 *
 * 
 */
package org.digital.install;

import org.digital.exceptions.DigException;
import org.digital.map.KarnaughMapMinimizer;

/**
 * @author msahu
 *
 * 
 */
public class DigitalInstaller 
{
	public boolean install(int n)
	{
		n=(n<=5 || n>10)?10:n;
		
		System.out.println("Generating upto "+n + "value KarnaughMap...");
		for(int i=1;i<=n;i++)
		{
		
			KarnaughMapMinimizer mg = KarnaughMapMinimizer.getInstance(i);
			
			
			
			try 
			{
				mg.generate();
			} catch (DigException e) 
			{
				e.printStackTrace();
				return false;
				
			}
			
		}
		
		
		return true;
	}
	public static void main(String args[])
	{
		DigitalInstaller installer = new DigitalInstaller();
		
		installer.install(Integer.parseInt(args[0]));
		
	}

}
