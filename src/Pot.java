public class Pot {

    public static int dealerWager;
    public static int playerWager;

    public void showPotValue(){
        int potValue = dealerWager + dealerWager;
        System.out.printf("%nOur total pot value is: $%d",potValue);
    }
    public void distributeWinnings(){

    }
}
//need to make a method that distributes the pot winnings to the winners bank.
//need to make a method for a tie condition..