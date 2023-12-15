import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part2 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String [] seeds = fileContents.substring(fileContents.indexOf(":") + 2, 
                      fileContents.indexOf("\n")).split(" ");

      fileContents = fileContents.substring(fileContents.indexOf("\n") + 2);
      BigInteger location = new BigInteger("-1");
      for (int i = 0; i < seeds.length ; i += 2){
        String category = fileContents.substring(fileContents.indexOf("\n") + 1);
        BigInteger low = new BigInteger(seeds[i]);
        BigInteger high = ((new BigInteger(seeds[i])).add(new BigInteger(seeds[i + 1])).subtract(new BigInteger("1")));
        BigInteger tempLocation = getLowestSeedValue(low, high, category);System.out.println(tempLocation);
        location = location.compareTo(new BigInteger("-1")) == 0 || tempLocation.compareTo(location) < 0 ? tempLocation : location;
      }
        
      System.out.println(location);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static BigInteger getLowestSeedValue(BigInteger low, BigInteger high, String ranges){
    String currLine = "";
    
    if (ranges.contains("\n")){
      currLine = ranges.substring(0, ranges.indexOf("\n"));
      if (currLine.contains(":") || currLine.length() == 0){
        ranges = ranges.substring(ranges.indexOf(":") + 2);
        currLine = ranges.substring(0, ranges.indexOf("\n"));
      }
    }else{
      currLine = ranges;
    }

    String [] sourceToDestinationArr = currLine.trim().replaceAll(" +", " ").split(" ");
    BigInteger source = new BigInteger(sourceToDestinationArr[1]);
    BigInteger destination = new BigInteger(sourceToDestinationArr[0]);
    BigInteger range = new BigInteger(sourceToDestinationArr[2]).subtract(new BigInteger("1"));
    BigInteger maxRangeSource = source.add(range);
    BigInteger lowest = new BigInteger("-1");

    if (low.compareTo(source) >= 0 && high.compareTo(maxRangeSource) <= 0){ // 2, 11, 14, 16
      low = low.subtract(source).add(destination);
      high = high.subtract(source).add(destination);
      if (ranges.contains(":"))
        lowest = getLowestSeedValue(low, high, ranges.substring(ranges.indexOf(":") + 2));
    } else if (low.compareTo(source) >= 0 && low.compareTo(maxRangeSource) <= 0 && high.compareTo(maxRangeSource) > 0){
      BigInteger betweenRange = getLowestSeedValue(low, maxRangeSource, (ranges));
      BigInteger overRange = getLowestSeedValue(maxRangeSource.add(new BigInteger("1")), high, ranges);
      lowest = betweenRange.compareTo(overRange) > 0 ? overRange : betweenRange;
    } else if (low.compareTo(source) < 0 && high.compareTo(source) >= 0){
      BigInteger underRange = getLowestSeedValue(low, source.subtract(new BigInteger("1")), ranges);
      BigInteger overSource;

      if (high.compareTo(maxRangeSource) <= 0){
        overSource = getLowestSeedValue(source, high, (ranges));
      } else {
        overSource = getLowestSeedValue(source, maxRangeSource, (ranges));
        BigInteger overRange = getLowestSeedValue(maxRangeSource.add(new BigInteger("1")), high, (ranges));
        overSource = overRange.compareTo(overSource) > 0 ? overSource : overRange;
      }

      lowest = underRange.compareTo(overSource) > 0 ? overSource : underRange;
    } else {
      if (ranges.contains("\n")){
        lowest = getLowestSeedValue(low, high, getStringNextLine(ranges));
      }
    }
    

    if ((lowest.compareTo(new BigInteger("-1")) == 0 || low.compareTo(lowest) < 0) && !ranges.contains(":")){
      lowest = low;
    }
    
    return lowest;
  }

  static String getStringNextLine(String str){
    return str.substring(str.indexOf("\n") + 1);
  }
}

