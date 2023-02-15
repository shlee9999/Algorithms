package SW_EXPERT.PRO.N6;

import java.util.Arrays;

class UserSolution {    //최대 최솟값 찾기
    final int INF = 100000001, MAX_SIZE = 150001;
    int size;
    Node[] tree = new Node[MAX_SIZE * 4];

    void init(int N, int[] mValue) { //동일. size만 좀 다름
        Arrays.fill(tree, new Node());
        size = 0;
        for (int i = 0; i < N; i++) {
            update(1, MAX_SIZE, 1, ++size, mValue[i]);
        }
    }

    Node merge(Node n1, Node n2) { //동일2
        int min = Math.min(n1.min, n2.min);
        int max = Math.max(n1.max, n2.max);
        int alive = n1.alive + n2.alive;
        return new Node(min, max, alive);
    }

    void add(int M, int[] mValue) { //동일
        for (int i = 0; i < M; i++) update(1, MAX_SIZE, 1, ++size, mValue[i]);
    }

    void update(int s, int e, int n, int i, int val) { //동일함2
        if (i < s || i > e) return; //범위 밖 -> 변경사항 없음
        if (s == e) {
            if (val == -100) tree[n] = new Node();
            else tree[n] = new Node(val, val, 1);
            return;
        }
        int mid = (s + e) / 2;   //범위 내 -> 분할
        update(s, mid, 2 * n, i, val);
        update(mid + 1, e, 2 * n + 1, i, val);
        tree[n] = merge(tree[n * 2], tree[n * 2 + 1]);
    }

    Node query(int s, int e, int n, int ps, int pe) { //동일2
        if (s > pe || e < ps) return new Node();
        if (ps<=s && e<=pe) return tree[n];
        int mid = (s + e) / 2;
        Node n1 = query(s, mid, 2 * n, ps, pe);
        Node n2 = query(mid + 1, e, 2 * n + 1, ps, pe);
        return merge(n1, n2);
    }

    int find_num_th_index(int s, int e, int n, int num) { //동일
        if (s == e) return s;  //리프 노드 도착 -> 인덱스 반환만
        int mid = (s + e) / 2;
        if (tree[2 * n].alive >= num) return find_num_th_index(s, mid, 2 * n, num);
        return find_num_th_index(mid + 1, e, 2 * n + 1, num - tree[2 * n].alive);
    }

    void erase(int mFrom, int mTo) { //동일
        for (int i = mTo; i >= mFrom; i--) {
            int find = find_num_th_index(1, MAX_SIZE, 1, i);
            update(1, MAX_SIZE, 1, find, -100);
        }
    }


    int find(int K) { //R값?
        int T = tree[1].alive; //살아있는 수의 개수
        int L = find_num_th_index(1, MAX_SIZE, 1, T - K + 1);
        Node result = query(1, MAX_SIZE, 1, L, size);
        return result.max - result.min;
    }

    class Node {
        int min;
        int max;
        int alive;

        public Node() {
            min = INF;
            max = -95;
            alive = 0;
        }

        public Node(int min, int max, int alive) {
            this.min = min;
            this.max = max;
            this.alive = alive;
        }
    }
}
