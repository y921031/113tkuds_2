package midterm;

import java.io.*;
import java.util.*;

class M07_BinaryTreeLeftView {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        if (n == 0 || a[0] == -1) {
            System.out.println("LeftView:");
            return;
        }
        List<Integer> ans = new ArrayList<>();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(0);
        while (!q.isEmpty()) {
            int sz = q.size();
            for (int t = 0; t < sz; t++) {
                int idx = q.poll();
                if (t == 0)
                    ans.add(a[idx]);
                int L = 2 * idx + 1, R = 2 * idx + 2;
                if (L < n && a[L] != -1)
                    q.add(L);
                if (R < n && a[R] != -1)
                    q.add(R);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("LeftView:");
        for (int i = 0; i < ans.size(); i++)
            sb.append(i == 0 ? " " : " ").append(ans.get(i));
        System.out.println(sb.toString());
    }
}
