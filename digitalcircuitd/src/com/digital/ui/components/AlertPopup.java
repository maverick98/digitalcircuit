/*
 * Created on Aug 5, 2007
 *
 * 
 */
package com.digital.ui.components;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.digital.ui.GUIConstants;

/**
 * @author msahu
 *
 * 
 */
public  class AlertPopup extends Frame implements ActionListener 
	{

		  public AlertPopup(String message ) 
		  {
		    add(new Label(message));
		    Button bt = new Button(GUIConstants.OK);
		    bt.addActionListener(this);
		    Panel panel = new Panel();
		    add("South",panel);
		    panel.add("South",bt);
		    setSize(200, 100);
		    

		  	addWindowListener
			(
        		new WindowAdapter()
				{
        			public void windowClosing(WindowEvent e)
        			{
        				Frame frame = (Frame)e.getSource();
				        frame.setVisible(false);
				        frame.dispose();
        			}
				}
        	);
		  }
		  
		  

	
		public void actionPerformed(ActionEvent e) 
		{
			Object obj = e.getSource();
			if( obj instanceof Button)
			{
				if (((Button)obj).getLabel()==GUIConstants.OK)
				{
					this.setVisible(false);
					this.dispose();
				}
			}
			
		}
	}


