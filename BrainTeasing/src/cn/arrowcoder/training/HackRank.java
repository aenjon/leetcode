package cn.arrowcoder.training;

import java.util.*;

public class HackRank {

	/**
	 * @param args
	 */
	
	static int solveMeSecond(int a, int b){
		return a + b;
	}
	
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t, a, b, sum;
		t = in.nextInt();
		for(int i = 0; i < t; i++){
			a = in.nextInt();
			b = in.nextInt();
			sum = a + b;
			System.out.println(sum);
		}
	}

	
}
