package ca.ciccc.wmad202.projects.problem2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ca.ciccc.wmad202.projects.problem2.Input.BattleNotOccursExceptions;

public class Test {

  public static void CreateInstanceOfTransfomers() {

    // < step 1 >
    // input from .txt file / separate data into array of each team / check if
    // battle is ocurrable or not.
    Input input = new Input();
    try {
      ArrayList<String> battle = input.inputTxtFile();

      // < step 2 >
      // split an array of each-team to transformer
      SplitTeamsToTransformers split = new SplitTeamsToTransformers();
      split.splitTeamToTransformer(battle);

      // < step 3 >
      // split transformers-array to each parameters(String name, String type,
      // ArrayList<Integer> parameters) of "Transformers.java".

    } catch (FileNotFoundException | BattleNotOccursExceptions e) {
      e.printStackTrace();
    }

  }

}
