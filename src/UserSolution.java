import java.util.Arrays;

class UserSolution {
    int[][] graph;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int MAP_SIZE;

    void bfs_init(int map_size, int map[][]) {
        graph = new int[map_size][map_size];
        for (int i = 0; i < map_size; i++) {
            for (int j = 0; j < map_size; j++) {
                graph[i][j] = map[i][j];
            }
        }
        MAP_SIZE = map_size;
    }

    int bfs(int x1, int y1, int x2, int y2) {
        que q = new que();
        boolean[][] visited = new boolean[MAP_SIZE][MAP_SIZE];
        q.add(new point(y1 - 1, x1 - 1));
        int d = 0;
        while (!q.isempty()) {
            int qSize = q.size;
            for (int z = 0; z < qSize; z++) {
                point cur = q.poll();
                if (cur.x == x2 - 1 && cur.y == y2 - 1) return d;
                visited[cur.y][cur.x] = true;
                for (int i = 0; i < 4; i++) {
                    int a = cur.y + dy[i];
                    int b = cur.x + dx[i];
                    if (0 <= a && a < MAP_SIZE && 0 <= b && b < MAP_SIZE && !visited[a][b] && graph[a][b] == 0) {
                        q.add(new point(a, b));
                    }
                }
            }
            d++;
        }
        return -1;
    }

    class que {
        point[] q = new point[200];

        public que() {
        }

        int size = 0;

        void add(point p) {
            q[size++] = p;
        }

        point poll() {
            point a = q[0];
            for (int i = 0; i < size - 1; i++) {
                q[i] = q[i + 1];
            }
            size--;
            return a;
        }

        boolean isempty() {
            return size == 0;
        }

    }

    class point {
        int y;
        int x;

        public point(int y, int x) {
            this.y = y;
            this.x = x;
        }


    }
}
