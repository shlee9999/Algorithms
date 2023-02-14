package BOJ;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N1786 { //KMP
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String parent = br.readLine();
        String pattern = br.readLine();
        kmp(parent, pattern);
        System.out.println(list.size());
        for (Integer integer : list) {
            System.out.print(integer + " ");
        }
    }

    static int[] make_table(String pattern) {
        int j = 0;
        int[] table = new int[pattern.length()];
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (pattern.charAt(j) == pattern.charAt(i)) table[i] = ++j;
        }
        return table;
    }

    static void kmp(String parent, String pattern) {
        int j = 0;
        int[] table = make_table(pattern);
        for (int i = 0; i < parent.length(); i++) {
            while (j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            if (parent.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    list.add(i - pattern.length() + 2);
                    j = table[j];
                } else {
                    j++;
                }
            }
        }
    }
}
