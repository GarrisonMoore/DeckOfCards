import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Dealer {

    private ArrayList<Card> hand;
    private String name;
    private int score;
    private int bank;
    private int wager;
    static boolean dealerBust;
    static boolean h = true;

    public Dealer(String name) {  //dealer constructor
        this.bank = 10000;
        this.dealerBust = false;
        this.name = name;
        this.wager = wager;  //Dealers wager automatically matches the players wager.
        this.hand = new ArrayList<>(); //making a new empty arraylist for the dealers hand.
        this.score = 0;  //starting score at 0.
    }
    public void ShuffleDeck(Deck deck1){
        Collections.shuffle(deck1.deckOfCards);
        System.out.printf("%n%S shuffling...",this.name);
    }

    public void dealInitialHand(Dealer dealer, Deck deck1,Player player){
        System.out.println("\nDealing the initial hand...");
        //deal two cards to the player and two to the dealer
        player.addCardToHand(this.drawCard(deck1));
        this.addCardToHand(this.drawCard(deck1));
        player.addCardToHand(this.drawCard(deck1));
        this.addCardToHand(this.drawCard(deck1));
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

    public void playerTurn(Player player, Deck deck1, Scanner scanner) throws InterruptedException { //this should probably be in the player class.
        while (h) { // trying to encapsulate this in a loop, so that in case of invalid input, the user can try again.
            System.out.printf("%n%s, would you like to Hit or Pass? (h/p)", player.getName());
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("h")) {  // if else checks to see what the player wants to do.

                System.out.println(player.getName() + " hits!");

                Card drawnCard = this.drawCard(deck1); //dealer draws a card object
                System.out.printf("%n%s's card drawn: %s",player.getName(), drawnCard);

                player.addCardToHand(drawnCard); //dealers gives the card object to the players hand
                player.showHand();  //shows the players updated hand
                player.checkPlayerScore(player);    //shows the players updated score

            } else if (choice.equalsIgnoreCase("p")) {
                System.out.printf("%n%s passes.",player.getName());
                h = false;
                break; // exit the loop immediately. preventing infinite loop
            } else {
                System.out.println("Invalid input. Please enter 'h' or 'p'.");
            }
        break; //ensuring there is no infinite loop
        }
        Thread.sleep(3000);
    }
    public void dealerTurn(Dealer dealer, Deck deck1, Player player) throws InterruptedException {
        if (dealer.score <= 17){
            Card drawnCard = this.drawCard(deck1);      // making the dealer hit until he gets to 17 or more.
            System.out.printf("%n%s hits!, ",dealer.name);
            System.out.printf("%n%s's card drawn: %s",name,drawnCard);
            dealer.addCardToHand(drawnCard); //adding card object to the dealers hand
            dealer.showHand();
            dealer.checkDealersScore(dealer);
            if (dealer.score > 17){     //trying to make the dealer have a 50/50 chance of hitting or passing when their score is above 17. not currently working correctly.
                Random random = new Random();
                int randomInt = random.nextInt(3);
                if (randomInt == 1){
                    drawnCard = this.drawCard(deck1);
                }else if (randomInt == 2){
                    System.out.printf("%n%s passes.",dealer.name);
                }
            }
        }

        Thread.sleep(3000);
    }

    public void checkDealersScore(Dealer dealer){  // method that calculates the dealers score and displays it.
        dealer.score = 0;
        for (Card card : dealer.hand) {  //sorting the dealers hand by card value.
            dealer.score += card.getCardValue();
        }
        System.out.printf("%nDealer's score: %d",dealer.score);

        if (dealer.score > 21){  // also check if the dealer has bust
            System.out.printf("%n%s busts!",dealer.name);
            dealerBust= true;
        }
    }

}
