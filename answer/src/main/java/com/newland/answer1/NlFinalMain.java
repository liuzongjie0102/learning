package com.newland.answer1;

public class NlFinalMain {

    public static void main(String[] args) {
        /**
         * b+s=55
         * 4b-2s=10
         */

        int a1=1;
        int b1=1;
        int c1=55;
        int a2=4;
        int b2=-2;
        int c2=10;

        int[] answer = getBinarOnceAnswer(a1, b1, c1, a2, b2, c2);

        System.out.println(String.format("大袋子：%s个，小袋子：%s个", answer[0], answer[1]));
    }

    private static int[] getBinarOnceAnswer(int a1, int b1, int c1, int a2, int b2, int c2){
        int j,k,l,x,y;
        j=a2/a1;
        a1=a1*j;
        b1=b1*j;
        c1=c1*j;
        k=c2-c1;
        l=b2-b1;
        y=k/l;
        x=(c2-b2*y)/a2;
        int[] answer = {x, y};
        return answer;
    }
}
