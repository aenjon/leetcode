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
	
	public String longestPalindrome(String s) {
	    if (s == null || s.isEmpty())
	    	return s;
	    int start = 0, maxlen = 1;
	    int low, high;
	    for(int i=1; i < s.length(); i++){
	        low = i - 1;
	        high = i;
	        while (low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
	            if (high - low + 1 > maxlen){
	                start = low;
	                maxlen = high-low+1;
	            }
	            low--; high++;            
	        }
	        low = i - 1;
	        high = i + 1;
	        while (low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
	            if (high - low + 1 > maxlen){
	                start = low;
	                maxlen = high-low+1;
	            }
	            low--; high++;            
	        }        
	    }
	    return s.substring(start, start+maxlen);
	}	

	public String convert(String s, int numRows) {
	    if (s == null || s.isEmpty() || numRows <= 1) return s;
	    int step = 2*numRows - 2;
	    int len = s.length();
	    StringBuffer buf = new StringBuffer();
	    for(int i=0; i<numRows; i++){
	        if (i==0 || i==numRows-1){
	            int curindex = i;
	            while (curindex < len){
	                buf.append(s.charAt(curindex));
	                curindex += step;
	            }
	        }
	        else {
	            int lindex = i;
	            int rindex = 2*numRows -i - 2;
	            while(true){
	                if (lindex < len){
	                    buf.append(s.charAt(lindex));
	                    lindex += step;
	                }
	                else
	                    break;
	                if (rindex < len){
	                    buf.append(s.charAt(rindex));
	                    rindex += step;
	                }
	                else
	                    break;
	            }
	        }
	    }
	    return buf.toString();
	}

	public int reverse(int x) {
	    long ret = 0;
	    long num = (long)x;
	    do{
	        ret = ret * 10 + num % 10;
	        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
	            return 0;
	        num /= 10;
	    }while (num != 0);
	    return (int)ret;
	}	
	
	public int myAtoi(String str) {
	    if (str == null || str.isEmpty()) return 0;
	    long ret = 0;
	    int len = str.length(), i = 0;
	    boolean sign = true;
	    while (i<len && str.charAt(i) == ' ' && str.charAt(i) == '\t' && str.charAt(i) == '\r'
	        && str.charAt(i) == '\n')
	        i++;
	    if (i<len)
	        if (str.charAt(i) == '+'){
	            sign = true;
	            i++;
	        }else if (str.charAt(i) == '-'){
	            sign = false;
	            i++;
	        }else{
	            sign = true;
	        }
	    while(i<len){
	        int curdigit = str.charAt(i) - 48;
	        if (curdigit < 0 || curdigit > 9)
	            break;
	        ret = ret * 10 + curdigit;
	        if (sign && ret > Integer.MAX_VALUE)
	            return Integer.MAX_VALUE;
	        else if (!sign && ret >= (long)Integer.MAX_VALUE + 1)
	            return Integer.MIN_VALUE;
	        i++;
	    }
	    return sign ? (int)ret : - (int)ret;    
	}	
	
}
