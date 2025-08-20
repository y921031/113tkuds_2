package midterm;

import java.io.*;
import java.util.*;

class M11_HeapSortWithTie {
    static int[] score, idx;
    static int n;

    static boolean greater(int i, int j) {
        if (score[i] != score[j])
            return score[i] > score[j];
        return idx[i] > idx[j]; // 分數同時，索引較大者視為「較大」，確保最終升冪時索引較小者在前
    }

    static void siftDown(int heapSize, int i) {
        while (true) {
            int left = 2 * i + 1, right = 2 * i + 2, best = i;
            if (left < heapSize && greater(left, best))
                best = left;
            if (right < heapSize && greater(right, best))
                best = right;
            if (best == i)
                break;
            swap(i, best);
            i = best;
        }
    }

    static void swap(int i, int j) {
        int ts = score[i];
        score[i] = score[j];
        score[j] = ts;
        int ti = idx[i];
        idx[i] = idx[j];
        idx[j] = ti;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        score = new int[n];
        idx = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            score[i] = Integer.parseInt(st.nextToken());
            idx[i] = i;
        }
        for (int i = n / 2 - 1; i >= 0; i--)
            siftDown(n, i); // build max-heap
        for (int end = n - 1; end > 0; end--) {
            swap(0, end);
            siftDown(end, 0);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0)
                sb.append(' ');
            sb.append(score[i]);
        }
        System.out.println(sb.toString());
    }
}
/*
 * Time Complexity: O(n log n)
 * 說明：建堆 O(n)，每次取最大元素並下沉 O(log n)，重複 n-1 次，總計 O(n log n)。
 * 穩定規則：分數相同時以輸入索引較小者優先（最終輸出較前），藉由堆比較函式在同分數下
 * 視「索引較大者」為較大，故在升冪排序中較小索引出現更前面。
 */
