import java.util.ArrayList;
import java.util.Scanner;

public class Player {

    private ArrayList<Card> playerHand;
    private String name;
    private int score;
    private int bank;
    private int wager;
    static boolean p1Bust = false;

    public Player(String name) {
        this.bank = 10000; //starting the players bank at 10,000. Value will be subtracted using the playerWager method.
        this.name = name;
        this.wager = wager;
        this.playerHand = new ArrayList<>(); //making a new empty arraylist for the players hand.
        this.score = 0;    //Starting score at 0.
    }

    public void addCardToHand(Card card){
        playerHand.add(card);
    }

    public void showHand(){
        System.out.printf("%n%s's hand: %s",this.name,playerHand);
    }

    public String getName(){
        return this.name;
    }

    public void checkPlayerScore(Player player){
        player.score = 0;
        for (Card card : playerHand) {
            player.score += card.getCardValue();
        }
        System.out.printf("%n%s's score: %d",name,player.score);

        if (player.score > 21){
            System.out.printf("%n%s busts!",name);
            p1Bust = true;
        }
    }

    public void showBank(){
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
