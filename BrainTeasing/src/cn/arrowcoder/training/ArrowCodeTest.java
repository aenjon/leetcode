package cn.arrowcoder.training;

import java.util.Arrays;
import java.util.List;

import cn.arrow.brainteasing.ListNode;
import cn.arrow.brainteasing.TeasingUtil;

public class ArrowCodeTest {

	//public ArrowCode arrowcode;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//ArrowCodeTest.test();
		ArrowCodeTest.classictest();
		
	}
	
	static public void classictest(){
		ArrowClassic arrowclassic = new ArrowClassic();

		/**
		 * Classic #1: sorting
		 */
		
		int nums[] = TeasingUtil.genList(400, 200);
		int nums2[] = Arrays.copyOf(nums, nums.length);
		int nums3[] = Arrays.copyOf(nums, nums.length);
		int nums4[] = Arrays.copyOf(nums, nums.length);
		TeasingUtil.printList(nums);
		//TeasingUtil.printList(nums2);
		int ctr_insert = arrowclassic.insertSort(nums);
		int ctr_merge = arrowclassic.mergeSort(nums2);
		int ctr_quick = arrowclassic.quickSort(nums3);
		int ctr_counter = arrowclassic.counterSort(nums4, 200);
		TeasingUtil.printList(nums);
		TeasingUtil.printList(nums2);
		TeasingUtil.printList(nums3);
		TeasingUtil.printList(nums4);
		System.out.println("Array length:" + nums.length);
		System.out.println("counter_insert:" + ctr_insert);
		System.out.println("counter_merger:" + ctr_merge);
		System.out.println("counter_quick:" + ctr_quick);
		System.out.println("counter_counter:" + ctr_counter);
		
		
		/**
		 * Heap Sort
		 */
		/*
		int nums[] = TeasingUtil.genList(20, 100);
		//Heap theap = new Heap(nums);
		TeasingUtil.printList(nums);
		Heap.heapSort(nums);
		TeasingUtil.printList(nums);
		*/
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
		//String s = "23";
		//TeasingUtil.printListString(arrowcode.letterCombinations(s));
		//List<List<Integer>> ret = arrowcode.threeSum2(nums);
		//TeasingUtil.printIntegerPowerSet(ret);
		//int ret = arrowcode.threeSumClosest(nums, -2);
		//System.out.println(ret);
		
	    /**
	     * Problem #18
	     * 4Sum
	     */
		//int[] num = {1, 0, 7,0, -1, 0, -2, 2, 0, 3, -4, 5, 4, 6};
		/*
		int[] num = {0,0,0,0};
		int[] num2 = Arrays.copyOf(num, num.length);
		List<List<Integer>> sol = arrowcode.fourSum(num, 0);
		List<List<Integer>> sol1 = arrowcode.fourSum2(num2, 0);
		TeasingUtil.printIntegerPowerSet(sol);
		TeasingUtil.printIntegerPowerSet(sol1);
		*/
	    /**
	     * Problem #19
	     * Remove Nth Node From End of List
	     */
		/*
		ListNode list = new ListNode(1);
		list.next = new ListNode(2);
		list.next.next = new ListNode(3);
		list.next.next.next = new ListNode(4);
		list.next.next.next.next = new ListNode(5);
		TeasingUtil.printListNode(list);
		ListNode removed = arrowcode.removeNthFromEnd(list, 5);
		//TeasingUtil.printListNode(list);
		TeasingUtil.printListNode(removed);
		*/
	    /**
	     * Problem #20
	     * Valid Parentheses
	     */
		//String ps = ")";
		//System.out.println(arrowcode.isValid(ps));
		
	    /**
	     * Problem #21
	     * Merge Two Sorted Lists
	     */
		/*
		ListNode list = new ListNode(1);
		list.next = new ListNode(5);
		list.next.next = new ListNode(10);
		//list.next.next.next = new ListNode(11);
		//list.next.next.next.next = new ListNode(15);
		
		ListNode list2 = new ListNode(3);
		list2.next = new ListNode(8);
		list2.next.next = new ListNode(13);
		
		TeasingUtil.printListNode(arrowcode.mergeTwoLists(list, list2));
		 */
	    /**
	     * Problem #22
	     * Generate Parentheses 
	     */
		//TeasingUtil.printStringList(arrowcode.generateParenthesis(4));
		
	    /**
	     * Problem #24
	     * Swap Nodes in Pairs 
	     */
		/*
		ListNode list = new ListNode(1);
		list.next = new ListNode(5);
		list.next.next = new ListNode(10);
		list.next.next.next = new ListNode(11);
		//list.next.next.next.next = new ListNode(15);
		TeasingUtil.printListNode(list);
		TeasingUtil.printListNode(arrowcode.swapPairs(list));
		*/
	    /**
	     * Problem #25
	     * Reverse Nodes in k-Group
	     */
		/*
		ListNode list = new ListNode(1);
		list.next = new ListNode(5);
		list.next.next = new ListNode(10);
		list.next.next.next = new ListNode(11);
		list.next.next.next.next = new ListNode(15);
		TeasingUtil.printListNode(list);
		TeasingUtil.printListNode(arrowcode.reverseKGroup(list, 1));
		*/
	    /**
	     * Problem #26
	     * Remove Duplicates from Sorted Array 
	     */
		/*
		int[] A = {1, 1, 2};
		System.out.println(arrowcode.removeDuplicates(A));
		TeasingUtil.printList(A);
	    */
	    /**
	     * Problem #27
	     * Remove Element
	     */
		/*
		int[] A = {1, 1, 2, 4, 19, 0};
		System.out.println(arrowcode.removeElement(A, 4));
		TeasingUtil.printList(A);
		 */
	    /**
	     * Problem #28
	     * Implement strStr() 
	     */
		/*
		String h = "abbcedf9022";
		String n = "22";
		System.out.println(arrowcode.strStr(h, n));
		 */
	    /**
	     * Problem #29
	     * Divide Two Integers 
	     */
		/*
		int a = 180;
		int b = 29;
		System.out.println(arrowcode.divide(a, b));
		*/
	    /**
	     * Problem #30
	     * Substring with Concatenation of All Words
	     */
		/*
		String s = "wefoobarbarfoobar";
		String[] words = {"foo", "bar"};
		String s1 = "baaabababab";
		String[] words1 = {"a","b","a"};
		String s2 = "cbaccbcbbc";
		String[] words2 = {"cb","bc"}; 
		//TeasingUtil.printListInteger(arrowcode.findSubstring2(s2, words2));
		TeasingUtil.printListInteger(arrowcode.findSubstring3(s, words));
		TeasingUtil.printListInteger(arrowcode.findSubstring3(s1, words1));
		TeasingUtil.printListInteger(arrowcode.findSubstring3(s2, words2));
		*/
	    /**
	     * Problem #31
	     * Next Permutation
	     */
		/*
		int[] num = {3, 7, 6, 6, 2, 1};
		arrowcode.nextPermutation(num);
		TeasingUtil.printList(num);
		*/
	    /**
	     * Problem #32
	     * Longest Valid Parentheses 
	     */
		
		String pstr = "()()()()(";
		System.out.println(arrowcode.longestValidParentheses(pstr));
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
