const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let NL = input[0].split(" ");
let N = Number(NL[0]);
let L = Number(NL[1]);

let map = new Array(N);
for (let i = 0; i < N; i++) {
   map[i] = new Array(N);
   map[i] = input[i + 1].split(" ").map((n) => n - "0");
}

let res = 0;

function isCorrectPath(arr) {
   let isUse = new Array(arr.length).fill(false);
   let isPath = true;
   let arrLen = arr.length;

   for (let i = 0; i < arrLen - 1; i++) {
      let cur = arr[i];
      let next = arr[i + 1];

      if (cur - next === 0) {
         // 높이가 같은 경우
      } else if (cur - next === 1) {
         // 아래로 내려가는 경우
         if (i + L > arrLen - 1) {
            isPath = false;
         } else {
            for (let z = i + 1; z <= i + L; z++) {
               if (next === arr[z] && isUse[z] === false) {
                  isUse[z] = true;
               } else {
                  isPath = false;
               }
            }
         }
      } else if (cur - next === -1) {
         // 위로 올라가는 경우
         if (i + 1 - L < 0) {
            isPath = false;
         } else {
            for (let z = i; z >= i - L + 1; z--) {
               if (cur === arr[z] && isUse[z] === false) {
                  isUse[z] = true;
               } else {
                  isPath = false;
               }
            }
         }
      } else {
         // 경사가 1차이 이상 나는 경우
         isPath = false;
      }

      if (isPath === false) {
         break;
      }
   }

   //console.log(isUse);

   return isPath;
}

//가로 방면 길 찾기(좌 -> 우)
for (let i = 0; i < N; i++) {
   let arr = [];
   for (let j = 0; j < N; j++) {
      arr.push(map[i][j]);
   }
   if (isCorrectPath(arr) === true) {
      arr.reverse();
      if (isCorrectPath(arr) === true) {
         res++;
      }
   }
}

// 세로 방면 길 찾기(상 -> 하)
for (let i = 0; i < N; i++) {
   let arr = [];
   for (let j = 0; j < N; j++) {
      arr.push(map[j][i]);
   }
   if (isCorrectPath(arr) === true) {
      arr.reverse();
      if (isCorrectPath(arr) === true) {
         res++;
      }
   }
}

console.log(res);
