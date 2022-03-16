package casemodules4.service;

import casemodules4.model.LikeComment;

import java.util.Optional;

public interface ILikeCommentService extends IGeneralService<LikeComment> {
    Optional<LikeComment> findById_CommentAndId_User (Long id_comment, Long id_user);

    Long countLikeCommentById_Comment(Long id_post);

    void deleteAllById_Comment(Long id_post);
}
