package midterm;

import java.io.*;
import java.util.*;

class M10_RBPropertiesCheck {
    static class Node {
        int val;
        char c;

        Node(int v, char cc) {
            val = v;
            c = cc;
        }
    }

    static int n;
    static Node[] A;

    static boolean isRed(Node x) {
        return x != null && x.val != -1 && x.c == 'R';
    }

    static boolean isBlack(Node x) {
        return x == null || x.val == -1 || x.c == 'B';
    }

    // returns black height, or -1 if mismatch
    static int blackHeight(int idx) {
        if (idx >= n)
            return 1; // beyond bounds = NIL (black)
        Node nd = A[idx];
        if (nd == null || nd.val == -1)
            return 1; // NIL black leaf
        int L = blackHeight(2 * idx + 1);
        if (L == -1)
            return -1;
        int R = blackHeight(2 * idx + 2);
        if (R == -1)
            return -1;
        if (L != R)
            return -1;
        return L + (nd.c == 'B' ? 1 : 0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        A = new Node[n];
        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        for (int i = 0; i < n; i++) {
            int v = Integer.parseInt(st.nextToken());
            String cs = st.nextToken();
            char c = cs.charAt(0);
            if (v == -1)
                c = 'B'; // 空節點視為黑
            A[i] = new Node(v, c);
        }
        // 1) Root black
        if (n > 0) {
            Node root = A[0];
            if (root == null || (root.val != -1 && root.c != 'B')) {
                System.out.println("RootNotBlack");
                return;
            }
        }
        // 2) No red-red adjacency (report first by index in level order)
        for (int i = 0; i < n; i++) {
            Node nd = A[i];
            if (nd == null || nd.val == -1)
                continue;
            if (nd.c == 'R') {
                int L = 2 * i + 1, R = 2 * i + 2;
                if (L < n) {
                    Node lc = A[L];
                    if (lc != null && lc.val != -1 && lc.c == 'R') {
                        System.out.println("RedRedViolation at index " + i);
                        return;
                    }
                }
                if (R < n) {
                    Node rc = A[R];
                    if (rc != null && rc.val != -1 && rc.c == 'R') {
                        System.out.println("RedRedViolation at index " + i);
                        return;
                    }
                }
            }
        }
        // 3) Black height consistent
        int bh = blackHeight(0);
        if (bh == -1) {
            System.out.println("BlackHeightMismatch");
            return;
        }
        System.out.println("RB Valid");
    }
}