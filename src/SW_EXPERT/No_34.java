package SW_EXPERT;

import java.io.*;
import java.util.StringTokenizer;

public class No_34{
    static long s;
    static int k;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            long a, b;
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Long.parseLong(st.nextToken());
            b = Long.parseLong(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            s = a + b;
            int result = (int) (a * power(2, k) % s);
            bw.write("#" + test_case + " " + Math.min(result, s - result) + '\n');
        }
        bw.flush();
        bw.close();
    }


    public static long power(long n, int k) throws IOException {
        long res = 1L;
        while (k > 0) {
            if (k % 2 == 1) res = res * n % s;
            n = (n % s) * (n % s) % s;
            k >>= 1;
        }
        return res % s;
    }

}