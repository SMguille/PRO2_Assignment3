package RedBox;

import java.util.ArrayList;
import java.util.List;

import GreenBox.Catalogue;
import OrangeBox.Gem;

public class Accountant implements Runnable
{
  private final TreasureRoomDoor treasureRoomDoor;
  private List<Gem> gems;

  public Accountant(TreasureRoomDoor treasureRoomGuard){
    this.treasureRoomDoor = treasureRoomGuard;
    this.gems = new ArrayList<>();
  }
  @Override public void run()
  {
    try {Thread.sleep(2500);}
    catch (InterruptedException e) {throw new RuntimeException(e);}

    while(true){
      int gemsWorth = 0;
      //Acquire read access
      treasureRoomDoor.acquireReadAccess("Accountant");
      gems = treasureRoomDoor.lookAtAllGems();
      for(Gem gem : gems){
        gemsWorth = gemsWorth + gem.getValue();
      }
      try {Thread.sleep(1000);}
      catch (InterruptedException e) {throw new RuntimeException(e);}
      //Release read access
      treasureRoomDoor.releaseReadAccess("Accountant");
      Catalogue.getInstance().log("Total worth of gems in the treasure room: " + gemsWorth);
      try {Thread.sleep(2000);}
      catch (InterruptedException e) {throw new RuntimeException(e);}
    }
  }
}
