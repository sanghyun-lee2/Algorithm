import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2206_벽부수고이동하기 {

	static int W; // 맵 가로크기
	static int H; // 맵 세로크기
	
	static char[][] map;
	static boolean[][][] isVisited;
	
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1};
	
	static int minDist; 
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt();
		W = sc.nextInt();
		
		map = new char[H][W];
		isVisited = new boolean[H][W][2];
		
		for(int i = 0; i < H; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		minDist = Integer.MAX_VALUE;
		
		bfs();
		
		if(minDist == Integer.MAX_VALUE) { // 목적지에 도달하지 못함 
			System.out.println("-1");
		}else {
			System.out.println(minDist);
		}
		
		sc.close();
	}
	
	static class Point{
		int x;
		int y;
		int dist;
		int cnt;
		Point(int x,int y, int dist, int cnt){
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.cnt = cnt;
		}
	}
	
	public static void bfs() {
		Queue<Point> queue = new LinkedList<>();

		isVisited[0][0][0] = true;
		queue.add(new Point(0, 0, 1, 0));
		
		while(queue.isEmpty() == false) {
			Point p = queue.poll();
			
			if(p.x == W - 1 && p.y == H - 1) {
				minDist = Math.min(minDist, p.dist);
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
				
				if(isVisited[ny][nx][p.cnt] == true) {
					continue;
				}
				
				if(map[ny][nx] == '1' && p.cnt >= 1) { // 1번이상 벽부수면 더이상 벽못부숨 
					continue;
				}
				
				isVisited[ny][nx][p.cnt] = true;
				
				if(map[ny][nx] == '0') {
					queue.offer(new Point(nx, ny, p.dist + 1, p.cnt));
				}else if(p.cnt <= 1){
					queue.offer(new Point(nx, ny, p.dist + 1, p.cnt + 1));
				}
			}
		}
	}
}
