
import java.util.*;

public class Deck {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String BLACK = "\u001B[30m";
    public static final String BLUE = "\u001B[34m";

    public ArrayList<Card> deckOfCards = new ArrayList<>(); // this will store all card objects, making up a deck
    public static final ArrayList<String> rankKeys = new ArrayList<String>(); // this list will store all the rank keys as Strings.
    public static final ArrayList<String> suits = new ArrayList<String>(); // this list will store all the suits, only needs to be String values.

    public static LinkedHashMap<String, Integer> ranks = new LinkedHashMap<String, Integer>();
        static {
            //rank list
            ranks.put(BLUE + "ACE" + RESET, 11);
            ranks.put(BLUE + "TWO" + RESET, 2);
            ranks.put(BLUE + "THREE" + RESET, 3);
            ranks.put(BLUE + "FOUR" + RESET, 4);  // putting all ranks in a linked hashmap so that a ranks String key can be used to retrieve values for scoring.
            ranks.put(BLUE + "FIVE" + RESET, 5);
            ranks.put(BLUE + "SIX" + RESET, 6);
            ranks.put(BLUE + "SEVEN" + RESET, 7);
            ranks.put(BLUE + "EIGHT" + RESET, 8);
            ranks.put(BLUE + "NINE" + RESET, 9);
            ranks.put(BLUE + "TEN" + RESET, 10);
            ranks.put(BLUE + "JACK" + RESET, 10);
            ranks.put(BLUE + "QUEEN" + RESET, 10);
            ranks.put(BLUE + "KING" + RESET, 10);
            rankKeys.addAll(ranks.keySet());
            //suit list
            suits.add(RED + "HEARTS" + " ♥" + RESET);
            suits.add(RED + "DIAMONDS" + " ♢" + RESET);
            suits.add(BLACK + "CLUBS" + " ♧" + RESET);
            suits.add(BLACK + "SPADES" + " ♠" + RESET);
        }
    public Deck() {
            // creating a new deck Object will also call generateDeck() to generate a new deck Object
        this.generateDeck();
    }

    /*public void getSuit(){                // this method has been superseded by the generateDeck method.
        Random random = new Random();
        int randomIndex = random.nextInt(suits.size());
        System.out.println(suits.get(randomIndex));
    }

    public void getRank(){                  // this method has been superseded by the generateDeck method.
        Random random = new Random();
        int randomIndex = random.nextInt(rankKeys.size());
        System.out.printf("%s of ",rankKeys.get(randomIndex));
    }*/

    public void generateDeck(){// this method will generate a deck deck object
        deckOfCards.clear();
        for(String suit : suits){   // loop sorts through all suits and ranks and creates a new card object for each
            for (String rank : rankKeys) {
                deckOfCards.add(new Card(rank,suit,ranks.get(rank))); // adds the card object to the deck
            }
        }
            //temporary display of deck for testing.
        System.out.printf("%nDisplaying all sorted card objects in our deck: %s",deckOfCards); //  < displays all cards in the deck
        System.out.printf("%nTotal Cards in the deck: %d",deckOfCards.size());                 //  < displays the total number of cards in the deck
    }

    public void clearDeck(){
        deckOfCards.clear();
    }
}
