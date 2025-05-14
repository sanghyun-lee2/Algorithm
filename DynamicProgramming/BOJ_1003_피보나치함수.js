const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const DP = new Array(42);
for (let i = 0; i < 42; i++){
  DP[i] = new Array(2).fill(0);
}

DP[0] = [1, 0];
DP[1] = [0, 1];

for (let i = 2; i <= 40; i++){
  DP[i][0] = DP[i - 1][0] + DP[i - 2][0];
  DP[i][1] = DP[i - 1][1] + DP[i - 2][1];
}

const T = +input[0];
for (let i = 1; i <= T; i++){
  let N = +input[i];

  const res = DP[N].join(" ");
  console.log(res);
}