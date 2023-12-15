import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part1 {
  public static void main(String[] args) {
    Path pathToFile = Paths.get("../input-test.txt");
    // Path pathToFile = Paths.get("input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String [] seeds = fileContents.substring(fileContents.indexOf(":") + 2, 
                      fileContents.indexOf("\n")).split(" ");

      fileContents = fileContents.substring(fileContents.indexOf("\n") + 2);
      BigInteger location = new BigInteger("-1");
      for (int i = 0; i < seeds.length; i++){
        boolean isLocationFound = false;
        BigInteger currLocation = new BigInteger(seeds[i]);
        String category = fileContents.substring(fileContents.indexOf("\n") + 1);
        
        while (category.length() > 0){
          int eolIndex = category.indexOf("\n");
          eolIndex = eolIndex == - 1 ? category.length() : eolIndex;
          String currLine = category.substring(0, eolIndex);

          if (eolIndex == category.length()) eolIndex -= 1;

          category = category.substring(eolIndex + 1);
          
          if (currLine.length() == 0 || currLine.indexOf(":") != -1){
            isLocationFound = currLine.indexOf(":") != -1 ? false : isLocationFound;
            continue;
          }
          
          String [] sourceToDestinationArr = currLine.trim().replaceAll(" +", " ").split(" ");
          BigInteger source = new BigInteger(sourceToDestinationArr[1]);
          BigInteger destination = new BigInteger(sourceToDestinationArr[0]);
          BigInteger range = new BigInteger(sourceToDestinationArr[2]).subtract(new BigInteger("1"));
          BigInteger maxRangeSource = source.add(range);
          
          if (!isLocationFound && currLocation.compareTo(source) >= 0 && currLocation.compareTo(maxRangeSource) <= 0){
            currLocation = (currLocation.subtract(source)).add(destination);
            isLocationFound = true;
          }
        }
        if (location.equals(new BigInteger("-1")) || location.compareTo(currLocation) > 0)
          location = currLocation;
        }
        
      System.out.println(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

