import java.util.*;

public class MultiWayTreeAndDecisionTree {
    static class MultiNode {
        int val;
        List<MultiNode> children = new ArrayList<>();
        MultiNode(int v) { val = v; }
    }

    static void dfs(MultiNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        for (MultiNode c : root.children) dfs(c);
    }

    static void bfs(MultiNode root) {
        if (root == null) return;
        Queue<MultiNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            MultiNode node = q.poll();
            System.out.print(node.val + " ");
            for (MultiNode c : node.children) q.offer(c);
        }
    }

    static int height(MultiNode root) {
        if (root == null) return 0;
        int maxH = 0;
        for (MultiNode c : root.children) {
            int h = height(c);
            if (h > maxH) maxH = h;
        }
        return 1 + maxH;
    }

    static class DecisionNode {
        String question;
        Map<String, DecisionNode> children = new HashMap<>();
        DecisionNode(String q) { question = q; }
    }

    static void simulateGuessNumber() {
        DecisionNode root = new DecisionNode("Is number > 50?");
        root.children.put("yes", new DecisionNode("Is number > 75?"));
        root.children.put("no", new DecisionNode("Is number > 25?"));
        root.children.get("yes").children.put("yes", new DecisionNode("Number is > 75"));
        root.children.get("yes").children.put("no", new DecisionNode("Number is between 51 and 75"));
        root.children.get("no").children.put("yes", new DecisionNode("Number is between 26 and 50"));
        root.children.get("no").children.put("no", new DecisionNode("Number is <= 25"));

        DecisionNode node = root;
        Scanner sc = new Scanner(System.in);
        while (node.children.size() > 0) {
            System.out.println(node.question);
            String ans = sc.nextLine().trim().toLowerCase();
            if (node.children.containsKey(ans)) node = node.children.get(ans);
            else {
                System.out.println("Invalid input.");
                break;
            }
        }
        System.out.println("Result: " + node.question);
        sc.close();
    }

    public static void main(String[] args) {
        MultiNode root = new MultiNode(1);
        MultiNode c1 = new MultiNode(2);
        MultiNode c2 = new MultiNode(3);
        root.children.add(c1);
        root.children.add(c2);
        c1.children.add(new MultiNode(4));
        c1.children.add(new MultiNode(5));
        c2.children.add(new MultiNode(6));

        System.out.print("DFS: ");
        dfs(root);
        System.out.println();

        System.out.print("BFS: ");
        bfs(root);
        System.out.println();

        System.out.println("Height: " + height(root));

        simulateGuessNumber();
    }
}
