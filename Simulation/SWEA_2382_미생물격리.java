package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_2382_미생물격리 {
	static int N;
	
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	
	static class Microbe implements Comparable<Microbe>{
		int x; // 미생물 위치 X
		int y; // 미생물 위치 Y
		int num; // 미생물 갯수
		int dir; // 군집 방향 (상1,하2,좌3,우4)
		int loc; // 미생물 위치(X * N + Y)
		
		public Microbe() {
		}

		@Override
		public int compareTo(Microbe o) {
			if(this.loc == o.loc) { // 위치가 같은 경우
				return o.num - this.num; // 미생물 수를 기준으로 내림차순 정렬
			}
			return this.loc - o.loc; // 위치가 다른경우 위치를 기준으로 오름차순 정렬
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt(); // 총 테스트 케이스 수
		for(int t = 1; t <= TC; t++) {
			N = sc.nextInt(); // 맵 크기 가로세로
			int M = sc.nextInt(); // 시간 
			int K = sc.nextInt(); // 미생물 군집 갯수
			
			ArrayList<Microbe> mlist = new ArrayList<>();
			
			// 미생물 군집리스트 입력
			for(int i = 0; i < K; i++) {
				Microbe m = new Microbe();
				
				m.y = sc.nextInt();
				m.x = sc.nextInt();
				m.num = sc.nextInt();
				m.dir = sc.nextInt();
				
				mlist.add(m);
			}
			
			for(int i = 0; i < M; i++) {
				moveMicro(mlist); // 시간별로 미생물 군집 이동
				combineMicro(mlist); // 시간별로 미생물 군집 결합
			}
			
			// 미생물 총 갯수 출력
			int res = 0;
			for(Microbe m : mlist) {
				res += m.num;
			}
			System.out.println("#" + t + " " + res);
		}
		
		sc.close();
	}
	
	// 시간별로 미생물 군집 이동
	public static void moveMicro(ArrayList<Microbe> mlist) {
		// 미생물 군집 이동 처리
		for(int i = 0; i < mlist.size(); i++) {
			Microbe m = mlist.get(i);
			
			// 맵바깥으로 나가는 경우는 고려하지 않음
			m.x += dx[m.dir]; // 새 x위치 구함
			m.y += dy[m.dir]; // 새 y위치 구함
			
			if(m.y == 0) { // 위 약품에 닿인경우
				m.dir = 2; // 아래로 방향 전환
				m.num /= 2; // 미생물수 반으로 줄어듬
			}
			if(m.y == N - 1) { // 아래 약품에 닿인경우
				m.dir = 1; // 위로 방향 전환
				m.num /= 2; // 미생물수 반으로 줄어듬
			}
			if(m.x == 0) { // 왼쪽 약품에 닿인경우
				m.dir = 4; // 우로 방향 전환
				m.num /= 2; // 미생물수 반으로 줄어듬
			}
			if(m.x == N - 1) { // 오른쪽 약품에 닿인경우
				m.dir = 3; // 좌로 방향 전환
				m.num /= 2; // 미생물수 반으로 줄어듬
			}
			
			m.loc = m.x * N + m.y;
		}
	}
	
	// 시간별로 미생물 군집 결합
	static void combineMicro(ArrayList<Microbe> mlist) {
		// 미생물 리스트 정렬
		mlist.sort(null);
		
		for(int i = 0; i < mlist.size() - 1; i++) {
			Microbe cur = mlist.get(i);
			Microbe next = mlist.get(i + 1);
			
			// 위치가 같다면
			if(cur.loc == next.loc) {
				// 다음 미생물 군락과 값을 합침
				cur.num += next.num;
				mlist.remove(i + 1); // 다음 미생물 군락 삭제
				i--;
			}
		}
	}
}
