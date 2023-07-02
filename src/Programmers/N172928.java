package Programmers;

import java.awt.*;

class N172928 {
    static char[][] graph;
    static Point nowPos;

    static public int[] solution(String[] park, String[] routes) {
        graph = new char[park.length][park[0].length()];
        for (int i = 0; i < park.length; i++) {
            for (int j = 0; j < park[0].length(); j++) {
                graph[i][j] = park[i].charAt(j);
                if (graph[i][j] == 'S') nowPos = new Point(i, j);
            }
        } //graph 세팅 및 시작지점 설정 완료
        for (int i = 0; i < routes.length; i++) {
            String[] split = routes[i].split(" ");
            move(split[0].charAt(0), Integer.parseInt(split[1]));
        }
        int[] answer = {nowPos.x, nowPos.y};
        return answer;
    }

    static void move(char op, int n) {
        switch (op) {
            case 'E':
                if (nowPos.y + n >= graph[0].length || !checkValid(op, n)) break;
                else nowPos = new Point(nowPos.x, nowPos.y + n);
                break;
            case 'W':
                if (nowPos.y - n < 0 || !checkValid(op, n)) break;
                else nowPos = new Point(nowPos.x, nowPos.y - n);
                break;
            case 'S':
                if (nowPos.x + n >= graph.length || !checkValid(op, n)) break;
                else nowPos = new Point(nowPos.x + n, nowPos.y);
                break;
            case 'N':
                if (nowPos.x - n < 0 || !checkValid(op, n)) break;
                else nowPos = new Point(nowPos.x - n, nowPos.y);
                break;
        }
    }

    static boolean checkValid(char op, int n) {
        switch (op) {
            case 'E':
                for (int i = nowPos.y; i <= nowPos.y + n; i++)
                    if (graph[nowPos.x][i] == 'X') return false;
                break;
            case 'W':
                for (int i = nowPos.y; i >= nowPos.y - n; i--)
                    if (graph[nowPos.x][i] == 'X') return false;
                break;
            case 'S':
                for (int i = nowPos.x; i <= nowPos.x + n; i++)
                    if (graph[i][nowPos.y] == 'X') return false;
                break;
            case 'N':
                for (int i = nowPos.x; i >= nowPos.x - n; i--)
                    if (graph[i][nowPos.y] == 'X') return false;
                break;
        }
        return true;
    }

}