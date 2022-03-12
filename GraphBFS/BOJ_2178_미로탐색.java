package GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2178_미로탐색 {

	static int H; // 지도 세로 크기
	static int W; // 지도 가로 크기
	static char[][] map; // 맵 데이터
	
	static int[] dx = { 1, -1, 0, 0};
	static int[] dy = { 0, 0, 1, -1};
	
	public static class Point{
		int x;
		int y;
		int move;
		Point(int x, int y, int move){
			this.x = x;
			this.y = y;
			this.move = move;
		}
		
		public int getMove() {
			return move;
		}
		public void setMove(int move) {
			this.move = move;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + ", move=" + move + "]";
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt(); // 지도 세로 크기 입력
		W = sc.nextInt(); // 지도 가로 크기 입력
		
		map = new char[H][W];
		
		for(int i = 0; i < H; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		int dist = bfs();
		System.out.println(dist);
		
		sc.close();
	}
	
	public static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] isVisited = new boolean[H][W];
		
		queue.offer(new Point(0, 0, 0)); // 0, 0위치에서 시작 이동거리는 0
		
		int minDist = Integer.MAX_VALUE;
		
		while(queue.isEmpty() == false) {
			Point p = queue.poll(); // 큐에서 위치 가져옴
			
			// 미로의 마지막 끝에 도달함!!
			if(p.x == W - 1 && p.y == H -1) {
				// 최소 이동거리 선택
				minDist = Math.min(minDist, p.move + 1); 
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= W) {
					continue;
				}
				
				if(ny < 0 || ny >= H) {
					continue;
				}
				
				if(map[ny][nx] == '0') {
					continue;
				}
				
				if(isVisited[ny][nx] == true) {
					continue;
				}
				
				isVisited[ny][nx] = true; 
				
				Point pNew = new Point(nx, ny, p.move + 1);
				
				queue.offer(pNew);
			}
		}
		
		return minDist;
	}

}
