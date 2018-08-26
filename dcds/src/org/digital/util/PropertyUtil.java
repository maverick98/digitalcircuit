/*
 * Created on Jul 29, 2007
 *
 * 
 */
package org.digital.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;

/**
 * @author msahu
 *
 * 
 */
public class PropertyUtil 
{
	private static DigLogger logger = DigLogManager.getLogger(PropertyUtil.class);
	private static  Properties settings = new Properties();
	private static String CONF_DIR=System.getProperty("CONF");
	static 
	{
		
		try 
		{
			settings.load(new FileInputStream(new File(CONF_DIR+"/settings.conf")));
		} catch (FileNotFoundException e) 
		{
			
			System.out.println("Could not find settings.conf ... continuing anyway!!!");
			
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	public static String getProperty(String key)
	{
		return settings.getProperty(key);
	}
	
	public static String getProperty(String key , String defaultValue)
	{
		String rtn ;
		return (rtn =getProperty(key))==null
							?
									defaultValue
								:
									rtn
							;		
	}
	
	public static boolean setProperty(String key , String value)
	{
		if(getProperty(key)==null)
		{
			return false;
		}
		settings.setProperty(key,value);
		
		try 
		{
			settings.store(new FileOutputStream(new File(CONF_DIR+"/settings.conf")),"");
		}
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return false;
		}
						
		return true;
	}
	
	

}
