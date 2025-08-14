public class AVLTree {
    private AVLNode root;

    private int getHeight(AVLNode node) {
        return (node != null) ? node.height : 0;
    }

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);

        if (data < node.data) node.left = insertNode(node.left, data);
        else if (data > node.data) node.right = insertNode(node.right, data);
        else return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node);
        if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }

    public boolean search(int data) {
        return searchNode(root, data);
    }

    private boolean searchNode(AVLNode node, int data) {
        if (node == null) return false;
        if (data == node.data) return true;
        return (data < node.data) ? searchNode(node.left, data) : searchNode(node.right, data);
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + node.getBalance() + ") ");
            printInOrder(node.right);
        }
    }

    // main 方法測試
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        System.out.println("==== 插入測試 ====");
        int[] values = {30, 20, 40, 10, 25, 50};
        for (int v : values) {
            tree.insert(v);
        }
        tree.printTree();

        System.out.println("\n==== 搜尋測試 ====");
        System.out.println("搜尋 25: " + tree.search(25));
        System.out.println("搜尋 60: " + tree.search(60));

        System.out.println("\n==== 刪除測試 ====");
        tree.printTree();
        System.out.println("刪除 20");
        tree.delete(20);
        tree.printTree();
        System.out.println("刪除 30");
        tree.delete(30);
        tree.printTree();
    }

    public void delete(int data) {
        root = deleteNode(root, data);
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    private AVLNode deleteNode(AVLNode node, int data) {
        if (node == null) return null;

        if (data < node.data) node.left = deleteNode(node.left, data);
        else if (data > node.data) node.right = deleteNode(node.right, data);
        else {
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
                if (temp == null) node = null;
                else {
                    node.data = temp.data;
                    node.left = temp.left;
                    node.right = temp.right;
                    node.height = temp.height;
                }
            } else {
                AVLNode temp = findMin(node.right);
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.data);
            }
        }

        if (node == null) return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && node.left.getBalance() >= 0) return AVLRotations.rightRotate(node);
        if (balance > 1 && node.left.getBalance() < 0) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && node.right.getBalance() <= 0) return AVLRotations.leftRotate(node);
        if (balance < -1 && node.right.getBalance() > 0) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }

        return node;
    }
}