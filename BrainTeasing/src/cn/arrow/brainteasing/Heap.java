package cn.arrow.brainteasing;

public class Heap {
	private int length;
	private int heapsize;
	private int[] tree;
	private Type type;
	
	public enum Type{
		MAXHEAP, MINHEAP, UNKNOWN
	}
	
	// TODO Distinguish length and heapsize 
	
	public Heap(){
		tree = TeasingUtil.genList(32, 100);
		length = tree.length;
		heapsize = tree.length;
		type = Type.UNKNOWN;
	}
	
	public Heap(int hsize, int datarange){
		tree = TeasingUtil.genList(hsize, datarange);
		length = tree.length;
		heapsize = tree.length;
		type = Type.UNKNOWN;
	}
	
	public Heap(int[] data){
		//tree = new int[data.length];
		//System.arraycopy(data, 0,tree, 0, data.length);
		tree = data;
		length = tree.length;
		heapsize = tree.length;
		type = Type.UNKNOWN;
		
	}
	
	public Type getType(){
		return type;
	}
	
	public int getLength(){
		return length;
	}
	
	public void setLength(int len){
		length = len;
	}
	
	public int getHeapSize() {
		return heapsize;
	}
	
	public void setHeapSize(int hsize){
		heapsize = hsize;
	}	
	
	public int[] getDataTree(){
		return tree;
	}
	
	// TODO Max- and Min- Heapify might be converted and optimized
	public void maxHeapify(int i){
		int largest, temp;
		
		while(true){
			int left = leftChild(i);
			int right = rightChild(i);

			if (left < heapsize && tree[left] > tree[i]){
				largest = left;
			} else {
				largest = i;
			}
			
			if (right < heapsize && tree[right] > tree[largest]){
				largest = right;
			}
			
			if (largest == i)
				return;
			
			temp = tree[i];
			tree[i] = tree[largest];
			tree[largest] = temp;
			
			i = largest;			
		}
	}

	public void minHeapify(int i){
		int smallest, temp;
		while(true){
			int left = leftChild(i);
			int right = rightChild(i);

			if (left < heapsize && tree[left] < tree[i]){
				smallest = left;
			} else {
				smallest = i;
			}
			
			if (right < heapsize && tree[right] < tree[smallest]){
				smallest = right;
			}
			
			if (smallest == i)
				return;
			
			temp = tree[i];
			tree[i] = tree[smallest];
			tree[smallest] = temp;
			
			i = smallest;			
		}
	}
	
	public void buildMaxHeap(){
		for (int i = heapsize/2; i >=0; i--){
			maxHeapify(i);
		}
		type = Type.MAXHEAP;
	}

	public void buildMinHeap(){
		for (int i =heapsize/2; i >=0; i--){
			minHeapify(i);
		}
		type = Type.MINHEAP;
	}
	
	public int extractHeapMax(){
		if ( heapsize < 1 || type != Type.MAXHEAP)
			return -1;
		int max = tree[0];
		tree[0] = tree[heapsize-1];
		heapsize--;
		maxHeapify(0);
		return max;
	}
	
	public int extractHeapMin(){
		if (heapsize < 1 || type != Type.MINHEAP)
			return -1;
		
		int max = tree[0];
		tree[0] = tree[heapsize-1];
		heapsize--;
		this.minHeapify(0);
		return max;
	}
	
	// TODO Implement decreaseKey

	public void increaseKey(int i, int key){
		if ( key <= tree[i])
			return;
		
		tree[i] = key;
		int temp;
		
		switch (type){
			case MAXHEAP:
				while (i>0 && tree[Parent(i)] < key){
					
					/* Revise the code according to Exercise 6.5-6*/
					/*
					temp = tree[Parent(i)];
					tree[Parent(i)] = tree[i];
					tree[i] = temp;
					*/
					tree[i] = tree[Parent(i)];
					i = Parent(i);
				}
				tree[i] = key;
				break;
			case MINHEAP:
				while (i>0 && tree[Parent(i)] > tree[i]){
					temp = tree[Parent(i)];
					tree[Parent(i)] = tree[i];
					tree[i] = temp;
					i = Parent(i);
				}
				break;
			case UNKNOWN:
				return;
		}	
	}
	
	public void insertMaxHeap(int key){
		int[] newtree = new int[heapsize+1];
		if (length < heapsize)
			length = heapsize;
		System.arraycopy(tree, 0, newtree, 0, tree.length);
		newtree[newtree.length-1] = Integer.MIN_VALUE;
		tree = newtree;
		heapsize = tree.length;
		if (length < heapsize)
			length = heapsize;

		increaseKey(heapsize-1, key);
	}
	
	public void deleteMaxHeap(int i){
		tree[i] = tree[heapsize-1];
		heapsize--;
		this.maxHeapify(i);
	}
	
	public void deleteMinHeap(int i){
		tree[i] = tree[heapsize-1];
		heapsize--;
		minHeapify(i);
	}
	
	private int leftChild(int i){
		return 2*i + 1;
	}
	
	private int rightChild(int i){
		return 2*i + 2;
	}
	
	private int Parent(int i){
		return (i-1)/2;
	}
	
}
