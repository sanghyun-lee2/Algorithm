/*
주어진 숫자 중 3개의 수를 더했을 때 소수가 되는 경우의 개수를 구하려고 합니다. 
숫자들이 들어있는 배열 nums가 매개변수로 주어질 때, 
nums에 있는 숫자들 중 서로 다른 3개를 골라 더했을 때 소수가 되는 경우의 개수를
 return 하도록 solution 함수를 완성해주세요.
*/

function combination(arr, num) {
    let result = [];
    if(num == 1) return arr.map(e => [e]);
    
    arr.forEach((e,i,array) => {
      let rest = array.slice(i+1);
      let combinations = combination(rest,num-1);
      let combiArr = combinations.map(x => [e, ...x])
      result.push(...combiArr);
    }) 
    return result;
 }

 function isPrime(n) {
    if (n === 1)
        return false;

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
        if (n % i === 0)    
            return false;
    }

    return true;
}

function solution(nums) {

    var answer = 0;
    
    // 숫자들이 들어있는 배열에서 서로 다른 3개를 구함(조합)
    var arrlst = combination(nums, 3);

    for(var i in arrlst)
    {
        // 서로 다른 3개의 수를 더함
        const sum = arrlst[i].reduce((stack, el)=>{
            return stack + el;
        }, 0);

        // 소수 인지 확인
        if(isPrime(sum)==true)
            answer++;
    }
    
    return answer;
}

var numsArr = [1,2,7,6,4];

var result = solution(numsArr);

console.log(result);