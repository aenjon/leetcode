package cn.arrow.careercup;

public class Stack {

	Node top;
	
	public Stack(){
		top = null;
	}
	
	public Object pop(){
		if (top != null){
			Object item = top.data;
			top = top.next;
			return item;
		}
		else
			return null;
	}
	
	public void push(Object item){
		if (item == null)
			return;
		Node node = new Node(item);
		node.next = top;
		top = node;
	}
}
