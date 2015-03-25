package cn.arrow.careercup;

import java.util.Stack;

class NodeWithMin extends Node{
	int min;
	
	public NodeWithMin(int key, int min){
		super(key);
		this.min = min;
	}
}

public class MinStack extends Stack<NodeWithMin>{
	
	private static final long serialVersionUID = 8069538440487048638L;

	public int min(){
		if (this.isEmpty())
			return Integer.MAX_VALUE;
		else
			return peek().min;
	}
		
	public void push(int key){
		int newMin = Math.min(key, min());
		super.push(new NodeWithMin(key, newMin));
	}

}
