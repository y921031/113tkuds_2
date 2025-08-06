public class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static java.util.List<Integer> rangeQuery(TreeNode root, int min, int max) {
        java.util.List<Integer> res = new java.util.ArrayList<>();
        if (root == null) return res;
        if (root.val > min) res.addAll(rangeQuery(root.left, min, max));
        if (root.val >= min && root.val <= max) res.add(root.val);
        if (root.val < max) res.addAll(rangeQuery(root.right, min, max));
        return res;
    }

    static int rangeCount(TreeNode root, int min, int max) {
        if (root == null) return 0;
        int count = 0;
        if (root.val >= min && root.val <= max) count = 1;
        return count + rangeCount(root.left, min, max) + rangeCount(root.right, min, max);
    }

    static int rangeSum(TreeNode root, int min, int max) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= min && root.val <= max) sum = root.val;
        return sum + rangeSum(root.left, min, max) + rangeSum(root.right, min, max);
    }

    static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        TreeNode current = root;
        while (current != null) {
            if (Math.abs(current.val - target) < Math.abs(closest - target)) {
                closest = current.val;
            }
            if (target < current.val) current = current.left;
            else if (target > current.val) current = current.right;
            else break;
        }
        return closest;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println("Range query [7, 15]: " + rangeQuery(root,7,15));
        System.out.println("Range count [7, 15]: " + rangeCount(root,7,15));
        System.out.println("Range sum [7, 15]: " + rangeSum(root,7,15));
        System.out.println("Closest to 9: " + closestValue(root, 9));
    }
}
