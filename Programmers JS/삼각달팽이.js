function move(cur, dir){
  let nx = cur.x;
  let ny = cur.y;
  if(dir === 1){
      ny++;
  }
  else if(dir === 2){
      nx++;
  }
  else if(dir === 3){
      nx--;
      ny--;
  }

  return {nx, ny};
}

function solution(n) {
   let map = [];
   let cntAll = 0;
   for(let i = 1; i <= n; i++){
       let arr = [];
       for(let j = 1; j <= i; j++){
           arr.push(0);
       }
       cntAll += i;
       map.push(arr);        
   }

   let dir = 1; // 1: 아래로, 2: 오른쪽으로, 3: 왼쪽위로
   let cnt = 1;
   let cur = { x: 0, y: 0 };

   //console.log(cntAll);

   while(cnt < cntAll){
      //console.log(cur.x, cur.y, cnt);
      let {nx, ny} = move(cur, dir);
      let ndir = dir;
      if(dir === 1){
         if(ny < 0 || ny > map.length - 1) ndir = 2;
         else if(map[ny][nx] > 0) ndir = 2; 
         else {}
      }
      else if(dir === 2){
         if(nx < 0 || nx > map[ny].length - 1) ndir = 3;
         else if(map[ny][nx] > 0) ndir = 3;
         else {}
      }
      else if(dir === 3){
         if(nx < 0 || ny < 0) ndir = 1;
         else if(map[ny][nx] > 0) ndir = 1;
         else {}
      }else {}

      if(dir === ndir){
        map[cur.y][cur.x] = cnt;
        cnt++;
        cur.x = nx;
        cur.y = ny;
      }
      else{
        dir = ndir;  
      }
   }

   let answer = [];
   for(let i = 1; i <= n; i++){
       for(let j = 1; j <= i; j++){
           const num = map[i - 1][j - 1];
           if(num === 0) answer.push(cntAll);
           else answer.push(num);
       }
   }
   return answer;
}
