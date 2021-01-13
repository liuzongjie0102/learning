package collector;

import java.util.Map;
import java.util.stream.IntStream;

public class TestCollector {

    public static void main(String[] args) {
        long star = System.currentTimeMillis();
        Map<Boolean, Integer> primeMap = IntStream.rangeClosed(2, 100000).boxed().collect(new PrimeNumberCollector());
        System.out.println(primeMap);
        System.out.println(System.currentTimeMillis() - star);
        long star2 = System.currentTimeMillis();
        int prime = 0;
        for (int n = 2; n < 100000; n++){
            boolean flag = true;
            for (int i = 2; i < n; i++){
                if (n % i == 0){
                    flag = false;
                }
            }
            if (flag){
                prime++;
            }
        }
        System.out.println(prime);
        System.out.println(System.currentTimeMillis() - star2);
    }
}
