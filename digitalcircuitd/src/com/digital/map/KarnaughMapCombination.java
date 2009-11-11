/*
 * Created on Jul 4, 2007
 *
 *
 */
package com.digital.map;

import java.util.ArrayList;
import java.util.List;

import com.digital.logging.DigLogManager;
import com.digital.logging.DigLogger;
import com.digital.map.helper.PersistenceHelper;
import com.digital.math.Combinator;
import com.digital.math.Number;
import com.digital.math.exceptions.InvalidNumberException;
import com.digital.math.exceptions.InvalidUserInputException;

/**
 * @author  msahu
 *
 *
 */
public class KarnaughMapCombination 
{

	private static DigLogger logger = DigLogManager.getLogger(KarnaughMapCombination.class);
	private int i;
	private boolean generationStatus =false;
	
	public KarnaughMapCombination(final int i) 
	{
		if(i<0)
		{
			System.out.println("i is negative "+i);
			System.exit(1);
		}
		this.i=i;
	}
	
	public int getI(){return i;}
	
	public boolean isGenerated(){return generationStatus;}
	
	
	public int hashCode(){return i;}
	
	public boolean equals(Object herCombination)
	{
		KarnaughMapCombination myCombination=this;
		
		return herCombination == null || ! (herCombination instanceof KarnaughMapCombination)
					?
							false
							:
							myCombination.hashCode() != ((KarnaughMapCombination)herCombination).hashCode()
								?
										false
										:
										true	
					;			
				
	}
	
	public String toString()
	{
		return "kmc:-"+i;
	}
	
	public String getName(){return this.toString();}
	
	/**
	 * Heart of the entire program
	 * @param map
	 * @param i
	 * @throws InvalidUserInputException
	 * @throws InvalidNumberException
	 */
	public boolean generate() throws InvalidUserInputException, InvalidNumberException 
	{
		int [] map=new int [KarnaughMap.getN()];
		int y = (int)Math.pow(2,i);
		int x = KarnaughMap.getN() - i;  
		int z = (int)Math.pow(2,x);
		List result = Combinator.findCombinations(map,x);
		
		MinTermContainer mtc = new MinTermContainer();
		 
		for(int j=0;j<result.size();j++)
		{
				int []tmp = (int [])result.get(j);
				
				for(int l=0;l<z;l++)
					{
						int tmp1[] = processBinaryBits
										(
												(int [])tmp.clone()
												,
												Number.getBitsOf
														(
																l
																,
																x==0 ?1:x
														)
												
										);
						
						List tmpMinTerms = new ArrayList();
						
						for(int k=0;k<y;k++)
						{
						
							
							
							tmpMinTerms.add
										  (
													processSpecialBits
														  (
																	(int [])tmp1.clone()
																	,
																	
																	Number.getBitsOf
																			(
																					k
																					,
																					i==0 ?1:i
																			)
																	
																					
														  )
										  );
						}

						MinTerm mt = new MinTerm(tmpMinTerms, KarnaughMap.getN());
						//System.out.println(mt.showMinTerms());
						//mapMinTerms.add(mt);
						mtc.add(mt);
						
					}
		
		}
		long start = System.currentTimeMillis();
		generationStatus =PersistenceHelper.save(x,mtc);
		logger.debug("time spent writing "+PersistenceHelper.getFile(x) + "is "+(System.currentTimeMillis()-start)/1000 + "s");
		return generationStatus;
	}

	
	
	/**
	 * @param tmp
	 * @param bits
	 * @throws InvalidNumberException
	 */
	private  void process(int[] tmp, int[] bits , int valueToProcess) throws InvalidNumberException 
	{
		
		for(int i=0,j=0;i<tmp.length;i++)
		{
			if(tmp[i]==valueToProcess)
			{
				tmp[i]=bits[j];
				j++;
				if(j == tmp.length)
				{
					break;
				}
			}
		}
	
		
	}
	
	
	/**
	 * @param tmp
	 * @param bits
	 * @throws InvalidNumberException
	 */
	private  int [] processBinaryBits(int[] tmp, int[] bits ) throws InvalidNumberException 
	{
		process(tmp,bits,0);	
		return tmp;
	}
	

	/**
	 * @param tmp
	 * @param bits
	 * @throws InvalidNumberException
	 */
	private  Number processSpecialBits(int[] tmp, int[] bits) throws InvalidNumberException 
	{

		process(tmp,bits,-1);	
		return  new Number(tmp);

	}


}
