package br.com.cleilsonandrade.dscommerceapi.services.exceptions;

public class DatabaseException extends RuntimeException {
  public DatabaseException(String msg) {
    super(msg);
  }

}