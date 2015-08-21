package cn.arrowcoder.training;

import java.util.*;

public class LeetStack {
	Queue<Integer> que;
	
	LeetStack() { que = new LinkedList<Integer>();}
    // Push element x onto stack.
    public void push(int x) {
        que.add(x);
        for (int i = 0; i<que.size()-1; i++)
        	que.add(que.poll());
    }

    // Removes the element on top of the stack.
    public void pop() {
        que.poll();
    }

    // Get the top element.
    public int top() {
    	if (!empty())
    		return que.peek().intValue();
    	else
    		return 0;
    }

    // Return whether the stack is empty.
    public boolean empty() {
       return que.isEmpty();
    }
	
}
