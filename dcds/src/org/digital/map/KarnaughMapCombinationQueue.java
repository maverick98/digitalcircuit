/*
 * Created on Jul 5, 2007
 *
 * 
 */
package org.digital.map;

import java.util.ArrayList;

/**
 * @author msahu
 *
 * 
 */
public class KarnaughMapCombinationQueue extends KarnaughMapQueue 
{
	
	private KarnaughMapCombinationQueue()
	{
		kMapQueue = new ArrayList();
	}
	private static class StaticInstanceHelper 
	{
		
		public static KarnaughMapQueue  theInstance = new KarnaughMapCombinationQueue();
	}

	public static  KarnaughMapQueue getInstance()
	{
		return StaticInstanceHelper.theInstance;
	}

	public boolean add(Object kMapCombination) 
	{
		return kMapCombination==null || !(kMapCombination instanceof KarnaughMapCombination)
				?
						false
						:
						kMapQueue.add((KarnaughMapCombination)kMapCombination)	
						
		        ;
	}

	public Object remove() 
	{
		return size()==0
		?
				null
				:
				kMapQueue.remove(size()-1)	
				
        ;
		
	}
	public Object remove(final int i)
	{
		return kMapQueue.remove(i);
	}
	
	
	
		
}
