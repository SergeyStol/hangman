package dev.sstol.ui;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
public class Painter {
   public void drawHangman(int stage) {
      switch (stage) {
         case 0:
            System.out.println(" +---+");
            System.out.println("     |");
            System.out.println("     |");
            System.out.println("     |");
            System.out.println("    ===");
            break;
         case 1:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println("     |");
            System.out.println("     |");
            System.out.println("    ===");
            break;
         case 2:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println(" |   |");
            System.out.println("     |");
            System.out.println("    ===");
            break;
         case 3:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println("/|   |");
            System.out.println("     |");
            System.out.println("    ===");
            break;
         case 4:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println("/|\\  |");
            System.out.println("     |");
            System.out.println("    ===");
            break;
         case 5:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println("/|\\  |");
            System.out.println("/    |");
            System.out.println("    ===");
            break;
         case 6:
            System.out.println(" +---+");
            System.out.println(" O   |");
            System.out.println("/|\\  |");
            System.out.println("/ \\  |");
            System.out.println("    ===");
            break;
         default:
            System.out.println("Invalid stage");
            break;
      }
   }
}
