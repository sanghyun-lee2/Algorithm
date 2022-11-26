#include <string>
#include <vector>

using namespace std;

int getYaksuCnt(int number){
    int cnt = 0;
    for(int i = 1; i * i <= number; i++){
        if(number % i == 0){
            cnt++;
            if(i * i < number){
                cnt++;
            }
        }
    }
    return cnt;
}

int solution(int number, int limit, int power) {
    int answer = 0;
    
    for(int i = 1; i <= number; i++){
        int yakcnt = getYaksuCnt(i);
        int res = yakcnt;
        if(yakcnt > limit){
            res = power;
        }
        answer += res;
    }
    
    return answer;
}