package OrangeBox;


public class GemDeposit
{
  private final Buffer adapter;

  public GemDeposit(int min, int max){
    this.adapter = new GemDepositAdapter(min, max);
  }

  public void saveGem(Gem gem){
    adapter.putGem(gem);
  }
  public Gem takeGem(){
    return adapter.takeGem();
  }
  public int totalValue() {
    return adapter.totalValue();
  }

}
