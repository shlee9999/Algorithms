//import java.io.*;
//import java.util.*;
//
//class SegmentTree {
//    int size;
//    long[] sum;
//    long[] lazy;
//
//    SegmentTree(int n) {
//        size = 1;
//        while (size < n) size <<= 1;
//        sum = new long[size * 2];
//        lazy = new long[size * 2];
//    }
//
//    void propagate(int node, int start, int end) {
//        if (lazy[node] == 0) return;
//        if (start != end) {
//            lazy[node * 2] += lazy[node];
//            lazy[node * 2 + 1] += lazy[node];
//        }
//        sum[node] += (end - start + 1) * lazy[node];
//        lazy[node] = 0;
//    }
//
//    void update(int node, int start, int end, int left, int right, long value) {
//        propagate(node, start, end);
//        if (left > end || right < start) return;
//        if (left <= start && end <= right) {
//            lazy[node] += value;
//            propagate(node, start, end);
//            return;
//        }
//        int mid = (start + end) / 2;
//        update(node * 2, start, mid, left, right, value);
//        update(node * 2 + 1, mid + 1, end, left, right, value);
//        sum[node] = sum[node * 2] + sum[node * 2 + 1];
//    }
//
//    long query(int node, int start, int end, int left, int right) {
//        propagate(node, start, end);
//        if (left > end || right < start) return 0;
//        if (left <= start && end <= right) return sum[node];
//        int mid = (start + end) / 2;
//        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
//    }
//}
//
//public class Main {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        while (T-- > 0) {
//            String[] line1 = br.readLine().split(" ");
//            int n = Integer.parseInt(line1[0]);
//            int q = Integer.parseInt(line1[1]);
//            String[] line2 = br.readLine().split(" ");
//            SegmentTree segTree = new SegmentTree(n);
//            for (int i = 0; i < n; i++) {
//                segTree.update(1, 0, segTree.size - 1, i, i, (i % 2 == 0 ? 1 : -1) * Integer.parseInt(line2[i
