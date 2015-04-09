package cn.arrowcoder.training;

import java.util.HashMap;
import cn.arrow.brainteasing.ListNode;

/*
public class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
		next = null;
	}
}
*/

public class ArrowCode {
	/** 
	 * Problem No. 1
	 * Two Sum
	 */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return null;
        int[] result = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();        
        for (int i = 0; i<numbers.length; i++){
            if (hm.containsKey(target-numbers[i])){
                result[0] = hm.get(target-numbers[i])+1;
                result[1] = i+1;
                break;
            }else if (!hm.containsKey(numbers[i])){
                hm.put(numbers[i],i);
            }
        }
        return result;
    }
    
    /**
     * Problem No. 2
     * Add Two Numbers presented in LinkedList
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	int c = 0;
    	ListNode dummy = new ListNode(0);
    	ListNode pre = dummy;
    	while( l1 != null && l2 != null){
    		int curval = l1.val + l2.val + c;
    		c = curval / 10;
    		ListNode cur = new ListNode(curval % 10);
    		pre.next = cur;
    		pre = pre.next;
    		l1 = l1.next;
    		l2 = l2.next;
    	}
    	ListNode rest = l1 == null ? l2 : l1;
    	while (rest != null){
    		int curval = rest.val + c;
    		c = curval / 10;
    		ListNode cur = new ListNode(curval % 10);
    		pre.next = cur;
    		pre = pre.next;
    		rest = rest.next;
    	}
    	if ( c > 0){
    		ListNode cur = new ListNode(c);
    		pre.next = cur;
    	}
    	return dummy.next;
    }
    
    /**
     * Problem No. 3
     * Longest Substring Without Repeating Characters
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int d = 0, maxlen = 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        char curchar;
        for (int i=0; i<s.length(); i++){
            curchar = s.charAt(i);
            if ( !hm.containsKey(curchar) || hm.get(curchar) < i - d)
                d++;
            else
                d = i - hm.get(curchar);
            hm.put(curchar, i);
            maxlen = d > maxlen ? d : maxlen;    
        }
        return maxlen;
     }
    
    /**
     * Problem No. 4
     * Median of two sorted arrays
     */
    /* See LeetCode CPP */
    
    /**
     * Problem No. 5
     * Longest Palindromic Substring 
     */
    public String longestPalindrome(String s) {
    	if (s == null || s.isEmpty()) return s;
    	int start = 0, maxlen = 1;
    	int low, high;
    	for(int i=1; i<s.length(); i++){
    		/*
    		 *  Start searching from the middle of the substring.
    		 *  If the pair chars are same, move the low end backward and move the high end forward
    		 *  Until it reaches the end of the string or the pair are different
    		 *  
    		 *  The first "while" loop tests the case of the substring's length is an even number, like "abba".
    		 *  The second "while" loop tests the case of the substring's length is an odd number, like "abcba".
    		 */    		
    		low = i-1;
    		high = i;
    		while (low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
    			if (high - low + 1 > maxlen){
    				start = low;
    				maxlen = high-low+1;
    			}
    			low--;
    			high++;
    		}
    		low = i-1;
    		high = i+1;
    		while (low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
    			if (high-low+1 > maxlen){
    				start = low;
    				maxlen = high-low+1;
    			}
    			low--;
    			high++;
    		}
    	}
    	return s.substring(start,start+maxlen);
    }
    /**
     * Manacher’s Algorithm
     * @param s
     * @return
     */
    public String longestPalindromeN(String s) {
    	//if (s == null || s.length() <= 1) return s;
    	String T = preProcess(s);
    	int n = T.length();
    	int[] P = new int[n];
    	int C = 0, R = 0;
    	for (int i = 1; i < n-1; i++){
    		int i_mirror = 2 * C - i; // equals to i' = C - (i - C)
    		
    		/*
    		 * If R > i, the current i is within the right range R,
    		 * then P[i] is either the mirror i' with the center C,
    		 * or R-i, choose the less one.
    		 * Note, if i_mirror is a center of a palindromic substring,
    		 * due to the symmetric property, i must be a center of palindromic substring too.
    		 * The range of the palindromic substring with the center i may be different from the one 
    		 * centered around i' because of the restriction of R of current C.
    		 * So choose the less value as the initial value, then expand it.
    		 * 
    		 * If i is out of the range R, set the initial value of 0, and start expand it.
    		 */
    		P[i] = (R > i) ? min(R-i, P[i_mirror]) : 0;
    		
    		// Expand palindrome centered at i
    		while (T.charAt(i + 1 + P[i]) == T.charAt(i - 1 - P[i]))
    			P[i]++;
    		
    		/*
    		 * If palindrome centered at i expand past R,
    		 * adjust center based on expanded palindrome.
    		 */
    		if (i + P[i] > R){
    			C = i;
    			R = i + P[i];
    		}    		
    	}
		int maxlen = 0, centerindex = 0;
		for(int i=1;i<n-1;i++){
			if (P[i] > maxlen){
				maxlen = P[i];
				centerindex = i;
			}
		}
		System.out.println("T is " + T);
		System.out.println("centerindex is " + centerindex);
		System.out.println("maxlen is " + maxlen);
		int start = (centerindex-1-maxlen)/2;
		return s.substring(start, start+maxlen);
    }
    
    /**
     * Aux. function for Manacher algorithm. Insert "^#$" into the original string.
     * @param t
     * @return
     */
    private String preProcess(String t){
    	int n = t.length();
    	if (n==0) return "^$";
    	StringBuffer strb = new StringBuffer();
    	strb.append("^");
    	for(int i=0; i<n; i++){
    		strb.append("#");
    		strb.append(t.charAt(i));
    	}
    	strb.append("#$");
    	return strb.toString();
    }
    
    /**
     * Problem #6: ZigZag Conversion 
     */
    public String convert(String s, int nRows) {
    	if (s == null || nRows <= 1) return s;
    	StringBuffer ret = new StringBuffer();
    	int step = 2 * nRows - 2;
    	int len = s.length();
    	
    	for (int i=0; i<nRows; i++){
    		if (i==0 || i == nRows-1){
    			int curindex = i;
    			while (curindex < len){
    				ret.append(s.charAt(curindex));
    				curindex += step;
    			}
    		}
    		else{
        		int lindex = i;
    			int rindex = 2*nRows-i-2;
        		while(true){
        			if (lindex < len){
        				ret.append(s.charAt(lindex));
            			lindex += step;
        			}
        			else
        				break;
        			if (rindex < len){
        				ret.append(s.charAt(rindex));
            			rindex += step;
        			}
        			else
        				break;
        		}    			
    		}
    	}
    	return ret.toString();
    }

    public String convert2(String s, int nRows) {
    	if (s == null || s.isEmpty() || nRows <=1) return s;
    	int step = 2*nRows-2;
    	StringBuffer[] buff = new StringBuffer[nRows];
    	for(int i=0; i<nRows;i++)
    		buff[i] = new StringBuffer();
    	for(int i=0; i<s.length(); i++){
    		int pos = i % step;
    		if (pos < nRows)
    			buff[pos].append(s.charAt(i));
    		else
    			buff[step-pos].append(s.charAt(i));
    	}    	
    	for (int i=1; i< nRows; i++)
    		buff[0].append(buff[i].toString());
    	return buff[0].toString();
    	
    }
    
    /**
     * Problem #7
     * Reverse Integer 
     */
    public int reverse(int x) {
        int ret = 0;
        boolean positive = x > 0 ? true : false;
        do{
        	if ( positive && (ret << 1 < 0 || ret << 2 < 0 || ret << 3 < 0))
        		return 0;
        	if ( !positive && (ret << 1 > 0 || ret << 2 > 0 || ret << 3 > 0))
        		return 0;
        	ret = ret * 10 + x % 10;
        	if (positive && ret < 0)
        		return 0;
        	if (!positive && ret > 0)
        		return 0;
        	x /= 10;        	
        } while (x != 0);
        return ret;
    }
    
    public int reverse2(int x){
    	long ret = 0;
    	long number = (long)x;
    	do{
    		ret = ret * 10 + number % 10;
    		if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE)
    			return 0;
    		number /= 10;
    	}while (number != 0);
    	return (int)ret;
    }
    
    /**
     * Problem #8
     * String to Integer (atoi) 
     */
    public int atoi(String str) {
    	long ret = 0;
    	if (str == null || str.isEmpty())
    		return 0;
    	int len = str.length();
    	int i = 0;
    	boolean sign=true;
    	while (i < len && (str.charAt(i) == ' ' || str.charAt(i) == '\t' 
    			|| str.charAt(i) == '\n' ))
    		i++;
    	if (i < len)
    		if (str.charAt(i) == '-'){
    			sign = false;
    			i++;
    		} else if (str.charAt(i) == '+') {
    			sign = true;
    			i++;
    		}else{
    			sign = true;
    		}    	
    	while (i < len){
    		int curdigit = str.charAt(i) - 48;
    		if (curdigit < 0 || curdigit > 9)
    			break;
    		ret = ret * 10 + curdigit;
    		if (sign && ret >= Integer.MAX_VALUE){
    			return Integer.MAX_VALUE;
    		}else if (!sign && ret >= (long)Integer.MAX_VALUE + 1)
    			return Integer.MIN_VALUE;
    		i++;
    	}    	
    	return sign ? (int)ret : -(int)ret;
    }
    
    /**
     * Problem #9
     * Palindrome Number 
     */
    public boolean isPalindrome(int x) {
    	if (x<0) return false;
    	int rev = 0;
    	int dx = x;
    	while (dx >= 10){
    		rev = rev * 10 + dx % 10;
    		dx /= 10;
    	}
    	return rev == x / 10 && dx == x % 10;        
    }

    public boolean isPalindrome2(int x) {
    	if (x<0 || (x!=0 && x%10 == 0)) return false;
    	int rev = 0;
    	while (x > rev){
    		rev = rev * 10 + x % 10;
    		x /= 10;
    	}
    	return rev == x || x == rev/10;        
    }

    /**
     * Problem #10
     * Regular Expression Matching 
     */
    public boolean isMatch(String s, String p) {
    	/**
    	 * Basic idea: dynamic programming
    	 * Create a boolean array b[i][j]. The value of b[i+1][j+1] means if the sub-string p[0..j] matches the sub-string s[0..i]
    	 * Start with b[0][0], fill the special case of empty strings 
    	 */
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean b[][] = new boolean[m+1][n+1];
        
        // If both strings are empty, the result is true
        b[0][0] = true;
        
        // if s is not empty but p is empty, then the result is not match
        // i.e. b[i][0] = false;
        for(int i=0; i<m; i++)
            b[i+1][0] = false;
        
        // p[j] matches an empty (sub)string iff
        // p[j] is '*' and p[j-2] matches. In this case, p[j-1] could be any char
        // i.e. b[0][j-1] is true and p[j] is '*'
        for (int j = 0; j < n; j++)
            b[0][j+1] = j > 0 && b[0][j-1] && p.charAt(j) == '*';
            
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (p.charAt(j) != '*')
                    b[i+1][j+1] = b[i][j] && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                else
                    b[i+1][j+1] = j > 0 && b[i+1][j-1] // p[j-2] matches and there is zero p[j-1] in s
                    || b[i+1][j] // p[j-1] matches and there is at least one p[j-1] in s. For example "a" and "a*"
                    || j>0 && b[i][j+1] && (p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i));
                        // p[j] matches the substring of s till s[i-1] and p[j-1] is either '.' or equals to s[i]
                        // for example, for the strings "aa" and "a*", "a*" matches "a", and then the second 'a' in s
                        // matches the 'a' in p 
        return b[m][n];                   	
    }

    
    /**
     * Problem No. 189
     * Rotate Array
     */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0)
            return;
        int[] buff;
        int i;
        int len = nums.length;
        k = k % len;
        if (k <= len/2){
            buff = new int[k];
            for (i=0; i<k; i++)
                buff[i] = nums[i];
            for (i=0; i<k; i++)
                nums[i] = nums[len-k+i];
            for (i=0; i<len-2*k; i++)
                nums[len-i-1] = nums[len-i-1-k];
            for (i=0; i<k; i++)
                nums[k+i] = buff[i];        
        }else{
            buff = new int[len-k];
            for(i=0; i<len-k;i++)
                buff[i] = nums[i+k];
            for (i=0; i<len-k; i++)
                nums[k+i] = nums[i];
            for (i=0; i<2*k-len; i++)
                nums[i] = nums[i+len-k];
            for (i=0; i<len-k;i++)
                nums[2*k-len+i] = buff[i];
        }    
    }    
    
    /**
     * Rotate in-place with O(1) space and O(n) time
     * After k % n shifts, the first n - k % n (we call it range) elements will be moved to
     * the last #range slots of the array. Swap the elements in the two ranges first.
     * Now n - k % n elements are in the right position after shifting.
     * Also, for the first k % n elements in the new array, we need rotate k - range % k times
     * Then start solve the sub problems in the first k % n element.
     */
    public void rotate2(int[] nums, int k) {
    	if (nums == null || nums.length == 0) return;
    	int ctr = 0;
    	int n = nums.length;
    	while ( (k %= n) > 0 && n > 0){
    		int range = n - k;
    		for (int i=1; i<= range; i++){
    			int val = nums[n-i];
    			nums[n-i] = nums[n-i-k];
    			nums[n-i-k] = val;
    			ctr++;
    		}
    		n = k;
    		k = n - (range % k);
    	}
    	System.out.println("move # is " + ctr);
    	return;
    }
    
    /**
     * Problem No. 190
     * Reverse Bits
     */
    
    public int reverseBits(int n) {
    	int result = 0;
    	for (int i = 0; i < 32; i++){
    		result = result + (n & 1);
    		if (i < 31) result = result << 1;
    		n = n >> 1;
    	}
    	return result;
    }
    /**
     * Problem No. 191
     * Number of 1 Bits
     */
    public int hammingWeight(int n) {
    	int len = 0;
    	for (int i = 0; i < 32; i++){
    		if ((n & 1) == 1) len++;
    		n = n >> 1;
    	}
    	return len;
    }
    
    /**
     * Simple utilities
     */
    int min (int a, int b){
    	return (a > b) ? b : a;
    }
}
