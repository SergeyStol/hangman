package dev.sstol.wordsstore;

import dev.sstol.common.RandomGeneratorNumber;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class WordsStoreService implements RandomWordsProvider {
   private final String[] words;
   private final Set<String> alreadyUsedWords = new HashSet<>();
   private RandomGeneratorNumber randomGeneratorNumber = new RandomGeneratorNumber();

   public WordsStoreService(String[] words, RandomGeneratorNumber randomGeneratorNumber) {
      this.words = words;
      this.randomGeneratorNumber = randomGeneratorNumber;
   }

   @Override
   public String nextRandomWord() {
      String result;
      while (true) {
         int randomNumber = randomGeneratorNumber.getNextInt(words.length);
         result = words[randomNumber];
         if (alreadyUsedWords.contains(result)) {
            continue;
         }
         break;
      }
      alreadyUsedWords.add(result);
      return result;
   }
}
