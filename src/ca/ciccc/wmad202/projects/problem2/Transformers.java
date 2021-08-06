package ca.ciccc.wmad202.projects.problem2;

import java.util.ArrayList;

public class Transformers implements Comparable<Type> {

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

  @Override
  public int compareTo(Type E) {
    return 0;
  }

}
