package cn.arrowcoder.training;
/**
 * Name 		: ArrowClassic.java
 * Description	: Classic data structures and algorithms 
 * Copyright
 * @author Jianqing Zhang <jianqing.g.zhang@gmail.com> 
 */

public class ArrowClassic {
	
	void insertSort(int nums[]){
		if (nums == null || nums.length == 0)
			return;
		for (int j = 1; j < nums.length; ++j){
			int key = nums[j];
			int i = j-1;
			while (i >= 0 && nums[i] > key){
				nums[i+1] = nums[i];
				--i;
			}
			nums[i+1] = key;
		}
	}
}
