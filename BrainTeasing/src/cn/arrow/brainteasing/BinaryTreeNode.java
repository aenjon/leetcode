package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class BinaryTreeNode {
	public int key;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
	BinaryTreeNode(int kval){
		key = kval;
		left = null;
		right = null;
	}
	
	
	public void init(int[] keys){
		int rootkey[] = TeasingUtil.randomSelect(keys, 0, keys.length-1, keys.length/2);
		int temp = keys[0];
		keys[0] = rootkey[1];
		keys[rootkey[0]] = temp;
		
		key = rootkey[1];

		for (int i=1;i<keys.length;++i)
			insertNode(this, new BinaryTreeNode(keys[i]));
	}
	
    public boolean isBalanced(TreeNode root) {
    	return true;
    }

    public List<Integer> postorderTraversal2(BinaryTreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
    	Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
    	st.push(root);
    	BinaryTreeNode pre = null;
    	while (!st.isEmpty()){
    		BinaryTreeNode cur = st.peek();    			
    		if (cur.left == null && cur.right == null){
    			output.add(cur.key);
    			st.pop();
    			pre = cur;
    			continue;
    		}
    		
    		if (pre!=null && (cur.left == pre || cur.right == pre )){
    			output.add(cur.key);
    			st.pop();
    			pre = cur;
    		}
    		else{
    			if (cur.right != null) st.push(cur.right);
    			if (cur.left != null) st.push(cur.left);
    		}
    	}
    	return output;
    }
    
    public List<Integer> postorderTraversal2() {
    	return postorderTraversal2(this);
    }
	
    public List<Integer> postorderTraversal(BinaryTreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
    	Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
    	HashMap<BinaryTreeNode, Boolean> hm = new HashMap<BinaryTreeNode, Boolean>();
    	st.push(root);
    	while (!st.isEmpty()){
    		BinaryTreeNode cur = st.pop();    			
    		if (cur.left == null && cur.right == null)
    			output.add(cur.key);
    		else if (hm.containsKey(cur))
    			output.add(cur.key);
    		else if (cur.left != null || cur.right != null){
    			st.push(cur);
    			hm.put(cur, true);
    			if (cur.right != null) st.push(cur.right);
    			if (cur.left != null) st.push(cur.left);
    		}
    	}
    	return output;
    }
    
    public List<Integer> postorderTraversal() {
    	return postorderTraversal(this);
    }
	
    public List<Integer> preorderTraversal(BinaryTreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
        Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
        BinaryTreeNode cur = root;
        st.push(cur);
        do{
        	cur = st.pop();
        	output.add(cur.key);
        	//System.out.print(cur.key + ",");
        	if (cur.right != null)
        		st.push(cur.right);
        	if (cur.left != null)
        		st.push(cur.left);
        }while(!st.isEmpty());
        return output;
    }
    
    public List<Integer> preorderTraversal() {
    	return preorderTraversal(this);
    }
	
    public List<Integer> inorderTraversal(BinaryTreeNode root) {
        List<Integer> output = new ArrayList<Integer>();    	
		if (root == null) return output;
		Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
		BinaryTreeNode cur = root;
		do{
			while (cur!=null){
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			output.add(cur.key);
			cur = cur.right;
		}while(!st.isEmpty() || cur != null);
		return output;        
    }
    
    public List<Integer> inorderTraversal() {
    	return inorderTraversal(this);
    }
    
    /*
	public void InorderTreeWalk2(BinaryTreeNode root){
		if (root == null) return;
		Stack<BinaryTreeNode> st = new Stack<BinaryTreeNode>();
		BinaryTreeNode cur = root;
		do{
			while (cur!=null){
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			System.out.print(cur.key + ",");
			cur = cur.right;
		}while(!st.isEmpty() || cur != null);
		
	}
	
	public void InorderTreeWalk2(){
		InorderTreeWalk2(this);
	}
	*/
    
	public void InorderTreeWalk(BinaryTreeNode root){
		
		if (root == null) //return "";
			return;
		/*
		String result;
		result = InorderTreeWalk(root.left) + ",";
		result += root.key + ",";
		result += InorderTreeWalk(root.right) + ",";
		return result;
		*/
		
		InorderTreeWalk(root.left);
		System.out.print(root.key + ",");
		InorderTreeWalk(root.right);
		
	}
	
	public void InorderTreeWalk(){
		InorderTreeWalk(this);
	}

	public BinaryTreeNode searchBinaryTree(BinaryTreeNode root, int kval){
		BinaryTreeNode cur = root;
		while (cur!=null && cur.key != kval){
			if (cur.key < kval)
				cur = cur.right;
			else
				cur = cur.left;
		}
		return cur;
	}
	
	public BinaryTreeNode searchBinaryTree(int kval){
		return searchBinaryTree(this, kval);
	}
	
	
	public BinaryTreeNode maxTreeNode(BinaryTreeNode root){
		BinaryTreeNode cur = root;
		while (cur != null && cur.right != null)
			cur = cur.right;
		return cur;
	}

	public BinaryTreeNode maxTreeNode(){
		return maxTreeNode(this);
	}
	
	public BinaryTreeNode minTreeNode(BinaryTreeNode root){
		BinaryTreeNode cur = root;
		while (cur != null && cur.left != null)
			cur = cur.left;
		return cur;
	}
	
	public BinaryTreeNode minTreeNode(){
		return minTreeNode(this);
	}
	
	public BinaryTreeNode insertNode(BinaryTreeNode root, BinaryTreeNode x){
		BinaryTreeNode cur = root, cur_parent = root;
		while(cur!=null){
			cur_parent = cur;
			if (cur.key < x.key)
				cur = cur.right;
			else
				cur = cur.left;
		}
		if (cur_parent == null) return x;
		if (cur_parent.key < x.key)
			cur_parent.right = x;
		else
			cur_parent.left = x;
		return root;
	}
}
