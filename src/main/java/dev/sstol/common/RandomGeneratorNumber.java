package dev.sstol.common;

import java.util.Random;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class RandomGeneratorNumber {

   private final Random random = new Random();

   public int getNextInt(int max) {
      return random.nextInt(max);
   }
}
