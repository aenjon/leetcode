package cn.arrow.careercup;

import java.util.EmptyStackException;
import java.util.Stack;

public class MinStack2 extends Stack<Integer>{

	private static final long serialVersionUID = -447784406742568534L;
	private Stack<Integer> minstack;
	
	public MinStack2(){
		minstack = new Stack<Integer>();
	}
	
	public Integer min(){
		if (minstack.isEmpty())
			return Integer.MAX_VALUE;
		else
			return minstack.peek();
	}
	
	public void push (int value){
		if (value < min())
			minstack.push(value);
		super.push(value);
	}
	
	public Integer pop(){
		try{
			Integer value = super.pop();
			if (value == min())
				minstack.pop();
			return value;
		}
		catch(EmptyStackException e){
			e.printStackTrace();
			return null;
		}
	}

}
