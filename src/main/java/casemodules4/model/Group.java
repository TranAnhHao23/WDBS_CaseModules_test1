package casemodules4.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groupTeam")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroup;

    private String name;

    private String imgUrl;

    @Transient
    private MultipartFile imgFile;

    @ManyToMany
    @JoinTable(name = "groupPost",
            joinColumns = @JoinColumn(name = "idGroup"),
            inverseJoinColumns = @JoinColumn(name = "idPost"))
    private Set<Post> postGroup;

    public Group() {
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

    public Long getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPostGroup() {
        return postGroup;
    }

    public void setPostGroup(Set<Post> postGroup) {
        this.postGroup = postGroup;
    }
}
