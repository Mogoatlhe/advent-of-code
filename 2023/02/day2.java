import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


class Minimums{
  public int blue = 0;
  public int red = 0;
  public int green = 0;
}

public class day2 {
  public static void main(String[] args) {
    int red = 12;
    int green = 13;
    int blue = 14;
    int validGamesCount = 0;
    int minimumsProduct = 0;
    
    try {
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);
      
      while (reader.hasNextLine()){
        String line = reader.nextLine();
        int gameNumberIndex = line.indexOf(':');
        int gameNumber = Integer.parseInt(line.substring(5, gameNumberIndex));
        int beginIndex = gameNumberIndex + 2;
        String [] sets = line.substring(beginIndex).split("; ");
        boolean setValidity = true;
        Minimums minimum = new Minimums();

        for (int i = 0; i < sets.length; i++){
          String set = sets[i];

          setMinimum(minimum, set, "red");
          setMinimum(minimum, set, "green");
          setMinimum(minimum, set, "blue");
          
          // if(!isCountValid(set, "red", red) || !isCountValid(set, "green", green) ||
          //   !isCountValid(set, "blue", blue)){
          //   setValidity = false;
          //   break;
          // }

        }

        System.out.println("-------------");
        System.out.println(minimum.red);
        System.out.println(minimum.blue);
        System.out.println(minimum.green);
        System.out.println("----------");
        System.out.println();
        minimumsProduct += minimum.red * minimum.green * minimum.blue;
        validGamesCount += setValidity ? gameNumber : 0;
      }

      System.out.println(validGamesCount);
      System.out.println(minimumsProduct);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static boolean isCountValid(String str, String colour, int colourMaxCount){
    if (!str.contains(colour))
      return true;
      
    int colourCount = getColourValue(str, colour);
    
    return colourCount > colourMaxCount ? false : true;
  }
  
  static void setMinimum(Minimums minimums, String str, String colour){
    if (!str.contains(colour))
      return;
      
    
    int colourCount = getColourValue(str, colour);

    // System.out.println(colourCount);
    // System.out.println(minimums.red);
    // System.out.println(minimums.blue);
    // System.out.println(minimums.green);
    if (colour == "red")
      minimums.red = colourCount > minimums.red ? colourCount : minimums.red;
    else if (colour == "green")
      minimums.green = colourCount > minimums.green ? colourCount : minimums.green;
    else if (colour == "blue")
      minimums.blue = colourCount > minimums.blue ? colourCount : minimums.blue;
  }

  static int getColourValue(String str, String colour){
    int cubeLastIndex = str.indexOf(colour) - 1;
    int cubeBeginIndex = str.substring(0, cubeLastIndex).lastIndexOf(' ') + 1;
    cubeBeginIndex = cubeBeginIndex == 0 ? 0 : cubeBeginIndex;
    
    return Integer.parseInt(str.substring(cubeBeginIndex, cubeLastIndex));
  }
}
