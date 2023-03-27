const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

let s = input[0];
s = s.replaceAll("XXXX", "AAAA");
s = s.replaceAll("XX", "BB");

if (s.indexOf('X') !== -1) {
  console.log('-1');
} else {
  console.log(s);
}