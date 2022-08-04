const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

var topArr = new Array(4);
for (let i = 0; i < 4; i++) {
   topArr[i] = new Array(8);
   topArr[i] = input[i].split("").map((n) => n - "0");
}

let K = Number(input[4]);
for (let i = 0; i < K; i++) {
   let [num, dir] = input[5 + i].split(" ").map((n) => n - "0");
   num--; // 실제 인덱스와 맞추기위해 감소
   let dirL = dir;
   let dirR = dir;

   let isRot = new Array(4).fill(0);
   isRot[num] = dir;
   // 오른쪽 톱니바퀴 회전체크
   for (let i = num; i < 4; i++) {
      if (i + 1 > 3) {
         continue;
      }
      dirR *= -1;
      if (topArr[i][2] !== topArr[i + 1][6]) {
         isRot[i + 1] = dirR;
      } else {
         break;
      }
   }
   // 왼쪽 톱니바퀴 회전체크
   for (let i = num; i > 0; i--) {
      if (i - 1 < 0) {
         continue;
      }
      dirL *= -1;
      if (topArr[i - 1][2] !== topArr[i][6]) {
         isRot[i - 1] = dirL;
      } else {
         break;
      }
   }

   // 회전가능한 톱니바퀴 회전
   for (let i = 0; i < 4; i++) {
      if (isRot[i] !== 0) {
         rotation(topArr[i], isRot[i]);
      }
   }
}

let score = 0;
if (topArr[0][0] === 1) {
   score += 1;
}
if (topArr[1][0] === 1) {
   score += 2;
}
if (topArr[2][0] === 1) {
   score += 4;
}
if (topArr[3][0] === 1) {
   score += 8;
}
console.log(score);

function rotation(arr, dir) {
   if (dir === -1) {
      arr.push(arr.shift());
   } else if (dir === 1) {
      arr.unshift(arr.pop());
   }
}
