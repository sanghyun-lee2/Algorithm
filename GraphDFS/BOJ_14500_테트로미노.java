package GraphDFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_14500_테트로미노 {

    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    static int[][] map; // 맵 데이터
    static int r; // 행
    static int c; // 열

    static int ans;

    public static void dfs(boolean[][] isVisited, int cnt, int area, int x, int y){
        if(cnt >= 3){
            ans = Math.max(area, ans);
            return;
        }

        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx < 0 || nx >= c) {
                continue;
            }
            if(ny < 0 || ny >= r){
                continue;
            }
            if(isVisited[ny][nx] == true){
                continue;
            }

            isVisited[ny][nx] = true;
            dfs(isVisited, cnt + 1, area + map[ny][nx], nx, ny);
            isVisited[ny][nx] = false;
        }
    }

    public static void checkTetrino(int x, int y, int type){
        int area = map[y][x];
        for(int d = type; d < type + 3; d++){
            int dd = d % 4;

            int nx = x + dx[dd];
            int ny = y + dy[dd];

            if(nx < 0 || nx >= c){
                continue;
            }
            if(ny < 0 || ny >= r){
                continue;
            }

            area += map[ny][nx];
        }
        ans = Math.max(area, ans);
    }

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        r = sc.nextInt();
        c = sc.nextInt();
        map = new int[r][c];

        for(int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        boolean[][] isVisited = new boolean[r][c];
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                isVisited[i][j] = true;
                int area = map[i][j];
                dfs(isVisited, 0, area, j, i);
                isVisited[i][j] = false;
                checkTetrino(j, i, 0);
                checkTetrino(j, i, 1);
                checkTetrino(j, i, 2);
                checkTetrino(j, i, 3);
            }
        }

        System.out.println(ans);
    }
}
