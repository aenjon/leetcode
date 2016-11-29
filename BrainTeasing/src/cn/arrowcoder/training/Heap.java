package cn.arrowcoder.training;

import cn.arrow.brainteasing.TeasingUtil;

public class Heap {

	private int[] heap;
	public int heap_size;
	
	public Heap() {}
	
	public Heap(int[] A){
		heap = A;
		heap_size = A.length;
	}
	
	//private int HeapParent(int i) { return i/2; }
	private int HeapLeft(int i) { return 2*i; }
	private int HeapRight(int i) { return 2*i + 1; }
	
	public void maxHeapify(){
		if (heap != null)
			maxHeapify(heap, 0);
	}
	
	public void maxHeapify(int i){
		maxHeapify(heap, i);
	}
	
	public void maxHeapify(int[] A, int i){
		int l = HeapLeft(i);
		int r = HeapRight(i);
		int largest = i;
		if (l < heap_size && A[i] < A[l])
			largest = l;
		else
			largest = i;
		if (r < heap_size && A[largest] < A[r] )
			largest = r;
		if (largest != i ){
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			maxHeapify(A, largest);
		}
	}
	
	public void buildMaxHeap(){
		buildMaxHeap(heap);
	}
	
	public void buildMaxHeap(int[] A){
		for(int i=heap_size/2; i>=0; --i)
			maxHeapify(A, i);
	}
	
	public void show(){
		if (heap != null)
			TeasingUtil.printList(heap);
		else
			System.out.println("The heap is empty.");
	}
	
	static public void heapSort(int[] A){
		Heap tmpHeap = new Heap(A);
		tmpHeap.buildMaxHeap();
		for (int i = A.length - 1; i >= 1; --i){
			int temp = A[i];
			A[i] = A[0];
			A[0] = temp;
			tmpHeap.heap_size--;
			tmpHeap.maxHeapify();
		}
		tmpHeap.heap_size = A.length;
	}

}
