package application;

import java.util.Random;

public class HiByeGame extends Game {
  int playerCount;
  Random rnd = new Random();

  public void initializeGame(int numberOfPlayers) {
    playerCount = numberOfPlayers;
  }

  public boolean endOfGame() {
    return playerCount < 2;
  }

  public void playSingleTurn(int player) {
    System.out.println("Player " + player + " says Hi!");
    if (rnd.nextInt(1, 3) == 1) {
      System.out.println("A player left ;-;");
      playerCount = playerCount - 1;
    }
  }

  public void displayWinner() {
    System.out.println("there is no winner without players ;-; (Game End)");
  }

}
