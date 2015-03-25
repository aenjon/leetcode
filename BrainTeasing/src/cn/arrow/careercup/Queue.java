package cn.arrow.careercup;

public class Queue {
	Node head;
	Node tail;
	
	public Queue(){
		head = null;
		tail = null;
	}
	
	public void enqueue(Object data){
		
		if (data == null)
			return;
		
		Node newitem = new Node(data);
		
		if (tail != null)
			tail.next = newitem;
		tail = newitem;
		
		if (head == null)
			head = tail;
	}
	
	public Object dequeue(){
		Object data = null;
		if (head != null){
			data = head.data;
			head = head.next;
		}
		
		return data;
	}
	
	
}
