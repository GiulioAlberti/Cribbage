import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardParser {
    static Map<String, String> rankConverter = Stream.of(new String[][]{
            {"A", "Ace"}, {"2", "2"}, {"3", "3"}, {"4", "4"},
            {"5", "5"}, {"6", "6"}, {"7", "7"}, {"8", "8"},
            {"9", "9"}, {"0", "10"}, {"J", "Jack"},
            {"Q", "Queen"}, {"K", "King"},
    }).collect(Collectors.toMap(p -> p[0], p -> p[1]));

    public static Card parseCard(String cardAsText) {
        String rank=rankConverter.get(cardAsText.substring(0,1));
        Suite suite=suiteCorrespondingTo(cardAsText.charAt(1));
        return new Card(rank, suite);
    }
    public static Suite suiteCorrespondingTo(char symbol){
        return switch (symbol) {
            case 'C' -> Suite.CLUBS;
            case 'D' -> Suite.DIAMONDS;
            case 'H' -> Suite.HEARTS;
            case 'S' -> Suite.SPADES;
            default -> throw new IllegalStateException("Unexpected symbol for Suite: " + symbol);
        };
    }
    public static ArrayList<Card> parseHand(String handAsText){
        ArrayList<Card> hand= new ArrayList<>();
        for(int i=0; i<5; i++){
            Card nextCard=CardParser.parseCard(handAsText.substring(2*i, 2*(i+1)));
            hand.add(nextCard);
        }
        return hand;
    }

}