package casemodules4.service.impl;

import casemodules4.model.Post;
import casemodules4.repository.IPostRepository;
import casemodules4.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        if (postRepository.findById(id).isPresent()){
            return postRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByContentContaining(String hashtag) {
        return postRepository.findAllByContentContaining(hashtag);
    }

    @Override
    public List<Post> findAllByUserPostIdUser(Long id) {
        return postRepository.findAllByUserPostIdUser(id);
    }
}
