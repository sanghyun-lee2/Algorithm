package Simulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_14499_주사위굴리기 {
    static int N; // 지도 세로 크기
    static int M; // 지도 가로 크기
    static int[][] map; // 지도 데이터
    static int[] dice; // 주사위 데이터
    static int diceX; // 주사위 위치 X
    static int diceY; // 주사위 위치 Y

    static int[] dx = { 0, 1, -1, 0, 0 }; // 동서북남 x 이동데이터
    static int[] dy = { 0, 0, 0, -1, 1 }; // 동서북남 y 이동데이터

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt(); // 지도 세로 크기 입력
        M = sc.nextInt(); // 지도 가로 크기 입력
        map = new int[N][M]; // 지도 데이터
        dice = new int[7]; // 정육면체 데이터

        diceY = sc.nextInt(); // 주사위 위치 X
        diceX = sc.nextInt(); // 주사위 위치 Y
        int K = sc.nextInt(); // 이동 횟수

        // 맵데이터 입력
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = sc.nextInt();
            }
        }

        // 주사위 이동 처리
        for(int i = 0; i < K; i++){
            int dir = sc.nextInt();
            boolean res = move(dir); // 주사위 이동 처리
            if(res == true) {
                System.out.println(dice[1]); // 주사위 윗면 출력
            }
        }
    }

    // 주사위 이동 처리(1:동, 2:서, 3:북, 4:남)
    public static boolean move(int dir){
        int nx = diceX + dx[dir]; // 새 x위치 계산
        int ny = diceY + dy[dir]; // 새 y위치 계산

        if(nx < 0 || nx >= M || ny < 0 || ny >= N){
            return false;
        }

        if(dir == 1){ // 동쪽 이동
            moveDiceRight();
        }else if(dir == 2){ // 서쪽 이동
            moveDiceLeft();
        }else if(dir == 3) { // 북쪽 이동
            moveDiceUp();
        }else if(dir == 4){ // 남쪽 이동
            moveDiceDown();
        }

        diceX = nx;
        diceY = ny;
        if(map[diceY][diceX] == 0) { // 맵데이터가 0인 경우
            map[diceY][diceX] = dice[6]; // 주사위 밑면 데이터 맵데이터에 입력
        }else if(map[diceY][diceX] > 0) { // 맵데이터가 0이 아닌 경우
            dice[6] = map[diceY][diceX]; // 주사위 밑면 데이터에 맵데이터 입력
            map[diceY][diceX] = 0; // 맵데이터 0으로 설정
        }

        return true;
    }

    public static void moveDiceRight(){
        int[] temp = new int[7];
        for(int i = 1; i < 7; i++){
            temp[i] = dice[i];
        }
        dice[4] = temp[6];
        dice[1] = temp[4];
        dice[3] = temp[1];
        dice[6] = temp[3];
    }

    public static void moveDiceLeft(){
        int[] temp = new int[7];
        for(int i = 1; i < 7; i++){
            temp[i] = dice[i];
        }
        dice[4] = temp[1];
        dice[1] = temp[3];
        dice[3] = temp[6];
        dice[6] = temp[4];
    }

    public static void moveDiceDown(){
        int[] temp = new int[7];
        for(int i = 1; i < 7; i++){
            temp[i] = dice[i];
        }
        dice[2] = temp[6];
        dice[1] = temp[2];
        dice[5] = temp[1];
        dice[6] = temp[5];
    }

    public static void moveDiceUp(){
        int[] temp = new int[7];
        for(int i = 1; i < 7; i++){
            temp[i] = dice[i];
        }
        dice[2] = temp[1];
        dice[1] = temp[5];
        dice[5] = temp[6];
        dice[6] = temp[2];
    }
}
