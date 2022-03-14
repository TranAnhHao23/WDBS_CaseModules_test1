package casemodules4.model;

import javax.persistence.*;

@Entity
@Table(name = "friendList")
public class FriendList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFriendList;

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;

    private String status;

    public FriendList() {
    }

    public FriendList(Long idFriendList, User userFrom, User userTo, String status) {
        this.idFriendList = idFriendList;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.status = status;
    }

    public Long getIdFriendList() {
        return idFriendList;
    }

    public void setIdFriendList(Long idFriendList) {
        this.idFriendList = idFriendList;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
