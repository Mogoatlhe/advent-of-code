import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part1 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String timesAsString = fileContents.substring(fileContents.indexOf(":") + 1, fileContents.indexOf("\n") + 1).replaceAll(" +", " ");
      String distanceAsString = fileContents.substring(fileContents.lastIndexOf(":") + 1).replaceAll(" +", " ");
      String [] times = timesAsString.trim().split(" ");
      String [] distance = distanceAsString.trim().split(" ");

      double product = 1;
      for (int i = 0; i < times.length; i++){
        int b = Integer.parseInt(times[i]);
        int c = Integer.parseInt(distance[i]);
        double lowerBound = Math.ceil((b - Math.sqrt((b * b) - 4 * c)) / 2);
        double upperBound = Math.ceil((b + Math.sqrt((b * b) - 4 * c)) / 2);

        if (upperBound * lowerBound != c)
          product *= (upperBound - lowerBound);
        else{
          product *= (upperBound - lowerBound) - 1;
        }
      }
      System.out.println(product);
     
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

