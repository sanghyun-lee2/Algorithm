const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let [H, W, N, M] = input[0].split(' ').map(Number);

let cnt = 0;
for (let i = 0; i < H; i += (N + 1)){
  for (let j = 0; j < W; j += (M + 1)) {
    cnt++;
  }
}

console.log(cnt);