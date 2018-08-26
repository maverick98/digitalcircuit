/*
 * Created on Jul 4, 2007
 *
 * 
 */
package org.digital.map.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.digital.map.KarnaughMap;
import org.digital.map.KarnaughMapConstants;
import org.digital.map.MinTermContainer;
import org.digital.ui.GUIConstants;
import org.digital.util.PropertyUtil;

/**
 * @author msahu
 *
 * 
 */
public class PersistenceHelper 
{
	
	
	public static String getFile(final int x ) 
	{
		
		return (x <0 ||x > KarnaughMap.getN())
					?
							null
							:
								 PropertyUtil.getProperty(KarnaughMapConstants.MAP_PATH)+GUIConstants.FILE_SEPARATOR+KarnaughMap.getN()+GUIConstants.FILE_SEPARATOR+x+".o"	
								
                    ;
	}
	
	public static Object load(final int x) throws FileNotFoundException
	{
		return (x <0 ||x > KarnaughMap.getN())
					?
							null
							:
							load(getFile(x))	
		            ;
	}
	public static boolean isGenerated()
	{
		for(int i=0;i<=KarnaughMap.getN();i++)
		{
			File file = new File(getFile(i));
			if(!file.exists())
			{
				return false;
			}
			
		}
		return true;
	}
	
	public static Object load(String file ) throws FileNotFoundException
	{
	
		if(file == null)
		{
			return null;
		}
		Object mtc = null;
		
		FileInputStream fi = null;
	    ObjectInputStream si = null;  	    
	    
		
		try 
		{
		    fi = new FileInputStream(file);
		    si = new ObjectInputStream(fi);  	    
		    mtc = si.readObject();
		    
		}catch(FileNotFoundException fnfe)
		{
			throw new FileNotFoundException("File not found ");
			
		}
		catch (Exception e) {
			
		    System.out.println(e);
		    System.exit(1);
		}
		finally
		{
			try 
			{
				if(fi!=null) fi.close();
				if(si!=null)si.close();
			} catch (IOException e1) 
			{
			
				e1.printStackTrace();
			}
			
		}
		return mtc;
	}

	
	public static boolean save(final int x, final Object mtc) 
	{
		
		return (x <0 ||x > KarnaughMap.getN())
					?
							false
							:
							((MinTermContainer)mtc).isEmpty()
								?
										false
										:
										save
										(
													getFile(x)
													,
													mtc
										)	
					;			
		
		
		
	}
	
	/**
	 * @param string
	 * @param mtc
	 */
	public static boolean save(String file, final Object mtc) 
	{
		FileOutputStream fo=null;
		ObjectOutputStream so=null;
		try 
		{
			File fDir = new File(file.substring(0,file.lastIndexOf(GUIConstants.FILE_SEPARATOR)));
			
			if(!fDir.exists())
			{
				fDir.mkdirs();
				
			}
			fo = new FileOutputStream(file);
			so = new ObjectOutputStream(fo);
		    so.writeObject(mtc);
		    so.flush();
		} catch (Exception e) {
		    System.out.println(e);
		    System.exit(1);
		}finally
		{
			try {
				if(fo!=null)fo.close();
				if(so!=null)so.close();
			} catch (IOException e1) 
			{
				
				e1.printStackTrace();
			}
			
		}
	
		return true;
	}
	
	public static String read(String file)
	{
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		try 
		{
			br = new BufferedReader(new FileReader(file));
			String line =null;
			do
			{
				line = br.readLine();
				sb.append(line);
			}while(line!=null);
		} catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		} catch (IOException e) 
		{
			
			e.printStackTrace();
		}
		
		return sb.toString();
	}


}
