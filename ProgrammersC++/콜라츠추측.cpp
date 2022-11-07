#include <string>
#include <vector>

using namespace std;

int solution(int num) {
    int answer = -1;
    
    long num2 = num;
    
    int cnt = 0;
    while(cnt < 500){
        if(num2 == 1){
            answer = cnt;
            break;
        }
        
        if(num2 % 2 == 0){
            num2 /= 2;
        }else{
            num2 = num2 * 3 + 1;
        }
        
        cnt++;
        
        //printf("%d %d\n", num2, cnt);
    }
    
    
    return answer;
}