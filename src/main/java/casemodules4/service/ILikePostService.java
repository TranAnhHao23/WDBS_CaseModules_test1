package casemodules4.service;

import casemodules4.model.LikePost;
import org.springframework.data.repository.query.Param;

public interface ILikePostService {

    Integer countByPost_IdPost(Long idPost);

    LikePost findLikeByPostIdAndLikeId( Long idPost, Long idLike);

    void deleteLikePost(LikePost likePost);

    LikePost save(LikePost likePost);
}
