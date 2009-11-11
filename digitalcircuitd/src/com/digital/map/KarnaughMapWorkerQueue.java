/*
 * Created on Jul 5, 2007
 *
 *
 */
package com.digital.map;

import java.util.LinkedList;



/**
 * @author msahu
 *
 * 
 */
public class KarnaughMapWorkerQueue extends KarnaughMapQueue {

	private KarnaughMapWorkerQueue()
	{
		kMapQueue = new LinkedList();
	}
	private static class StaticInstanceHelper 
	{
		
		public static KarnaughMapQueue  theInstance = new KarnaughMapWorkerQueue();
	}

	public static  KarnaughMapQueue getInstance()
	{
		return StaticInstanceHelper.theInstance;
	}
	
	public boolean add(Object kMapWorker) 
	{
		return kMapWorker==null || !(kMapWorker instanceof KarnaughMapWorker)
				?
						false
						:
						kMapQueue.add((KarnaughMapWorker)kMapWorker)	
						
		        ;
	}

	public Object remove() 
	{
		return null;
		
	}
	public Object remove(final int i)
	{
		return kMapQueue.remove(i);
	}
	
}
