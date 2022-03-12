package GraphDFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JO_1681_해밀턴순환회로 {

	static int N; // 정점 수(배달해야 하는 장소의 수)
	static int[][] graph; // 그래프 비용
	
	static boolean[] isVisited;
	
	static int minCost;
	
	static int INF = 999_999_999;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		graph = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				graph[i][j] = sc.nextInt();
			}
		}
		
		isVisited = new boolean[N];
		isVisited[0] = true;
		
		minCost = INF; // 최소비용 값 초기값 설정
		
		dfsGrsph(0, 0, 0);
		
		if(minCost == INF) {
			System.out.println("0");
		}else {
			System.out.println(minCost);
		}
		
		sc.close();
	}
	
	static void dfsGrsph(int vertex, int cnt, int cost) {
		// 가지치기.. 비용이 더 높은 값에 대해 계산 더이상 불필요
		if(minCost < cost) {
			return;
		}
		
		// 기저 조건(모든 정점 순회)
		if(cnt == N - 1) {
			if(graph[vertex][0] > 0) { // 시작지점으로 돌아가는 비용이 존재함
				cost += graph[vertex][0];
				if(minCost > cost) {
					minCost = cost;
				}
				return;
			}
		}
		
		for(int i = 1; i < N; i++){
			if(isVisited[i] == true) {
				continue;
			}
			
			if(graph[vertex][i] == 0) {
				continue;
			}
			
			isVisited[i] = true;
			
			dfsGrsph(i, cnt + 1, graph[vertex][i] + cost);
			
			isVisited[i] = false;
		}
	}
}
