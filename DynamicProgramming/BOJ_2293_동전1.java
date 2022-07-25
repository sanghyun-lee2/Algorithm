package DynamicProgramming;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BOJ_2293_동전1 {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("input.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] coins = new int[n + 1];
        int[][] DP = new int[n + 1][k + 1];

        for(int i = 1; i <= n; i++) {
            coins[i] = sc.nextInt();
        }

        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= k; j++){
                DP[i][j] = 0;
            }
            DP[i][0] = 1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= k; j++){
                if(coins[i] > j){
                    DP[i][j] = DP[i-1][j];
                }else{
                    DP[i][j] = DP[i-1][j] + DP[i][j - coins[i]];
                }
            }
        }

        System.out.println(DP[n][k]);
    }
}
