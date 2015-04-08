package cn.arrowcoder.training;

import cn.arrow.brainteasing.ListNode;
import cn.arrow.brainteasing.TeasingUtil;

public class ArrowCodeTest {

	static public ArrowCode arrowcode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrowCodeTest.arrowcode = new ArrowCode();		
		ArrowCodeTest.test();
		
	}
	
	static public void test(){
		/**
		 *  Problem No. 1
		 *  Two Sum
		 *  /
		/*
		int[] numbers = TeasingUtil.genList(40, 20);
		int[] result = arrowcode.twoSum(numbers, 23);
		TeasingUtil.printList(numbers);
		if (result != null)
			TeasingUtil.printList(result);
		*/

	    /**
	     * Problem No. 2
	     * Add Two Numbers presented in LinkedList
	     */
		/*
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(9);
		TeasingUtil.printListNode(l1);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		//l2.next.next = new ListNode(4);
		TeasingUtil.printListNode(l2);
		
		ListNode sumlist = arrowcode.addTwoNumbers(l1, l2);
		TeasingUtil.printListNode(sumlist);
		*/
	    /**
	     * Problem No. 3
	     * Longest Substring Without Repeating Characters
	     */
		//String s = "bvbf90daceb3";
		//System.out.println(arrowcode.lengthOfLongestSubstring(s));
		
		/**
		 * Problme No.5
		 */
		//String s = "eabcddcbaf";
		//String s = "e";
		//System.out.println(arrowcode.longestPalindromeN(s));
		
		/**
		 * Problem No.6
		 */
		//String s = "PAYPALISHIRING";
		//System.out.println(arrowcode.convert2(s, 4));
		
		/**
		 * Problem No.7
		 */
		//2147483647 
		//int input = 1534236469;
		//int input = -8923;
		//System.out.println( 1000 << 22);
		//System.out.println(964632435 * 10 + 1);
		//System.out.println(arrowcode.reverse2(input));
		
		/**
		 * Problem No. 8
		 */
		//String str = "     -1";
		//System.out.println(arrowcode.atoi(str));
		
		/**
		 * Problem #9
		 * Palindrome Number 
		 */
		int x = 1;
		System.out.println(arrowcode.isPalindrome2(x));
		/**
		 * Problem No.189
		 * Rotate Array
		 */
		/*
		int[] test = TeasingUtil.genList(100, 90, 1);
		TeasingUtil.printList(test);
		arrowcode.rotate2(test, 7);
		TeasingUtil.printList(test);
		*/
	    /**
	     * Problem No. 190
	     * Reverse Bits
	     */
		//int test = 43261596;
		//System.out.println("n = " + test + " reversed to " + arrowcode.reverseBits(test));

	    /**
	     * Problem No. 191
	     * Number of 1 Bits
	     */

		//System.out.println("n = " + 7 + " with " + arrowcode.hammingWeight(7) + " 1s");
	}
}
