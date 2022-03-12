// 카카오 2019 개발자 겨울인턴쉽 문제
// javascript

class Stack {
    constructor() {
      this._arr = [];
    }
    push(item) {
      this._arr.push(item);
    }
    pop() {
      return this._arr.pop();
    }
    peek() {
      return this._arr[this._arr.length - 1];
    }
  }

function solution(board, moves) {
  var answer = 0;

  var logTextPrev = "";
  var logTextVal = "";

  const stack = new Stack();

  for(var move in moves) // 크레인 입력된 이동 위치 반복
  {
    for(var i = 0; i < board.length; i++) // board y위치
    {
      for(var j = 0; j < board[i].length; j++) // board x위치
      {
        //var moveVal = board[i].length - moves[move];
        var moveVal = moves[move] - 1;

        if(moveVal == j) // 이동 위치와 x위치가 동일한 경우
        {
          if(board[i][j] > 0) // 보드 해당위치에 인형이 있음
          {
            var prevVal = stack.peek();

            if(prevVal == board[i][j])
            {
              stack.pop();
              answer += 2;
            }
            else
            {
              stack.push(board[i][j]);
            }

            logTextPrev += prevVal + ", ";
            logTextVal += board[i][j] + ", ";

            var prevStack = stack.pop();

            if(prevStack > 0 && board[i][j])

            stack.push(board[i][j]);
            board[i][j] = 0; // 해당 보드 초기화
            moves[move] = -1; // 이동 초기화
          }
        }
      }
    }
  }

  //console.log(logTextPrev);
  //logTextPrev = "";

  //console.log(logTextVal);
  //logTextVal = "";

  return answer;
}

var board = [[0,0,0,0,0],[0,0,1,0,3],[0,2,5,0,1],[4,2,4,4,2],[3,5,1,3,1]];
var moves = [1,5,3,5,1,2,1,4];

var result = solution(board, moves);
console.log(result);
