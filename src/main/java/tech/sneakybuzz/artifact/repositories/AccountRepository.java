package tech.sneakybuzz.artifact.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.sneakybuzz.artifact.model.entities.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity ,Long> {
}
