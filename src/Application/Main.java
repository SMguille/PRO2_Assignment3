package Application;

import RedBox.Accountant;
import RedBox.King;
import RedBox.TreasureRoomDoor;
import RedBox.TreasureRoomGuard;
import OrangeBox.*;
import GreenBox.*;

public class Main
{
  public static void main(String[] args)
  {//--Catalogue logs
    GemDeposit gemDeposit = new GemDeposit(0, 10);
    GemMineWorker gemMineWorker0 = new GemMineWorker(gemDeposit); //MineFast is the default Strategy
    GemMineWorker gemMineWorker1 = new GemMineWorker(gemDeposit);
    GemMineWorker gemMineWorker2 = new GemMineWorker(gemDeposit);
    gemMineWorker1.setCurrentStrategy(new MineSlowStrategy());
    gemMineWorker2.setCurrentStrategy(new MineGoldNuggetStrategy());

    TreasureRoomDoor treasureRoomGuard = new TreasureRoomGuard();
    GemTransporter gemTransporter0 = new GemTransporter(gemDeposit, treasureRoomGuard);
    GemTransporter gemTransporter1 = new GemTransporter(gemDeposit, treasureRoomGuard);
    GemTransporter gemTransporter2 = new GemTransporter(gemDeposit, treasureRoomGuard);

    Thread worker0 = new Thread(gemMineWorker0);
    Thread worker1 = new Thread(gemMineWorker1);
    Thread worker2 = new Thread(gemMineWorker2);
    Thread transporter0 = new Thread(gemTransporter0);
    //Thread transporter1 = new Thread(gemTransporter1);
   // Thread transporter2 = new Thread(gemTransporter2);


    King king = new King(treasureRoomGuard);
    Accountant accountant = new Accountant(treasureRoomGuard);

    Thread kingThread = new Thread(king);
    Thread accountantThread = new Thread(accountant);

    worker0.setDaemon(false);
    worker1.setDaemon(false);
    worker2.setDaemon(false);
    transporter0.setDaemon(false);
    // transporter1.setDaemon(false);
    //transporter2.setDaemon(false);
    kingThread.setDaemon(false);
    accountantThread.setDaemon(false);

    worker0.start();
    worker1.start();
    worker2.start();
    transporter0.start();
   // transporter1.start();
   // transporter2.start();
    kingThread.start();
    accountantThread.start();
  }
}
