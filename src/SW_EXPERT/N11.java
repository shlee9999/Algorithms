package SW_EXPERT;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class N11 {
    static int[][] tree;
    static int[] depth, parent;
    static int result;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException { //N11 공통조상
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); //정점 개수
            int E = Integer.parseInt(st.nextToken()); //간선 개수
            int n1 = Integer.parseInt(st.nextToken()); //자식노드1
            int n2 = Integer.parseInt(st.nextToken()); //자식노드2
            tree = new int[V + 1][2];
            depth = new int[V + 1];
            parent = new int[V + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= E; i++) {
                int p = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                if (tree[p][0] == 0) tree[p][0] = c;
                else tree[p][1] = c;
                parent[c] = p;
            }
            result = 0;
            dep(1); //노드별 깊이 설정
            int lca = lca(n1, n2);
            dfs(lca);
//            for (int i = 1; i < V; i++) {
//                bw.write(depth[i] + " ");
//            }
            bw.write("#" + tc + " " + lca(n1, n2) + " " + result + "\n");


        }
        bw.flush();
        bw.close();
    }

    static void dep(int node) throws IOException {
        Queue<Integer> q = new LinkedList<>();
        q.add(node);
        int d = 1;
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int cnt = 0; cnt < qSize; cnt++) {
                int cur = q.poll();
                depth[cur] = d;
                for (int i = 0; i < 2; i++) {
                    int next = tree[cur][i];
                    if (next != 0) q.add(next);
                }
            }
            d++;

        }
    }

    static int lca(int x, int y) {
        if (depth[x] < depth[y]) {//x의 깊이가 더 깊게 설정
            int temp = x;
            x = y;
            y = temp;
        }

        while (depth[x] != depth[y]) {
            x = parent[x];
        }
        if (x == y) return x;
        while (parent[x] != parent[y]) {
            x = parent[x];
            y = parent[y];
        }
        return parent[x];

    }

    static void dfs(int node) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            result++;
            for (int i = 0; i < 2; i++) {
                int next = tree[cur][i];
                if (next != 0) stack.add(next);
            }
        }
    }
}