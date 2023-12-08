
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class part2 {
  public static void main(String[] args) {
    try {
      // File file = new File("../input-test.txt");
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);
      int sum = 1;
      ArrayList<String> winningNumbersArr = new ArrayList<String>();
      ArrayList<String> myNumbersArr = new ArrayList<String>();
      
      while(reader.hasNextLine()){
        String line = reader.nextLine();
        int winningNumbersStartingIndex = line.indexOf(":") + 2;
        int winningNumbersEndingIndex = line.indexOf("|") - 1;
        int myNumbersStartingIndex = line.indexOf("|") + 2;
        
        String winningNumbers = line.substring(winningNumbersStartingIndex, winningNumbersEndingIndex).trim().replaceAll(" +", " ");
        String myNumbers = line.substring(myNumbersStartingIndex).trim().replaceAll(" +", " ");
        
        winningNumbersArr.add(winningNumbers);
        myNumbersArr.add(myNumbers);
      }

      int [] memo = new int [winningNumbersArr.size()];

      for (int i = 0; i < winningNumbersArr.size(); i++)
        sum += getTotalScratchCards(i, memo, winningNumbersArr, myNumbersArr);

      System.out.println(sum);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static int getWinsCount(List<String> winningNumbersArr, List<String> myNumbersArr){
    int winsCount = 0;
    for (int i = 0; i < winningNumbersArr.size(); i++){
      if (myNumbersArr.contains(winningNumbersArr.get(i))){
        winsCount += 1;
      }
    }

    return winsCount;
  }

  static int getTotalScratchCards(int currCard, int [] memo,
    ArrayList<String> winningNumbersArr, ArrayList<String> myNumbersArr){
    
    int currCardIndex = currCard - 1;
    
    if (currCardIndex < 0 || currCardIndex >= memo.length)
      return 0;

    if (memo[currCardIndex] != 0 ){
      return memo[currCardIndex];
    }

    List<String> currCardWinningNums = Arrays.asList(winningNumbersArr.get(currCardIndex).split(" "));
    List<String> currCardmyNums = Arrays.asList(myNumbersArr.get(currCardIndex).split(" "));
    int winsCount = getWinsCount(currCardWinningNums, currCardmyNums);
    int scratchCardsCount = 1;
    
    for (int i = 1; i <= winsCount; i++){
      scratchCardsCount += getTotalScratchCards(currCard + i, memo, winningNumbersArr, myNumbersArr);
    }

    memo[currCardIndex] = scratchCardsCount;
    return scratchCardsCount;

  }
}

