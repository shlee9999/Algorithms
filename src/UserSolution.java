import java.util.Arrays;
import java.util.Stack;

class UserSolution {
    Stack<Disk>[] top;
    Disk[] disk;
    int count, k2, prev;
    int a, b, c;

    void init(int[] N, int[][] mDisk) {
        top = new Stack[4];
        for (int i = 1; i <= 3; i++) {
            top[i] = new Stack<>();
        }
        disk = new Disk[N[0] + N[1] + N[2]];
        int cnt=0;
        for (int i = 0; i < 3; i++) {
            int below = N[i];
            for (int j = 0; j < N[i]; j++) {
                disk[cnt++] = new Disk(mDisk[i][j], i + 1, --below);
            }
        }
        Arrays.sort(disk);
        for (int i = disk.length - 1; i >= 0; i--) {
            if (i == disk.length - 1) disk[i].dst = 3;
            else {
                int d = -1;
                for (int j = 1; j <= 3; j++)
                    if (j != disk[i + 1].src && j != disk[i + 1].dst) d = j;
                if (disk[i + 1].src == disk[i + 1].dst) {
                    d = disk[i + 1].dst;
                }
                disk[i].dst = d;
            }
        }
//        for (int i = 0; i < disk.length; i++) {
//            System.out.print("disk[" + i + "] : size = " + disk[i].size);
//            System.out.println(", src = " + disk[i].src + ", dst = " + disk[i].dst + ", below = " + disk[i].below);
//        }
        for (int i = disk.length - 1; i >= 0; i--) {
            top[disk[i].src].push(disk[i]);
        }
        k2 = 0;
    }

    void destroy() {

    }

    void go(int k, int[] mTop) {
        top = new Stack[4];
        for (int i = 1; i <= 3; i++) {
            top[i] = new Stack<>();
        }
        for (int i = disk.length - 1; i >= 0; i--) {
            top[disk[i].src].push(disk[i]);
        }
        count = 0;
        k2 += k;
        for (int i = 1; i < disk.length; i++) {
            int mid = -1;
            for (int j = 1; j <= 3; j++)
                if (j != disk[i].src && j != disk[i].dst) mid = j;
//            System.out.println("i = " + i);
//            System.out.print("disk[" + i + "] :  = ");
//            System.out.println("src = " + disk[i].src + " dst = " + disk[i].dst + " below = " + disk[i].below);
            Move(disk[i].src, disk[i].dst);
            Hanoi(i, mid, disk[i].dst, disk[i].src);
        }
        mTop[0] = a;
        mTop[1] = b;
        mTop[2] = c;
    }


    void Hanoi(int N, int s, int e, int m) {
        if (N == 1) {
            Move(s, e);
            return;
        }
        Hanoi(N - 1, s, m, e);
        Move(s, e);
        Hanoi(N - 1, m, e, s);
    }

    void Move(int s, int e) {
        if (s != e) {
            count++;
            if (!top[s].isEmpty()) top[e].push(top[s].pop());
//            System.out.println("Move " + s + "->" + e);
            if (count == k2) {
                if (top[1].isEmpty()) a = 0;
                else a = top[1].peek().size;
                if (top[2].isEmpty()) b = 0;
                else b = top[2].peek().size;
                if (top[3].isEmpty()) c = 0;
                else c = top[3].peek().size;
//                System.out.println(a + " " + b + " " + c);
//                for (int i = 1; i <= 3; i++) {
//                    if (top[i].isEmpty()) System.out.println("0");
//                    else {
//                        for (Disk disk1 : top[i]) {
//                            System.out.print(disk1.size + " ");
//                        }
//                        System.out.println();
//                    }
//
//                }
            }
        }
    }


    class Disk implements Comparable<Disk> {
        int size, src, dst, below;


        public Disk(int size, int src, int below) {
            this.size = size;
            this.src = src;
            this.below = below;
        }

        @Override
        public int compareTo(Disk o) {
            return this.size - o.size;
        }
    }
}