public class RecursiveMathCalculator {
    public static void main(String[] args) {
        System.out.println("C(5,2) = " + combination(5, 2));
        System.out.println("Catalan(4) = " + catalan(4));
        System.out.println("Hanoi(3) moves = " + hanoiMoves(3));
        System.out.println("12321 是否回文？" + isPalindrome(12321));
    }

    static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    static int catalan(int n) {
        if (n <= 1) return 1;
        int res = 0;
        for (int i = 0; i < n; i++) res += catalan(i) * catalan(n - 1 - i);
        return res;
    }

    static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    static boolean isPalindrome(int n) {
        String s = Integer.toString(n);
        return isPalHelper(s, 0, s.length() - 1);
    }

    static boolean isPalHelper(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return isPalHelper(s, l + 1, r - 1);
    }
}
