package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_17135_캐슬디펜스 {

	static int W; // 가로 맵 크기
	static int H; // 세로 맵 크기
	static int[][] OrignMap; // 맵 데이터
	static int D; // 궁수의 공격거리 제한
	
	static int[] Archer; // 궁수 위치 0 ~ W
	static int[] selectArcher; // 궁수 선택 위치 0 ~ W에서 3개 선택
	static boolean[] isSelected; // 궁수 위치 선택 여부
	
	static int maxCntKill; // 최대 죽인 적 수
	
	static class CArcher{
		int x;
		int y;
		int attackX;
		int attackY;
		
		CArcher(int x, int y, int attackX, int attackY){
			this.x = x;
			this.y = y;
			this.attackX = attackX;
			this.attackY = attackY;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
	
		H = sc.nextInt(); // 세로 맵 크기
		W = sc.nextInt(); // 가로 맵 크기
		D = sc.nextInt(); // 궁수 공격거리 제한
		
		OrignMap = new int[H + 1][W]; // 궁수위치 까지 포함한 맵 !!
		Archer = new int[W]; // 궁수 위치 0 ~ W
		selectArcher = new int[3]; // 궁수 선택 위치 0 ~ W에서 3개 선택
		isSelected = new boolean[W]; // 궁수 위치 선택 여부
		
		maxCntKill = 0; // 최대 죽인 적 수
		
		for(int j = 0; j < W; j++) {
			Archer[j] = j; // 궁수 위치 0 ~ W
		}
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) { 
				OrignMap[i][j] = sc.nextInt(); // 궁수 위치 제외 데이터 입력
			}
		}
		
		// 궁수 위치 nCr(가로 맵 크기에서 3개의 위치 선택)
		comb(0, 0);
		
		System.out.println(maxCntKill); // 최대 죽인 적 수 출력
		
		sc.close();
	}
	
	public static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
	
	// 적 공격
	public static int attackEnemy(int[][] gameMap) {
		CArcher[] cArcherArr = new CArcher[3];
		int cntArcher = 0;
		for(int i = 0; i < W; i++) {
			if(gameMap[H][i] == 9) { // 궁수 위치가 9
				cArcherArr[cntArcher++] = new CArcher(i, H, -1, -1);
			}
		}
		
		int cntKill = 0;
		for(int k = 0; k < 3; k++) { // 궁수 3명까지 진행
			int distMin = 999_999_999;
			int selX = -1;
			int selY = -1;
			
			for(int i = 0; i < H; i++) {
				for(int j = 0; j < W; j++) {
					if(gameMap[i][j] == 1) { // 적인 경우
						int dist = getDistance(j, i, cArcherArr[k].x, cArcherArr[k].y);
						
						if(dist <= D) { // 최대 사거리 내에 적이 존재함
							if(distMin > dist) { // 최소거리 갱신
								distMin = dist;
								selX = j;
								selY = i;
							}
							else if(distMin == dist) { // 같은 최소거리라면
								if(j < selX) { // 더 왼쪽 위치의 적 선택
									selX = j;
									selY = i;
								}
							}
						}
					}
				}
			}
			
			if(selX >= 0 && selY >= 0) {
				cArcherArr[k].attackX = selX;
				cArcherArr[k].attackY = selY;
			}
		}
		
		for(int k = 0; k < 3; k++) {
			int x = cArcherArr[k].attackX;
			int y = cArcherArr[k].attackY;
			
			if(x == -1 || y == -1) { // 적이 선택되지 않은 경우는 제외
				continue;
			}
			
			if(gameMap[y][x] == 1) { // 적데이터가 있으면
				gameMap[y][x] = 0; // 적 삭제
				cntKill++; // 킬 수 증가
			}
		}
	
		return cntKill;
	}
	
	// 맵에 적 숫자 반환
	public static int getEnemyCnt(int[][] gameMap) {
		int cntEnemy = 0;
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				if(gameMap[i][j] == 1) {
					cntEnemy++;
				}
			}
		}
		
		return cntEnemy;
	}
	
	// 적 이동(모든 적 1칸 아래로)
	public static void moveEnemy(int[][] gameMap) {
		int[][] newGameMap = new int[H + 1][W];
		
		for(int i = 0; i < H - 1; i++) {
			for(int j = 0; j < W; j++) {
				newGameMap[i + 1][j] = gameMap[i][j];
			}
		}
		
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				gameMap[i][j] = newGameMap[i][j];
			}
		}
	}
	
	// 게임 맵 출력
	public static void printMap(int[][] gameMap) {
		for(int i = 0; i < H + 1; i++) {
			for(int j = 0; j < W; j++) { 
				System.out.print(gameMap[i][j] + " ");
			}
			System.out.println("");
		}
	}
	
	// 게임 맵 생성
	public static void createGameMap(int[][] gameMap) {
		for(int i = 0; i < H; i++) {
			for(int j = 0; j < W; j++) {
				gameMap[i][j] = OrignMap[i][j];
			}
		}
		
		for(int i = 0; i < 3; i++) {
			int idxArcher = selectArcher[i]; // 선택 궁수 위치
			gameMap[H][idxArcher] = 9; // 맵에서 궁수 데이터는 9로 설정
		}
	}
	
	// 궁수 위치 nCr(가로 맵 크기에서 3개의 위치 선택)
	public static void comb(int start, int cnt) {
		if(cnt == 3) { // 3개 위치 선택
			int[][] gameMap = new int[H + 1][W];
			createGameMap(gameMap);
			
			int totalKill = 0;
			while(true) {
//				System.out.println("kill :" + totalKill);
//				System.out.println("");
//				
//				printMap(gameMap);
//				System.out.println("");
//				System.out.println("");
				
				totalKill += attackEnemy(gameMap);
				
//				printMap(gameMap);
//				System.out.println("");
//				System.out.println("");
				
				if(getEnemyCnt(gameMap) == 0) {
					break;
				}
				
				moveEnemy(gameMap); // 적 이동(모든 적 1칸 아래로)
				
				if(getEnemyCnt(gameMap) == 0) {
					break;
				}
				
//				printMap(gameMap);
//				System.out.println("");
//				System.out.println("");
			}
			
			maxCntKill = Math.max(totalKill, maxCntKill);
			
			return;
		}
		
		for(int i = start; i < W; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			
			isSelected[i] = true;
			selectArcher[cnt] = Archer[i]; // 궁수는 데이터 9
			
			comb(++start, cnt + 1);
			
			isSelected[i] = false;
		}
	}
}
