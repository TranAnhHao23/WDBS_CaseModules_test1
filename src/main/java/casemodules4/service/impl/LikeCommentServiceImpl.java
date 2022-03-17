package casemodules4.service.impl;

import casemodules4.model.LikeComment;
import casemodules4.repository.ILikeCommentRepository;
import casemodules4.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCommentServiceImpl implements ILikeCommentService {
    @Override
    public Iterable<LikeComment> findAll() {
        return null;
    }

    @Override
    public Optional<LikeComment> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public LikeComment save(LikeComment likeComment) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Optional<LikeComment> findByIdCommentAndIdUser(Long idComment, Long idUser) {
        return Optional.empty();
    }

    @Override
    public Long countLikeCommentByIdComment(Long idPost) {
        return null;
    }

    @Override
    public void deleteAllByIdComment(Long idPost) {

    }
//    @Autowired
//    private ILikeCommentRepository iLikeCommentRepository;
//
//    @Override
//    public Iterable<LikeComment> findAll() {
//        return iLikeCommentRepository.findAll();
//    }
//
//    @Override
//    public Optional<LikeComment> findByIdCommentAndIdUser(Long idComment, Long idUser) {
//        return iLikeCommentRepository.findByIdCommentAndIdUser(idComment, idUser);
//    }
//
//    @Override
//    public Optional<LikeComment> findById(Long id) {
//        return iLikeCommentRepository.findById(id);
//    }
//
//    @Override
//    public LikeComment save(LikeComment likeComment) {
//        return iLikeCommentRepository.save(likeComment);
//    }
//
//    @Override
//    public Long countLikeCommentByIdComment(Long idPost) {
//        return iLikeCommentRepository.countLikeCommentByIdComment(idPost);
//    }
//
//    @Override
//    public void deleteAllByIdComment(Long idPost) {
//        iLikeCommentRepository.deleteAllByIdComment(idPost);
//    }
//
//    @Override
//    public void remove(Long id) {
//        iLikeCommentRepository.deleteById(id);
//    }
}
