import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * <그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다.
 *  철수는 이 지도를 가지고 연결된 집의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다.
 *   여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 
 *   대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다.
 *   지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.
 */

public class BOJ_2667_단지번호붙이기 {
	static char[][] map; // 맵 데이터 
	static boolean[][] isVisited; // 방문 여부 
	static ArrayList<Integer> houseArr; // 단지 내 집 갯수 배
	static int N; // 맵 크기
	
	static int sumHouseDanji; // 단지 내 집 갯수 
	
	static int[] dx = { 0, 0, -1, 1};
	static int[] dy = { -1, 1, 0, 0};
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		houseArr = new ArrayList<>();
		
		N = sc.nextInt();
		
		map = new char[N][N];
		isVisited = new boolean[N][N];
		
		// 맵 데이터 설정 
		for(int i = 0; i < N; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sumHouseDanji = 0;
				dfs(j, i);
				
				// 단지 내 집 정보가 존재하면 배열에 추가 
				if(sumHouseDanji > 0) {
					houseArr.add(sumHouseDanji);
				}
			}
		}
		
		// 오름차순 정렬 
		houseArr.sort(null);
		
		// 단지 갯수 출력 
		System.out.println(houseArr.size());
		
		// 단지 내 집 갯수 출력 
		for(int i = 0; i < houseArr.size(); i++) {
			System.out.println(houseArr.get(i));
		}
		
		sc.close();
	}
	
	static public void dfs(int x, int y) {
		// 집이 아닌 경우 제외 
		if(map[y][x] == '0') {
			return;
		}
		
		// 방문하지 않은 집이면 단지에 추가 
		if(isVisited[y][x] == false) {
			sumHouseDanji++;
		}
		
		// 해당 위치의 집은 방문 
		isVisited[y][x] = true;
		
		// 4방향 동서남북 탐색 
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx < 0 || nx >= N) {
				continue;
			}
			
			if(ny < 0 || ny >= N) {
				continue;
			}
			
			if(map[ny][nx] == '0') {
				continue;
			}
			
			if(isVisited[ny][nx] == true) {
				continue;
			}
			
			dfs(nx, ny);
		}
	}
}
