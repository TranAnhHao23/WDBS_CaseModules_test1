package casemodules4.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groupTeam")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGroup;

    private String name;

    @ManyToMany
    @JoinTable(name = "groupPost",
            joinColumns = @JoinColumn(name = "idGroup"),
            inverseJoinColumns = @JoinColumn(name = "idPost"))
    private Set<Post> postGroup;

    public Group() {
    }

    public Group(Long idGroup, String name, Set<Post> postGroup) {
        this.idGroup = idGroup;
        this.name = name;
        this.postGroup = postGroup;
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
