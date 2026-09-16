package application;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Player player = new Player(new Novice(), "Loser", 5);
    Scanner scanner = new Scanner(System.in);
    while (player.level.nextLevel != null || player.level.levelNum == 0) {
      System.out.printf("%s:\n", player.name);
      System.out.printf("Level: %d:\n", player.level.levelNum);
      System.out.printf("HP: %d:\n", player.health);
      System.out.printf("EXP: %d/%d:\n", player.experience, player.expRequirement);
      gameFlow(player, scanner);
      if (player.health <= 0) {
        System.out.println("Yikes, you died while fighting.");
        break;
      }
      if (player.level.getClass() == Master.class) {
        System.out.println("You became a master! (Game End!)");
        break;
      }
    }
  }

  public static void gameFlow(Player player, Scanner scanner) {
    if (player.level.levelNum >= 1) {
      System.out.println("1: train +exp");
    }
    if (player.level.levelNum >= 2) {
      System.out.println("2: meditate +hp");
    }
    if (player.level.levelNum == 3) {
      System.out.println("3: fight -hp +many exp");
    }
    int action = 0;

    while (true) {
      try {
        action = scanner.nextInt();

      } catch (Exception e) {
        System.out.println("not an integer.");
      }
      if (action == 1) {
        player.train();
        break;
      }
      if (action == 2) {
        player.meditate();
        break;
      }
      if (action == 3) {
        player.fight();
        break;
      }
    }

  }
}

abstract class Level {
  Level(Level _nextLevel, int _levelNum) {
    nextLevel = _nextLevel;
    levelNum = _levelNum;
  }

  protected Level nextLevel;
  protected int levelNum;

  abstract void train(Player player);

  abstract void meditate(Player player);

  abstract void fight(Player player);

  int getActions() {
    return levelNum;
  }

  Level getNextLevel() {
    return nextLevel;
  }
}

class Novice extends Level {
  Novice() {
    super(new Intermediate(), 1);
  }

  void train(Player player) {
    player.gainEXP(1);
  }

  void meditate(Player player) {
    System.out.println("can't");
  }

  void fight(Player player) {
    System.out.println("can't");
  }

}

class Intermediate extends Level {
  Intermediate() {
    super(new Expert(), 2);
  }

  void train(Player player) {
    player.gainEXP(2);
  }

  void meditate(Player player) {
    player.gainHP(1);
  }

  void fight(Player player) {
    System.out.println("can't");
  }

}

class Expert extends Level {
  Expert() {
    super(new Master(), 3);
  }

  void train(Player player) {
    player.gainEXP(3);
  }

  void meditate(Player player) {
    player.gainHP(2);
  }

  void fight(Player player) {
    player.loseHP(2);
    player.gainEXP(20);
  }

}

class Master extends Level {
  Master() {
    super(null, 0);
  }

  void train(Player player) {
    System.out.println("won't");
  }

  void meditate(Player player) {
    System.out.println("won't");
  }

  void fight(Player player) {
    System.out.println("won't");
  }

}

class Player {
  Level level;
  String name;
  int expRequirement;
  int experience = 0;
  int health = 1;

  Player(Level _level, String _name, int _expRequirement) {
    level = _level;
    name = _name;
    expRequirement = _expRequirement;
  }

  void train() {
    level.train(this);
  }

  void meditate() {
    level.meditate(this);
  }

  void fight() {
    level.fight(this);
  }

  void gainEXP(int exp) {
    if (experience + exp >= expRequirement) {
      experience = 0;
      expRequirement = expRequirement << 2;
      setState(level.getNextLevel());
    }
    experience = experience + exp;
  }

  void setState(Level level) {
    this.level = level;
  }

  boolean loseHP(int damage) {
    health = health - damage;
    return health >= 1; // false for dead
  }

  void gainHP(int amount) {
    health = health + amount;
  }
}
