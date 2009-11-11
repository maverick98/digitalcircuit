/*
 * Created on Jul 22, 2007
 *
 */
package com.digital.ui.handler;

import java.awt.Button;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.digital.ui.GUIConstants;
import com.digital.ui.InternetView;
import com.digital.ui.components.InputButton;

/**
 * @author msahu
 * @singleTon
 * 
 */
public class ActionHandler implements ActionListener {
	


	private static ActionHandler ac;
	protected static InternetView iv = InternetView.getInstance();
	protected ActionHandler(){super();}
	public static  ActionHandler getInstance()
	{
		return ac==null?(ac=new ActionHandler()):ac;
	}

	private void handleEvent(ActionEvent evt)
	{
		Object e = evt.getSource();
		
		if(e instanceof InputButton)
		{
		
			 	((InputButtonActionHandler)ActionHandler.getInstance(GUIConstants.INPUTBUTTON)).processEvent((InputButton)e); 
			 
		}else if((e instanceof Button) && ((Button)e).getLabel().equals(GUIConstants.MINIMIZE))
		{
		
				((MinimizeActionHandler)ActionHandler.getInstance(GUIConstants.MINIMIZE)).processEvent((Button)e);
		}else if((e instanceof Button) && (((Button)e).getLabel().equals(GUIConstants.BACK) || ((Button)e).getLabel().equals(GUIConstants.FORWARD)) )
		{
		
				((BackForwardButtonActionHandler)ActionHandler.getInstance(GUIConstants.BACKFORWARD)).processEvent((Button)e);
		}
		
		
		
		
		
	}
	
	
	private void processEvent(Component cmp) 
	{
		
		
	}
	private static ActionHandler getInstance(String string) 
	{
		return string.equals(GUIConstants.INPUTBUTTON)
						?
									InputButtonActionHandler.getInstance()
								:	
									string.equals(GUIConstants.MINIMIZE)
										?
													MinimizeActionHandler.getInstance()
												:
													string.equals(GUIConstants.BACKFORWARD)
													
														?
																	BackForwardButtonActionHandler.getInstance()
																:
																	null
													
						;
		
	}

	public void actionPerformed(ActionEvent evt) 
	{
		handleEvent(evt);
	}

}
