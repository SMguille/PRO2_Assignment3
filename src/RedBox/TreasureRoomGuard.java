package RedBox;

import OrangeBox.Gem;
import GreenBox.Catalogue;

import java.util.List;

public class TreasureRoomGuard implements TreasureRoomDoor
{         // READER PREFERENCE
  private final TreasureRoom treasureRoom;

  private int i;
  private int readers = 0;
  private int writers = 0;
  private int  waitingReaders= 0;

  public TreasureRoomGuard(){
    this.treasureRoom = new TreasureRoom();
  }
  @Override public synchronized void acquireReadAccess(String actorName)
  {
    waitingReaders++;
    while(writers > 0){
      try
      {
        Catalogue.getInstance().log("\u001B[35m" + actorName + " waiting for reading access\u001B[0m");
        wait();
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
    waitingReaders--;
    treasureRoom.acquireReadAccess(actorName);
    readers++;
  }

  @Override public synchronized void acquireWriteAccess(String actorName)
  {
    while (readers > 0 || writers > 0 || waitingReaders > 0){
      try
      {
        Catalogue.getInstance().log("\u001B[35m" + actorName + " waiting for writing access\u001B[0m");
        wait();
      }
      catch (InterruptedException e)
      {
        throw new RuntimeException(e);
      }
    }
    treasureRoom.acquireWriteAccess(actorName);
    writers++;
  }

  @Override public synchronized void releaseReadAccess(String actorName)
  {
    treasureRoom.releaseReadAccess(actorName);
    readers--;
    if (readers == 0){
      notify();
    }
  }

  @Override public synchronized void releaseWriteAccess(String actorName)
  {
    treasureRoom.releaseWriteAccess(actorName);
    writers--;
    notifyAll();
  }

  @Override public synchronized Gem retrieveValuable()
  {
    return treasureRoom.retrieveValuable();
  }

  @Override public synchronized void addValuable(Gem v)
  {
    treasureRoom.addValuable(v);
  }

  @Override public synchronized List<Gem> lookAtAllGems()
  {
    return treasureRoom.lookAtAllGems();
  }
}
