package OrangeBox;

public class Jewel implements Gem
{
  private final int value;

  public Jewel(){
    this.value = 8;
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
