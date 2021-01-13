package d20201231;

import java.util.*;

public class Solution {
    
    public int eraseOverlapIntervals(int[][] intervals) {
        
        Map<Integer, List<Integer>> headMap = new HashMap<>();
        for (int[] interval : intervals) {

            if (headMap.get(interval[0]) == null){
                headMap.put(interval[0], new ArrayList<>());
            }
            headMap.get(interval[0]).add(interval[1]);
        }

        Map<Integer, List<Integer>> tailMap = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : headMap.entrySet()) {
            List<Integer> intList = entry.getValue();
            Integer tail = intList.stream().min(Integer::compareTo).get();
            if (tailMap.get(tail) == null){
                tailMap.put(tail, new ArrayList<>());
            }
            tailMap.get(tail).add(entry.getKey());
        }

        return intervals.length - tailMap.size();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {{1,100}, {11,22}, {1,11},{2,12}};
        System.out.println(solution.eraseOverlapIntervals(intervals));
    }
}
