const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let N = +input[0];
let str = input[1];
let res = 0n;
let M = 1234567891n;

for (let i = 0; i < N; i++) {
  let num = str.charCodeAt(i) - 96;
  let v = Math.pow(31n, i) % M;
  res += BigInt(v * num) % M;
}

console.log(res.toString());