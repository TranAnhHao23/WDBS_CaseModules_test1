package casemodules4.repository;

import casemodules4.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ICommentRepository extends JpaRepository<Comment, Long> {
}
