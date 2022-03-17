package casemodules4.repository;

import casemodules4.model.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface ILikeCommentRepository extends JpaRepository<LikeComment, Long> {
//    Optional<LikeComment> findByComment_IdCommentAndIdLikeComment(Long idComment, Long idUser);
//
//    Long countLikeCommentByIdComment(Long idPost);
//
//    void deleteAllByIdComment(Long idPost);
}
