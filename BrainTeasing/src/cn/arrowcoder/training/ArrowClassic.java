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
}
