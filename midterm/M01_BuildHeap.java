package midterm;

import java.io.*;
import java.util.*;

class M01_BuildHeap {
    static void siftDown(int[] a, int n, int i, boolean isMax) {
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2;
            int best = i;
            if (left < n && cmp(a[left], a[best], isMax) > 0)
                best = left;
            if (right < n && cmp(a[right], a[best], isMax) > 0)
                best = right;
            if (best == i)
                break;
            int tmp = a[i];
            a[i] = a[best];
            a[best] = tmp;
            i = best;
        }
    }

    static int cmp(int x, int y, boolean isMax) {
        return isMax ? Integer.compare(x, y) : Integer.compare(y, x);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String type = br.readLine().trim();
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            a[i] = Integer.parseInt(st.nextToken());
        boolean isMax = type.equalsIgnoreCase("max");
        for (int i = n / 2 - 1; i >= 0; i--)
            siftDown(a, n, i, isMax);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }
}
/*
 * Time Complexity: O(n)
 * 說明：自底向上建堆，對於深度為 d 的節點，每次下沉最多 O(d)；
 * 各層節點數量成倍增加，總攤銷成本為 sum_{d>=0} (n/2^{d+1}) * O(d) = O(n)。
 */