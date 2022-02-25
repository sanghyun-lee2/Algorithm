import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class PGM_완주하지못한선수_211202 {

	public static String solution(String[] participant, String[] completion) {
		
		Arrays.sort(participant);
		Arrays.sort(completion);
		
		String answer = "";
		for(int i = 0; i < participant.length; i++) {
			if(i == participant.length - 1) {
				answer = participant[i];
			}
			else if(participant[i].compareTo(completion[i]) != 0) {
				// 다른경우
				answer = participant[i];
				break;
			}
		}
		
        return answer;
    }
	
	public static String solutionHash(String[] participant, String[] completion) {
		HashMap<String, Integer> hm = new HashMap<>();
		
		for(String player : participant) {
			if(hm.get(player) != null) {
				int num = hm.get(player);
				hm.put(player, num + 1);
			}else {
				hm.put(player, 1);
			}
		}
		
		for(String player : completion) {
			if(hm.get(player) != 0) {
				int num = hm.get(player);
				hm.put(player, num - 1);
			}
		}
		
		String answer = "";
		Iterator<String> keys = hm.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if(hm.get(key) != 0) {
				answer = key;
			}
		}
		
        return answer;
    }
	
	public static void main(String[] args) {
		String[] participant = {"mislav", "stanko", "mislav", "ana"};
		String[] completion = {"stanko", "ana", "mislav"};
		
		String res = solution(participant, completion);
		System.out.println(res);
	}

}
