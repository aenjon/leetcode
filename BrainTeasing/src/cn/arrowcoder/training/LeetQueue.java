package cn.arrowcoder.training;

import java.util.*;

public class LeetQueue {
	private Stack<Integer> in, out;
	public LeetQueue () {in = new Stack<Integer>(); out = new Stack<Integer>();}
    // Push element x to the back of queue.
    public void push(int x) {
        if (out.empty())
            out.push(x);
        else
            in.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (!out.empty())
        	out.pop();
        if (out.empty() && !in.empty()){
        	while (!in.empty())
        		out.push(in.pop());
        }
        return;
    }

    // Get the front element.
    public int peek() {
        if (!out.empty())
        	return out.peek().intValue();
        else
        	return 0;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return out.empty();
    }
}
