package casemodules4.controller;

import casemodules4.model.Comment;
import casemodules4.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping("/{idPost}/list-comment")
    public ResponseEntity<List<Comment>> showAllCommentByIdPost(@PathVariable("idPost") Long idPost){
        List<Comment> comments = commentService.findAllByPostId(idPost);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
