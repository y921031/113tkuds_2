import java.util.*;

public class LevelOrderTraversalVariations {
    static class Node {
        int val;
        Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        System.out.println("Each level nodes: " + levelOrder(root));
        System.out.println("Zigzag level order: " + zigzagLevelOrder(root));
        System.out.println("Last node each level: " + lastNodeEachLevel(root));
        System.out.println("Vertical order: " + verticalOrder(root));
    }

    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i=0; i<size; i++) {
                Node cur = q.poll();
                level.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(level);
        }
        return res;
    }

    static List<List<Integer>> zigzagLevelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        boolean leftToRight = true;
        while (!q.isEmpty()) {
            int size = q.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i=0; i<size; i++) {
                Node cur = q.poll();
                if (leftToRight) level.addLast(cur.val);
                else level.addFirst(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    static List<Integer> lastNodeEachLevel(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            Integer last = null;
            for (int i=0; i<size; i++) {
                Node cur = q.poll();
                last = cur.val;
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            res.add(last);
        }
        return res;
    }

    static List<List<Integer>> verticalOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Node> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();
        q.offer(root);
        cols.offer(0);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int col = cols.poll();
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(cur.val);
            if (cur.left != null) {
                q.offer(cur.left);
                cols.offer(col - 1);
            }
            if (cur.right != null) {
                q.offer(cur.right);
                cols.offer(col + 1);
            }
        }
        for (List<Integer> v : map.values()) {
            res.add(v);
        }
        return res;
    }
}
