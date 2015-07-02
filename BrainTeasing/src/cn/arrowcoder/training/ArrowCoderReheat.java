package cn.arrowcoder.training;

import java.util.HashMap;

import cn.arrow.brainteasing.ListNode;

public class ArrowCoderReheat {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	     ListNode dummy = new ListNode(0);
	     ListNode n = dummy;
	     int sum = 0;
	     while (l1 != null || l2 != null){
	         sum /= 10;
	         if (l1 != null){
	             sum += l1.val;
	             l1 = l1.next;
	         }
	         if (l2 != null){
	             sum += l2.val;
	             l2 = l2.next;
	         }
	         n.next = new ListNode(sum % 10);
	         n = n.next;
	     }
	     if (sum / 10 > 0)
	         n.next = new ListNode(1);
	      return dummy.next;
	 }
	
	public int lengthOfLongestSubstring(String s){
	    if (s == null || s.isEmpty()) return 0;
	    int d = 0, maxlen = 0;
	    HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
	    for(int i=0; i<s.length(); i++){
	        char curchar = s.charAt(i);
	        if (!hm.containsKey(curchar) || hm.get(curchar) < i - d)
	            d++;
	        else
	            d = i - hm.get(curchar);
	        hm.put(curchar, i);
	        maxlen = maxlen > d ? maxlen : d;
	    }
	    return maxlen;
	}
	
}
