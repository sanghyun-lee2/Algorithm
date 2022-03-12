package ProgrammersJava;

public class PGM_N진수게임 {
    public String solution(int n, int t, int m, int p) {
        
        int total = t * m + 1; // 전체 시행 갯수
        String TotalStr = "0";
        
        int cnt = 1;
        while(TotalStr.length() < total){
            //String strRadixNum = getRadixNum(cnt++, n);
            String strRadixNum = Integer.toString(cnt++, n);
            TotalStr += strRadixNum.toUpperCase();
        }
        
        StringBuilder sb = new StringBuilder(); // 결과값 저장
        
        p = p - 1;
        for(int i = 0; i < t; i++){
            sb.append(TotalStr.charAt(i * m + p));
        }
        
        String answer = sb.toString();
        return answer;
    }
    
    public static String getRadixNum(int number, int radix){
        StringBuilder sb = new StringBuilder();
        
        while(number > 0){
            int num = number % radix;
            if(num == 10){
                sb.append('A');
            }else if(num == 11){
                sb.append('B');
            }else if(num == 12){
                sb.append('C');
            }else if(num == 13){
                sb.append('D');
            }else if(num == 14){
                sb.append('E');
            }else if(num == 15){
                sb.append('F');
            }else{
                sb.append(Integer.toString(num));
            }
            number /= radix;
        }
        
        return sb.reverse().toString();
    }
}