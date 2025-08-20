package midterm;

import java.io.*;

class M06_PalindromeClean {
    static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) {
            System.out.println("Yes");
            return;
        }
        int i = 0, j = s.length() - 1;
        boolean ok = true;
        while (i < j) {
            char ci = s.charAt(i), cj = s.charAt(j);
            if (!isLetter(ci)) {
                i++;
                continue;
            }
            if (!isLetter(cj)) {
                j--;
                continue;
            }
            if (Character.toLowerCase(ci) != Character.toLowerCase(cj)) {
                ok = false;
                break;
            }
            i++;
            j--;
        }
        System.out.println(ok ? "Yes" : "No");
    }
}