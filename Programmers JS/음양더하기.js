function solution(absolutes, signs) {
  var value = 0;

  for (var i = 0; i < absolutes.length; i++) {
    if (signs[i] == true) {
      value += absolutes[i];
    } else {
      value -= absolutes[i];
    }
  }

  return value;
}

var _absolutes = [3, 4, 5];
var _signs = [true, false, true];

var result = solution(_absolutes, _signs);

console.log(result);
