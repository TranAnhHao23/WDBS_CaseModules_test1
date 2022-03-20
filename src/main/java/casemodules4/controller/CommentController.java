package casemodules4.controller;

import casemodules4.model.Comment;
import casemodules4.repository.ICommentRepository;
import casemodules4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @Autowired
    ICommentRepository commentRepository;

    @GetMapping("/{idPost}/list-comment")
    public ResponseEntity<List<Comment>> showAllCommentByIdPost(@PathVariable("idPost") Long idPost) {
        List<Comment> comments = commentService.findAllByPostId(idPost);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

//    @GetMapping("/{idComment}/detail")
//    public ResponseEntity<Optional<Comment>> getDetail(@PathVariable("idComment") Long idComment){
//        Optional<Comment> comment = commentRepository.findById(idComment);
//        return new ResponseEntity<>(comment, HttpStatus.OK);
//    }

    @PostMapping("/{idPost}/commentInPost")
    public ResponseEntity<Comment> createNewComment(@PathVariable("idPost") Long idPost, @RequestBody Comment comment) {
        if (comment == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
            System.out.println("wrong");
        }
        commentService.save(idPost, comment);
        System.out.println("done");
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }
}
