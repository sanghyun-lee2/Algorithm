package ProgrammersJava;

public class PGM_기지국설치  {
	
	public static void main(String[] args) {
		int n = 16;
		int[] statations = {9};
		int w = 2;
		
		int res = solution(n, statations, w);
		System.out.println(res);
	}
	
    public static int solution(int n, int[] stations, int w) {
    	int answer = 0;
    	
    	int loc = 1; // 건물 위치.. 1 ~ N 까지 
    	int idx = 0; // 기지국 인덱스 
    	
    	while(loc <= n) {
    		// 현재 위치가 기지국 전파 내에 존재하지 않음 
    		if(idx >= stations.length || loc < stations[idx] - w) {
    			loc += w * 2 + 1;
    			answer++;
    		}else { // 현재 위치가 기지국 전파 내에 존재
    			loc = stations[idx] + w + 1;
    			// 다음 기지국으로 변경 
    			idx++;
    		}
    	}
    	
        return answer;
    }
}