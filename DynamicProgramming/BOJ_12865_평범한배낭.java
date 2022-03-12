package DynamicProgramming;

import java.util.Scanner;

public class BOJ_12865_평범한배낭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 물품의 수
		int K = sc.nextInt(); // 버틸 수 있는 무게
		int[] W = new int[N + 1]; // 물품별 무게
		int[] V = new int[N + 1]; // 물품별 가치
		
		int[][] DP = new int[N + 1][K + 1]; // 
		
		for(int i = 1; i <= N; i++) {
			W[i] = sc.nextInt();
			V[i] = sc.nextInt();
		}
		
		for(int i = 1; i <= N; i++) {
			for(int w = 1; w <= K; w++){
				if(W[i] > w) { // 현재 물건 넣을 수 없으면 이전 값
					DP[i][w] = DP[i-1][w];
				}else { // 넣을 수 있다.
					DP[i][w] = Math.max(DP[i-1][w], DP[i-1][w - W[i]] + V[i]);
				}
			}
		}
		
		System.out.println(DP[N][K]);
		
		sc.close();
	}
}
