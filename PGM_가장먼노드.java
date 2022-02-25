import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PGM_가장먼노드 {
	
	public static int solution(int N, int[][] edge) {
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i < edge.length; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < edge.length; i++) {
			int from = edge[i][0] - 1;
			int to = edge[i][1] - 1;
			
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		
		// 그래프 1번 정점에서 방문가능한 정점 순회
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.offer(0);
		
		int[] dist = new int[N]; // 방문 횟수(정점별)
		boolean[] isVisit = new boolean[N]; // 왔던 정점 다시 돌아가지 않도록..

		isVisit[0] = true;
		dist[0] = 1;
		
		while(queue.isEmpty() == false) {
			int vertex = queue.poll();
			
			//System.out.println("vertex : " + (vertex + 1));
			//System.out.println(" depth : " + depth);
			
			for(int v : graph.get(vertex)) {
				if(isVisit[v] == false) {
					dist[v] = dist[vertex] + 1;
					isVisit[v] = true; // 해당정점은 방문
					queue.offer(v);
				}
			}
		}
		
		int answer = 0;
		
		int maxDist = 0;
		
		for(int i = 0; i < N; i++) {
			if(maxDist < dist[i]) {
				maxDist = dist[i];
			}
		}
		
		for(int i = 0; i < N; i++) {
			if(maxDist == dist[i]) {
				answer++;
			}
		}
		
        return answer;
    }
	
	public static void main(String[] args) {
		int n = 6;
		int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};
		
		int res = solution(n, edge);
		
		System.out.println(res);
	}

}
