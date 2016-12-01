package cn.arrowcoder.training;

import java.util.HashMap;

import cn.arrow.brainteasing.ListNode;

/**
 * Leetcode practice for Job 2017
 */
public class ArrowLeetCode {

    /**
     * P1: two sum
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
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
