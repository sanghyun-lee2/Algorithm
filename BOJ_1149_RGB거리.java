import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * 문제
RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
1번 집의 색은 2번 집의 색과 같지 않아야 한다.
N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
입력
첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
출력
첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
 */

/*
 * 풀이 방법
 * 만약 처음 집을 빨간색으로 선택한 경우, 다음 선택은 파란색,초록색 집 선택가능 두 집중에 최소비용 선택
 * 빨간색, 파란색, 초록색의 3가지 조건을 시작으로 N번째 집까지 최소비용을 선택한 조건 중에
 *  최소를 선택하면 3가지 조건 중에 최소의 조건 찾을 수 있음
 */
public class BOJ_1149_RGB거리 {
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] SumCost = new int[N + 1][3];
		int[][] House = new int[N + 1][3];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < 3; j++) {
				House[i][j] = sc.nextInt();
			}
		}
		
		SumCost[1][0] = House[1][0]; // Red로 칠하는데 필요한 비용
		SumCost[1][1] = House[1][1]; // Blue로 칠하는데 필요한 비용
		SumCost[1][2] = House[1][2]; // Green으로 칠하는데 필요한 비용
		
		for(int i = 2; i <= N; i++) {
			// 자신과 다른 색깔의 이전비용중에 적은 비용선택하고, 자신 색깔 비용 선택 
			SumCost[i][0] = Math.min(SumCost[i - 1][1], SumCost[i - 1][2]) + House[i][0];
			SumCost[i][1] = Math.min(SumCost[i - 1][0], SumCost[i - 1][2]) + House[i][1];
			SumCost[i][2] = Math.min(SumCost[i - 1][0], SumCost[i - 1][1]) + House[i][2];
		}
		
		// RGB중에 가장 적은 Cost 선택
		System.out.println(Math.min(Math.min(SumCost[N][0], SumCost[N][1]), SumCost[N][2]));
		
		sc.close();
	}
}
