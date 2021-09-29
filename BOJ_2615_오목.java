import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_2615_오목 {

	static int N;
	static int[][] map;
	
	// 방향 별 돌 갯수 확인
	public static int getSameStone(int color, int x, int y, int dx, int dy) {
		int cntStone = 0;
		while(true) {
			int nx = x + dx;
			int ny = y + dy;
			if(nx < 1 || nx > 19 || ny < 1 || ny > 19) {
				break;
			}
			if(map[ny][nx] != color) {
				break;
			}
			x = nx;
			y = ny;
			cntStone++;
		}
		
		return cntStone;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = 20;
		map = new int[N][N];
		
		for(int i = 1; i <= 19; i++) {
			for(int j = 1; j <= 19; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int winner = 0;
		int winCol = 0;
		int winRow = 0;
		
		exitGame : for(int i = 1; i <= 19; i++) {
			for(int j = 1; j <= 19; j++) {
				if(map[i][j] == 0) {
					continue;
				}
				int color = map[i][j];
				
				int cntHori = 1;
				int cntHoriL = getSameStone(color, j, i, -1, 0);
				int cntHoriR = getSameStone(color, j, i, 1, 0);
				
				int cntVert = 1;
				int cntVertU = getSameStone(color, j, i, 0, -1);
				int cntVertD = getSameStone(color, j, i, 0, 1);

				int cntDiago = 1;
				int cntDiagoL = getSameStone(color, j, i, -1, -1);
				int cntDiagoR = getSameStone(color, j, i, 1, 1);
				
				int cntDiago2 = 1;
				int cntDiagoL2 = getSameStone(color, j, i, -1, 1);
				int cntDiagoR2 = getSameStone(color, j, i, 1, -1);
				
				if(cntHoriL == 0 && cntHoriR + cntHori == 5) {
					winner = color;
					winCol = j;
					winRow = i;
					break exitGame;
				}
				
				if(cntVertU == 0 && cntVertD + cntVert == 5) {
					winner = color;
					winCol = j;
					winRow = i;
					break exitGame;
				}
				
				if(cntDiagoL == 0 && cntDiagoR + cntDiago == 5) {
					winner = color;
					winCol = j;
					winRow = i;
					break exitGame;
				}
				
				if(cntDiagoL2 == 0 && cntDiagoR2 + cntDiago2 == 5) {
					winner = color;
					winCol = j;
					winRow = i;
					break exitGame;
				}
			}
		}
		
		if(winner > 0) {
			System.out.println(winner);
			System.out.println(winRow + " " + winCol);
		}else {
			System.out.println("0");
		}
		
		sc.close();
	}
}
