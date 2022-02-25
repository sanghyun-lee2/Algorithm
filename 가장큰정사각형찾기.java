import java.util.*;

class Solution
{   
    public int solution(int [][]board)
    {
        //System.out.println(H + " " + W);
        // DP를 통해 문제풀이
        int H = board.length; // 보드 가로 길이
        int W = board[0].length; // 보드 세로 길이

        int[][] DP = new int[H][W];

        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                DP[i][j] = board[i][j];
            }
        }

        int maxLen = DP[0][0];

        for(int i = 1; i < H; i++){
            for(int j = 1; j < W; j++){
                if(DP[i][j] > 0){
                    int temp = Math.min(DP[i - 1][j], DP[i][j - 1]);   
                    DP[i][j] = Math.min(DP[i - 1][j - 1], temp) + 1;

                    maxLen = Math.max(maxLen, DP[i][j]);
                }
            }
        }

        int answer = maxLen * maxLen;
        return answer;
    }
}