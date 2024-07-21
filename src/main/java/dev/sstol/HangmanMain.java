package dev.sstol;

import dev.sstol.common.RandomNumberGenerator;
import dev.sstol.common.RandomNumberGeneratorDefault;
import dev.sstol.game.Game;
import dev.sstol.game.GameState;
import dev.sstol.httpclient.WordsHttpClient;
import dev.sstol.ui.CliInputOutput;
import dev.sstol.ui.Painter;
import dev.sstol.wordsstore.CurrentWordService;
import dev.sstol.wordsstore.WordsRepository;
import dev.sstol.wordsstore.WordsService;

import java.util.Random;
import java.util.Scanner;

public class HangmanMain {

   private static final int STARTED_LIVES = 6;

   public static void main(String[] args) {
      var scanner = new Scanner(System.in);
      var inputOutput = new CliInputOutput(scanner);
      var gameState = new GameState(STARTED_LIVES);
      var painter = new Painter();
      CurrentWordService currentWordService = getWordContainer();
      var game = new Game(currentWordService, inputOutput, painter, gameState);
      game.run();
   }

   private static CurrentWordService getWordContainer() {
      var wordsHttpClient = new WordsHttpClient(line -> line.length() > 4 && line.length() < 7);
      var wordsRepository = new WordsRepository(wordsHttpClient);
      var random = new Random();
      RandomNumberGenerator randomNumberGenerator = new RandomNumberGeneratorDefault(random);
      var wordsService = new WordsService(wordsRepository, randomNumberGenerator);

      return new CurrentWordService(wordsService);
   }
}