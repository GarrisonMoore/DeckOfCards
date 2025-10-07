/** The parent class for all "players", includes all dealer and player variants
 *
 */

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Players {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";

    private final ArrayList<Card> playerHand;
    protected String name;
    protected double wallet;
    protected int score;
    protected int wager;
    protected boolean Bust = false;
    protected boolean hasPassed = false;
    protected boolean blackjack = false;
    protected boolean turnActive = true;
    Scanner scanner = new Scanner(System.in);

    public Players(String name) {  //player constructor

        this.name = name;
        this.wager = wager;
        this.playerHand = new ArrayList<>(); //making a new empty arraylist for the players hand.
        this.score = 0;    //Starting score at 0.
    }

    public Card drawCard(Deck deck1){       //method draws a single card
        return deck1.deckOfCards.removeFirst();
    }

    public abstract void turn(Dealers Dealer, Deck deck1, Players players) throws InterruptedException;

    public void addCardToHand(Card card) {  //method adds a card object to the players hand, for use in playerTurn method.
        playerHand.add(card);
    }

    public void showHand() {  // method that displays the players hand
        System.out.printf("%n%s's hand: %s", this.name, playerHand);
    }

    public String getName() {  // method to get the players name.
        return this.name;
    }

    public void showWallet() {     //method that displays the player objects current wallet value.
        System.out.println("\n"+name+"'s wallet value: "+GREEN+"$"+ wallet+RESET+"\n");
    }

    public int getScore() {
        return score;
    }

    public void checkScore() { //method calculates the players score and displays it.
        this.score = 0;
        int aces = 0;
        for (Card card : playerHand) { // checking for aces and logging the number of aces.
            this.score += card.getCardValue();
            if (card.getRank().contains("ACE")) {
                aces++;
            }
        }
        while (this.score > 21 && aces > 0) {  //if score is greater than 21 and there is an ACE in the hand, score - 10.
            this.score -= 10;                  // this allows ace scoring automatically when favorable.
            aces--;
        }
        System.out.printf("%n%s's score: %d", name, this.score); //displays score

        if (this.score > 21) {     //also check if the player has bust
            System.out.printf("%n%s busts!", name);
            Bust = true;
        }
    }

    //method that accepts user input for a wager, gives it to the pot, and subtracts it from the players wallet.
    public void playerWager() {

        System.out.println("New Round!");
        showWallet();
        System.out.println("Enter your wager: ");
        boolean Exception = true;

        while (Exception) {
            try { int wager = scanner.nextInt();

                if (wager > this.wallet) {  //if the user enters a wager that is greater than their wallet, it will automatically go all in while keeping wallet above 0.
                    wager = (int) this.wallet;  // making the wager equal to the players wallet value so that the wallet value does not go negative.
                    this.wager = wager;
                    this.wallet -= wager;
                } else {
                    this.wager = wager;
                    this.wallet -= wager;
                }
                Exception = false;
            } catch (Exception e) { //catching invalid input
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            Pot.Wager = this.wager;  //passing the wager to the Pot object.
            scanner.nextLine();
            showWallet();
        }
    }

    public int getWager(){ //currently unused method to retrieve the wager value
        return this.wager;
    }

    public void clearPlayer(){  //clears object values for a new round
        this.playerHand.clear();
        this.score = 0;
        hasPassed = false;
        Bust = false;
        turnActive = true;
        blackjack = false;
    }

    public void checkForBlackjack(){ //checks for blackjacks
        if(this.score == 21 && this.playerHand.size() == 2){
            blackjack = true;
            System.out.printf("%n%s has Blackjack!", name);
        }
    }


}
