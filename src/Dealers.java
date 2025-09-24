import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public abstract class Dealers extends Players {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";

    private ArrayList<Card> hand;
    public boolean dealerBust;
    public boolean turnActive = true;

    public Dealers(String name) {
        super (name);
        this.hand = new ArrayList<>(); //making a new empty arraylist for the dealers hand.
        this.score = 0;  //starting score at 0.
    }

    public abstract void turn(Dealers Dealer,Deck deck1, Players players) throws InterruptedException;

    public void ShuffleDeck(Deck deck1){
        Collections.shuffle(deck1.deckOfCards);
        System.out.printf("%n%S shuffling...",name);
    }

    public void dealInitialHand(Dealers dealer, Deck deck1, Players player){
        System.out.printf("%nDealing the initial hand...");
        //deal two cards to the player and two to the dealer
        player.addCardToHand(this.drawCard(deck1));
        this.addCardToHand(this.drawCard(deck1));
        player.addCardToHand(this.drawCard(deck1));
        this.addCardToHand(this.drawCard(deck1));
        System.out.println(" ");
    }

    public Card drawCard(Deck deck1){       //method draws a single card
        return deck1.deckOfCards.removeFirst();
    }

    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    public void showHand(){     //displays the dealers hand.
        System.out.printf("%n%s's hand: %s",this.name,hand);
    }

    public void playerTurn(Players player, Deck deck1, Scanner scanner) throws InterruptedException { //this should probably be in the player class.
        while (turnActive) { // trying to encapsulate this in a loop, so that in case of invalid input, the user can try again.
            System.out.printf("%n%s, would you like to Hit or Stand? (h/s)", player.getName());
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("h")) {  // if else checks to see what the player wants to do.

                System.out.println(player.getName() + " hits!");

                Card drawnCard = this.drawCard(deck1); //dealer draws a card object
                System.out.printf("%n%s's card drawn: %s",player.getName(), drawnCard);

                player.addCardToHand(drawnCard); //dealers gives the card object to the players hand
                player.showHand();  //shows the players updated hand
                player.checkScore();//shows the players updated score
                if (player.Bust){
                    turnActive = false;
                }
            } else if (choice.equalsIgnoreCase("s")) {
                System.out.printf("%n%s stands.",player.getName());
                player.hasPassed = true;
                turnActive = false;
            } else {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
        }
        Thread.sleep(1000);
    }

    public int getScore(Integer score){
        return this.score;
    }

    @Override
    public void checkScore(){ //method calculates the players score and displays it.
        score = 0;
        int aces = 0;
        for (Card card : hand) {
            score += card.getCardValue();
            if (card.getRank().contains("ACE")) {
                aces++; // checking for aces and logging the number of aces.
            }
        }
        while (this.score > 21 && aces > 0) {  //if score is greater than 21 and there is an ACE in the hand, score - 10.
            this.score -= 10;
            aces--;
        }
        System.out.printf("%n%s's score: %d",name,score);

        if (score > 21){     //also check if the player has bust
            System.out.printf("%n%s busts!",name);
            this.dealerBust = true;
        }
    }

    public void showDealerBank(){     //method that displays the dealer current bank value.
        System.out.printf("%n%s's bank value: $%d%n",name,bank);
    }

    // not currently in use.
    public int getBankValue(){
        return bank;
    }

    public void clearDealer(){
        this.hand.clear();
        this.score = 0;
        this.dealerBust = false;
        this.turnActive = true;
    }

}
