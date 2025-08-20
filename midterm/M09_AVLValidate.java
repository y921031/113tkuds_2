package midterm;

import java.io.*;
import java.util.*;

class M09_AVLValidate {
    static int n;
    static int[] a;

    static class Res {
        boolean bst;
        boolean avl;
        int min, max, h;

        Res(boolean b, boolean av, int mi, int ma, int hh) {
            bst = b;
            avl = av;
            min = mi;
            max = ma;
            h = hh;
        }
    }

    static Res solve(int idx, Long lo, Long hi) {
        if (idx >= n || a[idx] == -1)
            return new Res(true, true, Integer.MAX_VALUE, Integer.MIN_VALUE, -1);
        int v = a[idx];
        if (!((lo == null || v > lo) && (hi == null || v < hi)))
            return new Res(false, false, 0, 0, 0);
        Res L = solve(2 * idx + 1, lo, (long) v);
        Res R = solve(2 * idx + 2, (long) v, hi);
        boolean bst = L.bst && R.bst;
        boolean avl = L.avl && R.avl && Math.abs(L.h - R.h) <= 1;
        int h = Math.max(L.h, R.h) + 1;
        int mi = Math.min(v, Math.min(L.min, R.min));
        int ma = Math.max(v, Math.max(L.max, R.max));
        return new Res(bst, avl, mi, ma, h);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        if (n == 0 || a[0] == -1) {
            System.out.println("Valid");
            return;
        }
        Res r = solve(0, null, null);
        if (!r.bst)
            System.out.println("Invalid BST");
        else if (!r.avl)
            System.out.println("Invalid AVL");
        else
            System.out.println("Valid");
    }
}
/*
 * Time Complexity: O(n)
 * 說明：單次後序遞迴，同步檢查 BST 區間約束與 AVL 高度差，
 * 每節點恰被訪問一次，故總時間 O(n)，額外空間為遞迴深度 O(h)。
 */
