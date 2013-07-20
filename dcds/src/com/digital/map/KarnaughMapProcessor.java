/*
 * Created on Jul 5, 2007
 *
 * 
 */
package org.digital.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.digital.math.exceptions.InvalidNumberException;
import org.digital.math.exceptions.InvalidUserInputException;




/**
 * @author msahu
 *
 * TODO
 * 
 *
 * 
 */
public class KarnaughMapProcessor implements Observer
{

	TaskStatusContainer taskStatusContainer = null;
	
	KarnaughMapQueue kMapWorkerQueue = null;
	
	KarnaughMapQueue kMapTaskQueue = null;
	
	private KarnaughMapProcessor(boolean isInMultiThreadMode )
	{
		
		
		
		
		initializeTaskQueue();
		
		initializeTaskStatusContainer();
		
		if(isInMultiThreadMode)
		{
			
			initializeWorkerQueue();
		}	
		
	}
	

	/**
	 * 
	 */
	private void initializeTaskStatusContainer() 
	{
		taskStatusContainer = new TaskStatusContainer();
		
		for(int i=0;i<kMapTaskQueue.size();i++)
		{
		
			TaskStatus taskStatus = new TaskStatus();
			taskStatus.setTask(((KarnaughMapCombination)kMapTaskQueue.get(i)).getI());
			taskStatus.addObserver(this);
			taskStatusContainer.put(taskStatus);
		}
		
	}

/*
	public static  KarnaughMapProcessor singleThreadInstance =null;
	
	private static  KarnaughMapProcessor multipleThreadInstance =null;
*/	
	public static  KarnaughMapProcessor getInstance(boolean isInMultiThreadMode )
	{
		
		return isInMultiThreadMode
					?
							new KarnaughMapProcessor(true)
										
						:
							new KarnaughMapProcessor(false)
							
					;				
							
													
	}
	
	public boolean process( )
	{
		
		
		  return kMapWorkerQueue==null
					?
								processWithMainThread()
							:
								processWithWorkers()
					;			
	}
	
	
	
	private void initializeQueues()
	{
		
		initializeTaskQueue();
		initializeWorkerQueue();
	}
	
	/**
	 * @return
	 */
	private void  initializeWorkerQueue() 
	{
		kMapWorkerQueue = KarnaughMapQueue.getQueue(KarnaughMapConstants.KMAPWORKERQUEUE);
		for(int i=0;i<KarnaughMapConstants.THREADPOOLSIZE ;i++)
        {
			kMapWorkerQueue.add(new KarnaughMapWorker());
        }
		

	}

	/**
	 * @return
	 */
	private void  initializeTaskQueue() 
	{
	
		kMapTaskQueue = KarnaughMapQueue.getQueue(KarnaughMapConstants.KMAPCOMBINATIONQUEUE);
		
         for(int i=0;i<=KarnaughMap.getN() ;i++)
         {
         	KarnaughMapCombination task = new KarnaughMapCombination(i);
         	
         	kMapTaskQueue.add(task);
         }
		
	}
	

	private boolean processWithMainThread()
	{
		
		for(int i=0;i<kMapTaskQueue.size();i++)
		{
			
			KarnaughMapCombination  task = (KarnaughMapCombination)kMapTaskQueue.get(i);
			
			try 
			{
			
				 task.generate();
			
			} catch (InvalidUserInputException e) 
			{
				
				e.printStackTrace();
			} catch (InvalidNumberException e) 
			{
				
				e.printStackTrace();
			}
		}
		return true;
	
		
	}
	
	/**
	 * This method is same as invoking process(KarnaughMapQueue , true)
	 * @param kMapCombinationQueue
	 * @return
	 */
	private boolean processWithWorkers()
	{
		
		boolean  status = false;
		
		do
		{
			KarnaughMapWorker worker = getWorker();
			
			
			if(worker!=null)
			{
				KarnaughMapCombination  task = getTask();
			
				if(task !=null)
				{
					TaskStatus taskStatus= taskStatusContainer.get(task.getI());
					worker.setKmc(task);
					worker.setTaskStatus(taskStatus);
					
					
					worker.work();
					
					
				}
			}
			
		
			status = taskStatusContainer.areAllTasksCompleted();
		}while(!status);
		
		return status;
	
	
	}

	private KarnaughMapCombination getTask() 
	{
		
		int  noOfTasks =-1;
		return (noOfTasks = kMapTaskQueue.size())==0
							?
										null
									:
			
										(KarnaughMapCombination)kMapTaskQueue.remove
																			(
																						getRandomNumber(noOfTasks)
																			)
							;			
		
	}

	private KarnaughMapWorker getWorker() 
	{
		int  noOfWorkers =-1;
		
		return (noOfWorkers = kMapWorkerQueue.size())==0
							?
										null
									:
			
										(KarnaughMapWorker)kMapWorkerQueue.remove
																			(
																						getRandomNumber(noOfWorkers)
																			)
							;			
		
		
	}

	private boolean addWorker(KarnaughMapWorker worker)
	{
		return kMapWorkerQueue.add(worker);
	}
	private int getRandomNumber(final int x)
	{
		return (int)(Math.random() * x);
		
		
	}
	public void update(Observable taskStatus, Object worker) 
	{
		System.out.println("Good Job "+worker+" your status is "+((TaskStatus)taskStatus).getStatus());
		taskStatusContainer.replace((TaskStatus)taskStatus); 
		
		addWorker((KarnaughMapWorker)worker);
	}
	
	class TaskStatus extends Observable
	{
		private int task;
		private int status;
		
		public TaskStatus(){}
		public TaskStatus(int task )
		{
			this(task,0);
		}
		
		public TaskStatus(int task, final int status )
		{
			this.task=task;
			this.status=status;
		}
		
		
		public int getStatus() 
		{
			return status;
		}
		public boolean isFinished()
		{ 
			if(status==100)
			{
				//System.out.println(this+" finished");
			}
				
			return status==100;
		}
		public int getTask() 
		{
			return task;
		}
		
		public void setTask(int task) 
		{
			this.task=task;
		}
		
		public int hashCode(){return task;}
		public String toString() {return "{Task-"+task +", status="+status+"}";}
		public boolean equals(Object o )
		{
			
			return o==null ||!(o instanceof TaskStatus)
					?
							false
							:
							this.hashCode() == ((TaskStatus)o).hashCode()	
					;			
		
		}
		public void notifyObservers(KarnaughMapWorker worker , final int status)
		{
			this.setChanged(status);
			super.notifyObservers(worker);
		}

		public void setChanged(final int status )	
		{
			this.setStatus(status);
			super.setChanged();
			
			
		}
	
		private void setStatus(final int status) {this.status= status;}
		
	}
	
	
	private static class TaskStatusContainer 
	{
	  	private  List list = new ArrayList();	  	
		
		private void replace(TaskStatus taskStatus)
		{
			list.remove(taskStatus.getTask());
			list.add(taskStatus.getTask(),taskStatus);
					
		}
		private void put(TaskStatus taskStatus)
		{
			
			list.add(taskStatus.getTask(),taskStatus);
					
		}

		/**
		 * 
		 */
		public boolean remove(TaskStatus taskStatus) 
		{
			return list.remove(taskStatus.getTask())!=null;
			
		}

		private TaskStatus get(int taskHashCode)
		{
			
			
			return (TaskStatus)list.get(taskHashCode);
		
		}
		
		
		private TaskStatus get(final KarnaughMapCombination task ){return  get(task.getI());}
		
		
		public void display()
		{
			System.out.println("********");
			for(int i=0;i<list.size();i++)
			{
				System.out.println(this.get(i));
			
				
			}
			System.out.println("######");
		}
		public boolean areAllTasksCompleted()
		{
			
			for(int i=0;i<list.size();i++)
			{
		
				if(!this.get(i).isFinished())
				{
					
					return false;
				}
				
			}
			
			return true;
		}
		
		
		
	}

	
	



}
