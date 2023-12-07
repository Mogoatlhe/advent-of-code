
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Arrays;
import java.util.Scanner;

public class part1 {
  public static void main(String[] args) {
    try {
      // File file = new File("../input-test.txt");
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);
      int sum = 0;
      
      while(reader.hasNextLine()){
        int doubleValue = 1;
        int currentCardPoints = 0;
        String line = reader.nextLine();
        int winningNumbersStartingIndex = line.indexOf(":") + 2;
        int winningNumbersEndingIndex = line.indexOf("|") - 1;
        int myNumbersStartingIndex = line.indexOf("|") + 2;
        
        String winningNumbers = line.substring(winningNumbersStartingIndex, winningNumbersEndingIndex).trim().replaceAll(" +", " ");
        String myNumbers = line.substring(myNumbersStartingIndex).trim().replaceAll(" +", " ");
        List<String> winningNumbersArr = Arrays.asList(winningNumbers.split(" "));
        List<String> myNumbersArr = Arrays.asList(myNumbers.split(" "));

        for (int i = 0; i < winningNumbersArr.size(); i++){
          if (myNumbersArr.contains(winningNumbersArr.get(i))){
            currentCardPoints = doubleValue;
            doubleValue *= 2;
          }
        }
        sum += currentCardPoints;
      }

      System.out.println(sum);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}

