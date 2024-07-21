package dev.sstol.wordsstore;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
@FunctionalInterface
public interface RandomWordsProvider {
   String nextRandomWord();
}
