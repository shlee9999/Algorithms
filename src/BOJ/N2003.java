package BOJ;

import java.io.*;
import java.util.StringTokenizer;

public class N2003 {
    //2003번
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int count = 0;
        int N, M;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int arr[] = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int lp = 0;
        int rp = 1;
        int sum = arr[0];
        while (lp<=rp) {
//            System.out.println("sum="+sum);
            if (sum > M) {
                sum-=arr[lp++];
            } else if(sum < M){
                if(rp>=N) break;
                sum+=arr[rp++];
            } else {
                //sum==M
//                System.out.println("lp="+lp);
//                System.out.println("rp=" + (rp - 1));
//                System.out.println("sum="+sum);
                count++;
                sum-=arr[lp++];
            }
        }
        System.out.println(count);

    }
}
