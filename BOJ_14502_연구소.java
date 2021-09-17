import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14502_연구소 {

	static int H; // 지도 세로 크기
	static int W; // 지도 가로 크기
	
	static int[][] map; // 지도 데이터
	static ArrayList<Point> pZeroList; // 데이터가 빈칸 인 위치 리스트
	static Point[] pSelZeroArr; // 선택한 빈칸 데이터 위치 리스트(3개)
	
	static boolean[] isSelected; // 위치 선택 여부
	
	static int[] dx = { 1, -1, 0, 0 }; // 동서 남북
	static int[] dy = { 0, 0, 1, -1 }; // 동서 남북
	
	static int maxZeroNum; // 최대 안전지대 수
	
	static class Point{
		int x;
		int y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "Point [x=" + x + ", y=" + y + "]";
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt();
		W = sc.nextInt();
		
		map = new int[H][W];
		pZeroList = new ArrayList<>();
		pSelZeroArr = new Point[3];
		isSelected = new boolean[H * W];
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
				
				if(map[i][j] == 0) { // 빈칸인 위치이면
					pZeroList.add(new Point(j, i)); // 빈칸위치 리스트에 추가
				}
			}
		}
		
		// 조합을 통해 빈칸인 위치 3개를 선택하는 경우의 수를 구함
		comb(0, 0);
		
		System.out.println(maxZeroNum); // 안전지대 수 출력
		
		sc.close();
	}
	
	// 너비우선 탐색으로 맵에 바이러스 퍼뜨리도록 하기
	public static void bfsVirus(int[][] mapAddWall) {
		boolean[][] isVisited = new boolean[H][W];
		
		Queue<Point> queue = new LinkedList<>();
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(mapAddWall[i][j] == 2) { // 바이러스 위치 큐에 넣음
					queue.add(new Point(j, i));
				}
			}
		}
		
		while(queue.isEmpty() == false) {
			Point p = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];
				
				if(nx < 0 || nx >= W) {
					continue;
				}
				
				if(ny < 0 || ny >= H) {
					continue;
				}
				
				if(isVisited[ny][nx] == true) { // 이미 방문한 위치는 방문 하지 않음
					continue;
				}
				
				if(mapAddWall[ny][nx] == 1) { // 벽인 경우에 바이러스 이동 불가능 
					continue;
				}
				
				isVisited[ny][nx] = true; // 방문 설정
				
				if(mapAddWall[ny][nx] == 0) { // 빈칸이면 바이러스가 이동가능함
					mapAddWall[ny][nx] = 2; // 바이러스 퍼뜨렸음
					
					queue.offer(new Point(nx, ny)); // 다음 바이러스 이동
				}
			}
		}
		
		int zeroNum = 0; // 안전지대 갯수
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(mapAddWall[i][j] == 0) { // 빈칸인 지역이면 안전지대
					zeroNum++;
				}
			}
		}
		
		// 최대 안전지대 수 구함
		maxZeroNum = Math.max(zeroNum, maxZeroNum);
	}
	
	// 조합을 통해 0인 위치 3개를 선택하는 경우의 수를 구함
	public static void comb(int cnt, int start) {
		if(cnt == 3) {
			int[][] mapAddWall = new int[H][W];
			
			// 맵데이터 복사 
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					mapAddWall[i][j] = map[i][j];
				}
			}
			
			// 조합 선택한 위치에 벽 세움
			for(int i = 0; i < 3; i++) {
				int x = pSelZeroArr[i].x;
				int y = pSelZeroArr[i].y;
				mapAddWall[y][x] = 1;
			}
			
			bfsVirus(mapAddWall); // 벽 세운 맵 바이러스 퍼뜨림
			return;
		}
		
		int sizeList = pZeroList.size();
		for(int i = start; i < sizeList; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			
			isSelected[i] = true;
			pSelZeroArr[cnt] = pZeroList.get(i);
			
			comb(cnt + 1, start++);
			
			isSelected[i] = false;
		}
	}
}
