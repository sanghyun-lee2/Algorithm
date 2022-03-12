function solution(numbers) {
    var answer = [];
   
    if(numbers.length >= 1 && numbers.length <= 100000)
    {
        for(let number of numbers)
        {
            for(let i = number + 1; i < 1000000000000000; i++)
            {
                let otherbitcnt = otherbitcal(number, i);

                if(otherbitcnt <= 2)
                {
                    answer.push(i);
                    break;
                }
            }
        }
    }

    return answer;
}

// 다른 비트의 개수 계산 함수
function otherbitcal(number1, number2)
{
    let cnt = 0;

    let numberxor = number1 ^ number2; // XOR 연산

    let strnumberxor = numberxor.toString(2); // 2진수 문자열로 변환

    let searchChar = '1'; // 찾으려는 문자

    let pos = strnumberxor.indexOf(searchChar); // 1문자 검색

    while (pos != -1) {
        cnt++; // 1 문자 발견 시 다름
        pos = strnumberxor.indexOf(searchChar, pos + 1); // 1문자 검색
    }
    
    return cnt;
}

var numbers = [2,7];

var result = solution(numbers);

console.log(result);