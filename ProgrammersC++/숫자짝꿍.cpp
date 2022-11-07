#include <string>
#include <vector>

using namespace std;

string solution(string X, string Y) {
    int Xcnt[10] = {0, };
    int Ycnt[10] = {0, };
    int XYcnt[10] = {0, };
    
    for(int i = 0; i < X.length(); i++){
        char c = X.at(i);
        int idx = c - '0';
        Xcnt[idx]++;
    }
    
    for(int i = 0; i < Y.length(); i++){
        char c = Y.at(i);
        int idx = c - '0';
        Ycnt[idx]++;
    }
    
    for(int i = 0; i < 10; i++){
        XYcnt[i] = min(Xcnt[i], Ycnt[i]);
    }
    
    string str = "";
    for(int i = 9; i >= 0; i--){ 
        int cnt = XYcnt[i];
        for(int j = 0; j < cnt; j++){
            str += to_string(i);
        }
    }
    
    string answer = "";
    if(str == ""){
        answer = "-1";
    }else if(str[0] == '0'){
        answer = "0";
    }else{
        answer = str;
    }
    
    return answer;
}