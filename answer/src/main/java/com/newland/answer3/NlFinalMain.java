package com.newland.answer3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NlFinalMain {

    public static void main(String[] args) {
        Integer[] hedges = {6,3,5,8,4,3,1,2};
        int maxArea = new NlFinalMain().getMaxArea(hedges);
        System.out.println(maxArea);
    }

    public int getMaxArea(Integer[] hedges){

        Arrays.sort(hedges,Collections.reverseOrder());
        int sum = 0;
        for (int hedge : hedges) {
            sum += hedge;
        }

        int halfSum = sum / 2;
        int i = halfSum / 2;
        for(; i > 0; i--){
            List<Integer> useList = new ArrayList<>();



            int next = halfSum - i;


        }


        int maxHedge = hedges[0];

        return maxHedge * (halfSum - maxHedge);
    }
}
