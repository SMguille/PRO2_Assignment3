package OrangeBox;

public class GoldNugget implements Gem
{
  private final int value;

  public GoldNugget(){
    this.value = 10;
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

