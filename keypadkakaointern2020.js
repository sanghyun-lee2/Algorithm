// 카카오 인턴쉽 문제 2020
// 키패드 문제
 
/*
이 전화 키패드에서 왼손과 오른손의 엄지손가락만을 이용해서 숫자만을 입력하려고 합니다.
맨 처음 왼손 엄지손가락은 * 키패드에 오른손 엄지손가락은 # 키패드 위치에서 시작하며, 엄지손가락을 사용하는 규칙은 다음과 같습니다.
엄지손가락은 상하좌우 4가지 방향으로만 이동할 수 있으며 키패드 이동 한 칸은 거리로 1에 해당합니다.
왼쪽 열의 3개의 숫자 1, 4, 7을 입력할 때는 왼손 엄지손가락을 사용합니다.
오른쪽 열의 3개의 숫자 3, 6, 9를 입력할 때는 오른손 엄지손가락을 사용합니다.
가운데 열의 4개의 숫자 2, 5, 8, 0을 입력할 때는 두 엄지손가락의 현재 키패드의 위치에서 더 가까운 엄지손가락을 사용합니다.
4-1. 만약 두 엄지손가락의 거리가 같다면, 오른손잡이는 오른손 엄지손가락, 왼손잡이는 왼손 엄지손가락을 사용합니다.
순서대로 누를 번호가 담긴 배열 numbers, 왼손잡이인지 오른손잡이인 지를 나타내는 문자열 hand가 매개변수로 주어질 때, 각 번호를 누른 엄지손가락이 왼손인 지 오른손인 지를 나타내는 연속된 문자열 형태로 return 하도록 solution 함수를 완성해주세요.
*/

function solution(numbers, hand) {
    var mapDistance = new Map();

    mapDistance.set("12", 1); // 현재 키패드 1이고 키패드 2 입력하고자 하는 경우 거리 1
    mapDistance.set("42", 2); // 현재 키패드 4이고 키패드 2 입력하고자 하는 경우 거리 2
    mapDistance.set("72", 3); // 현재 키패드 7이고 키패드 2 입력하고자 하는 경우 거리 3
    mapDistance.set("*2", 4); // 현재 키패드 *이고 키패드 2 입력하고자 하는 경우 거리 4
    mapDistance.set("32", 1); // 현재 키패드 3이고 키패드 2 입력하고자 하는 경우 거리 1
    mapDistance.set("62", 2); // 현재 키패드 6이고 키패드 2 입력하고자 하는 경우 거리 2
    mapDistance.set("92", 3); // 현재 키패드 9이고 키패드 2 입력하고자 하는 경우 거리 3
    mapDistance.set("#2", 4); // 현재 키패드 #이고 키패드 2 입력하고자 하는 경우 거리 4

    mapDistance.set("15", 2); // 현재 키패드 1이고 키패드 5 입력하고자 하는 경우 거리 2
    mapDistance.set("45", 1); // 현재 키패드 4이고 키패드 5 입력하고자 하는 경우 거리 1
    mapDistance.set("75", 2); // 현재 키패드 7이고 키패드 5 입력하고자 하는 경우 거리 2
    mapDistance.set("*5", 3); // 현재 키패드 *이고 키패드 5 입력하고자 하는 경우 거리 3
    mapDistance.set("35", 2); // 현재 키패드 3이고 키패드 5 입력하고자 하는 경우 거리 2
    mapDistance.set("65", 1); // 현재 키패드 6이고 키패드 5 입력하고자 하는 경우 거리 1
    mapDistance.set("95", 2); // 현재 키패드 9이고 키패드 5 입력하고자 하는 경우 거리 2
    mapDistance.set("#5", 3); // 현재 키패드 #이고 키패드 5 입력하고자 하는 경우 거리 3

    mapDistance.set("18", 3); // 현재 키패드 1이고 키패드 8 입력하고자 하는 경우 거리 3
    mapDistance.set("48", 2); // 현재 키패드 4이고 키패드 8 입력하고자 하는 경우 거리 2
    mapDistance.set("78", 1); // 현재 키패드 7이고 키패드 8 입력하고자 하는 경우 거리 1
    mapDistance.set("*8", 2); // 현재 키패드 *이고 키패드 8 입력하고자 하는 경우 거리 2
    mapDistance.set("38", 3); // 현재 키패드 3이고 키패드 8 입력하고자 하는 경우 거리 3
    mapDistance.set("68", 2); // 현재 키패드 6이고 키패드 8 입력하고자 하는 경우 거리 2
    mapDistance.set("98", 1); // 현재 키패드 9이고 키패드 8 입력하고자 하는 경우 거리 1
    mapDistance.set("#8", 2); // 현재 키패드 #이고 키패드 8 입력하고자 하는 경우 거리 2

    mapDistance.set("10", 4); // 현재 키패드 1이고 키패드 0 입력하고자 하는 경우 거리 4
    mapDistance.set("40", 3); // 현재 키패드 4이고 키패드 0 입력하고자 하는 경우 거리 3
    mapDistance.set("70", 2); // 현재 키패드 7이고 키패드 0 입력하고자 하는 경우 거리 2
    mapDistance.set("*0", 1); // 현재 키패드 *이고 키패드 0 입력하고자 하는 경우 거리 1
    mapDistance.set("30", 4); // 현재 키패드 3이고 키패드 0 입력하고자 하는 경우 거리 4
    mapDistance.set("60", 3); // 현재 키패드 6이고 키패드 0 입력하고자 하는 경우 거리 3
    mapDistance.set("90", 2); // 현재 키패드 9이고 키패드 0 입력하고자 하는 경우 거리 2
    mapDistance.set("#0", 1); // 현재 키패드 #이고 키패드 0 입력하고자 하는 경우 거리 1

    mapDistance.set("25", 1); // 현재 키패드 2이고 키패드 5 입력하고자 하는 경우 거리 1
    mapDistance.set("28", 2); // 현재 키패드 2이고 키패드 8 입력하고자 하는 경우 거리 2
    mapDistance.set("20", 3); // 현재 키패드 2이고 키패드 0 입력하고자 하는 경우 거리 3
    
    mapDistance.set("52", 1); // 현재 키패드 5이고 키패드 2 입력하고자 하는 경우 거리 1
    mapDistance.set("58", 1); // 현재 키패드 5이고 키패드 8 입력하고자 하는 경우 거리 1
    mapDistance.set("50", 2); // 현재 키패드 5이고 키패드 0 입력하고자 하는 경우 거리 2

    mapDistance.set("82", 2); // 현재 키패드 8이고 키패드 2 입력하고자 하는 경우 거리 2
    mapDistance.set("85", 1); // 현재 키패드 8이고 키패드 5 입력하고자 하는 경우 거리 1
    mapDistance.set("80", 1); // 현재 키패드 8이고 키패드 0 입력하고자 하는 경우 거리 1

    mapDistance.set("02", 3); // 현재 키패드 0이고 키패드 2 입력하고자 하는 경우 거리 3
    mapDistance.set("05", 2); // 현재 키패드 0이고 키패드 5 입력하고자 하는 경우 거리 2
    mapDistance.set("08", 1); // 현재 키패드 0이고 키패드 8 입력하고자 하는 경우 거리 1

    mapDistance.set("22", 0); // 현재 키패드 2이고 키패드 2 입력하고자 하는 경우 거리 0
    mapDistance.set("55", 0); // 현재 키패드 5이고 키패드 5 입력하고자 하는 경우 거리 0
    mapDistance.set("88", 0); // 현재 키패드 8이고 키패드 8 입력하고자 하는 경우 거리 0
    mapDistance.set("00", 0); // 현재 키패드 0이고 키패드 0 입력하고자 하는 경우 거리 0

    var leftFingerKeypad = '*'; // 왼쪽 엄지손가락 최초 키패드 상태
    var RightFingerKeypad = '#'; // 오른쪽 엄지손가락 최초 키패드 상태

    var answer = '';

    for(var i = 0; i < numbers.length; i++)
    {
        var number = numbers[i];

        if(number == 1 || number == 4 || number == 7)
        {
           answer += 'L';
           leftFingerKeypad += number; 
        }
        else if(number == 3 || number == 6 || number == 9)
        {
           answer += 'R';
           RightFingerKeypad += number; 
        }
        else // 2 5 8 0 입력 시
        {
            var lastleftfinger = leftFingerKeypad[leftFingerKeypad.length-1]; // 왼쪽 엄지손가락 마지막 입력 값 가져옴
            var lastrightfinger = RightFingerKeypad[RightFingerKeypad.length-1]; // 오른쪽 엄지손가락 마지막 입력 값 가져옴

            lastleftfinger += number;
            lastrightfinger += number;

            var leftFingerDistance = mapDistance.get(lastleftfinger);
            var RightFingerDistance = mapDistance.get(lastrightfinger);

            if(leftFingerDistance < RightFingerDistance) // 왼쪽엄지손가락이 거리가 더 짧은 경우
            {
                answer += 'L';
                leftFingerKeypad += number; 
            }
            else if(leftFingerDistance > RightFingerDistance) // 오른쪽엄지손가락이 거리가 더 짧은 경우
            {
                answer += 'R';
                RightFingerKeypad += number;
            }
            else // 동일한 거리인 경우
            {
                if(hand == "left")
                {
                    answer += 'L';
                    leftFingerKeypad += number; 
                }
                else
                {
                    answer += 'R';
                    RightFingerKeypad += number;
                }
            }
        }
    }

    return answer;
}

var numbers = [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5];
var hand = "right";

var result = solution(numbers, hand);

console.log(result);