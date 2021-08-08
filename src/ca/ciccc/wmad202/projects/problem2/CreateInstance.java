package ca.ciccc.wmad202.projects.problem2;

import java.util.ArrayList;

public class CreateInstance {

  public ArrayList<ArrayList<String>> createInstance(ArrayList<String> eachTeam) {

    // EX. now like " ["Soundwave, D, 8,9,2,6,7,5,6,10" ,"Cliffjumper, D,
    // 8,9,2,6,7,5,6,10"] "
    // split each members ability into three components
    ArrayList<ArrayList<String>> splittedAbilitiesTeamArray = new ArrayList<>();
    for (String member : eachTeam) {
      // store 3 components per member in this array.
      // [[ "Soundwave", "D", "8,9,2,6,7,5,6,10" ] , ... ]
      ArrayList<String> oneMemberEachAbilities = new ArrayList<>();
      for (String component : member.split(", ")) {
        oneMemberEachAbilities.add(component);
      }
      // splitted out one team array.
      splittedAbilitiesTeamArray.add(oneMemberEachAbilities);
    }

    return splittedAbilitiesTeamArray;

  }

}
