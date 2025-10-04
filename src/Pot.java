/** Pot object
 * holds wager values
 * calculate and distributed payouts
 */

public class Pot {

    protected static int playerWager;
    protected int potValue;


    public void displayPotValue() {
        potValue = playerWager;
        System.out.printf("%nOur total pot value is: $%d",potValue);
    }
    public Integer getPotValue() {
        return potValue;
    }

    public void clearPot() {
        this.potValue = 0;
    }

    public int returnPlayerWager() {
        return playerWager;
    }

    public int get1to1Payout(){
        return playerWager*2;
    }

    public int get3to2payout(){
        return (int) (playerWager + (playerWager * 1.5));
    }
}