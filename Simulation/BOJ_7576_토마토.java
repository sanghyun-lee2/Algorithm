package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_7576_토마토 {

	static int M; // 상자 가로 크기
	static int N; // 상자 세로 크기
	static int[][] map;
	
	static int maxTime;
	
	static int[] dx = { 1, -1, 0, 0 }; // 동서남북
	static int[] dy = { 0, 0, 1, -1 }; // 동서남북
	
	static class Point{
		int x;
		int y;
		int t;
		Point(int x, int y, int t){
			this.x = x;
			this.y = y;
			this.t = t;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt(); // 가로칸 수
		N = sc.nextInt(); // 세로칸 수
		
		map = new int[N][M];
		maxTime = 0;
		
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				map[n][m] = sc.nextInt();
			}
		}
		
		bfs();
		
		if(getCount() == 0) {
			System.out.println(maxTime);
		}else {
			System.out.println("-1");
		}
		
		sc.close();
	}
	
	public static void printMap() {
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				System.out.print(map[n][m]);
			}
			System.out.println("");
		}
	}
	
	public static int getCount() {
		int cnt = 0;
		// 큐에 익은 토마토 넣어줌..
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void bfs() {
		boolean[][] isVisited = new boolean[N][M];
		
		Queue<Point> queue = new LinkedList<>();
		
		// 큐에 익은 토마토 넣어줌..
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] == 1) {
					queue.offer(new Point(m, n, 0));
				}
			}
		}
		
		while(queue.isEmpty() == false) {
			Point p = queue.poll();
			
			maxTime = Math.max(p.t, maxTime);
			
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= M) {
					continue;
				}
				
				if(ny < 0 || ny >= N) {
					continue;
				}
				
				if(isVisited[ny][nx] == true) {
					continue;
				}
				
				if(map[ny][nx] == -1 || map[ny][nx] == 1) {
					continue;
				}
				
				isVisited[ny][nx] = true;
				map[ny][nx] = 1;
				
				queue.offer(new Point(nx, ny, p.t + 1));				
			}
		}
	}
}
