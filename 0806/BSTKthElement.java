import java.util.*;

public class BSTKthElement {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    static List<Integer> inorderList = new ArrayList<>();

    public static void main(String[] args) {
        Node root = buildBST(new int[]{10,5,15,3,7,18});
        System.out.println("3rd smallest: " + kthSmallest(root, 3));
        System.out.println("2nd largest: " + kthLargest(root, 2));
        System.out.println("Elements from 2nd to 4th smallest: " + kthRange(root, 2, 4));
    }

    static Node buildBST(int[] vals) {
        Node root = null;
        for (int v : vals) root = insert(root, v);
        return root;
    }

    static Node insert(Node root, int val) {
        if (root == null) return new Node(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    static int kthSmallest(Node root, int k) {
        inorderList.clear();
        inorder(root);
        return inorderList.get(k - 1);
    }

    static int kthLargest(Node root, int k) {
        inorderList.clear();
        inorder(root);
        return inorderList.get(inorderList.size() - k);
    }

    static List<Integer> kthRange(Node root, int k, int j) {
        inorderList.clear();
        inorder(root);
        return inorderList.subList(k - 1, j);
    }

    static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }
}
