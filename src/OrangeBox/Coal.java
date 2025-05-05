package OrangeBox;

public class Coal implements Gem
{
  private final int value;

  public Coal(){
    this.value = 1;
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
