function solution(N, stages) {
   let failMap = new Map();
   let clearMap = new Map();

   for (let i = 1; i <= N; i++) {
      failMap.set(i, x0);
      clearMap.set(i, 0);
   }

   for (let i = 1; i <= stages.length; i++) {
      let stage = stages[i - 1];

      if (stage <= N) {
         failMap.set(stage, failMap.get(stage) + 1);
      }

      if (stage <= N) {
         for (let s = 1; s <= stage; s++) {
            clearMap.set(s, clearMap.get(s) + 1);
         }
      } else {
         for (let s = 1; s <= N; s++) {
            clearMap.set(s, clearMap.get(s) + 1);
         }
      }
   }

   //console.log(failMap);
   //console.log(clearMap);

   let failRatioArr = [];
   for (let i = 1; i <= N; i++) {
      if (clearMap.get(i) === 0) {
         failRatioArr.push({ idx: i, ratio: 0 });
      } else {
         let failRatio = failMap.get(i) / clearMap.get(i);
         failRatioArr.push({ idx: i, ratio: failRatio });
      }
   }

   failRatioArr.sort((a, b) => {
      if (a.ratio > b.ratio) {
         return -1;
      } else if (a.ratio < b.ratio) {
         return 1;
      }
      return 0;
   });

   var answer = [];
   for (let i = 0; i < failRatioArr.length; i++) {
      answer.push(failRatioArr[i].idx);
   }

   return answer;
}
