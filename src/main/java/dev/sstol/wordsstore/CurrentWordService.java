package dev.sstol.wordsstore;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
public class CurrentWordService {
   private final WordsService wordsService;
   private String word;
   private String maskedWord;
   private StringBuilder prevLetters = new StringBuilder();

   public CurrentWordService(WordsService wordsService) {
      this.wordsService = wordsService;
   }

   public void nextRandomWord() {
      this.word = wordsService.getRandomWord();
      this.maskedWord = convertToMaskedWord(this.word);
      this.prevLetters = new StringBuilder();
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
