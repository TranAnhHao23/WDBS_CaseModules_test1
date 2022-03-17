package casemodules4.service;

import casemodules4.model.LikeComment;

import java.util.Optional;

public interface ILikeCommentService extends IGeneralService<LikeComment> {
    Optional<LikeComment> findByIdCommentAndIdUser (Long idComment, Long idUser);

    Long countLikeCommentByIdComment(Long idPost);

    void deleteAllByIdComment(Long idPost);
}
