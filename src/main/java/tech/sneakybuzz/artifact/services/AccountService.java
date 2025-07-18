package tech.sneakybuzz.artifact.services;

import tech.sneakybuzz.artifact.dto.requests.VerifyAccountRequest;

public interface AccountService {
  void sendVerificationEmail(String email);
  void verifyAccount(VerifyAccountRequest request);
}
