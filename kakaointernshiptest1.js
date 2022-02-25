function solution(s) {
    var strReplace = s;

    strReplace = strReplace.replace(/zero/gi, '0');
    strReplace = strReplace.replace(/one/gi, '1');
    strReplace = strReplace.replace(/two/gi, '2');
    strReplace = strReplace.replace(/three/gi, '3');
    strReplace = strReplace.replace(/four/gi, '4');
    strReplace = strReplace.replace(/five/gi, '5');
    strReplace = strReplace.replace(/six/gi, '6');
    strReplace = strReplace.replace(/seven/gi, '7');
    strReplace = strReplace.replace(/eight/gi, '8');
    strReplace = strReplace.replace(/nine/gi, '9');

    var answer = parseInt(strReplace);
    return answer;
}


var input = 'one4seveneight';
var result = solution(input);

console.log(result);