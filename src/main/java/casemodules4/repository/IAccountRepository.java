package casemodules4.repository;

import casemodules4.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long> {
    Iterable<Account> findAllByAccount(String username);
    Optional<Account> findByAccount(String username);
    Boolean existsByAccount(String name);
    Boolean existsByEmail(String name);
}
