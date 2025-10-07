/** Deck object that will store all card objects in a deck.
 */

import java.util.*;

public class Deck {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String YELLOW = "\u001b[33m";
    public static final String MAGENTA = "\u001b[35m";
    public static final String CYAN = "\u001b[36m";
    public static final String BLACK = "\u001B[30m";
    public static final String GREY = "\u001B[90m";
    public static final String WHITEbkgnd = "\u001B[47m";
    public static final String YELLOWbkgnd = "\u001B[43m";

    public static final String BOLD = "\u001B[1m\n";

    private int deckCount;

    protected ArrayList<Card> deckOfCards = new ArrayList<>(); // this will store all card objects, making up a deck
    private static final ArrayList<String> rankKeys = new ArrayList<String>(); // this list will store all the rank keys as Strings.
    private static final ArrayList<String> suits = new ArrayList<String>(); // this list will store all the suits.

    private static final LinkedHashMap<String, Integer> ranks = new LinkedHashMap<String, Integer>();
        // putting all ranks in a linked hashmap so that a ranks String key can be used to retrieve int values for scoring.

        static { /* Maybe these should be in the card class logically, but I think keeping ranks and suits in a deck would allow
                    for creating a different type of deck using the same Card class
                    */
            //rank list
            ranks.put(CYAN + "ACE" + RESET, 11);
            ranks.put(CYAN + "TWO" + RESET, 2);
            ranks.put(CYAN + "THREE" + RESET, 3);
            ranks.put(CYAN + "FOUR" + RESET, 4);
            ranks.put(CYAN + "FIVE" + RESET, 5);
            ranks.put(CYAN + "SIX" + RESET, 6);
            ranks.put(CYAN + "SEVEN" + RESET, 7);
            ranks.put(CYAN + "EIGHT" + RESET, 8);
            ranks.put(CYAN + "NINE" + RESET, 9);
            ranks.put(CYAN + "TEN" + RESET, 10);
            ranks.put(CYAN + "JACK" + RESET, 10);
            ranks.put(CYAN + "QUEEN" + RESET, 10);
            ranks.put(CYAN + "KING" + RESET, 10);
            rankKeys.addAll(ranks.keySet());  // Adding all rank keys to the rankKeys list.
            //suit list
            suits.add(RED + "HEARTS" + " ♥" + RESET);
            suits.add(RED + "DIAMONDS" + " ♢" + RESET);
            suits.add(GREY + "CLUBS" + " ♧" + RESET);
            suits.add(GREY + "SPADES" + " ♠" + RESET);
        }

    public Deck() {
        this(1);
    }

    public Deck(int deckCount) {
            this.deckCount = Math.max(1, deckCount);
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

    public void generateDeck(){// this method will generate a deck object
        deckOfCards.clear();
        for (int i = 0; i<deckCount;i++) {
            for (String suit : suits) {   // loop sorts through all suits and ranks and creates a new card object for each
                for (String rank : rankKeys) {
                    deckOfCards.add(new Card(rank, suit, ranks.get(rank))); // adds the card object to the deck
                }
            }
        }
            //temporary display of deck for testing.
        //System.out.printf("%nDisplaying all sorted card objects in our deck: %s",deckOfCards); //  < displays all cards in the deck
        //System.out.printf("%nTotal Cards in the deck: %d",deckOfCards.size());                 //  < displays the total number of cards in the deck
    }

    public void clearDeck(){ // Not in use currently
        deckOfCards.clear();
    }
}
