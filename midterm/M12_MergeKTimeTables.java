package midterm;

import java.io.*;
import java.util.*;

class M12_MergeKTimeTables {
    static class Node {
        int t, k, i;

        Node(int tt, int kk, int ii) {
            t = tt;
            k = kk;
            i = ii;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine().trim());
        List<int[]> lists = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            int len = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[len];
            if (len > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int i = 0; i < len; i++)
                    arr[i] = Integer.parseInt(st.nextToken());
            } else {
                // consume possible blank line
                // (optional) no-op
            }
            lists.add(arr);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.t));
        for (int k = 0; k < K; k++)
            if (lists.get(k).length > 0)
                pq.offer(new Node(lists.get(k)[0], k, 0));
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first)
                sb.append(' ');
            first = false;
            sb.append(cur.t);
            int ni = cur.i + 1;
            if (ni < lists.get(cur.k).length)
                pq.offer(new Node(lists.get(cur.k)[ni], cur.k, ni));
        }
        System.out.println(sb.toString());
    }
}