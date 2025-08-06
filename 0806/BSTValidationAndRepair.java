public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isValidBSTHelper(node.left, min, node.val) &&
               isValidBSTHelper(node.right, node.val, max);
    }

    static TreeNode prev = null;
    static TreeNode first = null;
    static TreeNode second = null;

    static void recoverTree(TreeNode root) {
        prev = first = second = null;
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    static int countToRemove(TreeNode root) {
        if (root == null) return 0;
        if (isValidBST(root)) return 0;
        int left = countToRemove(root.left);
        int right = countToRemove(root.right);
        return 1 + left + right;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2); // 範例錯誤節點

        System.out.println("Is valid BST? " + isValidBST(root));
        recoverTree(root);
        System.out.println("Is valid BST after repair? " + isValidBST(root));
        System.out.println("Nodes to remove to make BST valid: " + countToRemove(root));
    }
}
