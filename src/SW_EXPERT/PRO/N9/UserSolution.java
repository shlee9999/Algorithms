package SW_EXPERT.PRO.N9;

import java.util.*;

class UserSolution {
    int size, count;
    TreeSet<Node> pq;
    Map<Integer, Integer> find_locker; // mid -> locker
    Map<Integer, Node> range;

    public void init(int N) {
        pq = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.total == o2.total) {
                    return o1.s - o2.s;
                } else return o2.total - o1.total;
            }
        });
        find_locker = new HashMap<>();
        range = new HashMap<>();
        size = N;
        count = size;
        pq.add(new Node(1, size));
    }

    public int arrive(int mId) { //입실
        count--;
        int idx;
        Node n = pq.pollFirst();
        if (n.s == 1) {
            idx = 1;
            alloc(new Node(idx + 1, n.e));
        } else if (n.e == size) {
            idx = size;
            alloc(new Node(n.s, size - 1));
        } else if (n.total == 1) { //한 개 짜리 -> idx 넣고 pq에 추가 x
            idx = n.s;
        } else if (n.total == 2) { //두 개 짜리 -> pq 한 칸 짜리 추가됨
            idx = n.s;
            alloc(new Node(idx + 1, idx + 1));
        } else {
            idx = (n.s + n.e) / 2;
            Node n1 = new Node(n.s, idx - 1);
            Node n2 = new Node(idx + 1, n.e);
            alloc(n1);
            alloc(n2);
        }
        range.put(idx, null);
        find_locker.put(mId, idx);
        return idx;
    }

    public void alloc(Node node) {
        int s = node.s;
        int e = node.e;
        pq.add(node);
        range.put(s, node);
        range.put(e, node);
    }

    public int leave(int mId) {
        count++;
        int idx = find_locker.get(mId); //mid의 락커번호, range.get(idx) -> 락커가 속한 구간 노드가 나온다
        find_locker.put(mId, -1);
        Node left = range.get(idx - 1);
        Node right = range.get(idx + 1);
        if (left == null && right == null) { //양쪽 다 막힘
            Node n = new Node(idx, idx);
            alloc(n);
        } else if (left == null) { //좌측만 막힌 경우
            pq.remove(right);
            range.put(right.s, null);
            Node n1 = new Node(idx, right.e);
            alloc(n1);
        } else if (right == null) { //우측만 막힌 경우
            pq.remove(left);
            range.put(left.e, null);
            Node n2 = new Node(left.s, idx);
            alloc(n2);
        } else { //양쪽 다 뚫린 경우
            Node newNode = new Node(left.s, right.e);
            pq.remove(left);
            pq.remove(right);
            range.put(right.s, null);
            range.put(left.e, null);
            alloc(newNode);
        }
        return count;
    }

    class Node {
        int s, e, total;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
            total = e - s + 1;
        }
    }
}
