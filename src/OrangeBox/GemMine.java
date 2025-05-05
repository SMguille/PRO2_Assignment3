package OrangeBox;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GemMine
{
  private static Map<String, Gem> gems = new HashMap<>(); //Gem instances
  private static final Lock lock = new ReentrantLock();

  static {
    gems.put("Diamond", new Diamond());
    gems.put("GoldNugget", new GoldNugget());
    gems.put("Jewel", new Jewel());
    gems.put("Ruby", new Ruby());
    gems.put("WoodenCoin", new WoodenCoin());
    gems.put("Coal", new Coal());
  }

  public static Gem getInstance(String key){
    if (!gems.containsKey(key)){
      synchronized (lock){
        if (!gems.containsKey(key)){
          throw new NonExistingGemTypeError(key + " gem type doesn't exist");
        }
      }
    }
    return gems.get(key);
  }
}
