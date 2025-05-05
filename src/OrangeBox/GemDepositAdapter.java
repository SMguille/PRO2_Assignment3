package OrangeBox;

import OrangeBox.MyArrayList.ListADT;
import OrangeBox.MyArrayList.MyArrayList;

public class GemDepositAdapter implements Buffer{

  private final ListADT<Gem> queue;  // The “adaptee”
  private final int min, max;
  private int value = 0;

  public GemDepositAdapter(int min, int max) {
    this.min = min;
    this.max = max;
    this.queue = new MyArrayList<Gem>();
  }

  public synchronized void putGem(Gem gem) {
    while (value >= max) {
      try {wait(); }
      catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
    }
    queue.add(gem);
    value++;
    if (value == min + 1) {
      notifyAll();
    }
  }

  public synchronized Gem takeGem() {
    while (value <= min) {
      try {wait(); }
      catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
    }
    // remove from the front of list
    Gem g = queue.remove(0);
    value--;

    if (value == max - 1) {
      notifyAll();
    }
    return g;
  }

  public synchronized int totalValue() {
    return value;
  }
}
