package cn.arrow.careercup;
import java.util.Stack;

public class QueueBy2Stacks<T> {
	private Stack<T> s1;
	private Stack<T> s2;
	
	public QueueBy2Stacks(){
		s1 = new Stack<T>();
		s2 = new Stack<T>();
	}
	
	public int size(){ return s1.size() + s2.size();}

	public void enqueue(T value){ s1.push(value);}
	
	public T dequeue(){
		if (!s2.isEmpty())
			return s2.pop();
		while(!s1.isEmpty())
			s2.push(s1.pop());
		return s2.pop();
	}
}
