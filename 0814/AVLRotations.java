public class AVLRotations {

    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }

    public static AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    public static AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    public static void main(String[] args) {
        // ==== 測試右旋 ====
        AVLNode root = new AVLNode(30);
        root.left = new AVLNode(20);
        root.left.left = new AVLNode(10);
        root.left.updateHeight();
        root.updateHeight();

        System.out.println("==== 測試右旋 ====");
        System.out.println("右旋前：");
        System.out.println("根節點：" + root.data + ", 高度：" + root.height);
        System.out.println("平衡因子：" + root.getBalance());

        root = rightRotate(root);

        System.out.println("右旋後：");
        System.out.println("根節點：" + root.data + ", 高度：" + root.height);
        System.out.println("平衡因子：" + root.getBalance());

        // ==== 測試左旋 ====
        AVLNode rootL = new AVLNode(10);
        rootL.right = new AVLNode(20);
        rootL.right.right = new AVLNode(30);
        rootL.right.updateHeight();
        rootL.updateHeight();

        System.out.println("\n==== 測試左旋 ====");
        System.out.println("左旋前：");
        System.out.println("根節點：" + rootL.data + ", 高度：" + rootL.height);
        System.out.println("平衡因子：" + rootL.getBalance());

        rootL = leftRotate(rootL);

        System.out.println("左旋後：");
        System.out.println("根節點：" + rootL.data + ", 高度：" + rootL.height);
        System.out.println("平衡因子：" + rootL.getBalance());
    }
}
