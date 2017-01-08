package cn.arrowcoder.training;

import cn.arrow.brainteasing.TeasingUtil;

public class ArrowCodeTest {

    public int foo(int x) {
        try {
            return x;
        } finally {
            x++;
            System.out.println(x);
        }
    }

    // public ArrowCode arrowcode;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // int x = test.foo(5);
        // System.out.println(x);
        /*
         * String teststr = "12"; System.out.println(teststr.substring(1, 2));
         * if (teststr.substring(1, 1).equals("")) System.out.println("empty");
         */
        /*
         * HashSet<List<Integer>> test = new HashSet<List<Integer>>();
         * List<Integer> e1 = new LinkedList<Integer>(); e1.add(1); e1.add(2);
         * e1.add(3); List<Integer> e2 = new LinkedList<Integer>(); e2.add(1);
         * e2.add(2); e2.add(3); System.out.println(test.add(e1));
         * System.out.println(test.add(e2));
         */
        ArrowCodeTest.test();
        /*
         * Scanner sc = new Scanner(System.in); int n = sc.nextInt(); for(int t
         * = 0; t < n; t++) { int a = sc.nextInt(); int b = sc.nextInt();
         * System.out.println(a+b); }
         */

        // HashSet<Character> row[] = new HashSet<Character>[9];
        /*
         * String[] argss = {"a", "b"}; System.out.println("main...");
         * main(argss);
         */
        // ArrowCodeTest.classictest();

    }

    static public void classictest() {

        /**
         * Classic #1: sorting
         */
        /*
         * int nums[] = TeasingUtil.genList(400, 200); int nums2[] =
         * Arrays.copyOf(nums, nums.length); int nums3[] = Arrays.copyOf(nums,
         * nums.length); int nums4[] = Arrays.copyOf(nums, nums.length);
         * TeasingUtil.printList(nums); //TeasingUtil.printList(nums2); int
         * ctr_insert = arrowclassic.insertSort(nums); int ctr_merge =
         * arrowclassic.mergeSort(nums2); int ctr_quick =
         * arrowclassic.quickSort(nums3); int ctr_counter =
         * arrowclassic.counterSort(nums4, 200); TeasingUtil.printList(nums);
         * TeasingUtil.printList(nums2); TeasingUtil.printList(nums3);
         * TeasingUtil.printList(nums4); System.out.println("Array length:" +
         * nums.length); System.out.println("counter_insert:" + ctr_insert);
         * System.out.println("counter_merger:" + ctr_merge);
         * System.out.println("counter_quick:" + ctr_quick);
         * System.out.println("counter_counter:" + ctr_counter);
         */

        /**
         * Heap Sort
         */
        /*
         * int nums[] = TeasingUtil.genList(20, 100); //Heap theap = new
         * Heap(nums); TeasingUtil.printList(nums); Heap.heapSort(nums);
         * TeasingUtil.printList(nums);
         */

        /**
         * Binary Search Tree
         */
        BSTree root = new BSTree(15);
        root.left = new BSTree(6);
        root.left.parent = root;
        root.right = new BSTree(18);
        root.right.parent = root;

        root.left.left = new BSTree(3);
        root.left.left.parent = root.left;
        root.left.right = new BSTree(7);
        root.left.right.parent = root.left;

        root.right.left = new BSTree(17);
        root.right.left.parent = root.right;
        root.right.right = new BSTree(20);
        root.right.right.parent = root.right;

        root.left.left.left = new BSTree(2);
        root.left.left.left.parent = root.left.left;
        root.left.left.right = new BSTree(4);
        root.left.left.right.parent = root.left.left;

        root.left.right.right = new BSTree(13);
        root.left.right.right.parent = root.left.right;

        root.left.right.right.left = new BSTree(9);
        root.left.right.right.left.parent = root.left.right.right;

        root.inOrder();
        root.inOrderNR();
        /// root.preOrderNR();
        // root.postOrderNR();
        /*
         * List<Integer> treelist = new LinkedList<Integer>();
         * root.inOrderNR(treelist); TeasingUtil.printListInteger(treelist);
         */

        BSTree bst = root.search(4);
        /*
         * if (bst != null) bst.inOrder(); else System.out.println("not found");
         */

        BSTree succ = root.succ_in(bst);
        if (succ != null)
            System.out.println(succ.val);
        else
            System.out.println("null");

        BSTree x = new BSTree(18);
        root.insert(x);
        root.inOrder();
        /*
         * BSTree minnode = root.min(); BSTree maxnode = root.max();
         *
         * if (maxnode != null) System.out.println("Max value is " +
         * maxnode.val); if (minnode != null) System.out.println("Max value is "
         * + minnode.val);
         */

        TeasingUtil.printList(root.inOrderMorris(root));
    }

    static public void test() {
        ArrowLeetCode arrowcode = new ArrowLeetCode();

        /**
         * P #0: Binary Tree - preorder
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.left.left.left = new TreeNode(-1);
        root.right.left = new TreeNode(-2);
        // root.right.left.left = new TreeNode(30);

        TeasingUtil.printList(arrowcode.TreeInorder1(root));
        TeasingUtil.printList(arrowcode.TreeInorderRec(root));

        TeasingUtil.printList(arrowcode.TreePreorder(root));
        TeasingUtil.printList(arrowcode.TreePreorderRec(root));

        TeasingUtil.printList(arrowcode.TreePostorder(root));
        TeasingUtil.printList(arrowcode.TreePostorderRec(root));

        // System.out.println(arrowcode.p112_hasPathSumNR(root, -2));

        /**
         * Problem No. 1 Two Sum
         */
        /*
         * int[] numbers = TeasingUtil.genList(40, 20); int[] result =
         * arrowcode.twoSum(numbers, 23); TeasingUtil.printList(numbers); if
         * (result != null) TeasingUtil.printList(result);
         */
        /**
         * P #463: Island Perimeter
         */
        /*
         * int[][] grid = { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 1, 0 }, { 1,
         * 1, 1, 0 } }; int perimeter = arrowcode.islandPerimeter(grid);
         * System.out.println("Perimeter:" + perimeter);
         */
        /**
         * Problem No. 2 Add Two Numbers presented in LinkedList
         */
        /*
         * ListNode l1 = new ListNode(2); l1.next = new ListNode(4);
         * l1.next.next = new ListNode(9); TeasingUtil.printListNode(l1);
         *
         * ListNode l2 = new ListNode(5); l2.next = new ListNode(6);
         * TeasingUtil.printListNode(l2); ListNode sumlist =
         * arrowcode.p2_addTwoNumbers(l1, l2);
         * TeasingUtil.printListNode(sumlist);
         */
        /**
         * Problem No. 3 Longest Substring Without Repeating Characters
         */
        // String s = "bvbf90daceb3";
        // System.out.println(arrowcode.lengthOfLongestSubstring(s));

        /**
         * Problme No.5
         */
        // String s = "eabcddcbaf";
        // String s = "e";
        // System.out.println(arrowcode.longestPalindromeN(s));

        /**
         * Problem No.6
         */
        // String s = "PAYPALISHIRING";
        // System.out.println(arrowcode.convert2(s, 4));

        /**
         * Problem No.7
         */
        // 2147483647
        // int input = 1534236469;
        // int input = -8923;
        // System.out.println( 1000 << 22);
        // System.out.println(964632435 * 10 + 1);
        // System.out.println(arrowcode.reverse2(input));

        /**
         * Problem No. 8
         */
        /*
         * String str = "     -1"; System.out.println(arrowcode.p8_myAtoi(str));
         */
        /**
         * Problem #9 Palindrome Number
         */
        // int x = 210112;
        // System.out.println(arrowcode.p9_isPalindrome(x));

        /**
         * Problem #10 Regular Expression Match
         */
        /*
         * String s = "aab"; String p = "c*a*b";
         * System.out.println(arrowcode.isMatch(s, p));
         */

        /**
         * Problem #12 & #13 Integer to Roman Roman to Integer
         */
        /*
         * int x = 1689; String rx = arrowcode.intToRoman(x);
         * System.out.println(x + "-" + rx + "-" + arrowcode.romanToInt2(rx));
         */
        /**
         * Problem #13 Longest Common Prefix
         */
        // String[] strs= {"abcf", "abced", "abca"};
        // System.out.println(arrowcode.longestCommonPrefix(strs));

        /**
         * Problem #14 & #15 3Sum
         */
        /*
         * int[] nums = {-1, 0, 1, 2, -1, -4}; List<List<Integer>> ret =
         * arrowcode.threeSum2(nums); TeasingUtil.printListList(ret);
         */
        /**
         * Problem No. 14 Letter Combinations of a Phone Number
         */
        // String s = "901";
        // TeasingUtil.printList(arrowcode.p017_letterCombinations(s));
        // TeasingUtil.printListString(arrowcode.letterCombinations(s));
        // List<List<Integer>> ret = arrowcode.threeSum2(nums);
        // TeasingUtil.printIntegerPowerSet(ret);
        // int ret = arrowcode.threeSumClosest(nums, -2);
        // System.out.println(ret);

        /**
         * Problem #18 4Sum
         */
        /*
         * int[] num = {1, 0, 7,0, -1, 0, -2, 2, 0, 3, -4, 5, 4, 6};
         *
         * //int[] num = {0,0,0,0}; int[] num2 = Arrays.copyOf(num, num.length);
         * List<List<Integer>> sol = arrowcode.fourSum(num, 0);
         * List<List<Integer>> sol1 = arrowcode.fourSum2(num2, 0);
         * TeasingUtil.printListList(sol); TeasingUtil.printListList(sol1);
         */
        /**
         * Problem #19 Remove Nth Node From End of List
         */
        /*
         * ListNode list = new ListNode(1); list.next = new ListNode(2);
         * list.next.next = new ListNode(3); list.next.next.next = new
         * ListNode(4); list.next.next.next.next = new ListNode(5);
         * TeasingUtil.printListNode(list); ListNode removed =
         * arrowcode.removeNthFromEnd(list, 5);
         * //TeasingUtil.printListNode(list);
         * TeasingUtil.printListNode(removed);
         */
        /**
         * Problem #20 Valid Parentheses
         */
        // String ps = ")";
        // System.out.println(arrowcode.isValid(ps));

        /**
         * Problem #21 Merge Two Sorted Lists
         */
        /*
         * ListNode list = new ListNode(1); list.next = new ListNode(5);
         * list.next.next = new ListNode(10); //list.next.next.next = new
         * ListNode(11); //list.next.next.next.next = new ListNode(15);
         *
         * ListNode list2 = new ListNode(3); list2.next = new ListNode(8);
         * list2.next.next = new ListNode(13);
         *
         * TeasingUtil.printListNode(arrowcode.mergeTwoLists(list, list2));
         */
        /**
         * Problem #22 Generate Parentheses
         */
        // TeasingUtil.printStringList(arrowcode.generateParenthesis(4));

        /**
         * Problem #24 Swap Nodes in Pairs
         */
        /*
         * ListNode list = new ListNode(1); list.next = new ListNode(5);
         * list.next.next = new ListNode(10); list.next.next.next = new
         * ListNode(11); //list.next.next.next.next = new ListNode(15);
         * TeasingUtil.printListNode(list);
         * TeasingUtil.printListNode(arrowcode.swapPairs(list));
         */
        /**
         * Problem #25 Reverse Nodes in k-Group
         */
        /*
         * ListNode list = new ListNode(1); list.next = new ListNode(5);
         * list.next.next = new ListNode(10); list.next.next.next = new
         * ListNode(11); list.next.next.next.next = new ListNode(15);
         * TeasingUtil.printListNode(list);
         * TeasingUtil.printListNode(arrowcode.reverseKGroup(list, 1));
         */
        /**
         * Problem #26 Remove Duplicates from Sorted Array
         */
        /*
         * int[] A = {1, 1, 2};
         * System.out.println(arrowcode.removeDuplicates(A));
         * TeasingUtil.printList(A);
         */
        /**
         * Problem #27 Remove Element
         */
        /*
         * int[] A = {1, 1, 2, 4, 19, 0};
         * System.out.println(arrowcode.removeElement(A, 4));
         * TeasingUtil.printList(A);
         */
        /**
         * Problem #28 Implement strStr()
         */
        /*
         * String h = "abbcedf9022"; String n = "22";
         * System.out.println(arrowcode.strStr(h, n));
         */
        /**
         * Problem #29 Divide Two Integers
         */
        /*
         * int a = 180; int b = 29; System.out.println(arrowcode.divide(a, b));
         */
        /**
         * Problem #30 Substring with Concatenation of All Words
         */
        /*
         * String s0 = "wefoobarbarfoobar"; String[] words = {"foo", "bar",
         * "bar"}; String s1 = "baaababababa"; String[] words1 = {"a","b","a"};
         * String s2 = "cbaccbcbbc"; String[] words2 = {"cb","bc"};
         * //TeasingUtil.printListInteger(arrowcode.findSubstring2(s2, words2));
         * TeasingUtil.printList(arrowcode.findSubstring3(s0, words));
         * TeasingUtil.printList(arrowcode.findSubstring3(s1, words1));
         * TeasingUtil.printList(arrowcode.findSubstring3(s2, words2));
         */
        /**
         * Problem #31 Next Permutation
         */
        /*
         * int[] num = {3, 7, 6, 6, 2, 1}; arrowcode.nextPermutation(num);
         * TeasingUtil.printList(num);
         */
        /**
         * Problem #32 Longest Valid Parentheses
         */
        /*
         * String pstr = "(()())"; //String pstr = ")))()((()()()(())()))";
         * System.out.println(arrowcode.longestValidParentheses(pstr));
         */
        /**
         * Problem #33 Search in Rotated Sorted Array
         */
        // int[] nums = {1,1,1,2,3,1,1,1};
        // int[] nums = {10, 45,0,1,3, 5, 5, 7, 8,10};
        // System.out.println(arrowcode.search(nums, 3));

        /**
         * Problem #34 Search for a Range
         */
        /*
         * int[] nums = {1,4,4,5,5,5,7, 8, 9, 10,12}; int[] nums2 = {4,4};
         * TeasingUtil.printList(arrowcode.searchRange(nums2, 4));
         */
        /**
         * Problem #35 Search Insert Position
         */
        // int[] nums = {4};
        // System.out.println(arrowcode.searchInsert(nums, 4));

        /**
         * Problem #36 Valid Sudoku
         */
        /*
         * char[][] board ={{'5','3','.','.','7','.','.','.','.'},
         * {'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6
         * ','.'}, {'8','.','.','.','6','.','.','.','3'},
         * {'4','.','.','8','.','3','.','.','1'},
         * {'7','.','.','.','2','.','.','.','6'},
         * {'.','6','.','.','.','.','2','8','.'},
         * {'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7
         * ','9'}};
         *
         * char[][] board1 ={ {'4','6','3','7','2','8','9','5','1'},
         * {'2','5','9','4','6','1','7','3','8'},
         * {'7','8','1','3','5','9','6','4','2'},
         * {'5','3','2','1','9','7','4','8','6'},
         * {'9','1','4','6','8','2','5','7','3'},
         * {'6','7','.','5','4','3','1','2','9'},
         * {'8','2','.','9','7','5','3','1','4'},
         * {'1','4','7','2','3','6','8','9','5'},
         * {'3','9','.','.','1','4','2','.','7'}};
         */
        // System.out.println(arrowcode.isValidSudoku(board1));

        /**
         * Problem #37 Sudoku Solver
         */
        // arrowcode.solveSudoku(board);
        // TeasingUtil.printSudoku(board);

        /**
         * Problem #38 Count and Say
         */
        // System.out.println(arrowcode.countAndSay(7));

        /**
         * Problem #39 Combination Sum
         */
        // int[] candidates = {2,3,3,5,5,5,9};
        // TeasingUtil.printList(arrowcode.removeDuplicated(candidates));
        // TeasingUtil.printListList(arrowcode.combinationSum2(candidates, 16));

        /**
         * Problem #41 First Missing Positive
         */
        /*
         * int nums[] = {6, 1, 2, 3, 7, 9,8, 5, 4}; TeasingUtil.printList(nums);
         * System.out.println(arrowcode.firstMissingPositive(nums));
         * TeasingUtil.printList(nums);
         */
        /**
         * Problem #43 Multiply Strings
         */
        /*
         * String s1 = "189"; String s2 = "913";
         * System.out.println(arrowcode.add(s1, s2));
         */

        /**
         * Problem #46, #47 Permutations | Unique Permutations
         */
        /*
         * int[] nums = {1,2,3,4};
         * TeasingUtil.printListList(arrowcode.permute(nums));
         * System.out.println();
         */
        // TeasingUtil.printListList(arrowcode.permuteUnique(nums));

        /**
         * Problem #77 Combinations
         */
        // TeasingUtil.printListList(arrowcode.combine(5, 3));
        /**
         * Problem #80 Remove Duplicates from Sorted Array II
         */
        /*
         * int nums[] = {1,1,1,2,2,2,3,3,4,5,5,5,6,7,8, 8, 8, 9, 9, 10};
         * TeasingUtil.printList(nums);
         * System.out.println(arrowcode.removeDuplicatesII2(nums));
         * TeasingUtil.printList(nums);
         */

        /**
         * P088. Merge Sorted Array
         */
        // int nums1[] = { 1, 0 };
        // int nums2[] = { 2 };
        // arrowcode.p088_merge(nums1, 1, nums2, 1);
        // TeasingUtil.printList(nums1);

        /**
         * P148. Sort List
         */
        // ListNode list = new ListNode(2);
        // list.next = new ListNode(1);
        // list.next.next = new ListNode(4);
        // list.next.next.next = new ListNode(3);
        // list.next.next.next.next = new ListNode(1);
        // TeasingUtil.printList(list);
        // TeasingUtil.printList(arrowcode.p147_insertionSortList2(list));

        /**
         * Problem #90 Subsets II
         */
        // int[] test = { 1, 2, 4, 3,3,3};
        // TeasingUtil.printListList(arrowcode.subsetsWithDup(test));

        /**
         * Problem #96 Unique Binary Search Trees
         */
        // System.out.println(arrowcode.numTrees(5));
        /*
         * for(int i=1; i<=15; i++){ System.out.println("i:" + i);
         * arrowcode.generateTrees2(i); //arrowcode.generateTrees(i); }
         */

        /**
         * Problem No.189 Rotate Array
         */
        /*
         * int[] test = TeasingUtil.genList(100, 90, 1);
         * TeasingUtil.printList(test) arrowcode.rotate2(test, 7);
         * TeasingUtil.printList(test);
         */
        /**
         * Problem No. 190 Reverse Bits
         */
        // int test = 43261596;
        // System.out.println("n = " + test + " reversed to " +
        // arrowcode.reverseBits(test));

        /**
         * Problem No. 191 Number of 1 Bits
         */

        /**
         * Problem No. 203
         */
        /*
         * ListNode head = new ListNode (1); head.next = new ListNode(2);
         * head.next.next = new ListNode (3); head.next.next.next = new
         * ListNode(2); TeasingUtil.printList(arrowcode.removeElements(head,
         * 2));
         */
        // System.out.println("n = " + 7 + " with " + arrowcode.hammingWeight(7)
        // + " 1s");

        /**
         * Problem #146 LRU Cache
         */
        /*
         * LRUCache lrucache = new LRUCache(2);
         *
         * System.out.println("get 2:" + lrucache.get(2)); lrucache.set(2, 6);
         * lrucache.print(); System.out.println("get 1:" + lrucache.get(1));
         * lrucache.set(1, 5); lrucache.print(); lrucache.set(1, 2);
         * lrucache.print(); System.out.println("get 1:" + lrucache.get(1));
         * System.out.println("get 2:" + lrucache.get(2));
         */
        /*
         * lrucache.set(2, 1); lrucache.print(); System.out.println("get 2:" +
         * lrucache.get(2)); lrucache.print(); lrucache.set(3,2);
         * lrucache.print(); System.out.println("get 2:" + lrucache.get(2));
         * System.out.println("get 3:" + lrucache.get(3));
         */
        /*
         * lrucache.set(1, 101); lrucache.print(); lrucache.set(2,102);
         * lrucache.print(); lrucache.set(3,103); lrucache.print();
         * lrucache.set(4,104); lrucache.print(); lrucache.set(5,105);
         * lrucache.print(); System.out.println("get 2:" + lrucache.get(2));
         * System.out.println("get 9:" + lrucache.get(9)); lrucache.print();
         * lrucache.set(6,106); lrucache.print();
         */

        /**
         * Problem #150 Evaluate Reverse Polish Notation
         */

        /**
         * Problem #173
         */
        // String[] tokens = {"4", "13", "5", "/", "+"};
        // System.out.println(arrowcode.evalRPN3(tokens));
        /*
         * TreeNode root = new TreeNode(10); root.left = new TreeNode(5);
         * root.left.left = new TreeNode(3); root.left.right = new TreeNode(8);
         * root.right = new TreeNode(15); root.right.left = new TreeNode(12);
         * root.right.right = new TreeNode(18);
         *
         * BSTIterator bsti = new BSTIterator(root); while(bsti.hasNext())
         * System.out.print(bsti.next() + ",");
         */
        /**
         * Problem #202 Happy Number
         */
        // System.out.println(arrowcode.isHappy(782));

        /**
         * Problem #204 Count Primes
         */
        // arrowcode.countPrimes(150);

        /**
         * Problem #216 Combination Sum III
         */
        // TeasingUtil.printListList(arrowcode.combinationSumIII(4, 27));
        // TeasingUtil.printListList(arrowcode.combinationSumIII2(4, 27));
        // int[] nums = {1, 2, 5, 1,13};
        // List<Integer> test = Arrays.asList(new Integer[](nums));

        /**
         * Problem #231
         */
        // System.out.println(arrowcode.isPowerOfTwo(16));

        /**
         * Problem #258 Add Digits
         */
        /*
         * int x = 19023; System.out.println(arrowcode.addDigits(x));
         * System.out.println((x % 9) == 0 ? 9 : x % 9);
         */

        /**
         * P263
         */
        // System.out.println(arrowcode.p263_isUgly(7));

        /**
         * P264
         */
        System.out.println(arrowcode.p264_nthUglyNumber(7));
        /**
         * Problem #268 Missing Number
         */
        /*
         * int[] nums = {1, 0, 1, 1};
         * System.out.println(arrowcode.containsNearbyDuplicate(nums, 1));
         * TeasingUtil.printList(nums);
         */

        /**
         * Problem #998 Activity-selection problem
         */
        /*
         * int[] s = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 }; int[] f = { 4, 5, 6,
         * 7, 9, 9, 10, 11, 12, 14, 16 }; List<Integer> ret998 =
         * arrowcode.actSel(s, f); for (int i : ret998) System.out.print(s[i] +
         * "-" + f[i] + " : "); System.out.println(); ret998 =
         * arrowcode.actSel(s, f); for (int i : ret998) System.out.print(s[i] +
         * "-" + f[i] + " : "); System.out.println();
         */

        /**
         * Problem 999 conSum
         */
        // int[] nums = { 3, 2, 8 };
        // System.out.println("P999:");
        // System.out.println("8: " + arrowcode.p999_conSum(nums, 8));
        // System.out.println("2: " + arrowcode.p999_conSum(nums, 2));
        // System.out.println("3: " + arrowcode.p999_conSum(nums, 3));
        // System.out.println("5: " + arrowcode.p999_conSum(nums, 5));
        // System.out.println("10: " + arrowcode.p999_conSum(nums, 10));
        // System.out.println("13: " + arrowcode.p999_conSum(nums, 13));
        // System.out.println("15: " + arrowcode.p999_conSum(nums, 15));
        // System.out.println("1: " + arrowcode.p999_conSum(nums, 1));
        // System.out.println("4: " + arrowcode.p999_conSum(nums, 4));
        // System.out.println("9: " + arrowcode.p999_conSum(nums, 9));
        // System.out.println("11: " + arrowcode.p999_conSum(nums, 11));

    }
}
