package OrangeBox;

public interface Buffer
{
  void putGem(Gem g);
  Gem takeGem();

  int totalValue();
}
