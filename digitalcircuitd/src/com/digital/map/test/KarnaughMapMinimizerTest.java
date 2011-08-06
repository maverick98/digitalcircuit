/*
 * Created on Jun 27, 2007
 *
 * 
 */
package org.digital.map.test;

import java.util.ArrayList;
import java.util.List;

import org.digital.ds.util.BinaryTreeCreator;
import org.digital.exceptions.DigException;
import org.digital.logging.DigLogManager;
import org.digital.logging.DigLogger;
import org.digital.map.KarnaughMapMinimizer;
import org.digital.map.Minimizer;
import org.digital.math.Number;
import org.digital.util.Displayer;

/**
 * @author msahu
 * 
 */
public class KarnaughMapMinimizerTest 
{
	private static DigLogger logger = DigLogManager.getLogger(KarnaughMapMinimizerTest.class);
	public static void main(String args[]) throws NumberFormatException, DigException
	{
		KarnaughMapMinimizerTest kmgt = new KarnaughMapMinimizerTest();
		
		long start = System.currentTimeMillis();
		kmgt.testGenerate(Integer.parseInt(args[0]));
		
		long end1 = System.currentTimeMillis();
		
		Displayer.sop("time taken for generation="+(end1-start)/1000 +"s");
		
		
		kmgt.testMinimize1(args);
		long end2 = System.currentTimeMillis();
		Displayer.sop("time taken to minimize= "+(end2-end1)/1000+"s" );
		
		
	}

	
	
	/**
	 * @param args
	 * @throws DigException
	 */
	private void testMinimize1(String[] args) throws DigException 
	{
		BinaryTreeCreator btc = new BinaryTreeCreator(args[1]);
		
		String inp = btc.getInputNames();
		System.out.println("inp ="+inp);
		int size = inp.length();
		System.out.println("size = "+size);
		KarnaughMapMinimizer mg = KarnaughMapMinimizer.getInstance(size);
	
		System.out.println("output ="+mg.minimize(args[1]));
	}

	/**
	 * @param i
	 * @throws DigException
	 */
	private void testTestMinimize(int N) throws DigException {
		// TODO Auto-generated method stub
		KarnaughMapMinimizer mg = KarnaughMapMinimizer.getInstance(N);
		List numbers = new ArrayList();
		numbers.add(new Number(0));
		numbers.add(new Number(1));
		numbers.add(new Number(4));
		numbers.add(new Number(5));
		
		//mg.testMinimize(numbers);
		
		
	}

	/**
	 * @param i
	 * @throws DigException
	 */
	private void testMinimize( final String [] args) throws DigException 
	{
		KarnaughMapMinimizer mg = KarnaughMapMinimizer.getInstance(Integer.parseInt(args[0]));
		List list = new ArrayList();
		int l =-1;
		int i=2;
		int size = Integer.parseInt(args[1]);
		do
		{
			l = Integer.parseInt(args[i++]);
			list.add(new Number(l));
			
		}while(i<size+2);
		
		Displayer.sop("FINAL="+mg.minimize(list,args[i].toCharArray()));
		//Displayer.sop(list);
		
	}

	/**
	 * @param N
	 * @throws DigException
	 */
	private void testGenerate(final int N) throws DigException 
	{
		Minimizer mg = KarnaughMapMinimizer.getInstance(N);
		mg.generate();
		//Displayer.sop(list);
	}

}
