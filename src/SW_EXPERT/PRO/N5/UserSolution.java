package SW_EXPERT.PRO.N5;

class UserSolution {
    int[] parent, score;

    public void init(int N) {
        parent = new int[N+1];
        score = new int[N+1];
        for (int i = 1; i <= N; i++) parent[i] = i;
    }

    public void updateScore(int mWinnerID, int mLoserID, int mScore) { //팀 별 변동 점수 저장
        int a = getParent(mWinnerID);
        int b = getParent(mLoserID);
        score[a] += mScore;
        score[b] -= mScore;
    }

    public int getParent(int a) {
        if (parent[a] == a) return a; //본인이 root
        if (parent[parent[a]] == parent[a]) return parent[a]; //부모가 root
        int root = getParent(parent[a]);
        score[a] += score[parent[a]];
        parent[a] = root;
        return root;
    }

    public void union(int a, int b) {
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    public void unionTeam(int mPlayerA, int mPlayerB) {
        int a = getParent(mPlayerA);
        int b = getParent(mPlayerB);
        parent[b] = a;
        score[b] -= score[a];
    }

    public int getScore(int mID) {
        int root = getParent(mID);
        if (root == mID) return score[mID];
        if (root == parent[mID]) return score[mID] + score[root];
        return 0;
    }
}
