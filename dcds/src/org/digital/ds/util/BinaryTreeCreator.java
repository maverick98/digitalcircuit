/*
 * Created on Jul 6, 2007
 *
 * 
 */
package org.digital.ds.util;

import java.awt.Graphics;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.digital.circuit.Element;
import org.digital.circuit.Gate;
import org.digital.circuit.Input;
import org.digital.circuit.SequentialCircuit;
import org.digital.ds.Expression;
import org.digital.exceptions.DigException;
import org.digital.exceptions.IllegalStateException;
import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;
import org.digital.math.Number;
import org.digital.math.exceptions.InvalidUserInputException;
import org.digital.registry.ServiceRegistry;
import org.digital.registry.exception.RegistryException;
import org.digital.ui.GUIConstants;



/**
 * @author  msahu
 *
 * 
 */
public class BinaryTreeCreator implements Externalizable
{
	private static DigLogger logger = DigLogManager.getLogger(BinaryTreeCreator.class);
	private Node root =null;
	private Map inputValuesMap = new HashMap();
	private Map uniqueInputValuesMap = new TreeMap();
	private int noOfInputs;
	private int currentInput=0;
	
	public String getInputNames()
	{
		StringBuffer sb = new StringBuffer();
		
		for(Iterator itr = uniqueInputValuesMap.keySet().iterator();itr.hasNext();)
		{
			InputWrapper iWrapper = (InputWrapper)itr.next();
			sb.append(iWrapper.getInput().getElement());
		}
		
		return sb.toString();
	}
	
	private List inputNodes=new ArrayList();
	private String inputExpression;
	private String outputExpression=GUIConstants.MINIMIZATION_DEFAULT_VALUE;
	
	
	/**
	 * @return Returns the inputExpression.
	 */
	public String getInputExpression() {
		return inputExpression;
	}
	/**
	 * @param inputExpression The inputExpression to set.
	 */
	public void setInputExpression(String inputExpression) {
		this.inputExpression = inputExpression;
	}
	/**
	 * @return Returns the outputExpression.
	 */
	public String getOutputExpression() {
		return outputExpression;
	}
	/**
	 * @param outputExpression The outputExpression to set.
	 */
	public void setOutputExpression(String outputExpression) {
		this.outputExpression = outputExpression;
	}
	public void setXY(final int x , final int y)
	{
		assignXYCoordinates(x,y);
		setUpperLowerLines();
	}
	public int  getOutput(){return root.getValue();}
	
	public    class Node
	{
		
		private int x;
		private int y;
		private int upper_Line;
		private int lower_Line;
		
		private int value =-1;

		private Element data;
		private Node left;
		private Node right;
			
		
		public void setLeft(Node left){this.left = left;}
		public void setRight(Node right){this.right = right;}
		public void setData(Element data){this.data = data;}
		public Element getData(){return data;}
		public String toString(){return "" + data +"" +value;}

		
		public Node()
		{
		}
		public Node(Element data) 
		{
			this.data = data;
			if(data.isInput())
			{
				this.value= ((Input)data).getValue()
								?
											1
										:
											0
							;				
			}
		
		}
		
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public boolean isCalculated(){return this.value !=-1;};
		public void setX(int x){this.x=x;}
		public void setY(int y){this.y=y;}
		public int  getX(){return x;}
		public int  getY(){return y;}
		public void setUpper_Line(int upper_Line){this.upper_Line=upper_Line;}
		public void setLower_Line(int lower_Line){this.lower_Line=lower_Line;}
		public int  getUpper_Line(){return upper_Line;}
		public int  getLower_Line(){return lower_Line;}
		public Node getLeft(){return left;}
		public Node getRight(){return right;}
	}




	
	public void showTree()
	{
		showTree(5);
	}
	
	public void showTree(final int length)
	{
		showTree(root,length);
	}
	

	private  void showTree(final Node root,final int length)
	{
		if(root==null)
		   return;
		showTree( root.getRight(),length+1);
		int i=0;
		while(i<length)
		{
		 System.out.print("   ");
		 i++;
		}
		System.out.println(root.getData().getElement()+"("+root.getX()+","+root.getY()+")");				
		System.out.print(root.getData().getElement()+": ");
		if(root.getData().isInput())
		{
			Input input = (Input)root.getData();
			System.out.println(input.getValue());
		}
		showTree( root.getLeft(),length+1);		
	}

	public void assignX(final int x)
	{
		assignX(root,x);
	}
	
	private  void assignX(Node toor,int x)
	{
		if(toor==null)
			return ;

		assignX(toor.getRight(),x+60);


		toor.setX(x);
		assignX( toor.getLeft(),x+60);


	}

	public void  assignY(final int y)
	{
		assignY(root,y);
	}
	
	public void  assignXYCoordinates(final int x , final int y )
	{
		assignX(x);
		assignY(y);
		
	}
	
	
	
 	private  void assignY(Node toor,int y)
	{
		if(toor==null)
			return ;
		
		assignY( toor.getRight(),y-50*countGates(toor.getLeft()));

		toor.setY(y);

		
		assignY( toor.getLeft(),y+50*countGates(toor.getRight()));


	}
 	
 	
 	
 	
	private int countGates(Node toor) 
	{
		
		return toor==null
					?
								0
							:
								toor.getLeft()!=null && toor.getLeft().getData().isInput() && toor.getRight()!=null &&toor.getLeft().getData().isInput()
									?
												1
											:
												1+countGates(toor.getLeft())+countGates(toor.getRight())
								
					;				
		
	}
	public void setUpperLowerLines()
 	{
 		setUpperLowerLines(root);
 	}
 	
	private  void  setUpperLowerLines(Node toor)
	{
		if (toor == null)
			return ;

		setUpperLowerLines( toor.getRight());
		
	   if(toor.getData().isGate())
		{
		    int lr=0;
			if(toor.getRight()!=null && toor.getRight().getData().isGate())
			{
				toor.setUpper_Line(toor.getY()-toor.getRight().getY()-25);
			  lr+=1;
			}
			if(toor.getLeft()!=null && toor.getLeft().getData().isGate())
			{
				toor.setLower_Line(toor.getLeft().getY()-toor.getY()-25);
			  lr+=2;
			}
			if(lr==0)
			{
				toor.setUpper_Line(0);
				toor.setLower_Line(0);

			}
			else if(lr==1)
			{

				toor.setLower_Line(0);

			}else if(lr==2)
			{
				toor.setUpper_Line(0);
				
			}



		}
		setUpperLowerLines(toor.getLeft());

	}
	/**
	 * Created for java serialization mechanism
	 *
	 */
	public BinaryTreeCreator()
	{
		
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
		String str = (String)in.readObject();
		
		try 
		{
			initialize(str);
		
		} catch (IllegalStateException e) 
		{
			
			e.printStackTrace();
		} catch (InvalidUserInputException e) 
		{
			
			e.printStackTrace();
		}
		this.setOutputExpression((String)in.readObject());
		
		this.setCurrentInput(in.readInt());
		
		try 
		{
			this.assignInputValues(this.getCurrentInput());
		} catch (DigException e2) 
		{
			
			e2.printStackTrace();
		}
		
		try 
		{
			ServiceRegistry.set(this);
		} catch (RegistryException e1) 
		{
			
			e1.printStackTrace();
		}
		
	}


	public void writeExternal(ObjectOutput out) throws IOException 
	{
		
		out.writeObject(this.getInputExpression());
		
		out.writeObject(this.getOutputExpression());
		
		out.writeInt(this.getCurrentInput());
		
	}
	
	
	public BinaryTreeCreator( String str ) throws InvalidUserInputException
	{
	
		try {
				initialize(str);
		} catch (IllegalStateException e) 
		{
			e.printStackTrace();
			throw new InvalidUserInputException(str+" is not valid. unable to create BinaryTree");
			
		}
		
		try 
		{
			ServiceRegistry.set(this);
		} catch (RegistryException e1) 
		{
			
			e1.printStackTrace();
		}
	}
	
	static
	{
		ServiceRegistry.register(BinaryTreeCreator.class);
	}
	
	
	private  void initialize(final String str) throws IllegalStateException, InvalidUserInputException
		{
			 this.inputExpression=str;
			 SequentialCircuit sc = new SequentialCircuit(str);

			 Expression infixExpression = new Expression(sc);
			 List prefix = infixExpression.toPrefix();

			 int i=1,len=prefix.size();
			 Node root= new Node((Element)prefix.get(0));

			 Node toor= root;
			 while(i<len)
			 {
				
				 Element element = (Element)prefix.get(i);

				   if(element.isLeft(toor.getData()))
					{

						if(toor.getLeft()==null)
						{

							Node left= new Node(element);
							toor.setLeft(left);
							toor=root;
							i++;
						}
						else
						{

							toor= toor.getLeft();
					
						}
					}
					else
					{

						if(toor.getRight()==null)
						{


							Node right= new Node(element);
							toor.setRight(right);
							toor=root;
							i++;
						}
						else
						{

							toor= toor.getRight();
						}				
					}
			 }
			 
		 this.root = root;
		 initializeInputValues();
		 
		}
	
	private void initializeInputValues() 
	{
		collectNodes(root);
		List list = new ArrayList();
		for(int i=0;i<inputNodes.size();i++)
		{
			list.add((((Node)inputNodes.get(i)).getData()));
		}
		initializeInputValuesMap(list);
	
		
	}
	private void collectNodes(Node toor)
	{
		if(toor == null)
		{
			return ;
		}
		collectNodes(toor.getLeft());
		if(toor.getData().isInput())
		{
			inputNodes.add(toor);
		}
		collectNodes(toor.getRight());
	}
	
	private void initializeInputValuesMap(List circuit) 
	{
		Set set = new HashSet();
		for(int i=0;i<circuit.size();i++)
		{
			Input element=null ; 
			if((element =(Input)circuit.get(i)).isInput())
			{
				InputWrapper iWrapper =new InputWrapper((Input)element); 
				logger.debug("putting "+iWrapper + "in to the dumb map");
				inputValuesMap.put((Input)element,new Integer(0));
				logger.debug(" status "+set.contains(iWrapper));
				boolean b = set.add(iWrapper);
				if(b)logger.debug("successfully added "+iWrapper + " in to the set");
			}
		}
		
		for(Iterator itr = set.iterator();itr.hasNext();)
		{
			uniqueInputValuesMap.put((InputWrapper)itr.next(),new Integer(0));
		}
		this.noOfInputs=set.size();
	
		logger.debug("size  of unique inputs "+noOfInputs);
		logger.debug(" unique input"+set);
		logger.debug(" actual input"+inputValuesMap);
		logger.debug(" unique input map"+uniqueInputValuesMap);
		
		
	}
	
	

	private static class InputWrapper implements  Comparable
	{
		private Input input;
		public InputWrapper(Input input)
		{
			this.input = new Input
								(
										(char)(input.getElement()<97?(input.getElement()+32):input.getElement())
										,
										input.getPos()
								);
		}
		public Input getInput(){return input;}

		public int hashCode()
		{
			return input.getElement();			
			
		}
		public int compareTo( Object herObject) 
		{
			
			return  herObject!=null && herObject instanceof InputWrapper
						?
								this.compareTo((InputWrapper)herObject)
								:
									-2
					;
		}
		public String toString(){return input.toString()+ " :"+input.getValue();}
		public boolean equals(Object herObject)
		{
			
			return herObject!=null && herObject instanceof InputWrapper
							?
									equals((InputWrapper)herObject)
									:
										false
							;			
		}
		private boolean equals(InputWrapper herInput)
		{
			int ourCompatibility =0;
			
			//System.out.println("this ="+this.toString());
			//System.out.println("that ="+herInput);
			//System.out.println("diff =" +this.compareTo(herInput));
			return (ourCompatibility =this.compareTo(herInput))==0 
								?
											true
										:
											false
					;						
		}
		
		private int compareTo(InputWrapper herInput)
		{
			int ourCompatibility = 0;
			return input.getElement() == herInput.getInput().getElement()
							?
										0
									:
										(ourCompatibility =(input.getElement() - herInput.getInput().getElement()))==32
											?
														0
													:
														ourCompatibility==-32
															?
																		0
																	:
																		ourCompatibility
							;							
		}
	
		
	}
	public boolean assignInputValues(final int x) throws DigException
	{
		this.setCurrentInput(x);
		int values[]=null;
		values  = Number.getBitsOf(x,noOfInputs);
		int i=0;
		for(Iterator itr = uniqueInputValuesMap.keySet().iterator();itr.hasNext();i++)
		{
			  InputWrapper i1 = (InputWrapper)itr.next();
			  
			  i1.getInput().setValue(values[i]==1?true:false);
			  uniqueInputValuesMap.put(i1, new Integer(values[i]));
			  
			
		}
		
		logger.debug("final ="+uniqueInputValuesMap);
		
		for(Iterator itr = inputValuesMap.keySet().iterator();itr.hasNext();i++)
		{
			  Input i1 = (Input)itr.next();
			  boolean shouldFlip = i1.getElement()<97;
			  InputWrapper key = new InputWrapper(i1);
			  i1.setValue(
					  		(((Integer)uniqueInputValuesMap.get(key)).intValue()==1)
					  					?
					  								shouldFlip
					  									?
					  												false
					  											:
					  												true
					  							:
					  								shouldFlip
					  									?
					  												true
					  											:
					  												false
					  	 );
			  inputValuesMap.put(i1,i1.getValue()?new Integer(1):new Integer(0));							
			  
			  
			  
			
		}
		
		logger.debug("Funny "+inputValuesMap);
		for( i=0;i<inputNodes.size();i++)
		{
			Node inputNode = (Node)inputNodes.get(i);
			Input input  = (Input)(inputNode.getData());
			inputNode.setValue
							(
									((Integer)inputValuesMap.get(input)).intValue()
							);
			logger.debug(" setting "+inputNode.getData() + "  to "+inputNode.getValue());
			
		}
	
		logger.debug("final ,,,,, ="+inputValuesMap);
		
		return true;
	}
	
	
	public int  calculate() {return calculate(root);}
	public int calculate(Node toor)
	{
		
		if(toor.getData().isGate())
		{
		
			toor.setValue
					(
							((Gate)toor.getData()).calculate
							(
									calculate(toor.getLeft())
									,
									calculate(toor.getRight())
							)
		
							?
										1
									:
										0	
							
					);
			
										
										
		}
		
		return  toor.getValue();
		
		
	}


	public void display(Graphics g) {display(g,root);}
 
	
  
  private  void display(Graphics g, Node toor)
	{
	  if(toor==null)
		  return ;
	  
	  display(g,toor.getRight());
	  if(toor.getData().isGate())
	  {
		  ((Gate)toor.getData()).draw(g,toor.getX(),toor.getY(),toor.getUpper_Line(),toor.getLower_Line());
		  if(toor == root)
		  {
		  	g.drawString(this.getOutput()+"",toor.getX()-30,toor.getY());
		  }
		  BinaryTreeHelper.showOperands(toor,g);
		  
	  }
	  display(g,toor.getLeft());

	}
  
  	private static class BinaryTreeHelper
	{
 
  		static void showOperands(Node toor,Graphics g)
  		{
  			Input input  = null;
  	        String operand=null;  
  			if(toor.getLeft().getData().isInput())  
			{
  				input = (Input)toor.getLeft().getData();
  				
  				operand=input.getElement()+"="+input.intValue();
  				
  				g.drawString(operand,toor.getX()+60,toor.getY()+29);
 		    }
	
		   if(toor.getRight().getData().isInput()) 	  
			{
			   input =(Input) toor.getRight().getData();
			   
			   operand=input.getElement()+"="+input.intValue();
			   
			   g.drawString(operand,toor.getX()+60,toor.getY()-21);
			
			}
	
  			
  		}
  		static boolean calculte(Node toor)
  		{
  			boolean result=false;
  	
  			if(toor.getLeft().getData().isInput() && toor.getRight().getData().isInput()) 	  
			{
			 
  				result=  ((Gate)toor.getData()).calculate
  																(
  																	(Input)(toor.getLeft().getData())
  																	,
  																	(Input)(toor.getRight().getData())
  																		
  																)
  						;										
			}
  			return result;
  			
  		}
  		
  		
	}
  	
  
	/**
	 * @return Returns the currentInput.
	 */
	public int getCurrentInput() {
		return currentInput;
	}
	/**
	 * @param currentInput The currentInput to set.
	 */
	public void setCurrentInput(int currentInput) {
		this.currentInput = currentInput;
	}
}
