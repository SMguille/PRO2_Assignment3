package RedBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import GreenBox.Catalogue;
import OrangeBox.Gem;

public class King implements Runnable
{
  private final TreasureRoomDoor treasureRoomDoor;

  public King(TreasureRoomDoor treasureRoomGuard)
  {
    this.treasureRoomDoor = treasureRoomGuard;
  }

  @Override public void run()
  {
    try {Thread.sleep(5000);}
    catch (InterruptedException e) {throw new RuntimeException(e);}
    Random random = new Random();
    while(true){
      int partyCost = random.nextInt(301) + 100;
      //acquire write access
      treasureRoomDoor.acquireWriteAccess("King");

      int gemsTakenValue = 0;
      List<Gem> gemsTaken = new ArrayList<>();
      Gem gem;
      while (gemsTakenValue < partyCost && (gem = treasureRoomDoor.retrieveValuable()) != null){
        gemsTakenValue = gemsTakenValue + gem.getValue();
        gemsTaken.add(gem);
      }

      if (gemsTakenValue < partyCost){
        Catalogue.getInstance().log("\u001B[31mKing: Party cancelled (needed " + partyCost + ", got " + gemsTakenValue + ")\u001B[0m");
        for (Gem g : gemsTaken)
        {
          treasureRoomDoor.addValuable(g);
          try {Thread.sleep(100);}
          catch (InterruptedException e) {throw new RuntimeException(e);}
        }
      }
      else {
        Catalogue.getInstance().log("\u001B[32mKing: Party started!\u001B[0m");
      }
      //Release write access
      treasureRoomDoor.releaseWriteAccess("King");
      try {Thread.sleep(3000);}
      catch (InterruptedException e){throw new RuntimeException(e);}
    }
  }
}
