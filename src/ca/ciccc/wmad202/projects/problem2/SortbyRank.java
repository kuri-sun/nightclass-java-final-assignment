package ca.ciccc.wmad202.projects.problem2;

import java.util.ArrayList;
import java.util.Collections;

public class SortbyRank {

  public static ArrayList<Transformers> sortingByRank(ArrayList<Transformers> oneTeam) {

    ArrayList<Integer> ranksList = new ArrayList<>();

    for (Transformers oneTrans : oneTeam) {
      Integer rank = oneTrans.getRank();
      ranksList.add(rank);
    }

    Collections.sort(ranksList, Collections.reverseOrder());

    ArrayList<Transformers> newOneTeamOrder = new ArrayList<>();

    boolean isAbleToAdded;

    for (Integer rank : ranksList) {
      for (Transformers oneTrans : oneTeam) {
        isAbleToAdded = true;
        if (rank == oneTrans.getRank()) {

          for (Transformers transInNewList : newOneTeamOrder) {
            if (transInNewList == oneTrans) {
              isAbleToAdded = false;
            }
          }

          if (isAbleToAdded) {
            newOneTeamOrder.add(oneTrans);
          }

        }
      }
    }

    return newOneTeamOrder;

  }

}
