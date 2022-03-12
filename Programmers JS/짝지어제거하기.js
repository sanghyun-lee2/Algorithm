function findjungbok(str) {
  let findidx = 0;

  for (let i = 0; i < str.length; i++) {
    if (str.indexOf(str[i]) != i) {
      findidx = i;
      //str.splice(findidx, 2);
      break;
    }
  }

  return findidx;
}

function solution(str) {
  var answer = 0;

  while (true) {
    var _findidx = findjungbok(str);

    if (_findidx == 0) {
      break;
    }

    //str.splice(_findidx, 2);
    //console.log(_findidx);
  }

  return answer;
}

var _s = "baabaa";

var result = solution(_s);

console.log(result);
