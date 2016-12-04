package cn.arrowcoder.training;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import cn.arrow.brainteasing.ListNode;

/**
 * Leetcode practice for Job 2017
 */
public class ArrowLeetCode {

    /**
     * P # -2 Binary Tree - post-order
     */
    public List<Integer> TreePostorder(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        if (tree == null)
            return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(tree);
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            if (!hs.contains(cur)) {
                if (cur.right != null)
                    st.push(cur.right);
                if (cur.left != null)
                    st.push(cur.left);
                hs.add(cur);
            } else {
                ret.add(cur.val);
                st.pop();
            }
        }
        return ret;
    }

    public List<Integer> TreePostorderRec(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        TreePostorderRec_aux(tree, ret);
        return ret;
    }

    private void TreePostorderRec_aux(TreeNode tree, List<Integer> ret) {
        if (tree == null)
            return;
        TreePostorderRec_aux(tree.left, ret);
        TreePostorderRec_aux(tree.right, ret);
        ret.add(tree.val);
    }

    /**
     * P # -1 Binary Tree - pre-order
     */

    public List<Integer> TreePreorder(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        if (tree == null)
            return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        st.push(tree);
        while (!st.isEmpty()) {
            TreeNode cur = st.pop();
            ret.add(cur.val);
            if (cur.right != null)
                st.push(cur.right);
            if (cur.left != null)
                st.push(cur.left);
        }
        return ret;
    }

    public List<Integer> TreePreorderRec(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        TreePreorderRec_aux(tree, ret);
        return ret;
    }

    private void TreePreorderRec_aux(TreeNode tree, List<Integer> output) {
        if (tree == null)
            return;
        output.add(tree.val);
        TreePreorderRec_aux(tree.left, output);
        TreePreorderRec_aux(tree.right, output);
    }

    /**
     * P #0: Binary Tree - in-order
     */
    public List<Integer> TreeInorder(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        st.push(tree);
        while (!st.isEmpty()) {
            TreeNode tn = st.peek();
            if (tn.left == null) {
                st.pop();
                ret.add(tn.val);
                hs.add(tn);
                if (tn.right != null)
                    st.push(tn.right);
            } else {
                if (!hs.contains(tn.left)) {
                    st.push(tn.left);
                } else {
                    st.pop();
                    ret.add(tn.val);
                    hs.add(tn);
                    if (tn.right != null)
                        st.push(tn.right);
                }
            }
        }
        return ret;
    }

    public List<Integer> TreeInorder1(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        TreeNode cur = tree;
        do {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            ret.add(cur.val);
            cur = cur.right;
        } while (!st.isEmpty() || cur != null);
        return ret;
    }

    public List<Integer> TreeInorderRec(TreeNode tree) {
        List<Integer> ret = new LinkedList<Integer>();
        TreeInorderRec_aux(tree, ret);
        return ret;
    }

    private void TreeInorderRec_aux(TreeNode tree, List<Integer> ret) {
        if (tree == null)
            return;
        TreeInorderRec_aux(tree.left, ret);
        ret.add(tree.val);
        TreeInorderRec_aux(tree.right, ret);

    }

    /**
     * P1: two sum
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] p1_twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        hm.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            if (hm.get(target - nums[i]) != null) {
                ret[0] = hm.get(target - nums[i]).intValue();
                ret[1] = i;
                break;
            } else {
                hm.put(nums[i], i);
            }
        }
        return ret;
    }

    /**
     * P2. Add Two Numbers
     */
    public ListNode p2_addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        int c = 0;
        while (l1 != null && l2 != null) {
            int cursum = l1.val + l2.val + c;
            c = cursum / 10;
            cur.next = new ListNode(cursum % 10);
            cur = cur.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        l1 = l1 == null ? l2 : l1;
        while (l1 != null) {
            int cursum = l1.val + c;
            c = cursum / 10;
            cur.next = new ListNode(cursum % 10);
            cur = cur.next;
            l1 = l1.next;
        }
        if (c > 0) {
            cur.next = new ListNode(c);
        }
        return head.next;
    }

    /**
     * P #112. Path Sum
     */
    public boolean p112_hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return sum == root.val;
        return p112_hasPathSum(root.left, sum - root.val) || p112_hasPathSum(root.right, sum - root.val);
    }

    public boolean p112_hasPathSumNR(TreeNode root, int sum) {
        boolean ret = false;
        if (root == null)
            return ret;
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        st.push(root);
        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            if (cur.left == null && cur.right == null && sum == cur.val && !hs.contains(cur)) {
                ret = true;
                break;
            }
            if (!hs.contains(cur)) {
                if (cur.right != null) {
                    st.push(cur.right);
                }
                if (cur.left != null) {
                    st.push(cur.left);
                }
                sum -= cur.val;
                hs.add(cur);
            } else {
                st.pop();
                sum += cur.val;
            }
        }
        return ret;
    }

    /**
     * P #463: Island Perimeter
     *
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int ret = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    ret += 4;
                    /* Check cell on top */
                    if (i > 0 && grid[i - 1][j] == 1)
                        ret -= 2;
                    /* Check cell on left */
                    if (j > 0 && grid[i][j - 1] == 1)
                        ret -= 2;
                }
            }
        }
        return ret;
    }

}
