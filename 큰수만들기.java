
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < number.length(); i++){
            int num = number.charAt(i) - 48;

            if(k > 0){
                if(stack.isEmpty() == true){
                    stack.push(num);
                }else{
                    while(stack.isEmpty() == false){
                        if(num > stack.peek() && k > 0){
                            stack.pop();
                            k--;
                        }else{
                            break;
                        }
                    }
                    stack.push(num);
                }
            }else{
                stack.push(num);            
            }    
        }

        while(k > 0 && stack.isEmpty() == false){
            stack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();

        while(stack.isEmpty() == false){
            sb.append(stack.pop());   
        }

        sb.reverse();

        String answer = sb.toString();
        return answer;
    }
}