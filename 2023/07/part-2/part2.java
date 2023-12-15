import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class part2 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("../input.txt");

    try {
      int newLinesCount = 0;
      String fileContents = Files.readString(pathToFile);
      String [] handAndBids = fileContents.replaceAll(" +", " ").split("\n");
      String [] hands = new String[handAndBids.length];
      int [] bids = new int[handAndBids.length];
      String [] handTypes = new String[handAndBids.length];
      
      while (newLinesCount < handAndBids.length){
        String handAndBid = handAndBids[newLinesCount];
        hands[newLinesCount] = handAndBid.substring(0, handAndBid.indexOf(" "));
        hands[newLinesCount] = hands[newLinesCount].replaceAll("J", "1").replaceAll("Q", "W").replaceAll("K", "X").replaceAll("A", "Y");
        handTypes[newLinesCount] = getHandType(hands[newLinesCount], newLinesCount);
        bids[newLinesCount++] = Integer.parseInt(handAndBid.substring(handAndBid.indexOf(" ") + 1));
      }
      Arrays.sort(handTypes, Collections.reverseOrder());
      
      String [] sortedHands = new String [newLinesCount];
      int [] sortedBids = new int [newLinesCount];
      for (int i = 0; i < newLinesCount; i++){
        int index = Integer.parseInt(handTypes[i].substring(handTypes[i].lastIndexOf(" ") + 1));
        sortedBids[i] = bids[index];
        sortedHands[i] = hands[index];
      }
      
      for (int i = 0; i < newLinesCount - 1; i++){
        if (handTypes[i].charAt(0) != handTypes[i + 1].charAt(0)){
          continue;
        }
        
        if (sortedHands[i].compareTo(sortedHands[i + 1]) < 0){
          int bidsTemp = sortedBids[i];
          String temp = sortedHands[i];

          sortedBids[i] = sortedBids[i + 1];
          sortedHands[i] = sortedHands[i + 1];
          
          sortedHands[i + 1] = temp;
          sortedBids[i + 1] = bidsTemp;

          i -= 2;
          i = i >= 0 ? i : -1;
        }
      }

      int products = 0;
      
      for (int i = newLinesCount - 1; i >=0; i--){
        products += sortedBids[i] * (newLinesCount - i);
      }
      System.out.println(products);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static String getHandType(String hand, int i){
    Set<String> uniqueCards = new HashSet<String>(Arrays.asList(hand.split("")));
    long jCount = hand.chars().filter(ch -> ch == '1').count();

    int setSize = uniqueCards.size();
    if (setSize == 5){ // ABCDE
      if (jCount == 1)
        return "2 - one " + i;
      return "1 - high " + i;
    } else if (setSize == 1){ // AAAAA
      return "7 - five " + i;
    } else if (setSize == 4){ // AABCD
      if (jCount > 0)
        return "4 - three " + i;
      return "2 - one " + i;
    } else if (setSize == 3){ // AAABC || AABBC
      long max = getCharOccurence(uniqueCards, hand);
      if (max == 3){
        if (jCount > 0)
          return "6 - four " + i;
        return "4 - three " + i;
      }
        
      if (jCount == 1)
        return  "5 - full " + i;
      if (jCount == 2)
        return "6 - four " + i;

      return "3 - two " + i;
    } else { // AAAAB || AAABB
      if (jCount > 0)
        return "7 - five " + i;
      long max = getCharOccurence(uniqueCards, hand);
      return max == 4 ? "6 - four " + i : "5 - full " + i;
    }
  }

  static long getCharOccurence(Set<String> uniqueCards, String hand){
    long max = 0;
    for (String currChar: uniqueCards){
      long count = hand.chars().filter(ch -> ch == currChar.charAt(0)).count();
      max = max < count ? count : max;
    }

    return max;
  }
}

