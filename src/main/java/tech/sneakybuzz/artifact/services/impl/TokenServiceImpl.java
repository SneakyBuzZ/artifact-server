package tech.sneakybuzz.artifact.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.sneakybuzz.artifact.model.entities.TokenEntity;
import tech.sneakybuzz.artifact.repositories.TokenRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl {
  private final TokenRepository tokenRepository;

  public void createToken(){
    TokenEntity token = TokenEntity
        .builder()
        .token(UUID.randomUUID().toString())
        .user(null)
        .build();
  }
}
