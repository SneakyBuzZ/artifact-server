package tech.sneakybuzz.artifact.services;

import java.util.UUID;

public interface MailService {
  void sendVerificationMail(String email, String name, String token);
}
