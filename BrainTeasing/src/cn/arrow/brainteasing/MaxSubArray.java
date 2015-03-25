package cn.arrow.brainteasing;



public class MaxSubArray {
	
	public class Subarray {
		int low; int high; int sum;
	}
	
	public Subarray findMaxSubL(int[] A)
	{
		Subarray[] maxarray = new Subarray[A.length];
		Subarray  max;
		
		maxarray[0] = new Subarray();
		
		maxarray[0].low = 0; maxarray[0].high = 0; maxarray[0].sum = A[0];
		max = maxarray[0];
		
		for (int i = 1; i < A.length; i++){
			maxarray[i] = new Subarray();
			if (maxarray[i-1].sum < 0){
				maxarray[i].low = i;
				maxarray[i].high = i;
				maxarray[i].sum = A[i];
			}
			else{
				maxarray[i].low = maxarray[i-1].low;
				maxarray[i].high = i;
				maxarray[i].sum = maxarray[i-1].sum + A[i];
			}
		}

		for (int i =1; i < A.length; i++)
			if (max.sum < maxarray[i].sum)
				max = maxarray[i];
		
		return max;
		
	}

	public int[] findMaxSub(int[] A, int low, int high)
	{
		int[] result;
		int mid;
		
		if (low == high)
		{
			result = new int[3];
			result[0] = low; result[1] = high; result[2] = A[low];
		}
		else
		{
			mid = (low + high) / 2;
			int[] left = this.findMaxSub(A, low, mid);
			int[] right = this.findMaxSub(A, mid+1, high);
			int[] cross = this.findMaxCrossSub(A, low, mid, high);
			
			if (left[2] >= right[2] && left[2] >= cross[2])
				result = left;
			else if (right[2] >= left[2] && right[2] >= cross[2])
				result = right;
			else
				result = cross;
		}
		
		return result;
	}
	
	private int[] findMaxCrossSub(int[] A, int low, int mid, int high)
	{
		int leftsum = Integer.MIN_VALUE;
		int rightsum = Integer.MIN_VALUE;
		int sum = 0;
		
		int maxleft = mid;
		int maxright = mid+1;
		
		for (int i = mid; i>=low; i--)
		{
			sum += A[i];
			if (sum > leftsum)
			{
				leftsum = sum;
				maxleft = i;
			}
		}
		
		sum = 0;
		for (int i = mid+1; i<= high; i++)
		{
			sum += A[i];
			if (sum > rightsum)
			{
				rightsum = sum ;
				maxright = i;
			}
		}
		
		int[] result = new int[3];
		result[0] = maxleft;
		result[1] = maxright;
		result[2] = leftsum + rightsum;
		return result;
		
	}
}
