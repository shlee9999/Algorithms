package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2748 {
    static Long[] DP = new Long[100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DP[0] = 0L;
        DP[1] = 1L;
        System.out.println(FIBO(Integer.parseInt(br.readLine())));
    }

    public static Long FIBO(int N) {
        if (DP[N] != null) return DP[N];
        return DP[N] = FIBO(N - 2) + FIBO(N - 1);
    }
}
