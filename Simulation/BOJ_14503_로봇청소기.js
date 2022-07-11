const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

var NM = input[0].split(" ");
var N = Number(NM[0]);
var M = Number(NM[1]);

var robotXY = input[1].split(" ");
var robotY = Number(robotXY[0]);
var robotX = Number(robotXY[1]);
var robotD = Number(robotXY[2]);

var map = [];
for (let i = 2; i < input.length; i++) {
   if (input[i] !== "") {
      map.push(input[i].split(" "));
   }
}

var isVisit = new Array(N);
for (let i = 0; i < N; i++) {
   isVisit[i] = new Array(M);
}

for (let i = 0; i < N; i++) {
   for (let j = 0; j < M; j++) {
      isVisit[i][j] = false;
   }
}

// 서북동남
const dturn = [
   [-1, 0],
   [0, -1],
   [1, 0],
   [0, 1],
];

// 남서북동
const dback = [
   [0, 1],
   [-1, 0],
   [0, -1],
   [1, 0],
];

var cntVisit = 0;

solution();

console.log(cntVisit);

function solution() {
   let queue = [];
   queue.push([robotX, robotY]);

   while (queue.length) {
      let front = queue.shift();
      let temp = robotD;
      let isBack = true;

      if (isVisit[front[1]][front[0]] === false) {
         isVisit[front[1]][front[0]] = true;
         cntVisit++;
      }

      for (let i = 0; i < 4; i++) {
         let leftX = front[0] + dturn[robotD][0];
         let leftY = front[1] + dturn[robotD][1];

         robotD--;
         if (robotD < 0) robotD = 3;

         if (map[leftY][leftX] === "0" && isVisit[leftY][leftX] === false) {
            queue.push([leftX, leftY]);
            map[leftY][leftX] = "2";
            isBack = false;
            break;
         }
      }

      if (isBack === true) {
         let backX = front[0] + dback[temp][0];
         let backY = front[1] + dback[temp][1];

         if (map[backY][backX] === "1") break;
         else {
            queue.push([backX, backY]);
         }
      }
   }
}
