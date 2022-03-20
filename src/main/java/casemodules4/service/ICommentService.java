package casemodules4.service;

import casemodules4.model.Comment;

import java.util.List;

public interface ICommentService {

    List<Comment> findAllByPostId(Long idPost);

    Comment save(Long idOPost, Comment comment);
}
