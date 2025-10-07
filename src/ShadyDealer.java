/** This class represents the level 2 dealer, inherits from the Dealers / Players classes.
 * Dealer hits until they get to 17 or more.
 * Dealer uses a sleeved ace card to cheat, has a 50% chance of cheating if the dealer does not already have an ace in their hand.
 */


import java.util.Random;

public class ShadyDealer extends Dealers {

    private final Card sleevedACE; // hidden card this dealer uses to cheat
    private boolean hasCheated = false;

    public ShadyDealer(String name) {

        super (name = MAGENTA+"Shady Dealer"+RESET);
        this.deckCount = 3;
        this.sleevedACE = new Card("ACE", "SPADES", 11);
        displayDecks();
    }

    @Override
    public void turn (Dealers dealer, Deck deck1, Players player) throws InterruptedException {
        if (!hasCheated && score > 11) {
            Random RNG = new Random();
            boolean hasACE = false;
            for (Card card: hand ) {
                if (card.getRank().equalsIgnoreCase("ACE")) {
                    hasACE = true;
                    break;
                }
            }
            if (!hasACE) {
                if (RNG.nextInt(2) == 1) {
                    dealer.addCardToHand(sleevedACE);
                    System.out.printf("%n%s hits!, ", name);
                    System.out.printf("%n%s's card drawn: %s", name, sleevedACE);
                    dealer.showHand();
                    dealer.checkScore();
                    hasCheated = true;
                }else{
                    hasCheated = false;
                }
            }
            Thread.sleep(1000);
        }

        while (dealer.getScore(score) < 17){

            Card drawnCard = this.drawCard(deck1);      // making the dealer hit until he gets to 17 or more.
            System.out.printf("%n%s hits!, ",name);
            System.out.printf("%n%s's card drawn: %s",name,drawnCard);
            dealer.addCardToHand(drawnCard); //adding card object to the dealers hand
            dealer.showHand();
            dealer.checkScore();
            if (dealerBust) {
                return;
            }
            System.out.println(" ");
            Thread.sleep(2000);
        }
        if (!dealerBust){
            System.out.printf("%n%s stands.",name);
            dealer.hasPassed = true;
        }
        Thread.sleep(2000);
    }
}
