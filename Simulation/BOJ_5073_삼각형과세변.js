const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

for (let i = 0; i < input.length; i++){
  const line = input[i];
  let [a, b, c] = line.split(" ").map(Number);
  if (a <= 0) break;

  if (Math.max([a, b, c]) === a) {
    if (a >= (b + c)) { console.log("Invalid"); continue; };
  } else if (Math.max([a, b, c]) === b) {
    if (b >= (a + c)) { console.log("Invalid"); continue; };
  } else {
    if (c >= (b + a)) { console.log("Invalid"); continue; };
  }

  if (a === b && b === c && c === a) console.log("Equilateral");
  else if ((a === b) || (b === c) || (a === c)) console.log("Isosceles");
  else console.log("Scalene");
};