import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2636_치즈 {
	
	static int[] dx = { 1, -1, 0,  0 }; // 동서남북
	static int[] dy = { 0,  0, 1, -1 }; // 동서남북
	
	static boolean[][] isVisited; // 방문 기록
	static int[][] map; // 맵 데이터
	
	static int W; // 맵 가로크기
	static int H; // 맵 세로크기
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt();
		W = sc.nextInt();
		
		map = new int[H][W];
		isVisited = new boolean[H][W];
		
		// 맵데이터 설정
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int prevCheeaseNum = 0; // 이전 치즈 갯수
		int curCheeaseNum = getCheeaseNum(); // 현재 치즈 갯수
		int timeCnt = 0; // 시간정보
		
		while(curCheeaseNum > 0) {
			prevCheeaseNum = curCheeaseNum; // 이전 치즈 갯수 저장
			
			InitVisit(); // 방문 초기화
			edgeBfs(); // 가장자리 맵데이터를 공기(2)로 설정
			removeCheease(); // 공기와 맞닿은 치즈 삭제
			//printMap(); // 맵정보 출력

			curCheeaseNum = getCheeaseNum(); // 치즈 갯수 설정
			timeCnt++; // 1시간 추가
		}
		
		System.out.println(timeCnt);
		System.out.println(prevCheeaseNum);
		
		sc.close();
	}
	
	// 맵 정보 출력
	public static void printMap() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
	}
	
	// 방문기록 초기화
	public static void InitVisit() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				isVisited[i][j] = false;
			}
		}
	}
	
	// 맵 내에 치즈 갯수 반환
	public static int getCheeaseNum() {
		int cheeaseNum = 0;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 1) {
					cheeaseNum++;
				}
			}
		}
		
		return cheeaseNum;
	}
	
	// 공기에 닿은 치즈 데이터 삭제
	public static void removeCheease() {
		for(int i = 1; i < H - 1; i++) {
			for(int j = 1; j < W - 1; j++) {
				if(map[i][j] == 1) {
					for(int d = 0; d < 4; d++) {
						int nx = j + dx[d];
						int ny = i + dy[d];
						
						if(map[ny][nx] == 2) {
							map[i][j] = 3; // 공기에 닿은 치즈 부분 1 -> 3
						}
					}
				}
			}
		}
		
		// 공기, 공기에 닿은 치즈 데이터 0으로 설정
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 2 || map[i][j] == 3) {
					map[i][j] = 0;
				}
			}
		}
	}
	
	// 가장자리를 모두 공기로 변경
	public static void edgeBfs() {
		// (0, 0) 자리는 무조건 0이므로 (0, 0)부터 시작가능		
		Point startP = new Point(0, 0);
		Queue<Point> queue = new LinkedList<Point>();
		
		queue.add(startP);
		
		while(queue.isEmpty() == false) {
			Point p = queue.poll();
			
			map[p.y][p.x] = 2; // 가장자리 공기 설정 (2)
			
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= W) {
					continue;
				}
				
				if(ny < 0 || ny >= H) {
					continue;
				}
				
				// 치즈 데이터는 무시
				if(map[ny][nx] == 1) {
					continue;
				}
				
				// 이미 방문한 위치는 무시
				if(isVisited[ny][nx] == true) {
					continue;
				}
			
				isVisited[ny][nx] = true;
				
				Point np = new Point(nx, ny);
				queue.offer(np);
			}
		}
	}
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
}
