import java.util.ArrayList;
import java.util.Scanner;

public class Players {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";

    private final ArrayList<Card> playerHand;
    public String name;
    public int bank;
    protected int score;
    protected int wager;
    public boolean Bust = false;
    public boolean hasPassed = false;

    public Players(String name) {  //player constructor
        this.bank = 10000; //starting the players bank at 10,000. Value will be subtracted using the playerWager method.
        this.name = name;
        this.wager = wager;
        this.playerHand = new ArrayList<>(); //making a new empty arraylist for the players hand.
        this.score = 0;    //Starting score at 0.
    }

    public void addCardToHand(Card card) {  //method adds a card object to the players hand, for use in playerTurn method.
        playerHand.add(card);
    }

    public void showHand() {  //method that displays the players hand
        System.out.printf("%n%s's hand: %s", this.name, playerHand);
    }

    public String getName() {  // method to get the players name.
        return this.name;
    }

    public void showBank() {     //method that displays the player objects current bank value.
        System.out.printf("%n%s's bank value: $%d%n", name, bank);
    }

    public int getScore() {
        return score;
    }


    public void checkScore() { //method calculates the players score and displays it.
        this.score = 0;
        int aces = 0;
        for (Card card : playerHand) {
            this.score += card.getCardValue();
            if (card.getRank().contains("ACE")) {
                aces++; // checking for aces and logging the number of aces.
            }
        }
        while (this.score > 21 && aces > 0) {  //if score is greater than 21 and there is an ACE in the hand, score - 10.
            this.score -= 10;
            aces--;
        }
        System.out.printf("%n%s's score: %d", name, this.score);

        if (this.score > 21) {     //also check if the player has bust
            System.out.printf("%n%s busts!", name);
            Bust = true;
        }
    }

    //method that accepts user input for a wager, gives it to the pot, and subtracts it from the players bank.
    public void playerWager() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nEnter your wager: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        this.wager = scanner.nextInt();
        this.bank -= this.wager;
        Pot.playerWager = this.wager;
        Pot.dealerWager = this.wager;

        scanner.nextLine();
    }

    public int getWager(){
        return this.wager;
    }

    public void DealerMatchPlayerWager(Dealers dealer){
        dealer.bank -= getWager();
    }

    public void clearPlayer(){
        this.playerHand.clear();
        this.score = 0;
        hasPassed = false;
        Bust = false;
    }


}
