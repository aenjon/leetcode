package cn.arrow.brainteasing;

import java.util.Random;

public class RecBinarySearch {

	public static void main(String[] args)
	{
		int[] sortlist = TeasingUtil.genList(30, 1000);

		System.out.println("The list size is " + sortlist.length);
		System.out.print("The original list is: ");		
		TeasingUtil.printList(sortlist);
		
		Random rand = new Random();
		int orikeyindex = rand.nextInt(sortlist.length);
		int key = sortlist[orikeyindex];
		
		System.out.println("The searched key is: " + sortlist[orikeyindex]);
		
		Sort sort = new Sort();
		sort.selectedSort(sortlist);
		
		System.out.print("The sorted list is: " );
		TeasingUtil.printListWithIndex(sortlist);

		int keyindex = recBinarySearch(sortlist,key);
		
		System.out.print("The key in sorted list is: " + keyindex);
		
	}
	
	public static int recBinarySearch(int[] A, int key)
	{
		if (A==null)
			return -1;
		int size = A.length;
		if (size == 0)
			return -1;

		if (size == 1 && key != A[0])
			return -1;

		if (size == 1 && key == A[0])
			return 0;
		
		int mid_index = size / 2 ;
		int t_index, key_index = -1;

		
		if ( key == A[mid_index-1])
			key_index = mid_index-1;
		
		if (key > A[mid_index-1])
		{
			int[] B = new int[size - mid_index];
			for (int i=0; i < size-mid_index; i++)
				B[i] = A[i + mid_index];
			t_index = recBinarySearch(B,key);
			if ( t_index == -1)
				key_index = -1;
			else
				key_index = mid_index + t_index;		
		}
		
		if (key < A[mid_index - 1])
		{
			int[] B = new int[mid_index];
			for (int i = 0; i<mid_index; i++)
				B[i] = A[i];
			t_index = recBinarySearch(B,key);
			if ( t_index == -1)
				key_index = -1;
			else
				key_index = t_index;		
			
		}
		
		return key_index;
	}
}
