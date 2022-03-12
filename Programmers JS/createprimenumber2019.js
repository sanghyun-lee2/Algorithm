function combination(arr, num) {
  let result = [];
  if (num == 1) return arr.map((e) => [e]);

  arr.forEach((e, i, array) => {
    let rest = array.slice(i + 1);
    let combinations = combination(rest, num - 1);
    let combiArr = combinations.map((x) => [e, ...x]);
    result.push(...combiArr);
  });
  return result;
}

function isPrime(n) {
  if (n === 1) return false;

  if (n === 2) {
    return true;
  }

  /**
   * 2부터 주어진 자연수 n까지 모든 수를 하나씩 나누어도 같은 결과가 나오지만
   * 자연수 n의 약수 쌍의 경우, 반드시 둘 중 하나는 n 제곱근 이하이기 때문에
   * 제곱근까지만 나누어 확인할 경우 속도를 개선할 수 있다.
   */
  let size = Math.ceil(Math.sqrt(n));
  for (let i = 2; i <= size; i++) {
    if (n % i === 0) return false;
  }

  return true;
}

function solution(nums) {
  var answer = 0;

  // 숫자들이 들어있는 배열에서 서로 다른 3개를 구함(조합)
  var arrlst = combination(nums, 3);

  for (var i in arrlst) {
    // 서로 다른 3개의 수를 더함
    const sum = arrlst[i].reduce((stack, el) => {
      return stack + el;
    }, 0);

    // 소수 인지 확인
    if (isPrime(sum) == true) answer++;
  }

  return answer;
}

var numsArr = [1, 2, 7, 6, 4];

var result = solution(numsArr);

console.log(result);
