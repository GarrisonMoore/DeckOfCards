/** Card object class that holds the card's rank and suit.
 *
 */

public class Card {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";
    public static final String WHITEbkgnd = "\u001B[47m";
    public static final String YELLOWbkgnd = "\u001B[43m";
    public static final String BOLD = "\u001B[1m\n";

    private final String suit;
    private final String rank;
    private int cardValue = 0; //resetting the card value to 0 is necessary to prevent the card values from overlapping.

    public Card( String rank, String suit, int cardValue){
        this.rank = rank;
        this.suit = suit;
        this.cardValue = cardValue;
    }

    /*public String getRank(){             These methods have been superseded by the constructor.
        return rank;
    }

    public String getSuit(){
        return suit;
    }*/

    public String getRank(){ // Getter method for rank
        return rank;
    }

    public int getCardValue(){
        return cardValue;
    }

    public String toString() { // toString method used to display a concatenated string to show a whole card.
        return rank+ " of "+ suit;
    }
}