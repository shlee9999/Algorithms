class UserSolution {
    Player[] players;
    int size;

    public void init(int N) {
        players = new Player[N + 1];
        for (int i = 1; i <= N; i++) players[i] = new Player(i);
        size = N;
    }


    public void updateScore(int mWinnerID, int mLoserID, int mScore) {
        Player p1 = players[players[mWinnerID].team];
        Player p2 = players[players[mLoserID].team];
        while (p1 != null) {
            p1.score += mScore;
            p1 = p1.next;
        }
        while (p2 != null) {
            p2.score -= mScore;
            p2 = p2.next;
        }
    }

    public void unionTeam(int mPlayerA, int mPlayerB) {
        int a = players[mPlayerA].team; //팀 선두
        int b = players[mPlayerB].team;
        if (a > b) {
            int temp = a;
            a = b;
            b = temp; //a가 항상 더 작도록
            mPlayerB=mPlayerA;
        }
        Player last = players[a];
        while (last.next != null) {
            last = last.next;
        }
        last.next = players[b];
        Player p = players[mPlayerB];
        while (p != null) {
            p.team = a;
            p = p.next;
        }
    }

    public int getScore(int mID) {
        return players[mID].score;
    }


    class Player {
        int score, team;
        Player next;

        public Player(int index) {
            team = index;
            score = 0;
        }
    }


}
