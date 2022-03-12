package GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1261_알고스팟 {

	static int W; // 맵 가로크기
	static int H; // 맵 세로크기

	static char[][] map;
	static boolean[][] isVisited;
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1};
	
	static int minBrokeCnt; 
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		W = sc.nextInt();
		H = sc.nextInt();
		
		map = new char[H][W];
		isVisited = new boolean[H][W];
		
		for(int i = 0; i < H; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		minBrokeCnt = Integer.MAX_VALUE;
		
		isVisited[0][0] = true;
		bfs();
		//dfs(0, 0, 0);
		
		System.out.println(minBrokeCnt);
		
		sc.close();
	}
	
	static class Point{
		int x;
		int y;
		int cnt;
		Point(int x,int y, int cnt){
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		Queue<Point> queueZero = new LinkedList<>();
		Queue<Point> queueWall = new LinkedList<>();
 		
		queueZero.add(new Point(0, 0, 0));
		
		while(true) {
			Point p;
			
			if(queueZero.isEmpty() == false) {
				p = queueZero.poll();
			}else {
				if(queueWall.isEmpty() == false) {
					p = queueWall.poll();
				}else {
					break;
				}
			}
			
			if(p.x == W - 1 && p.y == H - 1) {
				minBrokeCnt = Math.min(minBrokeCnt, p.cnt);
				break;
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
				
				if(isVisited[ny][nx] == true) {
					continue;
				}
				
				isVisited[ny][nx] = true;
				
				if(map[ny][nx] == '0') {
					queueZero.offer(new Point(nx, ny, p.cnt));
				}else {
					queueWall.offer(new Point(nx, ny, p.cnt + 1));
				}
			}
		}
	}
}
