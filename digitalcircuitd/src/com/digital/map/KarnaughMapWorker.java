/*
 * Created on Jul 5, 2007
 *
 * 
 */
package org.digital.map;

import org.digital.math.exceptions.InvalidNumberException;
import org.digital.math.exceptions.InvalidUserInputException;

/**
 * @author  msahu
 *
 * 
 */
public class KarnaughMapWorker implements Runnable  
{
	private static final String WORKER_PREFIX="Worker --> ";
	private String name=WORKER_PREFIX;
	
	private KarnaughMapCombination kmc=null;
	
	private KarnaughMapProcessor.TaskStatus taskStatus =null;
	
	private boolean workStatus = false;
	
	
	public String toString(){return  WORKER_PREFIX+kmc;}
	
	public int hashCode(){return this.toString().hashCode();}
	
	public boolean equals(Object herWorker)
	{
		int ourCompatibility=-1;
		return herWorker == null || !(herWorker instanceof KarnaughMapWorker)
					?
							false
							:
							(ourCompatibility=(this.hashCode()-((KarnaughMapWorker)herWorker).hashCode()))==0
									
					;			
	}
	public KarnaughMapWorker(KarnaughMapCombination kmc , KarnaughMapProcessor.TaskStatus taskStatus)
	{
		this.kmc=kmc;
		this.taskStatus=taskStatus;
		this.name=WORKER_PREFIX+kmc;
		
	}
	public KarnaughMapWorker()
	{
		
	}
	
	
	/**
	 * @param kmc The kmc to set.
	 */
	public void setKmc(KarnaughMapCombination kmc) 
	{
		this.kmc = kmc;
		this.name=WORKER_PREFIX+kmc;
	}
	/**
	 * @param taskStatus The workerStatus to set.
	 */
	public void setTaskStatus(KarnaughMapProcessor.TaskStatus taskStatus) 
	{
		this.taskStatus = taskStatus;
	}
	public boolean work()
	{
		new Thread
		(
			this
			,
			this.toString()
			
		).start();
		
		return workStatus;
	}
	
	public boolean isFinished()
	{
		return workStatus;
	}
	
	
	public void run() 
	{
		try 
		{
			
			System.out.println(Thread.currentThread().getName() + " working on  "+kmc);
		
			workStatus = kmc.generate();
			
			if(workStatus)
			{
				
				taskStatus.notifyObservers(this,100);
				
				
			}
			
		}catch(OutOfMemoryError oum)
		{
			System.out.println(this+" got OutOfMemory "+"Please try increasing memory");
		
		} catch (InvalidUserInputException e) 
		{
			
			e.printStackTrace();
		} catch (InvalidNumberException e) 
		{
			
			e.printStackTrace();
		}
		
		
	}

	public String  getName() {return this.name;}
	

}
