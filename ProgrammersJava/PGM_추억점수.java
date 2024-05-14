import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {

        ArrayList<Integer> result = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for(int i = 0; i < name.length; i++){
            map.put(name[i], yearning[i]);
        }

        for(int i = 0; i < photo.length; i++){
            int score = 0;
            for(int j = 0; j < photo[i].length; j++){
                String n = photo[i][j];
                if(map.get(n) != null){
                    score += map.get(n);
                }
            }
            result.add(score);
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}