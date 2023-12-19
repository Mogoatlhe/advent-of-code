import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


class Coords{
  public int row;
  public int col;

  Coords(int row, int col){
    this.row = row;
    this.col = col;
  }
}

public class part1 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("../input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      fileContents = addSentinelValues(fileContents);
      String [] pattern = fileContents.split("\n");
      Coords prev = getStartingPoint(fileContents);
      Coords next = getNextCoords(pattern, prev);
      double steps = 0;

      while (pattern[next.row].charAt(next.col) != 'S'){
        char curr = pattern[next.row].charAt(next.col);

        if (curr == '|'){
          prev.col = next.col;
          if (prev.row != next.row + 1){
            prev.row = next.row;
            next.row = next.row + 1;
          } else {
            prev.row = next.row;
            next.row = next.row - 1;
          }
        }
        else if (curr == '-'){
          prev.row = next.row;
          if (prev.col != next.col + 1){
            prev.col = next.col;
            next.col = next.col + 1;
          } else {
            prev.col = next.col;
            next.col = next.col - 1;
          }
        }
        else if (curr == 'L'){
          if (prev.col != next.col + 1){
            prev.row = next.row;
            prev.col = next.col;
            next.col = next.col + 1;
          } else if (prev.row != next.row - 1) {
            prev.row = next.row;
            prev.col = next.col;
            next.row = next.row - 1;
          }
        }
        else if (curr == 'J'){
          if (prev.col != next.col - 1){
            prev.row = next.row;
            prev.col = next.col;
            next.col = next.col - 1;
          } else if (prev.row != next.row - 1) {
            prev.row = next.row;
            prev.col = next.col;
            next.row = next.row - 1;
          }
        }
        else if (curr == '7'){
          if (prev.col != next.col - 1){
            prev.row = next.row;
            prev.col = next.col;
            next.col = next.col - 1;
          } else if (prev.row != next.row + 1) {
            prev.row = next.row;
            prev.col = next.col;
            next.row = next.row + 1;
          }
        }
        else if (curr == 'F'){
          if (prev.col != next.col + 1){
            prev.row = next.row;
            prev.col = next.col;
            next.col = next.col + 1;
          } else if (prev.row != next.row + 1) {
            prev.row = next.row;
            prev.col = next.col;
            next.row = next.row + 1;
          }
        }

        steps += 1;
      }

      steps = Math.round(steps / 2);
      System.out.println(steps);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static String addSentinelValues(String str){
    String paddedString = "." + str.replaceAll("\n", ".\n.") + ".";
    int stringWidth = paddedString.indexOf("\n");

    char [ ] arr = new char[stringWidth];
    Arrays.fill(arr, '.');
    String paddedRow = new String(arr);
    String top = paddedRow + "\n";
    String bottom = "\n" + paddedRow;

    return top + paddedString + bottom;
  }

  static Coords getStartingPoint(String str){
    int sIndex = str.indexOf("S");
    int strWidth = str.indexOf("\n");
    int row = sIndex / strWidth;
    sIndex -= row;
    int col = sIndex % strWidth;

    return new Coords(row, col);
  }

  static int getTotalSteps(String [] pattern, Coords coords, int steps){
    if (steps != 0 && pattern[coords.row].charAt(coords.col) == 'S'){
      return steps;
    }

    steps += 1;

    Coords nexCoords = getNextCoords(pattern, coords);
    String currRow = pattern[coords.row];
    pattern[coords.row] = currRow.substring(0, coords.col) + "." + currRow.substring(coords.col + 1);

    return getTotalSteps(pattern, nexCoords, steps);
  }

  static Coords getNextCoords(String [] pattern, Coords currCoords){
    char down = pattern[currCoords.row - 1].charAt(currCoords.col); 
    if (down == '|' || down == 'L' || down == 'J'){
      return new Coords(currCoords.row - 1, currCoords.col);
    }
    
    char up = pattern[currCoords.row + 1].charAt(currCoords.col); 
    if (up == '|' || up == '7' || up == 'F'){
      return new Coords(currCoords.row + 1, currCoords.col);
    }
    
    char left = pattern[currCoords.row].charAt(currCoords.col - 1); 
    if (left == '-' || left == 'L' || left == 'F'){
      return new Coords(currCoords.row, currCoords.col - 1);
    }

    return new Coords(currCoords.row, currCoords.col + 1);
  }
}

