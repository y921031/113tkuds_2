public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static TreeNode prev = null;
    static TreeNode head = null;

    static void bstToDoublyList(TreeNode root) {
        prev = null;
        head = null;
        inorder(root);
    }

    static void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev == null) head = node;
        else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inorder(node.right);
    }

    static TreeNode sortedArrayToBST(int[] arr) {
        return sortedArrayToBSTHelper(arr, 0, arr.length -1);
    }

    static TreeNode sortedArrayToBSTHelper(int[] arr, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = sortedArrayToBSTHelper(arr, start, mid -1);
        node.right = sortedArrayToBSTHelper(arr, mid +1, end);
        return node;
    }

    static boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    static int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        if (left == -1) return -1;
        int right = height(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    static int sumGreaterEqual(TreeNode root, int val) {
        if (root == null) return 0;
        int sum = 0;
        if (root.val >= val) sum += root.val + sumGreaterEqual(root.left, val) + sumGreaterEqual(root.right, val);
        else sum += sumGreaterEqual(root.right, val);
        return sum;
    }

    public static void main(String[] args) {
        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode root = sortedArrayToBST(sortedArr);

        System.out.println("Is balanced? " + isBalanced(root));

        bstToDoublyList(root);
        System.out.print("BST to doubly linked list: ");
        TreeNode cur = head;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        System.out.println();

        System.out.println("Sum of nodes >=4: " + sumGreaterEqual(root, 4));
    }
}
