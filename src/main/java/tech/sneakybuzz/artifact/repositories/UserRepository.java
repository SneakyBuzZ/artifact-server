package tech.sneakybuzz.artifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sneakybuzz.artifact.model.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);
  Boolean existsByEmail(String email);
}
