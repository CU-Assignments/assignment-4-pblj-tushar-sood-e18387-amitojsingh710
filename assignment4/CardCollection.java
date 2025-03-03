import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String rank;
    private String symbol;

    public Card(String rank, String symbol) {
        this.rank = rank;
        this.symbol = symbol;
    }

    public String getRank() {
        return rank;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return rank + " of " + symbol;
    }
}

public class CardCollection {

    private static Map<String, List<Card>> cardCollection = new HashMap<>();

    public static void addCards() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        String[] symbols = {"Hearts", "Spades", "Diamonds", "Clubs"};

        for (String symbol : symbols) {
            List<Card> cards = new ArrayList<>();
            for (String rank : ranks) {
                cards.add(new Card(rank, symbol));
            }
            cardCollection.put(symbol, cards);
        }
    }

    public static void findCardsBySymbol(String symbol) {
        List<Card> cards = cardCollection.get(symbol);
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards of " + symbol + ":");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        addCards();

        while (true) {
            System.out.println("\nEnter a symbol to find all cards (e.g., Hearts, Spades, Diamonds, Clubs): ");
            String symbol = scanner.nextLine();

            if (symbol.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            findCardsBySymbol(symbol);
        }

        scanner.close();
    }
}
