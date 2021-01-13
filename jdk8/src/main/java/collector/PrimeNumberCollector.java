package collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class PrimeNumberCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, Integer>> {

    private Boolean isPrime(List<Integer> primes, int candidate){
        return primes.stream().noneMatch(i -> candidate % i == 0);
    }

    @Override public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return () -> new HashMap<Boolean, List<Integer>>(){{
            put(true, new ArrayList<>());
            put(false, new ArrayList<>());
        }};
    }

    @Override public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (Map<Boolean, List<Integer>> acc, Integer candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
    }

    @Override public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (Map<Boolean, List<Integer>> map1, Map<Boolean, List<Integer>> map2) -> {
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override public Function<Map<Boolean, List<Integer>>, Map<Boolean, Integer>> finisher() {
        return (Map<Boolean, List<Integer>> map) -> new HashMap<Boolean, Integer>(){{
            put(true, map.get(true).size());
            put(false, map.get(false).size());
        }};
    }

    @Override public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT));
    }

}
