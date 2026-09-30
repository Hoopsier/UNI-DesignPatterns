package application;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    List<Integer> data1 = new LinkedList<>();
    Random rnd = new Random();
    for (int i = 0; i < 1500; i++) {
      data1.add(rnd.nextInt(0, 1000000));
    }
    List<Integer> data2 = new LinkedList<>();
    for (int i = 0; i < 500; i++) {
      data2.add(rnd.nextInt(0, 1000000000));
    }
    Algorithm algorithm = new QuickSort();
    runAlgorithm(data1, algorithm);
    runAlgorithm(data2, algorithm);
    // for (Integer integer : data2) {
    //
    // System.out.printf("%d, ", integer);
    // }
  }

  private static void runAlgorithm(List<Integer> data, Algorithm algorithm) {
    for (int i = 0; i < 3; i++) {
      long start = System.currentTimeMillis();
      switch (i) {
        case 1:
          algorithm = new BubbleSort();
          break;
        case 2:
          algorithm = new CycleSort();
          break;
      }
      algorithm.Sorted(data);
      long end = System.currentTimeMillis();
      System.out.printf("Algorithm %d with data: %d ms\n", i + 1, end - start);
    }

  }
}

interface Algorithm {
  void Sorted(List<Integer> data);
}

class CycleSort implements Algorithm {
  public void Sorted(List<Integer> data) {
    int n = data.size();

    // traverse array elements and put it to on
    // the right place
    for (int cycle_start = 0; cycle_start <= n - 2; cycle_start++) {

      // initialize item as starting point
      int item = data.get(cycle_start);

      // Find position where we put the item. We
      // basically count all smaller elements on right
      // side of item.
      int pos = cycle_start;
      for (int i = cycle_start + 1; i < n; i++)
        if (data.get(i) < item)
          pos++;

      // If item is already in correct position
      if (pos == cycle_start)
        continue;

      // ignore all duplicate elements
      while (item == data.get(pos))
        pos += 1;

      // put the item to it's right position
      if (pos != cycle_start) {
        // Swapper.swap(data, cycle_start, pos);
        int temp = item;
        item = data.get(pos);
        data.set(pos, temp);
      }

      // Rotate rest of the cycle
      while (pos != cycle_start) {
        pos = cycle_start;

        // Find position where we put the element
        for (int i = cycle_start + 1; i < n; i++)
          if (data.get(i) < item)
            pos += 1;

        // ignore all duplicate elements
        while (item == data.get(pos))
          pos += 1;

        // put the item to it's right position
        if (item != data.get(pos)) {
          Swapper.swap(data, cycle_start, pos);
          int temp = item;
          item = data.get(pos);
          data.set(pos, temp);
        }
      }
    }
  }
}

class BubbleSort implements Algorithm {
  public void Sorted(List<Integer> data) {
    final int n = data.size();
    int i, j;

    boolean swapped;
    for (i = 0; i < n - 1; i++) {
      swapped = false;
      for (j = 0; j < n - i - 1; j++) {
        if (data.get(j) > data.get(j + 1)) {
          Swapper.swap(data, j, j + 1);
          swapped = true;
        }
      }
      if (!swapped) {
        break;
      }
    }
  }
}

class QuickSort implements Algorithm {
  public void Sorted(List<Integer> data) {
    final int size = data.size();
    Sort(data, 0, size - 1);
  }

  private int partition(List<Integer> data, int low, int high) {
    double pivot = data.get(high);
    int i = low - 1;

    for (int j = low; j <= high - 1; j++) {
      if (data.get(j) < pivot) {
        i++;

        Swapper.swap(data, i, j);
      }
    }

    Swapper.swap(data, i + 1, high);

    return i + 1;
  }

  private void Sort(List<Integer> data, int low, int high) {
    if (low < high) {
      int pi = partition(data, low, high);

      Sort(data, low, pi - 1);
      Sort(data, pi + 1, high);
    }
    return;
  }
}

class Swapper {
  public static void swap(List<Integer> list, int first, int second) {
    list.set(first, list.get(first) ^ list.get(second));
    list.set(second, list.get(first) ^ list.get(second));
    list.set(first, list.get(first) ^ list.get(second));
  }

}
