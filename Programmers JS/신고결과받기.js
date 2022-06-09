function solution(id_list, report, k) {
  var report_count_list = [];
  var stop_id_list = [];
  var answer = [];

  // 리포트 리스트 초기화
  for (let i = 0; i < id_list.length; i++) {
    report_count_list.push(0);
    answer.push(0);
  }

  // 리포트 중복 제거
  const setReports = new Set(report);
  const setReportArr = [...setReports];

  // 리포트 카운팅
  for (index in setReportArr) {
    var arr = setReportArr[index].split(" ");
    var reporter = arr[0];
    var badUser = arr[1];

    const isBadUser = (id) => id === badUser;
    const idx = id_list.findIndex(isBadUser);

    if (idx >= 0) {
      report_count_list[idx]++;
    }
  }

  // 정지 id 목록 갱신
  for (index in report_count_list) {
    if (report_count_list[index] >= k) {
      stop_id_list.push(id_list[index]);
    }
  }

  // 정지목록에 있다면 결과 메일 갱신
  for (index in setReportArr) {
    var arr = setReportArr[index].split(" ");
    var reporter = arr[0];
    var badUser = arr[1];

    const isBadUser = (id) => id === badUser;
    const isInUser = (id) => id === reporter;

    const idxStopUser = stop_id_list.findIndex(isBadUser);

    if (idxStopUser >= 0) {
      const idxUser = id_list.findIndex(isInUser);
      if (idxUser >= 0) {
        answer[idxUser]++;
      }
    }
  }

  return answer;
}
