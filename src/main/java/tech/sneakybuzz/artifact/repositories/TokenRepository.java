package tech.sneakybuzz.artifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sneakybuzz.artifact.model.entities.TokenEntity;

public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
}
