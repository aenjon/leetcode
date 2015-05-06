package cn.arrowcoder.training;

public class Heap {

	
	private int HeapParent(int i) { return i/2; }
	private int HeapLeft(int i) { return 2*i; }
	private int HeapRight(int i) { return 2*i + 1; }
	
	public void maxHeapify(int[] A, int i){
		int l = HeapLeft(i);
		int r = HeapRight(i);
		int largest = i;
		if (l < A.length && A[i] < A[l])
			largest = l;
		else
			largest = i;
		if (r < A.length && A[largest] < A[r] )
			largest = r;
		if (largest != i ){
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A, largest);
		}
	}

}
