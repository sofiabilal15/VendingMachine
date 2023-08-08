package Utility;
import java.math.RoundingMode;
import java.math.BigDecimal;

public class Change {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;

    public Change(BigDecimal changeInPennies){
        calculateChange(changeInPennies);
    }

    private void calculateChange(BigDecimal changeInPennies) {
        pennies = changeInPennies.intValue();

        for (Coin coin : Coin.values()) {
            BigDecimal coinValue = BigDecimal.valueOf(coin.getValue());
            int coinCount = changeInPennies.divide(coinValue, 0, RoundingMode.DOWN).intValue();
            changeInPennies = changeInPennies.remainder(coinValue);

            switch (coin) {
                case QUARTERS:
                    quarters = coinCount;
                    break;
                case DIME:
                    dimes = coinCount;
                    break;
                case NICKEL:
                    nickels = coinCount;
                    break;
                case PENNY:
                    pennies = coinCount;
                    break;
            }
        }
    }


    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
}
