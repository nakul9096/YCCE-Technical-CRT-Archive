class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Tree_1312 {

    static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Ignore negative branches3
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // Best path passing through this node
        int currentPath = node.val + leftGain + rightGain;

        maxSum = Math.max(maxSum, currentPath);

        // Return best one-sided path to parent
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);

        root.right.left = new TreeNode(-800);
        root.right.right = new TreeNode(-10);

        root.right.right.left = new TreeNode(-20);
        root.right.right.right = new TreeNode(100);

        System.out.println(maxPathSum(root)); // 107
    }
}