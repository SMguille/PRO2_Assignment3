package OrangeBox;

public class NonExistingGemTypeError extends RuntimeException
{
  public NonExistingGemTypeError(String message)
  {
    super(message);
  }
}
