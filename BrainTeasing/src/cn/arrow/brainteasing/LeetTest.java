package cn.arrow.brainteasing;

public class LeetTest {

	public static void main(String[] args) {
		
		LeetCode leetcode = new LeetCode();
		
		/**
		 * Rotate Array 
		 */
		int[] test = TeasingUtil.genList(20, 90, 1);
		TeasingUtil.printList(test);
		leetcode.rotate(test, 7);
		TeasingUtil.printList(test);
		
		/**
		 * Reverse Bits 
		 */
		//System.out.println(leetcode.reverseBits(43261596));
		
		/**
		 * Regular Expression Matching
		 */
		//System.out.println(leetcode.isMatch2("ab", ".*c"));
		/**
		 * Xor Test
		 */
		/*
		long L2_64[] = {0x9d1a4f78, 0x7c2822eb, 0x9f970f4e, 0x2d1cfa42};
		long L2_32[] = {0x75e5e3ea, 0x325032a9, 0x6068f0b1, 0xeea6e3dd};
		long R2_64[] = {0xcb28d863,	0xfdc48bfb, 0x932330e4, 0xc0b1d266};
		long R2_32[] = {0x773ec3e6, 0xc5e2364b, 0xb645c008, 0xb2146dd0};
		

		for (int i = 0; i < L2_64.length; i++){
			System.out.println("L2 Xor [" + i + "]:" + Long.toHexString(L2_64[i] ^ L2_32[i]));
			System.out.println("R2 Xor [" + i + "]:" + Long.toHexString(R2_64[i] ^ R2_32[i]));			
		}
		 */
		/*
		for (char x = '1'; x <= '9'; ++x)
			System.out.print(x);
		System.out.println()
		*/
		/**
		 * Search a 2D Matrix 
		 */
		
		//int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		//int[][] matrix = {{1},{3}};
		//System.out.println(leetcode.searchMatrix(matrix, 3));
		/**
		 * Find Minimum in Rotated Sorted Array
		 */
		//int[] num = {3,1,3};
		//System.out.println(leetcode.findMinII2(num));
		/**
		 * Decode Ways
		 */
		//String input = "10";
		//System.out.println(leetcode.numDecodings(input));
		/**
		 * Evaluate Reverse Polish Notation
		*/
		//String[] tokens = {"2", "1", "+", "3", "*"};
		//System.out.println(leetcode.evalRPN(tokens));
		
		/**
		 * Find Peak Element
		 */
		//int[] num = {3,4,2,1,3};
		//System.out.println(leetcode.findPeakElement2(num));
		
		/**
		 * Rotate Image 
		 */
		//int[][] matrix = {{1,2},{3,4}};
		
		/*int[][] matrix = {{1,2,3,4},{5,6,7,8},{7,8,9,0},{0,1,2,3} };
		TeasingUtil.printMatrix(matrix);
		System.out.println();
		leetcode.rotate(matrix);
		TeasingUtil.printMatrix(matrix);
		*/
		/**
		 * Majority Element
		 */
		
		//int[] num = {1,3,9,3,2,3,2,1,3,1,3,3,3};
		//int[] num = {12,13,12,12};
		//System.out.println(leetcode.majorityElement3(num));
		/**
		 * Anagrams
		 */
		/*
		String[] strs = {"","", "dog", "god", "tm","cat","odg", "tca", "", "aact", "caat", "m"};
		List<String> anagrams = leetcode.anagrams2(strs);
		TeasingUtil.printListString(anagrams);
		*/
		
		/**
		 * 	Next Permutation	 
		 */
		/*
		int[] num = {1,3,2};//TeasingUtil.genList(20, 50);
		TeasingUtil.printList(num);
		//leetcode.nextPermutation(num);
		//leetcode.quicksort(num, 0, num.length-1);
		leetcode.nextPermutation2(num);
		//TeasingUtil.printList(num);
		//leetcode.reverseList(num,0,num.length-1);
		TeasingUtil.printList(num);
		*/
		/**
		 * Word Search
		 */
		//char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
		//System.out.println(leetcode.exist(board, "ASFCC"));
		
		
		/**
		 * Intersection of Two Linked Lists
		 */
		/*
		ListNode A = new ListNode(1);
		ListNode B = new ListNode(2);
		ListNode common = new ListNode(100);
		common.next = new ListNode(101);
		common.next.next = new ListNode(102);
		A.next = new ListNode(11);
		A.next.next = common;
		B.next = new ListNode(21);
		
		TeasingUtil.printListNode(leetcode.getIntersectionNode(A, B));
		*/
		/**
		 * Spiral Matrix II 
		 */
		/*
		int n = 0;
		int[][] matrix = leetcode.generateMatrix(n);
		TeasingUtil.printMatrix(matrix);
		*/
		/**
		 * Spiral Matrix 
		 */
		/*
		int[][] matrix = {{1,2,3}};//,,{4,5,6},{7,8,9},{10,11,12}};
		List<Integer> list = leetcode.spiralOrder(matrix);
		TeasingUtil.printListInteger(list);
		*/
		/**
		 * Combination Sum 
		 */
		
		//int[] candidates = {2, 3,5,6,7,10};
		//int[] candidates = {10,1,2,7,2,1,5};
		/*
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1); l1.add(2);l1.add(3);
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(1); l2.add(2);l2.add(3);

		set.add(l1);
		set.add(l2);
		
		for (List<Integer> l : set){
			TeasingUtil.printListInteger(l);
		}
		*/
		
		//TeasingUtil.printList(leetcode.removeDup(candidates));
		//List<List<Integer>> result = leetcode.combinationSumII2(candidates, 15);
		//TeasingUtil.printIntegerPowerSet(result);
		
		/**
		 * 4Sum
		 */
		/*
		int[] num = {1, 0, -1, 0, 0, -2,2};
		List<List<Integer>> qs = leetcode.fourSum2(num, 0);
		TeasingUtil.printIntegerPowerSet(qs);
		*/
		/**
		 * 3Sum Closest 
		 */
		//int[] num = {-1,2,1,-4};
		//System.out.println(leetcode.threeSumClosest(num, 1));
		
		/**
		 * Container With Most Water 
		 */
		//int[] height = {1,2,4,3};
		//System.out.println(leetcode.maxArea(height));
		/**
		 * Minimum Path Sum 
		 */
		//int[][] grid = new int[3][4];
		//int[][] grid = { {1,3,8,1}, {2,9,2,4},{7,1,2,3}};//,{5,1,2,4}};
		//System.out.println(leetcode.minPathSum2(grid));
		
		/**
		 * Letter Combinations of a Phone Number
		 */
		//TeasingUtil.printStringList(leetcode.letterCombinations("123"));
		
		/**
		 * Triangle 
		 */
		/*
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();
		List<Integer> row = new ArrayList<Integer>();
		row.add(2);
		triangle.add(row);
		row = new ArrayList<Integer>();
		row.add(3); row.add(4);
		triangle.add(row);
		row = new ArrayList<Integer>();
		row.add(6); row.add(5); row.add(7);
		triangle.add(row);
		row = new ArrayList<Integer>();
		row.add(4); row.add(1);row.add(8); row.add(3);
		triangle.add(row);
		System.out.println(leetcode.minimumTotal2(triangle));
		*/
		/**
		 * Longest Palindromic Substring
		 */
		
		//System.out.println(leetcode.longestPalindrome1("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"));
		
		/**
		 * Reverse Words in a String
		 */
		//System.out.println(leetcode.reverseWords("     the s   ky is blue "));
		/**
		 * Length of Last Word
		 */
		//System.out.println(leetcode.lengthOfLastWord2(" HelloWorldsdfdsf "));
		/**
		 * Valid Number 
		 */
		//System.out.println(leetcode.isNumber(" 1e. "));
		
		/**
		 * Climbing Stairs  1134903170
		 */
		//System.out.println(leetcode.climbStairs2(44));
		
		/**
		 * Sum Root to Leaf Numbers
		 */
		/*
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(0);
		//root.left.right = new TreeNode(4);
		root.right = new TreeNode(3);
		root.right.right =  new TreeNode(0);
		System.out.println(leetcode.sumNumbers2(root));
		*/
		/*
		 * 		ZigZag Conversion 
		 */

		//System.out.println(leetcode.convertZigZag2("ABCDE", 4));
		
		
		/**
		 * Pascal's Triangle 
		 */
		/*
		List<List<Integer>> pastri = leetcode.generate(15);
		TeasingUtil.printIntegerPowerSet(pastri);
		
		List<Integer> pastriII = leetcode.getRow(4);
		TeasingUtil.printListInteger(pastriII);
		*/
		/**
		 * Sudoku Solver
		 */
		/*
		char[][] board ={{'5','3','.','.','7','.','.','.','.'}, {'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},
				{'8','.','.','.','6','.','.','.','3'}, {'4','.','.','8','.','3','.','.','1'}, {'7','.','.','.','2','.','.','.','6'},
				{'.','6','.','.','.','.','2','8','.'}, {'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		
		char[][] board1 =
			{ {'1', '.', '.','.' ,'.' ,'.' ,'.' ,'.' ,'.'},
			  {'.','2','.','.','.','.','.','.','.'},
			  {'.','.','3','.','.','.','.','.','.'},
			  {'.','.','.','4','.','.','.','.','.'},
			  {'.','.','.','.','5','.','.','.','.'},
			  {'.','.','.','.','.','6','.','.','.'},
			  {'.','.','.','.','.','.','7','.','.'},
			  {'.','.','.','.','.','.','.','8','.'},
			  {'.','.','.','.','.','.','.','.','9'},
			};
		TeasingUtil.printSudoku(board1);
		
		leetcode.solveSudoku(board1);
		
		TeasingUtil.printSudoku(board1);
		
		System.out.println(leetcode.isValidSudoku(board1));
		*/
		/**
		 * Valid Palindrome 
		 */
		//System.out.println(leetcode.cleanWord("race a car"));
		//System.out.println(leetcode.isPalindrome("A man, a plan, a canal: Panama"));
		
		/**
		 * Unique Binary Search Trees
		 */
		//System.out.println(leetcode.numTrees(5));
		//System.out.println(leetcode.numTrees2(5));
		/**
		 * Unique Binary Search Trees II 
		 */
		/*
		List<TreeNode> bsts = leetcode.generateTrees(3);
		
		for (TreeNode root : bsts){
			root.InorderTreeWalk();
			List<Integer> pre = root.preorderTraversal();
			TeasingUtil.printListInteger(pre);
			System.out.println();
		}
		*/
		/**
		 * Count and Say 
		 */
		//System.out.println(leetcode.countAndSay(22));
		
		/**
		 * N-Queens
		 */
		/*
		List<String[]> nqueen = leetcode.solveNQueens(10);
		TeasingUtil.printStringList(nqueen);
		System.out.println("traditional:" + leetcode.totalNQueens(10));
		System.out.println("bit:" + leetcode.totalNQueens2(10));
		*/
		
		/**
		 * Permutations 
		 */
		
		/*
		//int[] seed = TeasingUtil.genList(5, 20);
		int[] seed = {1,3,1,4};
		TeasingUtil.printList(seed);
		//List<List<Integer>> permutation = leetcode.permuteUnique(seed);
		List<List<Integer>> permutation = leetcode.permuteUnique2(seed);
		
		TeasingUtil.printIntegerPowerSet(permutation);
		*/
		/**
		 * Unique Paths
		 */
		/*
		System.out.println(leetcode.uniquePaths2(2, 3));
		System.out.println(leetcode.uniquePaths(33, 10));
		*/
		/**
		 * Clone Graph 
		 */
		
		/*
		UndirectedGraphNode[] nodes = new UndirectedGraphNode[8];
		for (int i = 0 ; i< 8; ++i){
			nodes[i] = new UndirectedGraphNode(i);
		}

		nodes[0].neighbors.add(nodes[0]);
		nodes[0].neighbors.add(nodes[0]);
		//nodes[0].neighbors.add(nodes[0]);
		 */
		 
		/*
		nodes[1].neighbors.add(nodes[5]);
		nodes[2].neighbors.add(nodes[3]);
		nodes[2].neighbors.add(nodes[5]);
		nodes[2].neighbors.add(nodes[6]);
		nodes[3].neighbors.add(nodes[2]);
		nodes[3].neighbors.add(nodes[6]);
		nodes[3].neighbors.add(nodes[7]);
		nodes[4].neighbors.add(nodes[0]);
		nodes[5].neighbors.add(nodes[1]);
		nodes[5].neighbors.add(nodes[2]);
		nodes[5].neighbors.add(nodes[6]);
		nodes[6].neighbors.add(nodes[2]);
		nodes[6].neighbors.add(nodes[3]);
		nodes[6].neighbors.add(nodes[5]);
		nodes[6].neighbors.add(nodes[7]);
		nodes[7].neighbors.add(nodes[3]);
		nodes[7].neighbors.add(nodes[6]);
		 */
		//System.out.println(nodes[0].BFSTraverse());
		//System.out.println(leetcode.cloneGraph2(nodes[0]).BFSTraverse());
		
		/**
		 * Populating Next Right Pointers in Each Node
		 */
		/*
		TreeLinkNode root = new TreeLinkNode(1);
		root.left = new TreeLinkNode(2);
		root.right = new TreeLinkNode(3);
		//root.left.left = new TreeLinkNode(3);
		//root.left.left.left = new TreeLinkNode(4);
		//root.left.left.left.left = new TreeLinkNode(5);

		root.left.right = new TreeLinkNode(5);
		root.right.left = new TreeLinkNode(6);
		root.right.right = new TreeLinkNode(7);
		leetcode.connectII(root);
		*/
		/**
		 * Binary Tree Maximum Path Sum
		 */
		/*
		TreeNode root = new TreeNode(-1);
		root.left = new TreeNode(-2);
		root.right = new TreeNode(-5);
		root.left.left = new TreeNode(-3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(-6);
		System.out.println(leetcode.maxPathSum(root));
		*/
		/**
		 * Flatten Binary Tree to Linked List
		 */
		
		/*
		TreeNode root = new TreeNode(1);
		
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right.right = new TreeNode(6);
		leetcode.flatten2(root);
		*/
		/**
		 * Binary Tree Zigzag Level Order Traversal
		 */
		
		/*
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		List<List<Integer>> output = leetcode.zigzagLevelOrder(root);
		for (List<Integer> layer: output){
			for (Integer i: layer)
				System.out.print(i+",");
			System.out.println();
		}
		*/
		/**
		 * Path Sum II 
		 */
		/*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.left.right = new TreeNode(1);
		root.right.right = new TreeNode(4);
		List<List<Integer>> output = leetcode.pathSumII2(root, 17);
		
		for (List<Integer> list: output){
			for (Integer i: list)
				System.out.print(i.toString()+',');
			System.out.println();
		}
		System.out.println();
		*/
		/**
		 * Construct Binary Tree from Preorder and Inorder Traversal
		 * Construct Binary Tree from Inorder and Postorder Traversal  
		 */
		/*
		//int[] preorder = {1,2,4,5,3,6,8,9,7};
		//int[] postorder = {4,5,2,8,9,6,7,3,1};
		//int[] inorder = {4,2,5,1,8,6,9,3,7};
		int[] inorder = {3,2,4,1};
		int[] postorder = {3,4,2,1};
		//TreeNode root = leetcode.buildTreePreIn2(preorder, inorder);
		TreeNode root = leetcode.buildTreePostIn(inorder,postorder);
		*/
		/*
		System.out.print("preoder0: ");
		TeasingUtil.printList(preorder);
		System.out.print("preoder1: ");
		List<Integer> preorder_output = root.preorderTraversal();
		for (Integer i: preorder_output)
			System.out.print(i.toString()+',');
		System.out.println();
		*/
		/*
		System.out.print("postorder0: ");
		TeasingUtil.printList(postorder);
		System.out.print("postorder1: ");
		List<Integer> postorder_output = root.preorderTraversal();
		for (Integer i: postorder_output)
			System.out.print(i.toString()+',');
		System.out.println();
		
		System.out.print("inorder0: ");
		TeasingUtil.printList(inorder);
		System.out.print("inorder1: ");
		List<Integer> inorder_output = root.inorderTraversal();
		for (Integer i: inorder_output)
			System.out.print(i.toString()+',');
		System.out.println();
		 */
		
		/**
		 * Simplify Path
		 */
		//System.out.println("Output:" + leetcode.simplifyPath("/..."));
		
		/**
		 * Path Sum 
		 */
		/*
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(4);
		root.right = new TreeNode(8);
		root.left.left = new TreeNode(11);
		root.left.left.left = new TreeNode(7);
		root.left.left.right = new TreeNode(2);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(4);
		*/
		//System.out.println(leetcode.hasPathSum(root,27));
		/**
		 * Rotate List
		 */
		/*
		int[] sortlist = TeasingUtil.genList(10, 100);
		//int[] sortlist = {1,2,3,4,5};
		ListNode listnode1 = leetcode.genList(sortlist);
		leetcode.printListNode(listnode1);
		int n = (new Random()).nextInt(20);
		System.out.println("rotate:"+n+" for " + sortlist.length);
		leetcode.printListNode(leetcode.rotateRight(listnode1, n));
		*/
		/**
		 * Recover Binary Search Tree
		 */
		/*
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.left.right.right = new TreeNode(5);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);
		//TreeNode root = new TreeNode(0);
		//root.left = new TreeNode(1);
		//root.right = new TreeNode(3);
		root.InorderTreeWalk();
		System.out.println();
		leetcode.recoverTree(root);
		root.InorderTreeWalk();
		*/
		/**
		 * Reverse Linked List II 
		 */
		/*
		int[] sortlist = {93, 67,990,23,89};
		//int[] sortlist = TeasingUtil.genList(20, 100);
		TeasingUtil.printList(sortlist);
		ListNode listnode1 = leetcode.genList(sortlist);
		leetcode.printListNode(leetcode.reverseBetween2(listnode1, 2, 5));
		*/
		/**
		 * Subsets 
		 */
		
		//int[] sortlist = TeasingUtil.genList(20, 100);
		/*
		int[] sortlist={11,12,11,11,12,13};
		System.out.print("The original list is ");
		TeasingUtil.printList(sortlist);
		List<List<Integer>> powerset = leetcode.subsetsWithDup(sortlist);
		for (List<Integer> list: powerset){
			System.out.print("[");
			for (Integer i: list)
				System.out.print(i+",");
			System.out.println("]");
		}
		*/
		
		/**
		 * Validate Binary Search Tree 
		 */
		/*
		int[] sortlist = TeasingUtil.genList(20, 1000);
		System.out.print("The original list is ");
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		//ListNode listnode = (new LeetCode()).genList(sortlist);
		TreeNode troot = TreeNode.sortedArrayToBST(sortlist);
		System.out.println(leetcode.isValidBST2(troot));
		
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		//root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(3);
		//root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		System.out.println(leetcode.isValidBST(root));
		*/
		/**
		 * Symmetric Tree 
		 */
		/*
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		//root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(3);
		//root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(3);
		*/
		//List<List<Integer>> output = leetcode.levelOrderBottom(root);
		//System.out.println(leetcode.isSymmetric(root));		
		/**
		 * Binary Tree Level Order Traversal I/II
		 */
		/*
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		List<List<Integer>> output = leetcode.levelOrderBottom(root);
		for (List<Integer> layer: output){
			for (Integer i: layer)
				System.out.print(i+",");
			System.out.println();
		}
		*/
		/**
		 * Convert Sorted List to Binary Search Tree
		 */
		/*
		int[] sortlist = TeasingUtil.genList(10, 150);
		//int[] sortlist = {0, 5, 5, 79, 109, 116, 149};
		System.out.println("The list size is " + sortlist.length);
		System.out.print("The original list is ");
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		ListNode listnode = leetcode.genList(sortlist);
		TreeNode troot = leetcode.sortedListToBST(listnode);

		List<Integer> output = troot.inorderTraversal();
		for (Integer i: output)
			System.out.print(i.toString()+',');
		System.out.println();
		*/
		/**
		 * Search Insert Position
		 */
		/*
		int[] sortlist1 = TeasingUtil.genListfull(10, 40);
		(new Sort()).bubbleSort(sortlist1);
		int k = (new Random()).nextInt(40);
		TeasingUtil.printList(sortlist1);
		System.out.println(k + "->"+leetcode.searchInsert(sortlist1, k));
		*/
		/**
		 * Partition List
		 */
		/*
		//int[] sortlist1 = TeasingUtil.genList(20, 40);
		int[] sortlist1 = {2,1};
		ListNode head = leetcode.genList(sortlist1);
		leetcode.printListNode(head);
		int k = (new Random()).nextInt(40);
		System.out.println("k:" + k);
		leetcode.printListNode(leetcode.partition2(head, 1));
		*/
		//System.out.println(leetcode.divide2(130, 100));
		/**
		 * Add Binary 
		 */
		/*
		String a = "10010", b = "10";
		System.out.println(leetcode.addBinary(a, b));
		*/
		/**
		 * Plus One
		 */
		/*
		int[] digits = {9,9};
		TeasingUtil.printList(leetcode.plusOne(digits));
		*
		/**
		 * Maximum Subarray
		 */
		/*
		int[] sortlist1 = TeasingUtil.genListfull(20, 100);
		TeasingUtil.printList(sortlist1);
		TeasingUtil.printList(leetcode.findMaxSubarray(sortlist1, 0, sortlist1.length-1));
		System.out.println(leetcode.maxSubArray(sortlist1));
		System.out.println(leetcode.maxSubArray2(sortlist1));
		TeasingUtil.printList(leetcode.maxSubArray3(sortlist1));
		*/
		/**
		 * Search for a Range 
		 */
		/*
		int[] sortlist1 = TeasingUtil.genList(20, 10);
		(new Sort()).bubbleSort(sortlist1);
		TeasingUtil.printList(sortlist1);
		//int[] sortlist = {5, 7, 7, 8, 8, 10};
		int target = (new Random()).nextInt(10);
		System.out.println("target:" + target);
		int[] result = leetcode.searchRange(sortlist1, target);
		System.out.println("Range:" + result[0] + "," + result[1]);
		*/
		/**
		 * Pow(x, n) 
		 */
		//System.out.println(leetcode.pow(1.000000001, Integer.MIN_VALUE));
		
		/**
		 * Sqrt(x) 
		 */
		//System.out.println(leetcode.pow(1.000000001, Integer.MIN_VALUE));
		
		//int[] sortlist1 = TeasingUtil.genList(20, 150);
		
		/**
		 * Reverse Nodes in k-Group
		 */
		/*
		int[] sortlist1 = TeasingUtil.genList(20, 100);
		ListNode head = leetcode.genList(sortlist1);
		leetcode.printListNode(head);
		int k = (new Random()).nextInt(10);
		System.out.println("k:" + k);
		leetcode.printListNode(leetcode.reverseKGroup2(head, k));
		*/
		
		/**
		 * Swap Nodes in Pairs
		 */
		/*
		int[] sortlist1 = TeasingUtil.genList(20, 100);
		ListNode head = leetcode.genList(sortlist1);
		leetcode.printListNode(head);
		leetcode.printListNode(leetcode.swapPairs(head));
		*/
		/**
		 * Remove Nth Node From End of List
		 */
		/*
		int[] sortlist1 = {1};
		TeasingUtil.printList(sortlist1);
		ListNode listnode1 = leetcode.genList(sortlist1);
		int remove = (new Random()).nextInt(sortlist1.length);
		System.out.println("remove " + remove);
		leetcode.printListNode(leetcode.removeNthFromEnd2(listnode1, 1));
		*/
		//ListNode l1 = leetcode.insertionSortList(listnode1);
		
		
		/**
		 * Median of Two Sorted Arrays 
		 */
		
		//int[] sortlist1 = TeasingUtil.genList(20, 100);
		//int[] sortlist2 = TeasingUtil.genList(20, 100);
		/*
		int[] sortlist1 = {};
		int[] sortlist2 = {1,1,7};
		
		//(new Sort()).insertSort(sortlist1);
		TeasingUtil.printList(sortlist1);
		TeasingUtil.printList(sortlist2);
		//System.out.println("Array Size:" + sortlist1.length);
		//System.out.println("Median: " + leetcode.calMedian(sortlist1, 0, sortlist1.length-1));
		System.out.println("Median: " + leetcode.findMedianSortedArrays3(sortlist1, sortlist2));
		*/
		/**
		 * Sort Colors
		 */
		/*
		int[] sortlist1 = TeasingUtil.genList(50, 7);
		TeasingUtil.printList(sortlist1);
		//ListNode listnode1 = leetcode.genList(sortlist1);
		//ListNode l1 = leetcode.insertionSortList(listnode1);
		leetcode.sortAnyColors(sortlist1, 7);
		TeasingUtil.printList(sortlist1);
		*/
		/**
		 * Merge k Sorted Lists
		 */
		/*
		int[] sortlist1 = TeasingUtil.genList(20, 150);
		ListNode listnode1 = leetcode.genList(sortlist1);
		ListNode l1 = leetcode.insertionSortList(listnode1);
		leetcode.printListNode(l1);

		int[] sortlist2 = TeasingUtil.genList(20, 150);
		ListNode listnode2 = leetcode.genList(sortlist2);
		ListNode l2 = leetcode.insertionSortList(listnode2);
		leetcode.printListNode(l2);

		int[] sortlist3 = TeasingUtil.genList(20, 150);
		ListNode listnode3 = leetcode.genList(sortlist3);
		ListNode l3 = leetcode.insertionSortList(listnode3);
		leetcode.printListNode(l3);
		
		int[] sortlist4 = TeasingUtil.genList(20, 150);
		ListNode listnode4 = leetcode.genList(sortlist4);
		ListNode l4 = leetcode.insertionSortList(listnode4);
		leetcode.printListNode(l4);
		
		List<ListNode> l = new ArrayList<ListNode>();
		l.add(l1); l.add(l2); l.add(l3); l.add(null); l.add(l4);
		leetcode.printListNode(leetcode.mergeKLists(l));
		*/
		/**
		 * Search in Rotated Sorted Array 
		 */
		/*
		int[] sortlist = TeasingUtil.genList(30, 30);
		//int[] sortlist = {1,3,1,1,1};
		System.out.println("The list size is " + sortlist.length);
		System.out.print("The original list is ");
		TeasingUtil.printList(sortlist);
		(new Sort()).bubbleSort(sortlist);
		Random rand = new Random();
		int r = rand.nextInt(20)+1;
		int r1 = rand.nextInt(30);
		System.out.println("rotation times " + r ); 
		TeasingUtil.rotateArray(sortlist,r);
		System.out.print("The roated sorted list is ");
		TeasingUtil.printList(sortlist);
		System.out.println("target is " + r1);
		System.out.print("the index is " + leetcode.searchII(sortlist, r1));
		*/
		
		/**
		 * Remove Duplicates from Sorted Array II 
		 */
		/*
		int[] sortlist = TeasingUtil.genList(30, 40);
		//int[] sortlist = {14, 14, 20, 28, 29, 29, 29, 43};
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		System.out.println(sortlist.length);
		System.out.println(leetcode.removeDuplicatesII2(sortlist));
		
		TeasingUtil.printList(sortlist);
		*/
		/**
		 * Remove Duplicates from Sorted List I/II
		 */
		/*
		int[] sortlist = TeasingUtil.genList(30, 100);
		//int[] sortlist = {10,10,15,15,18,46,50,60,77,80,101,112,119,126,126,133,133,134,134,135};
		ListNode listnode = leetcode.genList(sortlist);
		//leetcode.printListNode(listnode);
		ListNode l1 = leetcode.insertionSortList(listnode);
		leetcode.printListNode(l1);
		leetcode.printListNode(leetcode.deleteDuplicatesII2(l1));
		*/
		/**
		 * Insertion Sort List & Merge Two Sorted Lists 
		 */
		/*
		int[] sortlist = TeasingUtil.genList(20, 150);
		ListNode listnode = leetcode.genList(sortlist);
		//leetcode.printListNode(listnode);
		ListNode l1 = leetcode.insertionSortList(listnode);
		leetcode.printListNode(leetcode.insertionSortList(l1));

		int[] sortlist1 = TeasingUtil.genList(20, 150);
		ListNode listnode1 = leetcode.genList(sortlist1);
		//leetcode.printListNode(listnode1);
		ListNode l2 = leetcode.insertionSortList(listnode1);
		leetcode.printListNode(l2);
		leetcode.printListNode(leetcode.mergeTwoLists(l1, l2));
		*/
		
		/**
		 * Sort List
		 */
		/*
		int[] sortlist = TeasingUtil.genList(20, 150);
		//TeasingUtil.printList(sortlist);
		ListNode listnode = leetcode.genList(sortlist);
		leetcode.printListNode(listnode);
		leetcode.printListNode(leetcode.sortList(listnode));
		
		sortlist = TeasingUtil.genList(20, 150);
		//TeasingUtil.printList(sortlist);
		listnode = leetcode.genList(sortlist);
		leetcode.printListNode(listnode);
		leetcode.printListNode(leetcode.sortList(listnode));
		*/
		//String[] strs = {"abce","abcd","ab","abcde"};
		//System.out.println(leetcode.longestCommonPrefix(strs));
		
		/**
		 * threeSum
		 */
		/*
		int numbers[] = {-2,-1,1,1,4,3,0,0,0, -5,2,5, -6};
		//int numbers[] = {-1,0,1};
		
		List<List<Integer>> triplets = leetcode.threeSum2(numbers);
		
		for (int i = 0; i< triplets.size(); ++i){
			System.out.print("(");
			for (int j = 0; j< triplets.get(i).size(); ++j)
				System.out.print(triplets.get(i).get(j).intValue() + ",");
			System.out.print(")");
		}

		System.out.println();

		triplets = leetcode.threeSum(numbers);
		
		for (int i = 0; i< triplets.size(); ++i){
			System.out.print("(");
			for (int j = 0; j< triplets.get(i).size(); ++j)
				System.out.print(triplets.get(i).get(j).intValue() + ",");
			System.out.print(")");
		}
		*/
		/**
		 * Test Merge Sort
		 */
		/*
		//int[] sortlist = TeasingUtil.genList(20, 150);
		int[] sortlist = TeasingUtil.genList(20, 100, -100);
		System.out.println("The list size is " + sortlist.length);
		System.out.print("The original list is ");
		TeasingUtil.printList(sortlist);
		//sort.heapSort(sortlist, Sort.Order.ASCENT);
		//Sort.selectedSort(sortlist);
		leetcode.mergeSort(sortlist, 0, sortlist.length-1);
		TeasingUtil.printList(sortlist);
		*/
		/**
		 * Roman to Integer
		 */
		//System.out.println(leetcode.romanToInt("XXII"));
		
		/**
		 * Integer to Roman
		 */
		//System.out.println(leetcode.intToRoman(13));
		
		/**
		 * Valid Parentheses
		 */
		//System.out.println(leetcode.isValid("({[]()}[]"));
		
		/**
		 * Generate Parentheses
		 */
		/*
		List<String> output = leetcode.generateParenthesis(3);
		
		Iterator<String> it=output.iterator();
		
		while (it.hasNext())
			System.out.println((String)it.next());
		*/
		//System.out.println(leetcode.isPalindrome(101));
		
		/*
		ListNode init = new ListNode(0);
		
		int[] n1= {1,2,3,4,5};
		int[] n2= {5,4,8,2,8};
		ListNode l1 = init.genList(n1);
		ListNode l2 = init.genList(n2);
		
		
		//leetcode.reorderList(head);
		leetcode.printListNode(leetcode.addTwoNumbers(l1, l2));
		*/
		/*
		 * Two Sum
		 */
		//int[] sum_numbers = {2, 7, 11, 15};
		//int[] indices = leetcode.twoSumSilly(sum_numbers, 9);
		
		//String test = " 384248091823018039802384";
		//String test = "1234567890123456789012345678901234567890";
		//String test = "2147483648";
		// String test = "-10522545460";
		//int test = 2147483647;
		//2147483647
		//int result = leetcode.lengthOfLongestSubstring(test);
		//int result = leetcode.reverse(0);
		//leetcode.quicktest();
		
		//int A[] = {-3, -1, 0, 0};
		
		//int result = leetcode.removeDuplicates(A);
		/*
		String p0 = "abacab";
		String p1 = "PARTICIPATE IN PARACHUTE";
		String m0 = "aca";
		String m1 = "PARA";
		String p = "issip";
		
		
		String p2 = "ABCDABCDAB ABCDABCDABCEF";
		String m2 = "CDABCE";
		
		String p3 = "mississippi";
		String m3 = "issip";

		System.out.println(leetcode.strStr1(p0, m0));
		System.out.println(leetcode.strStr1(p1, m1));
		System.out.println(leetcode.strStr1(p2, m2));
		System.out.println(leetcode.strStr1(p3, m3));
		
		int[] T = new int[p0.length()+1];
		int[] T1 = new int[p0.length()];
		leetcode.initKMP(p0, T);
		leetcode.initKMP2(p0,T1);
		System.out.println(p0);
		TeasingUtil.printList(T);
		TeasingUtil.printList(T1);
		
		T = new int[p1.length()+1];
		T1 = new int[p1.length()];
		leetcode.initKMP(p1, T);
		leetcode.initKMP2(p1,T1);
		System.out.println(p1);
		TeasingUtil.printList(T);
		TeasingUtil.printList(T1);

		T = new int[p.length()+1];
		T1 = new int[p.length()];
		leetcode.initKMP(p, T);
		leetcode.initKMP2(p,T1);
		System.out.println(p);
		TeasingUtil.printList(T);
		TeasingUtil.printList(T1);
		
		T = new int[p2.length()+1];
		T1 = new int[p2.length()];
		leetcode.initKMP(p2, T);
		leetcode.initKMP2(p2, T1);
		System.out.println(p2);
		TeasingUtil.printList(T);
		TeasingUtil.printList(T1);
		*/
		
		
		//System.out.println("Mission Accomplished:" + leetcode.strStr("acdegede902def99", "998987g"));
		//System.out.println("Mission Accomplished:" + leetcode.strStr("acdegede902def99", "998987g"));
		return;
	}

}
