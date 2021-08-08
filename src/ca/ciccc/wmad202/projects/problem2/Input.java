package ca.ciccc.wmad202.projects.problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// ** NOT STATIC **
public class Input {

  // custom error class
  public class BattleNotOccursExceptions extends Exception {

    public BattleNotOccursExceptions(String errorMessage) {
      super(errorMessage);
    }

  }

  public ArrayList<String> inputTxtFile() throws BattleNotOccursExceptions, FileNotFoundException {

    ArrayList<String> battle = new ArrayList<>();
    Integer battleEndBlankArrayCount = 0;

    File inputFile = new File("out/transformers.txt");
    Scanner in = new Scanner(inputFile);

    while (in.hasNextLine()) {
      String line = in.nextLine().trim();
      // check if the line is empty or not.
      if (line.length() == 0) {
        battleEndBlankArrayCount++;
      }
      // check if "battleEndBlankArrayCount" is "2" or not.
      if (battleEndBlankArrayCount == 2) {
        if (battle.size() % 2 != 0) {
          throw new BattleNotOccursExceptions("YOUR OPPONENT IS NOT HERE !!!");
        } else {
          battleEndBlankArrayCount = 0;
        }
      }
      // check if the line is not empty and push the line to array of "battle"
      if (line.length() > 0) {
        battle.add(line);
      }
    }

    in.close();

    return battle;

  }

}
