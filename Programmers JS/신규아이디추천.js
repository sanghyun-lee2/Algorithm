function removeCase2(_changeidcase1) {
  var RegExp2 = /[^-_.\w]/gm; // 대문자, 소문자, -, _, . 제외 문자 검색 정규식

  var changeidcase2 = _changeidcase1.replace(RegExp2, ""); // 제외 문자 삭제

  return changeidcase2;
}

function removeCase3(_changeidcase2) {
  var RegExp3 = /\.+/gm; // .문자가 1개이상 검색 정규식

  var changeidcase3 = _changeidcase2.replace(RegExp3, "."); //

  return changeidcase3;
}

function removeCase4(_changeidcase3) {
  var changeidcase4 = _changeidcase3;

  if (changeidcase4[0] == ".") {
    changeidcase4 = changeidcase4.substr(1);
  }

  if (changeidcase4[changeidcase4.length - 1] == ".") {
    changeidcase4 = changeidcase4.substr(0, changeidcase4.length - 1);
  }

  return changeidcase4;
}

function solution(new_id) {
  var changeidcase1 = new_id.toLowerCase(); // 1단계 대문자->소문자로
  console.log(changeidcase1);

  var changeidcase2 = removeCase2(changeidcase1); // 2단계 숫자, 소문자, -, _, . 문자 제외 삭제
  console.log(changeidcase2);

  var changeidcase3 = removeCase3(changeidcase2); // 3단계 .. 연속된 부분 .으로 치환
  console.log(changeidcase3);

  var changeidcase4 = removeCase4(changeidcase3); // 4단계 처음 또는 마지막 문자에 . 삭제
  console.log(changeidcase4);

  var changeidcase5 = changeidcase4;
  if (changeidcase5 == "") {
    // 5단계 빈문자열이면 a로 치환
    changeidcase5 = "a";
  }
  console.log(changeidcase5);

  var changeidcase6 = changeidcase5;
  changeidcase6 = changeidcase6.substr(0, 15);
  changeidcase6 = removeCase4(changeidcase6);
  console.log(changeidcase6);

  var changeidcase7 = changeidcase6;
  var charcase7 = changeidcase7[changeidcase7.length - 1];

  while (changeidcase7.length < 3) {
    changeidcase7 += charcase7;
  }
  console.log(changeidcase7);

  var answer = changeidcase7;
  return answer;
}

var new_id = "z-+.^.";

var result = solution(new_id);

//console.log(result);
