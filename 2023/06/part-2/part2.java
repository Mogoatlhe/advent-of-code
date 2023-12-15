import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class part2 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String times = fileContents.substring(fileContents.indexOf(":") + 1, fileContents.indexOf("\n")).replaceAll(" ", "");
      String distance = fileContents.substring(fileContents.lastIndexOf(":") + 1).replaceAll(" ", "");

      
      BigDecimal product = new BigDecimal("1");
      BigDecimal b = new BigDecimal(times);
      BigDecimal c = new BigDecimal(distance);

      BigDecimal bSquared = b.pow(2);
      BigDecimal multiplyCby4 = (new BigDecimal("4")).multiply(c);
      BigDecimal discriminant = bSquared.subtract(multiplyCby4);
      BigDecimal discriminantSqrt = discriminant.sqrt(new MathContext(12));

      BigDecimal lowerBound = (b.subtract(discriminantSqrt).divide(new BigDecimal("2"))).setScale(0, RoundingMode.CEILING);
      BigDecimal upperBound = (b.add(discriminantSqrt).divide(new BigDecimal("2"))).setScale(0, RoundingMode.CEILING);

      if (upperBound.multiply(lowerBound).compareTo(c) != 0)
        product = product.multiply((upperBound.subtract(lowerBound)));
      else{
        product = product.multiply((upperBound.subtract(lowerBound)).subtract(new BigDecimal("1")));
      }

      System.out.println(product);
     
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

