/*
 * Created on Jul 28, 2007
 *
 * 
 */
package org.digital.ui;

import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;
import org.digital.ui.components.MainFrame;

/**
 * @author msahu
 * @TODO
 * 1) it does not calculate the length of bars connecting to other gates correctly when it is highly recursed(Fixed)
 * 2) new customized minimize method  screwed up the perfectly working older methods (Fixed)
 * 3) generate the kmapcombination in a file in the time of installation (now)
 * 
 * 
 * 6) provide facilities to design circuits from kmap(done)
 * 7) read threadpool size from the  java command line -D option(later)
 * 8) read the background colors etc. from the settings.conf property file(later)
 * 9) write a small installer and autolauncher  program (now)
 * 10) refactor the codes specially catch block where stack trace are thrown to standard o/p(later) 
 * 11) make the UI more user friendly(later)
 * 12) currently the UI does not display the input buttons unless it is resized.(later) 
 * 13) think of displaying the UI in an html instead of applet pane(later)
 * 14) handle the invalid inputs properly(Fixed)
 * 15) add functionality to save the current work and hence the user can later start with what she had done till yet.(Done)
 * 16) when you enter more than expression. do not minmize . and press back/forward button .and then minimize the system throws exception(Fixed)
 * 
 */
public class StandAloneView 
{
	private static DigLogger log = DigLogManager.getLogger(StandAloneView.class);
	public static void main(String[] args) 
	{
		log.debug("Starting the system");
		        
        StandAloneView sav= new StandAloneView();
        sav.createGUI();
        
	}
	
	public void createGUI()
	{
		InternetView applet = new InternetView();
		MainFrame mainFrame = new MainFrame(applet);
		mainFrame.display();
		
		
	}
}

