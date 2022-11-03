#include <string>
#include <vector>

using namespace std;

int bin = 0;

int getCola(int a, int b, int n){
    int cola = 0;
    bin = 0;
    while(true){
        if(a <= n){
            cola += b;
            n -= a;
            bin += a;
        }else{
            break;
        }
    }
    return cola;
}

int solution(int a, int b, int n) {
    int answer = 0;
    
    while(true){
        int cola = getCola(a, b, n);
        if(cola > 0){
            answer += cola;
            n -= bin;
            n += cola;
        }else{
            break;
        }
    }
    
    return answer;
}