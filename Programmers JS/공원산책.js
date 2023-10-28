function findStart(park){
  let start = { x: 0, y: 0 };
  for(let i = 0; i < park.length; i++){
      for(let j = 0; j < park[i].length; j++){
          if(park[i][j] === 'S'){
              start.x = j;
              start.y = i;
          }
      }
  }
  return start;
}

function movePark(park, cur, dir, cnt){
  const h = park.length;
  const w = park[0].length;
  
  let res = true;
  const move = { x: cur.x, y: cur.y }
      
  for(let i = 1; i <= cnt; i++){
      if(dir === 'E'){
          move.x += 1;
      }
      if(dir === 'W'){
          move.x -= 1;
      }
      if(dir === 'N'){
          move.y -= 1;
      }
      if(dir === 'S'){
          move.y += 1;
      }
      if(move.x < 0 || move.x >= w){
          res = false;
      }
      if(move.y < 0 || move.y >= h){
          res = false;
      }
      if(res === true && park[move.y][move.x] === 'X'){
          res = false;
      }
  }
  
  if(res === true){
      cur.x = move.x;
      cur.y = move.y;
  }

  return cur;
}

function solution(park, routes) {
  let loc = findStart(park);
  
  console.log(loc);
  routes.map(route => {
      const [dir, cnt] = route.split(' ');
      loc = movePark(park, loc, dir, Number(cnt));
      console.log(loc);
  });
  
  var answer = [loc.y, loc.x];
  return answer;
}