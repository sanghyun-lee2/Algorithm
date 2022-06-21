package ProgrammersJava;

import java.util.*;

class Solution {
    private static class LogObj{
        String strAct;
        String strId;
        String strNick;
    }

    LogObj[] LogObjs = new LogObj[100000];
    HashMap<String,String> map = new HashMap<>();

    public void parsing(String[] record){
        for(int i = 0; i < record.length;i++){
            String[] arr = record[i].split(" ");

            if(arr.length == 2){
                LogObj logObj = new LogObj();
                logObj.strAct = arr[0];
                logObj.strId = arr[1];
                LogObjs[i] = logObj;
            }else{
                LogObj logObj = new LogObj();
                logObj.strAct = arr[0];
                logObj.strId = arr[1];
                logObj.strNick = arr[2];
                LogObjs[i] = logObj;

                map.put(logObj.strId, logObj.strNick);
            }
        }
    }

    public String[] printLogs(String[] record){
        ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < record.length;i++){
            if(LogObjs[i].strAct.contains("Change") == false){
                String nick = map.get(LogObjs[i].strId);
                String act = "들어왔습니다.";

                if(LogObjs[i].strAct.contains("Leave") == true){
                    act = "나갔습니다.";
                }
                String res = nick + "님이 " + act;
                list.add(res);
            }
        }

        return list.toArray(new String[0]);
    }

    public String[] solution(String[] record) {
        parsing(record);
        String[] answer = printLogs(record);
        return answer;
    }
}