import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_2458_키순서 {
	static int INF = 999_999_999; // 무한대 값
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 그래프 정점 수(학생 수)
		int P = sc.nextInt(); // 그래프 간선 수(키를 비교한 횟수)
		int[][] graph = new int[N][N]; // 학생 키 정보 그래프(인접행렬)
		
		for(int i = 0; i < P; i++) {
			int start = sc.nextInt() - 1; // 그래프 시작 위치
			int end = sc.nextInt() - 1; // 그래프 종료 위치
			
			graph[start][end] = 1; // 시작 위치 종료 위치 이어줌
		}
		
		// 그래프 연결되어 있지않은 부분 무한대로 설정(플로이드-와샬 알고리즘 사용하기 위해)
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) { // 자기자신으로 가는 비용은 0으로..
					continue;
				}
				
				if(graph[i][j] == 0) { // 그래프가 연결되어 있지않으면 비용 무한대로 설정
					graph[i][j] = INF; 
				}
			}
		}
		
		// 플로이드-와샬 알고리즘을 통해 학생 사이 간 최소비용 경로 구함
		for(int k = 0; k < N; k++ ) { //경유지 k
            for(int i = 0; i < N; i++) {//출발지  i
                if(k == i) {
                    continue;
                }
                for(int j = 0; j < N; j++) {//도착지 j
                    if(j == i || j == k) {
                        continue;
                    }
                    if(graph[i][j] > graph[i][k] + graph[k][j]) {
                    	graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
		
		// 전체 이동가능한 갯수
		int res = 0;
		
		// 그래프 확인용
		for(int i = 0; i < N; i++) {	
			int checkNum = 0; // 연결 가능 학생 수 체크
			
			for(int j = 0; j < N; j++) {
				if(i == j) { // 같은학생 이동은 볼필요 없음..
					continue;
				}
				
				// 학생 i부터 학생 j 까지 이동 가능 또는 학생 j부터 학생 i 까지 이동 가능한 경우
				if(graph[i][j] < INF || graph[j][i] < INF) {
					checkNum++;
				}
			}
			
			if(checkNum == N - 1) { // i번째 학생과 연결되어있음
				res++;
			}
		}
		
		// 전체 이동가능한 갯수 출력
		System.out.println(res);
		
		sc.close();
	}
}
