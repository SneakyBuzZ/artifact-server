package tech.sneakybuzz.artifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sneakybuzz.artifact.model.entities.AccountEntity;
import tech.sneakybuzz.artifact.model.entities.UserEntity;

public interface AccountRepository extends JpaRepository<AccountEntity ,Long> {
  AccountEntity findByUser(UserEntity user);
}
