package OrangeBox;

public class WoodenCoin implements Gem
{
  private final int value;

  public WoodenCoin(){
    this.value = 2;
  }

  @Override public String getName()
  {
    return Gem.super.getName();
  }

  @Override public int getValue()
  {
    return value;
  }

}
