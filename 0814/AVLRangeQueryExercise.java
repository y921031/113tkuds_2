import java.util.*;

public class AVLRangeQueryExercise {
    class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; height = 1; }
    }

    private Node root;

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(Node node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (min < node.key) rangeQueryRec(node.left, min, max, result);
        if (node.key >= min && node.key <= max) result.add(node.key);
        if (max > node.key) rangeQueryRec(node.right, min, max, result);
    }

    public static void main(String[] args) {
        AVLRangeQueryExercise tree = new AVLRangeQueryExercise();
        int[] vals = {10, 20, 30, 40, 50, 25};
        for (int v : vals) tree.insert(v);
        System.out.println(tree.rangeQuery(20, 40));
    }
}
