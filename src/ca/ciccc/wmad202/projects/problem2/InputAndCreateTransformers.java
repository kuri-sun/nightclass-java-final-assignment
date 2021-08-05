package ca.ciccc.wmad202.projects.problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputAndCreateTransformers {

  public static ArrayList<Transformers> inputTxtFile() throws FileNotFoundException {

    File inputFile = new File("out/transformers.txt");
    Scanner in = new Scanner(inputFile);
    in.useDelimiter('*');
    while (in.hasNext()) {
      String words = in.next();
      System.out.println(words);

    }
    in.close();

    return null;

  }

  public static void main(String[] args) {

    try {
      inputTxtFile();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

}
