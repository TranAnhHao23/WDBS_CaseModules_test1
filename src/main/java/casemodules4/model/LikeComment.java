package casemodules4.model;

import javax.persistence.*;

@Entity
@Table(name = "likeComment")
public class LikeComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLikeComment;

    @ManyToOne
    @JoinColumn(name = "id_comment")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "id_like")
    private User userLike;

    public LikeComment() {
    }

    public LikeComment(Long idLikeComment, Comment comment, User userLike) {
        this.idLikeComment = idLikeComment;
        this.comment = comment;
        this.userLike = userLike;
    }

    public Long getIdLikeComment() {
        return idLikeComment;
    }

    public void setIdLikeComment(Long idLikeComment) {
        this.idLikeComment = idLikeComment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUserLike() {
        return userLike;
    }

    public void setUserLike(User userLike) {
        this.userLike = userLike;
    }
}
