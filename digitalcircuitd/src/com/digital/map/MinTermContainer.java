/*
 * Created on Jul 6, 2007
 *
 * 
 */
package org.digital.map;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * @author msahu
 *
 * 
 */
public class MinTermContainer implements Externalizable 
{

	private List list = null;
	
	public MinTermContainer()
	{
	
		list = new ArrayList();
	}
	public boolean add(MinTerm mt)
	{
		return mt==null
					?
							false
							:
							list.add(mt)
					;		
		
		
	}
	
	public boolean isEmpty()
	{
		return list.size() == 0
					?
							true
							:
							false
					;		
	}
	public boolean contains(MinTerm mt)
	{
		return mt==null
				?	
						false
						:
						list.contains(mt)
				;		
	}
	public List getMinTerms(boolean b)
	{
		
		return b?list:getMinTerms();
	}
	public List getMinTerms()
	{
		
		List results = new ArrayList();
		
		for(int i=0;i<list.size();i++)
		{
			
			MinTerm mt = (MinTerm)list.get(i);
			
			System.out.println("mt ="+mt);
			//results.add(MinTerm.clone(mt));
		}
		return results;
	}
	
	

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
		int size =  in.readInt();	
		List list = new ArrayList();
		for(int i=0 ;i <size;i++)
		{
			
			MinTerm mt = (MinTerm)in.readObject();
			list.add(mt);
	
		}
		this.list=list;
		
	}

	public void writeExternal(ObjectOutput out) throws IOException 
	{
		out.writeInt(list.size());
		for(int i=0 ;i <list.size();i++)
		{
			MinTerm mt = (MinTerm)list.get(i);
			out.writeObject(mt);
			
		}
	}

}
