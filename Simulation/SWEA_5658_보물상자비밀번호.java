package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;

/*
 * SWEA 보물상자비밀번호
 * Author : 이상현
 * 푼 날짜 : 2021.12.10
 */

public class SWEA_5658_보물상자비밀번호 {
	
	public static void main(String[] args) throws FileNotFoundException {
		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for(int t = 1; t <= TC; t++) {
			int N = sc.nextInt(); // 문자열 길이
			int K = sc.nextInt(); // 순서
		
			HashSet<String> strNum3List = new HashSet<>();
			
			int sideLen = N / 4; // 사각형 한 변의 길이
			char[] charNum = sc.next().toCharArray();
			
			String strNum = "";
			for(int i = 0; i < charNum.length; i++) {
				strNum += Character.toString(charNum[i]);
			}
			
			// 테이블 사각형 4변으로 문자열을 쪼갬.. 리스트에 추가
			for(int i = 0; i < 4; i++) {
				int start = i * sideLen;
				String s = strNum.substring(start, start + sideLen);
				strNum3List.add(s);
			}
			
			// 테이블 1 ~ 4/N 회전까지 진행
			for(int r = 1; r < sideLen; r++) {
				// 테이블 회전 처리
				char temp = charNum[N - 1];
				for(int j = N - 2; j >= 0; j--) {
					charNum[j + 1] = charNum[j];
				}
				charNum[0] = temp;
				
				// char배열 -> 문자열 변환
				strNum = "";
				for(int i = 0; i < charNum.length; i++) {
					strNum += Character.toString(charNum[i]);
				}
				
				// 테이블 사각형 4변으로 문자열을 쪼갬.. 리스트에 추가
				for(int i = 0; i < 4; i++) {
					int start = i * sideLen;
					String s = strNum.substring(start, start + sideLen);
					strNum3List.add(s);
				}
			}
			
			ArrayList<Integer> intNum3List = new ArrayList<>();
			
			// 10진수로 변환하여 리스트에 새로 추가
			for(String s : strNum3List) {
				int num = strNumToInt(s);
				intNum3List.add(num);
			}
			
			// 내림차순으로 정렬
			intNum3List.sort(Comparator.reverseOrder());
			
			// 리스트의 K-1번째 원소 출력
			int res = intNum3List.get(K - 1);
			System.out.println("#" + t + " " + res);
		}
		
		sc.close();
	}
	
	// 16진수문자열 -> 10진수 숫자로 변환
	public static int strNumToInt(String s) {
		int res = 0;
		int cnt = 1;
		for(int i = s.length() - 1; i >= 0; i--) {
			char c = s.charAt(i);
			int num = 0;
			
			if(c >= '0' && c <= '9') {
				num = c - 48;
			}else if(c >= 'A' && c <= 'F') {
				num = c - 55;
			}
			
			res += num * cnt;
			cnt *= 16;
		}
		
		return res;
	}
}
