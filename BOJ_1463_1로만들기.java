import java.util.Scanner;

/*
 * 문제
정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
X가 3으로 나누어 떨어지면, 3으로 나눈다.
X가 2로 나누어 떨어지면, 2로 나눈다.
1을 뺀다.
정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만들려고 한다. 연산을 사용하는 횟수의 최솟값을 출력하시오.
입력
첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 정수 N이 주어진다.
출력
첫째 줄에 연산을 하는 횟수의 최솟값을 출력한다.
 */
public class BOJ_1463_1로만들기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[] DP = new int[N + 1];
		
		DP[1] = 0; // 1은 1이되기위해서 더이상 계산 불필요~
		// DP[2] = 1; // 2를 1로 만들기 위한 최소 비용 (1로 뺀경우 or 2로 나눈경우)
		// DP[3] = 1; // 3을 1로 만들기 위한 최소 비용 (3으로 나눈경우)
		// DP[4] = 2; // 4를 1로 만들기 위한 최소 비용 (2로 나눈경우)
		// DP[5] = 3; // 5를 1로 만들기 위한 최소 비용 (1로 뺀경우)
		
		for(int i = 2; i <= N; i++) {
			// 1로 뺀 경우, 2로 나누어 떨어지는 경우, 3으로 나누어 떨어진 경우의 최소값 구함
			DP[i] = DP[i - 1] + 1; 
			if(i % 3 == 0) {
				DP[i] = Math.min(DP[i], DP[i / 3] + 1); 
			}
			if(i % 2 == 0) {
				DP[i] = Math.min(DP[i], DP[i / 2] + 1);
			}
		}
		
		System.out.println(DP[N]);
		
		sc.close();
	}
}
