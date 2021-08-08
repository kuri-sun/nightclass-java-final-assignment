package ca.ciccc.wmad202.projects.main;

import java.util.ArrayList;
import java.util.Iterator;

import ca.ciccc.wmad202.projects.problem1.Peak;
import ca.ciccc.wmad202.projects.problem1.returnInterface;
import ca.ciccc.wmad202.projects.problem1.Peak.InnerPeak;
import ca.ciccc.wmad202.projects.problem2.BattleFeild;
import ca.ciccc.wmad202.projects.problem2.BattleFeild.BattleNotOccurableException;

public class Driver {
  public static void main(String[] args) {
    System.out.println("\n");

    // ------------------- < problem 1 - Peak - > --------------------
    System.out.println(" ------------ < problem 1 - PEAK - > ----------- ");
    // create instance of the "Peak" class
    ArrayList<ArrayList<Integer>> allStretchLine1 = Peak.scannerForTxtFile();

    // using "anonimous class" as argument
    Peak.InnerPeak innerPeak = new InnerPeak();
    ArrayList<Integer> answerList1 = innerPeak.returnInt(allStretchLine1, new returnInterface() {
      public Integer returnInt(ArrayList<Integer> l) {
        Integer countMax = 0;
        Integer max = null;
        for (Integer item : l) {
          if (max == null) {
            countMax = 1;
            max = item;
          } else if (max < item) {
            countMax = 1;
            max = item;
          } else if (max == item) {
            countMax++;
          }
        }
        return countMax;
      };
    });
    // print the answers using "Iterator"
    Iterator<Integer> answer = answerList1.iterator();
    Integer index = 0;
    while (answer.hasNext()) {
      index++;
      System.out.println("The number of Land" + index + "'s-stretch's Peak is : " + answer.next());
    }

    System.out.println("\n");
    // ------------------- < problem 1 - Valley - > --------------------
    System.out.println(" ------------ < problem 1 - VALLEY - > ----------- ");
    ArrayList<ArrayList<Integer>> allStretchLandList2 = Peak.scannerForTxtFile();

    // "lamdba expression"
    Peak.InnerPeak innerValley = new Peak.InnerPeak();
    ArrayList<Integer> answeList2 = innerValley.returnInt(allStretchLandList2, l -> {
      Integer countMin = 0;
      Integer min = null;
      for (Integer item : l) {
        if (min == null) {
          countMin = 1;
          min = item;
        } else if (min > item) {
          countMin = 1;
          min = item;
        } else if (min == item) {
          countMin++;
        }
      }
      return countMin;
    });
    // using "Iterator"
    Iterator<Integer> answer2 = answeList2.iterator();
    Integer index2 = 0;
    while (answer2.hasNext()) {
      index2++;
      System.out.println("The number of Land" + index2 + "'s-stretch's Valley is : " + answer2.next());
    }

    // ----------------------- < problem 2 > ---------------------------------
    System.out.println("\n");
    System.out.println("------------- < problem 2 - TRANSFORMER - > ----------- ");

    BattleFeild bFeild = new BattleFeild();
    try {

      ArrayList<ArrayList<String>> result = bFeild.battleStart();
      int roundOftheBattle = 0;
      for (ArrayList<String> eachBattleResult : result) {
        roundOftheBattle++;

        // print the result
        System.out.println("--- ROUND-" + roundOftheBattle + " ---");
        System.out.println("The number of battles : " + eachBattleResult.get(0));
        System.out.println("The winning team : " + eachBattleResult.get(1));
        System.out.println("The surviving members of the losing team : " + eachBattleResult.get(2));
        System.out.println("\n");

      }

    } catch (BattleNotOccurableException e) {
      e.printStackTrace();
    }

  }

}
