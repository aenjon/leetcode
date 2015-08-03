package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.List;

class Duplicated {
	boolean val;
	Duplicated(boolean x){
		val = x;
	}
}


public class LeetCode {

	/*
    public int threeSumClosest(int[] num, int target) {
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    }
    */

	int prev = Integer.MIN_VALUE;
	
	TreeNode pre_node = new TreeNode(Integer.MIN_VALUE);
	TreeNode s1=null, s2=null;

	int maxpathsum = Integer.MIN_VALUE;
	
	/**
	 * Rotate Array 
	 */
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) return;
        int n = nums.length;
        while ( n>0 && (k %= n) > 0 ){
        	int range = n - k;
        	for(int i=1; i<=range; i++){
        		int val = nums[n-i];
        		nums[n-i] = nums[n-i-k];
        		nums[n-i-k] = val;
        	}
        	n = k;
        	k = n - (range % k);
        }
    }
	
	/**
	 * Reverse Bits 
	 */
    public int reverseBits(int n) {
    	int newint = 0;
    	for (int i = 0; i < 32; i++){
    		newint += (n & 1);
    		n >>= 1;
    		newint = i < 31 ? newint << 1 : newint;
    	}
    	return newint;
    }
	
	/**
	 * Number of 1 Bits
	 */
    public int hammingWeight(int n) {
    	int ones = 0;
    	for (int i=0; i< 32; i++){
    		ones = (n & 1) == 1 ? ones+1 : ones;
    		n >>= 1;
    	}
    	return ones;
    }
	/**
	 * Regular Expression Matching
	 */
	
	public boolean isMatch2(String s, String p){
		int i, j;
		int m = s.length();
		int n = p.length();
		boolean b[][] = new boolean[m+1][n+1];
		
		b[0][0] = true; 	// If both are empty, they match
		
		/*
		 * If s is not empty but p is empty, then the result is No Match.
		 * i.e. b[i][0] = false for i > 0
		 */
		for (i = 0; i < m; i++) 
			b[i+1][0] = false; 
		
		/*
		 * p[j] matches an empty string, iff
		 * p[j] is '*' and p[j-2] matches (and p[j-1] could be any char)
		 * i.e. b[0][j-1] is true (p[j-2] matches
		 */
		for (j = 0; j < n; j++)
			b[0][j+1] = j > 0 && p.charAt(j) == '*' && b[0][j-1];
		
		for (i = 0; i < m; i++)
			for (j = 0; j < n; j++){
				if (p.charAt(j) != '*')
					b[i+1][j+1] = b[i][j] && (p.charAt(j) == s.charAt(i) || '.' == p.charAt(j));
				else
					b[i+1][j+1] = b[i+1][j-1] && j > 0 // p[j-2] matches and there is zero p[j-1] in s 
									|| b[i+1][j] // there is at least one p[j-1]
									|| b[i][j + 1] && j > 0 && ('.' == p.charAt(j-1) || s.charAt(i) == p.charAt(j-1));		
			}
		return b[m][n];
	}
	
	public boolean isMatch(String s, String p) {
		if ( p == null || p.isEmpty())
			return ( s == null || s.isEmpty());
		return isMatch_help(s, 0, p, 0);
	}
	
	private  boolean isMatch_help(String s, int s_index, String p, int p_index){
		if ( p_index >= p.length() ) return (s_index >= s.length());
		
		if (p_index < p.length()-1){ // P has at least two more chars
			if (p.charAt(p_index+1) != '*'){ // no '*' followed for current character
				if (!matchChar(s,s_index,p,p_index)) return false;
				return isMatch_help(s,s_index+1,p,p_index+1);
			}else{ // a '*' follows the current char
				if (isMatch_help(s,s_index,p,p_index+2)) return true; // Try if the length of current char is zero
				while(matchChar(s,s_index,p,p_index)) // Try all possible lengths
					if (isMatch_help(s,++s_index,p,p_index+2)) return true;
				return false;
			}
			
		}else{ // Reach to the end of p
			return (s_index < s.length()-1) ? false : matchChar(s, s_index, p, p_index); 
		}
	}
	
	private boolean matchChar(String s, int s_index, String p, int p_index){
		if (p_index >= p.length() || s_index >= s.length())
			return false;
		else
			return ( s.charAt(s_index) == p.charAt(p_index) ||
				p.charAt(p_index) == '.' && s_index < s.length());
	}
	/*
    public boolean isMatch(String s, String p) {
    	if (s == null || s.length() == 0) return true;
    	if (p == null || p.length() == 0) return false;
    	boolean result = false;
    	int sp = 0, pp1 = 0, pp2 = 0;
    	char prechar = p.charAt(pp2);
    	while (sp<s.length() && pp1 < p.length() && pp2 < p.length()){
    		// Should not start with '*'
    		if (p.charAt(pp1) == '*'){
    			pp1++;
    			pp2=pp1;
    			continue;
    		}
    		
    		char char_s = s.charAt(sp);
    		char char_p = p.charAt(pp2);
    		char char_valid;
    		if (char_p == '*'){
    			if (prechar != '.'){
    				char_valid = prechar;
    			}else{
    				pp2++;
    				continue;
    			}
    		} else{
    			char_valid = char_p;
    		}
    		if (char_valid == '.' || char_valid == char_s){
    			if (sp == s.length()-1){
    				result = true;
    				break;
    			}else{
    				sp++;
    				if (char_p != '*'){
    					prechar = char_valid == '.' ? char_s : char_valid ;
    					pp2++;
    				}
    				continue;    					
    			}
    		}else{
    			if (char_p == '*'){
    				pp2++;
    				continue;
    			}else {
    				sp = 0;  
    				pp1++;
    				pp2=pp1;
    			}
    		}
    	}
    	
    	return result;
    }
    */
	/**
	 * 	Spiral Matrix II  
	 */
    public int[][] generateMatrix(int n) {
    	if (n <= 0) return new int[0][0];
    	int[][] res = new int[n][n];
    	int d = 0, rb = n-1, bb = n-1, lb = 0, tb = 1;
    	int i = 0, j = 0, k = 1;
    	boolean done = false;
    	while(!done){
    		switch (d){
    		case 0:
    			if (j<rb){
    				res[i][j] = k++; j++;
    			}else if (j == rb){
    				res[i][j] = k++;
    				d = 1; rb--; i++;
    				done = i > bb;
    			}
    			break;
    		case 1:
    			if (i<bb){
    				res[i][j] = k++; i++;
    			}else if (i == bb){
    				res[i][j] = k++;
    				d = 2; bb--; j--;
    				done = j < lb;
    			}
    			break;
    		case 2:
    			if (j > lb){
    				res[i][j] = k++; j--;
    			}else if (j == lb){
    				res[i][j] = k++;
    				d = 3; lb++; i--;
    				done = i < tb;
    			}
    			break;
    		case 3:
    			if (i>tb){
    				res[i][j] = k++; i--;
    			} else if (i==tb){
    				res[i][j] = k++;
    				d = 0; tb++; j++;
    				done = j > rb;
    			}
    			break;
    		default:
    		}
    	}
    	return res;
    }
	
	/**
	 * Spiral Matrix 
	 */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length ==0 || matrix[0].length == 0)
            return result;
        int direction = 0;
        int rb = matrix[0].length-1, bb = matrix.length-1,lb = 0, tb = 1;
        int i = 0, j = 0;
        //result.add(matrix[0][0]);
        boolean done = false;
        while(!done){
            switch (direction){
                case 0:
                    if (j < rb){
                        result.add(matrix[i][j]);
                        j++;
                    }else if (j==rb){
                        result.add(matrix[i][j]);
                        direction = 1;
                        i++;
                        rb--;
                        done = (i > bb);
                    }
                    break;
                case 1:
                    if (i < bb){
                        result.add(matrix[i][j]);
                        i++;
                    } else if (i == bb){
                    	result.add(matrix[i][j]);
                        direction = 2;
                        j--;
                        bb--;
                        done = ( j < lb);
                    }
                    break;
                case 2:
                    if (j > lb){
                        result.add(matrix[i][j]);
                        j--;
                    }else if (j == lb){
                        result.add(matrix[i][j]);
                    	direction = 3;
                    	i--;
                        lb++;
                        done = (i < tb);
                    }
                    break;
                case 3:
                    if (i > tb){
                        result.add(matrix[i][j]);
                        i--;
                    }else if (i == tb){
                        result.add(matrix[i][j]);
                    	direction = 0;
                    	j++;
                        tb++;
                        done = (j > rb);
                    }
                    break;
                default:
            }
        }
        return result;
    }	
	/**
	 * Search a 2D Matrix
	 */
    public boolean searchMatrix2(int[][] matrix, int target) {
    	if (matrix == null) return false;
    	int m = matrix.length, n = matrix[0].length;
    	int s = 0, e = m-1, mid, row;
    	boolean result = false;
    	
    	while(e>s){
    		mid = (s+e)/2;
			if (target >= matrix[mid][0] && target <= matrix[mid][n-1]){
				s = mid;
				break;
			}else if (target > matrix[mid][n-1])
				s = mid + 1;
			else
				e = mid-1;    		
    	}
    	
    	//if (s == -1)
    	//	return false;
    	row = s;
    	s = 0; e = n-1;
    	while(s<=e){
    		mid = (s+e)/2;
    		if (target == matrix[row][mid]){
    			result = true;
    			break;
    		}else if (target > matrix[row][mid])
    			s = mid+1;
    		else
    			e = mid-1;    			
    	}
    	return result;    	
    }
    
	
	
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null) return false;
    	int m = matrix.length, n = matrix[0].length;
    	int s = 0, e = m-1, mid=-1, row;
    	boolean result = false;
    	
    	while(e>s){
    		if (target < matrix[s][0]){
    			s--;
    			break;
    		}else if (target >= matrix[e][0]){
    			s = e;
    			break;
    		}
    		mid = (s+e)/2;
			if (target >= matrix[mid][0] && target <= matrix[mid][n-1]){
				s = mid;
				break;
			}else if (target >= matrix[mid][0])
				s = mid + 1;
			else
				e = mid-1;    		
    	}
    	
    	if (s == -1)
    		return false;
    	row = s;
    	s = 0; e = n-1;
    	while(s<=e){
    		mid = (s+e)/2;
    		if (target == matrix[row][mid]){
    			result = true;
    			break;
    		}else if (target > matrix[row][mid])
    			s = mid+1;
    		else
    			e = mid-1;    			
    	}
    	return result;
    }
	
	/**
	 * Find Minimum in Rotated Sorted Array II
	 */
    public int findMinII2(int[] num) {
    	if (num == null || num.length == 0)
    		return -1;
    	//if (num.length == 1) return num[0]
    	int s = 0, e = num.length-1, mid;
    	while(e-s>1){
    		mid = (s+e)/2;
    		if (num[mid]<num[e])
    			e = mid;
    		else if (num[mid] == num[e])
    			e--;
    		else if (num[s]<num[mid])
    			s = mid;
    		else if (num[mid] == num[s])
    			s++;
    	}
    	return num[s]<num[e] ? num[s] : num[e];
    }

	
	public int findMinII(int[] num) {
    	if (num == null || num.length == 0)
    		return 0;
    	
    	int s = 0, e = num.length-1, mid=0;
    	int flag = 0;
    	while(e-s>1){
    		if (flag == 0)
    			mid = (s+e)/2;
    		else if (flag == 1)
    			mid++;
    		else 
    			mid--;
    		
    		if (e==mid){
    			e = (s+e)/2;
    			flag = 0;
    		}else if (s==mid){
    			s = (s+e)/2;
    			flag = 0;
    		}else if (num[mid]<num[e]){
    			e = mid;
    			flag = 0;
    		}else if (num[mid] == num[e]){
    			flag = 1;
    		}else if (num[s]<num[mid]){
    			s = mid;
    			flag = 0;
    		}else if (num[s] == num[mid]){
    			flag = -1;
    		}
    	}
    	return num[s]<num[e] ? num[s] : num[e];
    }
	
	/**
	 * Find Minimum in Rotated Sorted Array
	 * @param num
	 * @return
	 */
    public int findMin(int[] num) {
    	if (num == null || num.length == 0)
    		return 0;
    	//if (num.length == 1) return num[0]
    	int s = 0, e = num.length-1, mid;
    	while(e-s>1){
    		mid = (s+e)/2;
    		if (num[mid]<num[e])
    			e = mid;
    		else if (num[s]<num[mid])
    			s = mid;
    	}
    	return num[s]<num[e] ? num[s] : num[e];
    }

	/**
	 * Decode Ways
	 * Start with the last character of the input string.
	 * For each digit, if it could not be decoded to a letter with the next digit, i.e. the value is greater than 26, it only can be counted as a single unit. 
	 * So the number of decode ways does not change at all.
	 * But if it could, then it could be counted as a unit by itself or counted together with the next digit.
	 * Thus, there are two ways to count the last single unit.
	 * So the total number should be the sum of the way before the new one is countered and the way before the next digit is counted.
	 * The special case is about 0. If a digit is 0, it only can be counted together with the next digit. Any digit following a 0 can only be counted as a single one
	 */
    public int numDecodings(String s) {
        if (s == null || s.isEmpty()) return 0;
        int n = s.length();
        
        int[] memo = new int[n+1];
        memo[n] = 1;
        memo[n-1] = s.charAt(n-1) == '0' ? 0 : 1;
        for(int i = n-2; i >=0; i--){
            if (s.charAt(i) == '0')
                continue;
            else
                memo[i] = Integer.parseInt(s.substring(i,i+2)) <= 26 ? memo[i+1]+memo[i+2] : memo[i+1];
        }
        return memo[0];
    }
	
	/**
	 * Evaluate Reverse Polish Notation
	 * @param tokens
	 * @return
	 */
    public int evalRPN(String[] tokens) {        
        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i< tokens.length; i++){
            if(tokens[i].equals("-") || tokens[i].equals("+") || tokens[i].equals("*") || tokens[i].equals("/")){
                try{
                    switch (tokens[i].charAt(0)){
                        case '+' :
                            result = stack.pop() + stack.pop();
                            break;
                        case '-' :
                            result = stack.pop();
                            result = stack.pop() - result;
                            break;
                        case '*' :
                            result = stack.pop() * stack.pop();
                            break;
                        case '/' :
                            result = stack.pop();
                            if (result == 0)
                                return Integer.MIN_VALUE;
                            result = stack.pop() / result;
                    }
                    stack.push(result);
                }catch(EmptyStackException e){
                    //e.printStackTrace();
                    return Integer.MIN_VALUE;
                }
                continue;
            }
            try{
                stack.push(Integer.parseInt(tokens[i]));
            }catch(NumberFormatException e){
                //e.printStackTrace();
                return Integer.MIN_VALUE;
            }
        }
        if (!stack.isEmpty())
            result = stack.pop();
        return stack.isEmpty() ? result : Integer.MIN_VALUE;
        
    }	

    public int evalRPN2(String[] tokens) {        
        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i< tokens.length; i++){
            if(tokens[i].equals("-") || tokens[i].equals("+") || tokens[i].equals("*") || tokens[i].equals("/")){
                    switch (tokens[i].charAt(0)){
                        case '+' :
                            result = stack.pop() + stack.pop();
                            break;
                        case '-' :
                            result = stack.pop();
                            result = stack.pop() - result;
                            break;
                        case '*' :
                            result = stack.pop() * stack.pop();
                            break;
                        case '/' :
                            result = stack.pop();
                            result = stack.pop() / result;
                        default :
                    }
                    stack.push(result);
                continue;
            }
            stack.push(Integer.parseInt(tokens[i]));
        }
        result = stack.pop();
        return result;
    }	
    
    
	/**
	 * Find Peak Element
	 * Note: Imagine num[-1] = num[n] = - infinity, so there must be peaks given num[i] != num[i+1]
	 * If an element num[i] < num[i+1], the the peak must be between num[i+1] and num[n], or between num[0] and num[i]
	 * So, do binary search to find the peak.	
	 */

	public int findPeakElement2(int[] num) {
    	if (num == null || num.length == 0) return -1;
    	int s = 0, e = num.length-1, mid1, mid2;
    	while (s < e){
    		mid1 = (s+e)/2;
    		mid2 = mid1+1;
    		if (num[mid1] < num[mid2])
    			s = mid2;
    		else
    			e = mid1;
    	}
    	return s;
	}
	
	public int findPeakElement(int[] num) {
    	if (num == null || num.length == 0) return -1;
    	int s = 0, e = num.length-1, mid;
    	while (s < e){
    		mid = (s+e)/2;
    		if (s == mid)
    			return num[s] > num[e] ? s : e;
    		else if (num[mid] > num[mid-1] && num[mid] > num[mid+1])
    			return mid;
    		else if (num[mid] < num[mid+1])
    			s = mid+1;
    		else
    			e = mid-1;
    	}
    	return s;
    }
	
	/**
	 * 
	 * @param matrix
	 */
	
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for(int layer = 0; layer < n/2; ++layer){
            int first = layer;
            int last = n-1-layer;
            for(int i=first; i<last; ++i){
                int offset = i - first;
                int top = matrix[first][i]; // Save top
                // left -> top
                matrix[first][i] = matrix[last-offset][first];
                // bottom -> left
                matrix[last-offset][first] = matrix[last][last-offset];
                // right -> bottom
                matrix[last][last-offset] = matrix[i][last];
                // top -> right
                matrix[i][last] = top;
            }
        }
     }   	
	/**
	 * Majority Element
	 */

	/**
	 * Divide the array to 2 halves. Search the majority of the first and the second half respectively, say A and B
	 * If A equals B, we are done. Otherwise, count the occurence of A and B in the whole array. Then we just need to check two candidates with O(n).
	 * The total cost is O(nlgn).
	 * Note: The number of A and B could be same in a divided section. For such case, we can randomly pick one. Because the majority must exist, same circumstance will not occur to the other half.
	 * If we pick the "wrong" number in the first part, the final result will be adjusted later.
	 * @param num
	 * @return
	 */
    public int majorityElement3(int[] num) {
        return calmaj(num, 0, num.length-1);
 
    }
    
    public int calmaj(int[] num, int start, int end){
        if (start == end)
            return num[start];
        int mid = (start+end)/2;
        int pre = calmaj(num, start,mid);
        int post = calmaj(num,mid+1,end);
        if (pre==post) return pre;
        int pre_ctr = 0, post_ctr = 0;
        for(int i=start; i<=end; i++){
            if (num[i] == pre)
                pre_ctr++;
            else if (num[i] == post)
                post_ctr++;
        }
        return pre_ctr > post_ctr ? pre : post;
    }	
	
    /**
     *  Moore voting algorithm: Basic idea of the algorithm is if we cancel out each occurrence of an element e with all the other elements 
     *  that are different from e then e will exist till end if it is a majority element. Below code loops through each element and maintains 
     *  a count of the element that has the potential of being the majority element. If next element is same then increments the count, 
     *  otherwise decrements the count. If the count reaches 0 then update the potential index to the current element and sets count to 1.
     * @param num
     * @return
     */
    public int majorityElement2(int[] num) {
        int mindex = 0;
        int counter = 1;
        for(int i=1; i<num.length; i++){
            counter = num[mindex] == num[i] ? counter+1 : counter - 1;
            if (counter == 0){
                mindex = i;
                counter = 1;
            }
        }
        return num[mindex];
    }	
	
	/**
	 * 
	 */
    public int majorityElement(int[] num) {
        quicksort(num,0, num.length-1);
        int counter = 1;
        for(int i=1;i<num.length;i++){
            if (num[i]==num[i-1])
                counter++;
            else if (counter > num.length/2)
                return num[i-1];
            else{
                counter=1;
            }
        }
        return num[num.length-1];
    }	
	
	/**
	 * Anagrams
	 */

	/**
	 * Step 1: Sort each string using counting sort (see the method below): O(n*m)
	 * Step 2: Create a HashMap whose key is the sorted string and the value is a list of integer storing the indexes of the strings which have same sorted string values
	 * Step 3: Traverse the sorted string array and fill the hash map: O(n)
	 * Step 4: Iterate the hash map in terms of keys (sorted string values), retrive the indexes and add the original strings to the output list
	 * @param strs
	 * @return
	 */
	
    public List<String> anagrams2(String[] strs) {
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
            for (Integer index : list)
                if (list.size() > 1)
                    ret.add(strs[index.intValue()]);
        }
        return ret;
    }
	
    /**
     * Sort a string using Counting Sort
     * Assumption: all elements are letters in low case. i.e. there are totally 26 types of elements, which can be seen as 26 integers
     * For each string, calculate the occurance of each letter, then assemble a new (sorted) string with the ascending order of elements by the number of occurance times.
     * Time complexity: O(n)
     * @param str
     * @return
     */
    
	public String sortString(String str){
	    int[] counter = new int[26];
	    StringBuffer sb = new StringBuffer();
	    for(int i=0; i<str.length(); i++)
	        counter[str.charAt(i)-'a']++;
	    for(int i=0;i<26;i++)
	        while(counter[i]-- > 0)
	            sb.append((char)('a' + i));            
	    return sb.toString();
	}
	
	/**
	 * Straightforward solution with O(n*n*m), where n is the size of the string array and m is the average length of a string.
	 * @param strs
	 * @return
	 */
	
    public List<String> anagrams(String[] strs) {
        List<String> output = new ArrayList<String>();
        if (strs == null || strs.length == 0)
            return output;
        List<HashMap<Character, Integer>> hms = new ArrayList<HashMap<Character, Integer>>();
        for(int i=0;i<strs.length;i++){
            hms.add(new HashMap<Character,Integer>());
        }
        
        for(int i=0; i<strs.length; i++){
            String curstr = strs[i];
            HashMap<Character, Integer> curhm = hms.get(i);
            for (int j=0; j<curstr.length(); j++){
                char curchar = curstr.charAt(j);
                if (curhm.containsKey(curchar)){
                	curhm.put(curchar, curhm.get(curchar)+1);
                }else{
                	curhm.put(curchar, 1);
                }
            }
        }
        
        for(int i=0; i<strs.length-1; i++){
            HashMap<Character, Integer> mhm = hms.get(i);
            for(int j=i+1; j<strs.length; j++){
            	HashMap<Character, Integer> chm = hms.get(j);
                if (chm.containsKey((char)0x80))
                    continue;
                if (strs[i].length() != strs[j].length())
                	continue;
                boolean match = true;           
                for(int k=0; k < strs[i].length(); k++){
                    char curchar = strs[i].charAt(k);
                    if (!chm.containsKey(curchar) || mhm.get(curchar) != chm.get(curchar)){
                        match = false;
                        break;
                    }
                }
                if (match){
                    if (!mhm.containsKey((char)0x80)){
                        output.add(strs[i]);
                        mhm.put((char)0x80, 0);
                    }
                    if (!chm.containsKey(0x80)){
                        output.add(strs[j]);
                        chm.put((char)0x80, 0);
                    }
                }
            }
       }        
       return output; 
    }	
	
	
	/**
	 * 	Next Permutation	 
	 */

    /**
	 * Observation: the change should happen where a digit is smaller than the following one, these two digits or the previous digit and the other following digits should be exchanged
	 * and the following digits should be reordered.
	 * Step 1: Start with the last digit, search for the digit (say x) which is smaller than the next one
	 * Step 2: Still start with the last digit, search for the digit (say y) larger than x until the one following x. The searched the digit could just be the immediately following
	 * Step 3: Exchange the position of x and y
	 * Step 4: Reverse the digits following (new) x. Note: the digits following x are in descending order (that's the way we find x). 
	 * 			To make sure the new digits are immediately higher than the original one, we need make them in ascending order, i.e. reverse the order. 
	 * @param num
	 */
    
	public void nextPermutation2(int[] num){
		if (num == null || num.length <= 1)
			return ;
		int i, j;
		for(i=num.length-2; i >= 0; i--)
			if (num[i] < num[i+1])
				break;
		
		if (i>0){
			for(j=num.length-1; j>i;j--)
				if(num[j] > num[i])
					break;
			int temp = num[i];
			num[i] = num[j];
			num[j] = temp;
		}
		reverseList(num,i+1,num.length-1);
	}
	
    public void nextPermutation(int[] num) {
        if (num == null || num.length <= 1)
            return;
        boolean flag = false;
        for(int i=num.length-1; i > 0; --i){
            if (num[i] > num[i-1]){
                quicksort(num, i, num.length-1);
                for(int j=i; j<num.length; ++j){
                	if(num[j] > num[i-1]){
                        int tmp = num[i-1];
                        num[i-1] = num[j];
                        num[j] = tmp;
                        flag = true;
                        break;
                    }
                }
                if (flag) break;
            }
        }        
        if (!flag)
        	reverseList(num);
        return;
    }	
	
	/**
	 * Word Search
	 */
	
    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.isEmpty())
            return false;

        /*
        boolean[][] log = new boolean[board.length][board[0].length];    
        for(int i=0; i<board.length; ++i)
            for(int j=0; j<board[0].length; ++j)
                log[i][j] = false;
        */    
        for(int i=0; i<board.length; ++i)
            for(int j=0; j<board[0].length; ++j)
                //if (check(board, i, j, word, 0, log))
            	if (check(board, i, j, word, 0))
            		return true;
        return false;
    }
    
    //public boolean check(char[][] board, int x, int y, String word, int index, boolean[][] log){
    public boolean check(char[][] board, int x, int y, String word, int index){    	
        if (board[x][y] != word.charAt(index))
            return false;
        else if (index == word.length() - 1)
            return true;
        board[x][y] ^= 0x80;
        if (y < board[0].length - 1 && board[x][y+1] < 0x80)
            if(check(board, x, y+1, word, index+1))
                return true;
        if( x < board.length-1 && board[x+1][y] < 0x80)        
            if(check(board, x+1, y, word, index+1))
                return true;
        if( x > 0 && board[x-1][y] < 0x80){        
            if(check(board, x-1, y, word, index+1))
                return true;
        }
        if( y > 0 && board[x][y-1] < 0x80){        
            if(check(board, x, y-1, word, index+1))
                return true;
        }
        board[x][y] ^= 0x80;
        return false;
    }	
	
	
	/**
	 * Intersection of Two Linked Lists
	 */
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	    if (headA == null || headB == null) return null;
	    int lenA = getLen(headA);
	    int lenB = getLen(headB);
	    int lenSub = lenA-lenB;    
	    if (lenSub > 0)
	        for(int i = 0; i<lenSub; ++i)
	            headA = headA.next;        
	    else
	        for(int i = 0; i < -lenSub; ++i)
	            headB = headB.next;
	    
	    while(headA != headB){
	        headA = headA.next;
	        headB = headB.next;
	    }
	    return headA;
	}

	public int getLen(ListNode head){
	    int len = 0;
	    while(head!=null){
	        len++;
	        head = head.next;
	    }
	    return len;
	}
	
	/**
	 * Combination Sum II: Each number can only be used once
	 */

	public List<List<Integer>> combinationSumII2(int[] candidates, int target) {
	    List<List<Integer>> ret = new LinkedList<List<Integer>>();
	    if (candidates == null || candidates.length == 0) return ret;
	    Arrays.sort(candidates); // sort the candidates
	    combinationSumII2recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
	    return ret;
	}	

	private void combinationSumII2recurse(List<Integer> list, int target, int[] candidates, int index, List<List<Integer>> ret) {
	    if (target == 0) {
	        ret.add(list);
	        return;
	    }
	    for (int i = index; i < candidates.length; i++) {
	        int newTarget = target - candidates[i];
	        if (newTarget >= 0) {
	            List<Integer> copy = new ArrayList<Integer>(list);
	            copy.add(candidates[i]);
	            combinationSumII2recurse(copy, newTarget, candidates, i+1, ret);
	            while (i<candidates.length-1 && candidates[i] == candidates[i+1])
	            	i++;
	        } else {
	            break;
	        }
	    }
	} 
	
	
	public List<List<Integer>> combinationSumII(int[] candidates, int target) {
	    List<List<Integer>> ret = new LinkedList<List<Integer>>();
	    Arrays.sort(candidates); // sort the candidates
	    Set<List<Integer>> set = new HashSet<List<Integer>>();
	    combinationSumIIrecurse(new ArrayList<Integer>(), target, candidates, 0, set);
	    for(List<Integer> list : set)
	    	ret.add(list);
	    return ret;
	}

	private void combinationSumIIrecurse(List<Integer> list, int target, int[] candidates, int index, Set<List<Integer>> set) {
	    if (target == 0) {
	        set.add(list);
	        return;
	    }
	    for (int i = index; i < candidates.length; i++) {
	        int newTarget = target - candidates[i];
	        if (newTarget >= 0) {
	            List<Integer> copy = new ArrayList<Integer>(list);
	            copy.add(candidates[i]);
	            combinationSumIIrecurse(copy, newTarget, candidates, i+1, set);
	        } else {
	            break;
	        }
	    }
	} 
	
	/**
	 * Combination Sum: a single element can be used many times.
	 * @param candidates
	 * @param target
	 * @return
	 */
	
	public List<List<Integer>> combinationSum3(int[] candidates, int target) {
	    List<List<Integer>> ret = new LinkedList<List<Integer>>();
	    Arrays.sort(candidates); // sort the candidates
	    candidates = removeDup(candidates);
	    // collect possible candidates from small to large to eliminate duplicates,
	    combinationSum3recurse(new ArrayList<Integer>(), target, candidates, 0, ret);
	    return ret;
	}

	// the index here means we are allowed to choose candidates from that index
	private void combinationSum3recurse(List<Integer> list, int target, int[] candidates, int index, List<List<Integer>> ret) {
	    if (target == 0) {
	        ret.add(list);
	        return;
	    }
	    for (int i = index; i < candidates.length; i++) {
	        int newTarget = target - candidates[i];
	        if (newTarget >= 0) {
	            List<Integer> copy = new ArrayList<Integer>(list);
	            copy.add(candidates[i]);
	            combinationSum3recurse(copy, newTarget, candidates, i, ret);
	        } else {
	            break;
	        }
	    }
	} 


	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
	    Arrays.sort(candidates);
	    int i=0, size = candidates.length, sum=0;
	    Stack<Integer> combi = new Stack<Integer>(), indices = new Stack<Integer>();
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    while (i < size) {
	        if (sum + candidates[i]>= target) {
	            if (sum + candidates[i] == target) {
	                combi.push(candidates[i]);
	                result.add(new ArrayList<Integer>(combi));
	                combi.pop();
	            }
	            // indices stack and combination stack should have the same size all the time
	            if (!indices.empty()){
	                sum -= combi.pop();
	                i = indices.pop();
	                while (i == size-1 && !indices.empty()) {
	                    i = indices.pop();
	                    sum -= combi.pop();
	                }
	            }
	            i++;
	        } else {
	            combi.push(candidates[i]);
	            sum +=candidates[i];
	            indices.push(i);
	        }
	    }
	    return result;
	}
	
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (candidates == null || candidates.length == 0) return result;
        
        Arrays.sort(candidates);
        candidates = removeDup(candidates);
        
        Stack<List<Integer>> tracker = new Stack<List<Integer>>();
        Hashtable<List<Integer>, Integer> tracker_index = new Hashtable<List<Integer>, Integer>();
        List<Integer> dummy = new ArrayList<Integer>();
        dummy.add(0);
        tracker.add(dummy);
        tracker_index.put(dummy, -1);
        //int index = 0;
        int curSum, curInd;
        
        while(true){
        	List<Integer> top = tracker.peek();
        	curInd = tracker_index.get(top);
        	tracker_index.remove(top);
        	curSum = getCurSum(top);
        	
        	if (curInd + 1 >= candidates.length || candidates[curInd+1] > target)
        		if (top != dummy){
        			tracker.pop();
        			continue;
        		}
        		else
        			break;
        	
        	if (curSum + candidates[curInd+1] < target){
     		   List<Integer> newtop = (top == dummy) ? new ArrayList<Integer>() : new ArrayList<Integer>(top);
     		   newtop.add(candidates[curInd+1]);
     		   tracker.add(newtop);
     		   //tracker_index.remove(top);
     		   tracker_index.put(top, curInd+1);
     		   tracker_index.put(newtop, curInd);
        	}else if (curSum + candidates[curInd+1] == target){

     		   if (top == dummy){
     			   tracker_index.put(top, curInd+1);
     			   top = new ArrayList<Integer>();
     		   }else
         		   tracker.pop();
     		   top.add(candidates[curInd+1]);
     		   result.add(top);
        	}else{
        		tracker.pop();
        		//tracker_index.remove(top);
        	}
        }
        return result;
    }
	
    public int[] removeDup(int[] nums){
    	int len = 1;
    	for(int i = 1; i<nums.length; ++i){
    		if (nums[i] != nums[i-1])
    			nums[len++] = nums[i];
    	}
    	int[] output = Arrays.copyOf(nums, len);
    	return output;
    }
 	
    public int getCurSum(List<Integer> nums){
    	int sum = 0;
    	if (!nums.isEmpty()){
    		for(int i = 0; i < nums.size(); ++i)
    			sum += nums.get(i);
    	}
    	return sum;
    }
    
    public class Pair {
        int left;
        int right;
        Pair(int _left, int _right) {
        	
            left = _left;
            right = _right;
        }
    }
    public List<List<Integer>> fourSum2(int[] num, int target) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        HashMap<Integer, List<Pair>> map = new HashMap<Integer, List<Pair>>(); 

        for(int i=0; i<num.length - 1; i++) {
            for(int j=i+1; j<num.length; j++) {
                int remain = target - num[j] - num[i];
                List<Pair> pairs = map.get(remain);
                if(map.get(remain) != null)
                    for(Pair pair : pairs) {
                        List<Integer> list  = Arrays.asList(num[pair.left], num[pair.right], num[i], num[j]);
                        if(!result.contains(list))
                            result.add(list);
                    }
            }
            for(int k=0; k<i; k++) {            
                int sum = num[k] + num[i];
                List<Pair> pairs = map.get(sum);
                if(pairs == null) {
                    pairs = new ArrayList<Pair>();
                    map.put(sum, pairs);
                }
                pairs.add(new Pair(k, i));
            }
        }
        return result;
    }
	
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null) return result;
        Arrays.sort(num);
        for (int i = 0 ; i < num.length - 3; ++i)
            for (int j = i+3; j < num.length; ++j)
                check4Sum(num,i,j,target-num[i]-num[j],result);
        return result;
    }

    public void check4Sum(int[] num, int s, int e, int target, List<List<Integer>> result){
        int lnum = num[s], rnum = num[e];
        //HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(
        HashSet<Integer> hs = new HashSet<Integer>();
        for(int k = s+1; k < e; ++k){
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
    
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length <=2) return Integer.MIN_VALUE;
        int i = 0, j = num.length - 1, delta = Integer.MAX_VALUE;
        Arrays.sort(num);
        while(i+1<j){
            delta = checkSum(num, i, j, target, delta);
            if (delta > 0)
                --j;
            else if (delta < 0)
                ++i;
            else
            	break;
        }
        return target + delta; 
    }
    
    public int checkSum(int[] num, int left, int right, int target, int delta){
        int lnum = num[left], rnum = num[right];
        ++left; --right;
        int mid;
        while(left <= right){
        	mid = (left+right)/2;
            int sum = lnum + rnum + num[mid];
            if (Math.abs(sum-target) < Math.abs(delta))
                delta = sum - target;
            if (sum > target)
                right = mid - 1;
            else
                left = mid + 1;
        }
        return delta;
    }	
	
    public int maxArea2(int[] height) {
        if (height == null || height.length <= 1) return 0;
        int i = 0, j = height.length-1, max = 0;
        while(i<j){
            max = Math.max(max,Math.min(height[i],height[j])*(j-i));
            if (height[i]<height[j])
                ++i;
            else
                --j;
        }
        return max;
    }

	
	public int maxArea(int[] height){
	    if (height == null || height.length <=1)
	        return 0;
	    int[] areas = new int[height.length];
	    
	    for(int i=0;i<height.length;++i){
	        for(int j=0; j<i; ++j){
	            if (height[j] < height[i])
	                areas[j] = areas[j] > height[j] * (i-j) ? areas[j] : height[j] * (i-j);
	            else{
	                areas[i] = areas[i] > height[i] * (i-j) ? areas[i] : height[i] * (i-j);
	           }
	        }
	    }
	    int max = 0;
	    for (int i = 0; i<height.length; ++i)
	        max = max > areas[i] ? max : areas[i];
	    return max;
	    
	}
	
	public int minPathSum2(int[][] grid){
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int[] sumrow = new int[grid[0].length];
        sumrow[0] = grid[0][0];
    	for (int i = 0; i< grid.length; ++i)
    		for (int j = 0; j<grid[0].length; ++j){
    			if (i==0 && j==0)
    				continue;
    			else if (i == 0)
    				sumrow[j] = grid[i][j] + sumrow[j-1];
    			else if (j == 0)
    				sumrow[j] += grid[i][j];
    			else
    				sumrow[j] = Math.min(sumrow[j], sumrow[j-1]) + grid[i][j];
    		}
    	return sumrow[grid[0].length-1];
	}

    public int minPathSum(int[][] grid) {
        if (grid == null) return -1;
        //if (grid.)
        int m = grid.length, n = grid[0].length;
        int[][] minsum = new int[m][n];
        minsum[m-1][n-1] = grid[m-1][n-1];
        for(int i=m-1; i>=0; --i)
            for (int j=n-1; j>=0; --j){
                if (i == m-1 && j == n-1)
                    continue;
                else if (i == m-1)
                    minsum[i][j] = grid[i][j] + minsum[i][j+1];
                else if (j == n-1)
                    minsum[i][j] = grid[i][j] + minsum[i+1][j];
                else
                    minsum[i][j] = minsum[i+1][j] < minsum[i][j+1] ? grid[i][j] + minsum[i+1][j] : grid[i][j] + minsum[i][j+1] ;
            }
        return minsum[0][0];
    }	
	
    public String longestPalindrome1(String s) {
        if (s==null || s.length()<=1) return s;
        int start=0, maxlen=1;
        for(int i=1; i<s.length(); ++i){
            int low = i-1;
            int high = i;
            while( low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
                if (high-low+1 > maxlen){
                    start = low;
                    maxlen = high-low+1;
                }
                --low;
                ++high;
            }
            
            low = i-1;
            high = i+1;
            while( low >=0 && high < s.length() && s.charAt(low) == s.charAt(high)){
                if (high-low+1 > maxlen){
                    start = low;
                    maxlen = high-low+1;
                }
                --low;
                ++high;
            }
        }
        
        return s.substring(start,start+maxlen);
    }	

    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        int rownum = matrix.length;
        if (rownum == 0) return;
        int colnum = matrix[0].length;
        if (colnum == 0) return;
        
        boolean firstrowzero = false, firstcolzero = false;
        
        for (int i=0;i<matrix.length;++i)
            if (matrix[i][0] == 0){
                firstcolzero = true;
                break;
            }
        for (int j=0; j<matrix[0].length; ++j)
            if(matrix[0][j] == 0){
                firstrowzero = true;
                break;
             }
        
        for(int i=0;i<matrix.length; ++i){
            for(int j=0; j<matrix[0].length;++j){
                if (matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for(int i=1;i<matrix.length; ++i)
            for(int j=1; j<matrix[0].length;++j)
                if (matrix[0][j] == 0 || matrix[i][0] == 0)
                    matrix[i][j] = 0;

        if (firstrowzero)
            for(int j = 0; j<matrix[0].length; ++j)
                matrix[0][j] = 0;
                
        if(firstcolzero)
            for(int i = 0; i<matrix.length; ++i)
                matrix[i][0] = 0;                
                 
    }
	
	public List<String> letterCombinations(String digits) {
        List<String> output = new ArrayList<String>();
        output.add("");
        if (digits == null || digits.length() == 0) return output;
        String[] digitsletters = new String[digits.length()];
        for(int i = 0; i<digits.length(); ++i){
             digitsletters[i] = digstr(digits.charAt(i));   
        }
        for (int i = 0 ; i < digits.length(); ++i){
            List<String> temp = new ArrayList<String>();
            for (int j = 0 ; j < digitsletters[i].length(); ++j){
                for(String s : output)
                    temp.add(s + digitsletters[i].charAt(j));
            }
            output = temp;
        }
        return output;
    }
	
    public String digstr(char digits){
        String str;
        switch (digits){
            case '1' :
                str = "";
                break;
            case '2' :
                str = "abc";
                break;
            case '3' :
                str = "def";
                break;
            case '4' :
                str = "ghi";
                break; 
            case '5' :
                str = "jkl";
                break;
            case '6' :
                str = "mno";
                break;
            case '7' :
                str = "pqrs";
                break;
            case '8' :
                str = "tuv";
                break;
            case '9' :
                str = "wxyz";
                break;
            case '0' :
                str = " ";
                break;
            default :
                str = "";
        }
        return str;
    }


    public List<List<Integer>> subsetsWithDup(int[] num) {
    	if (num==null) return null;
    	Arrays.sort(num);
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	output.add(new ArrayList<Integer>());
    	int j, lastlen = 0;
    	for(int i=0;i<num.length;++i){
    		// Duplicated numbers are only added to last length of arrays
            if (i != 0 && num[i] == num[i - 1])
                j = lastlen;
            else
                j = 0;
            lastlen = output.size();
    		List<List<Integer>> temp = new ArrayList<List<Integer>>();
    		// Copy the existing valid sets which will not introduce duplicates
    		for (; j < lastlen; ++j ){
    			temp.add(new ArrayList<Integer>(output.get(j)));
    		}
    		// Add num[i] to each valid subset
    		for (List<Integer> a : temp)
    			a.add(num[i]);
    		output.addAll(temp); // Add the extra sets to existing sets	
    	}
    	return output;
    }
	
    public int minimumTotal2(List<List<Integer>> triangle) {
    	if (triangle == null || triangle.size() == 0) return 0;
    	List<Integer> sums = new ArrayList<Integer>();
    	sums.addAll(triangle.get(triangle.size()-1));
    	for(int i = triangle.size()-2; i>=0; --i){
    		List<Integer> row = triangle.get(i);
    		for(int j = 0; j < row.size(); ++j ){
    			int newsum = sums.get(j) < sums.get(j+1) ? sums.get(j) + row.get(j) : sums.get(j+1) + row.get(j);
    			sums.set(j, newsum);
    		}
    	}
    	return sums.get(0);
    }
    
    public int minimumTotal(List<List<Integer>> triangle) {
    	if (triangle == null || triangle.size() == 0) return 0;
    	List<Integer> sums = new ArrayList<Integer>();
    	sums.add(triangle.get(0).get(0));
    	for(int i = 1; i < triangle.size(); ++i){
    		List<Integer> row = triangle.get(i);
    		sums.add(sums.get(sums.size()-1) + row.get(row.size()-1));
    		for (int j = row.size()-2; j > 0; --j){
    			int newsum = sums.get(j-1) < sums.get(j) ? sums.get(j-1) + row.get(j) : sums.get(j) + row.get(j); 
    			sums.set(j, newsum);
    		}
    		sums.set(0, sums.get(0) + row.get(0));
    	}    	
    	return minList(sums);
    }
    
    public int minList(List<Integer> list){
    	int min = Integer.MAX_VALUE;
    	for (Integer e : list)
    		min = min < e ? min : e;
    	return min;
    }
	
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
	    int maxprofit = Integer.MIN_VALUE;
	    int curprofit = 0;
	    for(int i = 0; i < prices.length; i++)
	    {
	        curprofit = curprofit + prices[i];
	        maxprofit = Math.max(maxprofit, curprofit);
	        if(curprofit < 0)
	            curprofit = 0;
	    }
	    return maxprofit > 0 ? maxprofit : 0;        
    }	
	
    public String longestPalindrome(String s) {
    	if (s==null || s.isEmpty()) return s;
    	int len = s.length();
    	String reversed = reverseString(s);
    	String checked;
    	for (int i = reversed.length(); i > 0; --i){
    		 for (int j = 0; j <= len - i; ++j){
    			 checked = reversed.substring(j, j+i);
    			 //System.out.println("checked: " + checked);
    			 if (strStr1(s, checked) != null)
    				 return checked;
    		 }
    	}
    	return null;
    }
    
    public String reverseString(String str){
    	if (str == null || str.length() <= 1) return str;
    	StringBuffer output = new StringBuffer();
    	for(int i = str.length() - 1; i >=0; --i){
    		output.append(str.charAt(i));
    	}
    	return output.toString();
    }
	
	public String reverseWords(String s) {
	    if (s == null || s.length() == 0) return s;
	    String output = "";
	    StringBuffer word = new StringBuffer();
	    boolean newword = false;
	    for (int i = 0; i < s.length(); ++i){
	        if (s.charAt(i) != ' '){
	        	newword = true;
	            word.append(s.charAt(i));
	        }else{
	        	if (newword){
	        		output = " " + word.toString() + output;
	        		word = new StringBuffer();
	        		newword = false;
	        	}
	        }
	    }
	    if (newword){
	    	output = word.toString() + output;
	    	return output;
	    }else if (output.length() > 1)
	    	return output.substring(1);
	    else
	    	return output;
	    		        
	}
	
    public int lengthOfLastWord2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        int i = s.length() - 1;
        while (i>= 0 && s.charAt(i) == ' ')
        	--i;
        if (i<0) return 0;
        while (i>=0 && s.charAt(i) != ' '){
        	++len;
        	--i;
        }
        return len;
    }
	
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = 0;
        boolean flag = false;
        for(int i=0;i<s.length();++i){
            if (s.charAt(i) == ' '){
                flag = true;
                continue;
            }
            if (s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >='A' && s.charAt(i) <= 'Z'){
                len = flag ? 1 : len+1;
                flag = false;
            }
        }
        return len;
    }

    public boolean isNumber(String s) {
    	if (s == null || s.length() == 0)
    		return false;
    	int i = 0, len = s.length();
    	for (; i< len && s.charAt(i) == ' '; ++i) {}
    	if ( i< len && (s.charAt(i) == '+' || s.charAt(i) == '-')) ++i;
    	
    	int n_num, n_pt;
    	for(n_num=0, n_pt=0; i<len && (s.charAt(i) <= '9' && s.charAt(i) >= '0' || s.charAt(i) =='.'); ++i)
    		if (s.charAt(i) =='.')
    			++n_pt;
    		else
    			++n_num;
    	if (n_pt > 1 || n_num < 1)
    		return false;
    	if ( i<len && s.charAt(i) =='e'){
    		++i;
    		if (i<len && (s.charAt(i) == '+' || s.charAt(i) == '-')) ++i;
    		n_num = 0;
        	for (; i<len && s.charAt(i) <= '9' && s.charAt(i) >= '0'; ++i, ++n_num) {}
        	if (n_num < 1)
        		return false;
    	}
    	for (; i<len && s.charAt(i) == ' '; ++i) {}
    	return (i==s.length());    	
    }
	
    public int climbStairs2(int n) {
    	if (n<=0)
    		return 0;
    	int[] ways = new int[n];
    	for (int i=0;i<n;++i){
    		if (i < 2){
    			ways[i] = i + 1;
    			continue;
    		}
    		ways[i] = ways[i-1] + ways[i-2];
    	}
    	return ways[n-1];
    }
	
    public int climbStairs(int n) {
    	if (n<=0)
    		return 0;
    	else if (n==1)
    		return 1;
    	else if (n==2)
    		return 2;
    	else{
    		return climbStairs(n-1) + climbStairs(n-2);
    	}
    }

    public int sumNumbers2(TreeNode root) {
    	if (root==null) return 0;
    	int sum = 0, subnum = 0;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	HashSet<TreeNode> hs = new HashSet<TreeNode>();
    	st.push(root);
    	while (!st.isEmpty()){
    		TreeNode cur = st.pop();
    		if (cur.left == null && cur.right == null){
    			sum += subnum * 10 + cur.val;
    		}else if (hs.contains(cur)){
    			subnum = (subnum-cur.val)/10;
    		}else if (cur.left != null || cur.right != null){
    			st.push(cur);
    			hs.add(cur);
    			subnum = subnum * 10 + cur.val;
    			if (cur.left != null) st.push(cur.left);
    			if (cur.right != null) st.push(cur.right);
    		}
    	}
    	return sum;
    }
	
    public int sumNumbers(TreeNode root) {
    	if (root==null) return 0;
    	int[] result = new int[1];
    	result[0] = 0;
    	sumnumbershelper(root, 0, result);
    	return result[0];
    }
    
    public void sumnumbershelper(TreeNode node, int subnum, int[] result){
    	int curnum = subnum * 10 + node.val;
    	if (node.left == null && node.right == null){
    		result[0] += curnum;
    		return;
    	}
    	if (node.left != null)
    		sumnumbershelper(node.left, curnum, result);
    	if (node.right != null)
    		sumnumbershelper(node.right, curnum, result);
    	return;
    }
	
    public String convertZigZag2(String s, int nRows) {
    	if (s==null || s.length() == 0 || nRows <=1) return s;
    	StringBuffer[] buff = new StringBuffer[nRows];
    	for(int i=0;i<nRows;++i)
    		buff[i] = new StringBuffer();
    	int seg = 2 * nRows - 2;
    	for (int i = 0; i<s.length(); ++i){
    		int pos = i % seg;
    		if (pos < nRows)
    			buff[pos].append(s.charAt(i));
    		else
    			buff[seg-pos].append(s.charAt(i));
    	}

    	for (int i = 1; i<nRows; ++i)
    		buff[0].append(buff[i].toString());
    	return buff[0].toString();
    }
	
    public String convertZigZag(String s, int nRows) {
    	if (nRows <= 1) return s;
    	StringBuffer[] buff = new StringBuffer[nRows];
    	for(int i=0;i<nRows;++i)
    		buff[i] = new StringBuffer();
    	int seg = 2 * nRows - 2;
    	int rounds = s.length()/seg + 1;
    	int count = 0;
    	for(int i=0;i<rounds;++i){
    		boolean done = false;
    		for (int j=0;j<nRows;++j){
    			if (i*seg+j < s.length()){
    				char c = s.charAt(i*seg+j);
    				buff[j].append(s.charAt(i*seg+j));
    				count++;
    			}
    			else{
    				done = true;
    				break;
    			}
    			if (j==0 || j== nRows -1)
    				continue;    			
    			if ((i+1)*seg-j < s.length()){
    				char c = s.charAt((i+1)*seg-j);
    				buff[j].append(s.charAt((i+1)*seg-j));
    				count++;
    			}
    			else{
    				if (count > s.length()){
    					done = true;
    					break;
    				}
    			}
    		}
    		if (done)
    			break;
    	}
    	
    	for (int i = 1; i<nRows; ++i)
    		buff[0].append(buff[i].toString());
    	return buff[0].toString();
    }

    	
    public String convertZigZagWrong(String s, int nRows) {
    	if (nRows <=1) return s;
    	char[][] convertArray = new char[nRows][s.length()];
    	StringBuffer output = new StringBuffer();
    	int sindex = 0;
    	int col = 0;
    	int curRows = nRows;
    	boolean down = true;
    	while (sindex < s.length()){
    		for (int i = (nRows-curRows)/2; i<(nRows+curRows)/2; ++i){
    			convertArray[i][col]=s.charAt(sindex++);
    			if (sindex >= s.length())
    				break;
    		}    			
    		
    		if (down && curRows <= 2)
    			down = false;
    		if (!down && curRows >= nRows)
    			down = true;
    		//down = curRows <= 2 ? !down : down;
    		//down = curRows >= nRows ? !down : down;
    		curRows = down ? curRows-2 : curRows + 2;
    		col++;
    		
    	}
    	TeasingUtil.printZigZag(convertArray);
   
    	sindex = 0;
    	for(int i=0;i<nRows;++i){
    		for(int j=0;j<s.length(); ++j){
    			if(convertArray[i][j] != 0){
    				output.append(convertArray[i][j]);
        			if (++sindex >= s.length())
        				break;
    			}
    		}
        	if (sindex >= s.length())
        		break;
    	}
    	return output.toString();
    }

	
    public List<Integer> getRow(int rowIndex) {
    	List<Integer> cur = new ArrayList<Integer>();
    	if (rowIndex < 0) return cur;
    	cur.add(1);
    	for (int i=1; i<=rowIndex; ++i){
    		cur.add(1);
    		for (int j=cur.size()-2; j > 0; --j){
    			cur.set(j, cur.get(j-1)+cur.get(j));
    		}
    	}
    	return cur;
    }
	
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	if (numRows <= 0) return output;
    	List<Integer> cur = new ArrayList<Integer>();
    	cur.add(1);
    	output.add(cur);
    	List<Integer> pre;
    	
    	for (int i=2; i<=numRows; ++i){
    		pre = cur;
    		cur = new ArrayList<Integer>();
    		cur.add(1);
    		for (int j = 1; j<pre.size(); ++j){
    			cur.add(pre.get(j-1) + pre.get(j));
    		}
    		cur.add(1);
    		output.add(cur);
    	}
    	return output;
    }
	
	// Sudoku
	class Cell{
		int i;
		int j;
		Cell (int x, int y){ i=x; j=y;}
	}
	
    public boolean isValidSudoku(char[][] board) {
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
    
	
    public void solveSudoku(char[][] board) {
    	if (board == null) return;
    	if (board.length != 9 || board[0].length != 9) return;
   	
    	List<Cell> unfilled = new ArrayList<Cell>();
    	for (int i = 0; i< board.length; ++i)
    		for (int j = 0; j < board[i].length; ++j)
    			if (board[i][j] == '.') unfilled.add(new Cell(i,j));
    	
    	solveSudokuhelper(unfilled,0, board);
    }
    
    public boolean solveSudokuhelper(List<Cell> unfilled, int cur, char[][] board){
    	if (cur == unfilled.size()) return true;
    	Cell cell = unfilled.get(cur);
    	for (char checker = '1'; checker <='9'; ++checker ){
    		if (checkCell(cell.i,cell.j,checker,board)){
    			board[cell.i][cell.j] = checker;
    			if (solveSudokuhelper(unfilled,cur+1,board))
    				return true;
    			else
    				board[cell.i][cell.j] = '.';
    		}
    	}
    	return false;
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
    
    public boolean isPalindrome2(String s){
        if (s == null || s.length() == 0) return false;
        int i = 0;
        int j = s.length()-1;
        while (i < j){
            if (!isValid(s.charAt(i))) { ++i; continue;}
            if (!isValid(s.charAt(j))) { --j; continue;}
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))){
                return false;
            }else{
                ++i;
                --j;
            }
        }
        return true;
    }
    
    public boolean isValid(char c){
        return (c >= 'a' && c <='z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9');
    }    
	
    public boolean isPalindrome(String s) {
    	if (s == null) return false;
        String word = cleanWord(s);
    	if (word.length() == 0) return true;
        for (int i=0; i<=word.length()/2; ++i)
        	if (word.charAt(i) != word.charAt(word.length()-i-1))
        		return false;
        return true;
    }
	
	public String cleanWord(String word){
		StringBuffer output = new StringBuffer();
		for(int i=0; i<word.length(); ++i){
			char cur = word.charAt(i);
			if (cur >= '0' && cur <='9' || cur >= 'a' && cur <= 'z')
				output.append(cur);
			else if (cur >= 'A' && cur <= 'Z'){				
				output.append(Character.toLowerCase(cur));
			}
		}
		return output.toString();
	}
	
	/**
	 * Unique Binary Search Trees
	 * @param n
	 * @return
	 * If the root is number less than n, i.e., i, 1 <= i <= n, we will have unique combinations of left child and right child. 
	 * Because it is a BST, the nodes in two children cannot be exchanged, numbers from 1 to i-1 are always at the left,
	 * and the numbers from i+1 to n are always at the right. So, the total number of combinations is numTree(1,i-1) and numTree(i+1,n).
	 * Although the right child does not begins with 1, it does not matter, so for each i, 1<=i<=n, the # is numTree(i-1) * numTree(n-i).
	 * Then we sum them up and get the result;
	 */
	public int numTrees2(int n){
		if (n<=0) return 0;
		int[] r = new int[n+1];
		r[0] = 1;
		for (int j=1; j<=n; ++j){
			for (int i=1; i<=j; ++i){
				r[j] += r[j-i]*r[i-1];
			}
		}
		return r[n];
	}
	
    public int numTrees(int n) {
        if (n<=0) return 0;
        List<TreeNode> result = generateTreesHelper2(1,n);        
        return result.size();
    }
	
    public List<TreeNode> generateTrees(int n) {
    	List<TreeNode> result = new ArrayList<TreeNode>();
    	if (n<=0) {
    		result.add(null);
    		return result;
    	}
    	return generateTreesHelper2(1,n);
    }

    public List<TreeNode> generateTreesHelper2(int start, int end){
		List<TreeNode> subtrees  = new ArrayList<TreeNode>();
    	if (start > end) {
    		subtrees.add(null);
    		return subtrees;
    	}
    	for (int i=start; i<=end; ++i){
    		List<TreeNode> leftchildren = generateTreesHelper2(start,i-1);
    		List<TreeNode> rightchidren = generateTreesHelper2(i+1, end);
			for (TreeNode left : leftchildren){
				for (TreeNode right : rightchidren){
					TreeNode root = new TreeNode(i);
					root.left = left;
					root.right = right;
					subtrees.add(root);
				}
			}
    	}
    	return subtrees;    	
    }
    
    public List<TreeNode> generateTreesHelper(int[] input, int start, int end){
		List<TreeNode> subtrees  = new ArrayList<TreeNode>();
    	if (start > end) {
    		subtrees.add(null);
    		return subtrees;
    	}
    	for (int i=start; i<=end; ++i){
    		//TreeNode root = 
    		List<TreeNode> leftchildren = generateTreesHelper(input,start,i-1);
    		List<TreeNode> rightchidren = generateTreesHelper(input, i+1, end);
    		if (leftchildren.size() == 0 && rightchidren.size() == 0){
    			subtrees.add(new TreeNode(input[i]));

    		}else if (leftchildren.size() != 0 && rightchidren.size() != 0){
    			for (TreeNode left : leftchildren)
    				for (TreeNode right : rightchidren){
    					TreeNode root = new TreeNode(input[i]);
    					root.left = left;
    					root.right = right;
    					subtrees.add(root);
    				}

    		}else if (leftchildren.size() == 0){
    			for (TreeNode right : rightchidren){
    				TreeNode root = new TreeNode(input[i]);
    				root.left = null;
    				root.right = right;
    				subtrees.add(root);
    			}
    		
    		}else{
    			for (TreeNode left : leftchildren){
    				TreeNode root = new TreeNode(input[i]);
    				root.left = left;
    				root.right = null;
    				subtrees.add(root);
    			}
    		}
    	}
    	return subtrees;
    }
    
    public String countAndSay(int n) {
    	if (n<=0) return "";
    	String output = "1";
    	for (int i=2; i<=n;++i){
    		StringBuffer newoutput = new StringBuffer();
    		int j = 0, counter = 1;
    		char cur = output.charAt(j);
    		while(++j < output.length()){
    			if (output.charAt(j) != cur){
    				newoutput.append(String.valueOf(counter) + String.valueOf(cur));
    				cur = output.charAt(j);
    				counter = 1;
    			}else{
    				++counter;
    			}
    		}
    		newoutput.append(String.valueOf(counter) + String.valueOf(cur));
    		output = newoutput.toString();
    	}
    	return output;
    }
	
	// Total NQueens
    /* backtrace program using bit-wise operation to speed up calculation.
     * 'limit' is all '1's.
     * 'h'  is the bits all the queens vertically projected on a row. If h==limit, then it's done, answer++.
     * 'r'   is the bits all the queens anti-diagonally projected on a row.
     * 'l'   is the bits all the queens diagonally projected on a row.
     * h|r|l  is all the occupied bits. Then pos = limit & (~(h|r|l)) is all the free positions.
     * p = pos & (-pos)  gives the right most '1'. pos -= p means we will place a queen on this bit 
     *                             represented by p.
     * 'h+p'  means one more queue vertically projected on next row.
     * '(r+p)<<1'  means one more queue anti-diagonally projected on next row. Because we are
     *                   moving to next row and the projection is skew from right to left, we have to 
     *                   shift left one position after moved to next row.
     * '(l+p)>>1'  means one more queue diagonally projected on next row. Because we are 
     *                  moving to next row and the projection is skew from left to right, we have to 
     *                  shift right one position after moved to next row.
     */
	
	int ans, limit;
    int totalNQueens2(int n) {
        ans = 0;
        limit = (1<<n) - 1;
        totalNQueendfs(0, 0, 0);
        return ans;
    }
    
    void totalNQueendfs(int h, int r, int l) {
        if (h == limit) {
            ans++;
            return;
        }
        int pos = limit & (~(h|r|l));
        while (pos>0) {
            int p = pos & (-pos);
            pos -= p;
            totalNQueendfs(h+p, (r+p)<<1, (l+p)>>1);
        }
    }

	
    public int totalNQueens(int n) {
    	int[] columnForRow = new int[n];
    	int[] count = new int[1];
    	count[0] = 0;
    	countQueen(0,n,count,columnForRow);
    	return count[0];
    }

    public void countQueen(int row, int n, int[] output, int[] columnForRow){
    	if (row == n){
    		output[0]++;
    		return;
    	}
    	for (int i=0; i<n; ++i){
    		columnForRow[row] = i;
    		if (checkRow(row,columnForRow))
    			countQueen(row+1,n,output,columnForRow);    		
    	}
    }

    
    public List<String[]> solveNQueens(int n) {
    	int[] columnForRow = new int[n];
    	List<String[]> output = new ArrayList<String[]>();
    	placeQueen(0,n,output,null,columnForRow);
    	return output;
    }	

    public boolean checkRow(int row, int[] columnForRow){
    	for (int i=0; i< row; ++i){
    		int diff = Math.abs(columnForRow[i] - columnForRow[row]);
    		if (diff == 0 || diff == row - i)
    			return false;
    	}
    	return true;
    }
    
    public void placeQueen(int row, int n, List<String[]> output, String[] partial, int[] columnForRow){
    	if (row == n){
    		String[] aout = Arrays.copyOf(partial, partial.length);
    		output.add(aout);
    		return;
    	}
    	if (row == 0)
    		partial = new String[n];    	
    	for (int i=0; i<n; ++i){
    		columnForRow[row] = i;
    		String arow = "";
    		for (int j = 0; j<n; ++j)
    			if (i==j)
    				arow += "Q";
    			else
    				arow += ".";
    		partial[row] = arow;
    		if (checkRow(row,columnForRow))
    			placeQueen(row+1,n,output,partial,columnForRow);    		
    	}
    }
    
    
    public List<List<Integer>> permuteUnique(int[] num) {
    	if (num == null) return null;
    	if (num.length == 0) return new ArrayList<List<Integer>>();
    	return permuteuniquehelper(num, 0);        
    }

    public List<List<Integer>> permuteUnique2(int[] num) {
    	if (num == null || num.length == 0)
    		return new ArrayList<List<Integer>>();
    	ArrayList<Integer> original = new ArrayList<Integer>();
    	for (int i = 0; i < num.length; ++i)
    		original.add(num[i]);
    	return permuteunique2helper(original);
    } 
    
    public List<List<Integer>> permuteunique2helper(List<Integer> seed){
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	if (seed.size() == 0){
    		ArrayList<Integer> e = new ArrayList<Integer>();
    		output.add(e);
    		return output;
    	}
    	HashSet<Integer> hs = new HashSet<Integer>();
    	
    	for (int i = 0; i<seed.size(); ++i){
    		int pivot = seed.get(i);
    		if (!hs.contains(pivot)){
    			hs.add(pivot);
    			seed.remove(i);
    			List<List<Integer>> suboutput = permuteunique2helper(seed);
    			for(List<Integer> record : suboutput){
    				record.add(0,pivot);
    				output.add(record);
    			}
    			seed.add(i,pivot);
    		}
    	}
    	
    	return output;    	
    }
    
    public List<List<Integer>> permuteuniquehelper(int[] num, int start){
    	List<List<Integer>> output = new ArrayList<List<Integer>>();

    	if (start == num.length - 1){
    		List<Integer> single = new ArrayList<Integer>();
    		single.add(num[start]);
    		output.add(single);
    		return output;
    	}
    	List<List<Integer>> subpermute = permuteuniquehelper(num,start+1);
    	HashMap<String, List<Integer>> hm = new HashMap<String, List<Integer>>();
    	for (List<Integer> seq : subpermute){
    		for (int i=0;i<=seq.size();++i){
    			List<Integer> newseq = new ArrayList<Integer>(seq);
				newseq.add(i, num[start]);
				String pattern = "";
				for (Integer e : newseq){
					pattern += e;
				}
				if (!hm.containsKey(pattern)){
					hm.put(pattern, newseq);
					output.add(newseq);
				}
    		}
    	}
    	return output;
    }

    /*
    
    public List<List<Integer>> permuteuniquehelper(int[] num, int start){
    	List<List<Integer>> output = new ArrayList<List<Integer>>();

    	if (start == num.length - 1){
    		List<Integer> single = new ArrayList<Integer>();
    		single.add(num[start]);
    		output.add(single);
    		return output;
    	}
    	List<List<Integer>> subpermute = permuteuniquehelper(num,start+1);
    	for (List<Integer> seq : subpermute){
    		for (int i=0;i<=seq.size();++i){
    			//boolean added = false;
    			List<Integer> newseq = new ArrayList<Integer>(seq);
    			if (i<seq.size()){
					newseq.add(i, num[start]);
					//added = true;
    				if (seq.get(i) == num[start]){
    					//newseq.add(i, num[start]);
    					++i;
    					while (i < seq.size() && seq.get(i)==seq.get(i-1))
    						++i;
    				}
    			
    			
    			}else if (num[start]!=seq.get(seq.size()-1)){
					newseq.add(seq.size(), num[start]);
					//added = true;
    			}
    			//if (added)
    				output.add(newseq);
    		}
    	}
    	return output;
    }
	*/
    
    public List<List<Integer>> permute2(int[] num) {
    	if (num == null || num.length == 0)
    		return new ArrayList<List<Integer>>();
    	ArrayList<Integer> original = new ArrayList<Integer>();
    	for (int i = 0; i < num.length; ++i)
    		original.add(num[i]);
    	return permute2helper(original);
    }
    
    public List<List<Integer>> permute2helper(List<Integer> seed){
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	if (seed.size() == 0){
    		ArrayList<Integer> e = new ArrayList<Integer>();
    		output.add(e);
    		return output;
    	}
    	
    	for (int i = 0; i<seed.size(); ++i){
    		int pivot = seed.get(i);
    		seed.remove(i);
    		List<List<Integer>> suboutput = permute2helper(seed);
    		for(List<Integer> record : suboutput){
    			record.add(0,pivot);
    			output.add(record);
    		}
    		seed.add(i,pivot);
    	}
    	
    	return output;
    }
    
    public List<List<Integer>> permute(int[] num) {
    	if (num == null) return null;
    	if (num.length == 0) return new ArrayList<List<Integer>>();
    	return permutehelper(num, 0);
    }
    
    public List<List<Integer>> permutehelper(int[] num, int start){
    	List<List<Integer>> output = new ArrayList<List<Integer>>();

    	if (start == num.length - 1){
    		List<Integer> single = new ArrayList<Integer>();
    		single.add(num[start]);
    		output.add(single);
    		return output;
    	}
    	List<List<Integer>> subpermute = permutehelper(num,start+1);
    	for (List<Integer> seq : subpermute){
    		for (int i=0;i<=seq.size();++i){
    			List<Integer> newseq = new ArrayList<Integer>(seq);
    			newseq.add(i, num[start]);
    			output.add(newseq);
    		}
    	}
    	return output;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	int m = obstacleGrid.length;
    	int n = obstacleGrid[0].length;
    	int[][] result = new int[m][n];
    	
    	return result[0][0];
    }
    
    public int uniquePaths2(int m, int n){
    	if (m <= 0 || n <= 0)
    		return -1;
    	int[][] result = new int[m][n];
    	for(int i=0;i<m;++i)
    		result[i][0] = 1;
    	for(int j=0;j<n;++j)
    		result[0][j] = 1;
    	for(int i=1; i < m; ++i)
    		for(int j=1; j < n; ++j){
    			result[i][j] = result[i-1][j] + result[i][j-1];
    		}
    	return result[m-1][n-1];
    }
    
    public int uniquePaths(int m, int n) {
		if (m <= 0 || n <= 0) 
			return -1;
		else if (m ==1 || n == 1)
			return 1;
		else
			return uniquepathhelper(m-1,n) + uniquepathhelper(m, n-1);
    }
    
    public int uniquepathhelper(int x, int y){
		if (x==1 || y == 1)
			return 1;
		else
			return uniquepathhelper(x, y-1) + uniquepathhelper(x-1, y);
    }
	
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
    	if (node == null) return null;
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> clonemap = 
        		new HashMap<UndirectedGraphNode,UndirectedGraphNode>();
        q.add(node);
        UndirectedGraphNode cloned = new UndirectedGraphNode(node.label);
        clonemap.put(node, cloned);
        
        while(!q.isEmpty()){
        	UndirectedGraphNode cur = q.poll();
        	UndirectedGraphNode clonecur = clonemap.get(cur);
        	for (UndirectedGraphNode neighbor : cur.neighbors){
        		if (clonemap.get(neighbor)==null){
        			UndirectedGraphNode newneighbor = new UndirectedGraphNode(neighbor.label);
        			clonemap.put(neighbor, newneighbor);
        			clonecur.neighbors.add(newneighbor);
        			q.add(neighbor);
        		}else{
        			clonecur.neighbors.add(clonemap.get(neighbor));
        		}
        	}
        }
        return cloned;
    }
	
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node==null) return null;
        Set<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        HashMap<Integer, UndirectedGraphNode> clonehm = new HashMap<Integer,UndirectedGraphNode>();
        set.add(node); q.add(node);
        UndirectedGraphNode output = null;
    	UndirectedGraphNode clonecur = null;        
    	UndirectedGraphNode cur = null;
    	while(!q.isEmpty()){
        	cur = q.poll();
        	if (clonehm.get(cur.label) == null){
        		clonecur = new UndirectedGraphNode(cur.label);
        		clonehm.put(cur.label, clonecur);
        	}
        	else
        		clonecur = clonehm.get(cur.label);
        	if (output==null)
        		output = clonecur;

        	for (UndirectedGraphNode neighbor : cur.neighbors){
        		if (!set.contains(neighbor)){
        			set.add(neighbor);
        			q.add(neighbor);
        		}
        		if (clonehm.get(neighbor.label)!=null){
        				clonecur.neighbors.add(clonehm.get(neighbor.label));
        		}else{
        			UndirectedGraphNode newneighbor = new UndirectedGraphNode(neighbor.label);
        			clonehm.put(neighbor.label, newneighbor);
        			clonecur.neighbors.add(newneighbor);
        		}
        	}
        }
    	return output;
    }

    public void connectII(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode pre = root, cur = null, curmost=root, nextmost;
        pre.next = null;
        if (pre.left != null)
        	nextmost = pre.left;
        else
        	nextmost = pre.right; 
        
        boolean lefted = false;
        while (nextmost!=null){
        	pre = curmost;
        	cur = nextmost;
        	curmost = cur;
        	nextmost = null;
        	
        	while (pre!=null){
        		if (nextmost == null)
        			nextmost = cur.left != null ? cur.left : cur.right;
        		if (pre.left != null && !lefted){
        			lefted = true;
        			if (pre.left != cur){
        				cur.next = pre.left;
            			cur = cur.next;
        			}
        		}else if (pre.right != null && pre.right != cur){
        				cur.next = pre.right;
        				cur = cur.next;
        		}else{
        			pre = pre.next;
        			lefted = false;
        		}
        	}
        }
    }
	 
    public void connect2(TreeLinkNode root) {
    	if (root == null) return;
    	TreeLinkNode cur = root;
    	cur.next = null;
    	TreeLinkNode leftmost = cur.left;
    	while(leftmost!=null){
    		cur.left.next = cur.right;
    		if (cur.next!=null){
    			cur.right.next = cur.next.left;
    			cur = cur.next;
    		}else{
    			cur.right.next = null;
    			cur = leftmost;
    			leftmost = leftmost.left;
    		}
    	}
    }
	
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        TreeLinkNode magic = new TreeLinkNode(0);
        q.add(root); q.add(magic);
        TreeLinkNode pre = null, cur = null;
        while(!q.isEmpty()){
        	cur = q.poll();
        	if (cur == magic){
        		if (pre != null) pre.next = null;
        		pre = null;
        		if (!q.isEmpty()) 
        			q.add(magic);
        	}else{
        		if (cur.left != null) q.add(cur.left);
        		if (cur.right != null) q.add(cur.right);
        		if (pre!=null)
        			pre.next = cur;
        		pre = cur;
        	}
        }
    }
	
	public int maxPathSum(TreeNode root){
        if (root == null)
        	return maxpathsum;
        maxPathSumhelper(root);
        return maxpathsum;
	}
	
    private int maxPathSumhelper(TreeNode root) {
        int leftmax, rightmax;
        if (root.left == null && root.right == null){
        	if (root.val > maxpathsum)
        		maxpathsum = root.val;
        	return root.val;
        }
        if (root.left != null)
        	leftmax = maxPathSumhelper(root.left);
        else
        	leftmax = 0;
        
        if (root.right != null)
        	rightmax = maxPathSumhelper(root.right);
        else
        	rightmax = 0;
        
        int localmax = max(root.val, root.val + leftmax, root.val + rightmax);
        if (localmax > maxpathsum)
        	maxpathsum = localmax;
        if (root.val + leftmax + rightmax > maxpathsum)
        	maxpathsum = root.val + leftmax + rightmax;

        return localmax;
    }
	
	private int max(int a, int b, int c){
		if (a >= b && a >=c)
			return a;
		else if (b>=c)
			return b;
		else
			return c;
	}
    
    public void flatten2(TreeNode root) {
    	flattenhelper(root);
    }
    
    private TreeNode flattenhelper(TreeNode root){
    	if (root == null)
    		return root;
    	if (root.left != null){
    		TreeNode temp = root.left;
    		root.left = root.right;
    		root.right = temp;
    	}
    	
    	flattenhelper(root.left);
    	TreeNode rightmost = flattenhelper(root.right);
    	if (rightmost != null)
    		rightmost.right = root.left;
    	else{
    		rightmost = root;
    		root.right = root.left;    		
    	}
    	while (rightmost.right != null)
    		rightmost = rightmost.right;
    	root.left = null;
    	return rightmost;
    }
	
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> st = new Stack<TreeNode>();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        st.push(root);
        TreeNode cur = null;
        while(!st.isEmpty()){
        	cur = st.pop();
        	q.add(cur);
        	if (cur.right != null)
        		st.push(cur.right);
        	if (cur.left != null)
        		st.push(cur.left);        	
        }
        cur = root;
        q.poll();
        while(!q.isEmpty()){
        	cur.left = null;
        	cur.right = q.poll();
        	cur = cur.right;
        }
        return;
        /*
        cur.left = null;
        cur.right = null;
        */
    }
	
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if (root == null) return output;
        Stack<TreeNode> cur_layer = new Stack<TreeNode>(); cur_layer.push(root);
        Stack<TreeNode> next_layer = new Stack<TreeNode>();
        List<Integer> layer_output = new ArrayList<Integer>();
        int d = 0; // 0: left to right; 1: right to left.
        
        while (!cur_layer.isEmpty()){
        	TreeNode node = cur_layer.pop();
        	layer_output.add(node.val);
        	if(d==0){
        		if (node.left != null) next_layer.push(node.left);
        		if (node.right != null) next_layer.push(node.right);
        	}else{
        		if (node.right != null) next_layer.push(node.right);
        		if (node.left != null) next_layer.push(node.left);
        	}
        	
        	if (cur_layer.isEmpty()){
        		output.add(layer_output);
        		layer_output = new ArrayList<Integer>();
        		cur_layer = next_layer;
        		next_layer = new Stack<TreeNode>();;
        		d ^= 1;
        	}
        }
        return output;
    }

    public List<List<Integer>> pathSumII2(TreeNode root, int sum) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
    	ArrayList<Integer> path = new ArrayList<Integer>();   	
        if (root == null) return output;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(root);
        int remaining = sum;
        //path.add(root.val);
        TreeNode pre = null;
        while (!st.empty()){
        	TreeNode cur = st.peek();
        	if (cur.left == null && cur.right == null){
        		if (remaining - cur.val == 0){
        			ArrayList<Integer> finalpath = new ArrayList<Integer>(path);
        			finalpath.add(cur.val);
        			output.add(finalpath);
        		}
        		pre = cur;
        		st.pop();
    			continue;
        	}
        	
    		if (pre!=null && (cur.left == pre || cur.right == pre )){
    			st.pop();
    			remaining += cur.val;
    			path.remove(path.size()-1);
    			pre = cur;
    		}
    		else{
    			path.add(cur.val);
    			remaining -= cur.val;
    			if (cur.right != null) st.push(cur.right);
    			if (cur.left != null) st.push(cur.left);
    		}
        }
        return output;
    	
    }
    public List<List<Integer>> pathSumII(TreeNode root, int sum) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
    	ArrayList<Integer> path = new ArrayList<Integer>();   	
        if (root == null) return output;
        //path.add(root.val);
        pathSumIIhelper(root,sum,output,path);
    	return output;
    }
    
    private void pathSumIIhelper(TreeNode root, int remaining, List<List<Integer>> output, ArrayList<Integer> path){
    	if (root == null) return;
    	if (root.left == null && root.right == null && remaining-root.val==0){
    		ArrayList<Integer> finalpath = new ArrayList<Integer>(path);
    		finalpath.add(root.val);
    		output.add(finalpath);
    	}else{
    		path.add(root.val);
    		pathSumIIhelper(root.left, remaining-root.val, output, path);
    		pathSumIIhelper(root.right, remaining-root.val, output, path);
    		path.remove(path.size()-1);
    	}
    }
	
    public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
    	if (inorder == null || postorder == null || inorder.length != postorder.length)
    		return null;
    	HashMap<Integer, Integer> hm = new HashMap<Integer,Integer>();
    	for (int i=0;i<inorder.length;++i)
    		hm.put(inorder[i], i);
    	return buildTreePostIn(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1,hm);
    }
    
    private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer,Integer> hm){
    	if (ps>pe || is>ie) return null;
    	TreeNode root = new TreeNode(postorder[pe]);
    	int ri = hm.get(postorder[pe]);
    	TreeNode leftchild = buildTreePostIn(inorder, is, ri-1, postorder, ps, ps+ri-is-1, hm);
    	TreeNode rightchild = buildTreePostIn(inorder,ri+1, ie, postorder, ps+ri-is, pe-1, hm);
    	root.left = leftchild;
    	root.right = rightchild;
    	return root;
    }
	
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
    	if (preorder == null || inorder == null ||
    			preorder.length != inorder.length)
    		return null;
    	HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
    	for (int i = 0; i<inorder.length; ++i)
    		hm.put(inorder[i], i);
    	return buildTreePreIn(preorder, 0, preorder.length-1,inorder,0,
    			inorder.length-1, hm);
    }
    
    private TreeNode buildTreePreIn(int[] preorder, int ps, int pe, 
    		int[] inorder, int is, int ie, HashMap<Integer,Integer> hm){   	
    	if (ps>pe || is>ie) return null;
    	TreeNode root = new TreeNode(preorder[ps]);
    	int ri = hm.get(preorder[ps]); // root index
    	TreeNode leftchild = buildTreePreIn(preorder, ps+1, ps+ri-is, inorder, is, ri-1,hm);
    	TreeNode rightchild = buildTreePreIn(preorder,ps+ri-is+1, pe, inorder, ri+1, ie,hm);
    	root.left = leftchild;
    	root.right = rightchild;    	
    	return root;
    } 
    
    public TreeNode buildTreePreIn2(int[] preorder, int[] inorder) {
    	if (preorder == null || inorder == null || preorder.length ==0 
    			|| inorder.length == 0 || preorder.length != inorder.length)
    		return null;
    	int i=1, j=0, flag = 0;
    	TreeNode root = new TreeNode(preorder[0]);
    	TreeNode cur = null;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	st.push(root);
    	
    	while (i<preorder.length){
    		if (!st.isEmpty() && st.peek().val == inorder[j]){
    			cur = st.pop();
    			flag = 1;
    			j++;
    		}
    		else if (flag == 1){
    			TreeNode node = new TreeNode(preorder[i]);
    			cur.right = node;
    			st.push(node);
    			flag = 0;
    			i++;
    		}else{
    			TreeNode node = new TreeNode(preorder[i]);
    			st.peek().left = node;
    			st.push(node);
    			i++;
    		}    			
    	}
    	return root;
    }    

    public String simplifyPath(String path) {
    	if (path == null || path.length() == 0) return path;
    	if (path.charAt(0) != '/') return "";

    	String output = "/", current = "";
    	for (int i = 1; i<path.length(); ++i){
    		if (path.charAt(i)!= '/' ){
    			current += path.charAt(i);
    			if (i != path.length()-1)
    				continue;
    		}
    		if (current.equals("") || current.equals("."))
    			;//output = output.substring(0, output.lastIndexOf('/')+1);
    		else if (current.equals("..")){
    			int index = output.lastIndexOf('/');
    			if (index >=1){
    				String temp = output.substring(0,index);
    				index = temp.lastIndexOf('/');
    				if (index >= 0){
    					temp = temp.substring(0,index+1);
    					output = temp;
    				}
    			}
    		}
    		else{
    			output = output + current + "/";
    		}
    		current = "";	
    	}
    	/*
    	if (current.equals("..")){
			int index = output.lastIndexOf('/');
			if (index >=1){
				String temp = output.substring(0,index);
				index = temp.lastIndexOf('/');
				if (index >= 0){
					temp = temp.substring(0,index+1);
					output = temp;
				}
			}
		} else if (!current.equals("."))
			output += current;
		*/	
    	if (output.endsWith("/") && output.length() > 1)
    		output = output.substring(0, output.length()-1);
    	return output;
    }
	
    public boolean hasPathSum(TreeNode root, int sum) {
    	return pathsumhelper(root,0,sum);
    }
	
    private boolean pathsumhelper(TreeNode root, int psum, int sum){
    	if (root==null) return false;
    	if (root.left==null && root.right==null && root.val + psum == sum)
    		return true;
    	return pathsumhelper(root.left,psum+root.val,sum) || 
    			pathsumhelper(root.right,psum+root.val,sum);
    }
    
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) return head;
        ListNode newhead = head, tail = head, pre=head;
        int count = 1;
        while(tail.next != null){
        	++count;
        	tail = tail.next;
        }
        int k = n % count;
        if (k==0) return head;
        k = count-k;
        //System.out.println("count " + count + " K " + k);
        //count = 0;
        while (--k >= 0){
        	//++count;
        	pre=newhead;
        	newhead = newhead.next;
        }
        tail.next = head;
        pre.next = null;
        return newhead;
    }
	
    public void recoverTree(TreeNode root) {
    	recoverhelper (root);
    	if (s1!=null && s2!=null){
    		int temp = s1.val;
    		s1.val = s2.val;
    		s2.val = temp;
    	}
    	return;
    }
    
    private void recoverhelper(TreeNode root){
    	if (root == null) return;
    	recoverhelper(root.left);
    	if (root.val < pre_node.val){
    		if (s1==null)
    			s1 = pre_node;
    		s2 = root;
    	}
    	pre_node = root;
    	recoverhelper(root.right);
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {
    	ListNode dummyhead = new ListNode(0);
    	dummyhead.next = head;
    	ListNode sublisthead = new ListNode(0);
    	ListNode sublisttail = new ListNode(0);
    	int count = 1;
    	ListNode pre_cur = dummyhead, cur = head;
    	while(count <=n){
			ListNode temp = cur.next;
    		if (count < m)
    			pre_cur = cur;
    		else if (count == m){
    			sublisttail = cur;
    			sublisthead.next = cur;
    		}else if (count > m){
    			cur.next = sublisthead.next;
    			sublisthead.next = cur;
    		}
    		cur = temp;
    		++count;
    	}
    	pre_cur.next = sublisthead.next;
    	sublisttail.next = cur;
    	return dummyhead.next;
    }
	
    public ListNode reverseBetween(ListNode head, int m, int n) {
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	int count = 1;
    	ListNode pre_cur = dummy, cur = head;
    	Stack<ListNode> st = new Stack<ListNode>();
    	while(count<=n){
    		if (count >= m)
    			st.push(cur);
    		if (count < m)
    			pre_cur = cur;
    		cur = cur.next;
    		++count;   		
    	}
    	while(!st.isEmpty()){
    		pre_cur.next = st.pop();
    		pre_cur = pre_cur.next;
    	}
    	pre_cur.next = cur;
    	return dummy.next;
    }

	
    public List<List<Integer>> subsets2(int[] S) {
    	if (S==null) return null;
    	Arrays.sort(S);
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	for(int i=0;i<S.length;++i){
    		List<List<Integer>> temp = new ArrayList<List<Integer>>();
    		// Copy the existing sets
    		for (List<Integer> a : output)
    			temp.add(new ArrayList<Integer>(a));

    		// Add S[i] to each subset
    		for (List<Integer> a : temp)
    			a.add(S[i]);
    		
    		// Add [S[i]] as a new set 
    		ArrayList<Integer> single = new ArrayList<Integer>();
    		single.add(S[i]);
    		temp.add(single);
    		output.addAll(temp); // Add the new sets to existing sets	
    	}
    	// Add the empty set
    	output.add(new ArrayList<Integer>());
    	
    	return output;
    }
	
    public List<List<Integer>> subsets(int[] S) {
    	if (S==null) return null;
    	Arrays.sort(S);
    	HashSet<List<Integer>> powerset = new HashSet<List<Integer>>();
    	ArrayList<Integer> mainlist = new ArrayList<Integer>();
    	for(int i=0;i<S.length;++i)
    		mainlist.add(S[i]);
    	buildPowerset(mainlist,powerset);
    	List<List<Integer>> output = new ArrayList<List<Integer>>();
    	powerset.add(new ArrayList<Integer>());
    	output.addAll(powerset);
    	return output;
    }	
   
    private void buildPowerset(List<Integer> list, HashSet<List<Integer>> powerset){
    	if (list.size()>0)
    		powerset.add(list);
    	for (int i=0;i<list.size();i++){
    		ArrayList<Integer> sublist = new ArrayList<Integer>(list);
    		sublist.remove(i);
    		buildPowerset(sublist,powerset);
    	}
    }
	
    public boolean isValidBST2(TreeNode root) {
		if (root == null) return true;
		if (!isValidBST2(root.left))
			return false;
		
		if (root.val <= prev)
			return false;
		prev = root.val;
		
		return isValidBST2(root.right);
    }
	
    public boolean isValidBST(TreeNode root) {
		if (root == null) return true;
		Stack<TreeNode> st = new Stack<TreeNode>();
		boolean result = true;
		int pre_val=Integer.MIN_VALUE;
		TreeNode cur = root;
		do{
			while (cur!=null){
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			if (pre_val >= cur.val){
				result = false;
				break;
			}					
			pre_val = cur.val;
			cur = cur.right;
		}while(!st.isEmpty() || cur != null);
		return result;
    }
	
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;  // only one has node in a tree and b tree
        if (a.val != b.val) return false;
        return helper(a.left, b.right) && helper(a.right, b.left); // a goes in in-order traversal, b goes right first then left.
    }	

    public boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        boolean result = true;
        ArrayList<TreeNode> layer = new ArrayList<TreeNode>();
        TreeNode dummy = new TreeNode(0);
        layer.add(root);
        while(result){
        	if (layer.isEmpty() || layer.get(0)==null)
        		break;
        	for(int i=0;i<layer.size()/2+1;++i){
        		TreeNode left = layer.get(i);
        		TreeNode right = layer.get(layer.size()-i-1);
        		if (left == dummy && right == dummy)
        			continue;
        		if (left==dummy||right==dummy){
        			result = false;
        			break;
        		}
        		if (left.val != right.val){
        			result = false;
        			break;
        		}
        	}
        	ArrayList<TreeNode> newlayer = new ArrayList<TreeNode>();
        	for(TreeNode node : layer){
        		if (node == dummy) continue;
        		if (node.left == null)
        			newlayer.add(dummy);
        		else
        			newlayer.add(node.left);
        		if (node.right == null)
        			newlayer.add(dummy);
        		else
        			newlayer.add(node.right);        		
        	}
        	layer = newlayer;
        }        
        return result;
    }
	
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if (root == null) return output;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode magic = new TreeNode(0);
        q.add(root); q.add(magic);
        List<Integer> layer = new ArrayList<Integer>();
        TreeNode cur;
        while(!q.isEmpty()){
        	cur = q.poll();
        	if (cur == magic){
        		output.add(0,layer);
        		layer = new ArrayList<Integer>();
        		if(!q.isEmpty())
        			q.add(magic);
        	}else{
        		layer.add(cur.val);
        		if (cur.left!=null)
        			q.add(cur.left);
        		if (cur.right!=null)
        			q.add(cur.right);
        	}
        }
        return output;        
    }
	
	public List<List<Integer>> levelOrder2(TreeNode root) {
	    List<List<Integer>> result = new ArrayList<List<Integer>>();
	    List<TreeNode> level = new ArrayList<TreeNode>();
	    level.add(root);
	    while(true){
	        if (level.isEmpty() || level.get(0) == null){
	            break;
	        }
	        List<TreeNode> nextLevel = new ArrayList<TreeNode>();
	        List<Integer> currentLevel = new ArrayList<Integer>();

	        for (TreeNode node : level){
	            currentLevel.add(node.val);
	            if (node.left != null) nextLevel.add(node.left);
	            if (node.right != null) nextLevel.add(node.right);
	        }
	        result.add(currentLevel);
	        level = nextLevel;
	    }
	    return result;
	}
	
	
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<List<Integer>>();
        if (root == null) return output;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        TreeNode magic = new TreeNode(0);
        q.add(root); q.add(magic);
        List<Integer> layer = new ArrayList<Integer>();
        TreeNode cur;
        while(!q.isEmpty()){
        	cur = q.poll();
        	if (cur == magic){
        		output.add(layer);
        		layer = new ArrayList<Integer>();
        		if(!q.isEmpty())
        			q.add(magic);
        	}else{
        		layer.add(cur.val);
        		if (cur.left!=null)
        			q.add(cur.left);
        		if (cur.right!=null)
        			q.add(cur.right);
        	}
        }
        return output;
    }
	
	 public TreeNode sortedListToBST(ListNode head) {
		 int len = 0;
		 ListNode dummy = new ListNode(0);
		 dummy.next = head;
		 while (head != null){
			 head = head.next;
			 ++len;
		 }
		 TreeNode root = createBST(dummy,0,len-1);
		 return root;
	 }
	 
	 public TreeNode createBST(ListNode list, int s, int e){
		 if (s>e) return null;
		 int mid = (s+e)/2;
		 TreeNode leftchild = createBST(list,s,mid-1);
		 TreeNode parent = new TreeNode(list.next.val);
		 parent.left= leftchild;
		 list.next = list.next.next;
		 TreeNode rightchild = createBST(list,mid+1,e);
		 parent.right = rightchild;
		 return parent;
	 }
	
	public boolean isAVLBalance = true;

    public int searchInsert(int[] A, int target) {
    	if (A==null) return 0;
    	int s = 0, e = A.length-1;
    	while(s<=e){
    		int mid = (s+e)/2;
    		if (A[mid]==target)
    			return mid;    		
    		if (target<A[mid]){
    			e = mid - 1;
    		}
    		else{
    			s = mid + 1;
    		}
    	}
    	return e+1;
    }
	
    
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        /*
        if (root.left == null && root.right == null)
    		// Leaf node
    		return 1;
    	*/
    	//if (root.left != null)
        int hleft = minDepth(root.left);
    	//if (root.right != null)
    	int hright = minDepth(root.right);
    	
    	if (hleft == 0)
    		// Non leaf node and left is null
    		return hright + 1;
    	if (hright == 0)
    		// Non leaf node and right is null
    		return hleft + 1;
    	return hleft < hright ? hleft+1 : hright+1;
    }
	
	public boolean isAVLBalanced2(TreeNode root) {
    	//if (root == null) return true;
		maxDepth2(root);
		return isAVLBalance;
    }
	
    public  int maxDepth2(TreeNode root) {
    	if (root == null) 
    		return 0;
    	else{
    		int hleft = maxDepth(root.left);
    		int hright = maxDepth(root.right);
    		if (Math.abs(hleft-hright)>1)
    			isAVLBalance = false;
        	return hleft > hright ? hleft+1 : hright + 1;
    	}
    }
	
    public boolean isAVLBalanced(TreeNode root) {
    	if (root == null) return true;
    	boolean rootbalance;
    	if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <=1)
    		rootbalance = true;
    	else
    		return false;
    	return rootbalance && isAVLBalanced(root.left) && isAVLBalanced(root.right);
    }
    
    public  int maxDepth(TreeNode root) {
    	if (root == null) 
    		return 0;
    	return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
	
    public boolean isBalanced(TreeNode root) {
        if (root == null) return false;
        int max = height(root, true);
        int min = height(root, false);
        
        if (max-min > 1)
        	return false;
        else
        	return true;
    }

    public int height(TreeNode root, boolean trueheight){
    	int hleft = -1, hright = -1;
    	if (root.left == null && root.right == null)
    		return 0;
    	if (root.left != null)
    		hleft = height(root.left,trueheight) + 1;
    	if (root.right != null)
    		hright = height(root.right,trueheight) + 1;
    	if (hleft == -1)
    		return hright;
    	else if (hright == -1)
    		return hleft;
    	else{
    		int max = hleft > hright ? hleft : hright;
    		int min = hleft < hright ? hleft : hright;
    		return trueheight ? max : min;
    	}
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();    	
		if (root == null) return output;
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode cur = root;
		do{
			while (cur!=null){
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			output.add(cur.val);
			cur = cur.right;
		}while(!st.isEmpty() || cur != null);
		return output;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	st.push(root);
    	TreeNode pre = null;
    	while (!st.isEmpty()){
    		TreeNode cur = st.peek();    			
    		if (cur.left == null && cur.right == null){
    			output.add(cur.val);
    			st.pop();
    			pre = cur;
    			continue;
    		}
    		
    		if (pre!=null && (cur.left == pre || cur.right == pre )){
    			output.add(cur.val);
    			st.pop();
    			pre = cur;
    		}
    		else{
    			if (cur.right != null) st.push(cur.right);
    			if (cur.left != null) st.push(cur.left);
    		}
    	}
    	return output;
    }

    
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return null;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	HashMap<TreeNode, Boolean> hm = new HashMap<TreeNode, Boolean>();
    	st.push(root);
    	while (!st.isEmpty()){
    		TreeNode cur = st.pop();    			
    		if (cur.left == null && cur.right == null)
    			output.add(cur.val);
    		else if (hm.containsKey(cur))
    			output.add(cur.val);
    		else if (cur.left != null || cur.right != null){
    			st.push(cur);
    			hm.put(cur, true);
    			if (cur.right != null) st.push(cur.right);
    			if (cur.left != null) st.push(cur.left);
    		}
    	}
    	return output;
    }
    
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return null;
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        st.push(cur);
        do{
        	cur = st.pop();
        	output.add(cur.val);
        	if (cur.right != null)
        		st.push(cur.right);
        	if (cur.left != null)
        		st.push(cur.left);
        }while(!st.isEmpty());
        return output;
    }
	
    public ListNode partition2(ListNode head, int x) {
    	if (head == null || head.next == null) return head;
    	ListNode dummy = new ListNode(0);
    	ListNode cur = head, pmark = dummy, tail = null;
    	
    	while (cur!=null){
    		ListNode p = cur;
    		cur = cur.next;
    		
    		if (p.val < x){
    			p.next = pmark.next;
    			pmark.next = p;
    			pmark = pmark.next;
    		}else{
    			if (tail ==null){
    				pmark.next = p;
    				p.next = null;
    				tail = p;
    			}else{
    				tail.next = p;
    				p.next = null;
    				tail = tail.next;
    			}
    		}
    	}
    	return dummy.next;
    }

	
    public ListNode partition(ListNode head, int x) {
    	if (head == null || head.next == null) return head;
    	ListNode dummy = new ListNode(0);
    	ListNode cur = head, pmark = dummy, xmark = null, tail = null;
    	
    	while (cur!=null){
    		ListNode p = cur;
    		cur = cur.next;
    		
    		if (p.val > x){
    			if (tail == null){
    				if (xmark==null)
    					pmark.next = p;
    				else
    					xmark.next = p;
    				p.next = null;
    				tail=p;
    			}else{
    				tail.next = p;
    				p.next = null;
    				tail = tail.next;
    			}
    		}else if (p.val < x){
    			p.next = pmark.next;
    			pmark.next = p;
    			pmark = pmark.next;
    		}else{
    			if (xmark ==null){
    				p.next = pmark.next;
    				pmark.next = p;
    				xmark = pmark.next;
    			}else{
    				p.next = xmark.next;
    				xmark.next = p;
    				xmark = xmark.next;
    			}
    		}
    	}
    	
    	return dummy.next;
    }
	
    public int singleNumber(int[] A) {
    	if (A==null || A.length==0) return Integer.MIN_VALUE;
    	int result = A[0];
    	for (int i=1;i<A.length;++i){
    		result ^= A[i];
    	}
    	return result;
    }
	

	public String addBinary2(String a, String b) {
	    int m = a.length();
	    int n = b.length();
	    int carry = 0;
	    String res = "";
	    // the final length of the result depends on the bigger length between a and b, 
	    // (also the value of carry, if carry = 1, add "1" at the head of result, otherwise)
	    int maxLen = Math.max(m, n);
	    for (int i = 0; i < maxLen; i++) {
	        // start from last char of a and b
	        // notice that left side is int and right side is char
	        // so we need to  minus the decimal value of '0'
	        int p = i < m ? a.charAt(m - 1 - i) - '0' : 0;
	        int q = i < n ? b.charAt(n - 1 - i) - '0' : 0;
	        int tmp = p + q + carry;
	        carry = tmp / 2;
	        res = tmp % 2 + res;
	    }
	    return (carry == 0) ? res : "1" + res;
	}
	
    public String addBinary(String a, String b) {
    	if (a == null || a.length() == 0) return b;
    	if (b==null || b.length()==0) return a;
    	int alen = a.length(), blen = b.length();
    	int loops = (alen < blen) ? alen : blen;
    	int offset = alen < blen ? blen - alen : alen - blen;
    	StringBuffer sbuf = new StringBuffer("");
    	int c = 0;
    	
    	for(int i=0; i<loops; ++i){
    		int a1 = Character.getNumericValue(a.charAt(alen-i-1));
    		int b1 = Character.getNumericValue(b.charAt(blen-i-1));
    		int sum = a1 ^ b1 ^ c;
    		c = (a1+b1+c) >> 1;
    		sbuf.insert(0, String.valueOf(sum).charAt(0));
    	}
    	if (alen < blen)
    		c = this.process(b, offset, c, sbuf);
    	else if (alen > blen)
    		c = this.process(a, offset, c, sbuf);
    	
    	if (c==1)
    		sbuf.insert(0, '1');
    	
    	return sbuf.toString();
    }	
	
	private int process (String s, int offset, int c, StringBuffer sbuf){
		for(int i=offset-1;i>=0;--i){
			int a = Character.getNumericValue(s.charAt(i));
			int b = a ^ c;
			c = a & c;
			sbuf.insert(0, String.valueOf(b).charAt(0));
		}
		return c;
	}
	
    public int[] plusOne2(int[] digits) {
    	for(int i=digits.length-1;i>=0;--i){
    		digits[i]++;
    		if(digits[i]<10)
    			break;
    		digits[i] = 0;
    	}
    	if (digits[0] == 0){
    		int[] newdigits = new int[digits.length+1];
    		System.arraycopy(digits, 0, newdigits, 1, digits.length);
    		newdigits[0] = 1;
    		return newdigits;
    	}else
    		return digits;
    	
    }
	
	
    public int[] plusOne(int[] digits) {
    	if (digits == null || digits.length == 0) return digits;
    	int curdigit = digits[digits.length-1];    	
    	if (curdigit > 9 || curdigit < 0) return null;
    	digits[digits.length-1] = (curdigit + 1) % 10;
    	int c = (curdigit + 1) / 10;
    	
    	if (c<1)
    		return digits;
    	
    	for (int i=digits.length-2;i>=0;i--){
    		curdigit = digits[i];
    		if (curdigit > 9 || curdigit < 0) return null;
    		digits[i] = (curdigit + c) % 10;
    		c = (curdigit + c) / 10;
    		if (c==0)
    			break;
    	}
    		
    	if (c==1){
    		int[] newdigits = new int[digits.length+1];
    		newdigits[0] = c;
    		for (int i=0; i< digits.length; ++i){
    			newdigits[i+1] = digits[i];
    		}
    		return newdigits;
   		}else
   			return digits;  	
    }

    public ListNode detectCycle(ListNode head) {
    	if (head == null) return head;
        ListNode n1 = head, n2 = head;
        while (n2!=null && n2.next != null){
        	n1 = n1.next;
        	n2 = n2.next.next;
        	if (n1==n2) break;
        }
        
        if (n2 == null || n2.next == null) 
        	return null;
        
        n1 = head;
        while(n1!=n2){
        	n1 = n1.next;
        	n2 = n2.next;
        }
        return n1;
    }
    
    public boolean hasCycle2(ListNode head) {
    	ListNode slow = head, fast = head;
    	while (fast != null && fast.next != null){
    		slow = slow.next;
    		fast = fast.next.next;
    		if (slow == fast)
    			return true;
    	}
    	return false;
    }
    
	
    public boolean hasCycle(ListNode head) {
    	int v = 0;
    	boolean cycle = false;
    	HashMap<ListNode, Integer> hm = new HashMap<ListNode,Integer>();
    	
    	ListNode cur = head;
    	while (cur!=null){
    		if (!hm.containsKey(cur)){
    			hm.put(cur, ++v);
    			cur = cur.next;
    		}else{
    			cycle = true;
    			break;
    		}
    	}
    	return cycle;
    }

	public int[] maxSubArray3(int[] A) {
		int[] result = new int[3];
		int left=0, right=0;
	    int maxSum = Integer.MIN_VALUE;
	    int curSum = 0;
	    for(int i = 0; i < A.length; i++)
	    {
	    	if (curSum == 0){
	            left = i;
	            right = i;
	    	}
	        curSum = curSum + A[i];
	        maxSum = maxSum > curSum ? maxSum : curSum;
	        right = maxSum > curSum ? right : i;
	        if(curSum < 0){
	            curSum = 0;
	        }	        
	    }

	    result[0] = left;
	    result[1] = right;
	    result[2] = maxSum;
	    return result;
	}

	
	public int maxSubArray2(int[] A) {
	    int maxSum = Integer.MIN_VALUE;
	    int curSum = 0;
	    for(int i = 0; i < A.length; i++)
	    {
	        curSum = curSum + A[i];
	        maxSum = Math.max(maxSum, curSum);
	        if(curSum < 0)
	            curSum = 0;
	    }

	    return maxSum;
	}
	
    public int maxSubArray(int[] A) {
        if (A==null || A.length == 0) return Integer.MIN_VALUE;
        int[] full = this.findMaxSubarray(A, 0, A.length-1);
        return full[2];
    }	
	
    public int[] findMaxSubarray(int[] A, int low, int high){
    	int[] result = new int[3];
    	if (low == high){
    		result[0] = low;
    		result[1] = high;
    		result[2] = A[low];
    		return result;
    	}

    	int mid = (low+high)/2;
    	int[] left = this.findMaxSubarray(A, low, mid);
    	int[] right = this.findMaxSubarray(A, mid+1, high);
    	int[] cross = this.findMaxCross(A, low, mid, high);
    	
    	if (left[2]>=right[2] && left[2]>=cross[2])
    		return left;
    	else if (right[2]>=left[2] && right[2]>=cross[2])
    		return right;
    	else
    		return cross;
    }
    
	public int[] findMaxCross(int[] A, int low, int mid, int high){
		int lsum = Integer.MIN_VALUE, rsum = Integer.MIN_VALUE, sum = 0;
		int lmaxindex = mid, rmaxindex = mid+1;
		for(int i=mid; i>=low; --i){
			sum += A[i];
			if (lsum < sum ){
				lsum = sum;
				lmaxindex = i;
			}
		}
		sum = 0;
		for(int i=mid+1; i<=high; ++i){
			sum += A[i];
			if (rsum < sum){
				rsum = sum;
				rmaxindex = i;
			}
		}
		int result[] = {lmaxindex,rmaxindex,lsum+rsum};
		return result;
	}
	
    public int[] searchRange(int[] A, int target) {
    	int[] result = {-1,-1};
    	if (A==null || A.length == 0) return result;
    	int s=0, e=A.length-1, m = (s+e)/2;
    	while (s<=e){
    		m = (s+e)/2;
    		if (target<A[m])
    			e = m-1;
    		else if (target>A[m])
    			s = m+1;
    		else
    			break;
    	}
    	if (s>e) return result;
    	
    	int base=m, base_s=s,base_e = e;
    	int pre=base, post=base;
    	
    	s = base+1; e=base_e;
    	while (s<=e){
    		m = (s+e)/2;
    		if (target != A[m])
    			e = m-1;
    		else{
    			post = m;
    			s=m+1;
    		}
    	}
    	
    	s=base_s; e=base-1;
    	while(s<=e){
    		m = (s+e)/2;
    		if (target != A[m])
    			s=m+1;
    		else{
    			pre=m;
    			e=m-1;
    		}
    	}
    	result[0] = pre; result[1] = post;
    	return result;
    }
	
    public double pow(double x, int n) {
        if (x==0.0)
        	return n>=0 ? 0 : Double.MIN_VALUE;
        double abs_x = x > 0 ? x : -x;
        int abs_n = n > 0 ? n : -n;
        double result = calpow(abs_x,abs_n);
        if (x<0)
        	result = abs_n % 2 == 0 ? result : - result;
        result = n > 0 ? result : 1/result;
        return result;
        	
    }
    
    public double calpow(double x, int n){
    	if (n==0) return 1.0;
    	double x_n_2 = calpow(x,n/2);
    	if (n % 2 == 0)
    		return x_n_2*x_n_2;
    	else
    		return x_n_2*x_n_2*x;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
    	if (k<=0) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode prek = dummy, fast = head;
    	while (fast != null){
    		boolean done = false;
    		for(int i=0;i<k;++i){
    			if (fast==null){
    				done = true;
    				break;
    			}
    			else
    				fast = fast.next;
    		}
    		if (done) break;
    		ListNode curk = prek.next;
    		while(curk.next!=fast){
    			ListNode nt = curk.next.next;
    			curk.next.next = prek.next;
    			prek.next = curk.next;
    			curk.next = nt;
    		}
    		prek = curk; 
    	}
    	return dummy.next;
    }
	
    public ListNode reverseKGroup(ListNode head, int k) {
    	if (k<=0) return head;
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	Stack<ListNode> s = new Stack<ListNode>();
    	ListNode prek = dummy, cur = head;
    	
    	while (cur != null){
    		boolean done = false;
    		for(int i=0;i<k;++i){
    			if (cur!=null)
    				s.push(cur);
    			else{
    				done = true;
    				break;
    			}
    			cur = cur.next;
    		}
    		
    		if (done) break;
    		while(!s.isEmpty()){
    			prek.next = s.pop();
    			prek = prek.next;
    		}
    		prek.next = cur;
    	}
    	return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
    	ListNode dummy = new ListNode(0);
    	dummy.next = head;
    	ListNode cur = head, pre_pair = dummy;
    	while (cur != null && cur.next != null){
    		ListNode suc_pair = cur.next.next;
    		pre_pair.next = cur.next;
    		cur.next.next = cur;
    		cur.next = suc_pair;
    		pre_pair = cur;
    		cur = cur.next;
    	}
        return dummy.next;
    }

	
    public int sqrt2(int x) {
    	if (x<=1) return x;
    	
    	int left = 0, right = x, mid;
    	
    	while (right-left>1){
    		mid = left + (right-left)/2;
    		if (mid == x/mid)
    			return mid;
    		else if (mid > x/mid)
    			right = mid;
    		else
    			left = mid;
    	}
    	
    	return left;
    	
    }
	
    public int sqrt(int x) {
    	if (x<=1) return x;
    	return calsqrt(1,x-1,x,1);
    }

    public int calsqrt(int s, int l, int x, long prevaule){
    	if (s>l) return (int)prevaule;
    	int test = (s+l)/2;
    	long test_result = (long)test * (long)test;
    	if (test_result == (long)x)
    		return test;
    	else if (test_result > (long)x)
    		return calsqrt(s,(s+l)/2-1,x,prevaule);
    	else
    		return calsqrt((s+l)/2+1,l,x,test);
    }
    
	
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null || n == 0) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy, pre_slow = dummy;
        for(int i=0;i<n;++i)
        	fast = fast.next;
        while(fast != null){
        	fast = fast.next;
        	pre_slow = slow;
        	slow = slow.next;
        }
        pre_slow.next = slow.next;
        return dummy.next;
    }
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n == 0) return head;
        if (head.next == null && n > 0) return null;
        
        Stack<ListNode> s = new Stack<ListNode>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head, pre_cur = dummy;
        
        while (cur.next != null){
        	s.push(cur);
        	pre_cur = cur;
        	cur = cur.next;
        }
        for (int i=0; i<n; ++i){
        	pre_cur = s.pop();
        	cur = pre_cur.next;
        }
        pre_cur.next = cur.next;
        return dummy.next;
    }

    public double findMedianSortedArrays3(int[] A, int[] B) {
    	if (A==null || B==null) return 0;
    	if (A.length == 0 && B.length == 0) return 0;
    	
    	int m1 = (A.length + B.length + 1) >> 1;
        int m2 = (A.length + B.length + 2) >> 1;
        
        return (getkth(A, 0, A.length, B, 0, B.length, m1) + getkth(A, 0, A.length, B, 0, B.length, m2))/2.0;
        
    }
    
    public double getkth(int[] S, int ss, int slen, int[] L, int sl, int llen,int k){
    	if (slen > llen)
    		return getkth(L,sl,llen,S,ss,slen,k);
    	if (slen == 0) return L[sl+k-1];
    	if (k == 1) return min(S[ss],L[sl]);
    	
    	int i = min(slen, k/2), j = min(llen, k/2);
    	if (S[ss+i-1] > L[sl+j-1])
    		return getkth(S,ss,slen,L,sl+j,llen-j,k-j);
    	else
    		return getkth(S,ss+i,slen-i,L,sl,llen,k-i);
    	
    	//return 0;
    }
    
    public double findMedianSortedArrays2(int[] A, int[] B) {
    	if(A.length > B.length)
    		return findMedianSortedSLArrays(B,0,B.length,A,0,A.length);
    	else
    		return findMedianSortedSLArrays(A,0, A.length,B,0, B.length);
    }
    	    
    //S for shorter or smaller , L for longer or larger
    public double findMedianSortedSLArrays(int[] S, int sstart, int m, int[] L, int lstart, int n){
    	//int m = S.length, n = L.length;
    	assert(n!=0); //n==0 would result both m and n is 0
    	if(m==0) {
    		System.out.print("test:m=0");
    		return n % 2 == 0 ? (L[n/2-1] + L[n/2])/2.0 : L[n/2];
    	}
    	if(m==1){
    		if(n==1){ // 1-1
    			System.out.print("test");
    			return (S[sstart]+L[lstart])/2.0;
    		}else if(n % 2 == 0){ // 1-2
    			System.out.print("test");
    			return medianOfThree(S[sstart],L[n/2-1],L[n/2]);
    		}else{
    			System.out.print("test");
    			return (L[n/2] + medianOfThree(S[sstart], L[n/2-1], L[n/2+1]))/2.0;
    		}
    	}
    	if(m==2){
    		if(n==2){
    			System.out.print("test");
    			return medianOfFour(S[sstart],S[sstart+1],L[lstart],L[lstart+1]);
    		}else if (n%2==0){
    			System.out.print("test");
    			return medianOfFour(max(S[sstart+1],L[n/2-2]),L[n/2-1],L[n/2],min(S[sstart],L[n/2+1]) );
    		}else{
    			System.out.println("testXX");
    			System.out.println(S[sstart+1]);
    			System.out.println(L[n/2-1]);
    			System.out.println(L[n/2]);
    			System.out.println(S[sstart]);
    			System.out.println(L[n/2+1]);
    			return medianOfThree(max(S[sstart+1],L[n/2-1]),L[n/2],min(S[sstart],L[n/2+1]) );
    			
    		}
    	}
    	        
    	int i=m/2,j=n/2;
    	int k;
    	if(S[i]<=L[j]){
    		k=i;
    		if( m % 2==0 && n%2==0){--k;}
    			return findMedianSortedSLArrays(S,sstart+k, m-k,L,lstart, n-k);
    	}else{
    	            //cause m<=n
    	            //when m and n is even , n-i-1=m/2-1, j=n/2， j-1=n/2-1
    	            // n-i-1 <= j-1,
    		k=m-i-1;
    		//TeasingUtil.p
    		//System.out.println("resullt" + sstart + "," + m-k + "," + k + "," + n-k);
    		System.out.println(sstart);
    		System.out.println(m-k);
    		System.out.println(k);
    		System.out.println(n-k);
    	    return findMedianSortedSLArrays(S,sstart, m-k,L, lstart+k ,n-k);
    	}
}

    public int max (int a, int b){
    	return a > b ? a : b;
    }
    
    public int min (int a, int b){
    	return a < b ? a : b;
    }
    public double medianOfThree(int a, int b, int c){
    	int maxI=max(max(a,b),c);
    	int minI=min(min(a,b),c);
    	return (a+b+c-maxI-minI);
	}
    	        
    public double medianOfFour(int a, int b, int c, int d){
    	int maxI=max(max(max(a,b),c),d);
    	int minI=min(min(min(a,b),c),d);
    	return (a+b+c+d-maxI-minI)/2.0;
	}

    public double findMedianSortedArrays(int A[], int B[]) {
    	if (A==null || B == null) return Double.MIN_VALUE;
    	if (A.length < 1) return calMedian(B,0,B.length-1);
    	if (B.length < 1) return calMedian(A,0,A.length-1);
    	    	
    	int sA = 0, eA = A.length-1, sB = 0, eB = B.length-1;
    	while(eA-sA > 1 || eB-sB > 1){
    		
    		if ((eA-sA == 1 && eB-sB == 2) || (eA-sA == 2 && eB-sB == 1))
    		{
    			int[] C = new int[5];
    			int indexA =sA, indexB = sB;
    			for(int j = 0; j<5;j++){
    				
    				if (indexA > eA)
    					C[j] = B[indexB++];
    				else if (indexB > eB)
    					C[j] = A[indexA++];
    				else if(A[indexA]<=B[indexB]){
    					C[j] = A[indexA++];
    				}
    				else{
    					C[j] = B[indexB++];
    				}
    			}
    			TeasingUtil.printList(C);
    			return C[2];
    		}	
    		
    		double mA = calMedian(A,sA,eA);
    		double mB = calMedian(B,sB,eB);
    		if (mA == mB) return mA;
    		else if (mA > mB) {
    			eA = (eA-sA+1) % 2 == 0 ? (sA+eA)/2+1:(sA+eA)/2;
    			sB = (sB + eB) / 2;
    		}
    		else{
    			sA = (sA + eA) / 2;
    			eB = (eB - sB + 1) % 2 == 0 ? (sB + eB)/2 + 1 : (sB + eB)/2;
    		}

 
    		
        	System.out.println("mA:" + mA);        	
        	System.out.println("mB:" + mB);
        	System.out.println("sA:" + sA);        	
        	System.out.println("eA:" + eA);
        	System.out.println("sB:" + sB);        	
        	System.out.println("eB:" + eB);
        	
        	System.out.println("-----");        	
        	
    		
    	}
    	    	
    	int s = A[sA] >= B[sB] ? A[sA] : B[sB];
    	int e = A[eA] <= B[eB] ? A[eA] : B[eB];
    	
    	
    	System.out.println("s:" + s);
    	System.out.println("e:" + e);
    	
    	return (double)(s+e)/2;
    }	
    
    public double calMedian(int[] A, int start, int end){
    	if (A==null || start > end) return Double.MIN_VALUE;
    	int len = A.length;
    	if (len % 2 == 0)
    		return ((double)A[(start+end)/2]+(double)A[(start+end)/2+1])/2;
    	else
    		return (double)A[(start+end)/2];
    }
    
    public void sortColorsForLeetCode(int[] A) {
    	int lowIndex = -1;
    	int highIndex = A.length;
    	for (int i=0;i<highIndex;){
    		if (A[i] < 1){
    			int tmp = A[i];
    			A[i] = A[++lowIndex];
    			A[lowIndex] = tmp;
    			++i;
    		}else if (A[i] >= 2){
    			int tmp = A[i];
    			A[i] = A[--highIndex];
    			A[highIndex] = tmp;
    		}else
    			++i;
    	}
    }
	
    public void sortAnyColors(int[] A, int n) {
        int[] colorIndex = new int[n];
        for (int i = 0; i<n; i++) colorIndex[i] = 0;
        for(int i = 0; i < A.length; i++)
        {
            int color = A[i];
            for (int j = n-1; j >= color; j--)
                A[colorIndex[j]++] = j;
        }
    }
    
    public void threeWayPartition(int[] data, int mid, int high){
    	int lowIndex = -1;
    	int highIndex = data.length;
    	for (int i=0;i<highIndex;){
    		if (data[i] < mid){
    			int tmp = data[i];
    			data[i] = data[++lowIndex];
    			data[lowIndex] = tmp;
    			++i;
    		}else if (data[i] >= high){
    			int tmp = data[i];
    			data[i] = data[--highIndex];
    			data[highIndex] = tmp;
    		}else
    			++i;
    	}
    }

    
    public ListNode mergeKLists3(List<ListNode> lists) {
        if (lists == null) return null;
        if(lists.isEmpty()) return null;
        if(lists.size() == 1) return lists.get(0);
        int k = lists.size();
        int log = (int)(Math.log(k)/Math.log(2));
        log = log < Math.log(k)/Math.log(2)? log+1:log; // take ceiling
        for(int i = 1; i <= log; i++){
            for(int j = 0; j < lists.size(); j=j+(int)Math.pow(2,i)){
                int offset = j+(int)Math.pow(2,i-1);
                lists.set(j, mergeTwoLists(lists.get(j), (offset >= lists.size()? null : lists.get(offset))));
            }
        }
        return lists.get(0);    	
    }
	
    public ListNode mergeKLists2(List<ListNode> lists) {
        if (lists == null) return null;
        if (lists.size() == 1) return lists.get(0);
        while(lists.size() > 1){
        	ListNode l1 = lists.get(0);
        	ListNode l2 = lists.get(1);
        	ListNode l = this.mergeTwoLists(l1,l2);
        	lists.remove(l1);
        	lists.remove(l2);
        	lists.add(l);
        }       
        return lists.get(0);
    }

	
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null) return null;
        if (lists.size() == 1) return lists.get(0);
        
        ListNode fakehead = new ListNode(0);
        ListNode cur = fakehead, MIN;
        while((MIN = findMIN(lists))!=null){
        	cur.next = MIN;
        	cur = cur.next;
        	if (MIN.next != null)
        		lists.add(MIN.next);
        	lists.remove(MIN);
        }
        cur.next = null;
        return fakehead.next;
    }

    public ListNode findMIN(List<ListNode> lists){
    	ListNode min = null;
    	for (ListNode head : lists){
    		if (head == null)
    			continue;
    		if (min == null)
    			min = head;
    		else if (min.val >= head.val)
    			min = head;
    	}
    	return min;
    }
    public boolean searchII(int[] A, int target) {
    	if (A == null) return false;
    	int start = 0, end = A.length -1;
    	while(start <= end){
    		int mid = (start+end)/2;
    		if (target == A[mid])
    			return true;
    		boolean duplicated = false;
    		while(start<=mid && A[start]==A[mid]){
    			start++;
    			duplicated = true;
    		}
    		while(end >= mid && A[mid] == A[end]){
    			end--;
    			duplicated = true;
    		}

    		if (duplicated)
    			continue;
    		    		
    		if (A[start] < A[mid]){
    				if (target >= A[start] && target < A[mid])
    					end = mid - 1;
    				else
    					start = mid + 1;
    		} else if (target > A[mid] && target <= A[end])
    			start = mid + 1;
    		else
    			end = mid - 1;
    	}
    	return false;
    }
	
    public int search(int[] A, int target) {
    	if (A == null) return -1;
    	int start = 0, end = A.length -1;
    	while(start <= end){
    		int mid = (start+end)/2;
    		if (target == A[mid])
    			return mid;
    		else if (A[start] <= A[mid]){
    				/*
    				if (target > A[mid])
    					start = mid + 1; 
    				else if (target >= A[start])
    					end = mid - 1;
    				else
    					start = mid + 1;
    				*/
    				if (target >= A[start] && target < A[mid])
    					end = mid - 1;
    				else
    					start = mid + 1;
    		} else if (target > A[mid] && target <= A[end])
    			start = mid + 1;
    		else
    			end = mid - 1;
    	}
    	return -1;
    }
	
    public ListNode deleteDuplicatesII2 (ListNode head) {
    	ListNode fakehead = new ListNode(0);
    	ListNode app = fakehead;
    	while(head != null){
    		int count = 1;
    		while (head.next!=null && head.val == head.next.val){
    			++count;
    			head = head.next;
    		}
    		if (count == 1){
    			app.next = head;
    			app = app.next;
    		}
    		head = head.next;
    	}
    	app.next = null;
    	return fakehead.next;
    }
	
    public ListNode deleteDuplicatesII (ListNode head) {
        if (head == null || head.next == null) return head;
        boolean headdup = false;
        while ( head.next != null && head.val == head.next.val){
        	headdup = true;
        	head = head.next;
        }
        
        if (head.next == null)
        	return headdup ? null : head;
        
        ListNode pre_cur = head, cur = head.next;
        
        while(cur != null){
        	boolean dup = false;
        	while(cur.next != null && cur.val == cur.next.val){
        		dup = true;
        		cur.next = cur.next.next;
        	}
        	pre_cur = dup ? pre_cur : cur;
        	pre_cur.next = cur.next;
        	cur = cur.next;
        }
        
        return headdup ? head.next : head;
    }
	
    public ListNode deleteDuplicates2(ListNode head) {
    	ListNode cur = head;
    	while (cur != null){
    		while (cur.next!=null && cur.val == cur.next.val)
    			cur.next = cur.next.next;
    		cur = cur.next;
    	}
    	return head;
    }
 
	public ListNode deleteDuplicates(ListNode head) {
    	if (head == null || head.next == null) return head;
    	
    	int pre_val = head.val; int cur_val;
    	ListNode pre_cur = head, cur = head.next;
    	
    	while(cur != null){
    		cur_val = cur.val;
    		if (pre_val == cur_val)
    			pre_cur.next = cur.next;
    		else{
    			pre_val = cur_val;
    			pre_cur = pre_cur.next;
    		}
    		cur = cur.next;
    	}
    	return head;
    }
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	ListNode cur1 = l1, cur2 = l2;
    	ListNode head;
    	if (cur1.val <= cur2.val){
    		head = cur1;
    		cur1 = cur1.next;
    	}else{
    		head = cur2;
    		cur2 = cur2.next;
    	}
    	ListNode cur = head;
    	
    	while(cur1 != null && cur2 != null){
    		if (cur1.val <= cur2.val){
    			cur.next = cur1;
    			cur1 = cur1.next;
    		}else{
    			cur.next = cur2;
    			cur2 = cur2.next;
    		}
    		cur = cur.next;
    	}
    	if (cur1 != null) cur.next = cur1;
    	if (cur2 != null) cur.next = cur2;
    	return head;
    }
	
    public void merge(int A[], int m, int B[], int n) {
    	if (A == null || B == null) return;
    	int i = m - 1;
    	int j = n - 1;
    	int k = m + n - 1;
    	
    	while (i>=0 && j >=0){
    		if (A[i] > B [j]){
    			A[k--] = A[i--];  			
    		}else
    			A[k--] = B[j--];
    	}
    	while (j >= 0){
    		A[k--] = B[j--];
    	}
    }
	
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head.next; ListNode pre_cur = head;
        
        while (cur != null){
        	ListNode checked = head; ListNode pre_checked = head;
        	int key = cur.val;
    		boolean inserted = false;
 
    		boolean skip = false;
    		if (cur.val >= pre_cur.val){
        		pre_cur = cur;
        		cur = cur.next;
        		skip = true;
    		}
    		if (skip)
    			continue;
    		
    		while (checked != cur){
        		inserted = false;
        		if ( key < checked.val){
        			inserted = true;
    				pre_cur.next = cur.next;
    				cur.next = checked;
        			if (checked == pre_checked)// head
        				head = cur;
        			else
        				pre_checked.next = cur;
        		}
        		if (inserted) break;
        		pre_checked = checked;
        		checked = checked.next;
        	}
    		if (inserted)
        		cur = pre_cur.next;
        	else{
        		pre_cur = cur;
        		cur = cur.next;
        	}
        }
        return head;
    }	
	
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode end = head;
        while(end.next != null)
        	end = end.next;
        qsortListNode(head,end);
        return head;
    }
    
    public ListNode sortList2(ListNode head){
    	return quicksort(head, null);
    }
    
    
    public ListNode quicksort(ListNode head, ListNode tail){
    	if (head == tail) return head;

    	ListNode pivot = randomPick2(head, tail);
    	exchangeListNodeVal(head, pivot);
    	
    	ListNode left = head;
    	ListNode right = head;
    	ListNode next = null, cur = null;
    	
    	boolean equal = true;
    	
    	for (cur = head.next; cur != tail; cur = next){
    		next = cur.next;
    		if (cur.val != head.val)
    			equal = false;
    		if (cur.val < head.val){
    			cur.next = left;
    			left = cur;
    		}else{
    			right.next = cur;
    			right = cur;
    		}
    	}
    	if (equal)
    		return head;
    	right.next = tail;
    	head.next = quicksort(head.next, tail);
    	return quicksort(left,head);
    }
	
    public void qsortListNode(ListNode start, ListNode end){
    	Duplicated duplicated = new Duplicated (false);
    	if (start != end){
    		ListNode q = partition(start, end, duplicated);
    		if (duplicated.val)    			
    			return;
    		if (q != start){
    			ListNode pre_q = start;
    			while (pre_q.next != q)
    				pre_q = pre_q.next;
    			qsortListNode(start, pre_q);
    		}
    		if (q != end)
    			qsortListNode(q.next,end);
    	}
    }
    
    
	public ListNode partition(ListNode start, ListNode end, Duplicated duplicated){
		ListNode mark = null;
		ListNode randomnode = randomPick(start,end);		
		exchangeListNodeVal(randomnode, end);
		int pivot = end.val;
		ListNode cur = start;
		boolean equal = true;
		
		while (cur != end){
			if (cur.val != pivot)
				equal = false;
			if (cur.val <= pivot){
				mark = mark == null ? start : mark.next;
				exchangeListNodeVal(mark, cur);
			}
			cur = cur.next;
		}
		
		if (mark == null){
			exchangeListNodeVal(start, end);
			return start;
		}
		else{
			exchangeListNodeVal(mark.next, end);
			if (equal)
				duplicated.val = true;
			return mark.next;
		}
	}

	private ListNode randomPick2(ListNode start, ListNode end){
		//ListNode[] result = new ListNode[2];
		if (start == end) return start;
		ListNode rnode = start;
		ListNode cur = start;
		Random rand = new Random();
		double max = rand.nextDouble();
		
		while (cur.next != end){
			cur = cur.next;
			if (cur != null){
				double cur_rand = rand.nextDouble();
				rnode = max > cur_rand ? rnode : cur;
				max = max > cur_rand ? max : cur_rand;
			}
		};
		return rnode;
	}
	
	private ListNode randomPick(ListNode start, ListNode end){
		if (start == end) return start;
		ListNode rnode = start;
		ListNode cur = start;
		Random rand = new Random();
		double max = rand.nextDouble();
		do{
			cur = cur.next;
			double cur_rand = rand.nextDouble();
			rnode = max > cur_rand ? rnode : cur;
			max = max > cur_rand ? max : cur_rand;
		}while (cur != end);

		return rnode;
	}
	
	private void exchangeListNodeVal(ListNode A, ListNode B){
		if (A == null || B == null) return;
		int temp = A.val; A.val = B.val; B.val = temp;
	}
	
	
	public List<List<Integer>> threeSum2(int[] num) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		Arrays.sort(num);
	   	List<List<Integer>> llll = new ArrayList<List<Integer>>();
		if (num == null) return llll;
	   	
		// Keep every item in mind
	   	for (int i = 0; i< num.length; i++)
	   		hm.put(num[i], i);

	   	for (int i = 0; i<num.length; i++){
	        if (i > 0 && num[i] == num[i-1])
	        	// Skip duplicated
	        	continue;
	        for (int j = i+1; j<num.length; j++){
	            if (j > i+1 && num[j] == num[j-1])
	            // Again, skip duplicated
	            	continue;

	            if (hm.containsKey(0-num[i] - num[j])){
	                if (hm.get(0-num[i]-num[j]) > j){
	                    // Found a match. > j garuantees no duplicated triplets will be generated.
	                    ArrayList<Integer> ll = new ArrayList<Integer>();
	                    ll.add(num[i]);
	                    ll.add(num[j]);
	                    ll.add(0-num[i] - num[j]);
	                    llll.add(ll);
	                }
	            }
	        }
	    }
	    return llll;
	}
	
    public List<List<Integer>> threeSum(int[] num) {
    	if (num == null) return null;
    	    	
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	ArrayList<Integer> triplet;
    	    	
    	int posIndex = -1, zeroIndex = -1, negIndex = -1;
    	int posNum = 0, zeroNum = 0, negNum = 0;
    	
    	mergeSort(num,0,num.length-1);
    	
    	TeasingUtil.printList(num);
    	
    	for (int i = 0; i<num.length; ++i){
    		if (num[i]<0){
    			++negNum;
    			negIndex = (negIndex == -1 ? i : negIndex);
    		}
    		if (num[i] == 0){
    			++zeroNum;
    			zeroIndex = (zeroIndex == -1 ? i : zeroIndex);
    		}
    		if (num[i] > 0){
    			++posNum;
    			posIndex = (posIndex == -1 ? i : posIndex);;
    		}
    	}
    	
    	// If there are more than three 0s
    	if (zeroNum >= 3){
    		triplet = new ArrayList<Integer>();
    		triplet.add(0);
    		triplet.add(0);
    		triplet.add(0);
    		result.add(triplet);
    		
    		for(Integer t:triplet)
    			System.out.print(t.intValue() + ",");
    		System.out.println();
    			
    	}
    	
    	if (negNum == 0 || posNum == 0)
    		return result;


    	// TODO convert it to a function
    	
    	HashMap<Integer, Integer> checkDup = new HashMap<Integer, Integer>();
    	
    	// To ensure no duplicated checking numbers used twice
    	HashMap<Integer,Integer> checkingNum = new HashMap<Integer, Integer>();
    	HashMap<Integer,Integer> checkedNum = new HashMap<Integer, Integer>();
    	
    	// Seek triplets with 1 negative and 2 positive
		//int startIndex = posIndex; 
		for (int i=0; i<negNum; ++i){

			Integer checking = checkingNum.get(num[i]);
			
			if (checking == null)
				checkingNum.put(num[i], i);
			else
				// Duplicated checking number
				continue; 
			
			
			//TODO 
			int target = num[i] * (-1); 
			// Scan non-negative 
			checkedNum.clear();
			checkDup.clear();
			for (int j = posIndex; j < num.length; ++j ){
				Integer checkedIndex = checkedNum.get(num[j]);
				
				if (checkedIndex == null)
					checkedNum.put(num[j], j);
				
				checkedIndex = checkedNum.get(target-num[j]);
				
				if (checkedIndex != null && checkDup.get(target-num[j]) == null && checkedIndex.intValue() != j){
					triplet = new ArrayList<Integer>();
		    		triplet.add(-target);
		    		triplet.add(target-num[j]);
		    		triplet.add(num[j]);
		    		result.add(triplet);
		    		
		    		for(Integer t:triplet)
		    			System.out.print(t.intValue() + ",");
		    		System.out.println();
		    		
					checkDup.put(target-num[j], checkedIndex);
				}
					
			}
		}

		
    	// Seek triplets with 1 positive and 2 negative
		checkingNum.clear();
		//int endIndex = zeroNum > 0 ? zeroIndex : posIndex-1; 
		for (int i=posIndex; i<num.length; ++i){
			Integer checking = checkingNum.get(num[i]);
			if (checking == null)
				checkingNum.put(num[i], i);
			else
				// Duplicated checking number
				continue; 			
			
			//TODO 
			int target = num[i] * -1; 
			// Scan negative 
			checkedNum.clear();
			checkDup.clear();
			for (int j = 0; j < negNum; ++j ){
				Integer checkedIndex = checkedNum.get(num[j]);
				
				if (checkedIndex == null)
					checkedNum.put(num[j], j);
				
				checkedIndex = checkedNum.get(target - num[j]);
				
				if (checkedIndex != null && checkDup.get(target-num[j]) == null && checkedIndex.intValue() != j){
					triplet = new ArrayList<Integer>();
		    		
		    		triplet.add(target-num[j]);
		    		triplet.add(num[j]);
		    		triplet.add(-target);
		    		result.add(triplet);
		    		
		    		for(Integer t:triplet)
		    			System.out.print(t.intValue() + ",");
		    		System.out.println();
					checkDup.put(target-num[j], checkedIndex);
				}
					
			}
		}
		
		if (zeroNum == 0)
			return result;
		
	   	// Seek triplets with 1 zero, 1 positive and 1 negative
		checkingNum.clear();
		for (int i=posIndex; i<num.length; ++i){
			Integer checking = checkingNum.get(num[i]);
			if (checking == null)
				checkingNum.put(num[i], i);
			else
				// Duplicated checking number
			continue; 			
				
			//TODO 
			int target = num[i]; 
			// Scan negative 
			checkedNum.clear();
			checkDup.clear();
			for (int j = 0; j < negNum; ++j ){
				Integer checkedIndex = checkedNum.get(num[j]);
					
				if (checkedIndex == null)
					checkedNum.put(num[j], j);
					
				checkedIndex = checkedNum.get(0 - target );
					
				if (checkedIndex != null && checkDup.get(0-target) == null){
						triplet = new ArrayList<Integer>();
			    		
						triplet.add(-target);
						triplet.add(0);
			    		triplet.add(target);
			    		
			    		result.add(triplet);
			    		
			    		for(Integer t:triplet)
			    			System.out.print(t.intValue() + ",");
			    		System.out.println();
			    		
			    		
						checkDup.put(0 - target, checkedIndex);
					}
						
				}
			}
		
    	return result;
    }

    /**
     * Quick Sort
     */
    
    public void quicksort(int[] A, int s, int e){
        if (s < e){
            int q = partition(A, s ,e);
            quicksort(A,s,q-1);
            quicksort(A,q+1,e);
        }
    }
    
    public int partition(int[] A, int s, int e){
        int equal = 0;
        int x = A[e];
        int i = s - 1;
        int temp;
        for(int j=s; j<e;++j){
            if (A[j] == x)
                ++equal;
            if (A[j] <= x){
                ++i;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        temp = A[i+1];
        A[i+1] = A[e];
        A[e] = temp;
        if(equal == (e-s))
            return i+1-equal/2;
        else
            return i+1;
    }
    
    /**
     * Reverse a list    
     */
    public void reverseList(int[] num){
        if (num == null || num.length <=1)
            return;
        int len = num.length;
        for(int i=0;i<len/2;++i){
            int temp = num[i];
            num[i] = num[len-1-i];
            num[len-1-i] = temp;
        }
        return;
    }
    
    public void reverseList(int[] num, int start, int end){
        if (num == null || num.length <=1 || start >= end)
            return;
        
        int len = end-start+1;
        for(int i=0;i<len/2; i++){
        	int temp = num[start+i];
        	num[start+i] = num[end-i];
        	num[end-i] = temp;
        }
    	
    }
    
    /**
     * mergeSort
     * @param A
     * @param start
     * @param end
     */
    
	public void mergeSort(int[] A, int start, int end)
	{
		int mid;
		if (start < end)
		{
			mid = (start + end) / 2;
			mergeSort(A, start, mid);
			mergeSort(A, mid+1, end);
			merge(A, start, mid, end);
		}
	}
	
	private void merge(int[] A, int start, int mid, int end)
	{
		int n1 = mid-start+1;
		int n2 = end - mid;
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for (int i = 0; i < n1; i++)
			L[i] = A[start+i];
		for (int i=0; i< n2; i++)
			R[i] = A[mid+i+1];
		
		int i = 0;
		int j = 0;
		
		for (int k = start; k < end; k++)
		{
			if (L[i] <= R[j])
			{
				A[k] = L[i];
				if (i == n1-1){
					copy(A, k+1, R, j);
					break;
				}
				++i;
			}
			else
			{
				A[k] = R[j];
				if (j == n2 - 1){
					copy(A, k+1, L, i);
					break;
				}
				++j;
			}
		}
	}
	
	private void copy(int[] M, int m, int[] N, int n){
		int size = N.length;
		for (int i = 0; i< size -n; i++)
			M[m+i] = N[n+i];
	}
	
    public int romanToInt2(String s) {
    	if (s==null) return 0;
    	
    	int result = 0;
    	
    	for (int i=s.length()-1; i >=0; --i){
    		char c = s.charAt(i);
    		
    		switch(c){
    		case 'M': 
    			result += 1000;
    			break;
    		case 'D':
    			result += 500;
    			break;
    		case 'C':
    			result += 100 * (result > 500 ? -1 : 1);
    			break;
    		case 'L':
    			result += 50;
    		case 'X':
    			result += 10 * (result > 50 ? -1 : 1);
    			break;
    		case 'V':
    			result += 5;
    			break;
    		case 'I':
    			result += (result > 5 ? -1 : 1);
    			break;
    		default:
    			return 0;
    		}
    	}
    	
    	return result;
    }
	
    public int romanToInt(String s) {
        if (s==null) return 0;
        
        int result = 0;
        
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        
        hm.put('M', 1000);
        hm.put('D', 500);
        hm.put('C', 100);
        hm.put('L', 50);
        hm.put('X', 10);
        hm.put('V', 5);
        hm.put('I', 1);
        
        for (int i=0; i<s.length(); ++i){
        	char c = s.charAt(i);
        	Integer value = hm.get(Character.valueOf(c));
        	if (value != null)
        		result += value.intValue();
        	else
        		return 0;        			
        }
        
        if (s.indexOf("CM") != -1)
        	result -= 200;
    
        if (s.indexOf("CD") != -1)
        	result -= 200;
        
        if (s.indexOf("XC") != -1)
        	result -= 20;
        
        if (s.indexOf("XL") != -1)
        	result -= 20;
        
        if (s.indexOf("IX") != -1)
        	result -= 2;
        
        if (s.indexOf("IV") != -1)
        	result -= 2;
        
        return result;
    }
	
	
    public String intToRoman(int num) {
    	StringBuffer s = new StringBuffer("");
    	int cur_digit = num/1000; num = num % 1000;
    	for (int i=1; i<=cur_digit; ++i)
    		s.append("M");
    	
    	cur_digit = num/100; num = num % 100;
    	convertDigit(cur_digit,"C", "M", "D",s);
    	
    	cur_digit = num/10; num = num % 10;
    	convertDigit(cur_digit, "X", "C", "L", s);
    	convertDigit(num, "I", "X", "V", s);
    	
    	return s.toString();
    }
	
	private void convertDigit(int digit, String unit, String highunit, String midunit, StringBuffer s){
		switch(digit){
		case 9:
			s.append(unit);
			s.append(highunit);
			break;
		case 8: case 7: case 6: case 5:
			s.append(midunit);
			for (int i=1; i<=digit-5; ++i)
				s.append(unit);
			break;
		case 4:
			s.append(unit);
			s.append(midunit);
			break;
		case 3: case 2: case 1:
			for (int i=1; i<=digit; ++i)
				s.append(unit);
			break;
		default:
			break;
		}
		return;
	}
	
    public boolean isValid(String s) {
        if (s == null) return false;
        boolean isvalid = true;
        Stack<String> v = new Stack<String>();
        
        for (int i = 0; i < s.length(); ++i){
        	char c = s.charAt(i);
        	
        	switch (c){
        		case '(':
        			v.push("P");
        			break;
        		case ')':
        			if (v.size() == 0 || v.pop() != "P")
        				isvalid = false;
        			break;
        		case '[':
        			v.push("SB");
        			break;
        		case ']':
        			if (v.size() == 0 || v.pop() != "SB")
        				isvalid = false;
        			break;
        		case '{':
        			v.push("CB");
        			break;
        		case '}':
        			if (v.size() == 0 || v.pop() != "CB")
        				isvalid = false;
        			break;
        		default: isvalid = false;
        	}
        }
        
        if (v.size() == 0)
        	return isvalid;
        else
        	return false;
    }

	public List<String> generateParenthesis2(int n){
	    ArrayList<String> list = new ArrayList<String>();
	    Stack<String> stack = new Stack<String>();
	    Stack<Integer> validationStack = new Stack<Integer>();
	    stack.push("(");
	    validationStack.push(0);
	    while(stack.size() != 0)
	    {
	        String s = stack.pop();
	        int v = validationStack.pop();
	        if(s.length() == n * 2)
	        {
	            list.add(s);
	            continue;
	        }
	        if(s.length() - v < n)
	        {
	            stack.push(s + "(");
	            validationStack.push(v);
	        }
	        if(2 * v < s.length())
	        {
	            stack.push(s + ")");
	            validationStack.push(v+1);
	        }
	    }
	    return list;		
	}
    
	public List<String> generateParenthesis(int n){
		List<String> result = new ArrayList<String>();
		genBrackets("",0,0,n,result);
		return result;
		
	}
	
	
    public void genBrackets(String output, int open, int close, int pairs, List<String> result)
    {
        if((open==pairs)&&(close==pairs))
        {
            
        	//System.out.println(output);
        	result.add(output);
        }
        else
        {
            if(open<pairs)
            	genBrackets(output + "(", open+1, close, pairs, result);
            if(close<open)
            	genBrackets(output + ")", open, close+1, pairs, result);
        }
    }
	
    public String longestCommonPrefix(String[] strs) {
    	if (strs == null || strs.length == 0) return "";
    	
    	for (int prefixLen = 0; prefixLen < strs[0].length(); ++prefixLen){
    		char c = strs[0].charAt(prefixLen);
    		for (int i=1; i<strs.length; ++i){
    			if (prefixLen >= strs[i].length() || strs[i].charAt(prefixLen) != c)
    				return strs[i].substring(0,prefixLen);
    		}
    	}
    	
    	return strs[0];
    }
	

    public int divide2(int dividend, int divisor) {
    
    	int ret = 0;
    	int sign = ((dividend > 0 && divisor >0) || (dividend < 0 && divisor < 0)) ? 1 : -1;

        long pdividend = Math.abs((long)dividend);
        long pdivisor = Math.abs((long)divisor);

        // 13,2 
        long remainder=pdividend;
        while(remainder>=pdivisor)
        {
            int moves=-1;
            long tmp=pdivisor;
            while(tmp <= remainder) // 13 | 5
            {
                tmp = tmp<<1; //4,8,16 | 4, 8,
                moves++; //0,1,2 | 0, 1
            }
            if(moves >= 0)
            {
                ret += 1<<moves; // 4, 6
                remainder -= (tmp>>1); //5, 1
            }
        }

        return sign>0?ret:-ret;
        
    }
	
	
    public int divide(int dividend, int divisor) {
    	
    	if (divisor == 0)
    		return Integer.MIN_VALUE;
    	
    	int de = dividend;
    	int dr = divisor;
    	
    	boolean de_positive = true;
    	boolean dr_positive = true;
    	
    	if (de < 0 ) {
    		de = -de;
    		de_positive = false;
    	}
    	if (dr < 0) {
    		dr = -dr;
    		dr_positive = false;
    	}
    	
    	int quotient = 0;
    	
    	while (de >= dr ){
    		++quotient;
    		de -= dr;
    	}
    	
    	return ((de_positive && dr_positive) || (!de_positive && !dr_positive)) ? quotient : -quotient;
    }
	
    public String convert(String s, int nRows) {
        return null;
    }
	
    public boolean isPalindrome(int x) {
    	if (x<0) return false;
    	
    	long reverse = 0;
    	int mod, dx=x;
    	
    	do{
    		mod = dx % 10;
    		reverse = reverse * 10 + mod;
    		dx /= 10;
    	}while(dx>0);
    	
    	return ((long)x == reverse);
    }
	
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode c1 = l1;
        ListNode c2 = l2;
        ListNode sentinel = new ListNode(0);
        ListNode d = sentinel;
        int sum = 0;
        while (c1 != null || c2 != null) {
            sum /= 10;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            d.next = new ListNode(sum % 10);
            d = d.next;
        }
        if (sum / 10 == 1)
            d.next = new ListNode(1);
        return sentinel.next;		
	}

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	if (l1 == null) return l2;
    	if (l2 == null) return l1;
    	
    	int digit = 0;
    	int c = 0;
    	ListNode head = new ListNode(0);
    	ListNode tail = head;
    	ListNode cur1 = l1; ListNode cur2 = l2; ListNode cur;
    	
    	while (cur1 != null || cur2 != null){
    		if (cur1 != null && cur2 !=null){
    			digit = (cur1.val + cur2.val + c) % 10;
    			c = (cur1.val + cur2.val + c) / 10;
    			cur = new ListNode(digit);
    			tail.next = cur;
    			tail = cur;
    			cur1 = cur1.next;
    			cur2 = cur2.next;
    		}
    		else if (cur1 != null ){
       			digit = (cur1.val + c) % 10;
    			c = (cur1.val + c) / 10;
    			cur = new ListNode(digit);
    			tail.next = cur;
    			tail = cur;
    			cur1 = cur1.next;    			
    		}else{
       			digit = (cur2.val + c) % 10;
    			c = (cur2.val + c) / 10;
    			cur = new ListNode(digit);
    			tail.next = cur;
    			tail = cur;
    			cur2 = cur2.next;    			
    		}
    	}
    	
    	if (c>0){
			cur = new ListNode(c);
			tail.next = cur;
			tail = cur;
    	}
 
    	return head.next;
    }

	public ListNode reverseListNode(ListNode head){

        ListNode newhead = null;
        ListNode cur, next;
        for (cur = head; cur != null; cur = next){
            next = cur.next;
            cur.next = newhead;
            newhead = cur;
        }
		return newhead;
		/*
		if (head == null) return null;
		if (head.next == null) return head;
		
		ListNode nh = new ListNode(0);
		ListNode prev = head;
		ListNode cur = head.next;
		
		while(cur != null){
			prev.next = nh;
			nh = prev;
			if (nh == head)
				nh.next = null;
			prev = cur;
			cur = cur.next;
		}
		
		prev.next = nh;
		nh = prev;
		return nh;
		*/
	}
	
	public void reorderList(ListNode head) {
    
		if (head == null || head.next == null) return;

		ListNode fast = head;
		ListNode slow = head;
		while (fast != null && fast.next !=null){
			fast = fast.next.next;
			slow = slow.next;
		}
		
		ListNode rhead = slow.next;
		slow.next = null;
		rhead = reverseListNode(rhead);
		this.printListNode(rhead);
		ListNode cur = head;
		ListNode tmp;
		
		while (rhead != null){
			tmp = cur.next;
			cur.next = rhead;
			rhead = rhead.next;
			cur.next.next = tmp;
			cur = tmp;
		}
		
    }
	
	
	public void initKMP(String word, int[] T){
		int i = 0, j = -1;
		T[0] = j;
		
		while (i<word.length()){
			while (j >= 0 && word.charAt(i) != word.charAt(j))
				j = T[j];
			i++; j++;
			T[i] = j;
		}
			
	}
	
	/**
	 * The initialization of KMP is to compute the width of border of the substrings before each character.
	 * @param w
	 * @param T
	 */
	
	public void initKMP2(String w, int[] T){
		int pos = 2; // The current position we are computing in T
		
		// cnd is the width of the border before the current character in the string
		int cnd = 0; // the zero-based index in W of the next character of the current candidate substring
		
		
		// The first few values are fixed but different from what the algorithm might suggest
		
		if (T.length >= 2)
		{
			T[0] = -1; T[1] = 0;
		} else if (T.length == 1)
			T[0] = -1;
		
		while (pos < T.length){
			
			// first case: the substring continues
			if (w.charAt(pos-1) == w.charAt(cnd)){
				++cnd;
				T[pos]=cnd;
				++pos;
			}
			// Second case: the substring or the border does not expand any more
			// Then fall back, i.e. seek the second longest border's width
			// If cnd is greater than 0, it means it possible to find a "shorter" border
			else if (cnd > 0)
				// T[cnd] indicates the width of the border before the current character.
				// T[cnd] actually indicates the next longest border
				// Then we check charAt(T[cnd]), 
				// i.e. to check if the current inspected character can be used to expand the shorter "sub" border.
				
				cnd = T[cnd];
				//cnd = 0;
			else{
				// If cnd <=0, there is no continuing substring, nor expandable border.
				 
				T[pos] = 0;
				++pos;
			}
		}
	}
	
	public String strStr1(String haystack, String needle){
		
    	if (haystack == null || needle == null)
    		return null;

    	if (haystack.length() < needle.length())
    		return null;    	
    	
    	if (needle == "")
    		return haystack;
    	
		int[] T = new int[needle.length()];
		int m = 0; // The beginning of the current match in haystack
		int i = 0; // The position of the current character in needle
		int s_len = haystack.length();
		int w_len = needle.length();
		
		// Initialize KMP table
		initKMP2(needle, T);
		
		while ( m + i < s_len){
			if (needle.charAt(i) == haystack.charAt(m+i)){
				if (i == w_len -1)
					return haystack.substring(m);
				++i;
			}
			else
				if (T[i] > -1){
					m = m + i - T[i];
					i = T[i];
				}
				else{
					i = 0;
					++m;
				}
		}
		
		return null;
	}
	
    public String strStr(String haystack, String needle) {
    	if (haystack == null || needle == null)
    		return null;
    	/*
    	if (haystack.length() < needle.length())
    		return null;
    	*/
    	
    	if (needle == "")
    		return haystack;
    	
    	boolean flag = false;
    	int i,j;
    	for (i = 0; i < haystack.length(); ++i){
    		    		
    		if (haystack.length() -i < needle.length())
    			break;
    		
    		if (haystack.charAt(i) == needle.charAt(0)){
    			for (j = 1 ; j < needle.length(); ++j)
    				if (haystack.charAt(j + i) != needle.charAt(j))
    					break;
    			if (j == needle.length()){
    				flag = true;
    			}
    		}
    		if (flag)
    			break;
    	}

    	if (!flag)
    		return null;
    	else 
    		return haystack.substring(i);
    }

    public int removeDuplicatesII2(int[] A) {
    	if (A == null) return 0;
    	if (A.length <= 2) return A.length;   	
    	int len = 2, index = 2, alen = A.length;
    	while (index < alen){
    		if (A[index] != A[len-2]){
    			A[len++] = A[index];
    		}
    		index++;
    	}
    	return len;
    }

    
    public int removeDuplicatesII(int[] A) {
    	if (A == null || A.length == 0) return 0;
    	int index = 1, count = 1;
    	for (int i = 1 ; i < A.length; ++i){
    		if (A[i-1] == A[i]){
    			if(count++ < 2)
    				A[index++] = A[i];
    		}
    		else{
    			count = 1;
    			A[index++] = A[i]; 
    		}
    	}
    	return index;
    }
    
    public int removeDuplicates2(int[] A) {
    	if (A == null) return 0;
    	int count = 0;
    	for (int i = 0 ; i< A.length ; ++i){
    		if (count == 0 || A[count-1]!=A[i])
    			A[count++] = A[i];
    	}
    	return count;
    }
    
	
    public int removeDuplicates(int[] A) {
    	int len = A.length;
    	for (int i=1; i< len; ++i)
    		if (A[i-1] == A[i]){
    			for (int j=i; j<len-1; ++j)
    				A[j] = A[j+1];
    			--len;
    			--i;
    		}
    	return len;
    }
	
	public int removeElement(int[] A, int elem) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i=0; i<A.length; i++)
			if (A[i] != elem)
				stack.push(A[i]);
		
		A = new int[stack.size()];
		for (int i = 0; i<A.length; ++i)
			A[i] = stack.pop();
		
		return A.length;
	}

	/*
    public int removeElementOLD(int[] A, int elem) {
    	if (A==null) return 0;
    	int counter = A.length;
    	if (counter < 1) return 0;
    	int newlen = 0;
    	
    	for (int i=0; i<counter; ++i){
    		
    		if (A[i] == elem){
    			while(A[counter-1] == elem)
    				if (--counter < 1) break;
    			
    			if (counter != 0){
    				A[i] = A[counter-1];
    				++newlen;
    				--counter;
    			}
    		}
    		else
    			++newlen;
    	}
    	return newlen;
    }*/

	public int[] twoSumSilly(int[] numbers, int target) {
		int i=0;
		int j=0;
	    boolean flag = false;
	    for (i=0; i<numbers.length; i++){
	    	for (j=i+1;j<numbers.length; j++)
	    		if (numbers[i] + numbers[j] == target){
	    			flag = true;
	    			break;
	    		}
	    	if (flag)
	    		break;
	    }
	        
	    if (flag){
	    	int[] result = new int[2];
	        result[0] = i+1;
	        result[1] = j+1;
	        return result;
	    }else
	    	return null;
	}
	
	public int[] twoSumHashtable(int[] numbers, int target) {
		int result[] = new int[2];
		
		HashMap<Integer, Integer> numht = new HashMap<Integer, Integer>();
		
		for (int i=0; i<numbers.length; i++){
			Integer n = numht.get(numbers[i]);
			
			if (n == null)
				numht.put(numbers[i],i);
			
			n = numht.get(target - numbers[i]);
			
			if (n!=null && n < i){
				result[0] = n+1;
				result[1] = i+1;
			}
		}
		return result;
		
	}
	
    public int lengthOfLongestSubstringUGLY(String s) {
    	HashMap<Character, Integer> charhm = new HashMap<Character, Integer>();
    	if (s.length() == 0)
    		return 0;
    	int longest = 1;
    	int curlen = 0;
    	int start = 0;
    	for (int i = 0 ; i<s.length() ; ++i){
    		char curchar = s.charAt(i);
    		
    		if (!charhm.containsKey(curchar)){
    			++curlen;
    			charhm.put(curchar, i);
    		} else {
    			if (curlen > longest)
    				longest = curlen;
    			i = ++start;
    			curlen = 1;
    			charhm.clear();
    			charhm.put(s.charAt(start), i);
    		}
    	}
    	// If the longest non-repeating characters
    	if (curlen > longest)
    		longest = curlen;
    	return longest;
        
    }
	
    public int lengthOfLongestSubstring(String s) {
    	HashMap<Character, Integer> charhm = new HashMap<Character, Integer>();
    	if (s.length() < 2)
    		return s.length();
    	int maxlen = 1;
    	int d = 1;

    	charhm.put(s.charAt(0), 0);
    	
    	for (int i = 1 ; i<s.length() ; ++i){
    		char curchar = s.charAt(i);
  
    		if (!charhm.containsKey(curchar) || charhm.get(curchar) < i - d)
    			++d;
    		else{
    			d = i - charhm.get(curchar);
    		}
        	if (d > maxlen)
        		maxlen = d;

        	charhm.put(curchar, i);
    		
    	}
    	// If the longest non-repeating characters
    	return maxlen;
    }

 
    public int atoi(String str) {
    	int result = 0;
    	if (str==null) return 0;
    	if (str.length() < 1 ) return 0;
    	
    	int beginIndex = 0;
    	int curdigit = 0;
    	boolean negative = false;
    	
    	char c = str.charAt(beginIndex);
    	while ( c == ' ' || c == '\t' || c == '\r' || c == '\n'){
    		c = str.charAt(++beginIndex);   	    	
    	}
    	String s1 = str.substring(beginIndex);
    	if (s1.length() < 1 ) return 0;
    	
    	if (s1.charAt(0) == '-')
    		negative = true;
    	
    	if (s1.charAt(0) == '-' || s1.charAt(0) == '+')
    		s1 = s1.substring(1);
    	if (s1.length() < 1 ) return 0;
    	
    	for (int i = 0; i<s1.length(); ++i){
    		curdigit = s1.charAt(i) - 48;

    		if (curdigit < 0 || curdigit > 9)
    			break;
    		
    		if (!negative && i > 9)
    			return Integer.MAX_VALUE;
    		if (negative && (i > 9))
    			return Integer.MIN_VALUE;
    		
    		if (i==9 && result > Integer.MAX_VALUE / 10)
    			if (!negative)
    				return Integer.MIN_VALUE;
    			else
    				return Integer.MAX_VALUE;

    		if (i==9 && result == Integer.MAX_VALUE / 10)
    			if (!negative && curdigit > 7)
    				return Integer.MAX_VALUE;
    			else if (negative && curdigit > 8)
    				return Integer.MIN_VALUE;
    	
    		result = result*10 + curdigit;
    	}
    	
    	if (negative)
    		return -result;
    	else
    		return result;
    }
    
    public int reverse(int x) {
    	boolean positive = (x > 0 ? true : false);
    	int test = ( positive ? x : -x); 
    	
    	//if (test == 0) return 0;
    	int result = 0; int curdigit = 0;
    	
    	while (test > 0){
    		curdigit = test % 10;
    		if (result > Integer.MAX_VALUE / 10)
    			return (positive ? Integer.MAX_VALUE : Integer.MIN_VALUE);
    		
    		if (result == Integer.MAX_VALUE / 10){
    			if (positive && curdigit > 7)
    				return Integer.MAX_VALUE;
    			if (!positive && curdigit > 8)
    				return Integer.MIN_VALUE;
    		}
    		
    		result = result*10 + curdigit;
    		test /= 10;
    	}
    	
    	return (positive ? result : -result);
    	
    	
    }
    
    public void quicktest(){
    	int test = Integer.MAX_VALUE * 4 + 1932610871;
    	int test1 = test;
    	
    	System.out.println(test1);
    }
    
    public void printListNode (ListNode head){
    	System.out.print("Print ListNode:");
    	
    	if (head == null) {
    		System.out.println();
    		return;
    	}
    	ListNode n = head;
    	
    	while (n != null){
    		System.out.print(n.val + ",");
    		n = n.next;
    	}
    	System.out.println();
    }

	public ListNode genList(int[] data){
		if (data == null) return null;
		if (data.length == 0) return null;
		
		ListNode head = new ListNode(data[0]);
		ListNode n = head;
		
		for (int i=1; i<data.length; ++i){
			ListNode newnode = new ListNode(data[i]);
			n.next = newnode;
			n = newnode;
		}
		return head;
	}
	
	public ListNode seekEnd(ListNode ln){
		if (ln == null) return null;
		ListNode end = ln;
		while(end.next != null)
			end = end.next;
		return end;
	}

	/*
	private int getListNodeLen(ListNode start, ListNode end){
		if (start == null || end == null) return 0;
		int len = 1;
		ListNode ln = start;
		while(ln != end){
			++len;
			ln = ln.next;
		}
		return len;
	}

	private ListNode randomPick(ListNode start, ListNode end){
		int len = getListNodeLen(start, end);
				
		Random rand = new Random();
		int randi = rand.nextInt(len);
				
		ListNode ln = start;
		for (int j=0;j<randi; ++j)
			ln = ln.next;
		return ln;

	}
	*/

	   /*
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
       List<List<Integer>> result = new ArrayList<List<Integer>>();
       if (candidates == null || candidates.length == 0) return result;
       
       int curNum = candidates[0], curSum = 0, counter = 0;
       Stack<List<Integer>> tracker = new Stack<List<Integer>>();
       boolean move = false;
       
       while(counter < candidates.length){
    	   if (move){
    		   if (curNum == candidates[counter]){
        		   counter++;
    			   continue;
    		   }
    		   else{
    			   curNum = candidates[counter];
    			   move = false;
    		   }
    	   }
    	   
    	   if (curNum > target)
    		   break;
    	   else if (curNum == target){
    		   List<Integer> top = new ArrayList<Integer>();
    		   top.add(curNum);
    		   result.add(top);
    		   break;
    	   }
    	   
    	   if (tracker.isEmpty()){
    		   List<Integer> top = new ArrayList<Integer>();
    		   top.add(curNum);
    		   tracker.add(top);
    	   }

		   curSum = getCurSum(tracker);
    	   if (curSum + curNum < target){
    		   List<Integer> top = tracker.peek();
    		   List<Integer> newtop = new ArrayList<Integer>(top);
    		   newtop.add(curNum);
    		   tracker.add(newtop);
    	   } else if (curSum + curNum == target){
    		   List<Integer> top = tracker.pop();
    		   top.add(curNum);
    		   result.add(top);
    		   //curSum = getCurSum(tracker);
    	   } else{
    		   tracker.pop();
    		   counter++;
    		   move = true;
    	   }
       }
       
       return result;
    }*/
	
}
