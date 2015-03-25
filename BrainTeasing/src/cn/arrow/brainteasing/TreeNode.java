package cn.arrow.brainteasing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;
	public TreeNode parent;
	
	TreeNode(int kval){
		val = kval;
		left = null;
		right = null;
		parent = null;
	}
	
	
	public void init(int[] keys){
		int rootkey[] = TeasingUtil.randomSelect(keys, 0, keys.length-1, keys.length/2);
		int temp = keys[0];
		keys[0] = rootkey[1];
		keys[rootkey[0]] = temp;
		
		val = rootkey[1];

		for (int i=1;i<keys.length;++i)
			insertNode(this, new TreeNode(keys[i]));
	}
	
    public static TreeNode sortedListToBST(ListNode head) {
    	if (head == null) return null;
    	ListNode mid = head, tail = head;
    	int hops = 0, midhops = 0;
    	while (tail.next!=null){
    		mid = mid.next;
    		tail = tail.next;
    		++midhops;
    		++hops;
    		if (tail.next != null){
    			tail = tail.next;
    			++hops;
    		}
    		else
    			break;
    	}
        TreeNode root = new TreeNode(mid.val);
        insertListNode(root,head,mid,midhops,hops);
        return root;
    }
    
    private static void insertListNode(TreeNode root, ListNode head, ListNode mid, int midhops, int hops){
    	//root.val = mid.val;
    	if (midhops>1){
        	int lefthops = (midhops-1)/2;
        	ListNode leftmid = move(head,lefthops);
    		root.left = new TreeNode(leftmid.val);
       		insertListNode(root.left,head,leftmid,lefthops,midhops-1);
    	}else if (midhops == 1)
    		root.left = new TreeNode(head.val);

    	if (hops-midhops>1){
    		int righthops = (hops-midhops-1)/2;
    		ListNode rightmid = move(mid.next,righthops);
    		root.right = new TreeNode(rightmid.val);
    		insertListNode(root.right,mid.next, rightmid,righthops,hops-midhops-1);
    	}else if (hops-midhops == 1)
    		root.right = new TreeNode(mid.next.val);
    }
    
    private static ListNode move(ListNode start, int hops){
    	for(int i=0;i<hops;++i)
    		if(start!=null)
    			start = start.next;
    	return start;
    }

    public static TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0) return null;
        TreeNode root = new TreeNode(0);
        insertNode(root,num,0,num.length-1);
        return root;
    }

    private static void insertNode(TreeNode root, int[] num, int s, int e){
    	if (s==e){
    		root.val = num[s];
    		return;
    	}
    	int mid = (s+e)%2 == 0 ? (s+e)/2 : (s+e)/2+1;
    	root.val = num[mid];
    	if (mid-s >= 1){
    		root.left = new TreeNode(0);
    		insertNode(root.left,num,s,mid-1);
    	}
    	
    	if (e-mid >=1){
    		root.right = new TreeNode(0);
    		insertNode(root.right,num,mid+1,e);
    	}
    	return;
    }
	
    public static boolean isSameTree(TreeNode p, TreeNode q) {
    	if (p==null || q==null)
    		return p==q;
        return (p.val == q.val) && isSameTree(p.left,q.left) && isSameTree(p.right, q.right);
    }
	
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int max = height(root, true);
        int min = height(root, false);
        
        if (max-min > 1)
        	return false;
        else
        	return true;        
    }
	
    public boolean isAVLBalanced(TreeNode root) {
    	if (root == null) return true;
    	boolean rootbalance;
    	if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) <=1)
    		rootbalance = true;
    	else
    		return false;
    	return rootbalance && isAVLBalanced(root.left) && isAVLBalanced(root.right);
    }
    
    public  int maxDepth(TreeNode root) {
    	if (root == null) 
    		return 0;
    	return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    
    /*
    public int minDepth(TreeNode root) {
    	if (root == null) 
    		return 0;
    	return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
    */
    public void MorrisTraversal(){
    	MorrisTraversal(this);
    }
    
    public void MorrisTraversal(TreeNode root){
    	TreeNode pre, cur = root;
    	while (cur != null){
    		if (cur.left == null){
    			System.out.print(cur.val + ",");
    			cur = cur.right;
    		}else{
    			pre = cur.left;
    			while (pre.right!=null && pre.right!=cur )
    				pre = pre.right;
    			if (pre.right == null){
    				// Set the link of the rightmost node of current's left child to current
    				pre.right = cur;
    				cur = cur.left;
    			}else{
    				// The rightmost node has been traversed
    				// Restore the link to null and 
    				pre.right = null;
        			System.out.print(cur.val + ",");
        			cur = cur.right;
    			}
    		}
    	}
    	System.out.println();
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int hleft = minDepth(root.left);
    	int hright = minDepth(root.right);
    	
    	if (hleft == 0)
    		return hright + 1;
    	if (hright == 0)
    		return hleft + 1;
    	return hleft < hright ? hleft+1 : hright+1;
    }    
    
    public int minDepth(TreeNode root) {
    	int hleft = -1, hright = -1;
    	if (root.left == null && root.right == null)
    		// Leaf node
    		return 1;
    	if (root.left != null)
    		hleft = minDepth(root.left) + 1;
    	if (root.right != null)
    		hright = minDepth(root.right) + 1;
    	
    	if (hleft == -1)
    		// Non leaf node and left is null
    		return hright;
    	else if (hright == -1)
    		// Non leaf node and right is null
    		return hleft;
    	else
    		return hleft < hright ? hleft : hright;
    }


    public int height(TreeNode root, boolean isMax){
    	int hleft = -1, hright = -1;
    	if (root.left == null && root.right == null)
    		// Leaf node
    		return 1;
    	if (root.left != null)
    		hleft = height(root.left,isMax) + 1;
    	if (root.right != null)
    		hright = height(root.right,isMax) + 1;
    	
    	if (hleft == -1)
    		// Non leaf node and left is null
    		return hright;
    	else if (hright == -1)
    		// Non leaf node and right is null
    		return hleft;
    	else{
    		int max = hleft > hright ? hleft : hright;
    		int min = hleft < hright ? hleft : hright;
    		return isMax ? max : min;
    	}
    }
    
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	st.push(root);
    	TreeNode pre = null;
    	while (!st.isEmpty()){
    		TreeNode cur = st.peek();
    		// Leaf node
    		if (cur.left == null && cur.right == null){
    			output.add(cur.val);
    			st.pop();
    			pre = cur;
    			continue;
    		}
    		
    		
    		if (pre!=null && (cur.left == pre || cur.right == pre )){
    			output.add(cur.val);
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
	
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
    	Stack<TreeNode> st = new Stack<TreeNode>();
    	HashMap<TreeNode, Boolean> hm = new HashMap<TreeNode, Boolean>();
    	st.push(root);
    	while (!st.isEmpty()){
    		TreeNode cur = st.pop();    			
    		if (cur.left == null && cur.right == null)
    			output.add(cur.val);
    		else if (hm.containsKey(cur))
    			output.add(cur.val);
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
	
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();
    	if (root == null) return output;
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = root;
        st.push(cur);
        do{
        	cur = st.pop();
        	output.add(cur.val);
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
	
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> output = new ArrayList<Integer>();    	
		if (root == null) return output;
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode cur = root;
		do{
			while (cur!=null){
				st.push(cur);
				cur = cur.left;
			}
			cur = st.pop();
			output.add(cur.val);
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
    
	public void InorderTreeWalk(TreeNode root){
		
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
		System.out.print(root.val + ",");
		InorderTreeWalk(root.right);
		
	}
	
	public void InorderTreeWalk(){
		InorderTreeWalk(this);
		System.out.println();
	}

	public TreeNode searchBinaryTree(TreeNode root, int kval){
		TreeNode cur = root;
		while (cur!=null && cur.val != kval){
			if (cur.val < kval)
				cur = cur.right;
			else
				cur = cur.left;
		}
		return cur;
	}
	
	public TreeNode searchBinaryTree(int kval){
		return searchBinaryTree(this, kval);
	}
	
	
	public TreeNode maxTreeNode(TreeNode root){
		TreeNode cur = root;
		while (cur != null && cur.right != null)
			cur = cur.right;
		return cur;
	}

	public TreeNode maxTreeNode(){
		return maxTreeNode(this);
	}
	
	public TreeNode minTreeNode(TreeNode root){
		TreeNode cur = root;
		while (cur != null && cur.left != null)
			cur = cur.left;
		return cur;
	}
	
	public TreeNode minTreeNode(){
		return minTreeNode(this);
	}
	
	public TreeNode insertNode(TreeNode root, TreeNode x){
		TreeNode cur = root, cur_parent = root;
		while(cur!=null){
			cur_parent = cur;
			if (cur.val < x.val)
				cur = cur.right;
			else
				cur = cur.left;
		}
		if (cur_parent == null) return x;
		if (cur_parent.val < x.val)
			cur_parent.right = x;
		else
			cur_parent.left = x;
		return root;
	}
}
