package cn.arrow.careercup;

import java.util.Stack;

public class Tower {

	private Stack<Integer> disks;
	private int index;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 5; // 5 disks
		Tower[] towers = new Tower[3];
		for (int i=0; i< 3; i++)
			towers[i] = new Tower(i);
		for (int i=n-1; i>=0; i--)
			towers[0].add(i);
		
		towers[0].print();
		towers[0].moveDisks(n, towers[2], towers[1]);
		towers[0].print();
		towers[2].print();
	}

	public Tower(int i){
		disks = new Stack<Integer>();
		index = i;
	}
	
	public int index() { return index;}
	
	public void add(int d){
		if (!disks.isEmpty() && d > disks.peek())
			System.out.println("Error");
		else
			disks.push(d);
	}
	
	public void moveTopTo(Tower t){
		int top = disks.pop();
		t.add(top);		
		System.out.println("Move " + top + " from Tower " + index() + " to Tower " + t.index());
		
	}
	
	public void moveDisks(int n, Tower dest, Tower buff){
		if (n>0){
			moveDisks(n-1, buff, dest);
			moveTopTo(dest);
			buff.moveDisks(n-1, dest, this);
		}
	}
	
	public void print(){
		System.out.println("Tower " + index() + ":");
		
		for (int i = disks.size() -1; i>=0; i--)
			System.out.print(" " + disks.get(i));
		System.out.println();	
	}
}
