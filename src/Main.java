<<<<<<< Updated upstream
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Node head = new Node('A', null, null);

        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                char root = st.nextToken().charAt(0);
                char left = st.nextToken().charAt(0);
                char right = st.nextToken().charAt(0);
                insertNode(head, root, left, right); //트리 생성
            }
        }
        preorder(head);
        System.out.println();
        inorder(head);
        System.out.println();
        postorder(head);

    }

    public static void insertNode(Node node, char root, char lc, char rc) {
        if (node.val == root) { //탐색하여 현재 입력값과 노드의 val이 일치하는 노드를 찾아 lc, rc를 삽입한다. 배열 인덱스가 아닌 주소(포인터)로 접근
            //단, 이 풀이는 문제에서 주어지는 입력값에서 항상 부모 노드가 먼저 나와야 한다는 전제조건이 있어야 성립한다는 것을 명심하자. 자식 노드가 먼저 나오게 되면 이와 같은 재귀 함수로 노드를 찾지 못함
            node.lc = lc == '.' ? null : new Node(lc, null, null);
            node.rc = rc == '.' ? null : new Node(rc, null, null);
        } else { //루트 노드부터 재귀로 탐색
            if (node.lc != null) insertNode(node.lc, root, lc, rc);
            if (node.rc != null) insertNode(node.rc, root, lc, rc);
        }
    }

    public static void preorder(Node node) {
        System.out.print(node.val);
        if (node.lc != null) preorder(node.lc);
        if (node.rc != null) preorder(node.rc);
    }

    public static void inorder(Node node) {
        if (node.lc != null) inorder(node.lc);
        System.out.print(node.val);
        if (node.rc != null) inorder(node.rc);
    }

    public static void postorder(Node node) {
        if (node.lc != null) postorder(node.lc);
        if (node.rc != null) postorder(node.rc);
        System.out.print(node.val);
    }

    static class Node {
        char val;
        Node lc, rc;

        public Node(char val, Node lc, Node rc) {
            this.val = val;
            this.lc = lc;
            this.rc = rc;
        }
    }

}
=======

class Main {
    public static void main(String[] args) {

    }
}
>>>>>>> Stashed changes
