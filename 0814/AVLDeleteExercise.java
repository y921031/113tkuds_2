public class AVLDeleteExercise {
    class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; height = 1; }
    }

    private Node root;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node n) {
        n.height = Math.max(height(n.left), height(n.right)) + 1;
    }

    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private Node balance(Node n) {
        updateHeight(n);
        int balance = getBalance(n);
        if (balance > 1) {
            if (getBalance(n.left) < 0) n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (balance < -1) {
            if (getBalance(n.right) > 0) n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    public void insert(int key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insertRec(node.left, key);
        else if (key > node.key) node.right = insertRec(node.right, key);
        return balance(node);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node node, int key) {
        if (node == null) return null;
        if (key < node.key) node.left = deleteRec(node.left, key);
        else if (key > node.key) node.right = deleteRec(node.right, key);
        else {
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node minNode = minValueNode(node.right);
                node.key = minNode.key;
                node.right = deleteRec(node.right, minNode.key);
            }
        }
        if (node != null) node = balance(node);
        return node;
    }

    private Node minValueNode(Node n) {
        while (n.left != null) n = n.left;
        return n;
    }

    public void printInOrder() {
        printInOrderRec(root);
        System.out.println();
    }

    private void printInOrderRec(Node node) {
        if (node != null) {
            printInOrderRec(node.left);
            System.out.print(node.key + " ");
            printInOrderRec(node.right);
        }
    }

    public static void main(String[] args) {
        AVLDeleteExercise tree = new AVLDeleteExercise();
        int[] vals = {10, 20, 30, 40, 50, 25};
        for (int v : vals) tree.insert(v);
        tree.printInOrder();
        tree.delete(40);
        tree.printInOrder();
    }
}
