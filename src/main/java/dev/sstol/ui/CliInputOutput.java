package dev.sstol.ui;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
public class CliInputOutput {
   private final Scanner scanner;

   public CliInputOutput(Scanner scanner) {
      this.scanner = scanner;
   }

   public void print(String message) {
      System.out.println(message);
   }

   public String getLetter(String message, String wrongInputMessage) {
      var isWrongInput = true;
      String letter = null;
      while (isWrongInput) {
         this.print(message);
         letter = scanner.nextLine();
         if (letter == null || letter.length() != 1) {
            print(wrongInputMessage);
         } else {
            isWrongInput = false;
         }
      }
      return letter;
   }

   public boolean yesNoQuestion(String question, Pattern yesAnswer) {
      print(question);
      String answer = scanner.nextLine();
      return yesAnswer.matcher(answer).find();
   }
}
