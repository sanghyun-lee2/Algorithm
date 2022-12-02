#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> numbers) {
    int answer = numbers.at(0) * numbers.at(1);

    vector<int> minusArr;
    vector<int> plusArr;

    for(int i = 0; i < numbers.size(); i++){
        int num = numbers.at(i);
        if(num < 0){
            minusArr.push_back(num);
        }else if( num > 0){
            plusArr.push_back(num);
        }
    }

    sort(minusArr.begin(), minusArr.end());
    sort(plusArr.begin(), plusArr.end());

    if(minusArr.size() > 1){
        int temp = minusArr.at(0) * minusArr.at(1);
        if(temp > answer){
            answer = temp;
        }
    }

    if(plusArr.size() > 1){
        int len = plusArr.size();
        int temp = plusArr.at(len - 2) * plusArr.at(len - 1);
        if(temp > answer){
            answer = temp;
        }
    }

    return answer;
}