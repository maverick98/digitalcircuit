/*
 * Created on Jul 22, 2007
 *
 * 
 */
package com.digital.ui.handler;

import java.awt.Button;
import java.util.Observable;
import java.util.Observer;

import com.digital.engine.KMapEngine;
import com.digital.ui.GUIConstants;

/**
 * @author msahu
 *
 * 
 */
public class MinimizeActionHandler extends ActionHandler implements Observer
{
	private static MinimizeActionHandler singleTonInstance;
	private MinimizeActionHandler()
	{
		super();
	}
	private Button butt=null;

	public static ActionHandler getInstance()
	{
		return singleTonInstance==null 
						? 	
									new MinimizeActionHandler()
								:
									singleTonInstance
									
						;
	}
	public void processEvent(Button butt)
	{
		this.butt=butt;
		butt.setLabel(GUIConstants.MINIMIZING);

		KMapEngine engine = KMapEngine.getInstance();
		Task task = new Task();
		task.addObserver(this);
		System.out.println("usr = "+iv.getUser());
		engine.minimize(iv.getUser(),task);
	
	}

	
	public void update(Observable o, Object arg) 
	{
		String op = (String)arg;
		iv.setOutput(op);
		butt.setLabel(GUIConstants.MINIMIZE);
		
	}
	
	public class Task extends Observable
	{
		public void done() 
		{
			
			super.setChanged();
		}
		
	}

	

}
