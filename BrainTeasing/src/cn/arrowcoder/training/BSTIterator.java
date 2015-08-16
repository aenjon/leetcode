package cn.arrowcoder.training;

import java.util.Stack;

public class BSTIterator {

	private Stack<TreeNode> next;
	
	public BSTIterator(TreeNode root) {
		next = new Stack<TreeNode>();
		TreeNode cur = root;
		while (cur != null){
			next.push(cur);
			cur = cur.left;
		}
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (!next.isEmpty());
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode cur = next.pop();
        int ret = cur.val;
        if (cur.right != null){
        	next.push(cur.right);
        	cur = cur.right;
        	while (cur.left != null){
        		next.push(cur.left);
        		cur = cur.left;
        	}
        }
        return ret;
    }

}
