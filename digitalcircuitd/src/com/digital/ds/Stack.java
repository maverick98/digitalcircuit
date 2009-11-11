/*
 * Created on Jun 27, 2007
 *
 * 
 */
package com.digital.ds;

import java.util.LinkedList;

/**
 * @author msahu
 * 
 * 
 */
public class Stack 
{

	protected LinkedList data;

	public Stack() {data = new LinkedList();}

	public void push(Object pushme) {data.add(pushme);}
	public Object pop(){return data.removeLast();}
	public Object top()
	{
		if (data.size() != 0) {
			return data.getLast();
		} else {
			return null;
		}
	}

	public void viewStack()
	{
		final int size = data.size();
		int i = 0;
		while (i < size) {
			System.out.print(data.get(i++));
		}

	}


	

}
