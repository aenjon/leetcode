package cn.arrowcoder.training;

import java.util.Arrays;

/**
 * Name 		: ArrowClassic.java
 * Description	: Classic data structures and algorithms 
 * Copyright
 * @author Jianqing Zhang <jianqing.g.zhang@gmail.com> 
 */

public class ArrowClassic {
	
	/**
	 * Insert Sort
	 * @param nums
	 * @return The times of operations
	 */
	public int insertSort(int nums[]){
		int count = 0;
		if (nums == null || nums.length == 0)
			return count;
		for (int j = 1; j < nums.length; ++j){
			int key = nums[j];
			int i = j-1;
			while (i >= 0 && nums[i] > key){
				nums[i+1] = nums[i];
				--i;
				count++;
			}
			nums[i+1] = key;
		}
		return count;
	}
	
	/**
	 * Merge Sort
	 * @param nums
	 * @return The times of operations
	 */
	public int mergeSort(int nums[]){
		if (nums == null || nums.length == 0)
			return 0;
		return mergesort_help(nums,0,nums.length-1);
	}
	
	private int mergesort_help(int nums[], int start, int end){
		if (start == end)
			return 0;
		int mid = (start + end)/2;
		int c1 = mergesort_help(nums,start,mid);
		int c2 = mergesort_help(nums,mid+1, end);
		int c3 = merge(nums, start, mid, end);
		return c1+c2+c3;
	}
	
	private int merge(int nums[], int start, int mid, int end){
		int count = 0;
		int temp[] = Arrays.copyOfRange(nums, mid+1, end+1);
		int i = mid;
		int j = temp.length-1;
		int k = end;
		while (i>=start && j >=0){
			++count;
			if (nums[i] > temp[j])
				nums[k--] = nums[i--];
			else
				nums[k--] = temp[j--];
		}
		while (j>=0){
			++count;
			nums[k--] = temp[j--];
		}
		return count;
	}
	
	static private int qsort_counter;
	public int quickSort(int nums[]){
		qsort_counter = 0;
		qsort_helper(nums, 0, nums.length-1);
		return qsort_counter;
	}
	
	private void qsort_helper(int nums[], int head, int tail){
		if (head < tail){
			int pindex = partition (nums, head, tail);
			qsort_helper(nums,head, pindex-1);
			qsort_helper(nums,pindex+1, tail);
		}
	}
	
	private int partition(int nums[], int head, int tail){
		int pivot = nums[tail];
		int pindex = head - 1;
		for (int i = head; i<=tail-1; ++i){
			if (nums[i] < pivot){
				++pindex;
				++qsort_counter;
				int temp = nums[pindex];
				nums[pindex] = nums[i];
				nums[i] = temp;
			}
		}
		++qsort_counter;
		int temp = nums[pindex+1];
		nums[pindex+1] = nums[tail];
		nums[tail] = temp;
		return pindex+1;
	}
	
	public int counterSort(int nums[], int max){
		int csort_ctr = 0;
		int[] nums_back = Arrays.copyOf(nums, nums.length);
		int nums_ctr[] = new int[max+1];
		// Initialize the counter array.
		for (int i=0; i<nums_ctr.length; ++i){
			csort_ctr++;
			nums_ctr[i] = 0;
		}
		for (int i=0; i<nums_back.length; ++i){
			nums_ctr[nums_back[i]]++;
			csort_ctr++;
		}
		for (int i=1; i<nums_ctr.length; ++i){
			nums_ctr[i] = nums_ctr[i] + nums_ctr[i-1];
			csort_ctr++;
		}
		//for (int i = nums.length-1; i>=0; --i){
		for (int i=0; i<nums.length; ++i){
			//System.out.println("i:" + i);
			//System.out.println("nums_back[i]:" + nums_back[i]);
			//System.out.println("nums_ctr[nums_back[i]]" + nums_ctr[nums_back[i]]);
			nums[nums_ctr[nums_back[i]]-1] = nums_back[i];
			nums_ctr[nums_back[i]]--;
			csort_ctr++;
		}
		return csort_ctr;
	}
	
}

