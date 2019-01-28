package network.marmoj.exception;

public class ValidationException extends RuntimeException {

  public ValidationException(String parameter) {
    super(String.format("The Parameter %s can not be null", parameter));
  }

}
