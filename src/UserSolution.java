import java.util.ArrayList;
import java.util.List;

class UserSolution {
    Player[] players;
    Team[] team;

    public void init(int N) {
        players = new Player[N + 1];
        team = new Team[N + 1];
        for (int i = 1; i <= N; i++) players[i] = new Player(i);
        for (int i = 1; i <= N; i++) {
            team[i] = new Team(i); //i번 팀 멤버는 player[i]
            team[i].add_member(players[i]);
            players[i].team = team[i];
        }
    }


    Team getTeam(int a) {
        return players[a].team;
    }

    public void updateScore(int mWinnerID, int mLoserID, int mScore) {
        Team win = getTeam(mWinnerID);
        Team lose = getTeam(mLoserID);
        for (int i = 0; i < win.members.size(); i++) {
            win.members.get(i).score += mScore;
        }
        for (int i = 0; i < lose.members.size(); i++) {
            lose.members.get(i).score -= mScore;
        }
    }

    public void unionTeam(int mPlayerA, int mPlayerB) {
        int a = getTeam(mPlayerA).team_number;
        int b = getTeam(mPlayerB).team_number;
        if (a > b) {
            int temp = a;
            a = b;
            b = temp; //a가 항상 더 작도록
        }
        Team aTeam = getTeam(a);
        Team bTeam = getTeam(b);
        for (int i = 0; i < bTeam.members.size(); i++) {
            players[a].team.add_member(bTeam.members.get(i));
            bTeam.members.get(i).team = aTeam;
        }
        team[b] = null;
    }

    public int getScore(int mID) {
        return players[mID].score;
    }


    class Player {
        int index, score;
        Team team;

        public Player(int index) {
            this.index = index;
            score = 0;
        }
    }

    class Team {
        int team_number;
        List<Player> members;

        public Team(int team_number) {
            this.team_number = team_number;
            members = new ArrayList<>();
        }

        public void add_member(Player p) {
            members.add(p);
        }

    }

}
