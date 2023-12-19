package br.com.cleilsonandrade.dscommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cleilsonandrade.dscommerce.entities.User;
import br.com.cleilsonandrade.dscommerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {
  @Autowired
  private UserService userService;

  public void validateSelfOrAdmin(Long userId) {
    User me = userService.authenticated();

    if (!me.hasRole("ROLE_ADMIN") && !me.getId().equals(userId)) {
      throw new ForbiddenException("Access denied");
    }
  }
}
