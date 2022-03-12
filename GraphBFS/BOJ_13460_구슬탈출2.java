package GraphBFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 완전탐색으로 풀이!
 */

public class BOJ_13460_구슬탈출2 {
	
	static int W; // 너비
	static int H; // 높이
	static char[][] map; // 맵
	
	static int[] dx = { 1, -1, 0, 0 }; // 동서남북
	static int[] dy = { 0, 0, 1, -1 }; // 동서남북
	
	static boolean[][][][] isVisit;
	
	static class Node{
		int redX; // 빨간공 위치 x
		int redY; // 빨간공 위치 y
		int blueX; // 파란공 위치 x
		int blueY; // 파란공 위치 y
		int move; // 이동 횟수
		Node(){
			
		}
		
		Node(int blueX, int blueY, int redX, int redY, int move){
			this.blueX = blueX;
			this.blueY = blueY;
			this.redX = redX;
			this.redY = redY;
			this.move = move;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		H = sc.nextInt(); // 높이 입력
		W = sc.nextInt(); // 너비 입력
		
		map = new char[H][W];
		isVisit = new boolean[H][W][H][W];
		
		for(int r = 0; r < H; r++) {
			map[r] = sc.next().toCharArray();
		}
		
		Node node = new Node();
		node.move = 0;
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(map[i][j] == 'R') {
					node.redX = j;
					node.redY = i;
				}else if(map[i][j] == 'B') {
					node.blueX = j;
					node.blueY = i;
				}
			}
		}
	
		bfs(node);
		
		sc.close();
	}
	
	public static void printMap() {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println("");
		}
		
		System.out.println("");
		System.out.println("");
	}

	public static void bfs(Node node) {
		Queue<Node> queue = new LinkedList<>();
		
		queue.offer(node);
		
		while(queue.isEmpty() == false) {
			Node n = queue.poll();
			
			isVisit[n.blueY][n.blueX][n.redY][n.redX] = true;
			
			if(n.move >= 10) {
				System.out.println(-1);
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				// 일단 파란색 공 먼저 굴린다.
				int nbx = n.blueX;
				int nby = n.blueY;
				while(true) {
					nbx += dx[d];
					nby += dy[d];
					
					if(map[nby][nbx] == '#') {
						nbx -= dx[d];
						nby -= dy[d];
						break;
					}
					
					// 벽 또는 출구를 만나면 멈춘다.
					if(map[nby][nbx] == 'O') {
						break;
					}
				}
				
				// 다음 빨간색 공 먼저 굴린다.
				int nrx = n.redX;
				int nry = n.redY;
				while(true) {
					nrx += dx[d];
					nry += dy[d];
					
					if(map[nry][nrx] == '#') {
						nrx -= dx[d];
						nry -= dy[d];
						break;
					}
					
					// 벽 또는 출구를 만나면 멈춘다.
					if(map[nry][nrx] == 'O') {
						break;
					}
				}
				
				// 파란색 구슬 출구로 빠졌는지 확인
				if(map[nby][nbx] == 'O') {
					continue; // 정답이 아님
				}
				
				// 빨간색 구슬만 출구로 빠졌다면, 이동횟수 출력하고 종료
				if(map[nry][nrx] == 'O') {
					System.out.println(n.move + 1);
					return;
				}
				
				// 빨간색, 파란색구슬이 같은 위치에 있는경우.. 위치조정
				if(nry == nby && nrx == nbx) {
					if(d == 0) {
						// 동쪽
						if(n.redX > n.blueX)
							nbx -= 1;
						else
							nrx -= 1;
					}
					else if(d == 1) { 
						// 서쪽
						if(n.redX < n.blueX)
							nbx += 1;
						else
							nrx += 1;
					}
					else if(d == 2) { 
						// 남쪽
						if(n.redY > n.blueY)
							nby -= 1;
						else 
							nry -= 1;
					}
					else if(d == 3) {
						// 북쪽
						if(n.redY < n.blueY)
							nby += 1;
						else 
							nry += 1;
					}
				}
				
				if(isVisit[nby][nbx][nry][nrx] == false) {
					Node newNode = new Node(nbx, nby, nrx, nry, n.move + 1);
					queue.offer(newNode);
				}
			}
		}
		System.out.println(-1);
	}
}
