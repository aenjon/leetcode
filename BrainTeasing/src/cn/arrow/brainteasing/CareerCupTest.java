package cn.arrow.brainteasing;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class CareerCupTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CareerCup ccup = new CareerCup();
		PrintStream cout = System.out;
		
		int[] s = {0, 1, 3, 0, 4, 3, 5, 6, 8, 8, 2, 12};
		int[] f = {0, 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16};
		Set<Integer> max = new HashSet<Integer>();
		ccup.recursiveActivitySelector(s, f, 0, s.length-1, max);
		for (Integer ai : max)
			cout.print(ai + ", ");
		cout.println();
		
		
		/*
		int[] p ={1,5,8,9,10,17,17,20,24,30};
		int[] r = ccup.BPCutRod(p, 10);
		*/
		//TeasingUtil.printList(r);
		
		//cout.println(ccup.getKthMagicNumber(13));
		//cout.println(ccup.fnDivide(90,4));
		//cout.println(ccup.lcm(60, 8));
		//List<List<Integer>> cents = ccup.calCents(20);
		
		//TeasingUtil.printIntegerPowerSet(cents);
		
		//ccup.printPar(4);
		/*
		List<String> permutation = ccup.permuteString("abc");
		for (String s : permutation)
			cout.println(s);
		*/
		/*
		int[] iset = TeasingUtil.genList(6, 100);
		TeasingUtil.printList(iset);
		TeasingUtil.printIntegerPowerSet(ccup.subset(iset));
		*/
		
		//ccout.println(ccup.RobotPath(4));
		
		//ccout.println(ccup.Fibonacci(11));
		/*
		 int x = 100;
		ccout.println(Integer.toBinaryString(x));
		int x_next = ccup.getNextSmall(x);
		ccout.println(Integer.toBinaryString(x_next));
		*/		
		//ccout.println(ccup.swapBits(10));
		//ccout.println(ccup.DecimalToBinary("1024.7652323906352990320934"));
		//ccout.println(ccup.bitSubstring(21, 1024, 2, 6));
		
		/*
		UndirectedGraphNode[] nodes = new UndirectedGraphNode[8];
		for (int i = 0 ; i< 8; ++i){
			nodes[i] = new UndirectedGraphNode(i);
		}
		/*
		nodes[0].neighbors.add(nodes[1]);
		nodes[0].neighbors.add(nodes[3]);
		nodes[1].neighbors.add(nodes[4]);
		nodes[2].neighbors.add(nodes[4]);
		nodes[2].neighbors.add(nodes[5]);
		nodes[3].neighbors.add(nodes[1]);			
		nodes[4].neighbors.add(nodes[3]);
		nodes[5].neighbors.add(nodes[5]);
		*/
		/*
		nodes[0].neighbors.add(nodes[1]);
		nodes[0].neighbors.add(nodes[4]);
		//nodes[1].neighbors.add(nodes[0]);
		nodes[1].neighbors.add(nodes[5]);
		nodes[2].neighbors.add(nodes[3]);
		nodes[2].neighbors.add(nodes[5]);
		nodes[2].neighbors.add(nodes[6]);
		nodes[3].neighbors.add(nodes[2]);
		nodes[3].neighbors.add(nodes[6]);
		nodes[3].neighbors.add(nodes[7]);
		//nodes[4].neighbors.add(nodes[0]);
		nodes[5].neighbors.add(nodes[1]);
		nodes[5].neighbors.add(nodes[2]);
		nodes[5].neighbors.add(nodes[6]);
		nodes[6].neighbors.add(nodes[2]);
		nodes[6].neighbors.add(nodes[3]);
		nodes[6].neighbors.add(nodes[5]);
		nodes[6].neighbors.add(nodes[7]);
		nodes[7].neighbors.add(nodes[3]);
		nodes[7].neighbors.add(nodes[6]);
		
		System.out.println(nodes[4].label + "->" + nodes[7].label);
		ccout.println(ccup.isRouted(nodes[4], nodes[7]));
		System.out.println(nodes[7].label + "->" + nodes[4].label);
		ccout.println(ccup.isRouted(nodes[7], nodes[4]));
		ccout.println(ccup.isConnected(nodes[4], nodes[7]));
		*/
		//ccout.println(nodes[0].BFSTraverse());
		
		/*
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(1);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(3);
		root.left.left.left.right = new TreeNode(5);
		root.right = new TreeNode(4);
		root.right.right = new TreeNode(4);
		ccout.println("Balance: " + ccup.isBalance(root));
		*/
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
