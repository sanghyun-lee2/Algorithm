import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class BOJ_2606_바이러스 {
	
	static int[][] graph; // 인접행렬 그래프
	static int N; // 컴퓨터 수
	static int P; // 간선 수

	static boolean[] isVisited;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // 컴퓨터 수 입력
		P = sc.nextInt(); // 간선 수 입력
		graph = new int[N][N]; // 그래프 
		isVisited = new boolean[N];

		// 그래프
		for(int i = 0; i < P; i++) {
			int from = sc.nextInt() - 1;
			int to = sc.nextInt() - 1;
			
			graph[from][to] = 1;
			graph[to][from] = 1;
		}
		
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < N; j++) {
//				System.out.print(graph[i][j]);
//			}
//			System.out.println("");
//		}
		
		int cnt = depthDFS(0);
		System.out.println(cnt);
		
		sc.close();
	}
	
	// 시작점 위치로 깊이우선 탐색
	public static int depthDFS(int vertex) {
		Stack<Integer> stack = new Stack<>();
		
		stack.push(vertex);
		
		while(stack.isEmpty() == false) {
			int v = stack.pop();
			
			if(isVisited[v] == true) {
				continue;
			}
			
			isVisited[v] = true;
			
			for(int i = N -1; i >= 0; i--) {
				if(graph[v][i] == 1) {
					if(isVisited[i] == false) {
						stack.push(i);
					}
				}
			}
		}
		
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			if(i == vertex) {
				continue;
			}
			if(isVisited[i] == true) {
				cnt++;
			}
		}
		
		return cnt;
	}
}
