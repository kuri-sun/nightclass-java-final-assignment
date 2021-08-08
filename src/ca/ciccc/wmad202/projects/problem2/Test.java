package ca.ciccc.wmad202.projects.problem2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import ca.ciccc.wmad202.projects.problem2.Input.BattleNotOccursExceptions;

public class Test {

  // custom error exceptions.
  public static class TypeNotFoundExceptions extends Exception {
    public TypeNotFoundExceptions(String errmessage) {
      super(errmessage);
    }
  }

  // custom exception
  public static class RequiredElementsDefected extends Exception {
    public RequiredElementsDefected(String errormessage) {
      super(errormessage);
    }
  }

  public static ArrayList<ArrayList<ArrayList<Transformers>>> CreateInstanceOfTransfomers()
      throws TypeNotFoundExceptions, RequiredElementsDefected, FileNotFoundException, BattleNotOccursExceptions {

    ArrayList<ArrayList<ArrayList<Transformers>>> teamAandB = new ArrayList<>();
    // < step 1 >
    // input from .txt file / separate data into array of each team / check if
    // battle is ocurrable or not.
    try {
      Input input = new Input();
      ArrayList<String> battle = input.inputTxtFile(); //

      // < step 2 >
      // split an array of each-team to transformer
      SplitTeamsToTransformers split = new SplitTeamsToTransformers();
      ArrayList<ArrayList<String>> allTeams = split.splitTeamToTransformer(battle); //

      // store each team-array in battle order.
      ArrayList<ArrayList<Transformers>> teamA = new ArrayList<>();
      ArrayList<ArrayList<Transformers>> teamB = new ArrayList<>();

      for (ArrayList<String> eachTeam : allTeams) {

        int indexOfTeam = allTeams.indexOf(eachTeam) + 1;

        // < step 3 >
        // split transformers-array to each parameters( name, type,
        // parameters) of "Transformers.java".
        CreateInstance create = new CreateInstance();
        ArrayList<ArrayList<String>> oneTeamArray = create.createInstance(eachTeam); //

        // ------- team A --------------------------------------------------------
        if (indexOfTeam % 2 != 0) {
          // if team-A's array comes in.
          ArrayList<Transformers> oneTeamInstances = new ArrayList<>();
          for (ArrayList<String> transformerAbilitiesArrayFirstTeam : oneTeamArray) {
            if (transformerAbilitiesArrayFirstTeam.size() != 3) {
              throw new RequiredElementsDefected("please fill in 'name', 'type', 'rating' currectly...");
            }
            // <first> define types... store which type based on interface.
            Type whichType;

            // retrieve as a character.
            String typeStringOfFirstTeam = transformerAbilitiesArrayFirstTeam.get(1);
            char typeCharOfFirstTeam = typeStringOfFirstTeam.charAt(0);

            if (typeCharOfFirstTeam == 'A') {
              Autobot autobot = new Autobot();
              whichType = autobot;
            } else if (typeCharOfFirstTeam == 'D') {
              Deception deception = new Deception();
              whichType = deception;
            } else {
              // if write a type doesn't exist...
              throw new TypeNotFoundExceptions("no match types !!");
            }

            // <second> convert string-overall-rating to integer
            ArrayList<Integer> overallRating = new ArrayList<>();
            for (String numberOfEachRate : transformerAbilitiesArrayFirstTeam.get(2).split(",")) {
              // convert string to integer.
              Integer eachRate = Integer.parseInt(numberOfEachRate);
              overallRating.add(eachRate);
            }
            if (overallRating.size() != 8) {
              throw new RequiredElementsDefected("not satisfied criteria of overall rate (must put 8 numbers)");
            }

            // <third> create Transformer
            Transformers transformer = new Transformers(transformerAbilitiesArrayFirstTeam.get(0), whichType,
                overallRating);

            // <forth> append one-transformer to one-team-array
            oneTeamInstances.add(transformer);

          }

          teamA.add(oneTeamInstances);

        } else {
          // ------- team B--------------------------------------------------------
          ArrayList<Transformers> oneTeamInstancesTwo = new ArrayList<>();
          for (ArrayList<String> transformerAbilitiesArraySecondTeam : oneTeamArray) {
            if (transformerAbilitiesArraySecondTeam.size() != 3) {
              throw new RequiredElementsDefected("please fill in 'name, type, rate' currectly ..");
            }
            // <first>
            Type types;

            // retrieve as a character
            String typeStringOfSecondTeam = transformerAbilitiesArraySecondTeam.get(1);
            char typeOfCharOfSecondTeam = typeStringOfSecondTeam.charAt(0);

            if (typeOfCharOfSecondTeam == 'A') {
              Autobot autobot = new Autobot();
              types = autobot;
            } else if (typeOfCharOfSecondTeam == 'D') {
              Deception deception = new Deception();
              types = deception;
            } else {
              throw new RequiredElementsDefected("no matched types here !!");
            }

            // <second>
            ArrayList<Integer> overallRating = new ArrayList<>();
            for (String numberOfEachRate : transformerAbilitiesArraySecondTeam.get(2).split(",")) {
              // covertion
              Integer eachRate = Integer.parseInt(numberOfEachRate);
              overallRating.add(eachRate);
            }
            if (overallRating.size() != 8) {
              throw new RequiredElementsDefected("please fill in 'name, type, rate' currectly ..");
            }

            // <third>
            Transformers transformer2 = new Transformers(transformerAbilitiesArraySecondTeam.get(0), types,
                overallRating);

            // <forth>
            oneTeamInstancesTwo.add(transformer2);
          }

          teamB.add(oneTeamInstancesTwo);

        }
      }
      // [ teamA, teamB ]( teamAandB.get(0) => teamA, teamAandB.get(1) => teamB )
      teamAandB.add(teamA);
      teamAandB.add(teamB);

    } catch (RequiredElementsDefected | TypeNotFoundExceptions | FileNotFoundException | BattleNotOccursExceptions e) {
      e.printStackTrace();
    }

    return teamAandB;

  }

}
