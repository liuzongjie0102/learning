import com.sun.xml.internal.ws.commons.xmlutil.Converter;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * sum
 */
public class ForkJoinTest implements Serializable {

    private long[] numbers;
    private int start;
    private int end;

    private static int THREAD_NUM = 100_000;

    public ForkJoinTest(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinTest(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override protected Long compute() {
        Converter
        int length = end - start;
        if (length <= THREAD_NUM){
            return this.computeSequentially();
        }

        ForkJoinTest left = new ForkJoinTest(numbers, start, start + length/2);
        left.fork();
        ForkJoinTest right = new ForkJoinTest(numbers, start + length/2, end);
        Long leftResult = right.compute();
        Long rightResult = left.join();

        return leftResult + rightResult;
    }

    private long computeSequentially(){
        long sum = 0;
        for (int i = this.start; i < this.end; i++){
            sum += numbers[i];
        }
        return sum;
    }

    public static void main(String[] args) {
//        long[] numbers = LongStream.range(1, 1000_000_00).toArray();
//        ForkJoinTest task = new ForkJoinTest(numbers);
//        long start = System.currentTimeMillis();
//        Long sum = new ForkJoinPool().invoke(task);
//        long end = System.currentTimeMillis();
//        System.out.println(sum);
//        System.out.println(Arrays.stream(numbers).sum());
//        long end2 = System.currentTimeMillis();
//        System.out.format("forkJoin %s \n" , end - start);
//        System.out.println(end2 - end);
        String temp = predictPartyVictory("RDD");
        System.out.println(temp);
    }

        public static String predictPartyVictory(String senate) {
            int n = senate.length();
            Queue<Integer> radiant = new LinkedList<>();
            Queue<Integer> dire  = new LinkedList<>();

            for (int i = 0; i < n; i++){
                char role = senate.charAt(i);
                if (role == 'R'){
                    radiant.offer(i);
                }else{
                    dire.offer(i);
                }
            }

            while (!radiant.isEmpty() && !dire.isEmpty()){
                int r = radiant.poll();
                int d = dire.poll();
                if (r > d){
                    dire.offer(d + n);
                }else {
                    radiant.offer(r + n);
                }

            }

            return radiant.isEmpty() ? "dire" : "Radiant";
        }
}
