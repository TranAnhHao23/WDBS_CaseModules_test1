package casemodules4.service;

import casemodules4.model.Comment;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findAllById_Post(Long id);
    Iterable<Comment> deleteAllById_Comment(Long id);
}
