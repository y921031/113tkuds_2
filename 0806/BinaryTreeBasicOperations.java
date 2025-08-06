import java.util.*;

public class BinaryTreeBasicOperations {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.right.left = new Node(12);
        root.right.right = new Node(18);

        int sum = treeSum(root);
        double avg = sum / (double) countNodes(root);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avg);
        System.out.println("Max: " + treeMax(root));
        System.out.println("Min: " + treeMin(root));
        System.out.println("Width: " + treeWidth(root));
        System.out.println("Is Complete: " + isCompleteTree(root));
    }

    static int treeSum(Node root) {
        if (root == null) return 0;
        return root.val + treeSum(root.left) + treeSum(root.right);
    }

    static int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static int treeMax(Node root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(treeMax(root.left), treeMax(root.right)));
    }

    static int treeMin(Node root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(treeMin(root.left), treeMin(root.right)));
    }

    static int treeWidth(Node root) {
        if (root == null) return 0;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int maxWidth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }
        return maxWidth;
    }

    static boolean isCompleteTree(Node root) {
        if (root == null) return true;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean reachedEnd = false;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node == null) {
                reachedEnd = true;
            } else {
                if (reachedEnd) return false;
                q.offer(node.left);
                q.offer(node.right);
            }
        }
        return true;
    }
}
