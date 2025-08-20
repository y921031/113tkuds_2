package midterm;

import java.io.*;

class M02_YouBikeNextArrival {
    static int toMin(String s) {
        String[] p = s.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }

    static String toHHMM(int m) {
        int h = m / 60, mm = m % 60;
        return String.format("%02d:%02d", h, mm);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = toMin(br.readLine().trim());
        int q = toMin(br.readLine().trim());
        int lo = 0, hi = n; // first strictly greater
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (a[mid] > q)
                hi = mid;
            else
                lo = mid + 1;
        }
        if (lo == n)
            System.out.println("No bike");
        else
            System.out.println(toHHMM(a[lo]));
    }
}