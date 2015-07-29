package cn.arrow.brainteasing;
import java.util.Hashtable;
import java.util.Random;
import java.util.List;


public class TeasingUtil
{
	public static void printMatrix(int[][] matrix){
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[0].length; j++){
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printListNode(ListNode head){

        System.out.print("[");
        while(head != null){
            System.out.print(head.val + ",");
            head = head.next;
        }
        System.out.println("]");
	}
	/*
	public static void printListString(List<String> list){
		if (list == null) return;
		int i = 0;
		for (String s : list){			
			System.out.print(i++ + "-" + s + ",");
		}
		System.out.println();		
	}*/
	
	public static void printStringList(List<String> strlist){
		if (strlist == null || strlist.size() == 0) return;
		System.out.println("[");
		for (String str : strlist){
			System.out.println("\"" + str + "\", ");
		}
		System.out.println("]");		
	}

	public static void printSudoku(char[][] board){
		for (int i=0; i<board.length; ++i){
			for (int j=0; j<board[0].length; ++j)
				System.out.print(board[i][j]);
			System.out.println();
		}			
	}

	public static void printZigZag(char[][] board){
		for (int i=0; i<board.length; ++i){
			for (int j=0; j<board[0].length; ++j)
				if (board[i][j] == 0)
					System.out.print(' ');
				else
					System.out.print(board[i][j]);
			System.out.println();
		}			
	}
	/*
	public static void printListInteger(List<Integer> list){
		if (list == null) return;
		for (Integer a : list){
			System.out.print(a.intValue() + ",");
		}
		System.out.println();
	}*/
	
	public static void printStringArrayList(List<String[]> slist){
		if (slist == null) return;
		System.out.println("[");
		for (String[] strings : slist){
			System.out.println("  [");
			for (int i=0; i<strings.length; ++i)
				System.out.println("   " + strings[i]);
			System.out.println("  ]");
		}
		System.out.println("]");
	}
	public static void printListList(List<List<Integer>> pset){
		if (pset == null) return;
		System.out.println("[");
		for (List<Integer> set : pset){
			System.out.print("[");
			for (Integer e : set)
				System.out.print(e + ",");
			System.out.println("]");
		}
		System.out.println("]");
	}
	
	public static int[] randomSelect(int[] A, int start, int end, int ith){
		int[] index_value = new int[2];
		if (start==end){
			index_value[0] = start;
			index_value[1] = A[start];
			return index_value;
		}
		int pindex = TeasingUtil.randomPatition(A, start, end);
		int kth = pindex - start + 1;
		if (ith==kth){
			index_value[0] = pindex;
			index_value[1] = A[pindex];
			return index_value;
		}
		else if (ith < kth)
			return TeasingUtil.randomSelect(A,start,pindex-1, ith);
		else
			return TeasingUtil.randomSelect(A,pindex+1,end, ith-kth);
	}
	
	/**
	 * Borrowed from random pick for linked list. Could be simpler
	 * @param start
	 * @param end
	 * @return
	 */
	public static int randomPick(int start, int end){
		int index = start;
		Random rand = new Random();
		double max = rand.nextDouble();
		
		for(int i=start; i<=end; ++i){
			double cur_rand = rand.nextDouble();
			index = max > cur_rand ? index : i;
			max = max > cur_rand ? max : cur_rand;
		}
		return index;
	}
	
	public static int partition(int[] A, int start, int end){
		int i = start - 1;
		int pivot = A[end];
		int temp;
		for (int j = start; j<end; ++j){
			if (A[j] <= pivot){
				++i;
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		temp = A[i+1];
		A[i+1] = A[end];
		A[end] = temp;
		return i+1;
	}
	
	public static int randomPatition(int[] A, int start, int end){
		int pindex = randomPick(start, end);
		int temp = A[end];
		A[end] = A[pindex];
		A[pindex] = temp;
		return TeasingUtil.partition(A,start,end);
	}
	
	public static void rotateArrayII(int[] A, int shifts){
		if (A==null || A.length <=1) return;
		int len = A.length;
		int r = shifts % len;
		if (r==0) return;
		int counter = 0, cur = r, shiftindex = 0, pre = A[0];
		while (counter < len){
			int tmp = A[cur];
			A[cur]= pre;
			pre = tmp;
			counter++;
			if(cur==shiftindex){
				pre = A[(cur+1)%len];
				cur = (cur+1) % len;
				shiftindex = (shiftindex+1) % len;
			}
			else
				cur = (cur+r) % len;
		}
	}
	
	public static void rotateArray(int[] A, int round){
		int len = A.length;
		int v = round % len;
		int[] tmp = new int[len*2];
		for(int i = 0; i< len; ++i){
			tmp[i] = A[i];
			tmp[i+len] = A[i];
		}
		for(int i = 0; i<len; ++i)
			A[i] = tmp[i+len-v];
	}
	
	public static <T> void printList(List<T> list){
		if (list == null) return;
		System.out.print("[ ");
		for(T e : list)
			System.out.print(e + ", ");
		System.out.println("]");
	}
	
	
	public static void printList(int[] list)
	{
		int size = list.length;
		for (int i=0;i<size;i++)
			if (i < size - 1)
				System.out.print(list[i] + ", ");
			else
				System.out.println(list[i]);
	}

	public static void printList(ListNode head){
		TeasingUtil.printListNode(head);
	}
	
	public static void printList(float[] list)
	{
		int size = list.length;
		for (int i=0;i<size;i++)
			System.out.println(list[i]);
	}
	
	public static void printList(int[] list, int len)
	{
		//int size = list.length;
		if (len > list.length){
			System.out.println("Error:OutOfArrayBound");
			return;
		}
			
		for (int i=0;i<len;i++)
			if (i < len - 1)
				System.out.print(list[i] + ", ");
			else
				System.out.println(list[i]);
	}

	public static void printListWithIndex(int[] list)
	{
		int size = list.length;
		for (int i=0;i<size;i++)
			if (i < size - 1)
				System.out.print(list[i] + "(" + i +"), ");
			else
				System.out.println(list[i] + "(" + i + ")");
	}
	
	/**
	 * Randomly generate an integer list
	 * @param size The range of the list size
	 * @param range The range of the value of integer elements
	 * @return The generated list
	 */
	public static int[] genList(int size, int range)
	{
		Random rand = new Random();
		int[] generatedlist = new int[rand.nextInt(size) + 1];		
		for (int i = 0; i < generatedlist.length; i++)
			generatedlist[i] = rand.nextInt(range);
		return generatedlist;
	}

	public static int[] genListfull(int size, int range)
	{
		Random rand = new Random();
		int[] generatedlist = new int[rand.nextInt(size) + 1];		
		for (int i = 0; i < generatedlist.length; i++)
			generatedlist[i] = rand.nextInt(2*range)-range;
		return generatedlist;
	}
	
	public static float[] genList(int size)
	{
		Random rand = new Random();
		float[] generatedlist = new float[rand.nextInt(size) + 1];		
		for (int i = 0; i < generatedlist.length; i++)
			generatedlist[i] = rand.nextFloat();
		return generatedlist;
	}

	
	public static int[] genList(int size, int max, int min){
		Random rand = new Random();
		int[] generatedlist = new int[rand.nextInt(size) + 1];		
		for (int i = 0; i < generatedlist.length; i++)
			generatedlist[i] = rand.nextInt(max-min) + 1 + min;
		return generatedlist;
	}
	
	public static boolean isPowerofTwo(int v)
	
	{
		boolean result = false;
		
		if ( v <= 0)
			return false;
		else if ( v == 1)
			return true;
		else
			while(v/2 != 0){
				if (v%2 == 1)
				{
					result = false;
					break;
				}
				v = v / 2;
			}
		return result;
	}
	
	
	// TODO Handle out of boundary of int * int * int
	// TODO Get rid of duplicated priorities
	public static void permuteBySorting(int[] A, boolean isTest){
		int size = A.length;
		int [] P = new int[A.length];
		Hashtable<Integer, Integer> temp = new Hashtable<Integer, Integer>();
		
		Random ran = new Random();
		int max = size * size * size;
		for (int i=0; i<size; i++){
			P[i] = ran.nextInt(max);
			temp.put(P[i], A[i]);
		}
		
		Sort sort = new Sort();
		
		if (isTest){
			System.out.println("The original priority list is: ");
			TeasingUtil.printList(P);
		}
		
		sort.mergeSort(P, 0, P.length-1);

		if (isTest){

			System.out.println("The sorted priority list is: ");
			TeasingUtil.printList(P);
		}
		
		for(int i=0;i<size; i++){
			A[i] = temp.get(P[i]);
		}
	
	}
	
	public static void printChars(char[] chars){
		if (chars == null)
			return;
		for (int i=0; i< chars.length; ++i){
			if (chars[i] != 0)
				System.out.print(chars[i]);
			else
				break;
		}
		System.out.println();
	}
	
}
