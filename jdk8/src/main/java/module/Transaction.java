package module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;
}
