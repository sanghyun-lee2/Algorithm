function solution(left, right) {
    let answer = 0;
    
    for(let i = left; i <= right; i++)
    {
        let cnt = dividerCnt(i);

        if(cnt % 2 == 0)
        {
            answer += i;
        }
        else
        {
            answer -= i;
        }
    }

    return answer;
}

function dividerCnt(n) {
    let cnt = 0;

    for (let i = 0; i <= n; i++)
    {
        if( n % i === 0 )
        {
            cnt++;
        }
    }

    return cnt;
}

var left = 13;
var right = 17;

var result = solution(left, right);

console.log(result);