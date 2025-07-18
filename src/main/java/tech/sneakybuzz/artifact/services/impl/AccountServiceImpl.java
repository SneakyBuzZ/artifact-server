package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.dto.requests.VerifyAccountRequest;
import tech.sneakybuzz.artifact.services.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  @Override
  public void sendVerificationEmail(String email) {

  }

  @Override
  public void verifyAccount(VerifyAccountRequest request) {

  }
}
