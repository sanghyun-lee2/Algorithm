package GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3055_탈출 {

	static int R; // 맵 세로크기
	static int C; // 맵 가로크기
	
	static char[][] map; // 맵 데이터 
	
	static int[] dx = { 0,  0, 1, -1 }; // 위 아래 오른쪽 왼쪽  
	static int[] dy = { 1, -1, 0,  0 }; // 위 아래 오른쪽 왼쪽 
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
	
		R = sc.nextInt(); // 맵 세로 크기 
		C = sc.nextInt(); // 맵 가로 크기 
		
		map = new char[R][C];
		
		// 맵 데이터 입력
		for(int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		// 홍수 영역, 고슴도치 이동 bfs로 구현 
		int time = bfs();
		
		if(time == -1) { // 굴에 도달하지 못한 경우 
			System.out.println("KAKTUS");
		}else { // 굴에 도달한 경우 
			System.out.println(time);
		}
		
		sc.close();
	}
	
	// 홍수 위치, 고슴도치 위치 bfs로 계산
	// 반환 값 : 고슴도치가 굴에 도착한 시간 
	public static int bfs() {
		Queue<Point> queueFlood = new LinkedList<>(); // 홍수 위치 Queue
		Queue<Point> queueHedgeHog = new LinkedList<>(); // 고슴도치 위치 Queue
		boolean isVisitedHog[][] = new boolean[R][C]; // 고슴도치 맵 방문 여부
		
		boolean isReachedD = false; // 굴에 도착했는지 여부 
		int time = 0;
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '*') { // 홍수 위치 큐에 저장 
					queueFlood.add(new Point(j, i));
				}
				
				if(map[i][j] == 'S') { // 고슴도치 위치 큐에 저장 
					queueHedgeHog.add(new Point(j, i));
				}
			}
		}
		
		exitWhile : while(true) {
			time++;
		
			// 고슴도치가 굴에 도착하지 못하고 더 이상 이동할 곳이 없다면.... 사망..
			if(queueHedgeHog.size() <= 0) {
				break;
			}
			
			int sizeFloodQueue = queueFlood.size();
			
			// 홍수 위치 이동..
			for(int i = 0; i < sizeFloodQueue; i++) {
				Point p = queueFlood.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					
					if(nx < 0 || nx >= C) {
						continue;
					}
					
					if(ny < 0 || ny >= R) {
						continue;
					}
					
					if(map[ny][nx] == 'X') {
						continue;
					}

					// 빈칸이면 이동 가능.. 이동한 칸을 홍수로 채움 
					if(map[ny][nx] == '.') {
						map[ny][nx] = '*';
						queueFlood.add(new Point(nx, ny));
					}	
				}
			}
			
			int sizeHedgehogSize = queueHedgeHog.size();
			
			// 고슴도치 이동 ~
			for(int i = 0; i < sizeHedgehogSize; i++) {
				Point p = queueHedgeHog.poll();
				
				for(int d = 0; d < 4; d++) {
					int nx = p.x + dx[d];
					int ny = p.y + dy[d];
					
					if(nx < 0 || nx >= C) {
						continue;
					}
					
					if(ny < 0 || ny >= R) {
						continue;
					}
					
					if(map[ny][nx] == 'D') { // 고슴도치가 굴에 도착 
						//System.out.println("탈출!!");
						isReachedD = true;
						break exitWhile;
					}
					
					if(map[ny][nx] == 'X') { // 벽은 지나갈 수 없음 
						continue;
					}
					
					if(map[ny][nx] == '*') { // 홍수는 지나갈 수 없음 
						continue;
					}
					
					if(isVisitedHog[ny][nx] == true) { // 이미 지나온 곳은 가지않
						continue;
					}
					
					isVisitedHog[ny][nx] = true;
					
					// 빈칸이면 고슴도치 이동 가능 -> 이동 이후에 원래 위치는 빈칸으로 변
					if(map[ny][nx] == '.') {
						map[ny][nx] = 'S';
						map[p.y][p.x] = '.';
						queueHedgeHog.add(new Point(nx, ny));
					}
				}
			}
		}
		
		// 굴에 도달하지 못함..
		if(isReachedD == false) {
			time = -1;
		}
		
		// 굴에 도달한 시간 반환 
		return time;
	}
	
	public static void printMap(char[][] map) {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("");
	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
