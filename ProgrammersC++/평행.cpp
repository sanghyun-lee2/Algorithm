#include <string>
#include <vector>

using namespace std;

bool isParrarell(vector<vector<int>> dots){
    double dx1 = dots.at(0).at(0) - dots.at(1).at(0);
    double dy1 = dots.at(0).at(1) - dots.at(1).at(1);
    double a1 = dy1 / dx1;

    double dx2 = dots.at(2).at(0) - dots.at(3).at(0);
    double dy2 = dots.at(2).at(1) - dots.at(3).at(1);
    double a2 = dy2 / dx2;

    if(a1 == a2){
        return true;
    }else{
        return false;
    }
}

int solution(vector<vector<int>> dots) {
    int answer = 0;

    vector<vector<int>> dots1;
    dots1.push_back(dots.at(0));
    dots1.push_back(dots.at(1));
    dots1.push_back(dots.at(2));
    dots1.push_back(dots.at(3));

    if(isParrarell(dots1) == true){
        answer = 1;
    }

    dots1.clear();
    dots1.push_back(dots.at(0));
    dots1.push_back(dots.at(2));
    dots1.push_back(dots.at(1));
    dots1.push_back(dots.at(3));

    if(isParrarell(dots1) == true){
        answer = 1;
    }

    dots1.clear();
    dots1.push_back(dots.at(0));
    dots1.push_back(dots.at(3));
    dots1.push_back(dots.at(1));
    dots1.push_back(dots.at(2));

    if(isParrarell(dots1) == true){
        answer = 1;
    }

    return answer;
}