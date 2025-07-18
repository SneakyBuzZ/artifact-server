package tech.sneakybuzz.artifact.services;


public interface AccountService {
  void sendVerificationEmail(String email);
  void verifyAccount(String token);
}
