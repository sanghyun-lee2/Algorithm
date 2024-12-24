const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : `input.txt`;
let input = fs.readFileSync(filePath).toString().split("\n");

// 0: 동, 1: 남, 2: 서, 3: 북
const mode = [
  [],
  [[0], [1], [2], [3]],
  [[0, 2], [1, 3]],
  [[0, 3], [0, 1], [1, 2], [2, 3]],
  [[0, 1, 3], [0, 1, 2], [0, 2, 3], [1, 2, 3]],
  [[0, 1, 2, 3]],
];

const dxdy = [
  [1, 0],
  [0, 1],
  [-1, 0],
  [0, -1],
];

const fill = (x, y, dir, tmp) => {
  let nx = x;
  let ny = y;
  while (true) {
    nx += dxdy[dir][0];
    ny += dxdy[dir][1];
    if (nx < 0 || nx >= N || ny < 0 || ny >= M || tmp[nx][ny] === 6) break;
    if (tmp[nx][ny] === 0) tmp[nx][ny] = -1;
  }
};

let [N, M] = input[0].split(" ").map((n) => Number(n));
let board = new Array(N).fill(0);
for (let i = 0; i < N; i++) {
  board[i] = new Array(M).fill(0);
};

let cctvs = [];

for (let i = 0; i < N; i++) {
  board[i] = input[i + 1].split(" ").map((n, idx) => {
    const nn = n.replace(/\r/g, "");
    if (nn === "0" || nn === "6") return Number(nn);
    else {
      cctvs.push([i, idx, Number(nn)]);
      return Number(nn);
    }
  });
}

let minValue = 999999999;

const dfs = (idx, map) => {
  if (idx === cctvs.length) {
    let cnt = 0;
    for (let i = 0; i < N; i++) {
      for (let j = 0; j < M; j++) {
        if (map[i][j] === 0) cnt++;
      }
    }
    minValue = Math.min(minValue, cnt);
    return;
  }

  const [x, y, type] = cctvs[idx];
  for (let i = 0; i < mode[type].length; i++) {
    const tmp = [...map.map((arr) => [...arr])];

    for (let j = 0; j < mode[type][i].length; j++) {
      fill(x, y, mode[type][i][j], tmp);
      // console.log(tmp);
      dfs(idx + 1, tmp);
    }
  }
};

const main = () => {
  dfs(0, board);
  console.log(minValue);
};

main();