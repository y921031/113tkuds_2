import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static List<List<Integer>> allPaths = new ArrayList<>();

    static void findPaths(TreeNode root, List<Integer> path) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null) {
            allPaths.add(new ArrayList<>(path));
        } else {
            findPaths(root.left, path);
            findPaths(root.right, path);
        }
        path.remove(path.size()-1);
    }

    static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    static int maxRootToLeafSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + Math.max(maxRootToLeafSum(root.left), maxRootToLeafSum(root.right));
    }

    static int maxPathSum = Integer.MIN_VALUE;

    static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxPathSum;
    }

    static int maxGain(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);
        int priceNewPath = node.val + left + right;
        maxPathSum = Math.max(maxPathSum, priceNewPath);
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        findPaths(root, new ArrayList<>());
        System.out.println("All root-to-leaf paths: " + allPaths);
        System.out.println("Has path sum 18? " + hasPathSum(root, 18));
        System.out.println("Max root-to-leaf sum: " + maxRootToLeafSum(root));
        System.out.println("Max path sum (any path): " + maxPathSum(root));
    }
}
