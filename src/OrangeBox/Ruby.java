package OrangeBox;

public class Ruby implements Gem
{
  private final int value;

  public Ruby(){
    this.value = 5;
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
