package casemodules4.service.impl;

import casemodules4.model.Comment;
import casemodules4.repository.ICommentRepository;
import casemodules4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository commentRepository;

    @Override
    public List<Comment> findAllByPostId(Long idPost) {
        return commentRepository.findAllByPostId(idPost);
    }
}
