/*
 * Created on Jul 22, 2007
 *
 * 
 */
package org.digital.ui.handler;

import org.digital.ui.components.InputButton;

/**
 * @author msahu
 * 
 * 
 */
public class InputButtonActionHandler extends ActionHandler 
{
	private static InputButtonActionHandler singleTonInstance;
	private InputButtonActionHandler()
	{
		super();
	}

	public static ActionHandler getInstance()
	{
		return singleTonInstance==null 
						? 	
									new InputButtonActionHandler()
								:
									singleTonInstance
									
						;
	}
	
	public void processEvent(InputButton ib)
	{
		ib.flip();
		iv.repaint();
	}
	
}
