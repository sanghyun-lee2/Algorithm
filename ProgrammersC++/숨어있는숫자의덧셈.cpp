#include <string>
#include <vector>

using namespace std;

int solution(string my_string) {
    int answer = 0;

    string numstr = "";
    for(int i = 0; i < my_string.size(); i++){
        char c = my_string.at(i);
        if(c >= '0' && c <= '9'){
            numstr += c;
        }else{
            if(numstr != ""){
                answer += atoi(numstr.c_str());
            }
            numstr = "";
        }
    }

    if(numstr != ""){
        answer += atoi(numstr.c_str());
    }

    return answer;
}