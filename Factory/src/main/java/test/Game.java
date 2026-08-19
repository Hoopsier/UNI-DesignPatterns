package test;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
abstract class Tile {
  String getType() {
    return "Character";
  }

  char getCharacter() {
    return 'x';
  }

  abstract void action();
}

class SwampTile extends Tile {
  public void action() {
    System.out.println("Aasdsadsa");
  }

  @Override
  String getType() {
    return "swamp";
  }

  @Override
  char getCharacter() {
    return 'S';
  }
}

class RoadTile extends Tile {
  public void action() {
    System.out.println("Basdsadsa");
  }

  @Override
  String getType() {
    return "road";
  }

  @Override
  char getCharacter() {
    return 'R';
  }
}

class BuildingTile extends Tile {
  public void action() {
    System.out.println("Casdsadsa");
  }

  @Override
  String getType() {
    return "building";
  }

  @Override
  char getCharacter() {
    return 'B';
  }
}

class WaterTile extends Tile {
  public void action() {
    System.out.println("Dasdsadsa");
  }

  @Override
  String getType() {
    return "water";
  }

  @Override
  char getCharacter() {
    return 'W';
  }
}

class ForestTile extends Tile {
  public void action() {
    System.out.println("Easdsadsa");
  }

  @Override
  String getType() {
    return "forest";
  }

  @Override
  char getCharacter() {
    return 'F';
  }
}

abstract class Map {
  Random rnd = new Random();

  abstract Tile createTile();

  void display() {
    System.out.println("");
  }
}

class CityMap extends Map {
  Tile createTile() {
    int r = rnd.nextInt(0, 3);
    switch (r) {
      case 0:
        return new ForestTile();
      case 1:
        return new BuildingTile();
      case 2:
        return new RoadTile();
      default:
        return new RoadTile();
    }
  }
}

class WildernessMap extends Map {
  Tile createTile() {
    int r = rnd.nextInt(0, 3);
    switch (r) {
      case 0:
        return new ForestTile();
      case 1:
        return new SwampTile();
      case 2:
        return new WaterTile();
      default:
        return new WaterTile();
    }
  }
}

public class Game {
  public static void main(String[] args) {
    Map creator = null;
    Scanner scanner = new Scanner(System.in);
    Integer choice = -1;
    System.out.print("0 or 1:");
    try {
      choice = Integer.parseInt(scanner.nextLine());
    } catch (Exception e) {
      System.out.println(e);
    }
    scanner.close();
    if (choice == 0) {
      creator = new CityMap();
    } else if (choice == 1) {
      creator = new WildernessMap();
    }

    if (creator == null)
      return;
    createMap(creator);
  }

  static void createMap(Map map) {
    char[][] matrix = new char[4][4];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = map.createTile().getCharacter();
      }
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j]);
      }
      System.out.println("");
    }
  }
}
