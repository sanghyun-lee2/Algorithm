package SlidingWindow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_2559_수열 {

	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 날짜 수
		int K = sc.nextInt(); // 온도 수열 최대길이
		int[] tempArr = new int[N]; // 온도 배열
		
		int maxTemp = Integer.MIN_VALUE; //최대 온도 순열 합
		
		for(int i = 0; i < N; i++) {
			tempArr[i] = sc.nextInt(); // 온도 배열 입력
		}
		
		for(int i = 0; i < N - K + 1; i++) { // 전체 순열 순회
			int sumTemp = 0;// 온도 순열 합 
			for(int j = i; j < i + K; j++) {
				sumTemp += tempArr[j]; // 온도순열 합 계산
			}
			
			maxTemp = Math.max(maxTemp, sumTemp);// 최대 온도 순열 합 계산
		}
		
		System.out.println(maxTemp);//최대 온도 순열 합 출력
		
		sc.close();
	}

}
