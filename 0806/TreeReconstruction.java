import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    static Map<Integer, Integer> inorderIndexMap;

    static TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndexMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length -1, 0);
    }

    static TreeNode buildPreIn(int[] preorder, int preStart, int preEnd, int inStart) {
        if (preStart > preEnd) return null;
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;
        root.left = buildPreIn(preorder, preStart+1, preStart + leftTreeSize, inStart);
        root.right = buildPreIn(preorder, preStart + leftTreeSize + 1, preEnd, inRootIndex +1);
        return root;
    }

    static TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) inorderIndexMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length -1, 0);
    }

    static TreeNode buildPostIn(int[] postorder, int postStart, int postEnd, int inStart) {
        if (postStart > postEnd) return null;
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRootIndex = inorderIndexMap.get(rootVal);
        int leftTreeSize = inRootIndex - inStart;
        root.left = buildPostIn(postorder, postStart, postStart + leftTreeSize -1, inStart);
        root.right = buildPostIn(postorder, postStart + leftTreeSize, postEnd-1, inRootIndex +1);
        return root;
    }

    static TreeNode buildCompleteTree(int[] levelorder) {
        if (levelorder.length == 0) return null;
        TreeNode root = new TreeNode(levelorder[0]);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < levelorder.length) {
            TreeNode node = q.poll();
            if (i < levelorder.length) {
                node.left = new TreeNode(levelorder[i++]);
                q.add(node.left);
            }
            if (i < levelorder.length) {
                node.right = new TreeNode(levelorder[i++]);
                q.add(node.right);
            }
        }
        return root;
    }

    static boolean equalsTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return equalsTree(a.left, b.left) && equalsTree(a.right, b.right);
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        int[] levelorder = {1,2,3,4,5,6,7};

        TreeNode root1 = buildTreePreIn(preorder, inorder);
        TreeNode root2 = buildTreePostIn(postorder, inorder);
        TreeNode root3 = buildCompleteTree(levelorder);

        System.out.println("PreIn and PostIn trees equal? " + equalsTree(root1, root2));
        System.out.println("Complete tree built from level order root value: " + root3.val);
    }
}
