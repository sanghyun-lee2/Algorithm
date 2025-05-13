const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const N = +input[0];
const arr = input.slice(1, N + 1);

const res = arr[0];
let check = new Array(50).fill(false);
for (let i = 1; i < arr.length; i++) {
  const str = arr[i];
  for (let j = 0; j < res.length; j++){
    if (check[j] === false && str[j] !== res[j]) {
      check[j] = true;
     }
  }
}

let ss = res.split("");
for (let i = 0; i < ss.length; i++){
  if (check[i] === true) ss[i] = '?';
}

console.log(ss.join(""));