import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_3055_탈출 {

	static int R; // 맵 세로크기
	static int C; // 맵 가로크기
	
	static char[][] map;
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
	
		R = sc.nextInt();
		C = sc.nextInt();
		
		map = new char[R][C];
		
		// 맵 데이터 입력
		for(int i = 0; i < R; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		sc.close();
	}
	
	public static void bfs() {
		
	}
}
