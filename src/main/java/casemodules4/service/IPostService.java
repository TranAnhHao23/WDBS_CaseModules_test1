package casemodules4.service;

import casemodules4.model.Post;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<Post> findAll();

    Optional<Post> findById(Long id);

    Post save(Post post);

    void deleteById(Long id);

    List<Post> findAllByContentContaining(String hashtag);

    List<Post> findAllByUserPostIdUser(Long id);
}
