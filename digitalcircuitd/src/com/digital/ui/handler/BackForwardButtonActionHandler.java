package org.digital.ui.handler;

import java.awt.Button;
import java.util.List;

import org.digital.ds.util.BinaryTreeCreator;
import org.digital.registry.ServiceRegistry;
import org.digital.registry.exception.RegistryException;
import org.digital.ui.GUIConstants;
import org.digital.ui.InternetView;


/*
 * Created on Jul 23, 2007
 *
 * 
 */

/**
 * @author msahu
 * @singleTon
 * 
 */
public class BackForwardButtonActionHandler extends ActionHandler 
{
	
	private static BackForwardButtonActionHandler singleTonInstance;
	private BackForwardButtonActionHandler()
	{
		super();
	}
	private Button butt=null;

	public static ActionHandler getInstance()
	{
		return singleTonInstance==null 
						? 	
									new BackForwardButtonActionHandler()
								:
									singleTonInstance
									
						;
	}
	
	public void processEvent(Button butt)
	{
		Class clazz = BinaryTreeCreator.class;
		int index = InternetView.getIndex();
		
		index = butt.getLabel().equals(GUIConstants.FORWARD)
						?
									++index
								:
									butt.getLabel().equals(GUIConstants.BACK)
													?
																--index
															:
																index
						;
		
		
		try 
		{
			List all = ServiceRegistry.lookupAll(clazz);
			if(index < 0||index >= all.size())
			{
				return ;
			}else
			{
				if(index==0)
				{
					iv.disableBackButton();
					iv.enableForwardButton();
				}else if(index == all.size()-1)
				{
					iv.disableForwardButton();
					iv.enableBackButton();
				}
				else{
					iv.enableBackButton();
					iv.enableForwardButton();
				}
			}
			InternetView.setIndex(index);
			BinaryTreeCreator btc = (BinaryTreeCreator)ServiceRegistry.lookup(clazz,index);
			iv.showHistory(btc);
		} catch (RegistryException e) 
		{
			
			e.printStackTrace();
		}
	
	}
	

}
