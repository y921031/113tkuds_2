public class AVLBasicExercise {
    class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; height = 1; }
    }

    private Node root;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return node;
    }

    public boolean search(int key) {
        return searchRec(root, key);
    }

    private boolean searchRec(Node node, int key) {
        if (node == null) return false;
        if (node.key == key) return true;
        return key < node.key ? searchRec(node.left, key) : searchRec(node.right, key);
    }

    public int height() {
        return height(root);
    }

    public boolean isAVL() {
        return isAVLRec(root);
    }

    private boolean isAVLRec(Node node) {
        if (node == null) return true;
        int balance = height(node.left) - height(node.right);
        return Math.abs(balance) <= 1 && isAVLRec(node.left) && isAVLRec(node.right);
    }

    public static void main(String[] args) {
        AVLBasicExercise tree = new AVLBasicExercise();
        tree.insert(10);
        tree.insert(20);
        tree.insert(5);
        System.out.println("搜尋 20: " + tree.search(20));
        System.out.println("高度: " + tree.height());
        System.out.println("是否 AVL: " + tree.isAVL());
    }
}
