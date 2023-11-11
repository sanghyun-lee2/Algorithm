function solution(s, skip, index) {
  const skipNum = skip.split('').map((ch) => ch.charCodeAt(0));

  const convertS = s.split('').map((ch) => {
     let num = ch.charCodeAt(0);
     let nindex = index;
     for(let i = 1; i <= nindex; i++){
        num += 1;
        if(num > 122) num = 97;
         if(skipNum.includes(num)) nindex++;
     }
     return String.fromCharCode(num);
  });   
  return convertS.join('').toString();
}