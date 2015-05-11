package cn.arrowcoder.training;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class BSTree {
	public int val;
	public BSTree left;
	public BSTree right;
	public BSTree parent;
	
	public BSTree(){
		val = 0;
		left = null;
		right = null;
		parent = null;
	}
	
	public BSTree(int key){
		val = key;
		left = null;
		right = null;
		parent = null;
	}
	
	public void inOrder(){
		inOrder(this);
		System.out.println();
	}
	
	public void inOrder(BSTree t){
		if (t != null){
			inOrder(t.left);
			System.out.print(t.val + ",");
			inOrder(t.right);
		}
	}
	
	public void inOrderNR(){
		inOrderNR(this, null);
		System.out.println();
	}
	
	public void inOrderNR(List<Integer> ret){
		inOrderNR(this, ret);
	}
	
	public void inOrderNR(BSTree t, List<Integer> ret){
		if (t == null) return ;
		Stack<BSTree> st = new Stack<BSTree>();
		HashSet<BSTree> hs = new HashSet<BSTree>();
		st.push(t);
		//hs.add(t);
		while (!st.isEmpty()){
			BSTree cur = st.pop();
			if (hs.contains(cur)){
				if (ret != null)
					ret.add(cur.val);
				else
					System.out.print(cur.val + ",");
				hs.remove(cur);
				continue;
			}
			if (cur.right != null)
				st.push(cur.right);
			st.push(cur);
			hs.add(cur);
			if (cur.left != null)
				st.push(cur.left);
		}
	}
	
	public void preOrderNR(){
		preOrderNR(this, null);
		System.out.println();
	}
	
	public void preOrderNR(List<Integer> ret){
		preOrderNR(this, ret);
	}
	
	public void preOrderNR(BSTree t, List<Integer> ret){
		if (t==null) return;
		Stack<BSTree> st = new Stack<BSTree>();
		st.push(t);
		while(!st.isEmpty()){
			BSTree cur = st.pop();
			if (ret != null)
				ret.add(cur.val);
			else
				System.out.print(cur.val + ",");
			if (cur.right != null)
				st.push(cur.right);
			if (cur.left != null)
				st.push(cur.left);
		}
	}
	
	public void postOrderNR(){
		postOrderNR(this, null);
		System.out.println();
	}
	
	public void postOrderND(List<Integer> ret){
		postOrderNR(this, ret);
	}
	
	public void postOrderNR(BSTree t, List<Integer> ret){
		if (t==null) return ;
		Stack<BSTree> st = new Stack<BSTree>();
		HashSet<BSTree> hs = new HashSet<BSTree>();
		st.push(t);
		while(!st.isEmpty()){
			BSTree cur = st.pop();
			if (hs.contains(cur)){
				if (ret != null)
					ret.add(cur.val);
				else
					System.out.print(cur.val + ",");
				continue;
			}
			st.push(cur);
			hs.add(cur);
			if (cur.right != null)
				st.push(cur.right);
			if (cur.left != null)
				st.push(cur.left);
			
		}
	}
	
	public void inOrder(List<Integer> ret){
		inOrder(this, ret);
	}
	
	public void inOrder(BSTree t, List<Integer> ret){
		if (t!=null){
			inOrder(t.left, ret);
			ret.add(t.val);
			inOrder(t.right, ret);
		}
	}
	
	public BSTree search(int key){
		return search(this, key);
	}
	
	public BSTree search(BSTree t, int key){
		if (t == null || t.val == key)
			return t;
		if (t.val > key)
			return search(t.left, key);
		else
			return search(t.right, key);
		//return null;
	}
	
	public BSTree min(){
		return min(this);
	}
	
	public BSTree min(BSTree t){
		while ( t.left != null)
			t = t.left;
		return t;
	}
	
	public BSTree max(){
		return max(this);
	}
	
	public BSTree max(BSTree t){
		while (t.right != null)
			t = t.right;
		return t;
	}
	
	public BSTree succ_in(BSTree x){
		if (x == null) return null;
		if (x.right != null){
			BSTree s = x.right;
			while (s.left != null)
				s = s.left;
			return s;
		}
		BSTree y = x.parent;
		while ( y != null && y.right == x){
			x = y;
			y = y.parent;
		}
		return y;
	}
	
	public void insert(BSTree x){
		insert(this, x);
	}
	
	public BSTree insert(BSTree root, BSTree x){
		BSTree y = null;
		BSTree r = root;
		while ( r != null){
			y = r;
			if (x.val < r.val)
				r = r.left;
			else
				r = r.right;
		}
		x.parent = y;
		if (y == null)
			return x;
		else if ( x.val < y.val)
			y.left = x;
		else
			y.right = x;
		return root;
	}
}
