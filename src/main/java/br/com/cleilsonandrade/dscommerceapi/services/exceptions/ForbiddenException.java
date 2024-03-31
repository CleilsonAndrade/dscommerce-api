package br.com.cleilsonandrade.dscommerceapi.services.exceptions;

public class ForbiddenException extends RuntimeException {
  public ForbiddenException(String msg) {
    super(msg);
  }
}
