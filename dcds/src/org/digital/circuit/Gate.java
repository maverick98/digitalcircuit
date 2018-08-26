/*
 * Created on Jul 7, 2007
 *
 *
 */
package org.digital.circuit;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.digital.ds.DataStructureConstants;

/**
 * @author msahu
 *
 *
 */
public class Gate extends Element
{
	
	private static final String GUIUtil = "org.digital.ui.gui.GUIUtil";
	private static Class GUIUtilClazz = null;
	private static Object GUIUtilInstance=null;
	
	
	private static final String GateCalculator = "org.digital.circuit.GateCalculator";
	private static Class GateCalculatorClazz = null;
	private static Object GateCalculatorInstance=null;
	
	
	static
	{
		
		try {
			GUIUtilClazz = Class.forName(GUIUtil);
			GUIUtilInstance = GUIUtilClazz.newInstance();
			
			GateCalculatorClazz = Class.forName(GateCalculator);
			GateCalculatorInstance = GateCalculatorClazz.newInstance();
			
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		
		} catch (InstantiationException e) 
		{
		
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
		
			e.printStackTrace();
		}
	}	
	
	
	private static class GateHelper
	{
		private static String  OR ="+";
		private static String  AND ="*";
		
		private static String  NOR ="%";
		private static String  NAND ="$";
		
		private static String  XOR ="@";
		private static String  XNOR ="#";
		
		private static final String DRAW_METHOD="draw_";
		
		
		private static  Map GATEMAP;
		
		static
		{
			GATEMAP=new HashMap();
			GATEMAP.put(OR,"OR");
			GATEMAP.put(AND,"AND");
			GATEMAP.put(NOR,"NOR");
			GATEMAP.put(NAND,"NAND");
			GATEMAP.put(XOR,"XOR");
			GATEMAP.put(XNOR,"XNOR");
		}
		
		public String getDrawingMethodName(final char element)
		{
			
			return DRAW_METHOD+(String)GATEMAP.get(element+"");
		}
		
		public String getCalculatingMethodName(final char element)
		{
			
			return ((String)GATEMAP.get(element+"")).toLowerCase();
		}
		
	}
	
	
	
	
	
	protected Gate(final char gate , final int pos) 
	{
		super(gate, pos);
	}
	
	

	
	public static Element getInstance(final char element,final int pos)
	{
		return new Gate(element,pos);
	}
	
	public int compareTo(Object herGate) 
	{
		Gate myGate = this;
		int diff =-999;
		return herGate==null || !(herGate instanceof Gate)
				?
							-1
						:
								(
									(diff 
									   = 
										   myGate.getPrecedence() 
										   - 
										   ((Gate)herGate).getPrecedence()
									)==0 
							 
								)
								?
											myGate.hashCode()- ((Gate)herGate).hashCode()
										:	
											diff
											
				;
	}
	
	
	public int getPrecedence()
	{
		return DataStructureConstants.OPERATOR.indexOf(this.element);
	}
	
	public boolean calculate(final int x , final int y )
	{
	
		Input dummyX = new Input('x',0);
		dummyX.setValue(x==1);
		Input dummyY = new Input('y',1);
		dummyY.setValue(y==1);
		
		return calculate(dummyX,dummyY);
		
	}
	
	public boolean calculate(final Input x ,final Input y)
	{
		Boolean result=null;
		GateHelper helper = new GateHelper();
		String methodName= helper.getCalculatingMethodName(this.getElement());
		try {
			Method calculatingMethod = GateCalculatorClazz.getDeclaredMethod
														(
															methodName
															,
															new Class[]
															          {       
																		Input.class
																		,
																		Input.class
															          }
														);
		
			result= (Boolean)calculatingMethod.invoke
								(
										GateCalculatorInstance
										,
										new Object[]
										           {
													x
													,
													y
										           }
								);
			
			
			
		} catch (SecurityException e) 
		{
			
			e.printStackTrace();
		} catch (NoSuchMethodException e) 
		{
		
			e.printStackTrace();
		} catch (IllegalArgumentException e) 
		{
			
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
			
			e.printStackTrace();
		} catch (InvocationTargetException e) 
		{
			
			e.printStackTrace();
		}
		return result.booleanValue();
	}
	
	public void draw(Graphics g , int x , int y , int up , int down)
	{
		
		try {
			GateHelper helper = new GateHelper();
			String methodName= helper.getDrawingMethodName(this.getElement());
			Method drawMethod = GUIUtilClazz.getDeclaredMethod
										(
												methodName
												,
												new Class [] 
												           {
																Graphics.class
																,
																int.class
																,
																int.class
																,
																int.class
																,
																int.class
												           }
										);
			
			drawMethod.invoke
						(
								GUIUtilInstance,
								new Object[] 
								           {
											g
											,
											new Integer(x)
											,
											new Integer(y)
											,
											new Integer(up)
											,
											new Integer(down)
											
								           }
						);			
			
			
		} catch (SecurityException e)
		{
		
			e.printStackTrace();
		} catch (NoSuchMethodException e) 
		{
		
			e.printStackTrace();
		} catch (IllegalArgumentException e) 
		{
		
			e.printStackTrace();
		} catch (IllegalAccessException e) 
		{
		
			e.printStackTrace();
		} catch (InvocationTargetException e) 
		{
		
			e.printStackTrace();
		}
		
		
		
	}

	
}
