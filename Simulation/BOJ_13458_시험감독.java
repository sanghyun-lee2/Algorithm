package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_13458_시험감독 {
    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("input.txt")); // 입력 데이터 설정
        Scanner sc = new Scanner(System.in); // 스캐너 할당

        long res = 0; // 응시생을 모두 감독하기 위해 필요한 감독관의 최소 수

        int cntRoom = sc.nextInt(); // 시험장 갯수

        long[] cntCandArr = new long[cntRoom]; // 시험장 별 응시자 수 배열

        for(int i = 0; i < cntRoom; i++) {
            cntCandArr[i] = sc.nextInt(); // 시험장 별 응시자 수
        }

        long firstDirectorCnt = sc.nextInt(); // 총감독 담당 응시자 수
        long secondDirectorCnt = sc.nextInt(); // 부감독 담당 응시자 수

        for(int i = 0; i < cntRoom; i++){
            long cntCand = cntCandArr[i]; // n번째 시험장 응시자 수
            cntCand -= firstDirectorCnt; // 총감독 응시자 수 제외

            long directorCnt = 1; // 총감독 1명

            if(cntCand > 0) {
                // 응시자 / 부감독 담당 응시자 수 = 부감독 수
                directorCnt += (cntCand / secondDirectorCnt);

                // 나머지가 있는 경우 부감독 수 증가
                if(cntCand % secondDirectorCnt > 0){
                    directorCnt += 1;
                }
            }

            res += directorCnt; // 시험장 별 감독관 수 추가
        }

        System.out.println(res); // 모든 감독관 수 출력

        sc.close(); // 스캐너 닫기
    }
}