import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SWEA_점심식사시간 {
	
	static class Person{
		int r; // 행
		int c; // 열
		int stair; // 선택 계단
		int arriveTime; // 도착 시간
		int stairTime; // 계단 내려가는 시간
		
		Person(int r, int c){
			this.r = r;
			this.c = c;
		}
		
		public void calArriveTime() {
			this.arriveTime = Math.abs(this.r - stairs.get(this.stair).r) +
					Math.abs(this.c - stairs.get(this.stair).c);
		}
	}
	
	static class Stair{
		int r; // 행
		int c; // 열
		int k; // 계단길이
		
		Stair(int r, int c, int k){
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}
	
	static ArrayList<Person> persons;
	static ArrayList<Stair> stairs;
	static boolean[] isReachStair; // 계단 도달여부
	static Queue<Person>[] qs;
	
	static int res; // 결과시간(분)
	
	public static void main(String[] args) {
		//System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		for(int t = 1; t <= TC; t++) {
			res = Integer.MAX_VALUE;
			
			qs = new LinkedList[2];
			qs[0] = new LinkedList<Person>();
			qs[1] = new LinkedList<Person>();
			
			persons = new ArrayList<>();
			stairs = new ArrayList<>();
			
			int N = sc.nextInt();
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					int data = sc.nextInt();
					
					if(data == 0) { // 빈공간
						continue; 
					}else if(data == 1) { // 사람
						persons.add(new Person(r, c));
					}else {
						stairs.add(new Stair(r, c, data));
					}
				}
			}
			
			dfs(0);
			
			System.out.println("#" + t + " " + res);
		}
		
		sc.close();
	}
	
	public static void dfs(int cnt) {
		if(cnt == persons.size()) {
			isReachStair = new boolean[persons.size()];
			
			int time = simulation();
			
			if(res > time) {
				res = time;
			}
			return;
		}
		
		persons.get(cnt).stair = 0;
		persons.get(cnt).calArriveTime();
		dfs(cnt + 1);
		
		persons.get(cnt).stair = 1;
		persons.get(cnt).calArriveTime();
		dfs(cnt + 1);
	}
	
	public static int simulation() {
		int time = 1;
		int cnt = 0;
		
		while(true) {
			// 계단에 도착한 사람들이 내려가는 것
			for(Queue<Person> q : qs) {
				int size = q.size();
				
				for(int i = 0; i < size; i++) {
					Person p = q.poll();
					
					if(p.stairTime + stairs.get(p.stair).k <= time) {
						continue;
					}
					
					q.offer(p);
				}
			}

			// 모든사람이 계단에 도착하고, 계단이 다 비어있는상태이면 모두 내려간상태
			if(cnt == persons.size() && qs[0].isEmpty() == true && qs[1].isEmpty() == true) {
				break;
			}
			
			// 계단에 도착할 때 까지 이동
			// 계단에 도착하면 큐에 넣음
			for(int i = 0; i < persons.size(); i++) {
				if(isReachStair[i] == false) {
					Person p = persons.get(i);
					Queue<Person> q = qs[p.stair];
					
					if(time > p.arriveTime && q.size() < 3) {
						p.stairTime = time;
						isReachStair[i] = true;
						q.offer(p);
						cnt++;
					}
				}
			}
			
			time++;
		}
		
		return time;
	}
}
