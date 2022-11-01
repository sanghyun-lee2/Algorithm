#include <string>
#include <vector>
#include <stack>
#include <vector>

using namespace std;

int solution(vector<int> ingredient) {
    stack<int> stack;
    int answer = 0;

    for(int i = 0; i < ingredient.size(); i++){
        stack.push(ingredient.at(i));

        vector<int> temp;

        if(stack.size() >= 4){
            temp.push_back(stack.top());
            stack.pop();
            temp.push_back(stack.top());
            stack.pop();
            temp.push_back(stack.top());
            stack.pop();
            temp.push_back(stack.top());
            stack.pop();

            if(temp.at(0) == 1 && temp.at(1) == 3 && temp.at(2) == 2 && temp.at(3) == 1){
                answer++;
            }else{
                stack.push(temp.at(3));
                stack.push(temp.at(2));
                stack.push(temp.at(1));
                stack.push(temp.at(0));
            }
        }
    }

    return answer;
}
