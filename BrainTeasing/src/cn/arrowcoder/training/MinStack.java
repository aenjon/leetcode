package cn.arrowcoder.training;

import java.util.Stack;

public class MinStack {
	Stack<Integer> main;
	Stack<Integer> min;
	
	public MinStack(){
		main = new Stack<Integer>();
		min = new Stack<Integer>();
	}

    public void push(int x) {
    	if (min.isEmpty() || x <= getMin()) min.push(x);
    	main.push(x);
    	return;
    }

    public void pop() {
    	if (main.isEmpty())
    		return;
    	if (main.peek().intValue() == getMin())
    		min.pop();
    	main.pop();
    }

    public int top() {
    	if (!main.isEmpty())
    		return main.peek().intValue();
    	else
    		return Integer.MIN_VALUE;
    }

    public int getMin() {
        if (!min.isEmpty())
        	return min.peek().intValue();
        else
        	return Integer.MIN_VALUE;
    }
	
}
