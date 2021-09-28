import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2846_오르막길 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 언덕 수
		
		int maxHill = 0; // 오르막길 최대 높이
		int[] hillArr = new int[N]; // 언덕 데이터
		
		// 데이터 설정
		for(int i = 0; i < N; i++) {
			hillArr[i] = sc.nextInt();
		}
		
		ArrayList<Integer> list = new ArrayList<>(); // 수열 정보
		
		for(int i = 0; i < N; i++) { // 배열전체 순회
			int listLen = list.size(); // 수열리스트 길이 가져옴
			if(listLen == 0) { // 수열정보가 비어있는 경우
				list.add(hillArr[i]); // 수열 리스트에 그대로 추가
			}else {
				if(list.get(listLen - 1) < hillArr[i]) { // 증가수열인 경우
					list.add(hillArr[i]); // 수열 리스트에 그대로 추가
				}else {
					// 수열 리스트의 마지막과 처음값을 빼서 오르막길 높이를 구함
					int Hill = list.get(listLen - 1) - list.get(0);
					maxHill = Math.max(Hill,  maxHill); // 오르막길 최대 높이 설정
					list.clear(); // 수열정보 초기화
					list.add(hillArr[i]); // 새로운 수 추가
				}
			}
		}
		
		// 수열정보가 남아있는 경우 처리
		int listLen = list.size();
		if(listLen > 0) {
			// 수열 리스트의 마지막과 처음값을 빼서 오르막길 높이를 구함
			int Hill = list.get(listLen - 1) - list.get(0);
			maxHill = Math.max(Hill,  maxHill); // 오르막길 최대 높이 설정
		}
		
		System.out.println(maxHill); // 오르막길 최대 높이 출력
		
		sc.close();
	}

}
