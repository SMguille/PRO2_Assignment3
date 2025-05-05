package OrangeBox;

import RedBox.TreasureRoomDoor;
import GreenBox.Catalogue;

import java.util.ArrayList;
import java.util.Random;

public class GemTransporter implements Runnable
{
  private int gemsWorth = 0;
  private final GemDeposit gemDeposit;
  private ArrayList<Gem> gems;
  private final TreasureRoomDoor treasureRoomDoor;

  public GemTransporter(GemDeposit gemDeposit, TreasureRoomDoor treasureRoomDoor){
    this.gemDeposit = gemDeposit;
    gems = new ArrayList<>();
    this.treasureRoomDoor = treasureRoomDoor;
  }
  @Override public void run()
  {
    Random random = new Random();
    while (true)
    {
      int maxPickingValue = random.nextInt(251) + 50;
      while (maxPickingValue > gemsWorth){
        Gem gem = gemDeposit.takeGem();
        gems.add(gem);
        gemsWorth = gemsWorth + gem.getValue();
      }

      //Get write access
      treasureRoomDoor.acquireWriteAccess("GemTransporter");
      //Write
      for (Gem gem : gems){
        treasureRoomDoor.addValuable(gem);
      }
      //release write access
      treasureRoomDoor.releaseWriteAccess("GemTransporter");
      Catalogue.getInstance().log("Transporter added a value of " + gemsWorth + " to the Treasure Room");
      gems.clear();
      gemsWorth = 0;
      try
      {
        Thread.sleep(50); //Break time between transportations
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
  }
}
