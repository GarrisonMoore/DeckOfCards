/** Main Blackjack Game class. *WORK IN PROGRESS*
 * By: Garrison Moore
 */


import java.util.Scanner;

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

    public static boolean loanActive = false;
    public static boolean replay = true;
    public static double dealerSwapThreshold = 2000;
    public static void main(String[] args) throws InterruptedException {

        boolean gameActive = true;
        // boolean values used to control the game loops

        System.out.println("Welcome to Blackjack!");
        System.out.println(" ");
            // first, we get user input for name and wager.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");

            // converting user input to a String object for easier transfer to player object name.
        String playerName = scanner.nextLine();

        Players player1 = new Player1(GREEN+playerName+RESET); // declaring the new player1 object, will ask for users name and wager, creating a new Player object.
        Dealers currentDealer = new LooseDealer (YELLOW + "Loose Dealer" + RESET);
        Pot currentPot = new Pot();// declaring a new pot object
        LoanShark loanshark = new LoanShark("loan shark");

        while (replay) {
            gameActive = true;
            player1.clearPlayer();
            currentDealer.clearDealer();
            currentPot.clearPot();
            Deck deck1 = new Deck();
            System.out.println(" ");

        player1.showBank();     // this method shows the players wallet balance.
        player1.playerWager();// this method gets a value from the user for their wager amount. this value is matched by the dealer.
        player1.showBank();
        currentPot.displayPotValue();  //this method shows the total pot value.
        Thread.sleep(3000);

            System.out.println(" ");
            currentDealer.ShuffleDeck(deck1);  //dealer method that shuffles the deck
            Thread.sleep(2000);
            currentDealer.dealInitialHand(currentDealer, deck1, player1); //dealer method that deals the initial 2 cards of blackjack to the dealer and player
            Thread.sleep(2000);

            currentDealer.showHand(); //dealer method shows the dealers hand
            currentDealer.checkScore();  //method shows the dealers score

            System.out.println(" ");

            player1.showHand(); //player method shows the players hand
            player1.checkScore(); //player method shows the players score
            Thread.sleep(1000);

            player1.checkForBlackjack();
            currentDealer.checkForBlackjack();

            while (gameActive) {//start of the game loop
                if (player1.blackjack || currentDealer.blackjack) {
                    break;
                }
                if (player1.Bust || currentDealer.dealerBust || (player1.hasPassed && currentDealer.hasPassed)) { //trying to end the game loop when either player busts
                    gameActive = false;
                }

                System.out.println(" ");
                Thread.sleep(1000);
                player1.turn(currentDealer, deck1, player1); // player turn method. see Dealer.java class for more info.
                if (player1.Bust) { // trying to end the game loop if player busts after their turn
                    break;
                }
                System.out.println(" ");
                Thread.sleep(1000);
                currentDealer.turn(currentDealer, deck1, player1); // dealer turn method. see Dealer.java class for more info.
                if (currentDealer.dealerBust) { // trying to end the game loop if dealer busts after their turn
                    break;
                }
                if (currentDealer.hasPassed) {
                    break;
                }
            }

                // end of game, winning/ losing conditions and payout below
            if (currentDealer.dealerBust) {
                if (player1.blackjack){
                    player1.wallet += currentPot.get3to2payout();
                    System.out.printf(GREEN + "%n%s wins this hand!" + RESET, player1.getName());
                    System.out.printf("%n%s wins: $%d", player1.getName(), currentPot.get3to2payout());
                }else {
                    player1.wallet += currentPot.get1to1Payout();
                    System.out.printf(GREEN + "%n%s wins this hand!" + RESET, player1.getName());
                    System.out.printf("%n%s wins: $%d", player1.getName(), currentPot.get1to1Payout());
                }
                player1.showBank();
                Thread.sleep(1000);
            }
            if (player1.Bust) {
                System.out.printf(GREEN + "%n%s wins this hand!" + RESET, currentDealer.getName());
                Thread.sleep(1000);
                currentPot.clearPot();
            }
            if (player1.hasPassed && currentDealer.hasPassed) {
                if (player1.getScore() > currentDealer.getScore() && !player1.Bust){
                    if (player1.blackjack){
                        player1.wallet += currentPot.get3to2payout();
                        System.out.printf(GREEN + "%n%s wins this hand!" + RESET, player1.getName());
                        System.out.printf("%n%s wins: $%d", player1.getName(), currentPot.get3to2payout());
                    }else {
                        player1.wallet += currentPot.get1to1Payout();
                        System.out.printf(GREEN + "%n%s wins this hand!" + RESET, player1.getName());
                        System.out.printf("%n%s wins: $%d", player1.getName(), currentPot.get1to1Payout());
                    }
                    player1.showBank();
                    Thread.sleep(1000);
                } else if (currentDealer.getScore() > player1.getScore() && !currentDealer.dealerBust) {
                    currentPot.clearPot();
                    System.out.printf(MAGENTA + "%n%s wins this hand!" + RESET, currentDealer.getName());
                    Thread.sleep(1000);
                }
            }
            if (player1.getScore() == currentDealer.getScore()) {     // tie condition
                System.out.printf(YELLOW + "%nIt's a draw!" + RESET);
                player1.wallet += currentPot.returnPlayerWager();
                Thread.sleep(1000);
            }

            System.out.printf("%nPlay another hand? (y/n)"); // replay
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("y")) {
                player1.blackjack = false;
                currentDealer.blackjack = false;
                break;
            } else if (playAgain.equalsIgnoreCase("n")) {
                replay = false;
            }

            if (player1.wallet >= dealerSwapThreshold ) {  // swapping dealers if they lose too much money.
                System.out.println("The house has removed the " + currentDealer.getName() + " from the game for losing too much money.");
                if (currentDealer instanceof ShadyDealer){
                    currentDealer = new LooseDealer(CYAN + "Loose Dealer" + RESET);
                }else{
                    currentDealer = new ShadyDealer(MAGENTA + "Shady Dealer" + RESET);
                }
            }

            if (loanActive && player1.wallet >= loanshark.accountBalance*2){  // if player has a loan and has more than double the loan amount in their wallet, they are offered a chance to pay back their loan
                loanshark.collectLoan(player1);
            }

            if (player1.wallet <= 0) { // if player loses all money, a loan shark offers a loan with interest
                System.out.println("\nYou have lost all your money!");
                if (!loanActive){
                    loanshark.loanToPlayer(player1);
                }else{
                    System.out.println("You already have a loan!");
                    System.out.printf("%s collects all of your valuables. The casino has consumed you.", currentDealer.getName());
                }
                System.out.println(" ");
            }


        }
        System.out.println(" ");
        Thread.sleep(3000);
        System.out.println("GAME OVER");
        System.out.println(" ");
        Thread.sleep(3000);
        scanner.close();

        //TODO
        //      need to display in the console that the dealer is matching the players wager
        //      need to remove dealer wallet, and matching wager. Replace with dealer collection, payout(1x), and blackjack payout(1.5x)
        //      need to implement multiple decks. blackjack usually uses up to 8 decks.
        //      need to implement a split hand method
        //      need to implement a double down method
        //      need to implement a hole card or non hole card game
        //      need to implement dealer rotation when dealer loses all money
        //      need to define and implement player variant specific traits
        //      need to define and implement greasyDealer variant specific traits
        //      need to implement a loan shark/wallet object that can loan money with interest
        //      need to offer player a loan from loan shark if player has no more money
        //
    }
}
