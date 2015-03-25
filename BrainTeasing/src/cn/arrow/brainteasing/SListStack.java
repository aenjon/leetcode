package cn.arrow.brainteasing;

public class SListStack extends Stack{
	private ListNode stacklist;
	
	@Override
	public void Push(int x) throws StackOverflowsException{
		if (stacklist == null)
			stacklist = new ListNode(x);
		else{
			ListNode newitem = new ListNode(x);
			newitem.next = stacklist;
			stacklist = newitem;
		}
	}
	
	@Override
	public int Pop() throws StackUnderflowsException{
		if (stacklist == null)
			throw new StackUnderflowsException("Single Linked List Stack Underflows");
		else{
			int keyvalue = stacklist.val;
			stacklist = stacklist.next;
			return keyvalue;
		}
	}

}
