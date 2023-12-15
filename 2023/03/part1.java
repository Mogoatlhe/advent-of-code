import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part1 {
  public static void main(String[] args) {
    try {
      int sum = 0;
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);
      ArrayList<String> lines = new ArrayList<String>();

      while (reader.hasNextLine()){
        String nextLine = reader.nextLine();

        if (nextLine.length() == 0)
          break;
        lines.add(nextLine);
      }
      
      for (int i = 0; i < lines.size(); i++){
        int topLineIndex = i - 1;
        int bottomLineIndex = i + 1;
        String currLine = lines.get(i);
        String currNum = "";
        boolean isSpecial = false;

        // 18
        for (int j = 0; j < currLine.length(); j++){
          char currChar = currLine.charAt(j);
          
          if (!Character.isDigit(currChar)){
            sum += isSpecial ? Integer.parseInt(currNum) : 0;
            isSpecial = false;
            currNum = "";
          } else {
            currNum += currChar;

            if (isSpecial){
              if (j == currLine.length() - 1)
                sum += Integer.parseInt(currNum);
              continue;
            }

            if (topLineIndex >= 0){
              isSpecial = isSpecial ? isSpecial : isCharacterSpecial(lines.get(topLineIndex).charAt(j));

              isSpecial = isLeftRightSpecial(currChar, j, lines.get(topLineIndex), isSpecial);
            }

            if (bottomLineIndex < lines.size()){
              isSpecial = isSpecial ? isSpecial : isCharacterSpecial(lines.get(bottomLineIndex).charAt(j));

              isSpecial = isLeftRightSpecial(currChar, j, lines.get(bottomLineIndex), isSpecial);
            }

            isSpecial = isLeftRightSpecial(currChar, j, currLine, isSpecial);
          }
        }
      }

      System.out.println(sum);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static boolean isCharacterSpecial(char currChar){
    return (!Character.isDigit(currChar) && currChar != '.') ? true : false;
  }

  static boolean isLeftRightSpecial(char currChar, int currIndex, String currLine, boolean isSpecial){
    if (currIndex > 0){
      isSpecial = isSpecial ? isSpecial : isCharacterSpecial(currLine.charAt(currIndex - 1)); 
    }

    if (currIndex < currLine.length() - 1){
      isSpecial = isSpecial ? isSpecial : isCharacterSpecial(currLine.charAt(currIndex + 1)); 
    }

    return isSpecial;
  }
}
