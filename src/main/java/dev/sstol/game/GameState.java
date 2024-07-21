package dev.sstol.game;

/**
 * @author Sergey Stol
 * 2024-07-13
 */
public class GameState {

   private final int totalLives;
   private int lives;
   private GameStatus gameStatus = GameStatus.IN_PROCESS;

   public GameState(int lives) {
      if (lives < 1) {
         throw new IllegalArgumentException("Number of lives should be one or more");
      }
      this.totalLives = lives;
      this.lives = lives;
   }

   public void lostLife() {
      if (--this.lives < 1) {
         gameStatus = GameStatus.LOOSE;
      }
   }

   public void reset() {
      lives = this.totalLives;
      gameStatus = GameStatus.IN_PROCESS;
   }

   public boolean isLoose() {
      return gameStatus == GameStatus.LOOSE;
   }

   public boolean inProgress() {
      return gameStatus == GameStatus.IN_PROCESS;
   }

   public int getAmountLostLives() {
      return totalLives - lives;
   }

   public void setWinner() {
      this.gameStatus = GameStatus.WIN;
   }
}
