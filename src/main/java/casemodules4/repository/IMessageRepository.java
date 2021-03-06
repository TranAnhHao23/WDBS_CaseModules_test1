package casemodules4.repository;

import casemodules4.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IMessageRepository extends JpaRepository<Message, Long> {
}
