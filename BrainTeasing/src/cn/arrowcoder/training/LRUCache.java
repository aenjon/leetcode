package cn.arrowcoder.training;

import java.util.HashMap;


public class LRUCache {
	
	class DListNode {	
		int key, val;
		DListNode prev;
		DListNode next;
		DListNode (int keyv, int valv) {key = keyv; val = valv;}
	}	
	 
	  private int cap;
	  private int counter;
	  private DListNode head, tail;
	  private HashMap<Integer, DListNode> index;

	  public LRUCache(int capacity) {
		  cap = capacity;
	      counter = 0;
	      index = new HashMap<Integer, DListNode>();
	      head = new DListNode(0,999);
	      tail = new DListNode(0,-999);
	      head.prev = null; head.next = tail;
	      tail.prev = head; tail.prev = head;	        
	  }
	    
	  public int get(int key) {
		  DListNode dnode = index.get(key);
		  if (dnode == null)
			  return -1;
		  moveToHead(dnode);
		  return dnode.val;
	  }
	    
	  public void set(int key, int value) {
		  DListNode cur; 
		  if ((cur = index.get(key)) == null){
			  cur = new DListNode(key, value);
			  counter++;
			  index.put(key, cur);
		  } else {
			  cur.val = value;
		  }
		  /* Add the newly accessed node to the head of list*/
		  if (cur.next == null){
			  head.next.prev = cur;
			  cur.next = head.next;
			  head.next = cur;
			  cur.prev = head;
		  } else {
			  moveToHead(cur);
		  }
		  if (counter > cap){
			  index.remove(tail.prev.key);
			  tail.prev.prev.next = tail;
			  tail.prev = tail.prev.prev;
			  counter--;
		  }
		  return;
	  }
	  
	  public void moveToHead(DListNode dnode){
		  /* Remove the latest accessed node from the list */
	      dnode.prev.next = dnode.next;
	      dnode.next.prev = dnode.prev;
	      /* Move the the latest accessed to the head of the list*/
	      head.next.prev = dnode;	      
	      dnode.next = head.next;
	      head.next = dnode;
	      dnode.prev = head;
	      return;
	  }

	  public void print(){
		  System.out.print("Cache layout: ");
		  DListNode cur = head.next;
		  while (cur != tail){
			  System.out.print("(" + cur.key + "," + cur.val + ")->");
			  cur = cur.next;
		  }
		  System.out.println("null");
	  }
}
