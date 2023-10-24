import java.util.Arrays;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;

        boolean area[] = new boolean[n];

        for(int i = 0; i < section.length; i++){
            int pos = section[i] - 1;
            if(area[pos] == false){
                // 페인트 칠
                answer++;
                for(int j = pos; j < pos + m; j++){
                    if(j >= n) break;
                    area[j] = true;
                }
            }
            // System.out.println(Arrays.toString(area));
        }

        return answer;
    }
}