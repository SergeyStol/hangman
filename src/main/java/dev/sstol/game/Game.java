package dev.sstol.game;

import dev.sstol.ui.CliInputOutput;
import dev.sstol.ui.Painter;
import dev.sstol.wordsstore.CurrentWordService;

import java.util.regex.Pattern;

/**
 * @author Sergey Stol
 * 2024-07-14
 */
public class Game {
   private final CurrentWordService currentWordService;
   private final CliInputOutput inputOutput;
   private final Painter painter;
   private final GameState gameState;

   public Game(CurrentWordService currentWordService,
               CliInputOutput inputOutput,
               Painter painter,
               GameState gameState) {
      this.currentWordService = currentWordService;
      this.inputOutput = inputOutput;
      this.painter = painter;
      this.gameState = gameState;
   }

   public void run() {
      boolean quit = false;
      while (!quit) {
         this.gameState.reset();
         inputOutput.print("I made a word, try to guess it!");
         currentWordService.nextRandomWord();
         inputOutput.print(currentWordService.getOpenWord());
         inputOutput.print(currentWordService.getMaskedWord());

         lap();

         quit = inputOutput.yesNoQuestion("Do you want to quit? (y/N)",
           Pattern.compile("y|yes", Pattern.CASE_INSENSITIVE));
      }
      inputOutput.print("Bye!");
   }

   private void lap() {
      while (gameState.inProgress()) {
         String letter = inputOutput.getLetter("Enter your letter: ", "Wrong input! Please, enter just one letter and press enter: ");
         if (!currentWordService.unmaskLetters(letter)) {
            gameState.lostLife();
            if (gameState.isLoose()) {
               inputOutput.print("The right word is '" + currentWordService.getOpenWord() + "'");
               inputOutput.print("You loose");
            }
            painter.drawHangman(gameState.getAmountLostLives());
         }

         if (currentWordService.isWordUnmasked()) {
            gameState.setWinner();
            inputOutput.print("The right word is '" + currentWordService.getOpenWord() + "'");
            inputOutput.print("You win");
         }

         if (gameState.inProgress()) {
            inputOutput.print(currentWordService.getMaskedWord());
         }
      }
   }
}
