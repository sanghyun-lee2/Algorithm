package GraphDFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

// "주어진 일이 모두 성공할 확률”의 최댓값을 구하는 프로그램을 작성

public class SWEA_1865_동철이의일분배 {

	static int[][] data; // 데이터 
	static boolean[] isSelected; // 선택
	static int N; // 전체 데이터 수
	
	static double maxPercent; // 승률 최대값 
	
	public static void dfs(int cnt, double percent) {
		if(cnt == N) {
			// 최대 승률 선택
			if(maxPercent < percent) {
				maxPercent = percent;
			}
			return;
		}
		
		// 가지치기.. 최대 승률보다 적어진 경우.. 더이상 계산불필요
		if(maxPercent >= percent) {
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			
			isSelected[i] = true;
			
			// 해당 단계에서의 승률을 구함
			int point = data[cnt][i];
			double newPercent = percent * (point / 100.0);
			
			dfs(cnt + 1, newPercent);
			
			isSelected[i] = false;
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int t = 1; t <= TC; t++) {
			N = sc.nextInt();
			
			data = new int[N][N];
			
			maxPercent = 0.0f;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					data[i][j] = sc.nextInt();
				}
			}
		
			isSelected = new boolean[N];
			
			dfs(0, 1.0f);
			
			double res = maxPercent * 100;
			
			// 소수점 7번째 자리에서 반올림.. 소수점 6번째 자리까지 출력
			System.out.println("#" + t + " " + String.format("%.6f", Math.round(res * 1000000) / 1000000.0));
		}
	
		sc.close();
	}
}
