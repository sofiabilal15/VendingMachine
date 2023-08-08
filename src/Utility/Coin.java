package Utility;

public enum Coin {
    PENNY(1),NICKEL(5),DIME(10),QUARTERS(25);

    private int value;

    Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
