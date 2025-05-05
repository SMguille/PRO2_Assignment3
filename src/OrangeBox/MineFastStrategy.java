package OrangeBox;

import java.util.ArrayList;
import java.util.Random;

public class MineFastStrategy implements MiningStrategy
{
  @Override
  public ArrayList<Gem> mine()
  {
    ArrayList<Gem> gems = new ArrayList<>();
    Random random = new Random();
    int numberOfGems = random.nextInt(6) + 5;

    for (int i = 0; i < numberOfGems; i++) {
      int value = random.nextInt(100) + 1;
      if (value <= 5) {
        gems.add(GemMine.getInstance("Diamond"));
      } else if (value <= 15) {
        gems.add(GemMine.getInstance("GoldNugget"));
      } else if (value <= 30) {
        gems.add(GemMine.getInstance("Jewel"));
      } else if (value <= 50) {
        gems.add(GemMine.getInstance("Ruby"));
      } else if (value <= 75) {
        gems.add(GemMine.getInstance("WoodenCoin"));
      } else {
        gems.add(GemMine.getInstance("Coal"));
      }
      try
      {
        Thread.sleep(200);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }

    return gems;
  }
}