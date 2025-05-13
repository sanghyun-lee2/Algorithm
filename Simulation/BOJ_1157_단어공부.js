const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

const map = new Map();

input[0].split("").forEach((ch) => {
  const chUp = ch.toUpperCase();
  let cnt = map.get(chUp) ? Number(map.get(chUp)) : 0;
  map.set(chUp, ++cnt);
});

let res = '';
let max = -1;
map.forEach((value, key) => {
  if (max === value) {
    res = '?';
  }
  if (max < value) {
    max = value;
    res = key.toString();
  }
});

console.log(res);