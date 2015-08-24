package cn.arrowcoder.training;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

import cn.arrow.brainteasing.ListNode;
import cn.arrow.brainteasing.TeasingUtil;

/*
class ListNode{
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
		next = null;
	}
}*/


class RandomListNode {
    int label;
    RandomListNode next, random;
    RandomListNode (int x) {label = x;}    
}

public class ArrowCode {
	
    static int counter4sum = 0;
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
     * Go through the string once, calculate the distance between any two duplicated chars
     * Update the max distance in the end
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        int d = 0, maxlen = 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        char curchar;
        for (int i=0; i<s.length(); i++){
            curchar = s.charAt(i);
            /* The char never occurs or it occurs before the current calculated distance */
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

    /**
     * Calculate the half-reversed number, and compare the half-reversed number with 
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
    	// Negative integer and the integers ending with 0 but not equal to 0 do not match
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
        // Note: "*" is an invalid regular expression string. "*" must follow another character
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
     * Problem #11
     * Container With Most Water
     * Basic idea: two points
     * The area is determined by two bars and x-axis, and its value is the production of the height of 
     * the short bar and the distance between the two bars.
     * So, we are searching such two bars.
     * Set two points, the first and the last bar (height),
     * Calculate the area between the two points, then move the two points forward or backward.
     * We always move the shorter bar because if we move the taller bar, we must get a smaller area,
     * Move the two bars and record the maximum value, until they come across. 
     */
    public int maxArea(int[] height) {
        if (height == null || height.length ==0) return 0;
        int i = 0, j = height.length - 1;
        int max = 0;
        while (i<j){
            max = Math.max(max, (j-i) * Math.min(height[i], height[j]));
            if (height[i] < height[j])
                ++i;
            else
                --j;
        }
        return max;
    }
    
    /**
     * Problem #12
     * Integer to Roman 
     */
    String intToRoman(int num) {
        String[] romantable = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX",
                             "", "X", "XX", "XXX", "LX", "L", "LX", "LXX", "LXXX", "XC",
                             "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM",
                             "", "M", "MM", "MMM"};
        return romantable[num/1000+30] + romantable[(num/100)%10+20] + romantable[(num/10)%10+10] + romantable[num%10]; 
    }
    /**
	 * Problem #13
     * Roman to Integer
     * Approach 1: Add all numbers first, search the pattern of 4, 9, 40, 90, 400, 900, etc and minus the extra
     * Pros: Easy to understand;
     * Cons: Need search the string multiple times
     */
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) return 0;
        HashMap<Character, Integer> romantable = new HashMap<Character, Integer>();
        romantable.put('M', 1000);
        romantable.put('D', 500);
        romantable.put('C', 100);
        romantable.put('L', 50);
        romantable.put('X', 10);
        romantable.put('V', 5);
        romantable.put('I', 1);
        
        int ret = 0;
        for(int i=0; i<s.length(); ++i){
            char c = s.charAt(i);
            Integer value = romantable.get(Character.valueOf(c));
            if (value != null)
                ret += value.intValue();
            else
                return 0;
        }
        if (s.indexOf("CM") != -1)
            ret -= 200;
        if (s.indexOf("CD") != -1)
            ret -= 200;
        if (s.indexOf("XC") != -1)
            ret -= 20;
        if (s.indexOf("XL") != -1)
            ret -= 20;
        if (s.indexOf("IX") != -1)
            ret -= 2;
        if (s.indexOf("IV") != -1)
            ret -= 2;
            
        return ret;
    }
    /**
     * Approach 2: compare two neighboring letters, if the left one is smaller than the right one,
     * 4s/9s pattern occurs. add or minus the left one accordingly.
     * Pros: just one time. 
     */
    public int romanToInt2(String s) {
        if (s == null || s.isEmpty()) return 0;
        HashMap<Character, Integer> romantable = new HashMap<Character, Integer>();
        romantable.put('M', 1000);
        romantable.put('D', 500);
        romantable.put('C', 100);
        romantable.put('L', 50);
        romantable.put('X', 10);
        romantable.put('V', 5);
        romantable.put('I', 1);
        
        int ret = 0;
        Integer value = romantable.get(s.charAt(s.length()-1));
        if (value != null)
            ret = value.intValue();
        else
            return 0;
        for(int i=s.length()-2; i>=0; --i){
            Integer lv = romantable.get(s.charAt(i));
            Integer rv = romantable.get(s.charAt(i+1));
            if (lv == null || rv == null)
            	return 0;
            if (lv.intValue() < rv.intValue())
            	ret -= lv.intValue();
            else
            	ret += lv.intValue();
        }
        return ret;
    }
    
    /**
     * Problem #14
     * Longest Common Prefix 
     * String[] strs= {"abcf", "abced", "abca"}, the longest common prefix is "abc"
     * O(n^2)
     */
    public String longestCommonPrefix(String[] strs) {
    	if (strs == null || strs.length == 0) return "";
    	for (int prefixlen = 0; prefixlen < strs[0].length(); prefixlen++){
        	char c = strs[0].charAt(prefixlen);
    		for(int i=1; i<strs.length; ++i){
    			if (prefixlen >= strs[i].length() || c != strs[i].charAt(prefixlen))
    				return strs[i].substring(0,prefixlen);
    		}
    	}
    	return strs[0];
    }

    /**
     * Problem #15
     * 3Sum  
     */

    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (num == null || num.length < 3) return ret;
        Arrays.sort(num);
        HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
        // Keep the index of all numbers
        for(int i=0; i<num.length; ++i)
            hm.put(num[i],i);
        for(int i=0; i<num.length; ++i){
            if (i>0 && num[i] == num[i-1])
            	// First step to avoid duplication
            	// Same numbers can only be used forward, cannot be used backward
            	continue;
            for (int j = i+1; j<num.length; ++j){
                if ( j > i+1 && num[j] == num[j-1])
                	// Avoid duplication again
                    continue;
                if (hm.containsKey(0-num[i]-num[j])){
                	// Avoid duplication again: the third item can only be "forward"
                    if (hm.get(0-num[i]-num[j]) > j){
                        ArrayList<Integer> item = new ArrayList<Integer>();
                        item.add(num[i]);
                        item.add(num[j]);
                        item.add(0-num[i]-num[j]);
                        ret.add(item);
                    }
                }
            }
        }
        return ret;
    }
    /*Appoach 2: Two points */
    public List<List<Integer>> threeSum2(int[] num) {
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	if (num == null || num.length < 3) return ret;
    	Arrays.sort(num);
    	for(int i=0; i<num.length; ++i){
    		if (i>0 && num[i] == num[i-1])
    			continue;
    		int twosum = 0 - num[i];
    		int low = i+1, high = num.length - 1;
    		while(low < high){
    			if (num[low] + num[high] == twosum){
    				ret.add(Arrays.asList(num[i],num[low],num[high]));
                    int lv = num[low], hv = num[high];
                    while (low < high && lv == num[low]) ++low;
                    while (low < high && hv == num[high]) --high;
    			}else if (num[low]+num[high] > twosum){
    				high--;
    			}else{
    				low++;
    			}
    		}
    	}
    	return ret;
    }
    
    /**
     * Problem #16
     * 3Sum Closest
     */
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3) return 0;
        Arrays.sort(num);
        int closest = target-num[0]-num[1]-num[2];
        int ret = num[0] + num[1] + num[2];
        for(int i=0; i<num.length; ++i){
            if (i>0 && num[i] == num[i-1])
                continue;
            int low = i + 1;
            int high = num.length-1;
            while (low < high){
                int cursum = num[i]+num[low]+num[high]; 
                if ( cursum == target)
                    return target;
                if (Math.abs(target-cursum) < Math.abs(closest)){
                    ret = num[i]+num[low]+num[high];
                    closest = target-cursum;
                    int lv = num[low], hv = num[high];
                    while (low < high && lv == num[low]) ++low;
                    while (low < high && hv == num[high]) --high;
                }
                else if (cursum > target)
                    --high;
                else
                    ++low;
            }
        }
        return ret;        
    }    

    /**
     * Problem No. 17
     * Letter Combinations of a Phone Number 
     */
    public List<String> letterCombinations(String digits) {
    	List<String> ret = new ArrayList<String>();
    	if (digits == null || digits.isEmpty()) return ret;
    	String[] table = {" ", "", "abc", "def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    	ret.add("");
    	for (int i=0; i<digits.length();++i){
    		List<String> temp = new ArrayList<String>();
    		for (String s : ret){
    			int index = digits.charAt(i) - 48;
    			for (int j = 0; j<table[index].length();++j){
    				String newcombo = s + table[index].charAt(j);
    				temp.add(newcombo);
    			}
    		}
    		ret = temp;
    	}
    	return ret;
    }

    /**
     * Problem #18
     * 4Sum
     */
    public List<List<Integer>> fourSum(int[] num, int target) {
//    	int counter = 0;
    	List<List<Integer>> ret = new ArrayList<List<Integer>>();
    	if (num == null || num.length < 4) return ret;
    	Arrays.sort(num);
    	for (int i = 0; i< num.length; ++i){
    		if ( i>0 && num[i] == num[i-1])
    			continue;
    		for (int j = i+1; j < num.length; ++j){
        		if ( j > i+1 && num[j] == num[j-1])
        			continue;
    			int itarget = target - num[i] - num[j];
    			int low = j + 1;
    			int high = num.length - 1;
    			while (low < high){
//    				counter++;
    				int i_sum = num[low] + num[high];
    				if (i_sum < itarget)
    					++low;
    				else if (i_sum > itarget)
    					--high;
    				else{
    					List<Integer> item = new ArrayList<Integer>();
    					item.add(num[i]);
    					item.add(num[j]);
    					item.add(num[low]);
    					item.add(num[high]);
    					ret.add(item);
    					int lv = num[low], hv = num[high];
    					while (low < high && lv == num[low]) ++low;
    					while (low < high && hv == num[high]) --high;
    				}
    			}
    		}
    	}
 //   	System.out.println("counter:" + counter);
    	return ret;
    }
    /**
     * The old inefficient approach for 4Sum
     * @param num
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] num, int target) {
    	counter4sum = 0;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null) return result;
        Arrays.sort(num);
        for (int i = 0 ; i < num.length - 3; ++i)
            for (int j = i+3; j < num.length; ++j)
                check4Sum(num,i,j,target-num[i]-num[j],result);
        System.out.println("old counter: " + counter4sum);
        return result;
    }

    public void check4Sum(int[] num, int s, int e, int target, List<List<Integer>> result){
        int lnum = num[s], rnum = num[e];
        //HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int k = s+1; k < e; ++k){
        	counter4sum++;
            if (hs.contains(target-num[k])){
                List<Integer> q = new ArrayList<Integer>();
                q.add(lnum);
                q.add(target-num[k]);
                q.add(num[k]);
                q.add(rnum);
                if (result.size() > 0){
                	if (!checkDup(q,result.get(result.size()-1)))
                		result.add(q);
                }
                else
                	result.add(q);
            }
            else
                hs.add(num[k]);
        }
    }   

    public boolean checkDup(List<Integer> test, List<Integer> checked){
    	for (int i=0; i<test.size(); ++i)
    		if (test.get(i) != checked.get(i))
    			return false;
    	return true;
    }
    
    /**
     * Problem #19
     * Remove Nth Node From End of List
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	if (head == null) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode fast = dummy;
    	ListNode slow = dummy;
    	while (n-- > 0) 
    		fast = fast.next;
    	while (fast.next != null){
    		fast = fast.next;
    		slow = slow.next;
    	}
    	slow.next = slow.next.next;
    	return dummy.next;
    }
    
    /**
     * Problem #20
     * Valid Parentheses
     */
    public boolean isValid(String s) {
    	Stack<Character> st = new Stack<Character>();
    	boolean int_ret = true;
    	for (int i=0; i<s.length(); i++){
    		char c = s.charAt(i);
    		if ( c == '(' || c == '[' || c == '{')
    			st.push(c);
    		else if (st.isEmpty())
    			int_ret = false;
    		else {
    			char tc = st.pop().charValue();
    			int_ret = (c == ')' && tc == '(')
    						|| (c == ']' && tc == '[')
    						|| (c == '}' && tc == '{');
    		}
    		if (!int_ret) break;
    	}
    	return st.isEmpty() && int_ret;
    }
    
    /**
     * Problem #21
     * Merge Two Sorted Lists
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	ListNode dummy = new ListNode(0);
    	ListNode cur = dummy;
    	while (l1 != null && l2 != null){
    		if (l1.val < l2.val){
    			cur.next = l1;
    			l1 = l1.next;
    		}else{
    			cur.next = l2;
    			l2 = l2.next;
    		}
			cur = cur.next;
    	}
    	if (l1 == null)
    		cur.next = l2;
    	else
    		cur.next = l1;
    	return dummy.next;
    }
    /**
     * Problem #22
     * Generate Parentheses 
     */
    //private static int pc;
    public List<String> generateParenthesis(int n) {
    	//pc = 0;
    	List<String> ret = new ArrayList<String>();
    	genPar_aux("(", 1, 0, n, ret);
    	//System.out.println("pc:" + pc);
    	return ret;
    }
    
    private void genPar_aux(String temp, int l, int r, int n, List<String> ret){
    	//pc++;
    	if ( l == r && l == n){
    		ret.add(temp);
    		return;
    	}
    	if (l < n && l != r)
    		genPar_aux(temp+"(", l+1, r, n, ret);
    	if (l > r)
    		genPar_aux(temp+")", l, r+1, n, ret);
    	if (l == r)
    		genPar_aux(temp+"(", l+1, r, n, ret);
    	return;
    }
    
    /**
     * Problem #23
     * Merge k Sorted Lists
     * A naive solution is just to scan the first element of each list, find the smallest one and insert it to the
     * result list. Each time it needs scan k nodes and scan n * k times, i.e O(n*k*k)
     * In the solution below, we merge lists in pair, and merge the intermediate result in pair, 
     * until we get the final list.
     * All is completed in place, so space complexity is O(1) and time complexity is O(n*k*lg(k)).
     */
    public ListNode mergeKLists(ListNode[] lists) {
    	if (lists == null || lists.length == 0) return null;
    	if (lists.length == 1) return lists[0];
    	int k = lists.length;
    	double realheight = Math.log(k)/Math.log(2);
    	int height = (int)realheight;
    	height = height < realheight ? height+1 : height;
    	int step = 1;
    	for (int i =1 ; i <= height; ++i){
    		step *= 2;
    		for (int j = 0; j < k; j += step){
    			int offset = step / 2;
    			lists[j] = this.mergeTwoLists(lists[j], (j + offset >= k ? null : lists[j+offset]));
    		}
    	}
    	return lists[0];
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        int step = 2, mstep = 1;
        while (mstep < lists.length){
            for (int i = 0; i < lists.length; i += step){
                lists[i] = mergeTwoLists(lists[i], i+mstep < lists.length ? lists[i+mstep] : null);
            }
            step <<= 1;
            mstep <<= 1;
        }
        return lists[0];
    }
    
    
    /**
     * Problem #24
     * Swap Nodes in Pairs 
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null && cur.next != null){
        	pre.next = cur.next;
        	cur.next = cur.next.next;
        	pre.next.next = cur;
        	pre = cur;
        	cur = cur.next;
        }
        return dummy.next;
    }
    
    /**
     * Problem #25
     * Reverse Nodes in k-Group
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = head;
        ListNode prek = dummy;
        while(fast != null){
        	boolean done = false;
        	for (int i = 0; i < k; ++i){
        		if (fast == null){
        			done = true;
        			break;
        		}
        		fast = fast.next;
        	}
        	if (done) break;
        	ListNode cur = prek.next;
        	while (cur.next != fast){
            	ListNode ln = cur.next.next;
        		cur.next.next = prek.next;
        		prek.next = cur.next;
        		cur.next = ln;
        	}
        	prek = cur;        	
        }
        return dummy.next;
    }
    
    /**
     * Problem #26
     * Remove Duplicates from Sorted Array 
     */
    public int removeDuplicates(int[] A) {
    	if (A == null || A.length == 0) return 0;
    	int ret = 0;
    	for(int i=1; i<A.length; ++i){
    		if (A[i] != A[ret])
    			A[++ret] = A[i];
    	}
    	return ret+1;
    }
    
    /**
     * Problem #27
     * Remove Element
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0)
            return 0;
        int i = 0, j = nums.length - 1;
        while (i <= j){
            if (nums[i] != val)
                i++;
            else{
                nums[i] = nums[j--];
            }
        }
        return j+1;
    }
    
    /**
     * Problem #28
     * Implement strStr() 
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        if (needle.length() > haystack.length()) return -1;
        if (needle.isEmpty()) return 0;
        int i, j;
        for(i = 0 ; i <= haystack.length() - needle.length(); ++i){
        	for (j=0; j<needle.length(); ++j){
        		if (haystack.charAt(i+j ) != needle.charAt(j))
        			break;
        	}
        	if (j == needle.length())
        		break;
        }
        return i > haystack.length() - needle.length() ? -1 : i;
    }
    
    /**
     * Problem #29
     * Divide Two Integers 
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        boolean sign = (dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0);
        if (dividend == Integer.MIN_VALUE){
            if (divisor == 1)
                return Integer.MIN_VALUE;
            if (divisor == -1)
                return Integer.MAX_VALUE;
        }
        long lremainder = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        int ret = 0;
        while (lremainder >= ldivisor){
            int folds = -1;
            long temp = ldivisor;
            while (temp <= lremainder){
                folds++;
                temp <<= 1;
            }
            if (folds >= 0){
                ret += (1 << folds);
                lremainder -= (temp >> 1);
            }
        }
        return sign ? ret : -ret;
    }
    
    /**
     * Problem #30
     * Substring with Concatenation of All Words
     */
    public List<Integer> findSubstring(String s, String[] words) {
    	List<Integer> ret = new LinkedList<Integer>();
    	if (s == null || words == null || words.length == 0 || s.isEmpty())
    		return ret;
    	HashSet<String> wordset = new HashSet<String>();
		resetWordset(words, wordset);
    	int wordlen = words[0].length();
    	for(int i=0; i<=s.length() - wordlen * words.length; ++i){
    		int j;
    		String curword;
    		for (j = 0; j< wordlen * words.length; j+=wordlen){
    			curword = s.substring(i+j, i + j + wordlen);
    			if (wordset.contains(curword)){
    				wordset.remove(curword);
    			}else
    				break;
    		}
    		if (wordset.isEmpty())
    			ret.add(i);
    		if (wordset.size() < words.length)
    			resetWordset(words, wordset);
    	}
    	return ret;
    }    

    private void resetWordset(String[] words, HashSet<String> wordset){
    	for(int i=0; i<words.length; ++i)
    		wordset.add(words[i]);
    }

    /* Handle repeated words*/
    /*
    public List<Integer> findSubstring2(String s, String[] words) {
    	List<Integer> ret = new LinkedList<Integer>();
    	if (s == null || words == null || words.length == 0 || s.isEmpty())
    		return ret;
    	HashMap<String, Integer> wordsmap = new HashMap<String, Integer>();
    	resetWordsMap(words, wordsmap);
    	int wordlen = words[0].length();
    	for(int i=0; i<=s.length() - wordlen * words.length; ++i){
    		int j;
    		String curword;
    		for (j = 0; j< wordlen * words.length; j+=wordlen){
    			curword = s.substring(i+j, i + j + wordlen);
    			if (wordsmap.containsKey(curword)){
    				int counter = wordsmap.get(curword).intValue();
    				if (counter == 0)
    					break;
    				else
    					wordsmap.put(curword, counter-1);
    			}else
    				break;
    		}
    		if (j==wordlen * words.length)
    			ret.add(i);
    		resetWordsMap(words, wordsmap);
    	}
    	return ret;
    }    

    private void resetWordsMap(String[] words, HashMap<String, Integer> wordsmap){
    	for(int i=0; i<words.length; ++i)
    		if (wordsmap.containsKey(words[i]))
    			wordsmap.put(words[i], wordsmap.get(words[i]).intValue() + 1);
    		else
    			wordsmap.put(words[i], 1);
    }
	*/
    /**
     * The words may contain duplicated words, i.e., the word could appear more than once in the 
     * searched string
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring3(String s, String[] words) {
    	List<Integer> ret = new LinkedList<Integer>();
    	HashMap<String, Integer> dict = new HashMap<String, Integer>();
    	// Fill a dictionary, record the occurrence times of each word.
    	for (int i = 0; i < words.length; ++i){
    		setDictCount(dict,words[i],1);
    		/*
    		if (dict.containsKey(words[i]))
    			dict.put(words[i], dict.get(words[i]).intValue() + 1);
    		else
    			dict.put(words[i], 1);
    		*/
    	}
    	int wlen = words[0].length();
    	int slen = s.length();
    	/**
    	 * Search word by word, rather that char by char
    	 * The offset is the length of a word
    	 */
    	for(int i=0; i<wlen; ++i){
    		int left = i, count = 0;
    		HashMap<String, Integer> tdict = new HashMap<String, Integer>();
        	/**
        	 * Each time, inspect combination of words starting with the offset i
        	 */
    		for (int j = i; j <= slen-wlen; j += wlen){
    			String curword = s.substring(j, j + wlen);
    			if (dict.containsKey(curword)){
    				setDictCount(tdict,curword,1);
    				if (tdict.get(curword).intValue() <= dict.get(curword).intValue()){
    					/* The (duplicated) words appears only once */
    					/* Because count starts with "0", we need "<="*/
    					++count;
    				}else{
    					/**
    					 * When the (duplicated) word appears in the string more times than it appears in the words
    					 * Discard the first word in the current checking sequence, and shift one word.
    					 */
    					while (tdict.get(curword).intValue() > dict.get(curword).intValue()){
    						String head = s.substring(left, left + wlen);
    						setDictCount(tdict,head,-1);
    						/* if the first word is not the over-repeated word, decrease the count by one */
    						if (tdict.get(head).intValue() < dict.get(head).intValue())
    							--count;
    						left += wlen;
    					}
    				}
    				/* Find a match */
    				if (count == words.length){
    					ret.add(left);
    					/* Discard the first word and shift one word */
    					setDictCount(tdict, s.substring(left, left+wlen), -1);
    					--count;
    					left += wlen;
    				}
    			}else{ /* Invalid, start over with the next word */
    	    		tdict.clear();
    	    		count = 0;
    	    		left = j + wlen;
    			}    			
    		}
    	}
    	return ret;
    }
    
    private void setDictCount(HashMap<String, Integer> hm, String key, int offset){
    	if (hm.containsKey(key))
    		hm.put(key, hm.get(key).intValue() + offset);
    	else
    		hm.put(key, 1);
    }
    /**
     * Problem #31
     * Next Permutation
     * The change should happen where a digit at position i is smaller than the one at i+1
     * The digit at i should be exchanged with the smallest digit after i, which is larger than i
     * Then reverse the the digits following digit i 
     */
    public void nextPermutation(int[] num) {
        if (num == null || num.length <=1) return;
        int i, j;
        for (i = num.length-2; i >= 0; --i)
        	if (num[i] < num[i+1])
        		break;
        if (i>=0){
        	for (j=num.length-1; j>i; --j)
        		if (num[i] < num[j])
        			break;
        	int tmp = num[i];
        	num[i] = num[j];
        	num[j] = tmp;
        }
        reverseList(num,i+1,num.length-1);
    }
    
    private void reverseList(int[] num, int start, int end){
    	if (num == null || start < 0 || end >= num.length)
    		return;
        int len = end-start+1;
        for(int i=0;i<len/2; i++){
        	int temp = num[start+i];
        	num[start+i] = num[end-i];
        	num[end-i] = temp;
        }    	
    }
    /**
     * Problem #32
     * Longest Valid Parentheses 
     */
    
    public int longestValidParentheses(String s) {
    	if (s == null || s.isEmpty()) return 0;
    	int[] ret = new int[s.length()];
    	for (int i=0; i<ret.length; ++i)
    		ret[i] = 0;
    	int open = 0;
    	for (int i=0; i<s.length(); ++i){
    		if (s.charAt(i) == '('){
    			++open;
    			ret[i] = 0;
    		}
    		else if (s.charAt(i) == ')'){
    			if (open == 0)
    				ret[i] = 0;
    			else{
    				open = open > 0 ? open-1 : 0;
    				ret[i] = ret[i-1] + 2;
    				if (i - ret[i] >= 0)
    					ret[i] = ret[i] + ret[i - ret[i]];
    			}
    		}
    	}
//    	TeasingUtil.printList(ret);
    	int max = ret[0];
    	for (int i=1; i<ret.length; ++i)
    		max = max > ret[i] ? max : ret[i];
    	return max;
    }
    
    
    /**
     * Problem #33
     * Search in Rotated Sorted Array 
     */
    public int search(int[] nums, int target) {
    	if (nums == null || nums.length < 1)
    		return -1;
    	int s = 0, e = nums.length-1, m = (s+e)/2;
    	while (s <= e){
    		if (nums[m] == target)
    			return m;
    		if (nums[e] >= nums[m])
    			if (target >= nums[m] && target <= nums[e])
    				s = m+1;
    			else
    				e = m-1;
    		else// (nums[m] >= nums[s])
    			if (target >= nums[s] && target <= nums[m])
    				e = m-1;
    			else
    				s = m+1;
    		m = (s+e)/2;
    	}
    	return -1;
    }  
    
    /**
     * Problem #34
     * Search for a Range
     * Do binary search twice. Once for the left (small) edge, once for the right (large) edge
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums == null) return null;
        int[] ret = new int[2];
        ret[0] = -1; ret[1] = -1;
        int s = 0, e = nums.length - 1, mid;
        // Looking for left (small) edge
        while (e - s > 1){
            mid = (e+s)/2;
            if (nums[mid] > target)
                e = mid - 1;
            else if (nums[mid] == target)
            	// Move the end index towards left edge
                e = mid;
            else
                s = mid + 1;
        }
        // If nums[s] == nums[e], always take the small one.
        if (target == nums[s])
            ret[0] = s;
        else if (target == nums[e])
            ret[0] = e;
        
        // Looking for right (large) edge
        s = 0; e = nums.length - 1;
        while (e - s > 1){
            mid = (e + s)/2;
            if (nums[mid] < target)
                s = mid + 1;
            else if (nums[mid] == target)
            	// Move the start index towards rigth edge
                s = mid;
            else
                e = mid - 1;
        }
        // If nums[s] == nums[e], always take the large one.
        if (target == nums[e])
            ret[1] = e;
        else if (target == nums[s])
            ret[1] = s;
        return ret;
    }
    
    /**
     * Problem #35
     * Search Insert Position 
     */
    public int searchInsert(int[] nums, int target) {
    	int ret = 0;
    	if (nums == null || nums.length == 0) return ret;
    	int s = 0, e = nums.length-1, mid;
    	while (s < e){
    		mid = (s+e)/2;
    		if (target <= nums[mid])
    			e = mid;
    		else
    			s = mid +1;
    	}
    	if (target <= nums[s])
    		ret = s;
    	else
    		ret = s + 1;
    	System.out.println(s);
    	return ret;
    }
    
    /**
     * Problem #36
     * Valid Sudoku
     */
    
	public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 ||
        		board[0] == null || board[0].length != 9)
        	return false;
        ArrayList<HashSet<Character>> rows = new ArrayList<HashSet<Character>>();
        ArrayList<HashSet<Character>> cols = new ArrayList<HashSet<Character>>();
        ArrayList<HashSet<Character>> boxes = new ArrayList<HashSet<Character>>();
        for (int i = 0; i<9; ++i){
        	rows.add(new HashSet<Character>());
        	cols.add(new HashSet<Character>());
        	boxes.add(new HashSet<Character>());
        }
        for (int i = 0; i<board.length; ++i)
        	for (int j = 0; j<board[0].length; ++j){
        		char cur = board[i][j];
        		if (cur == '.') continue;
        		// Check row and col
        		if (!rows.get(i).add(cur)) return false;
        		if (!cols.get(j).add(cur)) return false;
        		// Check sub-box
        		int x = i / 3, y = j / 3;
        		if (!boxes.get(x*3+y).add(cur))
        			return false;
        	}
        return true;
    }
	
    public boolean isValidSudoku2(char[][] board) {
    	if (board == null) return false;
    	if (board.length != 9 || board[0].length != 9) return false;

    	HashSet<Character> hs = new HashSet<Character>();
    	
    	// Check rows
    	for(int i=0; i<board.length; ++i){
    		for (int j=0; j<board[0].length; ++j)
    			if (board[i][j] != '.' && !hs.add(board[i][j]))
    				return false;
    		hs.clear();
    	}
    	
    	// Check cols
    	for (int j=0;j<board[0].length; ++j){
    		for (int i=0; i<board.length; ++i)
    			if (board[i][j] != '.' && !hs.add(board[i][j]))
    				return false;
    		hs.clear();
    	}
    	
    	// Check subbox
    	for (int i=0;i<3;++i)
    		for (int j=0;j<3;++j){
    			for (int m=0;m<3;++m)
    				for(int n=0;n<3;++n){
    					char c = board[i*3+m][j*3+n];
    					if (c !='.' && !hs.add(c))
    						return false;
    				}
    			hs.clear();
    		}
    	return true;        
    }

	
	/**
	 * Problem #37
	 * Sudoku Solver
	 */

	ArrayList<HashSet<Character>> row; //= new ArrayList<HashSet<Character>>();
	ArrayList<HashSet<Character>> col; //= new ArrayList<HashSet<Character>>();
	ArrayList<HashSet<Character>> subbox; //= new ArrayList<HashSet<Character>>();
	List<Cell> unfilled;
	
    public class SCell {
    	int x;
    	int y;
    	char val;
    	
    	public SCell(int i, int j, char key){
    		x = i; y = j; val = key;
    	}
    	
    }
    class Cell{
        int x;
        int y;
        Cell (int row, int col) {x = row; y = col;}
    }
    
    public void solveSudoku2(char[][] board) {
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9)
            return;
        
        row = new ArrayList<HashSet<Character>>();
        col = new ArrayList<HashSet<Character>>();
        subbox = new ArrayList<HashSet<Character>>();
        for(int i=0; i<9; i++){
            row.add(new HashSet<Character>());
            col.add(new HashSet<Character>());
            subbox.add(new HashSet<Character>());
        }
        unfilled = new ArrayList<Cell>();
        // Initialization
        for (int i = 0; i<board.length; i++)
            for (int j = 0; j< board[0].length; j++){
                char c = board[i][j];
                if (c == '.')
                    unfilled.add(new Cell(i, j));
                else{
                    row.get(i).add(c);
                    col.get(j).add(c);
                    subbox.get(i/3*3 + j/3).add(c);
                }
            }
        solveSudokuHelper(board, 0);
    }    
 
    private boolean solveSudokuHelper(char[][] board, int ufindex){
        if(ufindex == unfilled.size()) return true;
        Cell curcell = unfilled.get(ufindex);
        for (char checker = '1'; checker <= '9'; checker++){
            int i = curcell.x, j = curcell.y;
            if (checkCell(board, curcell, checker)){
                board[i][j] = checker;
                if (solveSudokuHelper(board, ufindex+1))
                    return true;
                else{
                    board[i][j] = '.';
                    row.get(i).remove(checker);
                    col.get(j).remove(checker);
                    subbox.get(i/3*3 + j/3).remove(checker);                
                }
                
            }
        }
        return false;
    }

    private boolean checkCell(char[][] board, Cell cell, char c){
        int i = cell.x, j = cell.y;
        if (!row.get(i).add(c)) return false;
        if (!col.get(j).add(c)) {
            row.get(i).remove(c);
            return false;
        }
        if (!subbox.get(i/3*3 + j/3).add(c)){
            row.get(i).remove(c);
            col.get(j).remove(c);
            return false;
        }
        return true;
    }
    
    
    public void solveSudoku(char[][] board) {
    	if (board == null || board.length == 0 
    			|| board[0] == null || board[0].length == 0)
    		return;

        Stack<SCell> stunfill = new Stack<SCell>();
        Stack<SCell> stfilled = new Stack<SCell>();
        
        for (int i=0; i<board.length; ++i)
        	for (int j=0; j<board[i].length; ++j){
        		if (board[i][j] == '.' )
        			stunfill.push(new SCell(i,j,'1'));
        	}
        
        while (!stunfill.isEmpty()){
        	SCell curcell = stunfill.pop();
        	while (curcell.val <= '9'){
        		if (checkCell(curcell.x, curcell.y, curcell.val, board)){
        			board[curcell.x][curcell.y] = curcell.val;
        			stfilled.push(curcell);
        			break;
        		}
            	curcell.val++;
        	}
        	if (curcell.val > '9')
        		if(!stfilled.isEmpty()){
        			curcell.val = '1';
        			stunfill.push(curcell);
        			curcell = stfilled.pop();
        			board[curcell.x][curcell.y] = '.';        			
        			curcell.val++;
        			stunfill.push(curcell);
        		}
        		else return;
        }
    }

    public boolean checkCell(int i, int j, char checker, char[][] board){
    	boolean result = true;
    	for (int k=0; k< board.length; ++k){
    		if (checker == board[k][j])
    			return false;
    	}
    	
    	for (int k=0; k<board[i].length; ++k)
    		if (checker == board[i][k])
    			return false;
    	
    	int top = (i / 3) * 3;
    	int left = (j / 3) * 3;
    	for (int m = top ; m < top+3; ++m)
    		for (int n = left; n < left + 3; ++n)
    			if (checker == board[m][n])
    				return false;
    	
    	return result;
    }

    /**
     * Problem #38
     * Count and Say
     */
    public String countAndSay(int n) {
    	if (n <= 0) return null;
    	String s = "1";
    	for (int i = 2; i <=n ; ++i){
    		String temp = "";
    		char prechar = s.charAt(0);
			int counter = 1;
    		for (int j=1; j<s.length(); j++){
    			if (prechar == s.charAt(j))
    				counter++;
    			else{
    				temp += String.valueOf(counter);
    				temp += prechar;
    				prechar = s.charAt(j);
    				counter = 1;
    			}
    		}
    		s = temp;
			s += String.valueOf(counter);
			s += prechar;
    	}
    	return s;
    }
    
    public String countAndSay2(int n) {
        if (n <= 0) return "";
        String ret = "1";
        for(int i = 1 ; i < n; i++){
            StringBuffer sb = new StringBuffer();
            int j = 0, count = 1;
            while (++j < ret.length()){
                if (ret.charAt(j-1) == ret.charAt(j))
                    count++;
                else{
                    sb.append(String.valueOf(count) + ret.charAt(j-1));
                    count = 1;
                }                    
            }
            sb.append(String.valueOf(count) + ret.charAt(j-1));
            ret = sb.toString();
        }
        return ret;
    }
    
    
    /**
     * Problem #39
     * Combination Sum
     */
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> ret = new LinkedList<List<Integer>>();
    	if (candidates == null || candidates.length == 0) return ret;
    	Arrays.sort(candidates);
    	int[] nodup = removeDuplicated(candidates);
    	csum_helper(new LinkedList<Integer>(), nodup, 0, target, ret);
    	return ret;
    }
    
    public void csum_helper(List<Integer> subret, int[] nums, int index, int target, List<List<Integer>> ret){
    	if (target == 0){
    		ret.add(subret);
    		return;
    	}
    	for (int i=index; i<nums.length; ++i){
    		int newtarget = target - nums[i];
    		if (newtarget >=0){
    			List<Integer> newsubret = new LinkedList<Integer>(subret);
    			newsubret.add(nums[i]);
    			csum_helper(newsubret, nums, i, newtarget, ret);
    		}else{
    			break;
    		}    			
    	}
    }
    /**
     * Remove duplicated integers in an array
     * A generic approach with HashSet.
     * @param nums
     * @return
     */
    public int[] removeDuplicated(int[] nums){
        if (nums == null) return null;
        HashSet<Integer> hs = new HashSet<Integer>();
        int len = 0;
        for (int i=0; i<nums.length; i++){
            if (hs.add(nums[i]))
                nums[len++] = nums[i];
        }
        int[] ret = Arrays.copyOf(nums,len);
        return ret;    	
    }
    
    /**
     * Problem #40
     * Combination Sum II 
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    	List<List<Integer>> ret = new LinkedList<List<Integer>>();
    	if (candidates == null || candidates.length == 0) return ret;
    	Arrays.sort(candidates);
    	csum2_helper(new LinkedList<Integer>(), candidates, 0, target, ret);
    	return ret;
    }
    
    public void csum2_helper(List<Integer> subret, int[] nums, int index, int target, List<List<Integer>> ret){
    	if (target == 0){
    		ret.add(subret);
    		return;
    	}
    	for (int i=index; i<nums.length; ++i){
    		int newtarget = target - nums[i];
    		if (newtarget >= 0){
    			List<Integer> newsubret = new LinkedList<Integer>(subret);
    			newsubret.add(nums[i]);
    			csum2_helper(newsubret, nums, i+1, newtarget, ret);
    			while (i<nums.length-1 && nums[i] == nums[i+1])
    				++i;
    		}else
    			break;
    	}
    } 
    
    /**
     * Problem #41
     * First Missing Positive
     * To search the first missing positive in O(n) without extras space, we need sophisticated solution.
     * The solution is "partially sort" the integer array using bucket sort.
     * Suppose all positive integers are in the array A. If we sort them, the number 1 should be in index 0,
     * 2 should be in index 1, and so on. That is, A[i] should be the number i+1.
     * So, begin with the first element, we check if A[i] == i+1. If it is not, swap the number to the position it should sit.
     * After that, go through the array again, if the number at index i does not equal to i+1, then i+1 is the missing positive.
     */
    public int firstMissingPositive(int[] nums) {
    	int i = 0;
    	int n = nums.length;
    	while (i < n){
    		/*
    		 * 1. check if nums[i] is at the right position i+1
    		 * 2. check if it is a positive integer between 1 and n, which the range of the array can contain
    		 * 		if it is non-positive integer, leave as it is.
    		 * 3. check if the integer in the intended position is already num[i], i.e. skip the duplicated integer
    		 */
    		if (nums[i] != (i+1) && nums[i] >= 1 && nums[i] <= n && nums[nums[i]-1] != nums[i]){
    			swap(nums,i, nums[i]-1);
    		}
    		else
    			++i;
    	}
    	for (i = 0; i < n; ++i)
    		if (nums[i] != (i+1))
    			return i+1;
    	/* If the array contains all non-duplicated integers from 1, return the next positive integer*/
    	return n+1;
    }
    
    
    public void swap(int[] A, int i, int j){
    	int temp = A[i];
    	A[i] = A[j];
    	A[j] = temp;
    }
    
    /**
     * Problem #42
     * Trapping Rain Water
     */
    public int trap(int[] height) {
    	return 0;
    }
    
    /**
     * Problem #43
     * Multiply Strings
     */
    
    public String multiply(String num1, String num2) {
        if (num1 == null || num1.isEmpty()) return num2;
        if (num2 == null || num2.isEmpty()) return num1;
        int m = num1.length(), n = num2.length();
        int[] product = new int[m+n];
        for (int i = m-1; i >= 0; i--)
            for (int j = n-1; j >= 0; j--){
                int d1 = num1.charAt(i) - '0';
                int d2 = num2.charAt(j) - '0';
                product[i+j+1] += d1 * d2;
            }
        int c = 0, sum = 0;
        for (int i = product.length-1; i>=0; i--){
            sum = product[i] + c;
            product[i] = sum % 10;
            c = sum / 10;
        }
        StringBuffer ret = new StringBuffer();
        for (int i : product)
            ret.append(i);
        while (ret.length() != 0 && ret.charAt(0) == '0') ret.deleteCharAt(0);
        return ret.length() == 0 ? "0" : ret.toString();        
    }

    /**
     * Problem #43.1
     * Add two strings 
     */
    public String add(String num1, String num2){
        if (num1 == null || num1.isEmpty()) return num2;
        if (num2 == null || num2.isEmpty()) return num1;
        String ret = "";
        int c = 0, sum = 0;
        int i = num1.length() - 1, j = num2.length() - 1;
        while (i >= 0 && j >= 0){
            sum = num1.charAt(i) + num2.charAt(j) - 'a' - 'a' + c;
            c = sum / 10;
            ret = sum % 10 + ret;
            i--; j--;
        }
        int k = i >= 0 ? i : j;
        String rest = i >= 0 ? num1 : num2;
        while ( k >= 0){
            sum = rest.charAt(k) - 48 + c;
            c = sum / 10;
            ret = sum % 10 + ret;
            k--;
        }
        return c > 0 ? "1"+ ret : ret;
    }
    
    /**
     * Problem #46
     * Permutations
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0) return ret;
        boolean[] flags = new boolean[nums.length];
        for (int i=0; i< flags.length; i++){
        	flags[i] = false;
        }
        permutehelper(nums,flags, 0, new LinkedList<Integer>(), ret);
        return ret;
    }
    
    public void permutehelper(int[] nums, boolean[] flags, int counter, List<Integer> subret, List<List<Integer>> ret){
        if (nums.length == counter){
            ret.add(subret);
            return;
        }
        for (int i = 0; i<nums.length; i++){
            if (flags[i])
                continue;
            List<Integer> newsubret = new LinkedList<Integer>(subret);
            newsubret.add(nums[i]);
            flags[i] = true;
            permutehelper(nums,flags, counter+1, newsubret,ret);
            flags[i] = false;
        }
        return;
    }

    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (nums == null || nums.length == 0)
            return ret;
        List<Integer> first = new LinkedList<Integer>();
        first.add(nums[0]);
        ret.add(first);
        for(int i=1; i<nums.length;i++){
            List<List<Integer>> tmp = new LinkedList<List<Integer>>();
            for (List<Integer> item : ret){
                for (int j = 0; j<=item.size(); j++){
                    List<Integer> newitem = new LinkedList<Integer>(item);
                    newitem.add(j,nums[i]);
                    tmp.add(newitem);
                }
            }
            ret = tmp;
        }
        return ret;          
    }    

    /**
     * Problem #47
     * Permutations II
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return ret;
        List<Integer> first = new LinkedList<Integer>();
        first.add(nums[0]);
        ret.add(first);
        for(int i=1; i< nums.length; i++){
            HashSet<List<Integer>> tmp = new HashSet<List<Integer>>();
            for(List<Integer> item : ret){
                for(int j=0; j<=item.size(); j++){
                    List<Integer> newitem = new LinkedList<Integer>(item);
                    newitem.add(j,nums[i]);
                    tmp.add(newitem);
                }
            }
            ret = new ArrayList<List<Integer>>();
            ret.addAll(tmp);
        }
        return ret;
    }    
    
    /**
     * Problem #48
     * Rotate Image
     */

    public void rotate(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0] == null ||
    			matrix[0].length == 0 || matrix.length != matrix[0].length ) return;
    	int n = matrix.length;
    	for(int layer = 0; layer < n/2; layer++){
    		int first = layer, last = n-1-layer;
    		for (int i=first; i< last; i++){
    			int offset = i - first;
    			int top = matrix[first][i];
    			matrix[first][i] = matrix[last-offset][first];
    			matrix[last-offset][first] = matrix[last][last-offset];
    			matrix[last][last-offset] = matrix[i][last];
    			matrix[i][last] = top;
    		}
    	}
    	return;
    }
    /**
     * Problem #49
     * Anagrams
     * 
	 * Step 1: Sort each string using counting sort (see the method below): O(n*m)
	 * Step 2: Create a HashMap whose key is the sorted string and the value is a list of integer storing the indexes of the strings which have same sorted string values
	 * Step 3: Traverse the sorted string array and fill the hash map: O(n)
	 * Step 4: Iterate the hash map in terms of keys (sorted string values), retrive the indexes and add the original strings to the output list
     */
    public List<String> anagrams(String[] strs) {
        List<String> ret = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return ret;
        HashMap<String, List<Integer>> counter = new HashMap<String, List<Integer>>();
        for (int i =0 ; i<strs.length; i++){
            String sorted = sortString(strs[i]);
            List<Integer> list = counter.get(sorted);
            if (list == null){
                list = new ArrayList<Integer>();
                counter.put(sorted, list);
            }
            list.add(i);
            
        }
        for (String key : counter.keySet()){
            List<Integer> list = counter.get(key);
            if (list.size() > 1)
                for (Integer index : list)
                    ret.add(strs[index.intValue()]);
        }
        return ret;
    }

    /**
     * Sort a string using Counting Sort
     * Assumption: all elements are letters in low case. i.e. there are totally 26 types of elements, which can be seen as 26 integers
     * For each string, calculate the occurrence of each letter, then assemble a new (sorted) string with the ascending order of elements by the number of occurrence times.
     * Time complexity: O(n)
     */
    public String sortString(String str){
        if (str == null) return str;
        int[] counter = new int[26];
        for(int i=0; i<str.length(); i++)
            counter[str.charAt(i)-'a']++;
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<counter.length; i++)
            while(counter[i]-- > 0)
                sb.append((char)('a'+i));
        return sb.toString();
    }
    
    /**
     * Anagrams2 
     * Assign a prime number for a to z, and then multiply all prime numbers together to form a hash value.
     */
    public List<String> anagrams2(String[] strs) {
        int[] PRIMES = new int[]{2, 3, 5, 7, 11 ,13, 17, 19, 23, 29, 31, 37, 41, 43,
                                 47,53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 107};
        List<String> ret = new LinkedList<String>();
        if (strs == null || strs.length == 0) return ret;
        HashMap<Long, List<Integer>> record = new HashMap<Long, List<Integer>>();
        for (int i=0; i<strs.length; i++){
            long mapping = 1;
            for (int j=0; j<strs[i].length(); j++)
                mapping *= PRIMES[strs[i].charAt(j) - 'a'];
            List<Integer> list = record.get(mapping);
            if (list == null){
                list = new LinkedList<Integer>();
                record.put(mapping, list);
            }
            list.add(i);
        }
        for (Long key : record.keySet()){
            List<Integer> list = record.get(key);
            if (list.size() > 1)
                for (Integer index : list)
                    ret.add(strs[index.intValue()]);
        }
        return ret;
    }
    
    /**
     * Problem #50
     * Pow(x, n) 
     */
    public double myPow(double x, int n) {
        if (x == 0.0)
            return n >=0 ? 0.0 : Double.MAX_VALUE;
        boolean sign = x >= 0 || n % 2 == 0;
        double ret = calpow(Math.abs(x), Math.abs(n));
        ret = sign ? ret : -ret;
        return n >= 0 ? ret : 1/ret;
    }
    
    public double calpow(double x, int n){
    	if (n==0) return 1.0;
    	double x_n_2 = calpow(x, n/2);
    	if (n % 2 == 0)
    		return x_n_2*x_n_2;
    	else
    		return x_n_2*x_n_2*x;
    }
    
    public double myPow2(double x, int n) {
        if (x == 0.0)
            return n >=0 ? 0.0 : Double.MAX_VALUE;
        double ret = 1.0;
        long p;
        if (n < 0){
            p = -n;
            x = 1/x;
        }else
            p = n;
        while( p > 0){
            if ((p & 1) == 1)
                ret *= x;
            x *= x;
            p >>= 1;
        }
        return ret;
    }
    
    /**
     * Problem #53
     * Maximum Sub-array 
     */
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0) return -1;
        int maxsum = Integer.MIN_VALUE;
        int cursum = 0;
        for(int i=0; i<A.length; i++){
            cursum += A[i];
            maxsum = maxsum > cursum ? maxsum : cursum;
            cursum = cursum < 0 ? 0 : cursum;
        }
        return maxsum;
    }

    public int[] maxSubArrayRange(int[] A) {
    	int[] ret = new int[3];
        if (A == null || A.length == 0) return ret;
        int maxsum = Integer.MIN_VALUE;
        int cursum = 0;
        int low = 0, high = 0;
        for(int i=0; i<A.length; i++){
        	if (cursum == 0){
        		low = i; high = i;
        	}
            cursum += A[i];
            maxsum = maxsum > cursum ? maxsum : cursum;
            high = maxsum > cursum ? high : i;
            cursum = cursum < 0 ? 0 : cursum;
        }
        ret[0] = low; ret[1] = high; ret[2] = maxsum;
        return ret;
    }
    
    
    /**
     * Problem #54
     * Spiral Matrix
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new LinkedList<Integer>();
        if (matrix == null || matrix.length == 0 )
            return ret;
        for (int i=0; i < matrix.length; i++)
            if (matrix[i] == null || matrix[i].length == 0)
                return ret;
        int m = matrix.length, n = matrix[0].length, total = m*n, cnt = 0;
        int level = 0, i=0, j= 0;
        while(cnt < total){
            while (j < n-level && cnt < total) {
                ret.add(matrix[i][j]);
                cnt++; j++;
            }
            i++; j--;
            while (i < m-level && cnt <total){
                ret.add(matrix[i][j]);
                cnt++; i++;
            }
            i--; j--;
            while (j >= level && cnt < total){
                ret.add(matrix[i][j]);
                cnt++; j--;
            }
            i--; j++;
            while( i > level && cnt < total){
                ret.add(matrix[i][j]);
                cnt++; i--;                
            }
            i++; j++;
            level++;                
        }
        return ret;
    }
    
    /**
     * Problem #58
     * Length of Last Word
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int i = s.length()-1, len=0;
        while (i>=0 && s.charAt(i) == ' ')
            i--;
        if (i < 0) return 0;
        while (i>=0 && s.charAt(i) != ' '){
            i--; len++;
        }
        return len;
    }

    
    /**
     * Problem #59
     * Spiral Matrix II 
     */
    public int[][] generateMatrix(int n) {
        int[][] ret = new int[n][n];
        int i = 0, j=0, layer = 0, count = 0, total = n*n;
        while (count < total){
            while ( j < n - layer && count < total){
                ret[i][j] = ++count;
                j++; 
            }
            i++; j--;
            while (i < n - layer && count < total) {
                ret[i][j] = ++count;                
                i++; 
            }
            i--; j--;
            while (j >= layer && count < total) {
                ret[i][j] = ++count;                
                j--; 
            }
            i--; j++;
            while ( i > layer && count < total) {
                ret[i][j] = ++count;
                i--;
            }
            i++; j++;
            layer++;
        }
        return ret;        
    }
    
    /**
     * Problem #60
     * Permutation Sequence
     * Put 1..n into a list with index starting with 1
     * (k-1)/(n-1)! presents the position of the first number
     */
    public String getPermutation(int n, int k) {
        LinkedList<Integer> nums = new LinkedList<Integer>();
        int fact = 1;
        for (int i = 1; i <= n; i++) {
            nums.add(i);
            fact *= i;
        }
        StringBuffer ret = new StringBuffer();
        for (k--; n > 0; n--) {
            fact /= n;
            ret.append(nums.remove(k / fact));
            k %= fact;
        }
        return ret.toString();                
    }

    
    /**
     * Problem #61
     * Rotate List 
     */
    public ListNode rotateRight(ListNode head, int k) {
    	if(head==null || head.next==null || k==0)
    		return head;
    //make it a circle, break from k position far from the head
    ListNode index=head;
    int len=1;// int len to record the length of list
    	while(index.next!=null)
        	{index=index.next; len++;}
    	index.next = head;
    	int mod = len - k % len;
    	for(int i=0; i < mod; i++)
    		index=index.next;
    	ListNode newhead = index.next;
    	index.next = null;
    	return newhead;
    }
    
    /**
     * Problem #62
     * Unique Paths
     */
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) return -1;
        int[][] paths = new int[m][n];
        for (int i = 0; i< m; i++)
            paths[i][0] = 1;
        for (int j = 0; j < n; j++)
            paths[0][j] = 1;
        for (int i = 1; i<m; i++)
            for (int j = 1; j<n; j++)
                paths[i][j] = paths[i-1][j] + paths[i][j-1];
        return paths[m-1][n-1];
    }

    /**
     * It is the permutation of right steps and down steps 
     * i.e. (m+n)!/(m! * n!)
     */
    public int uniquePaths2(int m, int n) {
        if (m == 1 || n == 1) return 1;
        m--; n--;
        if (m < n) { // Swap to make sure m >= n
            m += n;
            n = m - n;
            m = m - n;
        }
        long ret = 1;
        int  j = 1;
        /*
         *  (m+n)!/(m! * n!) = (m+n)(m+n-1)...(m+1)*m! / (m! * n!) =  (m+n)(m+n-1)...(m+1)/n!
         */
        for (int i = m+1; i <= m+n; i++, j++){
            ret *= i;
            ret /= j;
        }
        return (int)ret;
    }
    
    /**
     * Problem #63
     * Unique Paths II
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 ||
            obstacleGrid[0] == null || obstacleGrid[0].length == 0)
            return -1;
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        obstacleGrid[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i< m; i++)
            obstacleGrid[i][0] = obstacleGrid[i][0] == 1 ? 0 : obstacleGrid[i-1][0];
        for (int j = 1; j < n; j++)
            obstacleGrid[0][j] = obstacleGrid[0][j] == 1 ? 0 : obstacleGrid[0][j-1];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                obstacleGrid[i][j] = obstacleGrid[i][j] == 1 ? 0 : obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
        return obstacleGrid[m-1][n-1];
    }
    
    
    /**
     * Problem #66
     * Plus One
     */
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0)
            return null;
        for(int i = digits.length-1; i>=0; i--){
            if (++digits[i] < 10)
                break;
            digits[i] = 0;
        }
        if (digits[0] == 0){
            int[] newdigits = new int[digits.length+1];
            System.arraycopy(digits, 0, newdigits,1,digits.length);
            newdigits[0] = 1;
            return newdigits;
        }
        return digits;
    }
    
    /**
     * Problem #67
     * Add Binary
     */
    public String addBinary1(String a, String b) {
        if (a == null || a.isEmpty()) return b;
        if (b == null || b.isEmpty()) return a;
        StringBuffer ret = new StringBuffer();
        int i = a.length()-1, j = b.length()-1;
        int c=0;
        while (i >= 0 || j >= 0 || c > 0){
            int sum = c + 
                (i < 0 ? 0 : a.charAt(i--) - '0') + 
                (j < 0 ? 0 : b.charAt(j--) - '0');
            ret.insert(0, (char) (sum % 2 +'0'));
            c = sum >> 1;
        }
        return ret.toString();
    }
    
    /**
     * Problem #69
     * Sqrt(x)
     */
    public int mySqrt(int x) {
        if (x <= 1) return x;
        int s = 0, b = x, m = (s+b)/2;
        while (b-s > 1){
            int mid = x/m;
            if (m  == mid)
                break;
            else if ( m < mid)
                s = m;
            else
                b = m;
            m = (s+b)/2;
        }
        return m;
    }
    
    /**
     * Problem #70
     * Climbing Stairs
     * DP, current solution is the sum of the previous two
     */
    public int climbStairs(int n) {
    	if (n <= 1) return n; 
    	int[] ways = new int[n+1];
    	ways[0] = 1; ways[1] = 1;
        for (int i = 2; i <= n; i++)
            ways[i] = ways[i-1] + ways[i-2];
        return ways[n];
    }
    
    /* A simplified version where b stores the sum of the previous one */
    public int climbStairs2(int n) {
    	int a = 1, b = 1;
    	while (n-- > 0)
    		a = (b += a) - a;
    	return a;
    }
    
    
    /**
     * Problem #75
     * Sort Colors
     */
    public void sortColors(int[] nums) {
        if (nums == null) return ;
        int i = 0, low = 0, high = nums.length-1;
        while (i <= high){
            if (nums[i] == 0){
                int tmp = nums[low];
                nums[low++] = nums[i];
                nums[i] = tmp;
                // Since "low" starts with 0, if it contains number bigger than 1, 
                // It will be already moved to the end of the array, i.e. it only
                // contains number 0 or 1. So, i++
                i++;
            } else if (nums[i] == 2){
                int tmp = nums[high];
                nums[high--] = nums[i];
                nums[i] = tmp;
                // The number located in the end of the array could be any number
                // So don't change i and check nums[i] again
            }else
                i++;
        }
    }

    /**
     * Problem #78
     * Subsets     
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (nums == null) return ret;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++){
        	// Copy existing elements
            List<List<Integer>> tmp = new LinkedList<List<Integer>>();
            for (List<Integer> item : ret)
                tmp.add(new LinkedList<Integer>(item));
            // Add new number to the end of each element
            for (List<Integer> item : tmp)
                item.add(nums[i]);
            // Add the single element 
            List<Integer> single = new LinkedList<Integer>();
            single.add(nums[i]);
            tmp.add(single);
            ret.addAll(tmp);
        }
        // Add the empty set
        ret.add(new LinkedList<Integer>());
        return ret;
    }
    
    /**
     * Problem #82
     * Remove Duplicates from Sorted List II
     */
    public ListNode deleteDuplicatesII(ListNode head) {
      	ListNode dummy = new ListNode(0);
      	dummy.next = head;
		ListNode pre = dummy, cur = head;
        while (cur != null && cur.next != null){
            int count = 0;
            while (cur.next != null && cur.val == cur.next.val ){
                cur.next = cur.next.next;
                count++;
            }
            if (count > 0)
                pre.next = cur.next;
            else
        	    pre = cur;
            cur = cur.next;
      }
      return dummy.next;	
    }
     	
    /**
     * Problem #83
     * Remove Duplicates from Sorted List
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode pre = head;
        ListNode cur = head.next;
        while(cur != null){
            if (pre.val != cur.val){
                pre = pre.next;
            }else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }
    
    /**
     * Problem #86
     * Partition List
     * Create two dummy list nodes. The first one is used to link nodes with smaller values and the other one is used to
     * link nodes with larger values. Connect the two lists in the end;
     */

    public ListNode partition(ListNode head, int x) {
        ListNode h1 = new ListNode(0);
        ListNode h2 = new ListNode(0);
        ListNode t1 = h1, t2 = h2;
        while (head != null){
            if (head.val < x){
                t1.next = head;
                t1 = t1.next;
            }else{
                t2.next = head;
                t2 = t2.next;
            }
            head = head.next;
        }
        // Connect the two lists and have the tail of the second one point to null.
        t1.next = h2.next;
        t2.next = null;
        return h1.next;
    }
    
    /**
     * Problem #88
     * Merge Sorted Array
     * Assume nums1 has sufficient space for two arrays.
     */
    
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m+n;
        while (m > 0 && n > 0)
            if (nums1[m-1] > nums2[n-1])
                nums1[--len] = nums1[--m];
            else
                nums1[--len] = nums2[--n];
        while (n > 0)
            nums1[--len] = nums2[--n];
    }
    
    /**
     * Problem #91
     * Decode Ways
     * DP. Start with the last character
     */
    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        int b = 1;
        int a = s.charAt(n-1) == '0' ? 0 : 1;
        for(int i = n-2; i >= 0; i--){
            if (s.charAt(i) == '0'){
                b = a;
                a = 0;
                continue;
            }
            int new_a = Integer.valueOf(s.substring(i,i+2)).intValue() <= 26 ? a + b : a;
            b = a; a = new_a;
        }
        return a;
    }
    
    /*
     * Search for all combinations 
     */
    public List<List<Integer>> numDecodingsA(String s) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (s == null || s.isEmpty() || s.charAt(0) == '0') return ret;
        if (s.length() == 1 && !s.equals("0")){
            List<Integer> item = new LinkedList<Integer>();
            item.add(Integer.valueOf(s).intValue());
            ret.add(item);
            return ret;
        }
        List<List<Integer>> subret1 = numDecodingsA(s.substring(1));
        for (List<Integer> item : subret1)
            item.add(0, Integer.valueOf(s.charAt(0)-'0'));
    
        if (s.length() == 2 && Integer.valueOf(s) <= 26){
            List<Integer> item = new LinkedList<Integer>();
            item.add(Integer.valueOf(s));
            subret1.add(item);
            return subret1;
        }
        if (s.length() > 2 && Integer.valueOf(s.substring(0,2)).intValue() <= 26){
            List<List<Integer>> subret2 = numDecodingsA(s.substring(2));
            int a = Integer.valueOf(s.substring(0,2)).intValue();
            for (List<Integer> item : subret2)
                item.add(0, a);
            for (List<Integer> item : subret2)
               subret1.add(item); 
        }
        return subret1;
    }
    
    
    /**
     * Problem #92
     * Reverse Linked List II 
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head, newhead=null, next;
        int i = 0;
        while (i < m-1) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        ListNode tail = cur;
        while (i < n ){
            next = cur.next;
            cur.next = newhead;
            newhead = cur;
            cur = next;
            i++;
        }
        if (newhead != null){
            pre.next = newhead;
            tail.next = cur;                    
        }
        return dummy.next;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0), subhead = new ListNode(0), subtail = new ListNode(0);
        ListNode pre = dummy, cur = head;
        dummy.next = head;
        int i = 1;
        while (i <= n){
            ListNode next = cur.next;
            if ( i < m){
                pre = cur;
            } else if (i == m){
                subtail = cur;
                subhead.next = cur;
            } else{
                cur.next = subhead.next;
                subhead.next = cur;
            }
            cur = next;
            i++;
        }
        pre.next = subhead.next;
        subtail.next = cur;
        return dummy.next;
    }
    
    
    /**
     * Problem #94
     * Binary Tree Inorder Traversal
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        inTrav_helper(root, ret);
        return ret;        
    }

    public void inTrav_helper(TreeNode root, List<Integer> ret){
        if (root == null) return;
        inTrav_helper(root.left, ret);
        ret.add(root.val);
        inTrav_helper(root.right, ret);
        return;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        if (root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        do{
            while (cur != null){
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            ret.add(cur.val);
            cur = cur.right;            
        } while (!st.isEmpty() || cur != null);
        return ret;        
    }
    
    /**
     * Problem #98
     * Validate Binary Search Tree
     */

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    public boolean isValidBST(TreeNode root, TreeNode minnode, TreeNode maxnode) {
        if (root == null) return true;
        if (minnode != null && root.val <= minnode.val || maxnode != null && root.val >= maxnode.val)
            return false;
        return isValidBST(root.left, minnode, root) && isValidBST(root.right, root, maxnode);
    }

    
    /**
     * Problem #100
     * Same Tree
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null)
            return p.val == q.val && isSameTree(p.left, q.left) && 
                    isSameTree(p.right, q.right);
        else
            return p == q;
    }

    /**
     * Problem 101
     * Symmetric Tree
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        else
            return isSymm_aux(root.left, root.right);
    }

    public boolean isSymm_aux(TreeNode a, TreeNode b){
        if (a != null && b != null)
            return a.val == b.val && isSymm_aux(a.left, b.right) && isSymm_aux(a.right, b.left);
        else
            return a == b;
    }

    public boolean isSymmetric2(TreeNode root) {
        Stack<TreeNode> left = new Stack<TreeNode>();
        Stack<TreeNode> right = new Stack<TreeNode>();
        left.push(root); right.push(root);
        while(!left.isEmpty()){
            TreeNode l = left.peek();
            TreeNode r = right.peek();
            if ( (l==null ^ r==null) || l != null && r != null && l.val != r.val)
                break;
            left.pop(); right.pop();
            if (l != null && r != null){
                left.push(l.left); left.push(l.right);
                right.push(r.right); right.push(r.left);
            }
        }
        return left.isEmpty();
    }
    
    /**
     * Problem #102
     * Binary Tree Level Order Traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode dummy = new TreeNode(0);
        q.add(root); 
        q.add(dummy);
        List<Integer> item = new LinkedList<Integer>();
        TreeNode cur;
        while ( !q.isEmpty()){
            cur = q.poll();
            if (cur == dummy){
                ret.add(new LinkedList<Integer>(item));
                item.clear();
                if (q.peek() != null)
                    q.add(cur);
            }else{
                item.add(cur.val);
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            }
        }
        return ret;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        List<TreeNode> level = new LinkedList<TreeNode>();
        level.add(root);
        while (true){
            if (level.isEmpty())
                break;
            List<TreeNode> nextlevel = new LinkedList<TreeNode>();
            List<Integer> item = new LinkedList<Integer>();
            for (TreeNode t : level){
                item.add(t.val);
                if (t.left != null)
                    nextlevel.add(t.left);
                if (t.right != null)
                    nextlevel.add(t.right);
            }
            ret.add(item);
            level = nextlevel;
        }
        return ret;
    }
    
    /**
     * Problem #104
     * Maximum Depth of Binary Tree
     */
    public int maxDepth(TreeNode root) {
        return maxdep_aux(root);        
    }

    public int maxdep_aux(TreeNode root){
    	if (root == null) return 0;
    	int left = maxdep_aux(root.left);
    	int right = maxdep_aux(root.right);
    	return left > right ? left + 1 : right + 1;
    }

    /**
     * Problem #107
     * Binary Tree Level Order Traversal II
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode magic = new TreeNode(0);
        q.add(root); q.add(magic);
        List<Integer> item = new LinkedList<Integer>();
        while(!q.isEmpty()){
            TreeNode cur = q.poll();
            if (cur != magic){
                item.add(cur.val);
                if (cur.left != null)
                    q.add(cur.left);
                if (cur.right != null)
                    q.add(cur.right);
            } else {
                ret.add(0,item);
                item = new LinkedList<Integer>();
                if (!q.isEmpty())
                    q.add(magic);        
            }
        }
        return ret;
    }

    
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        List<TreeNode> level = new LinkedList<TreeNode>();
        level.add(root);
        while (!level.isEmpty()){
            List<Integer> item = new LinkedList<Integer>();
            List<TreeNode> nextlevel = new LinkedList<TreeNode>();
            for (TreeNode tn : level){
                item.add(tn.val);
                if (tn.left != null)
                    nextlevel.add(tn.left);
                if (tn.right != null)
                    nextlevel.add(tn.right);
            }
            ret.add(0,item);
            level = nextlevel;
        }
        return ret;
    }
    
    /**
     * Problem #108
     * Convert Sorted Array to Binary Search Tree 
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        return satobst(nums, 0, nums.length-1);
    }

    public TreeNode satobst(int[] nums, int low, int high){
        if (low == high)
            return new TreeNode(nums[low]);
        int mid = (low + high)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = mid - low < 1 ? null : satobst(nums, low, mid-1);
        root.right = satobst(nums, mid+1, high);
        return root;
    }

    /**
     * Problem #109
     * Convert Sorted List to Binary Search Tree
     */
    public TreeNode sortedListToBST(ListNode head) {
        int len = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        while (head != null){
            len++;
            head = head.next;
        }
        return sltobst(dummy, 0, len-1);
    }
    
    public TreeNode sltobst (ListNode list, int low, int high){
        if (low > high) return null;
        int mid = (low + high) / 2;
        TreeNode lc = sltobst(list, low, mid-1);
        TreeNode p = new TreeNode(list.next.val);
        list.next = list.next.next;
        TreeNode rc = sltobst(list, mid+1, high);
        p.left = lc; p.right = rc;
        return p;
    }
    
    /** Problem #110
     * Balanced Binary Tree
     * Check the height when computing tree height
     */
    public boolean isBalanced(TreeNode root) {
        return isBal_aux(root) >= 0;         
    }
    
    public int isBal_aux(TreeNode root){
        if (root == null) return 0;
        int ld = 0, rd = 0;
        if ((ld = isBal_aux(root.left)) < 0) return ld;
        if ((rd = isBal_aux(root.right)) < 0) return rd;
        return Math.abs(ld-rd) < 2 ? ((ld > rd) ? ld + 1 : rd + 1) : -1; 
    }
    
    /**
     * Problem #111
     * Minimum Depth of Binary Tree
     * Note: 1. if a node's left or right child is null, it only counts the other one
     * 2. count in the root.
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int l = root.left == null ? 0 : minDepth(root.left);
        int r = root.right == null ? 0 : minDepth(root.right);
        if (l != 0 && r != 0)
            return l < r ? l + 1 : r + 1; 
        else
            return l==0 ? r+1 : l+1;
    }
    
    /**
     * Problem #112
     *  Path Sum 
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        int newsum = sum - root.val;
        if (newsum == 0 && root.left == null && root.right == null)
            return true;
        return hasPathSum(root.left, newsum) || hasPathSum(root.right, newsum);
    }

    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) return false;
        boolean ret = false;
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        TreeNode cur;
        st.push(root);
        while(!st.isEmpty()){
            cur = st.peek();
            if(!hs.contains(cur)){
                hs.add(cur);
                sum -= cur.val;
                if (cur.right != null)
                    st.push(cur.right);
                if (cur.left != null)
                    st.push(cur.left);
            } else {
                st.pop();
                if (cur.left == null && cur.right == null && sum == 0){
                    ret = true;
                    break;
                }else
                    sum += cur.val;
            }
        }
        return ret;
    }
    
    /**
     * Problem #113
     * Path Sum II 
     */
    private List<List<Integer>> ret_pathsum;    
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ret_pathsum = new LinkedList<List<Integer>>();
        if (root == null) return ret_pathsum;
        psum_aux(root, sum, new LinkedList<Integer>());
        return ret_pathsum;
    }
    
    public void psum_aux(TreeNode root, int sum, List<Integer> subret){
        int newsum = sum-root.val;
        if (newsum ==0 && root.left == null && root.right == null){
            List<Integer> item = new LinkedList<Integer>(subret);
            item.add(root.val);
            ret_pathsum.add(item);
        }
        if (root.left != null){
            subret.add(root.val);
            psum_aux(root.left, newsum, subret);
            subret.remove(subret.size()-1);
        }
        if  (root.right != null){
            subret.add(root.val);
            psum_aux(root.right, newsum, subret);
            subret.remove(subret.size()-1);
        }        
        return;
    }
    
    public List<List<Integer>> pathSum2(TreeNode root, int sum) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        if (root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        List<Integer> subret = new LinkedList<Integer>();
        TreeNode cur;
        st.push(root);
        while (!st.isEmpty()){
            cur = st.peek();
            if (!hs.contains(cur)){
                hs.add(cur);
                sum -= cur.val;
                subret.add(cur.val);
                if (cur.right!=null)
                    st.push(cur.right);
                if (cur.left != null)
                    st.push(cur.left);
            } else {
                st.pop();
                if (cur.left == null && cur.right == null && sum == 0){
                    List<Integer> item = new LinkedList<Integer>(subret);
                    ret.add(item);
                }
                sum += cur.val;
                subret.remove(subret.size()-1);
            }
        }
        return ret;        
    }    
    
    
    /**
     * Problem #114
     * Flatten Binary Tree to Linked List
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        faltten_aux(root);
        return;
    }

    /**
     * Recursively flat left and right child trees
     * Return the last node (leaf) of the flatten left and right child trees
     * Move the flatten left to the right, set the right child as the right child the last leaf of flatten left child
     * Return the last child of flatten right child
     */
    
    public TreeNode faltten_aux(TreeNode root){
        if (root.left == null && root.right == null)
            return root;
        TreeNode ltail = root.left == null? null : faltten_aux(root.left);
        TreeNode rtail = root.right == null? null : faltten_aux(root.right);
        if (ltail != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = null; // No left child any more
            ltail.right = tmp;
        }
        return rtail == null ? ltail : rtail;
    }
    
    /**
     * Problem #136
     * Single Number
     * All numbers appear twice except one, find it.
     */
    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;
        int ret = nums[0];
        for(int i=1; i<nums.length; i++)
            ret ^= nums[i];
        return ret;
    }

    /**
     * Problem #137
     * Single Number II: All numbers appear three times except one, find it.
     */
    public int singleNumberII(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;
        int ones = 0, twos = 0, threes = 0;
        for (int x : nums){
            threes = twos & x;
            twos = (ones & x | twos) & (~threes);
            ones = (ones | x) & (~threes);
        }
        return ones;
    }    

    
    /**
     * Problem #138
     * Copy List with Random Pointer
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return head;        
        HashMap<RandomListNode, RandomListNode> map = new  HashMap<RandomListNode, RandomListNode>();
        RandomListNode cur = head;
        /* Loop 1: create the copied list*/
        while(cur != null){
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        cur = head;
        /* Loop 2: set up next and random connections */
        while(cur != null){
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    
    /**
     * Problem #141
     * Linked List Cycle
     * A fast pointer and a slow pointer.
     * If there is a cycle, the fast pointer will catch the slow one.
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                return true;
        }
        return false;
    }
    
    /**
     * Problem #142
     * Linked List Cycle II
     * Test if the linked list has cycle first.
     * If so, suppose the step from head, say H, to the beginning node of the cycle, say C, is d_HC = x,
     * Suppose fast and slow meet at the node of R and d_CR = y and d_RC = z
     * We know the fast pointer moves x + y + z + y steps and the slow pointer moves x + y steps,
     * and x + y + z + y = 2 * (x + y)  ==> x = z
     * Then we move the fast pointer back to the head, and move both the fast and the slow pointer at the same pace.
     * They will meet at Node C.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        boolean hascycle = false;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow){
                hascycle = true;
                break;
            }
        }
        if (!hascycle) return null;
        fast = head;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
    
    
    /**
     * Problem #143
     * Reorder List
     * Use a fast and a slow pointer to find the middle of the list
     * Reverse the second part of the list
     * Mix the first part of the list with the reversed second part of the list 
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode second = slow.next;
        slow.next = null;
        second = reverseList3(second);
        
        ListNode first = head, tmp;
        while (second != null){
            tmp = first.next;
            first.next = second;
            second = second.next;
            first.next.next = tmp;
            first = tmp;
        }
        return;
    }
    
    public ListNode reverseList3(ListNode head){
        if (head == null) return head;
        ListNode newhead = null;
        ListNode cur, next;
        for (cur = head; cur !=null; cur = next){
            next = cur.next;
            cur.next = newhead;
            newhead = cur;
        }
        return newhead;
    }   
    
    /**
     * Problem #144
     * Binary Tree Preorder Traversal 
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        preTrav_aux(root, ret);
        return ret;
    }
    
    public void preTrav_aux(TreeNode root, List<Integer> ret){
        if (root == null) return;
        ret.add(root.val);
        preTrav_aux(root.left, ret);
        preTrav_aux(root.right, ret);
        return;
    }
    
    public List<Integer> preorderTraversal_NR(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        if (root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode cur = st.pop();
            ret.add(cur.val);
            if (cur.right != null)
                st.push(cur.right);
            if (cur.left != null)
                st.push(cur.left);
        }
        return ret;
    }

    /**
     * Problem #145
     * Binary Tree Postorder Traversal
     */
    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        postTrav_aux(root, ret);
        return ret;
    }

    public void postTrav_aux(TreeNode root, List<Integer> ret){
        if (root == null) return;        
        postTrav_aux(root.left, ret);
        postTrav_aux(root.right, ret);
        ret.add(root.val);
        return;
    }    

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> ret = new LinkedList<Integer>();
        if (root == null) return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode cur = st.peek();
            if (hs.contains(cur)){
                ret.add(cur.val);
                st.pop();
            } else {
                hs.add(cur);
                if (cur.right != null)
                    st.push(cur.right);
                if (cur.left != null)
                    st.push(cur.left);
            }
        }
        return ret;        
    }
    
    /**
     * Problem #147
     * Insertion Sort List
     * If a node's value is smaller than the previous one, scan from the beginning and find the position the node should be inserted
     * In case the node might be inserted at the beginning, we need a dummy node.
     */
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);   
        dummy.next = head;
        ListNode check = dummy;
        ListNode cur = head;
        boolean inserted = false;
        while ( cur != null && cur.next!=null){
            if (cur.val > cur.next.val){
                while (true){
                    if (check.next.val > cur.next.val){
                    	// Insert operations
                        ListNode curtmp = cur.next;
                        cur.next = cur.next.next;
                        ListNode checktmp = check.next;
                        check.next = curtmp;
                        check.next.next = checktmp;
                        inserted = true;
                        break;
                    }
                check = check.next;
                }
            }
            // If an insertion occurs, the "cur" point does not move.
            if (!inserted)
                cur = cur.next;
            inserted = false;
            check = dummy;            
        }
        return dummy.next;
    }
    
    /**
     * Problem #148
     * Sort List
     */

    public ListNode sortList(ListNode head) {
        return quicksortList(head, null);        
    }
    
    public ListNode quicksortList(ListNode head, ListNode tail){
        if (head == tail) return head;
        boolean equal = true;
        ListNode left = head, right = head;
        int x = head.val;
        ListNode cur = head.next;
        /* 
         * Move the nodes with small values to the left of head
         * Move the nodes with equal or large value to the right of head
         * Recursively sort the list on the left and right until head meets tail
         */
       
        while (cur != tail){
            if (cur.val != x ) equal = false;
            ListNode next = cur.next;
            if (cur.val < x){
                cur.next = left;
                left = cur;
            }else{
                right.next = cur;
                right = cur;
            }
            cur = next;
        }
        // If all nodes' values are same, no more recursion required.	
        if (equal)
            return head;
        right.next = tail;
        head.next = quicksortList(head.next, tail);
        return quicksortList(left,head);
    }
    
    /**
     * Problem #153
     * Find Minimum in Rotated Sorted Array
     */

    public int findMin(int[] num) {
        if (num == null || num.length == 0)
            return Integer.MIN_VALUE;
        int s = 0, e = num.length-1, mid = (s+e)/2;
        while (e-s > 1){
            if (num[mid] < num[e])
            // If you are looking for the largest value,
            // compare num[mid] with num[s], and search in the other side
                e = mid;
            else
                s = mid;
            mid = (s+e)/2;
        }
        return num[s] < num[e] ? num[s] : num[e];
    }

    /**
     * Problem #154
     * Find Minimum in Rotated Sorted Array II
     */
    public int findMinII(int[] nums) {
        if (nums == null || nums.length == 0)
            return Integer.MIN_VALUE;
        int s = 0, e = nums.length - 1, mid = (s+e)/2;
        while (e-s > 1){
            if (nums[mid] < nums[e])
                e = mid;
            else if (nums[mid] == nums[e])
                e--;
            else if (nums[s] < nums[mid])
                s = mid;
            else if (nums[s] == nums[mid])
                s++;
            mid = (s+e)/2;
        }
        return nums[s] < nums[e] ? nums[s] : nums[e];
    }
    
    
    /**
     * Problem #160
     * Intersection of Two Linked Lists
     * Calculate d, the difference of the two lists' length
     * Have the longer move d steps first, then move the both lists together
     * until they hit or one of them comes to the end (no intersection)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lensub = getLen(headA) - getLen(headB);
        ListNode curA = headA, curB = headB;
        if (lensub > 0)
            for (int i=0; i<lensub; i++)
                curA = curA.next;
        else
            for(int i=0; i<-lensub; i++)
                curB = curB.next;
        while (curA != curB && curA != null && curB != null){
            curA = curA.next;
            curB = curB.next;
        }
        return curA == null ? curA : curB;
    }

    public int getLen(ListNode head){
        int ret = 0;
        ListNode cur = head;
        while (cur != null){
            cur = cur.next;
            ret++;
        }
        return ret;
    }
    
    /**
     * Problem #169
     * Majority Element
     * Because the majority element is the element that appears more than n/2 times.
     * Count a number and decount the number. The majority one will be left. 
     */
    public int majorityElement(int[] nums) {
    	int max = 0, count = 0;
        for(int i = 0; i<nums.length; i++){
          if (count == 0){
            max = nums[i];
            count = 1;
            continue;
          }
          count = max == nums[i] ? count+1 : count-1;
        }
        return max;
    }
    
    
    /**
     * Problem #179
     * Largest Number
     * Observation: for two numbers a and b, we want to compare which one is bigger, ab or ba.
     * Actually it is determining which one lexicographically proceeds the other one.
     * Design a new class named NumStr which implements Comparable<T>, and override compareTo function, 
     * which will be called by Arrays.sort().
     * When returning the final value, be careful the case where only 0s are listed in the original array.
     */
    class NumStr implements  Comparable<NumStr> {
        String numstr;
        public NumStr(int x) {
            numstr = String.valueOf(x);
        }
        
        @Override
        public int compareTo(NumStr o) {
            String s1 = this.numstr + o.numstr;
            String s2 = o.numstr + this.numstr;
            return s1.compareTo(s2);
        }
    }
        
    public String largestNumber(int[] nums) {
    	if (nums == null || nums.length == 0) return "";
    	NumStr[] numstrs = new NumStr[nums.length];
        for (int i = 0; i<nums.length; i++){
        	numstrs[i] = new NumStr(nums[i]);
        }
        Arrays.sort(numstrs);
        StringBuffer sb = new StringBuffer();
        for(int i=numstrs.length-1 ; i>=0; i--){
        	sb.append(numstrs[i].numstr);
        }
        return numstrs[numstrs.length-1].numstr.equals("0")? "0" : sb.toString();
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
     * Problem No. 203
     * Remove Linked List Elements
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;
        ListNode cur = head;
        while (cur.next != null){
            if (cur.next.val == val)
                cur.next = cur.next.next;          
            else
	            cur = cur.next;        
        }
        return head.val == val ? head.next : head;
    }    
    
    
    /**
     * Problem #206
     * Reverse Linked List 
     */
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        while (cur != null){
            ListNode next = cur.next;
            cur.next = dummy.next;
            dummy.next = cur;
            cur = next;
        }
        return dummy.next;
    }
    
    public ListNode reverseList2(ListNode head) {
    	if (head == null || head.next == null)
    		return head;
    	ListNode newhead = reverseList(head.next);
    	ListNode cur = newhead;
    	while (cur.next != null)
    		cur = cur.next;
    	cur.next = head;
    	head.next = null;
    	return newhead;
    }    

    /**
     * Problem #222
     * Count Complete Tree Nodes
     * Check the height of left and right child
     * If they are same, calculate the height of the left child by complete tree's feature, and recursion on the right
     * Otherwise, left child should be deeper than the right one, and the right one should already be full
     * then calculate the ndoes of the right child and recursion on the left child
     */

    public int countNodes(TreeNode root) {
        if (root == null) return 0;        
        int leftheight = getHeight(root.left);
        int rightheight = getHeight(root.right);
        if (leftheight == rightheight)
            return (1 << leftheight) + countNodes(root.right);
        else
            return countNodes(root.left) + (1 << rightheight);
    }
    
    public int getHeight(TreeNode root){
        int ret = 0;
        while(root != null){
            ret++;
            root = root.left;
        }
        return ret;
    }
    
    /**
     * Problem #225
     * Implement Stack using Queues
     * See LeetStack.java
     */
    
    /**
     * Problem #226
     * Invert Binary Tree
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;
        return root;        
    }
    
    /**
     * Problem #234
     * Palindrome Linked List
     */
    private ListNode root;
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        root = head;
        return isPal_aux(head);
    }
   
    public boolean isPal_aux(ListNode head){
        boolean flag = true;
        if (head.next != null)
            flag = isPal_aux(head.next);
        if (flag && head.val == root.val){
            root = root.next;
            return true;
        }
        return false;
    }
    
    /**
     * Problem #235
     * Lowest Common Ancestor of a Binary Search Tree
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
    	if (root == null || p == null || q == null) return null;
    	if (root.val >= p.val && root.val <= q.val || root.val <= p.val && root.val >= q.val)
    		return root;
    	if (root.val <= p.val && root.val <= q.val)
    		return lowestCommonAncestorBST(root.right, p, q);
  
    	if (root.val >= p.val && root.val >= q.val)
    		return lowestCommonAncestorBST(root.left, p, q);       
    	return null;  
    }    
    
    /**
     * Problem #236
     * Lowest Common Ancestor of a Binary Tree
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    	
      	if (root == null || root == p || root == q)
          return root;
      	TreeNode retl = lowestCommonAncestor(root.left, p, q);
      	TreeNode retr = lowestCommonAncestor(root.right, p, q);
    	if (retl != null && retr != null)
    		// If both children are not null, it means they are ancestor of p or q respectively
    		return root;
      	else 
      		// Or one of them is the ancestor of p or q, and the other one has nothing
      		// Return the non-null one
      		return retl == null ? retr : retl;
    }
    
    /**
     * Problem #237
     * Delete Node in a Linked List
     * A tricky problem. It does not require remove the tail,
     * So just copy the value of the next node to the current one and skip the next one. 
     */
    public void deleteNode(ListNode node) {
        if (node != null && node.next != null){
            node.val = node.next.val;
            node.next = node.next.next;
        }
        return;
    }
    
    /**
     * Problem #242
     * Valid Anagram
     */
    public boolean isAnagram(String s, String t) {
        if (s == null && t == null) return true;
        if ((s != null) ^ (t!=null)) return false;
        if (s.length() != t.length()) return false;
        int[] log = new int[26];
        for (int i : log)
            i = 0;
        for (int i = 0; i < s.length(); i++){
            log[s.charAt(i) - 'a']++;
            log[t.charAt(i) - 'a']--;
        }
        for (int i : log)
            if ( i != 0) return false;
        return true;
    }
    
    /**
     * Problem #257
     * Binary Tree Paths
     */
    private List<String> btp_ret;
    public List<String> binaryTreePaths(TreeNode root) {
        btp_ret = new LinkedList<String>();
        if (root == null)
            return btp_ret;
        if (root.left != null)
            btp_aux(root.left, String.valueOf(root.val));
        if (root.right != null)
            btp_aux(root.right, String.valueOf(root.val));
        if (root.left == null && root.right == null)
            btp_ret.add(String.valueOf(root.val));
        return btp_ret;      
    }

    public void btp_aux(TreeNode root, String path){
        if (root.left == null && root.right == null){
            String finalpath = new String(path);
            finalpath += "->" + root.val;
            btp_ret.add(finalpath);
            return;
        }
        if (root.left != null)
            btp_aux(root.left, path + "->" + root.val);
        if (root.right != null)
            btp_aux(root.right, path + "->" + root.val);
        return;
    }

    /**
     * Problem #999
     * Consecutive sum: check if an integer array has a consecutive numbers whose sum equals to the target
     * Set a sliding window, starting with a number leftmost,
     * Accumlate numbers from left to right. If the sum is smaller than the target, move forward;
     * Otherwise, move the sliding window towards right  
     */
    public boolean conSum(int[] nums, int target){
    	if (nums == null || nums.length == 0)
    		return false;
    	int low = 0, i = 0, sum = 0;
    	while(i < nums.length){
    		if (sum == target)
    			return true;
    		else if ( sum < target){
    			sum += nums[i];
    			i++;
    		} else {
    			while (sum > target)
    				sum -= nums[low++];
    			i = low;
    		}
    	}
    	return false;
    }
    /**
     * Simple utilities
     */
    int min (int a, int b){
    	return (a > b) ? b : a;
    }
}
