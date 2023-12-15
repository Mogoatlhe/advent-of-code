import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class part2 {
  public static void main(String[] args) {
    // Path pathToFile = Paths.get("input-test.txt");
    Path pathToFile = Paths.get("../input.txt");

    try {
      String fileContents = Files.readString(pathToFile);
      String directions = fileContents.substring(0, fileContents.indexOf("\n"));
      fileContents = fileContents.substring(fileContents.indexOf("\n") + 1).trim();
      long nodesCount = fileContents.chars().filter(ch -> ch == '\n').count() + 1;
      HashMap<String, String> elements = new HashMap<String, String>();
      ArrayList<String> currElements = new ArrayList<String>();
      for (int i = 0; i < nodesCount; i++){
        String key = fileContents.substring(0, fileContents.indexOf(" "));
        String value = fileContents.substring(fileContents.indexOf("("), fileContents.indexOf(")") + 1);
        elements.put(key, value);

        if (key.charAt(2) == 'A'){
          currElements.add(key);
        }
        
        if (fileContents.contains("\n")){
          fileContents = fileContents.substring(fileContents.indexOf("\n") + 1);
        }
      }

      ArrayList<Integer> finalSteps = new ArrayList<Integer>();
      
      for (int i = 0; i < currElements.size(); i++){
        int steps = 0;
        String element = currElements.get(i);

        for (int j = 0; j < directions.length(); j++){
          char currChar = directions.charAt(j);

          element = elements.get(element);
          if (currChar == 'L'){
            element = element.substring(element.indexOf("(") + 1, element.indexOf(","));
          } else {
            element = element.substring(element.indexOf(" ") + 1, element.indexOf(")"));
          }
          steps += 1;
          
          if (element.charAt(2) == 'Z'){
            finalSteps.add(steps);
            break;
          }

          if (j == directions.length() - 1){
            j = -1;
          }
        }
      }

      int lcm = 0;
      Collections.sort(finalSteps);

      System.out.println(lcm);
      System.out.println(finalSteps.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

