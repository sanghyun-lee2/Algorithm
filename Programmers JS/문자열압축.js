function solution(s) {
  let min = s.length;

  // 1 ~ 절반까지 반복되는 문자열 확인하여 가장 작은 수를 찾음
  for (let i = 1; i <= s.length / 2; i++) {
    let res = calMinStr(i, s);
    min = Math.min(res, min);
  }

  var answer = min;
  return answer;
}

function calMinStr(k, s) {
  let len = s.length;
  let prevStr = " ";
  let str = " ";
  let res = 0;
  let cnt = 1;

  for (let i = 0; i < len; i += k) {
    str = s.substr(i, k);

    if (str === prevStr) {
      // 같은 문자열이면 반복횟수 증가
      cnt++;
    } else {
      // 반복되는 문자열이면 반복수 + 반복문으로 문자열 치환
      if (cnt > 1) {
        let prevlen = s.length;
        let strrep = prevStr.repeat(cnt);
        s = s.replace(strrep, cnt + prevStr);
        let aftlen = s.length;
        i -= prevlen - aftlen;
        len -= prevlen - aftlen;
      }
      cnt = 1;
    }
    prevStr = str;
  }

  // 마지막 문자열 처리
  if (cnt > 1 && str.length > 0) {
    let strrep = str.repeat(cnt);
    s = s.replace(strrep, cnt + str);
  }

  return s.length;
}
