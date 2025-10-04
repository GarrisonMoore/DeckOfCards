/** Player 2 currently not implemented, inherits from the Players class.
 * need to add this variant to the "table" and make it play automatically like the dealers.
 */

import java.util.Scanner;

public class Player2 extends Players{

    public Player2(String name){
        super (name);
        this.wallet = 1000;
    }

    @Override
    public void turn(Dealers Dealer, Deck deck1, Players players) throws InterruptedException {
        Scanner scanner1 = new Scanner(System.in);
        while (turnActive) { // trying to encapsulate this in a loop, so that in case of invalid input, the user can try again.
            System.out.printf("%n%s, would you like to Hit or Stand? (h/s)", players.getName());
            String choice = scanner1.nextLine();

            if (choice.equalsIgnoreCase("h")) {  // if else checks to see what the player wants to do.

                System.out.println(players.getName() + " hits!");

                Card drawnCard = this.drawCard(deck1); //dealer draws a card object
                System.out.printf("%n%s's card drawn: %s",players.getName(), drawnCard);

                players.addCardToHand(drawnCard); //dealers gives the card object to the players hand
                players.showHand();  //shows the players updated hand
                players.checkScore();//shows the players updated score
                if (players.Bust){
                    turnActive = false;
                }
            } else if (choice.equalsIgnoreCase("s")) {
                System.out.printf("%n%s stands.",players.getName());
                players.hasPassed = true;
                turnActive = false;
            } else {
                System.out.println("Invalid input. Please enter 'h' or 's'.");
            }
        }
        Thread.sleep(1000);
    }
}

