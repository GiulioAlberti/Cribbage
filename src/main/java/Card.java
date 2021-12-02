public class Card {
    private final String rank;
    private final Suite suite;
    private final int value;
    public Card(String rank, Suite suite){
        this.rank =rank;
        this.suite=suite;
        this.value=determineValue(rank);
    }
    public String getRank() {
        return rank;
    }

    public Suite getSuite() {
        return suite;
    }
    public int getValue(){
        return value;
    }
    public static int determineValue(String rank){
        return switch(rank){
            case "Jack", "Queen", "King" -> 10;
            case "Ace"-> 1;
            default -> Integer.parseInt(rank);
        };
    }
    public boolean sameSuite(Card anotherCard){
        return this.suite==anotherCard.getSuite();
    }
}