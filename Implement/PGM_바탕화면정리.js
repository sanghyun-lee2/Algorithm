function solution(wallpaper) {
  var answer = [];

  let startX = 99999;
  let startY = 99999;
  let endX = -1;
  let endY = -1;

  wallpaper.map((str, y) => {
    str.split("").map((ch, x) => {
      if (ch === "#") {
        if (x < startX) startX = x;
        if (y < startY) startY = y;
        if (x + 1 > endX) endX = x + 1;
        if (y + 1 > endY) endY = y + 1;
      }
    });
  });

  answer.push(startY, startX, endY, endX);

  return answer;
}