package dev.sstol.wordsstore;

import dev.sstol.httpclient.WordsHttpClient;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class WordsRepository {
   private WordsHttpClient wordsHttpClient;
   private final String[] words;

   public WordsRepository(WordsHttpClient wordsHttpClient) {
      this.words = wordsHttpClient.fetch();
   }

   public String[] getWords() {
      return words;
   }
}
