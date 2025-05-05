package OrangeBox;

import java.util.ArrayList;

public class GemMineWorker implements Runnable
{
  private GemMine gemMine;
  private final GemDeposit gemDeposit;
  private MiningStrategy currentStrategy;

  public GemMineWorker(GemDeposit gemDeposit){
    gemMine = new GemMine();
    this.gemDeposit = gemDeposit;
    this.currentStrategy = new MineFastStrategy();
  }

  public void setCurrentStrategy(MiningStrategy miningStrategy)
  {
    this.currentStrategy = miningStrategy;
  }

  @Override public void run()
  {
    while (true){
      ArrayList<Gem> gems = currentStrategy.mine();  //mine() takes multiple gems from GemMine
      for (Gem gem : gems){
        gemDeposit.saveGem(gem);
      }
      try
      {
        Thread.sleep(15); //Time between different mining iterations
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
