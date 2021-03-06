function solution(lottos, win_nums) {
  let max_wins = 0; // 최대 로또 번호 일치 갯수
  let min_wins = 0; // 최소 로또 번호 일치 갯수

  for (
    var i = 0;
    i < lottos.length;
    i++ // 로또 번호 갯수만큼 반복
  ) {
    let lotto = lottos[i];

    if (lotto == 0) {
      // '0' 입력 시
      max_wins++;
    } else {
      if (win_nums.includes(lotto) == true) {
        // 로또 번호가 존재
        max_wins++;
        min_wins++;
      }
    }
  }

  let ranking = new Map([
    [6, 1], // 맞춘갯수, 등수
    [5, 2],
    [4, 3],
    [3, 4],
    [2, 5],
    [1, 6],
    [0, 6],
  ]);

  let rank_min_wins = ranking.get(min_wins);
  let rank_max_wins = ranking.get(max_wins);

  var answer = [];

  answer.push(rank_max_wins);
  answer.push(rank_min_wins);

  return answer;
}

let lottos = [0, 0, 0, 0, 0, 0];
let win_nums = [38, 19, 20, 40, 15, 25];

let result = solution(lottos, win_nums);

console.log(result);
