package GreenBox;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Catalogue
{
  private File logFile;
  private static Catalogue instance;
  private static Lock lock = new ReentrantLock();

  private Catalogue(){
    logFile = new File("LogFile.txt");
  }

  public static Catalogue getInstance(){
    if (instance == null){
      synchronized (lock){
        if (instance == null){
          instance = new Catalogue();
        }
      }
    }
    return instance;
  }

  public void log(String txt){
    try{
      Writer out = new BufferedWriter(new FileWriter(logFile, true));
      out.append(txt).append("\n");
      out.flush();
      out.close();
      System.out.println(txt);
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }
}
