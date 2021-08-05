package ca.ciccc.wmad202.projects.problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// peak class ( has only one mehtod )
public class Peak {

  // input method and store it as class-properties.
  public static ArrayList<ArrayList<Integer>> scannerForTxtFile() {

    // [Array 2]
    ArrayList<ArrayList<Integer>> linelist = new ArrayList<ArrayList<Integer>>();

    File inputFile = new File("out/castles.txt");
    // throwing error when detect something.(built-in error)
    try {

      Scanner in = new Scanner(inputFile);

      while (in.hasNextLine()) {
        String words = in.nextLine();
        // [Array 1]
        ArrayList<Integer> landStretchList = new ArrayList<Integer>();

        for (int i = 0; i < words.length(); i++) {
          Character word = words.charAt(i);
          // <improve> *** I want to use regular expression ... ***
          if (word == '1' || word == '2' || word == '3' || word == '4' || word == '5' || word == '6' || word == '7'
              || word == '8' || word == '9' || word == '0') {
            Integer integer = Character.getNumericValue(word);
            // store this to [Array 1]
            landStretchList.add(integer);
          }

        }
        // store to [Array 2].
        linelist.add(landStretchList);
      }

      // make sure that closing opened file.
      in.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    return linelist;
  }

  // inner class ( has only one method )
  public static class InnerPeak {

    public ArrayList<Integer> returnInt(ArrayList<ArrayList<Integer>> allStretchLandList, returnInterface reint) {

      // answer list for each stretch-land array.
      ArrayList<Integer> integerAnsList = new ArrayList<Integer>();

      // iterating through each stretch-land array.
      for (ArrayList<Integer> eachList : allStretchLandList) {
        Integer integerAns = reint.returnInt(eachList);
        integerAnsList.add(integerAns);
      }

      return integerAnsList;
    }

  }

}
