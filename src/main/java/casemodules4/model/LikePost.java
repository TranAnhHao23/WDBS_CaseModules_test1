package casemodules4.model;

import javax.persistence.*;

@Entity
@Table(name = "likePost")
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLikePost;

    @ManyToOne
    @JoinColumn(name = "id_post")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "id_like")
    private User userLike;

    public LikePost() {
    }

    public LikePost(Long idLikePost, Post post, User userLike) {
        this.idLikePost = idLikePost;
        this.post = post;
        this.userLike = userLike;
    }

    public Long getIdLikePost() {
        return idLikePost;
    }

    public void setIdLikePost(Long idLikePost) {
        this.idLikePost = idLikePost;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUserLike() {
        return userLike;
    }

    public void setUserLike(User userLike) {
        this.userLike = userLike;
    }
}
