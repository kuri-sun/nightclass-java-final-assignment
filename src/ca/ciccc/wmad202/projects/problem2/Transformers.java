package ca.ciccc.wmad202.projects.problem2;

import java.util.ArrayList;

public class Transformers implements Comparable<Transformers> {

  // store each indentity.
  public String name;
  public Type type;
  public ArrayList<Integer> parameters;

  // contruct by input data.
  public Transformers(String name, Type type, ArrayList<Integer> parameters) {
    this.name = name;
    this.type = type;
    this.parameters = parameters;
  }

  public Integer getOverrallRate() {
    Integer overAllRate = 0;
    for (Integer rate : this.parameters) {
      overAllRate = overAllRate + rate;
    }
    return overAllRate;
  }

  public Integer getRank() {
    return this.parameters.get(4);
  }

  public Integer getCourage() {
    return this.parameters.get(5);
  }

  public Integer getSkils() {
    return this.parameters.get(7);
  }

  public Integer getStrength() {
    return this.parameters.get(0);
  }

  public String getName() {
    return this.name;
  }

  @Override
  public int compareTo(Transformers others) {

    // compare to others.
    if (this.getOverrallRate() > others.getOverrallRate()) {
      return 1;
    } else if (this.getOverrallRate() < others.getOverrallRate()) {
      return -1;
    } else {
      return 0;
    }
  }

  @Override
  public String toString() {
    return ("(" + this.type + ")" + this.name);
  }

}
