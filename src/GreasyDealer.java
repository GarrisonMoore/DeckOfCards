import java.util.Random;

public abstract class GreasyDealer extends Dealers {

    public GreasyDealer(String name){
        super (name);
    }

    @Override
    public void turn(Dealers dealer, Deck deck1, Players player) throws InterruptedException {
        if (dealer.getScore(score) < 17){
            Card drawnCard = this.drawCard(deck1);      // making the dealer hit until he gets to 17 or more.
            System.out.printf("%n%s hits!, ",name);
            System.out.printf("%n%s's card drawn: %s",name,drawnCard);
            dealer.addCardToHand(drawnCard); //adding card object to the dealers hand
            dealer.showHand();
            dealer.checkScore();
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
}
