package br.com.cleilsonandrade.dscommerce.services.exceptions;

public class DatabaseException extends RuntimeException {
  public DatabaseException(String msg) {
    super(msg);
  }

}