import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private ArrayList<Card> playerHand;
    private String name;
    private int score;
    private int bank;
    private int wager;
    static boolean p1Bust = false;

    public Player(String name) {  //player constructor
        this.bank = 10000; //starting the players bank at 10,000. Value will be subtracted using the playerWager method.
        this.name = name;
        this.wager = wager;
        this.playerHand = new ArrayList<>(); //making a new empty arraylist for the players hand.
        this.score = 0;    //Starting score at 0.
    }

    public void addCardToHand(Card card){  //method adds a card object to the players hand, for use in playerTurn method.
        playerHand.add(card);
    }

    public void showHand(){  //method that displays the players hand
        System.out.printf("%n%s's hand: %s",this.name,playerHand);
    }

    public String getName(){  // method to get the players name.
        return this.name;
    }

    public void checkPlayerScore(Player player){ //method calculates the players score and displays it.
        player.score = 0;
        for (Card card : playerHand) {
            player.score += card.getCardValue();
        }
        System.out.printf("%n%s's score: %d",name,player.score);

        if (player.score > 21){     //also check if the player has bust
            System.out.printf("%n%s busts!",name);
            p1Bust = true;
        }
    }

    public void showBank(){     //method that displays the player objects current bank value.
        System.out.printf("%n%s's bank value: $%d%n",name,this.bank);
    }

    //method that accepts user input for players wager, gives it to the pot, and subtracts it from the players bank.
    public void playerWager(){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nEnter your wager: ");
        try {
            this.wager = scanner.nextInt();
            this.bank -= this.wager;
            Pot.playerWager = this.wager;
            Pot.dealerWager = this.wager;
        }catch (Exception e){
            System.out.println("Invalid input. Please enter a valid number.");
        }
        scanner.nextLine();
    }


}
