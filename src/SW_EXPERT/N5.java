package SW_EXPERT;

import java.io.*;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

class N5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N, M, L;
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            LinkedList<Integer> arr = new LinkedList<>();
            for (int i = 0; i < N; i++) arr.add(Integer.parseInt(st.nextToken()));
            for (int i = 0; i < M; i++) {
                String[] split = br.readLine().split(" ");
                String c = split[0];
                switch (c) {
                    case "I": {
                        int index = Integer.parseInt(split[1]);
                        int val = Integer.parseInt(split[2]);
                        arr.add(index, val);
                        break;
                    }
                    case "D": {
                        int index = Integer.parseInt(split[1]);
                        arr.remove(index);
                        break;
                    }
                    case "C": {
                        int index = Integer.parseInt(split[1]);
                        int val = Integer.parseInt(split[2]);
                        arr.set(index, val);
                        break;
                    }
                }
            }
            bw.write("#" + test_case + " ");
            if (L < arr.size()) bw.write(arr.get(L) + "\n");
            else bw.write("-1\n");
        }
        bw.flush();
        bw.close();
    }
}