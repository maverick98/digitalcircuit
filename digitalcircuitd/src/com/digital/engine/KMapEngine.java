/*
 * Created on Jul 22, 2007
 *
 * 
 */
package com.digital.engine;

import com.digital.ds.util.BinaryTreeCreator;
import com.digital.exceptions.DigException;
import com.digital.map.KarnaughMapMinimizer;
import com.digital.registry.ServiceRegistry;
import com.digital.registry.exception.RegistryException;
import com.digital.ui.GUIConstants;
import com.digital.ui.InternetView;
import com.digital.ui.handler.MinimizeActionHandler;


/**
 * @author msahu
 *
 * 
 */
public class KMapEngine 
{
	private static KMapEngine singleTonInstance;
	private KMapEngine()
	{
		super();
	}

	public static KMapEngine getInstance()
	{
		return singleTonInstance==null 
						? 	
									new KMapEngine()
								:
									singleTonInstance
									
						;
	}
	public void minimize(String exp,MinimizeActionHandler.Task observable)
	{
		
		String op = minimize(exp);
		observable.done();
		observable.notifyObservers(op);
	}
	
	public  String minimize(String exp)
	{
		String op=null;
		try 
		{
			System.out.println("index  "+InternetView.getIndex());
			BinaryTreeCreator btc = (BinaryTreeCreator)ServiceRegistry.lookup(BinaryTreeCreator.class,InternetView.getIndex());
		
			System.out.println("btc "+btc.getInputNames());
			if(btc.getOutputExpression().equals(GUIConstants.MINIMIZATION_DEFAULT_VALUE))
			{
				
				KarnaughMapMinimizer kmg = KarnaughMapMinimizer.getInstance(btc.getInputNames().length());
		
				try 
				{
					System.out.println("exp="+exp);
					op = kmg.minimize(exp);
				
					btc.setOutputExpression(op);
				} catch (DigException e1) {
				
					e1.printStackTrace();
				}
			}else
			{
				
				op=btc.getOutputExpression();
				
			}
			
		} catch (RegistryException e) 
		{
			
			e.printStackTrace();
		}
		return op;
	}
}
