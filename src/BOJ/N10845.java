package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class N10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] q = new int[N + 1];
        int size = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            switch (command) {
                case "push":
                    q[size++] = Integer.parseInt(st.nextToken());
                    break;
                case "pop":
                    if (size == 0) {
                        System.out.println("-1");
                        break;
                    }
                    System.out.println(q[0]);
                    for (int j = 0; j < size - 1; j++)
                        q[j] = q[j + 1];
                    size--;
                    break;
                case "size":
                    System.out.println(size);
                    break;
                case "empty":
                    if (size == 0) System.out.println(1);
                    else System.out.println(0);
                    break;
                case "front":
                    if (size == 0) System.out.println("-1");
                    else System.out.println(q[0]);
                    break;
                case "back":
                    if (size == 0) System.out.println("-1");
                    else System.out.println(q[size - 1]);
                    break;

            }

        }
    }

}
