var logObjs = [];
var map = new Map();

function parsing(record) {
  for (data of record) {
    const [act, id, nick] = data.split(" ");

    // 로그 객체에는 전체 다 넣음
    let logObj = { act: act, id: id, nick: nick };
    logObjs.push(logObj);

    // 나가기가 아니면 맵객체에 넣음 키:Id, 값:닉네임
    if (logObj.act !== "Leave") {
      map.set(id, nick);
    }
  }
}

function printLogs() {
  let res = [];

  for (log of logObjs) {
    if (log.act !== "Change") {
      nick = map.get(log.id); // 로그 id에 해당되는 닉네임 가져옴

      act = "";
      if (log.act === "Enter") {
        act = " 들어왔습니다.";
      } else if (log.act === "Leave") {
        act = " 나갔습니다.";
      }

      res.push(nick + "님이" + act);
    }
  }

  return res;
}

function solution(record) {
  parsing(record);
  var answer = printLogs();
  return answer;
}
