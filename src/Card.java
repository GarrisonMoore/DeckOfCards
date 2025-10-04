/** Card object class that holds the card's rank and suit.
 *
 */

public class Card {

    private final String suit;
    private final String rank;
    private int cardValue = 0; //resetting the card value to 0 is necessary to prevent the card values from overlapping.

    public Card( String rank, String suit, int cardValue){
        this.rank = rank;
        this.suit = suit;
        this.cardValue = cardValue;
    }

    /*public String getRank(){              methods have been superseded by the constructor.
        return rank;
    }

    public String getSuit(){
        return suit;
    }*/

    public String getRank(){
        return rank;

    }

    public int getCardValue(){
        return cardValue;
    }
    public String toString(){
        return rank + " of " + suit;
    }
}