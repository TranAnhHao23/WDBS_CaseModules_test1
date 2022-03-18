package casemodules4.controller;

import casemodules4.model.LikePost;
import casemodules4.service.ILikePostService;
import casemodules4.service.IPostService;
import casemodules4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/like-post")
public class LikePostController {

    @Autowired
    private ILikePostService likePostService;

    @Autowired
    private IPostService postService;

    @Autowired
    private IUserService userService;

    @GetMapping("/{idPost}/{idUser}")
    public ResponseEntity<LikePost> showDetailLikePost(@PathVariable("idPost") Long idPost, @PathVariable("idUser") Long idUser){
        LikePost likePost = likePostService.findLikeByPostIdAndLikeId(idPost, idUser);
        return new ResponseEntity<>(likePost, HttpStatus.OK);
    }

    @GetMapping("/{idPost}/{idUser}/showLike")
    public ResponseEntity<Integer> showAllLikePost(@PathVariable("idPost") Long idPost, @PathVariable("idUser") Long idUser){
        Integer likes = likePostService.countByPost_IdPost(idPost);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }

    @GetMapping("/{idPost}/{idUser}/checkLike")
    public ResponseEntity<Integer> checkAllLikePost(@PathVariable("idPost") Long idPost, @PathVariable("idUser") Long idUser){
        LikePost likePost = likePostService.findLikeByPostIdAndLikeId(idPost, idUser);
        if (likePost != null){
            likePostService.deleteLikePost(likePost);
        } else {
            likePostService.save(new LikePost(postService.findById(idPost), userService.findById(idUser)));
        }
        Integer likes = likePostService.countByPost_IdPost(idPost);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}
