package midterm;

import java.io.*;
import java.util.*;

class M08_BSTRangedSum {
    static int n;
    static int[] a;

    static long dfs(int idx, int L, int R) {
        if (idx >= n || a[idx] == -1)
            return 0;
        long val = a[idx];
        long sum = 0;
        if (val > L)
            sum += dfs(2 * idx + 1, L, R); // left
        if (L <= val && val <= R)
            sum += val;
        if (val < R)
            sum += dfs(2 * idx + 2, L, R); // right
        return sum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long ans = dfs(0, L, R);
        System.out.println("Sum: " + ans);
    }
}