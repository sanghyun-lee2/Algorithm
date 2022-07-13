const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const Oper = ["P", "M", "N", "D"]; // 연산자 종류 덧셈 뺄셈 곱셈 나눗셈

let N = Number(input[0]); // 피연산자 수
let AN = input[1].split(" "); // 피연산자 배열
let CN = input[2].split(" "); // 연산자 갯수 배열
let OperArr = []; // 연산자 배열
let RandOperArr = []; // 순열로 변경되는 연산자 배열
let IsVisit = [];
let answerMin = Number.MAX_SAFE_INTEGER;
let answerMax = Number.MIN_SAFE_INTEGER;

for (let i = 0; i < 4; i++) {
   let cnt = Number(CN[i]); // 해당되는 연산자 수
   for (let j = 0; j < cnt; j++) {
      OperArr.push(Oper[i]); // 갯수만큼 연산자 배열에 추가
      RandOperArr.push(Oper[i]);
   }
}

for (let i = 0; i < OperArr.length; i++) {
   IsVisit.push(false);
}

perm(0);

if (answerMax === -0) {
   console.log(0);
} else {
   console.log(answerMax);
}

if (answerMin === -0) {
   console.log(0);
} else {
   console.log(answerMin);
}

function getSum() {
   let Num = Number(AN[0]);

   for (let i = 1; i < N; i++) {
      let operator = RandOperArr[i - 1];
      let operand = Number(AN[i]);

      if (operator === "P") {
         // 덧셈
         Num += operand;
      } else if (operator === "M") {
         // 빼기
         Num -= operand;
      } else if (operator === "N") {
         // 곱셈
         Num *= operand;
         Num = Math.floor(Num);
      } else if (operator === "D") {
         // 나누기
         if (Num < 0) {
            Num = -Math.floor(-Num / operand);
         } else {
            Num = Math.floor(Num / operand);
         }
      }
   }
   return Num;
}

function perm(cnt) {
   if (cnt === N - 1) {
      //console.log(RandOperArr);
      let res = getSum();
      answerMax = Math.max(res, answerMax);
      answerMin = Math.min(res, answerMin);

      if (answerMax < res) answerMax = res;
      if (answerMin > res) answerMin = res;
      return;
   }

   for (let i = 0; i < OperArr.length; i++) {
      if (IsVisit[i] === false) {
         RandOperArr[cnt] = OperArr[i];
         IsVisit[i] = true;
         perm(cnt + 1);
         IsVisit[i] = false;
      }
   }
}
