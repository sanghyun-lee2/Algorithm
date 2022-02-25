/*
수많은 마라톤 선수들이 마라톤에 참여하였습니다. 
단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.
마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 
이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return
 하도록 solution 함수를 작성해주세요.
*/

function solution(participant, completion) {
    var idxNotComp = 0; // 참여한사람 중 완주 못 한사람 인덱스

    var participantSort = participant.sort();
    var completionSort = completion.sort();

    for(var i in participantSort)
    {
        if(participantSort[i] != completionSort[i])
        {
            idxNotComp = i;
            break;
        }
    }

    var answer = participantSort[idxNotComp];

    return answer;
}

var participant = ["marina", "josipa", "nikola", "vinko", "filipa"];
var completion = ["josipa", "filipa", "marina", "nikola"]

var result = solution(participant, completion);

console.log(result);