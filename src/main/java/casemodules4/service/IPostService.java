package casemodules4.service;

import casemodules4.model.Post;
import casemodules4.model.User;

import java.util.List;

public interface IPostService {
    List<Post> findAll();

    Post findById(Long id);

    Post save(Post post);

    void deleteById(Long id);

    List<Post> findAllByContentContaining(String hashtag);

    List<Post> findAllByUserPostIdUserOrStatus(Long id, String status);

    List<Post> findAllByUserPostAndStatus(User user, String status);

}
