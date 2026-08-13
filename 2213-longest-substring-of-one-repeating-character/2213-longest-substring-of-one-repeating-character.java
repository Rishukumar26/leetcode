class Solution {
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char leftChar;
        char rightChar;

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.leftChar = c;
            this.rightChar = c;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;
    private int n;

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Base max length from either child
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // Standard prefix and suffix lengths
        res.prefLen = left.prefLen;
        res.suffLen = right.suffLen;

        // Check boundary match
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);

            // If left node is fully uniform, prefix extends into right child
            if (left.prefLen == leftLen) {
                res.prefLen = leftLen + right.prefLen;
            }
            // If right node is fully uniform, suffix extends into left child
            if (right.suffLen == rightLen) {
                res.suffLen = rightLen + left.suffLen;
            }
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            chars[idx] = c;
            tree[node] = new Node(c);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, c);
        } else {
            update(2 * node + 1, mid + 1, end, idx, c);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1], mid - start + 1, end - mid);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        n = s.length();
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].maxLen;
        }

        return ans;
    }
}