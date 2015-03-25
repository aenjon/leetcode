package cn.arrow.careercup;

public class SingleLinkedList {
	private Node head;
	
	public SingleLinkedList() {head = null;}
	
	public void insert (Object object){
		Node node = new Node(object);
		node.next = head;
		head = node;			
	}
	
	public Node head() {
		return head;
	}
	
	public void reverse(){
		if (head == null) return;
		if (head.next == null) return;
		
		Node next = head.next;
		Node cur = head;
		Node newhead = cur;
		newhead.next = null;
		
		while (next != null){
			if (cur != head)
				cur.next = newhead;
			newhead = cur;
			cur = next;
			next = next.next;			
		}
		
		cur.next = newhead;
		head = cur;
	}

}
