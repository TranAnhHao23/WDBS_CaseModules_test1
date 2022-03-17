package casemodules4.service.impl;

import casemodules4.model.Comment;
import casemodules4.repository.ICommentRepository;
import casemodules4.service.ICommentService;
import casemodules4.service.ILikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ICommentRepository iCommentRepository;

    @Autowired
    private ILikeCommentService iLikeCommentService;

    @Override
    public Iterable<Comment> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        iLikeCommentService.deleteAllById_Comment(id);
        iCommentRepository.deleteById(id);
    }

    @Override
    public Iterable<Comment> findAllById_Post(Long id) {
        return iCommentRepository.findAllById_Post(id);
    }

    @Override
    public Iterable<Comment> deleteAllById_Comment(Long id) {
        return iCommentRepository.deleteAllById_Comment();
    }
}
