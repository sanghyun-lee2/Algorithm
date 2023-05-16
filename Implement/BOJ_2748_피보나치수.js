const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let N = Number(input[0]);
let arr = new Array(N + 1);
let answer = 0;

if (N === 0 || N === 1) {
  answer = N;
} else {
  arr[0] = 0;
  arr[1] = 1;

  for (let i = 2; i <= N; i++) {
    arr[i] = BigInt(arr[i - 1]) + BigInt(arr[i - 2]);
  }
  answer = arr[N];
}

console.log(answer.toString());