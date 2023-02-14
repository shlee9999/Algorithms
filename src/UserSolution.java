import java.util.Arrays;

class UserSolution {
    final int INF = 100000001, MAX_SIZE = 150001;
    int size;
    Node[] tree;

    void init(int N, int[] mValue) {
        tree = new Node[MAX_SIZE * 4];
        size = N;
        make_tree(0, MAX_SIZE, 1, mValue);
    }

    Node merge(Node n1, Node n2) {
        return new Node(Math.min(n1.min, n2.min), Math.max(n1.max, n2.max), n1.alive + n2.alive);
    }

    Node make_tree(int s, int e, int n, int[] arr) {
        if (s == e) {
            if (s >= size) return tree[n] = new Node();
            return tree[n] = new Node(arr[s], arr[s], 1);
        }
        int mid = (s + e) / 2;
        Node n1 = make_tree(s, mid, 2 * n, arr);
        Node n2 = make_tree(mid + 1, e, 2 * n + 1, arr);
        return tree[n] = merge(n1, n2);
    }

    void add(int M, int[] mValue) {
        for (int i = 0; i < M; i++) update(0, MAX_SIZE, 1, size + i, mValue[i]);
        size += M;
    }

    Node update(int s, int e, int n, int i, int val) {
        if (i < s || i > e) return tree[n]; //범위 밖 -> 변경사항 없음
        if (s == e) {
            if (val == -100) return tree[n] = new Node();
            return tree[n] = new Node(val, val, 1);
        }
        int mid = (s + e) / 2;   //범위 내 -> 분할
        Node n1 = update(s, mid, 2 * n, i, val);
        Node n2 = update(mid + 1, e, 2 * n + 1, i, val);
        return tree[n] = merge(n1, n2);

    }

    void erase(int mFrom, int mTo) {
        for (int i = mTo; i >= mFrom; i--) {
            int find = find_num_th_index(0, MAX_SIZE, 1, i);
            update(0, MAX_SIZE, 1, find, -100);
        }
    }

    int find_num_th_index(int s, int e, int n, int num) { //num번째 수 찾기. 여기서 오류?
        if (s == e) return s;  //리프 노드 도착 -> 인덱스 반환만
        int mid = (s + e) / 2;
        if (tree[2 * n].alive >= num) return find_num_th_index(s, mid, 2 * n, num);
        else return find_num_th_index(mid + 1, e, 2 * n + 1, num - tree[2 * n].alive);
    }

    Node query(int s, int e, int n, int ps, int pe) {
        if (s > pe || e < ps) return new Node();
        if (s == e) return tree[n];
        int mid = (s + e) / 2;
        Node n1 = query(s, mid, 2 * n, ps, pe);
        Node n2 = query(mid + 1, e, 2 * n + 1, ps, pe);
        return merge(n1, n2);

    }

    int find(int K) {
        int T = tree[1].alive; //살아있는 수의 개수
        int L = find_num_th_index(0, MAX_SIZE, 1, T - K + 1);
        int R = find_num_th_index(0, MAX_SIZE, 1, T);
        Node result = query(0, MAX_SIZE, 1, L, R);
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
