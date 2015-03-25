package cn.arrow.brainteasing;

public class Sort {

	/**
	 * Sort a integer list using recursive insert sort
	 * @param A The integer list to be sorted
	 */
	
	public enum Order{
		ASCENT, DESECENT
	}
	
	public void bucketSort(float[] A){
		if (A == null || A.length <=1) return;
		ListNodeF[] B = new ListNodeF[10];
		for (int i=0; i<B.length; ++i)
			B[i] = null;
		
		for (int i = 0; i<A.length; ++i)
			insertBucket(B,A[i]);
		
		int j = 0;
		for (int i=0; i<B.length; ++i){
			ListNodeF b = B[i];
			while (b!=null){
				A[j++] = b.val;
				b = b.next;
			}
		}
	}
	
	private void insertBucket(ListNodeF[] C, float x){
		int index = Math.round(x);
		ListNodeF nodex = new ListNodeF(x); 
		if (C[index] == null)
			C[index] = nodex;
		else
			C[index] = insertBucketNode(C[index],nodex);
		return;
	}
	
	private ListNodeF insertBucketNode(ListNodeF head, ListNodeF nodex){
		if (nodex.val < head.val){
			nodex.next = head;
			return nodex;
		}
		
		ListNodeF cur = head.next;
		ListNodeF pre_cur = head;
		boolean inserted = false;
		while(cur != null){
			if (nodex.val<cur.val){
				pre_cur.next = nodex;
				nodex.next = cur;
				inserted = true;
			}
			if(inserted)
				break;
			pre_cur = cur;
			cur = cur.next;
		}
		if (!inserted)
			pre_cur.next = nodex;
		return head;
	}
	
	public void bubbleSort(int[] A){
		if(A==null || A.length <= 1) return;
		
		for (int i=0; i<A.length-1; ++i)
			for(int j = i+1; j<A.length; ++j)
				if (A[i] > A[j]){
					int temp = A[i];
					A[i] = A[j];
					A[j] = temp;
				}
	}
	
	public void quickSort(int[] A, int start, int end){
		if (start < end){
			int q = partition(A,start,end);
			quickSort(A,start,q-1);
			quickSort(A,q+1,end);
		}
	}
	
	public int partition(int[] A, int start, int end){
		if (A == null) return -1;
		
		int equal = 0;
		
		int x = A[end];
		
		int i = start - 1;
		int temp;
		for (int j = start; j < end; ++j){
			
			if (A[j] == x)
				++equal;
			if (A[j] <= x){
				++i;
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
			}
		}
		temp = A[i+1];
		A[i+1] = A[end];
		A[end] = temp;
		
		if (equal == (end-start))
			return i+1-equal/2;
		else
			return i+1;
	}

	public void insertSort(int[] A){
		if (A==null) return;
		
		for(int j = 1; j<A.length; ++j){
			int key = A[j];
			int i = j-1;
			while (i>=0 && key<A[i]){
				A[i+1] = A[i];
				--i;
			}
			A[i+1] = key;
		}
	}
	
	public void recInsertSort(int[] A)
	{
		int size = A.length;
		if (size == 1)
			return;
		int[] B = new int[size-1];
		
		// Copy the list except the last element
		// O(n)
		for (int i=0; i< size-1; i++) 
			B[i] = A[i];

		// Sort the sub list
		// It will be called O(n) times
		recInsertSort(B);
		
		// Copy the sorted sublist to the original list
		// O(n)
		for (int i=0; i< size-1; i++)
			A[i] = B[i];
		
		
		int i = size-2;
		int key = A[size-1];
		
		// Find the position for the last element
		// O(n)
		while (i >= 0 && A[i] > key)
		{
			A[i+1] = A[i];
			i--;
		}
		A[i+1] = key;
	}

	/**
	 * Sort an integer list using  selected sort
	 * @param A The integer list to be sorted
	 */
	public void selectedSort(int[] A){
		
		if ( A == null)
			return;

		int key, index;
		int size = A.length;
		
		for (int i = 0; i < size-1; i++)
		{
			key = A[i];
			index = i;
			for (int j = i+1; j<size; j++)
			{
				// Find the smallest element in the rest of list
				// Remember the index
				if (A[j] < key)
				{
					key = A[j];
					index = j;
				}
			}
			if (index != i)
			{
				// If the first element is not the smallest one, exchange it with the smallest one in the remaining list
				A[index] = A[i];
				A[i] = key;
			}
		}
	}
	
	public void mergeSort(int[] A, int start, int end)
	{
		int mid;
		if (start < end)
		{
			mid = (start + end) / 2;
			mergeSort(A, start, mid);
			mergeSort(A, mid+1, end);
			merge(A, start, mid, end);
		}
	}
	
	public  void merge(int[] A, int start, int mid, int end)
	{
		int n1 = mid-start+1;
		int n2 = end - mid;
		int[] L = new int[n1];
		int[] R = new int[n2];
		
		for (int i = 0; i < n1; i++)
			L[i] = A[start+i];
		for (int i=0; i< n2; i++)
			R[i] = A[mid+i+1];
		
		int i = 0;
		int j = 0;
		
		for (int k = start; k < end; k++)
		{
			if (L[i] <= R[j])
			{
				A[k] = L[i];
				if (i == n1-1){
					copy(A, k+1, R, j);
					break;
				}
				i++;
			}
			else
			{
				A[k] = R[j];
				if (j == n2 - 1){
					copy(A, k+1, L, i);
					break;
				}
				j++;
			}
		}
	}
	
	public void heapSort(int[] A, Order order){
		Heap heap = new Heap(A);
		int temp;
		int[] heaptree;
		
		if (order == Order.ASCENT)
			heap.buildMaxHeap();
		else if (order == Order.DESECENT)
			heap.buildMinHeap();
		else
			return;
		
		for (int i = heap.getLength() - 1; i>0; i--){
			heaptree = heap.getDataTree();
			temp = heaptree[0];
			heaptree[0] = heaptree[i];
			heaptree[i] = temp;
			heap.setHeapSize(heap.getHeapSize()-1);
			
			if (order == Order.ASCENT)
				heap.maxHeapify(0);
			else if(order == Order.DESECENT)
				heap.minHeapify(0);
		}
		//return heap.getDataTree();
	}
	
	private void copy(int[] M, int m, int[] N, int n){
		int size = N.length;
		for (int i = 0; i< size -n; i++)
			M[m+i] = N[n+i];
	}
}
