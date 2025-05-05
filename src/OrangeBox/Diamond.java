package OrangeBox;

public class Diamond implements Gem
{
  private final int value;

  public Diamond(){
    this.value = 15;
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
