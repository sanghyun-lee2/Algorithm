import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/*
 * 그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.
입력으로 주어지는 그래프는 하나의 연결 그래프임이 보장된다.

[입력]
가장 첫 번째 줄에는 전체 test case의 수 T(1≤T≤10)가 주어진다. 
각 케이스의 첫째 줄에는 정점의 개수 V(1≤V≤100,000)와 간선의 개수 E(1≤E≤200,000)가 주어진다.
다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다.
C는 음수일 수도 있으며, 절대값이 1,000,000을 넘지 않는다.

[출력]
각 테스트케이스마다 한 줄에 걸쳐, 테스트케이스 수 “#(테스트케이스 번호) “를 출력하고,  최소 스패닝 트리의 가중치를 출력한다.
 * 
 */

/* [SOL]
 * 최소 스패닝 트리를 구하기 위해 크루스칼 알고리즘을 사용한다.
 */

public class SWEA_3124_최소스패닝트리 {
	
	static int V; // 정점의 갯수 
	static int E; // 간선의 갯수  
	static Edge[] edgeList; // 간선 리스트

	static int[] P; // 셋 부모 배열 
		
	public static int findSet(int a) {
		if(a == P[a]) {
			return a;
		}
		
		return P[a] = findSet(P[a]);
	}
	
	public static void makeSet() {
		P = new int[V]; // 정점의 갯수 만큼 부모 배열만듦 
		
		for(int i = 0; i < V; i++) {
			P[i] = i; // 자신의 값을 초기화 설정 
		}
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) {
			return false;
		}
		P[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for(int t = 1; t <= T; t++) {
			V = sc.nextInt(); // 정점 갯수 입력 
			E = sc.nextInt(); // 간선 갯수 입력 
			
			// 간선 데이터 입력 
			edgeList = new Edge[E];
			for(int i = 0; i < E; i++) {
				int start = sc.nextInt() - 1;
				int end = sc.nextInt() - 1;
				long weight = sc.nextLong();
				edgeList[i] = new Edge(start, end, weight);
			}
			
			// 오름차순 정렬 (그리디한 문제 접근)
			Arrays.sort(edgeList); 
			
			// union-find 알고리즘 통해서 MST 생성
			makeSet();
			
			// 간선 하나씩 사이클 여부를 판다하면서 전체 간선 반복
			// 중간에 MST 완성되면 중간에서 break 구문 활용
			int cnt = 0; // 간선의 갯수
			long res = 0; // 최소 가중치 값
			for(int i = 0; i < E; i++) {
				Edge e = edgeList[i];
				if(union(e.start, e.end) == true) {
					res += e.weight;
					cnt++;
					if(cnt == V - 1) {
						break;
					}
				}
			}
			
			System.out.println("#" + t + " " + res);
		}
		
		sc.close();
	}
	
	static class Edge implements Comparable<Edge>{
		int start;
		int end;
		long weight;
		
		public Edge(int start, int end, long weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}
}
