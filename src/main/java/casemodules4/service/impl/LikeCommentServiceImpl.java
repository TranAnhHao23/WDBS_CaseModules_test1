package casemodules4.service.impl;

import casemodules4.model.LikeComment;
import casemodules4.repository.ILikeCommentRepository;
import casemodules4.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements ILikeCommentService {
    @Autowired
    private ILikeCommentRepository iLikeCommentRepository;

    @Override
    public Iterable<LikeComment> findAll() {
        return iLikeCommentRepository.findAll();
    }

    @Override
    public Optional<LikeComment> findById_CommentAndId_User(Long id_comment, Long id_user) {
        return iLikeCommentRepository.findById_CommentAndId_User(id_comment, id_user);
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return iLikeCommentRepository.findById(id);
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return iLikeCommentRepository.save(likeComment);
    }

    @Override
    public Long countLikeCommentById_Comment(Long id_post) {
        return iLikeCommentRepository.countLikeCommentById_Comment(id_post);
    }

    @Override
    public void deleteAllById_Comment(Long id_post) {
        iLikeCommentRepository.deleteAllById_Comment(id_post);
    }

    @Override
    public void remove(Long id) {
        iLikeCommentRepository.deleteById(id);
    }
}
