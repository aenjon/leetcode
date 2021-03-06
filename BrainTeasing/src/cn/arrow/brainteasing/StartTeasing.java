package cn.arrow.brainteasing;

import java.util.List;

public class StartTeasing {
	
	class Duplicated {
		boolean val;
	}

	public static void main(String[] args) {
		
		/*
		int x = 6 % 5;
		
		int yo = (-6) / (-5);
		int y = (-6) % (-5);
		int y1 = ((-6) % (-5) - 5) % (-5);
		
		int z0 = (-6) / 5;
		int z = (-6) % 5;
		int z1 = ((-6)%5 + 5) % 5;
		
		
		int k = 6 % (-5);
		int k0 = 6/ (-5);
		int k1 = (6/(-5)-5)%(-5);
		*/
		/*
		SingleLinkedList2 a1 = new SingleLinkedList2();
		//a1.Insert(9);
		a1.Insert(5);
		a1.Insert(1);
		a1.Insert(3); //9899
		
		SingleLinkedList2 a2 = new SingleLinkedList2();
		a2.Insert(2);
		a2.Insert(9);
		a2.Insert(5); //305
		
		Node add = a1.addNodes(a1, a2);
		//sll.Insert(200);
		//sll.Insert(900);
		//Node nnode = sll.searchNthNode(0);
		//Node snode = sll.Search(400);
		//sll.Reverse();
		//sll.Delelte(400);
		//sll.removeDuplicate2();
		
		(new SingleLinkedList2(add)).print();
		
		*/
		/*
		float one =0, zero = 0;
		for(int i = 0 ; i < 200; ++i){
			Random rand = new Random();
			int r = rand.nextInt(2);
			if (r==0)
				zero += 1;
			if(r==1)
				one += 1;
		}
		System.out.print("one:" + one + ",zero: " + zero);
		System.out.println(" rate:" + one/zero);
		*/
		
		
		/**
		 * Generate a random list
		 */
		int[] sortlist = TeasingUtil.genList(20, 150);
		//int[] sortlist = {0, 5, 5, 79, 109, 116, 149};
		//int[] sortlist = TeasingUtil.genList(40, 100, -100);
		//System.out.println("The list size is " + sortlist.length);
		System.out.print("The original list is ");
		//TeasingUtil.printList(sortlist);
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		//TeasingUtil.printList(sortlist);
		//ListNode listnode = (new LeetCode()).genList(sortlist);
		//TreeNode troot = TreeNode.sortedListToBST(listnode);
		TreeNode troot = TreeNode.sortedArrayToBST(sortlist);
		System.out.print("Morris Traversal: ");
		troot.MorrisTraversal();
		
		List<Integer> output = troot.inorderTraversal();
		for (Integer i: output)
			System.out.print(i.toString()+',');
		System.out.println();

		
		//TreeNode troot = new TreeNode(0);
		//TreeNode troot1 = new TreeNode(0);
		//troot.init(sortlist);
		
		/*
		troot.left = new TreeNode(1);
		troot.right = new TreeNode(2);
		troot.left.left = new TreeNode(3);
		troot.right.right = new TreeNode(4);
		troot.right.right.left = new TreeNode(5);

		troot1.left = new TreeNode(1);
		troot1.right = new TreeNode(2);
		//troot1.left.left = new TreeNode(3);
		troot1.right.right = new TreeNode(4);
		troot1.right.right.left = new TreeNode(5);
		*/
		
		//troot.right = new TreeNode(1);
		//troot.right.right = new TreeNode(2);
		//System.out.println("Tree is " + troot.InorderTreeWalk());
		/*
		List<Integer> output = troot.inorderTraversal();
		for (Integer i: output)
			System.out.print(i.toString()+',');
		//troot.InorderTreeWalk2();
		System.out.println();
		*/
		/*
		output = troot.postorderTraversal();
		for (Integer i: output)
			System.out.print(i.toString()+',');
		System.out.println();

		output = troot.postorderTraversal2();
		for (Integer i: output)
			System.out.print(i.toString()+',');
		System.out.println();
		*/
		

		//System.out.println("Height: " + troot.maxDepth(troot));
		//System.out.println("Min Depth: " + troot.minDepth2(troot));

		//System.out.println("AVL Balance:" + troot.isAVLBalanced(troot));
		//System.out.println("Balance:" + troot.isBalanced(troot));
		
		//System.out.println("Same is" + TreeNode.isSameTree(troot, troot1));
		
		/*
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		
		/*double median=0.0;
		if (sortlist.length % 2 == 0)
			median = (TeasingUtil.randomSelect(sortlist, 0, sortlist.length-1, sortlist.length/2) + TeasingUtil.randomSelect(sortlist, 0, sortlist.length-1, sortlist.length/2+1))/2.0;
		else
			median = TeasingUtil.randomSelect(sortlist, 0, sortlist.length-1, sortlist.length/2+1) * 1.0;
		
		(new Sort()).bubbleSort(sortlist);
		TeasingUtil.printList(sortlist);
		System.out.println("Median is " + median);
		*/
		/*Random rand = new Random();
		int r = rand.nextInt(20)+1;
		System.out.println("rotation times " + r);
		TeasingUtil.rotateArray(sortlist,r);
		TeasingUtil.printList(sortlist);
		*/
		//TeasingUtil.printListWithIndex(sortlist);	
		/*
		float[] fsortlist = TeasingUtil.genList(15);
		System.out.println("The float list size is " + fsortlist.length);
		System.out.print("The original float list is ");
		TeasingUtil.printList(fsortlist);
		*/
		
		//LeetCode leetcode = new LeetCode();
		//leetcode.printListNode(leetcode.sortList(null));
		//int B[] = {134,63,17,147};
		//ListNode listnode = leetcode.genList(sortlist);
		//leetcode.printListNode(listnode);
		//ListNode lnend = leetcode.seekEnd(listnode);
		//leetcode.printListNode(leetcode.partition(listnode, lnend));
		//leetcode.printListNode(leetcode.sortList2(listnode));
		/**
		 * For sorting
		 */
		//Sort.recInsertSort(sortlist);
		//Sort sort = new Sort();
		//sort.heapSort(sortlist, Sort.Order.ASCENT);
		//Sort.selectedSort(sortlist);
		//sort.mergeSort(sortlist, 0, sortlist.length-1);
		//sort.insertSort(sortlist);
		//sort.bubbleSort(sortlist);
		//sort.bucketSort(fsortlist);
		
		//int[] A ={120,123,120,113,120};
		//int[] A = {2,8,4,7,3,5,6,4};
		//System.out.println("The ascent sorted list is ");
		//sort.quickSort(sortlist, 0, sortlist.length-1);
		//TeasingUtil.printList(sortlist);
		//TeasingUtil.printList(fsortlist);
		
		//System.out.println(sort.partition(A, 0, A.length-1));
		//TeasingUtil.printList(A);
		//sort.quickSort(A, 0, A.length-1);
		//TeasingUtil.printList(sortlist);
		/**
		 * For Maximum SubArray
		 */
		//MaxSubArray msa = new MaxSubArray();
		//int[] reuslt = msa.findMaxSub(sortlist, 0, sortlist.length-1);
		//MaxSubArray.Subarray maxsubarray = msa.findMaxSubL(sortlist);
		
		/**
		 * Random Permutation
		 */
		/*
		TeasingUtil.permuteBySorting(sortlist, true);
		System.out.println("The permuted list is: ");
		TeasingUtil.printList(sortlist);
		*/
		
		
		/*
		sort.heapSort(sortlist, Sort.Order.DESECENT);
		System.out.print("The descent sorted list is ");
		TeasingUtil.printList(sortlist);
		*/
		//System.out.print(";" + String.valueOf(maxsubarray.low) + "," + String.valueOf(maxsubarray.high) + "," + String.valueOf(maxsubarray.sum) );
		
		
		 // For Heap
		 /**
		 */
		/*
		//int[] heaptree = {27, 17, 3, 16, 13, 10, 1, 5, 7, 12, 4, 8, 9,0};
		//int[] heaptree = {16, 14, 10, 8, 7, 9, 3, 2, 4, 1};
		//int[] heaptree = {15, 13, 9, 5, 12, 8, 7, 4, 0, 6, 2, 1};
		//int[] heaptree = {4,1,3,2,16,9,10,14,8,7};
		//int[] heaptree = {5, 3, 17, 10, 84, 19, 6, 22, 9};
		Heap heap = new Heap(heaptree);
		//heap.maxHeapify(heap, 0);
		heap.buildMaxHeap();
		System.out.print("Max Heap is:");
		TeasingUtil.printList(heap.getDataTree());
		System.out.println("Length is " + heap.getLength() + ". Heap Size is " + heap.getHeapSize());
		//System.out.println("Max is:" +heap.extractHeapMax());
		heap.insertMaxHeap(10);
		
		//heap = new Heap(heaptree);
		//heap.buildMinHeap();
		//heap.increaseKey(8, 15);
		System.out.print("New Heap is:");
		TeasingUtil.printList(heap.getDataTree(), heap.getHeapSize());
		System.out.println("Length is " + heap.getLength() + ". Heap Size is " + heap.getHeapSize());
		*/
		
		
	}

}
