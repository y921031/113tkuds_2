package midterm;

import java.io.*;
import java.util.*;

class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int id;

        Item(String n, int q, int i) {
            name = n;
            qty = q;
            id = i;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty)
                return Integer.compare(a.qty, b.qty); // min-heap by qty
            return a.name.compareTo(b.name); // 次鍵：字典序（小者視為較小）
        });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());
            Item it = new Item(name, qty, i);
            if (pq.size() < K)
                pq.offer(it);
            else if ((qty > pq.peek().qty) || (qty == pq.peek().qty && name.compareTo(pq.peek().name) > 0)) {
                pq.poll();
                pq.offer(it);
            }
        }
        List<Item> list = new ArrayList<>(pq);
        list.sort((a, b) -> {
            if (b.qty != a.qty)
                return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name); // 同量時依字典序升冪
        });
        StringBuilder sb = new StringBuilder();
        for (Item it : list) {
            sb.append(it.name).append(' ').append(it.qty).append('\n');
        }
        System.out.print(sb.toString());
    }
}
/*
 * Time Complexity: O(n log K)
 * 說明：每筆資料與大小為 K 的最小堆互動，插入/替換成本 O(log K)，總計 O(n log K)。
 */