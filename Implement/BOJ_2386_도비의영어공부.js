const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

for (let i = 0; i < input.length; i++) {
  let data = input[i];
  if (data === '#') break;

  let [ch, ...s] = data.split(' ');
  let str = s.join('');
  str = str.toLowerCase();

  let cnt = 0;
  str.split("").map((e) => {
    if (e === ch) cnt++;
  })

  console.log(ch, cnt);
}