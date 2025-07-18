package tech.sneakybuzz.artifact.services;

import tech.sneakybuzz.artifact.dto.requests.RegisterRequest;

public interface UserService {
  void registerUser(RegisterRequest request);
}
