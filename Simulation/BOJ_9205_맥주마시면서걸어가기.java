package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_9205_맥주마시면서걸어가기 {
	static int N; // 편의점 갯수
	static int[][] mapDistance; // 거리정보 맵
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 편의점 갯수
			
			mapDistance = new int[N + 2][N + 2]; // 상근이네 집, 락페스티벌, 편의점 거리 맵
			
			int[] PointX = new int[N + 2]; // X 위치
			int[] PointY = new int[N + 2]; // Y 위치
		
			PointX[0] = sc.nextInt(); // 상근이네 X위치 설정
			PointY[0] = sc.nextInt(); // 상근이네 Y위치 설정
			
			for(int i = 1; i < N + 1; i++) {
				PointX[i] = sc.nextInt(); // 편의점 X위치 설정
				PointY[i] = sc.nextInt(); // 편의점 Y위치 설정
			}
			
			PointX[N + 1] = sc.nextInt(); // 락페 X위치 설정
			PointY[N + 1] = sc.nextInt(); // 락페 Y위치 설정
			
			for(int i = 0; i < N + 2; i++) {
				for(int j = 0; j < N + 2; j++) {
					if(i == j) { // 같은 위치는 구할 필요 없음
						continue; 
					}
					// 상근이네 집, 락페스티벌, 편의점 거리 맵 사이의 거리를 구해서 거리 정보 2차원 맵에 추가
					mapDistance[i][j] = Math.abs(PointX[j] - PointX[i]) + Math.abs(PointY[j] - PointY[i]);
					
					if(mapDistance[i][j] <= 1000) { // 거리가 1000m 이하면 비용이 0으로 설정(맥주먹으면서 이동가능..)
						mapDistance[i][j] = 0;
					}
				}
			}
			
			// 플로이드-와샬 알고리즘을 통해 최소비용 경로 구함
			for(int k = 0; k < N + 2; k++ ) { //경유지 k
	            for(int i = 0; i < N + 2; i++) {//출발지  i
	                if(k == i) {
	                    continue;
	                }
	                for(int j = 0; j < N + 2; j++) {//도착지 j
	                    if(j == i || j == k) {
	                        continue;
	                    }
	                    if(mapDistance[i][j] > mapDistance[i][k] + mapDistance[k][j]) {
	                    	mapDistance[i][j] = mapDistance[i][k] + mapDistance[k][j];
	                    }
	                }
	            }
	        }
			
			// 상근이네 집에서 락페 까지 거리비용이 1000이하이면 도착가능
			if(mapDistance[0][N + 1] <= 1000) {
				System.out.println("happy");
			}else {
				System.out.println("sad");
			}
		}
		
		
		sc.close();
	}
}
