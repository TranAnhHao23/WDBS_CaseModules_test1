package casemodules4.repository;

import casemodules4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findAllByFullNameContaining(String name);
}
