package casemodules4.service;

import casemodules4.model.Comment;

public interface ICommentService extends IGeneralService<Comment> {
    Iterable<Comment> findAllByIdPost(Long id);
    Iterable<Comment> deleteAllByIdComment(Long id);
}
