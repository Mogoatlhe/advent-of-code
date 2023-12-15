import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class part1 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("../input-test.txt");
    Path pathToFile = Paths.get("../input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String directions = fileContents.substring(0, fileContents.indexOf("\n"));
      fileContents = fileContents.substring(fileContents.indexOf("\n") + 1).trim();
      long nodesCount = fileContents.chars().filter(ch -> ch == '\n').count() + 1;
      HashMap<String, String> elements = new HashMap<String, String>();
      
      for (int i = 0; i < nodesCount; i++){
        String key = fileContents.substring(0, fileContents.indexOf(" "));
        String value = fileContents.substring(fileContents.indexOf("("), fileContents.indexOf(")") + 1);
        elements.put(key, value);

        if (fileContents.contains("\n")){
          fileContents = fileContents.substring(fileContents.indexOf("\n") + 1);
        }
      }

      int steps = 0;

      String element = "AAA";
      for (int i = 0; i < directions.length(); i++){
        char currChar = directions.charAt(i);

        element = elements.get(element);
        if (currChar == 'L'){
          element = element.substring(element.indexOf("(") + 1, element.indexOf(","));
        } else {
          element = element.substring(element.indexOf(" ") + 1, element.indexOf(")"));
        }
        steps += 1;
        
        if (element.compareTo("ZZZ") == 0){
          break;
        }

        if (i == directions.length() - 1){
          i = -1;
        }
      }
      
      System.out.println(steps);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

