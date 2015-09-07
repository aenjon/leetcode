package cn.arrowcoder.training;

import java.util.*;

public class MinStack2 {
	private final Stack<Long> main = new Stack<Long>(); 
	private long min = 0L;
	
    public void push(int x) {
        main.push((long)x - (min = main.isEmpty() ? (long)x : min));
        if ((long)x < min) min = (long)x;
    }

    public void pop() {
        if (main.isEmpty()) return;
        if (main.peek().longValue() < 0l) min -= main.peek().longValue();
        main.pop();
    }

    public int top() {
        return main.peek().longValue() < 0l ? (int)min : (int)(main.peek().longValue() + min);
    }

    public int getMin() {
        return (int)min;
    }
	
}
