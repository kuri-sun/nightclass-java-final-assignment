package ca.ciccc.wmad202.projects.problem2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ca.ciccc.wmad202.projects.problem2.Input.BattleNotOccursExceptions;
import ca.ciccc.wmad202.projects.problem2.Test.RequiredElementsDefected;
import ca.ciccc.wmad202.projects.problem2.Test.TypeNotFoundExceptions;

public class BattleFeild {

  public static class BattleNotOccurableException extends Exception {
    public BattleNotOccurableException(String errormessage) {
      super(errormessage);
    }
  }

  // All battles are gonna be fired.
  public ArrayList<ArrayList<String>> battleStart() throws BattleNotOccurableException {

    // result
    ArrayList<ArrayList<String>> result = new ArrayList<>();

    // receive an array of instances.
    try {
      ArrayList<ArrayList<ArrayList<Transformers>>> teamAandB = Test.CreateInstanceOfTransfomers();

      // <allteams <one-team <Transformer> > >
      ArrayList<ArrayList<Transformers>> team_A = teamAandB.get(0);
      ArrayList<ArrayList<Transformers>> team_B = teamAandB.get(1); //

      // in case of match not occurs.
      if (team_A.size() != team_B.size()) {
        throw new BattleNotOccurableException(
            "battle is not occurable because your entered infomation in .txt is wrong...");
      }

      int round = 0;

      // LIST OF WHAT WE SHOULD DISPLAY.
      // ----- start battles one-team vs one-team. ----------------------------
      for (int i = 0; i < team_A.size(); i++) {

        round++;

        ArrayList<String> displayList = new ArrayList<>();

        // compare each team based on the rule ...
        ArrayList<Transformers> teamAonBattleFeild = team_A.get(i);
        ArrayList<Transformers> teamBonBattleFeild = team_B.get(i); //

        // -------- < special rule > --------
        // ! (Property) name
        // the opponent class's "name" property is "Optimus Prime" or "Predaking"
        // => automatically lose.

        // ! Courage and ! Strength
        // *** VS.
        // for any fighter down for (4 or less). (Courage)
        // opponents compared to the opponents ( 3 more ) (Strength)
        // => lose.

        // ! Skill
        // *** VS. opponents compared to the your own "Skill" by 3 points or less
        // points. ***
        // => lose.

        // --------- < simple rules > ------------
        // ! Comparable <Simple rule> -
        // => By "overall rating" ( = Strength + Intelligence + Speed + Endurance +
        // Firepower )

        String wingingTeam = "";
        String survivingMembersInLosingTeam = "";

        // 1. The number of battles
        // check how many round we occurs ...
        Integer numberOfBattles = 0;

        // <required> sort each team's array by "rank".
        ArrayList<Transformers> teamAonBattleFeild_sorted = SortbyRank.sortingByRank(teamAonBattleFeild);
        ArrayList<Transformers> teamBonBattleFeild_sorted = SortbyRank.sortingByRank(teamBonBattleFeild); //

        int battleWillNotOccursNumbersToSupportIndex = Math
            .abs(teamAonBattleFeild_sorted.size() - teamBonBattleFeild_sorted.size());

        int indexForTeamA = 0;
        int indexForTeamB = 0;
        int winningTeamAcount = 0;
        int winningTeamBcount = 0;

        // -----// start one on one //-----
        while ((wingingTeam == "")
            && (teamAonBattleFeild_sorted.size() > indexForTeamA && teamBonBattleFeild_sorted.size() > indexForTeamB)) {

          numberOfBattles++;
          // System.out.println(indexForTeamB + "=" + teamBonBattleFeild_sorted.size());

          Transformers transformerFromTeamA = teamAonBattleFeild_sorted.get(indexForTeamA);
          Transformers transformerFromTeamB = teamBonBattleFeild_sorted.get(indexForTeamB);
          boolean isBattleEnded = false;
          String winner = "drow"; //

          // <1> - automatically win by (name) special rule -
          if (!isBattleEnded) {
            if (transformerFromTeamA.name.contains("Optimus Prime")
                || transformerFromTeamA.name.contains("Predaking")) {
              winner = "teamA";
              isBattleEnded = true;
            }
            if (transformerFromTeamB.name.contains("Optimus Prime")
                || transformerFromTeamB.name.contains("Predaking")) {
              if (winner.contains("teamA")) {
                winner = "drow";
              } else {
                winner = "teamB";
                isBattleEnded = true;
              }
            }
          }

          // <2> - compare (strength && courage) (skills) to another automatically rule -
          if (!isBattleEnded) {

            if ((transformerFromTeamA.getCourage() - transformerFromTeamB.getCourage() > 4)
                && (transformerFromTeamA.getStrength() - transformerFromTeamB.getStrength() > 3)) {
              isBattleEnded = true;
              winner = "teamA";
            }

            if ((transformerFromTeamB.getCourage() - transformerFromTeamA.getCourage()) > 4
                && (transformerFromTeamB.getCourage() - transformerFromTeamA.getCourage()) > 3) {
              isBattleEnded = true;
              winner = "teamB";
            }

          }

          // <3> - simple compare to the other overrall rate rule -
          if (!isBattleEnded) {
            if (transformerFromTeamA.compareTo(transformerFromTeamB) == 1) {
              // transformerFromTeamA wins
              isBattleEnded = true;
              winner = "teamA";
            } else if (transformerFromTeamA.compareTo(transformerFromTeamB) == -1) {
              // transformerFromTeamB wins
              isBattleEnded = true;
              winner = "teamB";
            } else if (transformerFromTeamA.compareTo(transformerFromTeamB) == 0) {
              // transformerFromTeamA and B drow
              isBattleEnded = true;
              winner = "drow";
            }
          }

          // --- then eliminate the loser and sliding index in winner team. --
          if (isBattleEnded) {
            if (winner.contains("teamA")) {
              teamBonBattleFeild_sorted.remove(indexForTeamB);
              indexForTeamA++;
              winningTeamAcount++;
            } else if (winner.contains("teamB")) {
              teamAonBattleFeild_sorted.remove(indexForTeamA);
              indexForTeamB++;
              winningTeamBcount++;
            } else if (winner.contains("drow")) {
              indexForTeamA++;
              indexForTeamB++;
            }

            System.out.println("(A)" + round + "round, " + numberOfBattles + "battle, " + indexForTeamA + "index, "
                + winningTeamAcount + "-" + winningTeamBcount + "count  |  " + teamAonBattleFeild_sorted);
            System.out.println();
            System.out.println("(B)" + round + "round, " + numberOfBattles + "battle, " + indexForTeamB + "index, "
                + winningTeamAcount + "-" + winningTeamBcount + "count  |  " + teamBonBattleFeild_sorted);
            System.out.println("\n");

          }

          // 2. The winning team
          // --- if length of either team gonna be 'indexForTeam', then winner is gonna be
          // determined.
          if (isBattleEnded) {

            // System.out.println(round + " | " + "( " + numberOfBattles + " battles ) : " +
            // winningTeamAcount + "-"
            // + winningTeamBcount + " " + isBattleEnded);

            // System.out.println(teamAonBattleFeild_sorted.size() == indexForTeamA);

            if (teamBonBattleFeild_sorted.size() == (indexForTeamB + battleWillNotOccursNumbersToSupportIndex)
                && (winningTeamAcount > winningTeamBcount)) {
              wingingTeam = "(Deception Team)";
              survivingMembersInLosingTeam = "(Autobot Team)"; //

            } else if (teamAonBattleFeild_sorted.size() == (indexForTeamA + battleWillNotOccursNumbersToSupportIndex)
                && (winningTeamBcount > winningTeamAcount)) {
              wingingTeam = "(Autobot Team)";
              survivingMembersInLosingTeam = "(Deception Team)";

            } else if ((teamAonBattleFeild_sorted.size() == indexForTeamA
                && teamBonBattleFeild_sorted.size() == indexForTeamB) && (winningTeamAcount == winningTeamBcount)) {
              wingingTeam = "(Drow)";
              survivingMembersInLosingTeam = "(Drow)";

            }

          }

          // 3. The surviving members of the losing team
          // --- check the survived transformers in a loser team. ---

          if (isBattleEnded) {

            if (wingingTeam != "") {

              if ((teamAonBattleFeild_sorted.size() > 0) && wingingTeam.contains("Autobot")) {

                // add survivers in "loser team" to "survivingMembersInLosingTeam"
                if (teamBonBattleFeild_sorted.size() > 0) {
                  for (Transformers remainingTransOnWinnerTeam : teamBonBattleFeild_sorted) {
                    wingingTeam = wingingTeam + " " + remainingTransOnWinnerTeam.name;
                  }
                }

                // add survivers in "loser team" to "survivingMembersInLosingTeam"
                for (Transformers remainingTrans : teamAonBattleFeild_sorted) {
                  survivingMembersInLosingTeam = survivingMembersInLosingTeam + " " + remainingTrans.name;
                }

              } else if ((teamBonBattleFeild_sorted.size() > 0) && wingingTeam.contains("Deception")) {

                // add survivers in "loser team" to "survivingMembersInLosingTeam"
                if (teamAonBattleFeild_sorted.size() > 0) {
                  for (Transformers remainingTransOnWinnerTeam : teamBonBattleFeild_sorted) {
                    wingingTeam = wingingTeam + " " + remainingTransOnWinnerTeam.name;
                  }
                }

                // add survivers in "loser team" to "survivingMembersInLosingTeam"
                for (Transformers remainingTrans : teamBonBattleFeild_sorted) {
                  survivingMembersInLosingTeam = survivingMembersInLosingTeam + " " + remainingTrans.name;
                }

              } else if (teamAonBattleFeild_sorted.size() == 0 && teamBonBattleFeild_sorted.size() == 0) {
                survivingMembersInLosingTeam = survivingMembersInLosingTeam + " they were all destroyed each other";

              } else {
                survivingMembersInLosingTeam = survivingMembersInLosingTeam + " no one survived ...";
              }
            }

          }

        }
        // (index) (what we gonna push)
        // 1. The number of battles
        displayList.add(String.valueOf(numberOfBattles));
        // 2. The winning team
        displayList.add(wingingTeam);
        // 3. The surviving members of the losing team
        displayList.add(survivingMembersInLosingTeam);

        // lastly, push displayList to result.
        result.add(displayList);
      }

    } catch (FileNotFoundException | TypeNotFoundExceptions | RequiredElementsDefected | BattleNotOccursExceptions e) {
      e.printStackTrace();
    }

    return result;
  }
}
