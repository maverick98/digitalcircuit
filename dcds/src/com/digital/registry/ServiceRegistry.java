/*
 * Created on Jul 22, 2007
 *
 * 
 */
package org.digital.registry;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;
import org.digital.registry.exception.RegistryException;


/**
 * @author msahu
 * @singleTon
 * 
 */
public class ServiceRegistry 
{
	private static DigLogger logger = DigLogManager.getLogger(ServiceRegistry.class);
	private static Map registry= new HashMap();
	private static ServiceRegistry singleTonInstance;
	private ServiceRegistry()
	{
		super();
	}

	public static ServiceRegistry getInstance()
	{
		return singleTonInstance==null 
						? 	
									new ServiceRegistry()
								:
									singleTonInstance
									
						;
	}
	public static List lookupAll(Class clazz) throws RegistryException
	{
		if(!registry.containsKey(clazz))
		{
			logger.debug(clazz +" does not register itself with the serviceregistry");
			throw new RegistryException(clazz +" does not register ");
		}
		logger.debug("getting the the value for "+clazz);
		return (List)registry.get(clazz);
		
		
	}
	public static Object lookup(Class clazz) throws RegistryException
	{
		List all = null;
		return (all = lookupAll(clazz))!=null
									?
												all.get(all.size()-1)
											:
												null
									;
	}
	public static Object lookup(Class clazz , int index) throws RegistryException
	{
		
		List all = lookupAll(clazz);
		if(all == null) return null;
		
		if(index <0 || index >=all.size()){throw new RegistryException(" index  out of  range");}
		
		return all.get(index);
		
	}
	public static Object lookup(String classStr) throws RegistryException
	{
		Class clazz=null;
		try 
		{
			clazz = Class.forName(classStr);
			return lookup(clazz);
		} catch (ClassNotFoundException e) 
		{

			e.printStackTrace();
		}
		if(!registry.containsKey(clazz))
		{
			throw new RegistryException(clazz +" does not register ");
		}
		
		return registry.get(clazz);

	}
	
	public static void register(Class  clazz)
	{
		
		registry.put(clazz,null);
		
	}
	
	public static boolean set(Object obj) throws RegistryException
	{
		Class clazz = obj.getClass();
		
		if(!registry.containsKey(clazz))
		{
			throw new RegistryException(clazz +" does not register ");
		}
		
		List list = null;
		(list = ((list=(List)registry.get(clazz))==null
									?
												new LinkedList()
											:	
												list
				)
		).add(obj);
		
		
		return registry.put(clazz,list)!=null;
	}
	
	public static void register(String classStr)
	{
		logger.debug("Registering the class "+classStr +" with the serviceRegistry");
		try {
			Class clazz = Class.forName(classStr);
			register(clazz);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	

}
