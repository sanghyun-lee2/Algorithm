const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let cnt = 0;
while (input[cnt] !== "0") {
  let N = Number(input[cnt]);

  let prev = 1;
  let res = 1;
  for (let i = 2; i <= N; i++) {
    res = i * i + prev;
    prev = res;
  }
  console.log(res);
  cnt++;
}