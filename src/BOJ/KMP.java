package BOJ;

import java.io.*;
import java.util.Arrays;

public class KMP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = "aababccddeaceaabdaabcaab";
        String pattern = "aab";
        KMP(str, pattern);
    }

    static int[] make_table(String pattern) {
        int j = 0;
        int[] table = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(j) != pattern.charAt(i)) {
                j = table[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) {
                table[i] = ++j;
            }
        }
        Arrays.sort(table);
        return table;
    }

    static void KMP(String parent, String pattern) {
        int j = 0;
        int[] table = make_table(pattern);
        for (int i = 0; i < parent.length(); i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    System.out.println((i - pattern.length() + 2) + "번째에서 찾았습니다");
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
    }
}
