package casemodules4.service.impl;

import casemodules4.model.LikePost;
import casemodules4.repository.ILikePostRepository;
import casemodules4.service.ILikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class LikePostServiceImpl implements ILikePostService {

    @Autowired
    private ILikePostRepository likePostRepository;

    @Override
    public Integer countByPost_IdPost(Long idPost) {
        return likePostRepository.countByPost_IdPost(idPost);
    }

    @Override
    public LikePost findLikeByPostIdAndLikeId(Long idPost, Long idLike) {
        return likePostRepository.findLikeByPostIdAndLikeId(idPost, idLike);
    }

    @Override
    public void deleteLikePost(LikePost likePost) {
        likePostRepository.delete(likePost);
    }

    @Override
    public LikePost save(LikePost likePost) {
        return likePostRepository.save(likePost);
    }
}
