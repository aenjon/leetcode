package cn.arrowcoder.training;

import java.util.ArrayList;
import java.util.Arrays;
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
    public int[] p001_twoSum(int[] nums, int target) {
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
    public ListNode p002_addTwoNumbers(ListNode l1, ListNode l2) {
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
     * P3. Longest Substring Without Repeating Characters
     */
    public int p003_lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int maxlen = 0, d = 0;
        HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!hm.containsKey(cur) || hm.get(cur) < i - d) {
                d++;
            } else {
                d = i - hm.get(cur);
            }
            hm.put(cur, i);
            maxlen = maxlen > d ? maxlen : d;
        }
        return maxlen;
    }

    /**
     * P5. Longest Palindromic Substring
     */

    private int p5_start, p5_maxlen = 1;

    public String p005_longestPalindrome(String s) {
        if (s == null || s.isEmpty())
            return s;
        for (int i = 1; i < s.length(); i++) {
            searchPalindromic(s, i - 1, i);
            searchPalindromic(s, i - 1, i + 1);
        }
        return s.substring(p5_start, p5_start + p5_maxlen);
    }

    private void searchPalindromic(String s, int low, int high) {
        while (low >= 0 && high < s.length() && s.charAt(low) == s.charAt(high)) {
            if (high - low + 1 > p5_maxlen) {
                p5_start = low;
                p5_maxlen = high - low + 1;
            }
            low--;
            high++;
        }
    }

    /**
     * P7. Reverse Integer
     */
    public int p007_reverse(int x) {
        long ret = 0;
        do {
            ret = ret * 10 + x % 10;
            x /= 10;
        } while (x != 0);
        if (ret > Integer.MAX_VALUE || ret < Integer.MIN_VALUE) {
            return 0;
        } else {
            return (int) ret;
        }
    }

    /**
     * P8. String to Integer (atoi)
     */
    public int p008_myAtoi(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int i = 0, len = str.length();
        boolean sign = true;
        long ret = 0;
        while (i < len && str.charAt(i) == ' ')
            i++;
        if (i < len) {
            if (str.charAt(i) == '+') {
                i++;
            } else if (str.charAt(i) == '-') {
                sign = false;
                i++;
            }
        }
        while (i < len) {
            int cur = str.charAt(i) - 48;
            if (cur > 9 || cur < 0) {
                break;
            }
            ret = ret * 10 + cur;
            if (sign && ret > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (!sign && ret >= ((long) Integer.MAX_VALUE + 1)) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return sign ? (int) ret : -(int) ret;
    }

    /**
     * P9. Palindrome Number
     */
    public boolean p009_isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0)
            return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return (x == rev / 10 || x == rev);
    }

    /**
     * P11. Container With Most Water
     */
    public int p011_maxArea(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        int maxarea = 0, i = 0, j = height.length - 1;
        while (i < j) {
            maxarea = Math.max(maxarea, (j - i) * Math.min(height[i], height[j]));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxarea;
    }

    /**
     * P012. Integer to Roman
     */
    public String p012_intToRoman(int num) {
        String[] romanTable = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "", "X", "XX", "XXX", "XL",
                "L", "LX", "LXX", "LXXX", "XC", "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM", "", "M",
                "MM", "MMM" };
        return romanTable[num / 1000 + 30] + romanTable[(num / 100) % 10 + 20] + romanTable[(num / 10) % 10 + 10]
                + romanTable[num % 10];
    }

    /**
     * P013. Roman to Integer
     */
    public int p013_romanToInt(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int ret = 0;
        int[] nums = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    nums[i] = 1;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'X':
                    nums[i] = 10;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'M':
                    nums[i] = 1000;
                    break;
                default:
                    return 0;
            }
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                ret -= nums[i];
            } else {
                ret += nums[i];
            }
        }
        return ret + nums[nums.length - 1];
    }

    /**
     * P014. Longest Common Prefix
     */
    public String p014_longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0 || strs[0] == null)
            return "";
        for (int prefixlen = 0; prefixlen < strs[0].length(); prefixlen++) {
            for (int i = 1; i < strs.length; i++) {
                String curStr = strs[i];
                if (prefixlen >= curStr.length() || curStr.charAt(prefixlen) != strs[0].charAt(prefixlen)) {
                    return strs[0].substring(0, 0 + prefixlen);
                }
            }
        }
        return strs[0];
    }

    /**
     * P015. 3Sum
     */
    public List<List<Integer>> p015_threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 3)
            return ret;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                if (nums[i] + nums[low] + nums[high] == 0) {
                    ret.add(Arrays.asList(nums[i], nums[low], nums[high]));
                    int lv = nums[low], hv = nums[high];
                    while (low < high && lv == nums[low])
                        low++;
                    while (low < high && hv == nums[high])
                        high--;
                } else if (nums[i] + nums[low] + nums[high] > 0) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return ret;
    }

    /**
     * P016. 3Sum Closest
     */
    public int p016_threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3)
            return 0;
        Arrays.sort(nums);
        int ret = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1, high = nums.length - 1;
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (Math.abs(target - sum) < Math.abs(target - ret)) {
                    ret = sum;
                }
                if (sum == target)
                    return target;
                else if (target < sum)
                    high--;
                else
                    low++;
            }
        }
        return ret;
    }

    /**
     * P112. Path Sum
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
     * P #113. Path Sum II
     */
    public List<List<Integer>> p113_pathSum(TreeNode root, int sum) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        List<Integer> subret = new LinkedList<Integer>();
        p113_pathSum_aux(root, sum, ret, subret);
        return ret;
    }

    private void p113_pathSum_aux(TreeNode root, int target, List<List<Integer>> ret, List<Integer> subret) {
        if (root == null)
            return;
        subret.add(root.val);
        if (root.left == null && root.right == null && root.val == target) {
            List<Integer> newItem = new LinkedList<Integer>(subret);
            ret.add(newItem);
            subret.remove(subret.size() - 1);
            return;
        }
        p113_pathSum_aux(root.left, target - root.val, ret, subret);
        p113_pathSum_aux(root.right, target - root.val, ret, subret);
        subret.remove(subret.size() - 1);
    }

    public List<List<Integer>> p113_pathSumNR(TreeNode root, int sum) {
        List<List<Integer>> ret = new LinkedList<List<Integer>>();
        List<Integer> subret = new LinkedList<Integer>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        HashSet<TreeNode> hs = new HashSet<TreeNode>();
        st.add(root);
        while (!st.isEmpty()) {
            TreeNode cur = st.peek();
            if (cur == null) {
                st.pop();
                continue;
            }
            if (cur.left == null && cur.right == null && sum == cur.val && !hs.contains(cur)) {
                subret.add(cur.val);
                List<Integer> newItem = new LinkedList<Integer>(subret);
                ret.add(newItem);
                subret.remove(subret.size() - 1);
                st.pop();
                continue;
            }
            if (!hs.contains(cur)) {
                subret.add(cur.val);
                sum -= cur.val;
                st.add(cur.right);
                st.add(cur.left);
                hs.add(cur);
            } else {
                st.pop();
                sum += cur.val;
                subret.remove(subret.size() - 1);
            }
        }
        return ret;
    }

    /**
     * P463: Island Perimeter
     *
     * @param grid
     * @return
     */
    public int p463_islandPerimeter(int[][] grid) {
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

    /**
     * P999: Consecutive sum: check if an integer array has a consecutive
     * numbers whose sum equals to the target
     */
    public boolean p999_conSum(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        int low = 0, high = 0, sum = 0;

        do {
            if (sum == target) {
                break;
            } else if (sum < target) {
                if (high < nums.length) {
                    sum += nums[high];
                    high++;
                } else {
                    break;
                }
            } else {
                while (sum > target) {
                    sum -= nums[low];
                    low++;
                }
            }
        } while (true);

        /*
         * while (high < nums.length)
         *
         * { if (sum == target) { break; } else if (sum < target) { sum +=
         * nums[high]; high++; } else { while (sum > target) { sum -= nums[low];
         * low++; } } }
         */
        return sum == target;
    }
}
