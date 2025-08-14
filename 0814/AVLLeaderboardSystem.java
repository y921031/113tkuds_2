import java.util.*;

public class AVLLeaderboardSystem {
    class Node {
        String player;
        int score, height, size;
        Node left, right;
        Node(String player, int score) {
            this.player = player;
            this.score = score;
            height = 1;
            size = 1;
        }
    }

    private Node root;

    private int height(Node n) {
        return n == null ? 0 : n.height;
    }

    private int size(Node n) {
        return n == null ? 0 : n.size;
    }

    private void update(Node n) {
        n.height = Math.max(height(n.left), height(n.right)) + 1;
        n.size = size(n.left) + size(n.right) + 1;
    }

    private int compare(String p1, int s1, String p2, int s2) {
        if (s1 != s2) return Integer.compare(s2, s1);
        return p1.compareTo(p2);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        update(y);
        update(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        update(x);
        update(y);
        return y;
    }

    private int getBalance(Node n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    private Node balance(Node n) {
        update(n);
        int b = getBalance(n);
        if (b > 1) {
            if (getBalance(n.left) < 0) n.left = rotateLeft(n.left);
            return rotateRight(n);
        }
        if (b < -1) {
            if (getBalance(n.right) > 0) n.right = rotateRight(n.right);
            return rotateLeft(n);
        }
        return n;
    }

    public void addOrUpdate(String player, int score) {
        root = addOrUpdateRec(root, player, score);
    }

    private Node addOrUpdateRec(Node node, String player, int score) {
        if (node == null) return new Node(player, score);
        if (node.player.equals(player)) {
            node.score = score;
        } else {
            int cmp = compare(player, score, node.player, node.score);
            if (cmp < 0) node.left = addOrUpdateRec(node.left, player, score);
            else if (cmp > 0) node.right = addOrUpdateRec(node.right, player, score);
        }
        return balance(node);
    }

    public int getRank(String player) {
        return getRankRec(root, player, 0);
    }

    private int getRankRec(Node node, String player, int higherCount) {
        if (node == null) return -1;
        if (node.player.equals(player)) {
            return higherCount + size(node.left) + 1;
        }
        int cmp = compare(player, 0, node.player, 0);
        if (cmp < 0) {
            return getRankRec(node.left, player, higherCount);
        } else {
            return getRankRec(node.right, player, higherCount + size(node.left) + 1);
        }
    }

    public List<String> topK(int k) {
        List<String> result = new ArrayList<>();
        topKRec(root, k, result);
        return result;
    }

    private void topKRec(Node node, int k, List<String> result) {
        if (node == null || result.size() >= k) return;
        topKRec(node.left, k, result);
        if (result.size() < k) result.add(node.player + ":" + node.score);
        topKRec(node.right, k, result);
    }

    public static void main(String[] args) {
        AVLLeaderboardSystem lb = new AVLLeaderboardSystem();
        lb.addOrUpdate("A", 50);
        lb.addOrUpdate("B", 70);
        lb.addOrUpdate("C", 60);
        System.out.println("Top 2: " + lb.topK(2));
        System.out.println("Rank of C: " + lb.getRank("C"));
    }
}
