package casemodules4.controller;

import casemodules4.model.Post;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
    @Value("${UPLOAD_FILE:}")
    private String fileUpload;

    @Value("src/main/resources/static/images")
    private String view;
    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Post>> showAllPost(){
        Iterable<Post> posts = postService.findAll();
        if (!posts.iterator().hasNext()){
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<Post> showDetailPost(@PathVariable("idPost") Long id){
        Optional<Post> post = postService.findById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(()
                -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{user_id}")
    public ResponseEntity<Post> createNewPost(@PathVariable("user_id") Long user_id,
                                              @RequestPart("post") Post post, @RequestPart("file")MultipartFile file){
        post.setDateCreated(LocalDate.now());
        String fileName = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        }catch (IOException io){
            io.printStackTrace();
        }
        post.setImgFile(view + fileName);
        post.setUser(userService.findById(user_id));
        Post postCreate = postService.save(post);
        return new ResponseEntity<>(postCreate, HttpStatus.CREATED);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<Post> updatePost(@PathVariable("idPost") Long id, @RequestPart("postUpdate") Post postUpdate,
                                           @RequestPart("file") MultipartFile file) {
        Optional<Post> post1 = postService.findById(id);
        if (!post1.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postUpdate.setIdPost(post1.get().getIdPost());
        if (file.getSize() != 0){
            String fileName = file.getOriginalFilename();
            try {
                FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            postUpdate.setImgFile(view + fileName);
        }else {
            postUpdate.setImgFile(post1.get().getImgFile());
        }
        postUpdate = postService.save(postUpdate);
        return new ResponseEntity<>(postUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<Post> deletePost(@PathVariable("idPost") Long id){
        Optional<Post> post = postService.findById(id);
        if (!post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deleteById(id);
        return  new ResponseEntity<>(post.get(), HttpStatus.OK);
    }
    
    @GetMapping("/searchHashtag")
    public ResponseEntity<List<Post>> findAllPostByHashtag(@RequestParam("hashtag") String hashtag){
        List<Post> posts = postService.findAllByContentContaining(hashtag);
        if (!posts.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
