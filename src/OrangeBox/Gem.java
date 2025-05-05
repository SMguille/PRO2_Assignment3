package OrangeBox;

public interface Gem
{
  default String getName(){return getClass().getSimpleName();}
  int getValue();
}