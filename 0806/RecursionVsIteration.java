public class RecursionVsIteration {
    public static void main(String[] args) {
        System.out.println("二項式係數 C(5,2)：遞迴=" + combR(5, 2) + " 迭代=" + combI(5, 2));

        int[] arr = {2, 3, 4};
        System.out.println("陣列乘積：遞迴=" + productR(arr, 0) + " 迭代=" + productI(arr));

        String str = "education";
        System.out.println("字串元音數量：遞迴=" + vowelR(str, 0) + " 迭代=" + vowelI(str));

        String brackets = "(()())";
        System.out.println("括號配對：遞迴=" + bracketR(brackets, 0, 0) + " 迭代=" + bracketI(brackets));
    }

    static int combR(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combR(n - 1, k - 1) + combR(n - 1, k);
    }

    static int combI(int n, int k) {
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                dp[i][j] = (j == 0 || j == i) ? 1 : dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    static int productR(int[] a, int i) {
        if (i == a.length) return 1;
        return a[i] * productR(a, i + 1);
    }

    static int productI(int[] a) {
        int p = 1;
        for (int x : a) p *= x;
        return p;
    }

    static int vowelR(String s, int i) {
        if (i == s.length()) return 0;
        return ("aeiou".indexOf(s.charAt(i)) >= 0 ? 1 : 0) + vowelR(s, i + 1);
    }

    static int vowelI(String s) {
        int c = 0;
        for (char ch : s.toCharArray()) if ("aeiou".indexOf(ch) >= 0) c++;
        return c;
    }

    static boolean bracketR(String s, int i, int bal) {
        if (bal < 0) return false;
        if (i == s.length()) return bal == 0;
        return bracketR(s, i + 1, bal + (s.charAt(i) == '(' ? 1 : -1));
    }

    static boolean bracketI(String s) {
        int bal = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') bal++;
            else bal--;
            if (bal < 0) return false;
        }
        return bal == 0;
    }
}
