import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SWEA_5643_키순서 {
	
	static int N; // 정점 수 
	static int E; // 간선 수 
	static int[][] graph; // 그래프 
	
	static final int INF = 999_999_999;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // 테스트 케이스 수 
		
		for(int t = 1; t <= T; t++) {
			N = sc.nextInt(); // 정점 수 입력 
			E = sc.nextInt(); // 간선 수 입력 
		
			graph = new int[N][N]; // 그래프 
			
			// 그래프 데이터 초기화
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(i == j) {
						graph[i][j] = 0; // 자기 자신으로 간선은 0 
					}else {
						graph[i][j] = INF; // 나머지 간선 무한대로 설정 
					}
				}
			}
			
			// 그래프 간선 데이터 입력 
			for(int i = 0; i < E; i++) {
				int from = sc.nextInt() - 1;
				int to = sc.nextInt() - 1;
				graph[from][to] = 1;
			}
			
			// 플로이드 와샬 정리 각 정점에서 정점으로의 최소 거리를 계산 
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(graph[i][k] + graph[k][j] < graph[i][j]) {
							graph[i][j] = graph[i][k] + graph[k][j];
						}
					}
				}
			}
			
			int cnt = 0; // 전체 연결 카운트 
			for(int i = 0; i < N; i++) {
				int check = 0; // 경로 확인 
				for(int j = 0; j < N; j++) {
					// i 에서 j로 또는 j에서 i로 갈수 있는 경로가 있다면 이어져 있음!!
					if(graph[i][j] < INF || graph[j][i] < INF) {
						check++; // 경로 확인 증가 
					}
				}
				if(check == N) { // 1 ~ N까지 정점까지 이어져 있다면 전체 연결 카운트 증가!
					cnt++;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
		
		sc.close();
	}
}
