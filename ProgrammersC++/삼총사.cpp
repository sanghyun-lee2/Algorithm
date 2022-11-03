#include <string>
#include <vector>

using namespace std;

int N = 0;
int dpArr[3] = {0, };
bool isVisit[13];
int res = 0;

void perm(int depth, vector<int> number);

int solution(vector<int> number) {
    N = number.size();
    vector<int> arr;
    perm(0, number);

    int answer = res;
    return answer;
}

void perm(int depth, vector<int> number){
    if(depth == 3){
        if(dpArr[0] + dpArr[1] + dpArr[2] == 0){
            res++;
        }
        return;
    }

    for(int i = 0; i < N; i++){
        if(isVisit[i] == true){
            return;
        }

        isVisit[i] = true;
        int num = number.at(i);
        dpArr[depth] = num;
        perm(depth + 1, number);
        isVisit[i] = false;
    }
}
