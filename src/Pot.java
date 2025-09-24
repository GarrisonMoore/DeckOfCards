public class Pot {

    public static int dealerWager;
    public static int playerWager;
    public int potValue;

    public Integer displayPotValue(){
        potValue = dealerWager + playerWager;
        System.out.printf("%nOur total pot value is: $%d",potValue);
        return potValue;
    }
    public Integer getPotValue(){
        return potValue;
    }

    public void clearPot(){
        this.potValue = 0;
    }
}
//need to make a method that distributes the pot winnings to the winners bank.
//need to make a method for a tie condition..