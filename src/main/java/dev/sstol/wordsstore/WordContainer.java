package dev.sstol.wordsstore;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
public class WordContainer implements RandomWordsProvider {
   private final RandomWordsProvider randomWordsProvider;
   private String word;
   private String maskedWord;
   private StringBuilder prevLetters = new StringBuilder();

   public WordContainer(RandomWordsProvider randomWordsProvider) {
      this.randomWordsProvider = randomWordsProvider;
   }

   @Override
   public String nextRandomWord() {
      this.word = randomWordsProvider.nextRandomWord();
      this.maskedWord = convertToMaskedWord(this.word);
      this.prevLetters = new StringBuilder();
      return this.word;
   }

   private String convertToMaskedWord(String word, String prevLetters) {
      return word.replaceAll(prevLetters == null ? "." : "[^" + prevLetters + "]",
        "*");
   }

   private String convertToMaskedWord(String word) {
      return convertToMaskedWord(word, null);
   }

   public String getOpenWord() {
      return this.word;
   }

   public String getMaskedWord() {
      return this.maskedWord;
   }

   public boolean isWordUnmasked() {
      return word.equals(maskedWord);
   }

   public boolean unmaskLetters(String letter) {
      prevLetters.append(letter);
      String newMaskedWord = convertToMaskedWord(word, prevLetters.toString());
      boolean contains = !newMaskedWord.equals(maskedWord);
      maskedWord = newMaskedWord;
      return contains;
   }
}
