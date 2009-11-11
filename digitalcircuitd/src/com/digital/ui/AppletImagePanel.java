/*
 * Created on Jul 28, 2007
 *
 */
package com.digital.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.net.URL;

/**
 * @author msahu
 *
 * 
 */
public class AppletImagePanel extends Panel
{

	Image image;
	 
	    public AppletImagePanel()
	    {
	       loadImage();
	    }
	 
	    public void paint(Graphics g)
	    {
	        super.paint(g);
	        int w = getWidth();
	        int h = getHeight();
	        int imageWidth = image.getWidth(this);
	        int imageHeight = image.getHeight(this);
	        int x = (w - imageWidth)/2;
	        int y = (h - imageHeight)/2;
	      
	        g.drawImage(image, x, y, this);
	    }
	 
	    public Dimension getPreferredSize()
	    {
	        return new Dimension(image.getWidth(this), image.getHeight(this));
	    }
	 
	    private void loadImage()
	    {
	        String fileName = "/xxx.jpg";
	        MediaTracker tracker = new MediaTracker(this);
	        URL url = getClass().getResource(fileName);
	        System.out.println("url "+url);
	        image = Toolkit.getDefaultToolkit().getImage(url);
	        tracker.addImage(image, 0);
	        try
	        {
	            tracker.waitForID(0);
	        }
	        catch(InterruptedException ie)
	        {
	            System.err.println("interrupt: " + ie.getMessage());
	        }
	    }
}
