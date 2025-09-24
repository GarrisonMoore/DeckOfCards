import java.util.Scanner;

/* This is a work in progress towards a replayable blackjack game. It is not fully functional currently.

 If you just want to see the code for a deck of cards; see up to line 40 in this class(CardGame.java), and see Deck.java class
*/
public class CardGame {

    //getting colors
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";

    public static void main(String[] args) throws InterruptedException {

        boolean gameActive = true;
        boolean replay = false;     // boolean values used to control the game loops

            // first, we get user input for name and wager.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");

            // converting user input to a String object for easier transfer to player object name.
        String playerName = scanner.nextLine();

        Players player1 = new Player1(GREEN+playerName+RESET); // declaring the new player1 object, will ask for users name and wager, creating a new Player object.
        Dealers dealer1 = new ShadyDealer ("Shady Dealer"); // declaring a new dealer variant object.
        Pot currentPot = new Pot();// declaring a new pot object

        while (true) {

            player1.clearPlayer();
            dealer1.clearDealer();
            currentPot.clearPot();
            Deck deck1 = new Deck();
            System.out.println(" ");

        player1.showBank();     // this method shows the players bank balance.
        player1.playerWager();// this method gets a value from the user for their wager amount. this value is matched by the dealer.
        player1.DealerMatchPlayerWager(dealer1);
        dealer1.showDealerBank();
        player1.showBank();
        currentPot.displayPotValue();  //this method shows the total pot value.
        Thread.sleep(3000);

            System.out.println(" ");
            dealer1.ShuffleDeck(deck1);  //dealer method that shuffles the deck
            Thread.sleep(2000);
            dealer1.dealInitialHand(dealer1, deck1, player1); //dealer method that deals the initial 2 cards of blackjack to the dealer and player
            Thread.sleep(2000);

            dealer1.showHand(); //dealer method shows the dealers hand
            dealer1.checkScore();  //method shows the dealers score

            System.out.println(" ");

            player1.showHand(); //player method shows the players hand
            player1.checkScore(); //player method shows the players score
            Thread.sleep(1000);


            while (gameActive) {  //start of the game loop
                if (player1.Bust || dealer1.dealerBust || (player1.hasPassed && dealer1.hasPassed)) { //trying to end the game loop when either player busts
                    gameActive = false;
                }

                System.out.println(" ");
                Thread.sleep(1000);
                dealer1.playerTurn(player1, deck1, scanner); // player turn method. see Dealer.java class for more info. This should probably be in the player class.
                if (player1.Bust) { // trying to end the game loop if player busts after their turn
                    break;
                }
                System.out.println(" ");
                Thread.sleep(1000);
                dealer1.turn(dealer1, deck1, player1); // dealer turn method. see Dealer.java class for more info.
                if (dealer1.dealerBust) { // trying to end the game loop if dealer busts after their turn
                    break;
                }
                if (dealer1.hasPassed) {
                    break;
                }
            }
            //end of game, need to create another loop for replayability.

            if (player1.getScore() > dealer1.getScore() && !player1.Bust) {
                player1.bank += currentPot.getPotValue();
                System.out.printf(GREEN + "%s wins this hand!" + RESET, player1.getName());
                System.out.printf("%n%s wins the pot of $%d", player1.getName(), currentPot.getPotValue());
                player1.showBank();
            } else if (player1.getScore() < dealer1.getScore() || !dealer1.dealerBust) {
                dealer1.bank += currentPot.getPotValue();
                System.out.printf(GREEN + "%s wins this hand!" + RESET, dealer1.getName());
                System.out.printf("%n%s wins the pot of $%d", dealer1.getName(), currentPot.getPotValue());
                dealer1.showBank();
            } else {}

            System.out.printf("%nPlay again? (y/n)");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            } else if (playAgain.equalsIgnoreCase("n")) {
                gameActive = false;
            }
        }
        System.out.println(" ");
        Thread.sleep(3000);
        System.out.println("GAME OVER");
        System.out.println(" ");
        Thread.sleep(3000);
        scanner.close();

        //TODO  i need to figure out how to end the game loop after both players have passed.
        //      need to make a method for a tie condition.
        //      need to implement ACE 1 or 11 scoring option.
        //      need to implement hiding the dealers second card dealt.
    }
}
