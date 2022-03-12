function solution(n, lost, reserve) {
  var clothPerStudent = [];
  for (let i = 0; i < n; i++) {
    clothPerStudent.push(1); // 기본 체육복 1개씩 가지고 있음
  }

  for (let i = 0; i < lost.length; i++) {
    let idxLost = lost[i] - 1;
    clothPerStudent[idxLost]--; // 체육복 잃은사람 갯수 카운트
  }

  for (let i = 0; i < reserve.length; i++) {
    let idxreserve = reserve[i] - 1;
    clothPerStudent[idxreserve]++; // 여유체육복 가진사람 갯수 카운트
  }

  console.log(clothPerStudent);

  for (let i = n; i > 0; i--) {
    // 여유분이 있는 경우 체육복 분배
    if (i < n - 1) {
      if (clothPerStudent[i] >= 2 && clothPerStudent[i + 1] < 1) {
        clothPerStudent[i]--;
        clothPerStudent[i + 1]++;
      }
    }

    // 여유분이 있는 경우 체육복 분배
    if (i > 0) {
      if (clothPerStudent[i] >= 2 && clothPerStudent[i - 1] < 1) {
        clothPerStudent[i]--;
        clothPerStudent[i - 1]++;
      }
    }
  }

  console.log(clothPerStudent);

  var answer = 0;

  // 체육수업 가능한 인원 카운트
  for (let i = 0; i < n; i++) {
    if (clothPerStudent[i] > 0) {
      answer++;
    }
  }

  return answer;
}

var _n = 10;
var _lost = [5, 4, 3, 2, 1];
var _reserve = [3, 1, 2, 5, 4];

var result = solution(_n, _lost, _reserve);

console.log(result);
