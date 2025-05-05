package OrangeBox;

import java.util.ArrayList;
import java.util.Random;

public class MineGoldNuggetStrategy implements MiningStrategy
{
  @Override
  public ArrayList<Gem> mine()
  {
    ArrayList<Gem> goldNuggets = new ArrayList<>();
    Random random = new Random();
    int numberOfGems = random.nextInt(6) + 3;

    for (int i = 0; i < numberOfGems; i++) {
      goldNuggets.add(GemMine.getInstance("GoldNugget"));
      try
      {
        Thread.sleep(1500);
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }

    return goldNuggets;
  }
}