class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<>();
        return buildTrees(1, n);
    }

    private List<TreeNode> buildTrees(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }

        // Iterate through each value as the root
        for (int i = start; i <= end; i++) {
            // Generate all left subtrees with values < i
            List<TreeNode> leftTrees = buildTrees(start, i - 1);
            // Generate all right subtrees with values > i
            List<TreeNode> rightTrees = buildTrees(i + 1, end);

            // Combine each left and right subtree with root i
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode currentRoot = new TreeNode(i);
                    currentRoot.left = left;
                    currentRoot.right = right;
                    allTrees.add(currentRoot);
                }
            }
        }
        return allTrees;
    }
}