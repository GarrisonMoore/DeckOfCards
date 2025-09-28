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

    protected ArrayList<Card> hand;
    protected boolean dealerBust;


    public Dealers(String name) {
        super (MAGENTA+name+RESET);
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

    public void addCardToHand(Card card){
        this.hand.add(card);
    }

    public void showHand(){     //displays the dealers hand.
        System.out.printf("%n%s's hand: %s",this.name,hand);
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
        this.blackjack = false;
    }

    public void checkForBlackjack(){
        if(this.score == 21 && hand.size() == 2){
            blackjack = true;
            System.out.printf("%n%s has Blackjack!", name);
        }
    }
}
