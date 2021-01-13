package d20201223;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
    public int firstUniqChar(String s) {

        if (s.length() > 0){
            Map<Object, Long> map = Arrays.stream(s.split("")).collect(Collectors.groupingBy(str -> str.charAt(0), Collectors.counting()));
            for (int i = 0; i < s.length(); i++){
                if (map.get(s.charAt(i)) == 1){
                    return i;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.firstUniqChar(""));
    }
}
