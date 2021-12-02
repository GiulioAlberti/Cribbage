
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParseHandTest {

    @ParameterizedTest
    @CsvSource({"5H, 5, HEARTS, 5", "JD, Jack, DIAMONDS, 10", "0S, 10, SPADES, 10",
            "KC, King, CLUBS, 10", "AS, Ace, SPADES, 1"})
    void parseOneCardPara(String cardAsText, String rank, Suite suite, int value ) {
        Card card = CardParser.parseCard(cardAsText);
        assertAll(
                () -> assertEquals(rank, card.getRank()),
                () -> assertEquals(suite, card.getSuite()),
                () -> assertEquals(value, card.getValue())
        );
    }
    @Test
    void parseFullHand(){
        ArrayList<Card> hand1=CardParser.parseHand("5H5D5SJC5C");
        StringBuilder suites=new StringBuilder();
        StringBuilder ranks = new StringBuilder();
        for(Card card: hand1){
            ranks.append(card.getRank());
            suites.append(card.getSuite());
        }
        assertAll(
                () -> assertEquals("555Jack5", ranks.toString()),
                () -> assertEquals("HEARTSDIAMONDSSPADESCLUBSCLUBS", suites.toString())
        );
    }
    @Test
    void pointsOfHand6(){
        ArrayList<Card> hand2=CardParser.parseHand("5D4D3DJD5C");
        assertEquals(6, Points.totalPoints(hand2));
    }
    @Test
    void pointsOfHand8(){
        ArrayList<Card> hand2=CardParser.parseHand("5D4D3DJD5D");
        assertEquals(8, Points.totalPoints(hand2));
    }
    @Test
    void pointsOfHand7(){
        ArrayList<Card> hand2=CardParser.parseHand("5C5H3DJD5D");
        assertEquals(7, Points.totalPoints(hand2));
    }
}