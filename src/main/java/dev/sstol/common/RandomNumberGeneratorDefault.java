package dev.sstol.common;

import java.util.Random;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class RandomNumberGeneratorDefault implements RandomNumberGenerator {

   private final Random random;

   public RandomNumberGeneratorDefault(Random random) {
      this.random = random;
   }

   public int getNextInt(int max) {
      return random.nextInt(max);
   }
}
