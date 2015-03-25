package cn.arrow.brainteasing;

public class Node {
	public Node next;
	public int key;
	
	public Node(){
		next = null;
		key = Integer.MIN_VALUE;
	}
	
	public Node(int x){
		next = null;
		key = x;
	}

}
