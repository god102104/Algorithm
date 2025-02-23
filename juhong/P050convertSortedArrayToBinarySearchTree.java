/** https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return makeLeaf(0, nums.length - 1, nums);
    }

    public TreeNode makeLeaf(int left, int right, int[] nums) {
        TreeNode node = null;
        
        if (0 <= right && left < nums.length && left <= right) {
            int mid = (left + right) / 2;

            node = new TreeNode(nums[mid]);
            node.left = makeLeaf(left, mid - 1, nums);
            node.right = makeLeaf(mid + 1, right, nums);
        }
        return node;
    }
}