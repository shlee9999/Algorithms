package SW_EXPERT;


import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;


class N3 { //암호문3
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N; //암호문 길이
        StringTokenizer st;
        LinkedList<String> arr;
        for (int test_case = 1; test_case <= 10; test_case++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            arr = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                arr.add(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int count = 0; count < M; count++) {
                String token = st.nextToken();
                if (token.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) arr.add(x + i, st.nextToken());
                    continue;
                }
                if (token.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = x; i < x + y; i++) arr.remove(i);
                    continue;
                }
                if (token.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for (int i = 0; i < y; i++) arr.add(st.nextToken());
                }
            }
            bw.write("#" + test_case + " ");
            for (int i = 0; i < 10; i++) bw.write(arr.get(i) + " ");
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
}