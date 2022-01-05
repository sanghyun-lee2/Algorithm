import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_3190_뱀 {
    static int N; // 보드 크기

    static int[][] map; // 맵

    static int dir = 0; // 뱀 이동방향(동0 남1 서2 북3)

    static int[] dx = {1, 0, -1, 0}; // x이동 인자(동남서북)
    static int[] dy = {0, 1, 0, -1}; // y이동 인자(동남서북)

    static ArrayList<Point> snakePArr = new ArrayList<>(); // 뱀 위치 리스트

    // 위치 클래스
    public static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //System.setIn(new FileInputStream("input.txt")); // 입력 파일 설정
        Scanner sc = new Scanner(System.in); // 스캐너 선언

        N = sc.nextInt(); // 보드 크기 입력

        map = new int[N][N]; // 보드 크기만큼 맵 할당

        int K = sc.nextInt(); // 사과 갯수
        for(int i = 0; i < K; i++){
            int apy = sc.nextInt() - 1;
            int apx = sc.nextInt() - 1;
            map[apy][apx] = 1; // 맵에 사과 설정
        }

        int L = sc.nextInt(); // 뱀 방향 변환 횟수
        int[] timeL = new int[L + 1]; // 뱀 방향 시간 정보
        char[] dirL = new char[L + 1]; // 뱀 방향 변환 정보
        for(int i = 0; i < L; i++){
            timeL[i] = sc.nextInt(); // 뱀 방향 시간정보 설정
            dirL[i] = sc.next().charAt(0); // 뱀 방향 변환정보 설정
        }

        int time = 0; // 진행 시간
        int LCnt = 0; // 방향 전환 횟수

        snakePArr.add(new Point(0, 0)); // 뱀 처음 위치는 0, 0

        // 새로운 뱀 위치가 벽을 넘어서는 위치거나, 뱀 몸통에 도착할때까지 반복
        while(true){
            time++; // 시간 증가

            // 뱀 새로운 위치 계산
            Point lastPoint = snakePArr.get(snakePArr.size() - 1);
            int nx = lastPoint.x + dx[dir];
            int ny = lastPoint.y + dy[dir];

            // 벽에 부딪힌 경우.. 종료
            if(nx < 0 || nx >= N || ny < 0 || ny >= N){
                break;
            }

            // 새로운 위치가 뱀의 몸통에 해당되는 경우.. 종료
            int size = snakePArr.size();
            boolean bExit = false;
            for(int i = 0; i < size; i++){
                Point p = snakePArr.get(i);
                if(p.x == nx && p.y == ny){
                    bExit = true;
                    break;
                }
            }

            if(bExit){
                break;
            }

            // 새로도착한 칸 리스트에 추가
            snakePArr.add(new Point(nx, ny));

            // 새로도착한 칸이 빈칸이면 꼬리 삭제
            if(map[ny][nx] == 0 && snakePArr.size() > 1) {
                snakePArr.remove(0);
            }

            // 새로도착한 칸이 사과이면 사과 삭제
            if(map[ny][nx] == 1){
                map[ny][nx] = 0;
            }

            //printMap();
            //System.out.println();

            // 뱀 방향 설정
            if(time == timeL[LCnt]) { // 방향 전환 시간이면..
                if (dirL[LCnt] == 'L') {
                    dir--; // 왼쪽으로 방향 전환
                }
                if (dirL[LCnt] == 'D') {
                    dir++; // 오른쪽으로 방향 전환
                }

                if (dir > 3) {
                    dir = 0;
                }
                if (dir < 0) {
                    dir = 3;
                }

                LCnt++; // 방향 전환 횟수 증가
            }
        }

        System.out.println(time);
    }

    static void printMap(){
        int[][] mapPrint = new int[N][N];

        for(int i = 0; i < N; i++){
            System.arraycopy(map[i], 0, mapPrint[i], 0, N);
        }

        int size = snakePArr.size();
        for(int i = 0; i < size; i++){
            int x = snakePArr.get(i).x;
            int y = snakePArr.get(i).y;
            mapPrint[y][x] = 2;
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(mapPrint[i][j] + " ");
            }
            System.out.println();
        }
    }
}
