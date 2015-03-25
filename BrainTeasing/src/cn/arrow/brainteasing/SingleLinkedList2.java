package cn.arrow.brainteasing;

public class SingleLinkedList2 {
	public ListNode head;
	
	public SingleLinkedList2(){
		head = null;
	}
	
	public SingleLinkedList2(ListNode node){
		head = node;
	}
	
	public SingleLinkedList2(int x){
		head = new ListNode(x);		
	}
	
	public ListNode Insert(ListNode n){
		if (n != null){
			n.next = head;
			head = n;
		}
		return head;		
	}
	
	public ListNode Insert(int x){
		ListNode n = new ListNode (x);
		return Insert(n);
	}
	
	public void print(){
		ListNode n = head;
		while (n!=null){
			System.out.print(n.val);
			n = n.next;
		}
		
		System.out.println();
	}
	
	public ListNode addNodes(SingleLinkedList2 l1, SingleLinkedList2 l2){
		ListNode result = null;
		ListNode add1 = l1.head;
		ListNode add2 = l2.head;
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
			carry = (add3.val + carry) / 10;
			if (result != null){
				newnode.next = result;
				result = newnode;
			}
			else
				result = newnode;
				add3 = add3.next;
		} while (add3 != null);
		
		if (carry > 0){
			ListNode newnode = new ListNode(carry);
			newnode.next = result;
			result = newnode;
		}
		
		return result;
	}

}
