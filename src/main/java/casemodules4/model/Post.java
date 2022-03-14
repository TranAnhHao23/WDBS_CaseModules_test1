package casemodules4.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    private String content;

    private String imgUrl;

    @Transient
    private MultipartFile imgFile;

    @ManyToOne
    private User userPost;

    @OneToMany
    @JoinTable(name = "commentInPost")
    private Set<Comment> comments;

    @ManyToMany(mappedBy = "postGroup", fetch = FetchType.LAZY)
    private Set<Group> groups;

    public Post() {
    }

    public Post(Long idPost, String content, String imgUrl, MultipartFile imgFile, User userPost, Set<Comment> comments, Set<Group> groups) {
        this.idPost = idPost;
        this.content = content;
        this.imgUrl = imgUrl;
        this.imgFile = imgFile;
        this.userPost = userPost;
        this.comments = comments;
        this.groups = groups;
    }

    public Long getIdPost() {
        return idPost;
    }

    public void setIdPost(Long idPost) {
        this.idPost = idPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public User getUserPost() {
        return userPost;
    }

    public void setUserPost(User userPost) {
        this.userPost = userPost;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
}
