#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(int k, vector<int> score) {
    vector<int> answer;

    vector<int> honor;
    for(int i = 0; i < score.size(); i++){
        if(honor.size() < k){
            honor.push_back(score.at(i));
        }else{
            if(honor.at(0) < score.at(i)){
                honor.erase(honor.begin(), honor.begin() + 1);   
                honor.push_back(score.at(i));
            }
        }
        sort(honor.begin(), honor.end(), less<>());
        answer.push_back(honor.at(0));
    }

    return answer;
}