public class AVLRotationExercise {
    class Node {
        int key, height;
        Node left, right;
        Node(int key) { this.key = key; height = 1; }
    }

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node n) {
        n.height = Math.max(height(n.left), height(n.right)) + 1;
    }

    private Node rotateLeft(Node z) {
        Node y = z.right;
        Node T2 = y.left;
        y.left = z;
        z.right = T2;
        updateHeight(z);
        updateHeight(y);
        return y;
    }

    private Node rotateRight(Node z) {
        Node y = z.left;
        Node T3 = y.right;
        y.right = z;
        z.left = T3;
        updateHeight(z);
        updateHeight(y);
        return y;
    }

    private Node rotateLeftRight(Node z) {
        z.left = rotateLeft(z.left);
        return rotateRight(z);
    }

    private Node rotateRightLeft(Node z) {
        z.right = rotateRight(z.right);
        return rotateLeft(z);
    }

    public static void main(String[] args) {
        AVLRotationExercise t = new AVLRotationExercise();
        Node a = t.new Node(30);
        Node b = t.new Node(20);
        Node c = t.new Node(40);
        Node d = t.new Node(10);
        a.left = b; a.right = c;
        b.left = d;
        t.updateHeight(d);
        t.updateHeight(b);
        t.updateHeight(a);
        Node newRoot = t.rotateRight(a);
        System.out.println("旋轉後新根: " + newRoot.key);
    }
}
