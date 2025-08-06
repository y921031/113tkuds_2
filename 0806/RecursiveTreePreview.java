import java.util.*;

public class RecursiveTreePreview {
    static class Node {
        String name;
        List<Node> children = new ArrayList<>();
        Node(String n) { name = n; }
    }

    public static void main(String[] args) {
        Node root = new Node("root");
        Node folderA = new Node("folderA");
        Node folderB = new Node("folderB");
        folderA.children.add(new Node("file1"));
        folderA.children.add(new Node("file2"));
        folderB.children.add(new Node("file3"));
        root.children.add(folderA);
        root.children.add(folderB);

        System.out.println("Total files: " + countFiles(root));
        System.out.println("Menu structure:");
        printMenu(root, 0);

        Object[] nestedArray = new Object[] {1, new Object[] {2, 3, new Object[] {4, 5}}, 6};
        int[] flat = flatten(nestedArray);
        System.out.print("Flattened array: ");
        for (int v : flat) System.out.print(v + " ");
        System.out.println();

        System.out.println("Max depth: " + maxDepth(root));
    }

    static int countFiles(Node node) {
        if (node.children.isEmpty()) {
            return 1;
        }
        int total = 0;
        for (Node c : node.children) {
            total += countFiles(c);
        }
        return total;
    }

    static void printMenu(Node node, int depth) {
        String indent = "";
        for (int i = 0; i < depth; i++) {
            indent += "  ";
        }
        System.out.println(indent + node.name);
        for (Node c : node.children) {
            printMenu(c, depth + 1);
        }
    }

    static int[] flatten(Object[] arr) {
        List<Integer> list = new ArrayList<>();
        flattenHelper(arr, list);
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    static void flattenHelper(Object[] arr, List<Integer> list) {
        for (Object o : arr) {
            if (o instanceof Integer) {
                list.add((Integer) o);
            } else if (o instanceof Object[]) {
                flattenHelper((Object[]) o, list);
            }
        }
    }

    static int maxDepth(Node node) {
        if (node.children.isEmpty()) {
            return 1;
        }
        int max = 0;
        for (Node c : node.children) {
            int d = maxDepth(c);
            if (d > max) {
                max = d;
            }
        }
        return max + 1;
    }
}
