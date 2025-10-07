/** This class represents the 'level 1' dealer, inherits from the Dealers / PLayers classes.
 * This object represents an honest dealer.
 * It will hit until it gets to 17 or more.
 * No cheating used here.
 */

public class LooseDealer extends Dealers {

    public LooseDealer(String name) {
        super (name = YELLOW+"Loose Dealer"+RESET);
        this.deckCount = 2;
        System.out.printf("A %s wants to play "+CYAN+"BlackJack!"+RESET,name);
        displayDecks();
    }

    @Override
    public void turn (Dealers dealer, Deck deck1, Players player) throws InterruptedException {
        while (dealer.getScore(score) < 17) {
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
