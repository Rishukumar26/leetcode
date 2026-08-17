class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        helper(node.left, result);  // Visit left subtree
        result.add(node.val);       // Process current node
        helper(node.right, result); // Visit right subtree
    }
}
