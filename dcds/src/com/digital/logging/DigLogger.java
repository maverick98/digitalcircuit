/*
 * Created on Jul 28, 2007
 *
 * 
 */
package org.digital.logging;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.digital.util.PropertyUtil;


/**
 * @author msahu
 * 
 * 
 */
public class DigLogger 
{
	
	private DigLogger(Class clazz)
	{
		Matcher matcher =null;
		if((matcher=pattern.matcher(clazz.toString())).matches())
		{
			setClazz(matcher.group(2).substring(1));
			setProp(matcher.group(1));
			
		}
	}

	protected static DigLogger getInstance(Class clazz)
	{
		DigLogger theInstance = new DigLogger(clazz);
		return theInstance;							
	}
	
	

	private static Writer  logger = null;
	private static Pattern pattern = Pattern.compile("\\s*class\\s*(.*(\\..*))");

	private String clazz;
	
	private String prop;
	

	private String getClazz() 
	{
		return clazz;
	}
	
	private void setClazz(String clazz) 
	{
		
		this.clazz = clazz;
	}
	
	private String getProp() 
	{
		return prop;
	}
	
	private void setProp(String prop) 
	{
		
		this.prop = prop;
	}

	public void debug(Object message)
	{
		String value;
		if((value=PropertyUtil.getProperty(prop))==null?false:value.equalsIgnoreCase("debug"))
		{
			write(message);
		}
		
	}

	/**
	 * @param message
	 */
	private void write(Object message) {
		BufferedWriter bfr =null;
		try 
		{
			logger = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("dig.log",true))); 
			logger.write(clazz+" :: "+message.toString()+"\n");
		
		} catch (FileNotFoundException e) 
		{
			System.out.println("Cound not find dig.log . will write to console");
			System.out.println(clazz+" :: "+message.toString());
			e.printStackTrace();
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}finally
		{
			try 
			{
				logger.close();
			} catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}
		}
	}

	/**
	 * @param e
	 */
	public void error(Throwable e) 
	{
		write(e);
	}

	
	
	
	
}
