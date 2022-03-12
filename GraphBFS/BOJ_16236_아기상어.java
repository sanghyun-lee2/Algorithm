package GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_16236_아기상어 {

	static int N; // 맵 크기 (가로, 세로)
	static int[][] map; // 맵 데이터
	static int time; // 시간
	
	static int[] dx = {1, -1, 0, 0}; // 동서남북
	static int[] dy = {0, 0, 1, -1}; // 동서남북
	
	static Fish m_shark; // 상어 클래스
	
	public static int bfs() {
		boolean[][] isVisited = new boolean[N][N];
		Queue<Fish> queue = new LinkedList<Fish>();
		boolean isReachTarget = false; // 표적 도달여부
		
		Point prevPoint = new Point(m_shark.x, m_shark.y);
		m_shark.move = 0; // 상어 이동거리..
		queue.add(m_shark); // 초기 상어 위치 넣어줌../
		
		ArrayList<Point> pList = new ArrayList<>();
		int minMove = Integer.MAX_VALUE;
		
		while(queue.isEmpty() == false) {
			Fish shark = queue.poll();
			
			Point pShark = new Point(shark.x, shark.y);
			int mapShark = map[pShark.y][pShark.x];
			
			if(	mapShark >= 1 && mapShark < 9) // 물고기
			{
				if(mapShark <= m_shark.size - 1) { // 상어크기보다 작은 물고기
					if(minMove == shark.move) { // 기존 물고기 최단거리와 동일한 거리에 물고기가 존재함..
						pList.add(pShark); // 
					}
					else if(minMove > shark.move) {
						pList.clear();
						pList.add(pShark);
						minMove = shark.move;
					}
					isReachTarget = true;
				}	
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = shark.x + dx[d];
				int ny = shark.y + dy[d];
				int size = shark.size;
				
				if(nx < 0 || nx >= N) {
					continue;
				}
				
				if(ny < 0 || ny >= N) {
					continue;
				}
				
				if(map[ny][nx] > size) {
					continue;
				}
				
				if(isVisited[ny][nx] == true) {
					continue;
				}
				
				isVisited[ny][nx] = true;
				
				if(map[ny][nx] <= size) {
					queue.add(new Fish(nx, ny, shark.size, shark.eat, shark.move + 1));
				}
			}
		}
		
		Collections.sort(pList); // 같은 거리에 먹잇감 물고기가 있는 경우, 먹잇감 물고기 위치를 결정하기 위해 정렬 
		
		if(isReachTarget == false) { // 먹잇감 물고기에 도달하지 못하였음..
			minMove = -1;
		}else {
			Point pNew = pList.get(0); // 가장 가깝고, 가장 위 왼쪽 지점 물고기 위치
			
			//if(map[pNew.y][pNew.x] == m_shark.size - 1) {
			m_shark.eat += 1;
			if(m_shark.eat >= m_shark.size) {
				m_shark.size++;
				m_shark.eat = 0;
			}
			//}
			
			map[prevPoint.y][prevPoint.x] = 0;
			map[pNew.y][pNew.x] = m_shark.size + 7; 
		}
	
		return minMove;
	}

	// 상어위치 계산
	public static void FindShark() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] >= 9) {
					m_shark.x = j;
					m_shark.y = i;
					m_shark.setSize(map[i][j] - 7);
				}
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		map = new int[N][N];
		m_shark = new Fish();
		
		// 맵데이터 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		while(true) {
//			printMap();
//			System.out.println("");
//			System.out.println("");
			
			FindShark(); // 상어위치 계산
			
			int dist = bfs(); // 먹이 물고기까지 이동거리 반환
			if(dist == -1) { // 이동하지 않은경우.. 먹이물고기를 찾지못했음
				break;
			}
			
			time += dist; // 시간은 거리 더한 값
			
//			System.out.println(dist);
//			System.out.println("");
//			System.out.println("");
		}
		
		System.out.println(time);
		
		sc.close();
	}
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		
		Point(){
			
		}
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			int result = 0;
			
			if(this.y == o.y) { // 비교위치와 y위치가 동일한 상황.. x위치로 비교해야함
				if(this.x > o.x) { // 비교위치보다 x위치가 높은경우
					result = 1;
				}else if(this.x < o.x) { // 비교위치보다 x위치가 낮은경우
					result = -1;
				}
				// 아니면 같다..
			}else if(this.y > o.y) {
				result = 1;
			}else {
				result = -1;
			}
			
			return result;
		}
	}
	
	static class Fish{
		int x;
		int y;
		int size;
		int eat;
		int move;
		
		Fish(){
		}
		
		Fish(int x, int y, int size, int eat, int move){
			this.x = x;
			this.y = y;
			this.size = size;
			this.eat = eat;
			this.move = move;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}
	
		public int getEat() {
			return eat;
		}

		public void setEat(int eat) {
			this.eat = eat;
		}
		
		public int getMove() {
			return move;
		}

		public void setMove(int move) {
			this.move = move;
		}
		
		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
	
	public static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println("");
		}
	}
}
