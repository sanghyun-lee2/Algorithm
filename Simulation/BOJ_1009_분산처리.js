const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const N = +input[0];
for (let i = 1; i <= N; i++){
  const [a, b] = input[i].split(" ").map(Number);

  let res = a;
  for (let j = 2; j <= b; j++){
    res = (res * a) % 100;
  }
  console.log(res % 10 === 0 ? 10 : res % 10);
}