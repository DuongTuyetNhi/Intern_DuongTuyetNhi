package enums;

public enum Amount {
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10");

    private final String amount;

    Amount(String amount) {
        this.amount = amount;
    }
    public String getValueAmount(){
        return amount;
    }
}
