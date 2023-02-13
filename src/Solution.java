import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int N, M;
    static int[] cow;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            cow = new int[N]; //z값 좌표들
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken()); //나중에 더해줘야 함7g
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                cow[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(cow);
            int cnt = 0;
            int min = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int horse = Integer.parseInt(st.nextToken());
                int idx = binary_search(horse); //입력된 horse보다 크거나 같은 가장 가까운 cow를 찾음
                int distance = Math.abs(horse - cow[idx]);
                if (idx > 0) {
                    if (distance > Math.abs(horse - cow[idx - 1])) { //이전 소와의 거리가 더 짧다면 distance 갱신
                        distance = Math.abs(horse - cow[idx - 1]);
                    }
                }
                if (min > distance) { //최솟값 갱신되면 cnt=1로 설정
                    cnt = 0;
                    min = distance;
                }
                if (min == distance) {
                    cnt++;
                }
                if (min == distance && idx > 0 && Math.abs(horse - cow[idx]) == Math.abs(horse - cow[idx - 1])) { //이전 소와 거리가 같다면 cnt++

                    cnt++;
                }
            }
            bw.write("#" + test_case + " " + (Math.abs(c1 - c2) + min) + " " + cnt + '\n');

        }
        bw.flush();
        bw.close();
    }

    static int binary_search(int target) {
        int low = 0;
        int high = N;
        if (target < cow[low]) return 0; // 소들 범위 바깥에 말이 있는 경우
        else if (target > cow[N-1]) return N-1; // 끝 값을 반환해줌
        int mid = (low + high) / 2;
        while (low <= high) {
            mid = (low + high) / 2;
            if (cow[mid] > target) high = mid - 1;
            else if (cow[mid] < target) low = mid + 1;
            else return mid;
        }
        if (cow[mid] < target) mid++; //항상 target이상에서의 가장 가까운 값을 찾아냄 그 대신, 하나 전의 인덱스는 target이하에서의 가장 가까운 값이 됨
        return mid;
    }
}