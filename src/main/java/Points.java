import java.util.ArrayList;
import java.util.HashMap;

public class Points {
    public static int totalPoints(ArrayList<Card> hand){
        return flush(hand)+nob(hand)+pairs(hand)+fifteen_twos(hand)+runs(hand);
    }
    public static int fifteen_twos(ArrayList<Card> hand){
        return 0;
    }
    public static int runs(ArrayList<Card> hand){
        return 0;
    }
    public static int pairs(ArrayList<Card> hand){
        HashMap<String, Integer> frequencyOfRanks = new HashMap<>();
        ArrayList<String> listOfRanks = new ArrayList<>();
        for(Card card: hand){
            listOfRanks.add(card.getRank());
        }
        for(String rank: listOfRanks){
            if(frequencyOfRanks.containsKey(rank)){
                frequencyOfRanks.put(rank, frequencyOfRanks.get(rank)+1);
            }else{
                frequencyOfRanks.put(rank, 1);
            }
        }
        int partialPoints=0;
        for(var entry: frequencyOfRanks.entrySet()){
            if(entry.getValue()>1){
                switch (entry.getValue()) {
                    case 2 -> partialPoints += 2;
                    case 3 -> partialPoints += 6;
                    case 4 -> partialPoints += 12;
                }
            }
        }
        return partialPoints;
    }
    public static int flush(ArrayList<Card> hand){
        int partialPoints=0;
        for(Card card: hand){
            if(hand.get(0).getSuite()!=card.getSuite()){
                return (partialPoints==4) ? 4 : 0 ;
            }else{
                partialPoints+=1;
            }
        }
        return partialPoints;
    }
    public static int nob(ArrayList<Card> hand){
        for(int i=0; i<4; i++){
            if(hand.get(i).getRank().equals("Jack") && hand.get(i).sameSuite(hand.get(4))){
                return 1;
            }
        }
        return 0;
    }
}

