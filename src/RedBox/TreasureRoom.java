package RedBox;

import java.util.ArrayList;
import java.util.List;
import GreenBox.Catalogue;
import OrangeBox.Gem;

public class TreasureRoom implements TreasureRoomDoor {
  private List<Gem> gems;

  public TreasureRoom() {
    gems = new ArrayList<>();
  }

  public synchronized void acquireReadAccess(String actorName) {
    Catalogue.getInstance().log("\u001B[33mThe reader: " + actorName + " entered\u001B[0m");
  }

  public synchronized void acquireWriteAccess(String actorName) {
    Catalogue.getInstance().log("\u001B[33mThe writer: " + actorName + " entered\u001B[0m");
  }

  public synchronized void releaseReadAccess(String actorName) {
    Catalogue.getInstance().log("\u001B[34mThe reader: " + actorName + " left\u001B[0m");
  }

  public synchronized void releaseWriteAccess(String actorName) {
    Catalogue.getInstance().log("\u001B[34mThe writer: " + actorName + " left\u001B[0m");
  }

  public Gem retrieveValuable() {
    Gem v = null;
    if (gems.size() > 0) {
      v = gems.remove(0);
    }
    return v;
  }

  public void addValuable(Gem v) {
    gems.add(v);
  }

  @Override
  public List<Gem> lookAtAllGems() {
    return new ArrayList<>(gems);
  }
}