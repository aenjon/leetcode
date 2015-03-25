package cn.arrow.careercup;

import java.util.Stack;

public class CareerCupTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		SingleLinkedList list = new SingleLinkedList();
		list.insert(100);
		list.insert(200);
		list.insert(300);
		list.insert(400);
		list.insert(500);
		
		list.reverse();
		
		System.out.println("------------------------");
		*/
		CareerCup ccup = new CareerCup();
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(10);
		s.push(11);
		s.push(8);
		s.push(7);
		s.push(30);
		s.push(78);
		
		Stack<Integer> sortedstack = ccup.sortStack(s);
		sortedstack.toString();
		System.out.println("------------------------");
		//ccup.printASCII();

		/*
		System.out.println();
		System.out.println("------------------------");
		System.out.println("P1.8: rotateString");
		//String s1 = "Dedfew";
		String s1 = "edf";
		
		String s2 = "ABCDedfew";
		System.out.println(ccup.isSubstring(s1, s2));
		*/
		/*
		System.out.println();
		System.out.println("------------------------");
		System.out.println("P1.5: Replace Space");
		String test1_5 = "Abc   oeA dc";
		//char[] test1_5 = new String("AbcoeA dc").toCharArray();
		System.out.println(ccup.replaceSpace(test1_5));
		
		System.out.println();
		System.out.println("------------------------");
		System.out.println("P1.4: anagram");
		String test1_4_1 = "AbcoeAdc";
		String test1_4_2 = "AAcbceed";
		if (ccup.anagram(test1_4_1, test1_4_2))
			System.out.println(true);
		else
			System.out.println(false);

		
		System.out.println();
		System.out.println("------------------------");
		System.out.println("P1.3: removeDuplicates");
		String test1_3 = "aaaa";
		char[] test1_3_chars = test1_3.toCharArray();
		ccup.removeDuplicates2(test1_3_chars);
		//System.out.println(test1_3_chars);
		TeasingUtil.printChars(test1_3_chars);
		
		System.out.println();
		System.out.println("------------------------");
		System.out.println("P1.1: checkUniqueLetters");
		String test1_1 = "ABcodeRe";
		System.out.println("Test1_1:" + test1_1);
		if (ccup.checkUniqueLetters(test1_1))
			System.out.println(true);
		else
			System.out.println(false);
			*/
	}
	
}
