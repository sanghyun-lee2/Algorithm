#include <string>
#include <vector>

using namespace std;

int solution(string s) {
    int answer = 0;
    
    char first = '.';
    int pos = 0;
    int meCnt = 0;
    int otherCnt = 0;
    while(s.size() > 0){
        char c = s.at(pos);
        if(first == '.'){ // 첫번째 문자
            first = c;
            meCnt++;
        }else if(first == c){
            meCnt++;            
        }else{
            otherCnt++;
        }
        
        if(meCnt == otherCnt || pos == s.size() - 1){
            s.erase(0, pos + 1);
            pos = 0;
            meCnt = 0;
            otherCnt = 0;
            first = '.';
            answer++;
        }else{
            pos++;   
        }
    }
    
    
    return answer;
}