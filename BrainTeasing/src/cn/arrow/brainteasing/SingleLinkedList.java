package cn.arrow.brainteasing;

import java.util.HashMap;

public class SingleLinkedList {
	public ListNode Nil;
	private int size;
	
	public SingleLinkedList(){
		Nil = new ListNode();
		Nil.next = Nil;
	}

	public void Insert(int x){
		ListNode newnode = new ListNode(x);
		newnode.next = Nil.next;
		Nil.next = newnode;
		++size;
	}
	
	public ListNode Search(int x){
		Nil.val = x;
		ListNode snode = Nil.next;
		while (snode.val != x)
			snode = snode.next;
		
		Nil.val = Integer.MIN_VALUE;
		
		if (snode == Nil)
			return null;
		else
			return snode;
	}
	
	public void Delelte(int x){
		//Nil.key = x;
		ListNode dnode = Nil;
		while (dnode.next != Nil)
			if (dnode.next.val == x)
				dnode.next = dnode.next.next;
			else
				dnode = dnode.next;
		
		--size;
	}
	
	public void Reverse(){
		ListNode prev = Nil;
		ListNode node = Nil.next;
		ListNode next;
		
		while(node != Nil){
			next = node.next;
			node.next = prev;
			prev = node;
			node = next;
		}
		
		Nil.next = prev;
	}
	
	public void removeDuplicate1(){
		HashMap<Integer, Boolean> hm = new HashMap<Integer, Boolean>();
		ListNode prev = Nil;
		ListNode node = Nil.next;
		
		while (node != Nil){
			if (hm.containsKey(node.val)){
				prev.next = node.next;	
				--size;
			}
			else{
				hm.put(node.val, true);
				prev = node;				
			}
			node = node.next;
		}
	}
	
	public void removeDuplicate2(){
		ListNode prev = Nil;
		ListNode node = Nil.next;
		
		while (node != Nil){
			ListNode snode = this.Search(node.val);
			if (snode != node){
				prev.next = node.next;
				--size;
			}
			else{
				prev = node;
			}
			node = node.next;
		}
	}
	
	public ListNode searchNthNode(int n){
		ListNode p1 = Nil.next;
		ListNode p2 = Nil.next;
		if (n > size || n <= 0)
			return null;
		
		for (int i=0; i< n-1; ++i)
			p1 = p1.next;
		
		while(p1.next != Nil){
			p1 = p1.next;
			p2 = p2.next;
		}
		return p2;
	}
	
	public ListNode addNodes(ListNode n1, ListNode n2){
		ListNode result = null;
		ListNode add1 = n1;
		ListNode add2 = n2;
		ListNode add3;
		int carry = 0;
		
		while (add1 != null && add2 != null){
			ListNode newnode = new ListNode();
			newnode.val = (add1.val + add2.val + carry) % 10;
			carry = (add1.val + add2.val + carry) / 10;
			
			if (result != null){
				newnode.next = result;
				result = newnode;
			}
			else
				result = newnode;
			
			add1 = add1.next;
			add2 = add2.next;
		}
		
		if (add1 != null)
			add3 = add1;
		else if (add2 != null)
			add3 = add2;
		else
			return result;
		
		do {
			ListNode newnode = new ListNode();
			newnode.val = (add3.val + carry) % 10;
			newnode.val = (add3.val + carry) / 10;
			if (result != null){
				newnode.next = result;
				result = newnode;
			}
			else
				result = newnode;
				add3 = add3.next;
		} while (add3 != null);
		return result;
	}
}
