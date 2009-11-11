/*
 * Created on Jun 17, 2007
 *
 * 
 */
package com.digital.map;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.digital.ds.util.BinaryTreeCreator;
import com.digital.exceptions.DigException;
import com.digital.logging.DigLogManager;
import com.digital.logging.DigLogger;
import com.digital.map.helper.PersistenceHelper;
import com.digital.math.Number;
import com.digital.registry.ServiceRegistry;
import com.digital.ui.InternetView;

/**
 * 
 * TODO
 *  
 *  
 * 
 * 
 * 
 * 
 * 
 *
 * @see com.digital.map.MapGenerator#generate()
 * @author msahu
 * This is a singleton class 
 * 
 */
public class KarnaughMapMinimizer extends MapMinimizer
{
	private static DigLogger logger = DigLogManager.getLogger(KarnaughMapMinimizer.class);
	private static KarnaughMapMinimizer singleTonInstance;
	private KarnaughMapMinimizer(final int N)
	{
		super(N);
	}
	
	public static KarnaughMapMinimizer getInstance(final int N)
	{
		return  singleTonInstance ==null  || KarnaughMap.getN()!=N
					?
							singleTonInstance = new KarnaughMapMinimizer(N)
								:
							singleTonInstance
					;
		
	}
	
	
	

	public  void generate() throws DigException
	{

		KarnaughMapProcessor kMapProcessor=KarnaughMapProcessor.getInstance
														(
																KarnaughMapConstants.THREADPOOLSIZE!=0
														);
														
		kMapProcessor.process();
	
		
	}
	
	

	


	public String minimize(final int [] numbers) throws DigException 
	{
		super.minimize(numbers);
		
		List numbersList = new ArrayList();
		
		for(int i=0;i<numbers.length;i++)
		{
			numbersList.add(new Number(numbers[i]));
			
		}
		
		return minimize(numbersList,null);
	}
	
	public  String minimize(final List numbers , char names[]) throws DigException
	{
		StringBuffer sb = new StringBuffer();
		
		List results = new ArrayList();
		
		super.minimize(numbers);
		if(!PersistenceHelper.isGenerated())
		{
			logger.debug("pain in the ass . going to generate");
			this.generate();
			
		}else
		{
			logger.debug("Cool already generated");
		}
		
		for(int x =getX(numbers.size()); x<=KarnaughMap.getN();x++)
		{
			
			MinTermContainer mtcx=null;
			try {
				logger.debug("loading "+PersistenceHelper.getFile(x));
				mtcx = (MinTermContainer)PersistenceHelper.load(x);
			} catch (FileNotFoundException e) 
			{
				
			
			}
			if(mtcx == null)
			{
				return null;
			}
			
			List minTerms = mtcx.getMinTerms(true);
			logger.debug("Minterms ="+minTerms);
			for(int j=0;j<minTerms.size();j++)
			{
				MinTerm mt = (MinTerm)minTerms.get(j);
				logger.debug("$$$$");
				mt.display();
				logger.debug("######");
				logger.debug("mt = "+mt);
				logger.debug("numbers ="+numbers);
				if(mt.isEqualsTo(numbers))
				{
					
					logger.debug("returning "+mt);
					return mt.toString(names);
				}else if(mt.isSubsetOf(numbers) && !isAlreadyComputed(results,mt))
				{
					logger.debug("adding");
					sb.append(mt.toString(names)+"+ ");
					results.add(mt);
				}
				
				
			}
			
		}
		sb.delete(sb.length()-2,sb.length());
		
		return sb.toString();
	}
	
	
	/**
	 * @param i
	 * @return
	 */
	private int getX(final int i) 
	{
		return i==0?KarnaughMap.getN():(int)(KarnaughMap.getN()-(Math.log(i)/Math.log(2)));
		
	}

	private boolean  isAlreadyComputed(List results, MinTerm myMinTerm) 
	{
		for(int i=0;i<results.size();i++)
		{
			MinTerm herMinTerm = (MinTerm) results.get(i);
			if(myMinTerm.belongsTo(herMinTerm))
			{
				return true;
			}
			
		}
		
		return false;
	}

	
	
	public String minimize(String exp) throws DigException 
	{

		BinaryTreeCreator btc = (BinaryTreeCreator)ServiceRegistry.lookup(BinaryTreeCreator.class,InternetView.getIndex());
		
		String inp = btc.getInputNames();
		
		
		
		int size = inp.length();
		List numbers = new ArrayList();
		
		for(int i=0;i<(int)Math.pow(2,size);i++)
		{
			logger.debug("assigning values  "+i);
			btc.assignInputValues(i);
			btc.calculate();
			if(btc.getOutput()==1)
			{
				logger.debug("got op for "+i);
				numbers.add(new Number(i));
			}
		}
		
		
		System.out.println("numbers are "+numbers);
		System.out.println("array ="+inp);
		return this.minimize(numbers,inp.toCharArray());
	}
	
	
}
