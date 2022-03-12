package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_12100_2048Easy {
	
	static int N; // 맵 크기
	static int[][] map; // 맵 데이터
	static int res; // 5번 이동 후 최대 블록 크기
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		// 전역변수 초기화
		N = sc.nextInt();
		map = new int[N][N];
		res = 0;
		
		// 맵데이터 입력
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		dfs(0);
		System.out.println(res);
		
		sc.close();
	}
	
	public static void dfs(int cnt) {
		if(cnt == 5) { // 5번움직이면 종료!
			calMaxVal(); // 최대 블록크기 계산
			return;
		}
		
		int[][] clone = new int[N][N];
		for(int i = 0; i < N; i++) {
			clone[i] = map[i].clone();
		}
		
		// 동서남북으로 퍼즐판을 움직임!
		for(int d = 0; d < 4; d++) {
			move(d);
			dfs(cnt + 1);
			
			// 맵 원래대로 돌려놓음!
			for(int i = 0; i < N; i++) {
				map[i] = clone[i].clone(); 
			}
		}
	}
	
	public static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
	
	public static void move(int dir) {
		if(dir == 0) { // 동쪽이동
			for(int i = 0; i < N; i++) {
				ArrayList<Integer> list = new ArrayList<>();
				for(int j = N - 1; j >= 0; j--) {
					if(map[i][j] > 0) {
						list.add(map[i][j]);
					}
					map[i][j] = 0;
				}
				
				// 리스트 순회
				for(int j = 0; j < list.size() - 1; j++) {
					// 같은 번호라면 합침!
					if((int)list.get(j) == (int)list.get(j + 1)) { 
						list.set(j, list.get(j) + list.get(j + 1)); // 더해줌
						list.remove(j + 1); // 뒤에부분 삭제
					}
				}
				
				for(int j = 0; j < list.size(); j++) {
					map[i][N - j - 1] = list.get(j);
				}
			}
		}else if(dir == 1) { // 서쪽 이동
			for(int i = 0; i < N; i++) {
				ArrayList<Integer> list = new ArrayList<>();
				for(int j = 0; j < N; j++) {
					if(map[i][j] > 0) {
						list.add(map[i][j]);
					}
					map[i][j] = 0;
				}
				
				// 리스트 순회
				for(int j = 0; j < list.size() - 1; j++) {
					// 같은 번호라면 합침!
					if((int)list.get(j) == (int)list.get(j + 1)) { 
						list.set(j, list.get(j) + list.get(j + 1)); // 더해줌	
						list.remove(j + 1); // 뒤에부분 삭제
					}
				}
				
				for(int j = 0; j < list.size(); j++) {
					map[i][j] = list.get(j);
				}
			}
		}else if(dir == 2) { // 남쪽 이동
			for(int j = 0; j < N; j++) {
				ArrayList<Integer> list = new ArrayList<>();
				for(int i = N - 1; i >= 0; i--) {
					if(map[i][j] > 0) {
						list.add(map[i][j]);
					}
					map[i][j] = 0;
				}
				
				// 리스트 순회
				for(int i = 0; i < list.size() - 1; i++) {
					// 같은 번호라면 합침!
					if((int)list.get(i) == (int)list.get(i + 1)) { 
						list.set(i, list.get(i) + list.get(i + 1)); // 더해줌
						list.remove(i + 1); // 뒤에부분 삭제
					}
				}
				
				for(int i = 0; i < list.size(); i++) {
					map[N - i - 1][j] = list.get(i);
				}
			}
		}else if(dir == 3) { // 북쪽 이동
			for(int j = 0; j < N; j++) {
				ArrayList<Integer> list = new ArrayList<>();
				for(int i = 0; i < N; i++) {
					if(map[i][j] > 0) {
						list.add(map[i][j]);
					}
					map[i][j] = 0;
				}
				
				// 리스트 순회
				for(int i = 0; i < list.size() - 1; i++) {
					// 같은 번호라면 합침!
					if((int)list.get(i) == (int)list.get(i + 1)) { 
						list.set(i, list.get(i) + list.get(i + 1)); // 더해줌
						list.remove(i + 1); // 뒤에부분 삭제
					}
				}
				
				for(int i = 0; i < list.size(); i++) {
					map[i][j] = list.get(i);
				}
			}
		}
	}
	
	// 최대 블록크기 계산
	public static void calMaxVal() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int val = map[i][j];
				if(res < val) {
					res = val;
				}
			}
		}
	}
}
