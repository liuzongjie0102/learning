package service;

import module.Trader;
import module.Transaction;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class lamdaMain {
    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                        new Transaction(brian, 2011, 300),
                        new Transaction(raoul, 2012, 1000),
                        new Transaction(raoul, 2011, 400),
                        new Transaction(mario, 2012, 710),
                        new Transaction(mario, 2012, 700),
                        new Transaction(alan, 2012, 950)
        );

        /**
         * (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
         * (2) 交易员都在哪些不同的城市工作过？
         * (3) 查找所有来自于剑桥的交易员，并按姓名排序。
         * (4) 返回所有交易员的姓名字符串，按字母顺序排序。
         * (5) 有没有交易员是在米兰工作的？
         * (6) 打印生活在剑桥的交易员的所有交易额。
         * (7) 所有交易中，高的交易额是多少？
         * (8) 找到交易额小的交易。
         */

        List<Transaction> demo1 = transactions.stream()
                        .filter(t -> t.getYear() == 2011)
                        .sorted(Comparator.comparing(Transaction::getValue))
                        .collect(Collectors.toList());
        System.out.println(demo1);

        List<String> demo2 = transactions.stream()
                        .map(t -> t.getTrader().getCity()).distinct()
                        .collect(Collectors.toList());
        System.out.println(demo2);

        List<Trader> demo3 = transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(t -> StringUtils.equalsIgnoreCase(t.getCity(), "Cambridge"))
                        .collect(Collectors.toList());
        System.out.println(demo3);
        
        String demo4 = transactions.stream()
                        .map(t -> t.getTrader().getName())
                        .distinct()
                        .sorted()
//                        .reduce("", (a, b) -> a + b);
                        .collect(Collectors.joining());
        System.out.println(demo4);
        
        boolean demo5 = transactions.stream().anyMatch(t -> StringUtils.equalsIgnoreCase(t.getTrader().getCity(), "Milan"));
        System.out.println(demo5);

        transactions.stream()
                        .filter(t -> StringUtils.equalsIgnoreCase(t.getTrader().getCity(), "Cambridge"))
                        .forEach(t -> System.out.println(t.getValue()));
        
        Integer demo7 = transactions.stream().map(t -> t.getValue()).reduce(Integer::max).get();
        System.out.println(demo7);
        
        Transaction demo8 = transactions.stream().min(Comparator.comparing(Transaction::getValue)).get();
        System.out.println(demo8);

    }
}
