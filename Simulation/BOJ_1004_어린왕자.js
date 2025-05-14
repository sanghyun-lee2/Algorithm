const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const T = +input[0];
let cnt = 1;

const distance = (x1, y1, x2, y2) => {
  const dx = x2 - x1;
  const dy = y2 - y1;
  return Math.sqrt(dx * dx + dy * dy);
}

for (let t = 0; t < T; t++){
  const a = input[cnt++].split(" ").map(Number);
  const start = [a[0], a[1]];
  const end = [a[2], a[3]];

  const N = +input[cnt++];

  let res = 0;

  for (let i = 0; i < N; i++){
    const circle = input[cnt++].split(" ").map(Number);
    
    const d1 = distance(start[0], start[1], circle[0], circle[1]);
    const d2 = distance(end[0], end[1], circle[0], circle[1]);
    const r = circle[2];

    if (d1 <= r && d2 <= r) {
      
    } else if (d1 <= r) {
      res++;
    } else if (d2 <= r) {
      res++;
    } else {
      
    }
  }
   console.log(res);
}