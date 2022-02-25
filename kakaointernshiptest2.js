function solution(places) {
    var answer = [1, 1, 1, 1, 1];

    for(var z = 0; z < places.length; z++)
    {

    
    var place = places[z];

    var PersonIdx = []; // 응시자 위치
    var BlockIdx = []; // 차단막 위치

    // 응시자위치 배열에 추가
    for(var i = 0; i < 5; i++)
    {
        var pos = 0;
        while(true){
            var foundPos = place[i].indexOf("P", pos);
            if(foundPos == -1) break;
    
            PersonIdx.push( i*5+foundPos );
            pos = foundPos + 1;
        }
    }

    // 차단막위치 배열에 추가
    for(var i = 0; i < 5; i++)
    {
        var pos = 0;
        while(true){
            var foundPos = place[i].indexOf("X", pos);
            if(foundPos == -1) break;
    
            BlockIdx.push( i*5+foundPos );
            pos = foundPos + 1;
        }
    }

    var manHtenArr = [];

    // 추가된 응시자위치에 대해 맨해튼 거리 확인
    for(var i = 0; i < PersonIdx.length; i++)
    {
        var orign = parseInt(PersonIdx[i]); // 응시자위치

        for(var j = 0; j < PersonIdx.length; j++)
        {
            var compare = parseInt(PersonIdx[j]);
            if(orign != compare)
            {
                // 맨해튼 거리 |r1-r2| + |c1-c2|
                var manHaten = Math.abs(parseInt(orign % 5) - parseInt(compare % 5)) + Math.abs(parseInt(orign / 5) - parseInt(compare / 5));
                manHtenArr.push(manHaten);

                if(manHaten <= 2) // 맨해튼 거리가 2이하 인 경우
                {
                    var IsFaild = false;

                    // 차단막 설치 확인
                    var orignCol = orign % 5;
                    var orignRow = orign / 5;
                    var compareCol = compare % 5;
                    var compareRow = compare / 5;

                    if(orignCol - compareCol > 1)
                    {
                        if( BlockIdx.includes(orignRow * 5 + orignCol + 1) == false )
                        {
                            IsFaild = true;
                        }
                    }
                    
                    if(compareCol - orignCol > 1)
                    {
                        if( BlockIdx.includes(compareRow * 5 + compareCol + 1) == false )
                        {
                            IsFaild = true;
                        }
                    }

                    if(orignRow - compareRow > 1)
                    {
                        if( BlockIdx.includes((orignRow + 1) * 5 + orignCol) == false )
                        {
                            IsFaild = true;
                        }
                    }

                    if(compareRow - orignRow > 1)
                    {
                        if( BlockIdx.includes((compareRow + 1) * 5 + compareCol) == false )
                        {
                            IsFaild = true;
                        }
                    }

                    if(IsFaild == true)
                    {
                        //console.log("거리두기실패!!");
                        answer[z] = 0;
                    }
                }
            }
        }
    }

}
    //console.log(PersonIdx);
    //console.log(manHtenArr);

    //var answer = [];
    return answer;
}

//var place = ["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"];
//var place = ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"];

var places = [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]];

var result = solution(places);

console.log(result);
