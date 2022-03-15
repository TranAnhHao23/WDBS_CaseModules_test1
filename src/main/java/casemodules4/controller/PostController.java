package casemodules4.controller;

import casemodules4.model.Post;
import casemodules4.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {

    @Autowired
    private IPostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> showAllPost(){
        List<Post> posts = postService.findAll();
        if (posts.isEmpty()){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<Post> showDetailPost(@PathVariable("idPost") Long id){
        Post post = postService.findById(id);
        if (post == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createNewPost(@RequestBody Post post){
        if (post == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<Post> updatePost(@PathVariable("idPost") Long id, @RequestBody Post post){
        Post post1 = postService.findById(id);
        if (post1 == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setIdPost(id);
        postService.save(post);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<Post> deletePost(@PathVariable("idPost") Long id){
        Post post = postService.findById(id);
        postService.deleteById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }
    
    @GetMapping("/searchHashtag")
    public ResponseEntity<List<Post>> findAllPostByHashtag(@RequestParam("hashtag") String hashtag){
        List<Post> posts = postService.findAllByContentContaining(hashtag);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
