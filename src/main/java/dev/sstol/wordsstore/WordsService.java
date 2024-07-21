package dev.sstol.wordsstore;

import dev.sstol.common.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Stol
 * 2024-07-21
 */
public class WordsService {
   private final WordsRepository wordsRepository;
   private final List<String> alreadyUsedWords = new ArrayList<>();
   private final RandomNumberGenerator random;

   public WordsService(WordsRepository wordsRepository,
                       RandomNumberGenerator random) {
      this.wordsRepository = wordsRepository;
      this.random = random;
   }

   String getRandomWord() {
      String newRandomWord;
      String[] allWords = wordsRepository.getWords();
      while (true) {
         newRandomWord = allWords[random.getNextInt(allWords.length)];
         if (!alreadyUsedWords.contains(newRandomWord)) {
            alreadyUsedWords.add(newRandomWord);
            break;
         }
      }
      return newRandomWord;
   }
}
