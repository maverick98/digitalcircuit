/*
 * Created on Jul 5, 2007
 *
 *
 */
package com.digital.map;

import java.util.List;

/**
 * @author msahu
 *
 * 
 */
public abstract class KarnaughMapQueue 
{
	protected  List  kMapQueue = null;
	
	public int size() 
	{
		return kMapQueue.size();
	}
	public boolean isEmpty()
	{
		return size()==0;
	}

	public  boolean add(Object  o) {return kMapQueue.add(o);} 
	public  Object get(final int idx) {return kMapQueue.get(idx);}
	
	
	public abstract Object remove();
	
	public Object remove(int i){return null;}
	

	
	public  static KarnaughMapQueue getQueue(String queue)
	{
		return KarnaughMapConstants.KMAPCOMBINATIONQUEUE.equals(queue)
					?
							KarnaughMapCombinationQueue.getInstance()
							:
							KarnaughMapConstants.KMAPWORKERQUEUE.equals(queue)
								?
										KarnaughMapWorkerQueue.getInstance()
										:
										null	
					;			
	
	}
	public void show()
	{
		for(int i=0;i<size();i++)
		{
		
			System.out.println("object is "+kMapQueue.get(i) + " its type is "+kMapQueue.get(i).getClass());
		}
		
		
	}

}