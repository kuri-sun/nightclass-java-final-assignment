package ca.ciccc.wmad202.projects.problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Valleys {

  // scan from .txt file
  public static ArrayList<ArrayList<Integer>> scannerForTxtFile() {
    // [ Array 2 ]
    ArrayList<ArrayList<Integer>> lineList = new ArrayList<ArrayList<Integer>>();
    File inputFile = new File("out/castles.txt");

    try {
      Scanner in = new Scanner(inputFile);
      while (in.hasNextLine()) {
        String words = in.nextLine();
        // [ Array 1 ]
        ArrayList<Integer> landStretchList = new ArrayList<Integer>();
        for (int i = 0; i < words.length(); i++) {
          Character word = words.charAt(i);
          if (word == '1' || word == '2' || word == '3' || word == '4' || word == '5' || word == '6' || word == '7'
              || word == '8' || word == '9' || word == '0') {
            Integer integer = Character.getNumericValue(word);
            // store to [ Array 1 ]
            landStretchList.add(integer);
          }
        }
        // store to [ Array 2 ]
        lineList.add(landStretchList);
      }
      in.close();

    } catch (FileNotFoundException e) {

      e.printStackTrace();

    }

    return lineList;
  }

  // testing method.
  public static ArrayList<Integer> returnInt(ArrayList<ArrayList<Integer>> allStretchLandList, returnInterface reint) {

    ArrayList<Integer> integerAnsList = new ArrayList<Integer>();

    for (ArrayList<Integer> eachlList : allStretchLandList) {
      Integer integer = reint.returnInt(eachlList);
      integerAnsList.add(integer);
    }

    return integerAnsList;
  }

}
