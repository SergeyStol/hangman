package dev.sstol;

import dev.sstol.common.RandomGeneratorNumber;
import dev.sstol.game.Game;
import dev.sstol.game.GameState;
import dev.sstol.httpclient.HttpClientService;
import dev.sstol.ui.CliInputOutput;
import dev.sstol.ui.Painter;
import dev.sstol.wordsstore.WordContainer;
import dev.sstol.wordsstore.WordsStoreService;

import java.util.Scanner;

public class HangmanMain {

   private static final int STARTED_LIVES = 6;

   public static void main(String[] args) {
      var scanner = new Scanner(System.in);
      var inputOutput = new CliInputOutput(scanner);
      var gameState = new GameState(STARTED_LIVES);
      var painter = new Painter();
      WordContainer wordContainer = getWordContainer();
      var game = new Game(wordContainer, inputOutput, painter, gameState);
      game.run();
   }

   private static WordContainer getWordContainer() {
      var httpClientService = new HttpClientService(line -> line.length() > 4 && line.length() < 7);
      String[] words = httpClientService.fetch();

      var randomGeneratorNumber = new RandomGeneratorNumber();
      var wordsStoreService = new WordsStoreService(words, randomGeneratorNumber);
      return new WordContainer(wordsStoreService);
   }
}