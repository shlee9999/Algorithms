import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class No_40 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[] cow = new int[N]; //z값 좌표들
            int[] horse = new int[M]; //z값 좌표들
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken()); //나중에 더해줘야 함7
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cow[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                horse[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(cow);
            Arrays.sort(horse);
            int i = 0, j = 0;
            int cnt = 1;
            int min = Integer.MAX_VALUE;
            while (i < N && j < M) {
                int distance = Math.abs(cow[i] - horse[j]);
                if (distance < min) {
                    min = distance;
                    cnt = 1;
                } else if (distance == min) cnt++;
                if (cow[i] > horse[j]) j++;
                else i++;
            }
            bw.write("#" + test_case + " " + (Math.abs(c1 - c2) + min) + " " + cnt + '\n');

        }
        bw.flush();
        bw.close();
    }


}