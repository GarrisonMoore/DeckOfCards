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

        boolean gameActive = true; // a boolean value used to control the game loop.

            // first, we get user input for name and wager.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");

            // converting user input to a String object for easier transfer to player object name.
        String playerName = scanner.nextLine();

        Player player1 = new Player (playerName); // declaring the new player object, will ask for users name and wager, creating a new Player object.
        Dealer dealer1 = new Dealer ("Greasy Dealer"); // declaring a new dealer object.
        Pot currentPot = new Pot();                         // declaring a new pot object
        System.out.println("You will start with $10,000 to wager.");
        player1.showBank();     // this method shows the players bank balance.
        player1.playerWager();  // this method gets a value from the user for their wager amount. this value is matched by the dealer.
        player1.showBank();
        currentPot.showPotValue();  //this method shows the total pot value.
        Thread.sleep(3000);

        // below I call the deck class to create a deck object using card objects. Also displays all cards in the deck and total # of cards in the deck.
        Deck deck1 = new Deck();    // see Deck.java class for more info



        // everything below is a work in progress towards a blackjack game. if you just want to see the code for a deck of cards, see Deck.java class
        // some things in the game loop are not working correctly right now.

        System.out.println(" ");
        dealer1.ShuffleDeck(deck1);  //dealer method that shuffles the deck
        Thread.sleep(2000);
        dealer1.dealInitialHand(dealer1,deck1,player1); //dealer method that deals the initial 2 cards of blackjack to the dealer and player
        Thread.sleep(2000);

        dealer1.showHand(); //dealer method shows the dealers hand
        dealer1.checkDealersScore(dealer1);  //method shows the dealers score

        System.out.println(" ");

        player1.showHand(); //player method shows the players hand
        player1.checkPlayerScore(player1); //player method shows the players score
        Thread.sleep(1000);


        while (gameActive) {  //start of the game loop
            if (Player.p1Bust || Dealer.dealerBust) { //trying to end the game loop when either player busts
                gameActive = false;
                break;
            }

            System.out.println(" ");
            Thread.sleep(3000);
            dealer1.playerTurn(player1, deck1, scanner); // player turn method. see Dealer.java class for more info. This should probably be in the player class.
            if (Player.p1Bust){ // trying to end the game loop if player busts after their turn
                break;
            }
            System.out.println(" ");
            Thread.sleep(5000);
            dealer1.dealerTurn(dealer1, deck1, player1); // dealer turn method. see Dealer.java class for more info.
            if (Dealer.dealerBust) { // trying to end the game loop if dealer busts after their turn
                break;
            }
        }
            //end of game, need to create another loop for replayability.
        if (Player.p1Bust){
            System.out.println("You Busted!");
        }
        if (dealer1.dealerBust){
            System.out.println("Dealer Busted!");
        }
        System.out.println(" ");
        Thread.sleep(3000);
        System.out.println("GAME OVER");
        System.out.println(" ");
        Thread.sleep(3000);
        scanner.close();

        // i need to figure out how to end the game loop after both players have passed.
        // need to make a method for a tie condition.
    }
}
