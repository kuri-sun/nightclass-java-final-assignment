package ca.ciccc.wmad202.projects.problem2;

import java.util.ArrayList;

// ** NOT STATIC **
// teams => transformers 
public class SplitTeamsToTransformers {

  public ArrayList<ArrayList<String>> splitTeamToTransformer(ArrayList<String> battle) {

    // all battles arrays
    ArrayList<ArrayList<String>> allBattles = new ArrayList<>();

    for (int i = 1; i <= battle.size(); i++) {
      // team-A
      if (i % 2 != 0) {
        // get team-A strings.
        String teamA_Members = battle.get(i);

        // if team is consist of more than 2 transformers.
        ArrayList<String> teamA_EachMember = new ArrayList<>();
        if (teamA_Members.contains("*")) {
          for (String eachMember : teamA_Members.split("*")) {
            teamA_EachMember.add(eachMember);
          }
        } else {
          // if team is consist of one person.
          teamA_EachMember.add(teamA_Members);
        }

        // add splitted team to battle.
        allBattles.add(teamA_EachMember);

      } else {
        // get team-B strings
        String teamB_Members = battle.get(i);

        // if team is consist of more than 2 transformers.
        ArrayList<String> teamB_EachMember = new ArrayList<>();
        if (teamB_Members.contains("*")) {
          for (String eachMember : teamB_Members.split("* ")) {
            teamB_EachMember.add(eachMember);
          }
        } else {
          // if team is consist of one person.
          teamB_EachMember.add(teamB_Members);
        }

        // add splitted team to battle.
        allBattles.add(teamB_EachMember);

      }

    }

    return allBattles;

    // what we do here ...

    // ["Soundwave, D, 8,9,2,6,7,5,6,10* Cliffjumper, D, 8,9,2,6,7,5,6,10",
    // "Bluestreak, A, 6,6,7,9,5,2,9,7* Hubcap, A, 4,4,4,4,4,4,4,4"]

    // ||
    // \/

    // [ ["Soundwave, D, 8,9,2,6,7,5,6,10" ,"Cliffjumper, D, 8,9,2,6,7,5,6,10"] , ["
    // Soundwave, D, 8,9,2,6,7,5,6,10", "Cliffjumper, D, 8,9,2,6,7,5,6,10"], ... ]

  }

}
