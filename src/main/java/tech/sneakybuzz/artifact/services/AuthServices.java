package tech.sneakybuzz.artifact.services;

import tech.sneakybuzz.artifact.dto.requests.LoginRequest;

public interface AuthServices {
  String login(LoginRequest request);
}
