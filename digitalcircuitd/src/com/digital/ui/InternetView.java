
package com.digital.ui;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.digital.ds.util.BinaryTreeCreator;
import com.digital.exceptions.DigException;
import com.digital.logging.DigLogManager;
import com.digital.logging.DigLogger;
import com.digital.math.Number;
import com.digital.math.exceptions.InvalidNumberException;
import com.digital.math.exceptions.InvalidUserInputException;
import com.digital.ui.components.AlertPopup;
import com.digital.ui.components.InputButton;
import com.digital.ui.handler.ActionHandler;
import com.digital.util.PropertyUtil;




public class InternetView extends Applet 
{
		private static DigLogger logger = DigLogManager.getLogger(InternetView.class);
		private static int index=-1;
		private static InternetView iv;
		public InternetView()
		{
			super();
			iv=this;
		
		}
		public static InternetView getInstance()
		{
		
			return iv;
		}
	
		Button back=null;
		Button forward=null;
		Image backImage=null;
		

		public void init()
		{
		
		
	        Panel fieldPanel = new Panel();
	        input = new TextField(30);
	        fieldPanel.add(input,"South");
	        tf.setEditable(false);
	        fieldPanel.add(tf,"North");
	       	panel.add(fieldPanel,"South");
			setLayout(new BorderLayout());
		    add("South",panel);
		    
		    Button minimizeButton = new Button(GUIConstants.MINIMIZE);
		    ac=ActionHandler.getInstance();
		    minimizeButton.addActionListener(ac);
		    back = new Button(GUIConstants.BACK);
		    
		    back.addActionListener(ac);
		    forward = new Button(GUIConstants.FORWARD);
		    
		    forward.addActionListener(ac);
		    
		    buttonPanel.add("South",back);
		    buttonPanel.add("South",forward);
		    buttonPanel.add("South",minimizeButton);
		    add("North",buttonPanel);
		    
		    
		   
		    
		    backImage = Toolkit.getDefaultToolkit().getImage
													(
															PropertyUtil.getProperty
																			(
																					"BACKGROUNDIMAGE"
																					,
																					GUIConstants.BACKGROUNDIMAGE
																			)
													);
		    
		   
		    
		    this.setBackground(GUIConstants.BACKGROUNDCOLOR);
		    this.setSize(1600,1200);
		    
		    
		}
		
	

    
    public boolean handleEvent(Event event) 
    {
        switch (event.id) {
            case Event.ACTION_EVENT:
                if (event.target == input) {
                	
                    initiate(input.getText()); 
                    return true; 
                }
        }
        return false; 
    } 
    /**
	 * 
	 */
    public void enableBackButton()
    {
    	back.setEnabled(true);
    }
    public void enableForwardButton()
    {
    	forward.setEnabled(true);
    }
    public void disableBackButton()
    {
    	back.setEnabled(false);
    }
    public void disableForwardButton()
    {
    	forward.setEnabled(false);
    }
    
    
	private void initiate(String txt) 
	{
		logger.debug("initiating ...");
		index++;
		
		
		
		try 
		{
			iv.work(new BinaryTreeCreator(txt));
		} catch (InvalidUserInputException e) 
		{
		
			
			AlertPopup popup = new AlertPopup(GUIConstants.INVALID_USER_EXPRESSION);
			popup.setVisible(true);
			popup.setLocationRelativeTo(this);
			index--;
			
		}
	}
	
	public void showHistory(BinaryTreeCreator btc)
	{
		iv.work(btc);
	}
	private void  work(BinaryTreeCreator btc)
	{
		iv.setOutput(btc.getOutputExpression());
		iv.setUser(btc.getInputExpression());
		
		iv.getInput().setText(btc.getInputExpression());
		iv.setBtc(btc);
		iv.processInput();
		
		iv.repaint();
	}
	

	private void setUser(String user) 
	{
		this.user=user;
		
	}

	BinaryTreeCreator  btc = null;
  
    private void processInput()
    {
    	
    	 if(iv.getUser()!=null)
		  {
			   try 
			   {
			   		
			   		iv.setInp(iv.getBtc().getInputNames());
			   		iv.removeButtons();
			   		iv.catchButtons();
			   		iv.assignValuesToInputButtons(iv.getBtc().getCurrentInput());
			   		iv.getBtc().assignInputValues(iv.getBtc().getCurrentInput());
	
			   } catch (InvalidUserInputException e) 
			   {
				
			   		e.printStackTrace();
			   } catch (DigException e) 
			   {
				
				e.printStackTrace();
			   }
		}
    }

	private void assignValuesToInputButtons(int currentInput) 
	{	
		int bits[] = null;
		try 
		{
			bits = Number.getBitsOf(currentInput,iv.getUserList().size());
		} catch (InvalidNumberException e) 
		{

			e.printStackTrace();
		} catch (InvalidUserInputException e) 
		{
		
			e.printStackTrace();
		}
		
		for(int i=0;i<iv.getUserList().size();i++)
		{
			InputButton herButt = (InputButton)userList.get(i);
			herButt.setLabel(bits[i]);
			
		}
		
	}
	private void catchButtons() 
	{
		
		InputButton butt=null;
		userList = new ArrayList();
		for(int i=0;i<inp.length();i++)
		{
			butt = new InputButton(inp.charAt(i));
			userList.add(butt);
			butt.addActionListener(ac);
			
		}
		Collections.sort(userList);
		iv.setUserList(userList);
	}	
	
	private void addButtons()
	{
	   
	   for(int i=0;i<userList.size();i++)
		{
			InputButton ib = (InputButton)userList.get(i);
			buttonPanel.add(ib);
		}
	  
	}
	
	private void removeButtons()
	{
	   
	   for(int i=0;i<iv.getUserList().size();i++)
		{
			InputButton ib = (InputButton)iv.getUserList().get(i);
			buttonPanel.remove(ib);
		}
	}
	
	private long getNumber()
	{
		int bits[] = new int[iv.getUserList().size()];
		long rtn=0;
		for(int i=0;i<iv.getUserList().size();i++)
		{
			InputButton ib = (InputButton)iv.getUserList().get(i);
			bits[i]=ib.getValue();
		}
		
		try 
		{
			rtn =(new Number(bits)).getValue();
		
		} catch (InvalidNumberException e) 
		{
			
			e.printStackTrace();
		}
		return rtn;
	}

	
	public void paint(Graphics g)
	{

		
		g.drawImage(backImage,0,0,this);
	  	iv.addButtons();
		iv.displayGraph(g);
	
	}
	/**
	 * @param g
	 */
	private void displayGraph(Graphics g) 
	{
		if(iv.getBtc()!=null)
		{
		
			try 
			{
	
				iv.getBtc().assignInputValues((int)getNumber());
			} 
			catch (DigException e) 
			{
				
				e.printStackTrace();
			}
			iv.getBtc().calculate();
			iv.getBtc().setXY
						(
								Integer.parseInt(PropertyUtil.getProperty("x",GUIConstants.X+""))
								,
								Integer.parseInt(PropertyUtil.getProperty("y",GUIConstants.Y+""))
						);
			
			iv.getBtc().display(g);
		}	
 
		 
	}


	
	
	
  




	/**
	 * @return Returns the btc.
	 */
	public BinaryTreeCreator getBtc() {
		return btc;
	}
	/**
	 * @param btc The btc to set.
	 */
	public void setBtc(BinaryTreeCreator btc) {
		this.btc = btc;
	}
	/**
	 * @return Returns the buttonPanel.
	 */
	public Panel getButtonPanel() {
		return buttonPanel;
	}
	/**
	 * @param buttonPanel The buttonPanel to set.
	 */
	public void setButtonPanel(Panel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}
	/**
	 * @return Returns the input.
	 */
	public TextField getInput() {
		return input;
	}
	/**
	 * @param input The input to set.
	 */
	public void setInput(TextField input) {
		this.input = input;
	}
	/**
	 * @return Returns the label.
	 */
	public Label getLabel() {
		return label;
	}
	/**
	 * @param label The label to set.
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	/**
	 * @return Returns the n.
	 */
	public Number getN() {
		return n;
	}
	/**
	 * @param n The n to set.
	 */
	public void setN(Number n) {
		this.n = n;
	}
	/**
	 * @return Returns the panel.
	 */
	public Panel getPanel() {
		return panel;
	}
	/**
	 * @param panel The panel to set.
	 */
	public void setPanel(Panel panel) {
		this.panel = panel;
	}
	/**
	 * @return Returns the userList.
	 */
	public List getUserList() {
		return userList;
	}
	/**
	 * @param userList The userList to set.
	 */
	public void setUserList(List userList) {
		this.userList = userList;
	}
	Label label;
    TextField input;
    String user; 
    String inp;
    
    public String getUser(){return user;}
    public String getInp()
    {
    	return inp;
    }
    public void setInp(String inp){this.inp=inp;}
    public TextField getTextField(){return tf;}
    public void setOutput(String op)
    {
    	this.tf.setText(op);
    }
    
    Number n = new Number();
    
    List userList =new ArrayList();
    
    Panel panel = new Panel(new BorderLayout());
    Panel buttonPanel = new Panel();
    TextField tf = new TextField(30);
    ActionHandler ac  ;

		/**
		 * @return Returns the index.
		 */
		public static int getIndex() {
			return index;
		}
		/**
		 * @param index The index to set.
		 */
		public static void setIndex(int index) {
			InternetView.index = index;
		}
		
		public static void incrementIndex()
		{
			index++;
		}
} 








