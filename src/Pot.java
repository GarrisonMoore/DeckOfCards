/** Pot object
 * holds wager values
 * calculate and distributed payouts
 */

public class Pot {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";
    public static final String WHITEbkgnd = "\u001B[47m";
    public static final String YELLOWbkgnd = "\u001B[43m";

    public static final String BOLD = "\u001B[1m\n";

    protected static int Wager; // int value used in playerWager method, transfers players wager to this pot object.
    protected int potValue; // int value to represent the pots total value

    public void displayPotValue() {  // Method transfers the Wager value to the potValue, and displays potValue.
        potValue = Wager;
        System.out.printf("%nOur total pot value is: "+GREEN+"$%d"+RESET,potValue);
    }

    public Integer getPotValue() { // Unused currently
        return potValue;
    }

    public void clearPot() { // Clears the pot value when a round is over.
        this.potValue = 0;
    }

    public int returnPlayerWager() { // Gives the player back their wager, used under tie condition.
        return Wager;
    }

    public int get1to1Payout() { // Standard payout
        return Wager *2;
    }

    public int get3to2payout() {  // Blackjack payout
        return (int) (Wager + (Wager * 1.5));
    }
}