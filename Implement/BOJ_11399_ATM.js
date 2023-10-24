const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let N = +input[0];
let P = input[1].split(" ").map(Number).sort((a, b) => a - b);

let sum = 0;
let res = 0;
for (let i = 0; i < N; i++) {
  res += P[i] + sum;
  sum += P[i];
}

console.log(res);