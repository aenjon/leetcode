package cn.arrowcoder.training;

import java.util.List;

import cn.arrow.brainteasing.ListNode;
import cn.arrow.brainteasing.TeasingUtil;

public class ArrowCodeTest {

	//public ArrowCode arrowcode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrowCodeTest.test();
		ArrowCodeTest.classictest();
		
	}
	
	static public void classictest(){
		ArrowClassic arrowclassic = new ArrowClassic();

		/**
		 * Classic #1: insert sorting
		 */
		int nums[] = TeasingUtil.genList(15, 100);
		TeasingUtil.printList(nums);
		arrowclassic.insertSort(nums);
		TeasingUtil.printList(nums);
	}
	
	static public void test(){
		ArrowCode arrowcode = new ArrowCode();		

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
		//int x = 1;
		//System.out.println(arrowcode.isPalindrome2(x));
		
		/**
		 * Problem #10
		 * Regular Expression Match
		 */
		/*
		String s = "aab";
		String p = "c*a*b";
		System.out.println(arrowcode.isMatch(s, p));
		 */
		
	    /**
	     * Problem #12 & #13
	     * Integer to Roman 
	     * Roman to Integer 
	     */
		/*
		int x = 1689;
		String rx = arrowcode.intToRoman(x);
		System.out.println(x + "-" + rx + "-" + arrowcode.romanToInt2(rx));
		*/
	    /**
	     * Problem #13
	     * Longest Common Prefix 
	     */
		//String[] strs= {"abcf", "abced", "abca"};
		//System.out.println(arrowcode.longestCommonPrefix(strs));
		
	    /**
	     * Problem #14 & #15
	     * 3Sum  
	     */
		/*
		int[] nums = {-1, 0, 1, 2, -1, -4};
		List<List<Integer>> ret = arrowcode.threeSum2(nums);
		TeasingUtil.printIntegerPowerSet(ret);
		*/
	    /**
	     * Problem No. 14
	     * Letter Combinations of a Phone Number 
	     */
		String s = "23";
		TeasingUtil.printListString(arrowcode.letterCombinations(s));
		//List<List<Integer>> ret = arrowcode.threeSum2(nums);
		//TeasingUtil.printIntegerPowerSet(ret);
		//int ret = arrowcode.threeSumClosest(nums, -2);
		//System.out.println(ret);
		/**
		 * Problem No.189
		 * Rotate Array
		 */
		/*
		int[] test = TeasingUtil.genList(100, 90, 1);
		TeasingUtil.printList(test)
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
