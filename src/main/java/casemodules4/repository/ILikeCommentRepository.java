package casemodules4.repository;

import casemodules4.model.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findById_CommentAndId_User(Long id_comment, Long id_user);

    Long countLikeCommentById_Comment(Long id_post);

    void deleteAllById_Comment(Long id_post);
}
