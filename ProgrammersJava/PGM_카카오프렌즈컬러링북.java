package ProgrammersJava;

import java.util.*;

class PGM_카카오프렌즈컬러링북 {
    static class Data{
        int x;
        int y;
        int color;

        Data(int x, int y, int color){
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }

    int numOfArea;
    int width;
    int height;
    boolean[][] isVisited;
    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    public int bfs(int x, int y, int[][] picture){
        int color = picture[y][x];
        if(color == 0){
            return 0;
        }
        if(isVisited[y][x] == true){
            return 0;
        }
        numOfArea++;

        int area = 0;
        isVisited[y][x] = true;
        Queue<Data> queue = new LinkedList<>();
        queue.offer(new Data(x, y, color));

        while(!queue.isEmpty()){
            area++;
            Data data = queue.poll();
            for(int d = 0; d < 4; d++){
                int nx = data.x + dx[d];
                int ny = data.y + dy[d];

                if(nx >= width || nx < 0){
                    continue;
                }

                if(ny >= height || ny < 0){
                    continue;
                }

                if(color != picture[ny][nx]){
                    continue;
                }

                if(isVisited[ny][nx] == true){
                    continue;
                }

                isVisited[ny][nx] = true;
                queue.offer(new Data(nx, ny, picture[ny][nx]));
            }
        }

        return area;
    }

    public int[] solution(int m, int n, int[][] picture) {
        width = n;
        height = m;
        isVisited = new boolean[m][n];
        int maxSizeOfOneArea = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int area = bfs(j, i, picture);
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area);
            }
        }

        int[] answer = new int[2];
        answer[0] = numOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
}