package midterm;

import java.io.*;

class M04_TieredTaxSimple {
    static long taxOf(long x) {
        long tax = 0;
        long rem = x;
        long t = Math.min(rem, 120000);
        tax += Math.round(t * 0.05);
        rem -= t;
        if (rem <= 0)
            return tax;
        t = Math.min(rem, 500000 - 120000);
        tax += Math.round(t * 0.12);
        rem -= t;
        if (rem <= 0)
            return tax;
        t = Math.min(rem, 1000000 - 500000);
        tax += Math.round(t * 0.20);
        rem -= t;
        if (rem <= 0)
            return tax;
        if (rem > 0)
            tax += Math.round(rem * 0.30);
        return tax;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        long sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(br.readLine().trim());
            long t = taxOf(x);
            sum += t;
            sb.append("Tax: ").append(t).append('\n');
        }
        long avg = Math.round(sum / (double) n);
        sb.append("Average: ").append(avg).append('\n');
        System.out.print(sb.toString());
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每筆收入只需固定 4 段的累加計算，為 O(1)；總共 n 筆，總時間 O(n)。
 */
