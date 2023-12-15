import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part1 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("../input.txt");

    try {
      int sum = 0;
      String fileContents = Files.readString(pathToFile);
      long historyCount = fileContents.chars().filter(ch -> ch == '\n').count() + 1;
      
      for (int i = 0; i < historyCount; i++){
        String history;
        if (fileContents.contains("\n")){
          history = fileContents.substring(0, fileContents.indexOf("\n"));
          fileContents = fileContents.substring(fileContents.indexOf("\n") + 1);
        } else {
          history = fileContents;
        }

        sum += getPredictions(history);
      }

      System.out.println(sum);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static int getPredictions(String history){
    String[] historyArr = history.split(" ");

    String sequence = "";
    int zeroCount = 0;
    int difference = 0;
    int next = 0;
    for (int i = 0; i < historyArr.length - 1; i++){
      int curr = Integer.parseInt(historyArr[i]);
      next = Integer.parseInt(historyArr[i + 1]);
      difference = next - curr;

      if (difference == 0){
        zeroCount += 1;
      }

      sequence += difference + " ";
    }

    int last = Integer.parseInt(historyArr[historyArr.length - 1]);
    if (zeroCount == historyArr.length - 1){
      return difference + last;
    }

    sequence.trim();
    return getPredictions(sequence) + last;
  }
}

