public class AdvancedStringRecursion {
    public static void main(String[] args) {
        System.out.println("排列組合：");
        permute("abc", 0, 2);
        System.out.println("字串匹配(abc vs a*c)： " + match("abc", "a*c"));
        System.out.println("移除重複： " + removeDuplicate("aabbcc"));
        System.out.println("所有子字串組合：");
        substrings("abc", 0, "");
    }

    static void permute(String s, int l, int r) {
        if (l == r) { System.out.println(s); return; }
        for (int i = l; i <= r; i++) {
            s = swap(s, l, i);
            permute(s, l + 1, r);
            s = swap(s, l, i);
        }
    }

    static String swap(String s, int i, int j) {
        char[] c = s.toCharArray();
        char t = c[i]; c[i] = c[j]; c[j] = t;
        return new String(c);
    }

    static boolean match(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.charAt(0) == '*') return !s.isEmpty() && match(s.substring(1), p) || match(s, p.substring(1));
        if (!s.isEmpty() && (p.charAt(0) == s.charAt(0))) return match(s.substring(1), p.substring(1));
        return false;
    }

    static String removeDuplicate(String s) {
        if (s.length() <= 1) return s;
        if (s.charAt(0) == s.charAt(1)) return removeDuplicate(s.substring(1));
        return s.charAt(0) + removeDuplicate(s.substring(1));
    }

    static void substrings(String s, int i, String cur) {
        if (i == s.length()) { if (!cur.isEmpty()) System.out.println(cur); return; }
        substrings(s, i + 1, cur + s.charAt(i));
        substrings(s, i + 1, cur);
    }
}
