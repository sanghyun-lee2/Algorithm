#include <string>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

typedef struct Data{
    int num;
    int diff;
}Data;

bool cmp(const Data &d1, const Data &d2){
    if(d1.diff < d2.diff){
        return true;
    }else if(d1.diff == d2.diff){
        return d1.num > d2.num;
    }else{
        return false;
    }
}

vector<int> solution(vector<int> numlist, int n) {
    Data dList[100];
    for(int i = 0; i < numlist.size(); i++){
        dList[i].num = numlist.at(i);
        dList[i].diff = abs(n - numlist.at(i));
    }
    
    sort(dList, dList + numlist.size(), cmp);
    
    vector<int> answer;
    for(int i = 0; i < numlist.size(); i++){
        answer.push_back(dList[i].num);
    }
    return answer;
}