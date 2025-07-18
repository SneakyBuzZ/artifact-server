package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.dto.requests.VerifyAccountRequest;
import tech.sneakybuzz.artifact.model.entities.AccountEntity;
import tech.sneakybuzz.artifact.model.entities.TokenEntity;
import tech.sneakybuzz.artifact.model.entities.UserEntity;
import tech.sneakybuzz.artifact.model.enums.OnBoardingStatus;
import tech.sneakybuzz.artifact.model.enums.TokenType;
import tech.sneakybuzz.artifact.repositories.AccountRepository;
import tech.sneakybuzz.artifact.repositories.TokenRepository;
import tech.sneakybuzz.artifact.repositories.UserRepository;
import tech.sneakybuzz.artifact.services.AccountService;
import tech.sneakybuzz.artifact.services.MailService;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
  private final MailService mailService;
  private final UserRepository userRepository;
  private final TokenRepository tokenRepository;
  private final AccountRepository accountRepository;

  @Override
  public void sendVerificationEmail(String email) {
    try{
      UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
      TokenEntity token = TokenEntity.builder()
          .user(user)
          .value(UUID.randomUUID().toString())
          .type(TokenType.VERIFICATION_EMAIL)
          .expiresAt(Instant.now().plusSeconds(300))
          .isUsed(false)
          .build();
      token = tokenRepository.save(token);
      mailService.sendVerificationMail(email,user.getFirstName(),token.getValue());
    }catch (UsernameNotFoundException e){
      throw new UsernameNotFoundException("User with email " + email + " not found", e);
    } catch (Exception e) {
      throw new RuntimeException("Unexpected error occurred while sending verification email", e);
    }
  }

  @Override
  public void verifyAccount(String token) {
    TokenEntity tokenEntity = tokenRepository.findByValueAndType(token, TokenType.VERIFICATION_EMAIL)
        .orElseThrow(() -> new RuntimeException("Invalid or expired verification token"));

    if (tokenEntity.isUsed()) {
      throw new RuntimeException("Token has already been used");
    }

    UserEntity user = tokenEntity.getUser();
    AccountEntity account = accountRepository.findByUser(user);
    account.setVerified(true);
    accountRepository.save(account);

    user.setOnBoardingStatus(OnBoardingStatus.EMAIL_VERIFIED_NO_ROLE);
    userRepository.save(user);

    tokenEntity.setUsed(true);
    tokenRepository.save(tokenEntity);

    log.info("Account for user {} has been verified successfully", user.getEmail());
  }
}
