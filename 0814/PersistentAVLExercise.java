
public class PersistentAVLExercise {
    class Node {
        final int key, height;
        final Node left, right;
        Node(int key, Node left, Node right, int height) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = height;
        }
    }

    private Node[] versions = new Node[100];
    private int versionCount = 0;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private Node update(Node left, int key, Node right) {
        return new Node(key, left, right, Math.max(height(left), height(right)) + 1);
    }

    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        return update(update(x.left, x.key, T2), x.key, update(T2, y.key, y.right));
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        return update(update(x.left, x.key, T2), y.key, update(T2, y.key, y.right));
    }

    private Node balance(Node n) {
        if (n == null) return null;
        int b = getBalance(n);
        if (b > 1) {
            if (getBalance(n.left) < 0) {
                return rotateRight(update(rotateLeft(n.left), n.key, n.right));
            }
            return rotateRight(n);
        }
        if (b < -1) {
            if (getBalance(n.right) > 0) {
                return rotateLeft(update(n.left, n.key, rotateRight(n.right)));
            }
            return rotateLeft(n);
        }
        return n;
    }

    private Node insertRec(Node node, int key) {
        if (node == null) return new Node(key, null, null, 1);
        if (key < node.key) node = update(insertRec(node.left, key), node.key, node.right);
        else if (key > node.key) node = update(node.left, node.key, insertRec(node.right, key));
        return balance(node);
    }

    public void insert(int version, int key) {
        versions[versionCount + 1] = insertRec(versions[version], key);
        versionCount++;
    }

    public Node getVersion(int v) {
        return versions[v];
    }

    public void printInOrder(Node root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.key + " ");
            printInOrder(root.right);
        }
    }

    public static void main(String[] args) {
        PersistentAVLExercise pavl = new PersistentAVLExercise();
        pavl.insert(0, 10);
        pavl.insert(1, 20);
        pavl.insert(2, 5);
        pavl.insert(3, 15);

        System.out.print("版本 2: ");
        pavl.printInOrder(pavl.getVersion(2));
        System.out.println();

        System.out.print("版本 4: ");
        pavl.printInOrder(pavl.getVersion(4));
        System.out.println();
    }
}
