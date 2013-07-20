/*
 * Created on Jul 4, 2007
 *
 * 
 */
package org.digital.ui.gui;

import java.awt.Graphics;

import org.digital.ui.GUIConstants;

/**
 * @author msahu
 *
 * 
 */
public class GUIUtil 
{
	
	  public static void draw_AND(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
		  
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+10,y);
		 g.drawArc(x+10,y-15,30,30,90,180);
		 g.fillArc(x+10,y-15,30,30,90,180);     
		 g.drawLine(x+25,y-15,x+35,y-15);
	  	 g.drawLine(x+25,y+15,x+35,y+15);
		 g.drawLine(x+35,y-15,x+35,y+15);

		 g.fillRect(x+25,y-15,10,30);

		 g.drawLine(x+35,y-10,x+45,y-10);
	 	 g.drawLine(x+35,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);

	   

		
		}//end of draw_AND ()

	public static void draw_OR(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+10,y);
		 g.drawArc(x+10,y-15,40,30,90,180);
	     g.fillArc(x+10,y-15,40,30,90,180);
		 g.drawArc(x+20,y-15,25,30,90,180);
	  	 g.setColor(GUIConstants.color);
		 g.fillArc(x+20,y-15,25,30,90,180);	
		      g.setColor(GUIConstants.color);

		 g.drawLine(x+25,y-10,x+45,y-10);
	 	 g.drawLine(x+25,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);
	   

		
		}//end of draw_OR ()
	  
	public static void draw_NOR(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+6,y);
		 g.drawOval(x+6,y-1,4,4);

		 g.drawArc(x+10,y-15,40,30,90,180);
	     g.fillArc(x+10,y-15,40,30,90,180);
		 g.drawArc(x+20,y-15,25,30,90,180);

		 g.setColor(GUIConstants.color);
		 g.fillArc(x+20,y-15,25,30,90,180);	
		      g.setColor(GUIConstants.color);
			

		 g.drawLine(x+25,y-10,x+45,y-10);
	 	 g.drawLine(x+25,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);
	   

		
		}//end of draw_NOR ()
	  
	   public static void draw_NAND(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+6,y);
		 g.drawOval(x+6,y-1,4,4);

		 g.drawArc(x+10,y-15,30,30,90,180);
		 g.fillArc(x+10,y-15,30,30,90,180);
	     g.drawLine(x+25,y-15,x+35,y-15);
	  	 g.drawLine(x+25,y+15,x+35,y+15);
		 g.drawLine(x+35,y-15,x+35,y+15);
		 	 g.fillRect(x+25,y-15,10,30);

		 g.drawLine(x+35,y-10,x+45,y-10);
	 	 g.drawLine(x+35,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);
	   

		
		}//end of draw_NAND ()

	public  static void draw_XOR(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+10,y);
		 g.drawArc(x+10,y-15,40,30,90,180);
	     
		 g.drawArc(x+20,y-15,25,30,90,180);
		 g.fillArc(x+10,y-15,40,30,90,180);
		 	
			 g.setColor(GUIConstants.color);
		 g.fillArc(x+20,y-15,25,30,90,180);	
		      g.setColor(GUIConstants.color);
		  g.drawArc(x+26,y-15,25,30,90,180);	

		 g.drawLine(x+30,y-10,x+45,y-10);
	 	 g.drawLine(x+30,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);
	   

		
		}//end of draw_XOR ()
	  public static void draw_XNOR(Graphics g,int x, int y,int up,int down) //60X50 rectangle
		{
	     g.setColor(GUIConstants.color);
	     g.drawLine(x,y,x+6,y);
	 	 g.drawOval(x+6,y-1,4,4);

		 g.drawArc(x+10,y-15,40,30,90,180);
		 	 g.fillArc(x+10,y-15,40,30,90,180);
	     
		 g.drawArc(x+20,y-15,25,30,90,180);
			
		 g.setColor(GUIConstants.color);
		 g.fillArc(x+20,y-15,25,30,90,180);	
		      g.setColor(GUIConstants.color);
		   g.drawArc(x+26,y-15,25,30,90,180);	

		 g.drawLine(x+30,y-10,x+45,y-10);
	 	 g.drawLine(x+30,y+10,x+45,y+10);

		 g.drawLine(x+45,y-10,x+45,y-25);
	 	 g.drawLine(x+45,y+10,x+45,y+25);

		 g.drawLine(x+45,y-25,x+60,y-25);
	 	 g.drawLine(x+45,y+25,x+60,y+25);

		 g.drawLine(x+60,y-25,x+60,y-25-up);
	 	 g.drawLine(x+60,y+25,x+60,y+25+down);
	   

		
		}//end of draw_XNOR ()

	 

}
