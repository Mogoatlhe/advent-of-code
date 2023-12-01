import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;;


class Data{
  public String wordedDigit = "";
  public char digit = ' ';
}

public class day1 {
  public static void main(String[] args) {
    try {
      int sum = 0;
      File file = new File("input.txt");
      Scanner reader = new Scanner(file);

      while (reader.hasNextLine()){
        Data firstData = new Data();
        Data lastData = new Data();
        String line = reader.nextLine();
        
        for (int i = 0; i < line.length(); i++){
          setDigit(firstData, line, "first", i);
          setDigit(lastData, line, "last", i);
          
          if (!Character.isWhitespace(firstData.digit) && !Character.isWhitespace(lastData.digit)){
            sum += Integer.parseInt(Character.toString(firstData.digit) + Character.toString(lastData.digit));
            break;
          }
        }
      }

      System.out.println(sum);
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  static void setDigit(Data data, String line, String type, int index){
    if (!Character.isWhitespace(data.digit))
      return;

    int charIndex = (type == "first") ? index : line.length() - (index + 1);
    ArrayList<String> digits = new ArrayList<String>(List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"));

    if (Character.isDigit(line.charAt(charIndex))){
      data.digit = line.charAt(charIndex);
    } else if (!digits.contains(data.wordedDigit)){
      if (type == "first")
        data.wordedDigit += line.charAt(charIndex); 
      else
        data.wordedDigit = line.charAt(charIndex) + data.wordedDigit;

      for (int j = 0; j < digits.size(); j++){
        if (digits.get(j).contains(data.wordedDigit)){
          if (digits.contains(data.wordedDigit)){
            data.digit = (char)((digits.indexOf(data.wordedDigit) + 1) + '0');
          }
          break;
        }

        data.wordedDigit = removeLetter(data.wordedDigit, type, j);
      }
    }
  }

  static String removeLetter(String str, String posToRemove, int currIndex){
    int DIGITS_LENGTH = 9;

    if (currIndex < DIGITS_LENGTH - 1)
      return str;
    
    int bound = str.length() - 1;
    return (posToRemove == "first") ? str.substring(1) : str.substring(0, bound); 
  }
}
