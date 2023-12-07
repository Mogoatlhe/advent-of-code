import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part2 {
  public static void main(String[] args) {
    try {
      int product = 0;
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);
      ArrayList<String> lines = new ArrayList<String>();

      while (reader.hasNextLine()){
        String nextLine = reader.nextLine();

        if (nextLine.length() == 0)
          break;
        lines.add(nextLine);
      }
      
      // goes through each line
      for (int i = 0; i < lines.size(); i++){
        String currLine = lines.get(i);
        
        while (currLine.indexOf("*") != -1){
          int starInLineIndex = currLine.indexOf("*");

          if (starInLineIndex == -1)
            break;

          int num = 1;
          int numCount = 0;
          String left = "";
          String right = "";
          
          if (i > 0){
            String topLine = lines.get(i - 1);
            
            if (starInLineIndex > 0
            && Character.isDigit(topLine.charAt(starInLineIndex - 1))
            ){
              left = getNumLeftRight(topLine, starInLineIndex - 2, "left");
              right = getNumLeftRight(topLine, starInLineIndex, "right");
              num *= Integer.parseInt((left + topLine.charAt(starInLineIndex - 1) + right));
              numCount += 1;
            }

            
            if (!Character.isDigit(topLine.charAt(starInLineIndex - 1))
            && Character.isDigit(topLine.charAt(starInLineIndex))){
              left = getNumLeftRight(topLine, starInLineIndex - 1, "left");
              right = getNumLeftRight(topLine, starInLineIndex + 1, "right");
              num *= Integer.parseInt((left + topLine.charAt(starInLineIndex) + right));
              numCount += 1;
            }
            
            if (!Character.isDigit(topLine.charAt(starInLineIndex))
            && Character.isDigit(topLine.charAt(starInLineIndex + 1))){
              left = getNumLeftRight(topLine, starInLineIndex, "left");
              right = getNumLeftRight(topLine, starInLineIndex + 2, "right");
              num *= Integer.parseInt((left + topLine.charAt(starInLineIndex + 1) + right));
              numCount += 1;
            }
          }
          
          if (i < (lines.size() - 1)){
            String bottomLine = lines.get(i + 1);
            
            if (starInLineIndex > 0
            && Character.isDigit(bottomLine.charAt(starInLineIndex - 1))
            ){
              left = getNumLeftRight(bottomLine, starInLineIndex - 2, "left");
              right = getNumLeftRight(bottomLine, starInLineIndex, "right");
              num *= Integer.parseInt((left + bottomLine.charAt(starInLineIndex - 1) + right));
              numCount += 1;
            }
            
            if (!Character.isDigit(bottomLine.charAt(starInLineIndex - 1))
            && Character.isDigit(bottomLine.charAt(starInLineIndex))){
              left = getNumLeftRight(bottomLine, starInLineIndex - 1, "left");
              right = getNumLeftRight(bottomLine, starInLineIndex + 1, "right");
              num *= Integer.parseInt((left + bottomLine.charAt(starInLineIndex) + right));
              numCount += 1;
            }
            
            if (!Character.isDigit(bottomLine.charAt(starInLineIndex))
            && Character.isDigit(bottomLine.charAt(starInLineIndex + 1))){
              left = getNumLeftRight(bottomLine, starInLineIndex, "left");
              right = getNumLeftRight(bottomLine, starInLineIndex + 2, "right");
              num *= Integer.parseInt((left + bottomLine.charAt(starInLineIndex + 1) + right));
              numCount += 1;
            }
          }
          
          left = getNumLeftRight(currLine, starInLineIndex - 1, "left");
          right = getNumLeftRight(currLine, starInLineIndex, "right");
          num *= left.length() > 0 ? Integer.parseInt(left) : 1;
          numCount += left.length() > 0 ? 1 : 0;
          
          left = getNumLeftRight(currLine, starInLineIndex, "left");
          right = getNumLeftRight(currLine, starInLineIndex + 1, "right");
          num *= right.length() > 0 ? Integer.parseInt(right) : 1;
          numCount += right.length() > 0 ? 1 : 0;
          
          if (numCount != 2) num = 0;
          
          numCount = 0;
          product += num;
          num = 1;
          currLine = currLine.substring(0, starInLineIndex) + '.' + currLine.substring(starInLineIndex + 1);
        }
      }

      System.out.println(product);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static String getNumLeftRight(String line, int currCharIndex, String direction){
    if (currCharIndex >= line.length() || currCharIndex < 0 || !Character.isDigit(line.charAt(currCharIndex)))
    return "";
    
    char currChar = line.charAt(currCharIndex);
    if (direction == "right")
      return currChar + getNumLeftRight(line, currCharIndex + 1, direction);
    return getNumLeftRight(line, currCharIndex - 1, direction) + currChar;
  }
}
