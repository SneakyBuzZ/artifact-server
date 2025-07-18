package tech.sneakybuzz.artifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sneakybuzz.artifact.model.entities.TokenEntity;
import tech.sneakybuzz.artifact.model.enums.TokenType;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
  Optional<TokenEntity> findByValueAndType(String value, TokenType type);
}
