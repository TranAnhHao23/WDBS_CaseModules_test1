package casemodules4.model;

import javax.persistence.*;

@Entity
@Table(name = "groupMembers")
public class GroupMembers {

    @EmbeddedId
    private EmbeddedGroupMembers id;

    @ManyToOne
    @MapsId("idGroup")
    @JoinColumn(name = "idGroup")
    private Group group;

    @ManyToOne
    @MapsId("idUser")
    @JoinColumn(name = "idUser")
    private User user;

    private String role;

    public GroupMembers() {
    }

    public GroupMembers(EmbeddedGroupMembers id, Group group, User user, String role) {
        this.id = id;
        this.group = group;
        this.user = user;
        this.role = role;
    }

    public EmbeddedGroupMembers getId() {
        return id;
    }

    public void setId(EmbeddedGroupMembers id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
