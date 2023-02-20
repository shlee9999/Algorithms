import java.util.Arrays;
import java.util.Stack;

class UserSolution {
    stack[] top;
    Disk[] disk;
    Peak[] peaks;
    int count, n;
    int a, b, c;

    class stack {
        int size;
        Disk[] arr;

        public stack() {
            arr = new Disk[1001];
            size = 0;
        }

        boolean isempty() {
            return size == 0;
        }

        Disk peek() {
            return arr[size - 1];
        }

        Disk pop() {
            return arr[size-- - 1];
        }

        void push(Disk disk) {
            arr[size++] = disk;
        }

        public int size() {
            return size;
        }
    }

    void init(int[] N, int[][] mDisk) {
        peaks = new Peak[500001];
        top = new stack[4];
        count = 0;
        n = 0;
        for (int i = 1; i <= 3; i++) {
            top[i] = new stack();
        }
        disk = new Disk[N[0] + N[1] + N[2]];
        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < N[i]; j++) {
                disk[cnt++] = new Disk(mDisk[i][j], i + 1);
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
        for (int i = disk.length - 1; i >= 0; i--) {
            top[disk[i].src].push(disk[i]);
        }

        for (int i = 0; i < disk.length; i++) {
            int mid = -1;
            for (int j = 1; j <= 3; j++)
                if (j != disk[i].src && j != disk[i].dst) mid = j;
            if (i == 0) {
                Move(disk[i].src, disk[i].dst);
                continue;
            }
            if (disk[i].src != disk[i].dst) {
                Move(disk[i].src, disk[i].dst);
                Hanoi(i, mid, disk[i].dst, disk[i].src);
            } else Hanoi(i, disk[i - 1].dst, disk[i].dst, disk[i].src);


        }

    }

    void destroy() {
    }

    void go(int k, int[] mTop) {
        n += k;
        if (n > count) {
            mTop[0] = 0;
            mTop[1] = 0;
            mTop[2] = top[3].peek().size;
        } else {
            mTop[0] = peaks[n].top1;
            mTop[1] = peaks[n].top2;
            mTop[2] = peaks[n].top3;
        }
    }


    void Hanoi(int N, int s, int e, int m) {
        if (count >= 500000) return;
        if (N == 1) {
            Move(s, e);
            return;
        }
        Hanoi(N - 1, s, m, e);
        Move(s, e);
        Hanoi(N - 1, m, e, s);
    }

    void Move(int s, int e) {
        if (count >= 500000) return;
        if (s != e) {
            if (top[3].size() == disk.length) {
                peaks[++count] = new Peak(0, 0, top[3].peek().size);
            } else {
                if (!top[s].isempty()) top[e].push(top[s].pop());
                if (top[1].isempty()) a = 0;
                else a = top[1].peek().size;
                if (top[2].isempty()) b = 0;
                else b = top[2].peek().size;
                if (top[3].isempty()) c = 0;
                else c = top[3].peek().size;
                peaks[++count] = new Peak(a, b, c);
            }
        }
    }

    class Peak {
        int top1, top2, top3;

        public Peak(int top1, int top2, int top3) {
            this.top1 = top1;
            this.top2 = top2;
            this.top3 = top3;
        }
    }

    class Disk implements Comparable<Disk> {
        int size, src, dst;


        public Disk(int size, int src) {
            this.size = size;
            this.src = src;
        }

        @Override
        public int compareTo(Disk o) {
            return this.size - o.size;
        }
    }
}