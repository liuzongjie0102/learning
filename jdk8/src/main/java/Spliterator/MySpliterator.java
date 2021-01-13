package Spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class MySpliterator {
    public static void main(String[] args) {
        String SENTENCE = " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";

        Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        WordCounter wordCounter = stream.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("Found " + wordCounter.getCounter() + " words");

        Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
        Stream<Character> stream1 = StreamSupport.stream(spliterator, true);
        WordCounter wordCounter1 = stream1.parallel().reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
        System.out.println("Found " + wordCounter1.getCounter() + " words");
    }
}
