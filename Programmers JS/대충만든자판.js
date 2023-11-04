function findChar(keymap, alpha){
  let idx = 9999;
  keymap.forEach(str => {
     let findIdx = str.indexOf(alpha) + 1;
     if(findIdx > 0){
        idx = idx > findIdx ? findIdx : idx;
     }
  });
  return idx;
}

function solution(keymap, targets) {
  var answer = [];
  targets.forEach(target => {
      let res = 0;
      for(let i = 0; i < target.length; i++){
          let idx = findChar(keymap, target[i]);
          if(idx === 9999){
              answer.push(-1);
              return;
          }
          res += idx;
      }
      answer.push(res);
  });    
  return answer;
}
 